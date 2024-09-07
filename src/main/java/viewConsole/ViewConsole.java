package viewConsole;

import model.Observer;
import model.*;
import model.Observable;
import model.Validator.Validator;

import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ViewConsole implements Observer {

    private final GameFacade gameFacade;

    public ViewConsole(GameFacade gameFacade) {
        this.gameFacade = gameFacade;
        this.gameFacade.addObserver(this);
    }

    public void startGame() {
        messageStart();

        Scanner clavier = new Scanner(System.in);

        boolean badRep = false;
        boolean isGood;
        do {
            System.out.println(
                    "Voulez vous choisir un problème en fonction de sa difficulté ?," +
                            " repondez par 'o' pour oui et 'n' pour non");

            String rep = clavier.nextLine();

            isGood = rep.length() == 1;

            if (isGood) {
                switch (rep.charAt(0)) {
                    case 'o': {
                        displayProblemsWithDifficutltyOrNot(true);
                        executeProblemFromDifficulty();
                        break;
                    }
                    case 'n': {
                        displayProblemsWithDifficutltyOrNot(false);
                        executeProblemSimple();
                        break;
                    }
                    default:
                        badRep = true;
                        System.out.println("Veuillez entrez 'o' pour oui et 'n' pour non !");
                }
            }
        }
        while (badRep || !isGood);

    }


    private void afficherJeuEnCours() {
        displayValidators();
        displayScore();
    }

    private void selecteCommande() {
        Scanner clavier = new Scanner(System.in);
        boolean continuee;
        do {
            System.out.print("Faites votre choix : ");
            String rep = clavier.nextLine().toLowerCase();
            continuee = false;
            switch (rep) {
                case "validator": {
                    if (gameFacade.getGame().getValidatorPlayed().size() < 3) {
                        choosevalidatorsFromUser();
                        displayResultValidators();
                    } else {
                        System.out.println("tu as dépassé le nombre de validateur autorisés par manche");
                        System.out.println("Passe à la manche suivante");
                        System.out.println(" ou utilise Undo ;) ");
                        displayCommand();
                    }

                    break;
                }
                case "pass": {
                    gameFacade.nextRound();
                    break;
                }
                case "test": {
                    if (gameFacade.isCorrect()) {
                        System.out.println("Bravoo!!!, Vous venez de decouvrir le code secret");
                    } else {
                        System.out.println("Mince , Vous avez perdu");
                    }
                    break;
                }
                case "quit": {
                    gameFacade.quitGame();
                    break;
                }
                case "undo": {
                    gameFacade.undo();
                    break;
                }
                case "redo": {
                    gameFacade.redo();
                    break;
                }

                default:
                    continuee = true;
                    System.out.println("veuillez entrez une commande valide");
            }
        } while (continuee);
    }

    private void codeCommande() {
        if (gameFacade.getGame().getValidatorPlayed().isEmpty()) {
            Code code = chooseCodeFromUser();
            gameFacade.enterCode(code.getNb1(), code.getNb2(), code.getNb3());
        } else {
            System.out.println("Vous ne pouvez pas entrer de nouveau code pendant la manche en cours");
        }
    }

    private void displayResultValidators() {
        for (Map.Entry<Integer, Boolean> b : gameFacade.getGame().getValidatorResult().entrySet()) {
            System.out.println(" validator " + (b.getKey() + 1) + ",  resultat :  " + b.getValue());
        }
    }

    private void displayCommand() {
        System.out.println("-----------------------------------------");
        System.out.println("|Commandes disponibles :");
        System.out.println("|1. code - Entrer un code");
        System.out.println("|2. validator - Choisir un validateur");
        System.out.println("|3. pass - Passer à la manche suivante");
        System.out.println("|4. test - Vérifier le code");
        System.out.println("|5. quit - Quitter le jeu");
        System.out.println("|6. undo - Revenir en arrière");
        System.out.println("|7. redo - Rétablir une action annulée");
        System.out.println("-----------------------------------------");
        selecteCommande();
    }

    private void displayScore() {
        System.out.println("Nombre de validateurs vérifiés : " + gameFacade.getGame().getValidatorPlayed().size());
        System.out.println("Vous etes à la mache " + gameFacade.getGame().getRound());
        System.out.println();
    }

    public Code chooseCodeFromUser() {
        Scanner scanner = new Scanner(System.in);

        // Expression régulière pour un code à trois chiffres
        String regex = "\\b\\d{3}\\b";
        Pattern pattern = Pattern.compile(regex);


        boolean toLong = false;
        Code code = new Code(0, 0, 0);
        do {

            System.out.println("veuillez entrez le code à 3 chiffres MAXIMUM , chaque chiffre doit etre compris entre " +
                    "1 et 5");

            String input = scanner.nextLine();

            if (input.length() > 3) {
                toLong = true;
            } else {
                toLong = false;
            }
            Matcher matcher = pattern.matcher(input);

            if (matcher.matches()) {
                // Récupération de chaque chiffre pour créer un nouveau Code
                int chiffre1 = Character.getNumericValue(input.charAt(0));
                int chiffre2 = Character.getNumericValue(input.charAt(1));
                int chiffre3 = Character.getNumericValue(input.charAt(2));
                code = new Code(chiffre1, chiffre2, chiffre3);
            }

        } while (!isCodeCorrect(code) || toLong);

        return code;
    }

    private boolean isCodeCorrect(Code code) {
        return (code.getNb1() > 0 && code.getNb1() <= 5)
                && (code.getNb2() > 0 && code.getNb2() <= 5)
                && (code.getNb3() > 0 && code.getNb3() <= 5);
    }

    private void displayValidators() {
        System.out.println("Voici les validateurs que vous pouvez choisir , MAXIMUM 3 par mache !");

        List<Validator> possibles = gameFacade.getGame().getValidatorPossibles();
        for (int i = 1; i <= possibles.size(); i++) {
            System.out.println("validateur  : " + i + ", " + possibles.get(i - 1).getDescription());
        }
        System.out.println();
    }

    public void choosevalidatorsFromUser() {
        displayValidators();

        List<Integer> indexListChoosed = new ArrayList<>();

        int indexValidator = 0;
        do {

            System.out.println("choisissez le numéro d'un validateur valide");
            try {
                Scanner clavier = new Scanner(System.in);
                indexValidator = clavier.nextInt();
            } catch (Exception e) {
                System.out.println("Merci de taper le bon numéro de validateur");
            }

        } while (!isCorrectIndex(indexValidator, gameFacade.getGame().getValidatorPossibles(),
                indexListChoosed));

        gameFacade.chooseValidator(indexValidator - 1);
    }


    private boolean isCorrectIndex(int index, List<Validator> validators, List<Integer> integerList) {
        return (index <= validators.size() && index > 0) && (!integerList.contains(index));
    }

    private void executeProblemSimple() {
        HashMap<Integer, Problem> map = LoadGameFromFile.getInstance().getProblemHashMap();
        int choixPb = demanderChoixProbleme(map.size());

        gameFacade.start(choixPb);
    }

    private void executeProblemFromDifficulty() {
        int difficulty = chooseNbDifficulty();
        List<Problem> problemList = LoadGameFromFile.getInstance()
                .getProblemsFromDifficulty(difficulty);

        int pb = 0;
        do {

            for (Problem p : problemList) {
                System.out.println(p.getnProblem());
            }
            System.out.println("Veuillez entre un bon numéro de probleme");
            try {
                Scanner clavier = new Scanner(System.in);
                pb = clavier.nextInt();
            } catch (Exception e) {
                System.out.println("Veuillez entre un numéro etre 1 et " + problemList.size());
            }
        } while (!isContains(problemList, pb));

        gameFacade.start(pb);
    }

    private boolean isContains(List<Problem> problemList, int pb) {
        for (Problem problem : problemList) {
            if (problem.getnProblem() == pb) {
                return true;
            }
        }
        return false;
    }


    private int chooseNbDifficulty() {
        do {
            Scanner clavier = new Scanner(System.in);
            System.out.println("Tapez '1' pour Facile OU tapez '2' pour Difficile ");
            try {
                int nb = clavier.nextInt();
                switch (nb) {
                    case 1:
                        return 1;
                    case 2:
                        return 2;
                    default:
                        throw new InputMismatchException();
                }
            } catch (InputMismatchException e) {
                System.out.println("Votre choix est incorrect !");
            }

        } while (true);

    }

    private void messageStart() {
        System.out.println("Bienvenue dans le jeu Machine de Turing");
    }


    private void displayProblemsWithDifficutltyOrNot(boolean withDifficulty) {
        if (withDifficulty) {
            System.out.println("Voici tous les problemes avec leur difficulté");
        } else {
            System.out.println("Voici tous les problemes");
        }

        HashMap<Integer, Problem> map = LoadGameFromFile.getInstance().getProblemHashMap();

        for (Map.Entry<Integer, Problem> entry : map.entrySet()) {
            int key = entry.getKey();
            if (withDifficulty) {
                Problem pb = entry.getValue();
                System.out.println("Probleme " + key + " , " + pb.getDifficulty());
            } else {
                System.out.println("Probleme : " + key);
            }
        }
    }

    private int demanderChoixProbleme(int tailleListe) {
        Scanner scanner = new Scanner(System.in);


        int problemeChoisi = 0;
        boolean saisieValide = false;

        while (!saisieValide) {
            System.out.println("Veuillez choisir un problème (entre 1 et " + tailleListe + "):");
            String saisieUtilisateur = scanner.nextLine();

            try {
                problemeChoisi = Integer.parseInt(saisieUtilisateur);
                if (problemeChoisi >= 1 && problemeChoisi <= tailleListe) {
                    saisieValide = true;
                } else {
                    System.out.println("Veuillez entrer un numéro valide.");
                }
            } catch (NumberFormatException e) {
                System.out.println("Veuillez entrer un numéro valide.");
            }
        }

        return problemeChoisi;
    }

    @Override
    public void update(Observable observable, int arg) {
        switch (arg) {
            //Apres Start game
            // afficher le score/ validateurs possibles
            // ajouter un code
            case 4:
            case 1: {
                afficherJeuEnCours();
                codeCommande();
                break;
            }
            //Apres entrer un code
            //Afficher la liste des commande
            case 2: {
                displayCommand();
                break;
            }
            //Apres choisir un validateur
            case 3: {
                displayResultValidators();
                displayCommand();
                break;
            }
            case 5: {
                System.out.println("vous avez effectué un Undo");
                displayCommand();
            }
            case 6: {
                System.out.println("vous avez effectué un redo");
                displayCommand();
            }
        }

    }

}
