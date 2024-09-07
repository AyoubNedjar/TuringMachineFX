package controller;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import model.GameFacade;
import model.LoadGameFromFile;
import viewFx.BootomJfx;
import viewFx.GameInfo;
import viewFx.ValidatorList;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.atomic.AtomicInteger;


public class MainJfx extends Application {


    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage stage) throws Exception {

        Scene scene = createFirstPage(stage);
        stage.setScene(scene);


        stage.show();


    }

    public Scene createFirstPage(Stage stage) {
        stage.setTitle("Turing Machine");

        VBox vbox = new VBox();
        vbox.setPadding(new Insets(10));
        vbox.setSpacing(10);
        vbox.setAlignment(Pos.CENTER);
        vbox.setStyle("-fx-background-color: lightgreen;");

        Image image = new Image(getClass().getResource("/validatorPictures/tur.png").toExternalForm());
        ImageView imageView = new ImageView(image);



        Label npb = new Label("Choisissez un problème :");
        npb.setTextFill(Color.WHITE);
        ComboBox<Integer> integerComboBox = new ComboBox<>();
        integerComboBox.getItems().addAll(LoadGameFromFile.getInstance().getProblemHashMap().keySet());

        AtomicInteger pb = new AtomicInteger();
        integerComboBox.setOnAction(e -> {
            pb.set(integerComboBox.getSelectionModel().getSelectedItem());
            System.out.println("Problème choisi : " + pb.get());
        });

        vbox.getChildren().addAll(imageView, npb, integerComboBox);


        Button startGameButton = new Button("Lancer la partie");
        startGameButton.setOnAction(e -> {
            // Création et affichage de la nouvelle scène pour le jeu
            Scene gameScene = createGameScene(pb.get(), stage); // Méthode pour créer la nouvelle scène
            stage.setScene(gameScene);
            stage.show();

        });


        Button randomGameButton = new Button("Lancer partie au hasard");
        List<Integer> randomList = new ArrayList<>();
        for (int i = 1; i < 12; i++) {
            randomList.add(i);
        }
        randomGameButton.setOnAction(e -> {
            // Création et affichage de la nouvelle scène pour le jeu
            int nbChoose = randomNbChoose(randomList);
            Scene gameScene = createGameScene(nbChoose, stage); // Méthode pour créer la nouvelle scène
            stage.setScene(gameScene);
            stage.show();
        });

        vbox.getChildren().addAll(startGameButton, randomGameButton);

        stage.widthProperty().addListener((obs, oldVal, newVal) ->
                imageView.fitWidthProperty().bind(stage.widthProperty().multiply(0.5))
        );

        stage.heightProperty().addListener((obs, oldVal, newVal) ->
                imageView.fitHeightProperty().bind(stage.heightProperty().multiply(0.5))
        );

        return new Scene(vbox, 800, 600);
    }

    public static int randomNbChoose(List<Integer> liste) {
        // Vérification si la liste est vide
        if (liste == null || liste.isEmpty()) {
            throw new IllegalArgumentException("La liste ne peut pas être vide ou nulle.");
        }

        // Génération d'un nombre aléatoire en utilisant la taille de la liste comme borne supérieure
        Random random = new Random();
        int index = random.nextInt(liste.size());

        // Retourner le nombre correspondant à l'index généré aléatoirement
        return liste.get(index);
    }

    private Scene createGameScene(int pb, Stage stage) {

        VBox vBox = new VBox();

        vBox.setStyle("-fx-background-color: lightgreen;");
        GameFacade game = new GameFacade();
        game.start(pb);
        GameInfo infos = new GameInfo(game);
        game.addObserver(infos);
        ValidatorList validatorList = new ValidatorList(game);
        game.addObserver(validatorList);
        BootomJfx bootomView  = new BootomJfx(game, stage);
        game.addObserver(bootomView);

        vBox.getChildren().addAll(infos, validatorList, bootomView);

        return new Scene(vBox, 800, 600);


    }

    public void restart(Stage primaryStage) {
        primaryStage.close(); // Fermez la scène actuelle
        Scene firstScene = createFirstPage(primaryStage); // Obtenez la scène initiale
        primaryStage.setScene(firstScene); // Définissez la nouvelle scène
        primaryStage.show(); // Affichez la nouvelle scène
    }



}
