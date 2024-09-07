package viewFx;

import javafx.scene.control.ScrollPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.stage.Stage;
import model.GameFacade;
import model.Observable;
import model.Observer;

import java.util.ArrayList;
import java.util.List;

import javafx.scene.control.ScrollPane;
import javafx.scene.layout.HBox;

public class ValidatorList extends ScrollPane implements Observer {

    public ValidatorList(GameFacade gameFacade) {

        this.setFitToWidth(true);

        HBox hbox = new HBox();
        hbox.setSpacing(20);
        initializeValidators(gameFacade, hbox);

        this.setContent(hbox);
    }

    private void initializeValidators(GameFacade gameFacade, HBox hbox) {
        List<Integer> integerList = getIndexFromProblem(gameFacade);
        String[] letters = {"A", "B", "C", "D", "E", "F"}; // Les lettres de base

        for (int i = 0; i < integerList.size(); i++) {
            ValidatorView validatorView = new ValidatorView(gameFacade, integerList.get(i), letters[i], i);
            validatorView.setScaleX(0.7); // Réduire l'échelle horizontale
            validatorView.setScaleY(0.7);
            gameFacade.addObserver(validatorView);
            hbox.getChildren().add(validatorView); // Ajouter le ValidatorView à la HBox
        }
    }

    private List<Integer> getIndexFromProblem(GameFacade game){
        return game.getProblem().getValidIndexList();
    }

    @Override
    public void update(Observable observable, int arg) {
        // Logique de mise à jour si nécessaire
    }
}
