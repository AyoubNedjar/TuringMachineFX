package viewFx;

import javafx.scene.Scene;
import javafx.stage.Stage;
import model.Observable;
import model.Observer;

public class HistoryDefence implements Observer {
    private Stage historyStage;
    private Scene scene;
    public HistoryDefence(HistoryJfx historyFenetre) {

        scene  = new Scene(historyFenetre, 400, 300);
        historyStage = new Stage();
        historyStage.setScene(scene);
    }

    public void show() {
        historyStage.show();
    }

    @Override
    public void update(Observable observable, int arg) {
        switch (arg){
            case 4 : {
                show();
            }
        }
    }
}
