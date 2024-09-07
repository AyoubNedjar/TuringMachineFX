package viewFx;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import model.Code;
import model.GameFacade;
import model.Observable;
import model.Observer;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Stack;

public class HistoryJfx extends VBox implements Observer {

    private VBox vBox;
    private Stack<HBox> removedEntriesStack;


    public HistoryJfx() {
        vBox = new VBox();
       removedEntriesStack =new Stack<>();
        vBox.setSpacing(10);
        vBox.setPadding(new Insets(10));
        vBox.setStyle("-fx-border-color: black; -fx-border-width: 3px; -fx-padding: 5px; -fx-background-radius: 5px;");

        Label lb = new Label("History");
        lb.setFont(Font.font("Arial", FontWeight.BOLD, 14));
        this.getChildren().addAll(lb, vBox);

    }

    public void addCodeEntry(Code code, Map<Integer, Boolean> validators) {

        Label codeLabel = new Label("Code: " + code.toString());

        HBox hBoxValid = new HBox();
        for (Map.Entry<Integer, Boolean> v : validators.entrySet()){
            Label validatorsLabel = new Label((v.getKey()+1)+"");
            if(v.getValue()){
                validatorsLabel.setStyle("-fx-background-color: green");
            }else{
                validatorsLabel.setStyle("-fx-background-color: red");
            }

            hBoxValid.getChildren().add(validatorsLabel);
        }
        hBoxValid.setSpacing(2);


        HBox entryBox = new HBox(10); // Création d'une HBox pour la ligne
        entryBox.getChildren().addAll(codeLabel, hBoxValid);

        // Centrer horizontalement les éléments dans la HBox
        codeLabel.setAlignment(Pos.CENTER);
        hBoxValid.setAlignment(Pos.CENTER);
        vBox.getChildren().add(entryBox); // Ajout de la nouvelle ligne à la VBox
    }

    public void removeCodeEntry() {
        if(!vBox.getChildren().isEmpty()){
            HBox removedEntry = (HBox) vBox.getChildren().remove(vBox.getChildren().size() - 1);
            removedEntriesStack.push(removedEntry); // Ajout de l'entrée supprimée à la pile
        }

    }
    public void readdCodeEntry() {
        if (!removedEntriesStack.isEmpty()) {
            HBox removedEntry = removedEntriesStack.pop(); // Récupération de l'entrée supprimée
            vBox.getChildren().add(removedEntry); // Réajout de l'entrée dans la VBox
        }
    }

    @Override
    public void update(Observable observable, int arg) {
        GameFacade gameFacade = (GameFacade) observable;

        switch (arg){

            case 2  :{
                break;
            }
            case 3 :{
                break;
            }
            case 4 : {//nextRound
                    addCodeEntry(gameFacade.getPrecedent(), gameFacade.getLastResult());
                break;
            }
            case 5  : {//undo
                removeCodeEntry();

                break;
            }
            case 6 : {//redo
                readdCodeEntry();
                break;
            }

        }
    }
}
