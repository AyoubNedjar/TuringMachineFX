package viewFx;

import controller.MainJfx;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;
import model.GameFacade;
import model.Observable;
import model.Observer;

import java.util.Optional;

public class BootomJfx extends HBox implements Observer {

    private GameFacade gameFacade;
    private CodeJfx codeElement;

    private HistoryJfx historyJfx;
    private Button undoButton;
    private Button redoButton;

    private Button nextRound;
    private Button testCode;
    private Button quit;

    private Label lb;

    private HistoryDefence defence;

    private Scene scene;
    private Stage primaryStage;



    public BootomJfx(GameFacade gameFacade,  Stage primaryStage) {
        this.gameFacade = gameFacade;
        this.primaryStage = primaryStage;
        this.setSpacing(100); // Espacement entre les éléments de la HBox
        this.setPadding(new Insets(0, 10, 0, 10));
        // Création des éléments nécessaires
        codeElement = new CodeJfx(gameFacade); // Initialisation d'un objet Code, ajustez cela selon votre implémentation
        undoButton = new Button("Revenir en arrière");
        redoButton = new Button("Revenir en avant");
        nextRound = new Button("Prochaine manche");
        testCode = new Button("Tester son code");
        quit = new Button("Quitter le jeu");

        historyJfx = new HistoryJfx();
        gameFacade.addObserver(historyJfx);
        lb = new Label("Code Actuel : Aucun");
        lb.setFont(Font.font("Arial", FontWeight.BOLD, 14)); // Mettre en gras (bold) et définir la taille de la police
        lb.setStyle("-fx-border-color: black; -fx-border-width: 3px; -fx-padding: 5px; -fx-background-radius: 5px;");


        VBox vBox = new VBox();
        vBox.setSpacing(20);
        vBox.getChildren().addAll(undoButton, redoButton, nextRound, testCode, quit );
        // Ajout des actions pour les boutons (vous pouvez ajuster les actions selon vos besoins)
        defence = new HistoryDefence(historyJfx);
        gameFacade.addObserver(defence);
        nextRound.setOnAction(event ->{
            gameFacade.nextRound();
            defence.show();

        });
        undoButton.setOnAction(event -> {
            gameFacade.undo();
        });

        redoButton.setOnAction(event -> {
           gameFacade.redo();
        });
        testCode.setOnAction(event -> {
            boolean ok = gameFacade.isCorrect();

            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Confirmation");
            alert.setHeaderText(null);

            if (ok) {
                alert.setContentText("BRAVO, vous avez gagné. Voulez-vous rejouer une partie ?");
            } else {
                alert.setContentText("Mince, vous avez perdu. Voulez-vous rejouer une partie ?");
            }

            ButtonType ouiButton = new ButtonType("Oui");
            ButtonType nonButton = new ButtonType("Non");

            alert.getButtonTypes().setAll(ouiButton, nonButton);
            Optional<ButtonType> result = alert.showAndWait();

            if (result.isPresent()) {
                if (result.get() == ouiButton) {
                    // Redémarrer la partie
                    MainJfx mainInstance = new MainJfx();
                    mainInstance.restart(primaryStage);
                    primaryStage.show();
                } else if (result.get() == nonButton) {
                    // Fermer la fenêtre
                    primaryStage.close();
                }
            }
        });

        quit.setOnAction(e->{
            gameFacade.quitGame();
            primaryStage.close();
        });


        // Ajout des éléments à la HBox
        this.getChildren().addAll(lb, codeElement, vBox);
        HBox.setMargin(codeElement, new Insets(25, 0, 0, 0));
        HBox.setMargin(historyJfx, new Insets(25, 0, 0, 0));
        HBox.setMargin(vBox, new Insets(35, 0, 0, 0));

        this.setAlignment(Pos.CENTER);
        // Ajoutez d'autres configurations nécessaires pour la HBox si besoin
    }

    @Override
    public void update(Observable observable, int arg) {

        GameFacade gameFacade = (GameFacade) observable;

        switch (arg){
            case 2  :{

                lb.setText("Code Actuel : "+gameFacade.getGame().getPlayerCode().toString()+"");

                break;
            }
            case 3 :{

                break;

            }
            case 4 : {
                lb.setText("Code Actuel : - - -");
                break;
            }
            case 5  : {
                if(gameFacade.getGame().getValidatorResult().isEmpty()){
                    lb.setText("Code Actuel : "+gameFacade.getPrecedent().toString());
                }

                break;
            }
            case 6 : {
                break;
            }


        }
    }
}
