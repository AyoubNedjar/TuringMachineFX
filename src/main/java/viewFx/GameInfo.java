package viewFx;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import model.GameFacade;
import model.Observer;
import model.Observable;

public class GameInfo extends HBox implements Observer {
    private Label problemNumberLabel;
    private Label scoreLabel;
    private Label chanceLabel;

    private Label difficulty;

    public GameInfo(GameFacade gameFacade) {
        // Création des labels pour afficher les informations du jeu

        problemNumberLabel = new Label("Problem: "+gameFacade.getProblem().getnProblem());
        scoreLabel = new Label("Score: "+gameFacade.getGame().getRound());
        chanceLabel = new Label("luck: "+gameFacade.getProblem().getLuck());
        difficulty = new Label("Difficulty: "+gameFacade.getProblem().getDifficulty());

        // Ajout des labels à la vue GameInfo (HBox)
        this.getChildren().addAll(problemNumberLabel, scoreLabel, chanceLabel, difficulty);
        this.setAlignment(Pos.CENTER);
        this.setSpacing(10);
        this.setPadding(new Insets(5));
    }

    @Override
    public void update(Observable observable, int arg) {

        GameFacade gameFacade = (GameFacade) observable;
        switch (arg) {

            case 4: {

                problemNumberLabel = new Label("Problem: "+gameFacade.getProblem().getnProblem());
                scoreLabel = new Label("Score: "+gameFacade.getGame().getRound());
                chanceLabel = new Label("luck: "+gameFacade.getProblem().getLuck());
                difficulty = new Label("Difficulty: "+gameFacade.getProblem().getDifficulty());

                break;
            }

        }

    }
}

