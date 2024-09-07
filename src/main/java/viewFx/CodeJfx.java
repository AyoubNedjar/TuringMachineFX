package viewFx;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import model.Code;
import model.GameFacade;
import model.Observable;
import model.Observer;

import java.util.ArrayList;
import java.util.List;


public class CodeJfx extends VBox implements Observer{

    private GameFacade gameFacade;

    private Button selectedButtonCol1;
    private Button selectedButtonCol2;
    private Button selectedButtonCol3;

    private List<Button> buttonList1 = new ArrayList<>();
    private  List<Button> buttonList2 = new ArrayList<>();
    private List<Button> buttonList3  =new ArrayList<>();



    public CodeJfx(GameFacade gameFacade) {

        this.gameFacade = gameFacade;
        gameFacade.addObserver(this);
        GridPane gp  = new GridPane();
        Label lb = new Label("Enter Code");
        lb.setFont(Font.font("Arial", FontWeight.BOLD, 14)); // Mettre en gras (bold) et définir la taille de la police

        gp.setPadding(new Insets(10));
        gp.setHgap(10);
        gp.setVgap(10);

        // Création des 15 boutons et ajout dans la grille
        for (int row = 1; row <= 5; row++) {
            for (int col = 0; col < 3; col++) {
                Button button = new Button(""+ row);
                int finalCol = col;
                button.setOnAction(event -> handleButtonClick(button, finalCol));
                gp.add(button, col, row);
            }
        }
        gp.setStyle("-fx-border-color: black; -fx-border-width: 3px; -fx-background-radius: 1px;");
        // Bouton "OK" pour valider le code sélectionné

        Button okButton = new Button("OK");
        okButton.setOnAction(event -> handleOKButtonClick());
        gp.add(okButton, 1, 6);


        this.getChildren().addAll(lb, gp);

    }

    private void handleButtonClick(Button button, int col) {



        switch (col) {
            case 0://colonne 1
            {

                    if(buttonList1.isEmpty()){
                        buttonList1.add(button);
                        selectedButtonCol1 = button;
                        button.setStyle("-fx-border-color: blue; -fx-border-width: 2px;");
                    }else {
                        selectedButtonCol1 = button;
                        buttonList1.get(buttonList1.size()-1).setStyle(null);
                        buttonList1.remove(buttonList1.get(buttonList1.size()-1));
                        buttonList1.add(button);
                        button.setStyle("-fx-border-color: blue; -fx-border-width: 2px;");
                    }

                break;
            }
            case 1://colonne 2
            {
                if(buttonList2.isEmpty()){
                    buttonList2.add(button);
                    selectedButtonCol2 = button;
                    button.setStyle("-fx-border-color: blue; -fx-border-width: 2px;");
                }else {
                    selectedButtonCol2 = button;
                    buttonList2.get(buttonList2.size()-1).setStyle(null);
                    buttonList2.remove(buttonList2.get(buttonList2.size()-1));
                    buttonList2.add(button);
                    button.setStyle("-fx-border-color: blue; -fx-border-width: 2px;");
                }

                break;
            }
            case 2://colonne 3
            {
                if(buttonList3.isEmpty()){
                    buttonList3.add(button);
                    selectedButtonCol3 = button;
                    button.setStyle("-fx-border-color: blue; -fx-border-width: 2px;");
                }else {
                    selectedButtonCol3 = button;
                    buttonList3.get(buttonList3.size()-1).setStyle(null);
                    buttonList3.remove(buttonList3.get(buttonList3.size()-1));
                    buttonList3.add(button);
                    button.setStyle("-fx-border-color: blue; -fx-border-width: 2px;");
                }
                break;
            }
        }
    }
    private void handleOKButtonClick() {
        int selectedNumberCol1  = Integer.parseInt(selectedButtonCol1.getText());
        int selectedNumberCol2 = Integer.parseInt(selectedButtonCol2.getText());
        int selectedNumberCol3 = Integer.parseInt(selectedButtonCol3.getText());


        gameFacade.enterCode(selectedNumberCol1, selectedNumberCol2, selectedNumberCol3);

    }


    @Override
    public void update(Observable observable, int arg) {

        GameFacade gameFacade = (GameFacade) observable;

        switch (arg){
            case 2 :{
                selectedButtonCol1.setStyle("");
                selectedButtonCol2.setStyle("");
                selectedButtonCol3.setStyle("");

            }
        }
    }
}
