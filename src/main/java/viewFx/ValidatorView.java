package viewFx;

import javafx.geometry.Pos;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import model.GameFacade;
import model.Observable;
import model.Observer;

import java.util.Map;

public class ValidatorView extends VBox implements Observer {


    private GameFacade gameFacade;
    private final Image pictureRobot;
    private final char letterPlace;
    private final Image pictureValidator;

    private final int nvalid ;

    private boolean isJust;
    private final int placeVal;
    private final Circle statusIndicator;
    private final Button checkButton;

    public ValidatorView(GameFacade gameFacade, int nval, String placeRobot , int placeVal) {
        this.gameFacade  =gameFacade;
        this.placeVal = placeVal;
        this.letterPlace = placeRobot.charAt(0);
        String robotPicture = "/robots/robot"+letterPlace+".png";
        System.out.println(robotPicture);
        pictureRobot = new Image(getClass().getResourceAsStream(robotPicture));

        this.nvalid = nval;
        // Création du cercle pour l'indicateur de statut
        String validatorPicture = "/validatorPictures/card"+nvalid+".png";
        pictureValidator = new Image(getClass()
                .getResourceAsStream(validatorPicture));


        HBox hBox = new HBox();

        statusIndicator = new Circle(10);
        statusIndicator.setFill(Color.LIGHTGRAY); // Couleur par défaut

        // Création du bouton pour vérifier le validateur
        checkButton = new Button("Check");

        checkButton.setOnAction(event -> {

            if (gameFacade.getGame().getValidatorPlayed().size()<3){
                if(gameFacade.getGame().getPlayerCode()!=null){// pour eviter d'utiliser des validateurs sans avoir
                    // entré de code
                    gameFacade.chooseValidator(placeVal);
                }else {
                    Alert alert = new Alert(Alert.AlertType.INFORMATION);
                    alert.setTitle("Choix de validateurs");
                    alert.setHeaderText(null);
                    alert.setContentText("Veuillez entrer un code avant d'utiliser les utilisateurs");
                    alert.showAndWait();
                }

            }else {
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Choix de validateurs");
                alert.setHeaderText(null);
                alert.setContentText("Vous avez déjà choisi vos trois validateurs.");
                alert.showAndWait();
            }

        });
        hBox.getChildren().addAll(checkButton, statusIndicator);
        ImageView robot = new ImageView(pictureRobot);
        ImageView validatorPhoto = new ImageView(pictureValidator);
        validatorPhoto.setFitWidth(this.getWidth());

        robot.setFitWidth(50);
        robot.setPreserveRatio(true);


        VBox imagesBox = new VBox();
        imagesBox.getChildren().addAll(robot, validatorPhoto);
        imagesBox.setAlignment(Pos.CENTER);



        // Ajout des éléments à la vue Validator (HBox)
        this.getChildren().addAll(imagesBox, hBox);
        this.setSpacing(15);
    }

    public void setStatusIndicator(boolean isValid) {
        if (isValid) {
            statusIndicator.setFill(Color.GREEN); // Cercle vert pour vrai
        } else {
            statusIndicator.setFill(Color.RED); // Cercle rouge pour faux
        }
    }

    @Override
    public void update(Observable observable, int arg) {

        GameFacade gameFacade = (GameFacade) observable;

        switch (arg){

            case 2  :{

            }
            case 3 :{
                    //si il a trouver il retourne boolean sinon null
                    Boolean result = gameFacade.getGame().getValidatorResult().getOrDefault(placeVal, null);
                    if(result!=null){
                        System.out.println("rsultat : "+result);
                        setStatusIndicator(result);
                    }
                break;

            }
            case 4 : {
                statusIndicator.setFill(Color.GRAY);
                break;
            }
            case 5  : {

                Boolean result = gameFacade.getGame().getValidatorResult().getOrDefault(placeVal, null);
                if(result!=null){
                    setStatusIndicator(result);
                }else{
                    statusIndicator.setFill(Color.GRAY);
                }
                break;
            }
            case 6 : {
                break;
            }


        }
    }
}
