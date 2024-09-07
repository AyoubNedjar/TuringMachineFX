package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
class GameFacadeTest {

    private GameFacade gameFacade;

    @BeforeEach
    void setUp() {
        gameFacade = new GameFacade();
        gameFacade.start(1);

    }

    @Test
    void startCodeSecret(){

        Code code = new Code(2, 4, 1);
        Code codeResult= gameFacade.getProblem().getSecretCode();
        assertEquals(code, codeResult);
    }
    @Test
    void startProblemesSize(){

        int taille = 16;
        int tailleResult = LoadGameFromFile.getInstance().getProblemHashMap().size();

        assertEquals(taille, tailleResult);
    }
    @Test
    void startOneProblemes(){

       Problem pResult = LoadGameFromFile.getInstance().getProblem(1);
       Problem resProbelem = gameFacade.getProblem();

        assertEquals(pResult, resProbelem);
    }
    @Test
    void startProblemesDifficulty(){

        Problem pResult = LoadGameFromFile.getInstance().getProblemsFromDifficulty(1).get(0);
        Problem resProbelem = gameFacade.getProblem();

        assertEquals(pResult, resProbelem);
    }
    @Test
    void startProblemesDifficulty2(){

        Problem pResult = LoadGameFromFile.getInstance().getProblemsFromDifficulty(1).get(3);
        Problem resProblem = gameFacade.getProblem();

        assertNotEquals(pResult, resProblem);
    }

    @Test
    void enterCode() {
        // Cas de test standard avec des valeurs correctes
        int nb1 = 1;
        int nb2 = 3;
        int nb3 = 2;

        gameFacade.enterCode(nb1, nb2, nb3);

        Code code = new Code(nb1, nb2, nb3);

        Code result = gameFacade.getGame().getPlayerCode();

        assertEquals(code, result);

        // Cas de test avec d'autres valeurs correctes
        int other1 = 4;
        int other2 = 1;
        int other3 = 5;

        gameFacade.enterCode(other1, other2, other3);

        Code otherCode = new Code(other1, other2, other3);

        Code otherResult = gameFacade.getGame().getPlayerCode();

        assertEquals(otherCode, otherResult);
    }

    @Test
    void enterBadCode() {
        // Cas de test avec une valeur en dehors de la plage autorisée
        int nb1 = 1;
        int nb2 = 7;
        int nb3 = 2;

        assertThrows(IllegalArgumentException.class, () -> {
            gameFacade.enterCode(nb1, nb2, nb3);
        });

        // Cas de test avec d'autres valeurs en dehors de la plage autorisée
        int other1 = 0;
        int other2 = 8;
        int other3 = 3;

        assertThrows(IllegalArgumentException.class, () -> {
            gameFacade.enterCode(other1, other2, other3);
        });
    }

    @Test
    void enterBad2Code() {
        // Cas de test avec des valeurs nulles
        int nb1 = 0;
        int nb2 = 0;
        int nb3 = 0;

        assertThrows(IllegalArgumentException.class, () -> {
            gameFacade.enterCode(nb1, nb2, nb3);
        });

        // Cas de test avec d'autres valeurs nulles
        int other1 = 0;
        int other2 = 2;
        int other3 = 0;

        assertThrows(IllegalArgumentException.class, () -> {
            gameFacade.enterCode(other1, other2, other3);
        });
    }
    @Test
    void enterCodeNegativeValues() {
        int nb1 = -1;
        int nb2 = -3;
        int nb3 = -2;

        assertThrows(IllegalArgumentException.class, () -> {
            gameFacade.enterCode(nb1, nb2, nb3);
        });
    }

    @Test
    void enterCodeMaxValues() {
        int max1 = 5;
        int max2 = 5;
        int max3 = 5;

        gameFacade.enterCode(max1, max2, max3);

        Code code = new Code(max1, max2, max3);

        Code result = gameFacade.getGame().getPlayerCode();

        assertEquals(code, result);
    }

    @Test
    void enterCodeMinValues() {
        int min1 = 1;
        int min2 = 1;
        int min3 = 1;

        gameFacade.enterCode(min1, min2, min3);

        Code code = new Code(min1, min2, min3);

        Code result = gameFacade.getGame().getPlayerCode();

        assertEquals(code, result);
    }

    @Test
    void enterCodeDuplicateValues() {
        int dup1 = 3;
        int dup2 = 3;
        int dup3 = 3;

        gameFacade.enterCode(dup1, dup2, dup3);

        Code code = new Code(dup1, dup2, dup3);

        Code result = gameFacade.getGame().getPlayerCode();

        assertEquals(code, result);
    }

    @Test
    void enterCodeMixedValues() {
        int mix1 = 1;
        int mix2 = 5;
        int mix3 = 3;

        gameFacade.enterCode(mix1, mix2, mix3);

        Code code = new Code(mix1, mix2, mix3);

        Code result = gameFacade.getGame().getPlayerCode();

        assertEquals(code, result);
    }

    @Test
    void enterCodeSequentialValues() {
        int seq1 = 1;
        int seq2 = 2;
        int seq3 = 3;

        gameFacade.enterCode(seq1, seq2, seq3);

        Code code = new Code(seq1, seq2, seq3);

        Code result = gameFacade.getGame().getPlayerCode();

        assertEquals(code, result);
    }

    @Test
    void enterCodeLargeValues() {
        int large1 = 1000;
        int large2 = 500;
        int large3 = 10000;

        assertThrows(IllegalArgumentException.class, () -> {
            gameFacade.enterCode(large1, large2, large3);
        });
    }
    @Test
    void chooseValidator() {

        gameFacade.enterCode(1, 3, 4);
        gameFacade.chooseValidator(0);
        gameFacade.chooseValidator(2);
        gameFacade.chooseValidator(1);

        int size = 3;
        int sizeResult  = gameFacade.getGame().getValidatorPlayed().size();

        assertEquals(size, sizeResult);
    }
    @Test
    void chooseBadValidator() {

        gameFacade.enterCode(1, 3, 4);
        gameFacade.chooseValidator(0);

        assertThrows(IndexOutOfBoundsException.class, () -> {
            gameFacade.chooseValidator(6);
        });

    }
    @Test
    void chooseValidatorWithOneChoice() {
        gameFacade.enterCode(1, 3, 4);
        gameFacade.chooseValidator(0);

        int size = 1;
        int sizeResult = gameFacade.getGame().getValidatorPlayed().size();

        assertEquals(size, sizeResult);
    }

    @Test
    void chooseValidatorWithMultipleChoices() {
        gameFacade.enterCode(1, 3, 4);
        gameFacade.chooseValidator(0);
        gameFacade.chooseValidator(2);
        gameFacade.chooseValidator(1);

        int size = 3;
        int sizeResult = gameFacade.getGame().getValidatorPlayed().size();

        assertEquals(size, sizeResult);
    }

    @Test
    void chooseValidatorWithDuplicateChoices() {
        gameFacade.enterCode(1, 3, 4);
        gameFacade.chooseValidator(0);

        // Choix du même validateur devrait lever une IllegalArgumentException
        assertThrows(IllegalArgumentException.class, () -> {
            gameFacade.chooseValidator(0);
        });

        assertThrows(IllegalArgumentException.class, () -> {
            gameFacade.chooseValidator(0);
        });
    }


    @Test
    void chooseValidatorWithDifferentValidators() {
        gameFacade.enterCode(1, 3, 4);
        gameFacade.chooseValidator(0);
        gameFacade.chooseValidator(1);
        gameFacade.chooseValidator(2);

        int size = 3;
        int sizeResult = gameFacade.getGame().getValidatorPlayed().size();

        assertEquals(size, sizeResult);
    }

    @Test
    void chooseValidatorWithMaxChoices() {
        gameFacade.enterCode(1, 3, 4);

        // Choisir les validateurs jusqu'au maximum
        gameFacade.chooseValidator(0);
        gameFacade.chooseValidator(1);
        gameFacade.chooseValidator(2);

        // Choisir à nouveau les mêmes validateurs devrait lever une IllegalArgumentException
        assertThrows(IllegalArgumentException.class, () -> {
            gameFacade.chooseValidator(0);
        });

        assertThrows(IllegalArgumentException.class, () -> {
            gameFacade.chooseValidator(1);
        });

        assertThrows(IllegalArgumentException.class, () -> {
            gameFacade.chooseValidator(2);
        });
    }


    @Test
    void chooseBadValidatorWithValidIndex() {
        gameFacade.enterCode(1, 3, 4);

        assertThrows(IndexOutOfBoundsException.class, () -> {
            gameFacade.chooseValidator(6);
        });
    }

    @Test
    void chooseBadValidatorWithNegativeIndex() {
        gameFacade.enterCode(1, 3, 4);

        assertThrows(IndexOutOfBoundsException.class, () -> {
            gameFacade.chooseValidator(-1);
        });
    }

    @Test
    void chooseBadValidatorWithZeroIndex() {
        gameFacade.enterCode(1, 3, 4);

        // Choisir un validateur avec un index de 0 ne devrait pas lever d'exception
        assertDoesNotThrow(() -> {
            gameFacade.chooseValidator(0);
        });
    }

    @Test
    void chooseBadValidatorWithInvalidIndex() {
        gameFacade.enterCode(1, 3, 4);
        gameFacade.chooseValidator(0);
        gameFacade.chooseValidator(1);
        gameFacade.chooseValidator(2);

        assertThrows(IndexOutOfBoundsException.class, () -> {
            gameFacade.chooseValidator(10);  // Utilisez un index invalide ici
        });
    }


    @Test
    void chooseBadValidatorWithMaxInvalidIndex() {
        gameFacade.enterCode(1, 3, 4);

        assertThrows(IndexOutOfBoundsException.class, () -> {
            gameFacade.chooseValidator(10);
        });
    }

    @Test
    void chooseBadValidatorWithMinInvalidIndex() {
        gameFacade.enterCode(1, 3, 4);

        assertThrows(IndexOutOfBoundsException.class, () -> {
            gameFacade.chooseValidator(-5);
        });
    }

    @Test
    void chooseBadValidatorWithLargeIndex() {
        gameFacade.enterCode(1, 3, 4);

        assertThrows(IndexOutOfBoundsException.class, () -> {
            gameFacade.chooseValidator(100);
        });
    }

    @Test
    void chooseBadValidatorWithSequentialIndexes() {
        gameFacade.enterCode(1, 3, 4);
        gameFacade.chooseValidator(0);
        gameFacade.chooseValidator(1);
        gameFacade.chooseValidator(2);

        // Choisir à nouveau un validateur déjà choisi devrait lever une IllegalArgumentException
        assertThrows(IllegalArgumentException.class, () -> {
            gameFacade.chooseValidator(0);
        });

        assertThrows(IllegalArgumentException.class, () -> {
            gameFacade.chooseValidator(1);
        });

        assertThrows(IllegalArgumentException.class, () -> {
            gameFacade.chooseValidator(2);
        });
    }

    @Test
    void chooseFulldValidator() {

        gameFacade.enterCode(1, 3, 4);
        gameFacade.chooseValidator(0);
        gameFacade.chooseValidator(1);


        assertThrows(IllegalArgumentException.class, () -> {
            gameFacade.chooseValidator(0);
        });

    }

    @Test
    void chooseFull2dValidator() {

        gameFacade.enterCode(1, 3, 4);
        gameFacade.chooseValidator(0);
        gameFacade.chooseValidator(1);
        gameFacade.chooseValidator(2);

        assertThrows(IllegalArgumentException.class, () -> {
            gameFacade.chooseValidator(0);
        });

    }

    @Test
    void chooseBadValidatorWithRandomIndexes() {
        gameFacade.enterCode(1, 3, 4);
        gameFacade.chooseValidator(0);
        gameFacade.chooseValidator(2);
        gameFacade.chooseValidator(1);

        assertThrows(IndexOutOfBoundsException.class, () -> {
            gameFacade.chooseValidator(5);
        });
    }


    @Test
    void nextRoundList() {
        gameFacade.enterCode(1, 3, 4);
        gameFacade.chooseValidator(0);
        gameFacade.chooseValidator(2);

        gameFacade.nextRound();
        int result = gameFacade.getGame().getValidatorPlayed().size();
        int size = 0;

        assertEquals(size, result);

    }

    @Test
    void nextRoundCpt() {
        gameFacade.enterCode(1, 3, 4);
        gameFacade.chooseValidator(0);
        gameFacade.chooseValidator(2);

        gameFacade.nextRound();
        int result = gameFacade.getGame().getRound();
        int size = 1;

        assertEquals(size, result);
    }
    @Test
    void nextRoundMap() {
        gameFacade.enterCode(1, 3, 4);
        gameFacade.chooseValidator(0);
        gameFacade.chooseValidator(2);

        gameFacade.nextRound();
        int result = gameFacade.getGame().getValidatorResult().size();
        int size = 0;

        assertEquals(size, result);
    }
    @Test
    void nextRoundListWithDifferentChoices() {
        gameFacade.enterCode(1, 3, 4);
        gameFacade.chooseValidator(0);
        gameFacade.chooseValidator(1);

        gameFacade.nextRound();
        int result = gameFacade.getGame().getValidatorPlayed().size();
        int size = 0;

        assertEquals(size, result);
    }

    @Test
    void nextRoundCptWithMultipleRounds() {
        gameFacade.enterCode(1, 3, 4);
        gameFacade.chooseValidator(0);
        gameFacade.chooseValidator(2);

        gameFacade.nextRound();
        int result = gameFacade.getGame().getRound();
        int size = 1;

        assertEquals(size, result);

        // Simuler plusieurs rounds supplémentaires
        gameFacade.nextRound();
        gameFacade.nextRound();
        result = gameFacade.getGame().getRound();
        size = 3;

        assertEquals(size, result);
    }

    @Test
    void nextRoundMapWithResults() {
        gameFacade.enterCode(1, 3, 4);
        gameFacade.chooseValidator(0);
        gameFacade.chooseValidator(2);

        gameFacade.nextRound();
        int result = gameFacade.getGame().getValidatorResult().size();
        int size = 0;

        assertEquals(size, result);

    }
    @Test
    void nextRoundListWithNoChoices() {
        gameFacade.enterCode(1, 3, 4);

        gameFacade.nextRound();
        int result = gameFacade.getGame().getValidatorPlayed().size();
        int size = 0;

        assertEquals(size, result);
    }

    @Test
    void nextRoundCptWithNoRounds() {
        gameFacade.enterCode(1, 3, 4);

        int result = gameFacade.getGame().getRound();
        int size = 0;

        assertEquals(size, result);
    }

    @Test
    void nextRoundMapWithNoResults() {
        gameFacade.enterCode(1, 3, 4);

        gameFacade.nextRound();
        int result = gameFacade.getGame().getValidatorResult().size();
        int size = 0;

        assertEquals(size, result);
    }

    @Test
    void nextRoundListWithSingleChoice() {
        gameFacade.enterCode(1, 3, 4);
        gameFacade.chooseValidator(0);

        gameFacade.nextRound();
        int result = gameFacade.getGame().getValidatorPlayed().size();
        int size = 0;

        assertEquals(size, result);
    }

    @Test
    void nextRoundMapWithSingleResult() {
        gameFacade.enterCode(1, 3, 4);
        gameFacade.chooseValidator(0);

        gameFacade.nextRound();
        int result = gameFacade.getGame().getValidatorResult().size();
        int size = 0;

        assertEquals(size, result);
    }
    @Test
    void nextRoundMapWithMultipleResults() {
        gameFacade.enterCode(1, 3, 4);
        gameFacade.chooseValidator(0);
        gameFacade.chooseValidator(1);
        gameFacade.chooseValidator(2);

        gameFacade.nextRound();

        // Ajouter plusieurs résultats simulés pour le round suivant
        gameFacade.getGame().addResult(0, true);
        gameFacade.getGame().addResult(1, false);
        gameFacade.getGame().addResult(2, true);

        int result = gameFacade.getGame().getValidatorResult().size();
        int size = 3;

        assertEquals(size, result);
    }


    @Test
    void undo() {
        System.out.println("manche 1");
        gameFacade.enterCode(3, 3, 2);  //test code
        System.out.println("votre code : 332");

        gameFacade.chooseValidator(1);
        gameFacade.chooseValidator(2);
        gameFacade.chooseValidator(3);

        System.out.println("manche 2");
        gameFacade.nextRound();
        gameFacade.enterCode(5,3,4); //testcode
        gameFacade.chooseValidator(3);
        gameFacade.chooseValidator(2);

        int sizeBeforeUndo = gameFacade.getGame().getValidatorPlayed().size();
        int size1 = 2;
        assertEquals(size1, sizeBeforeUndo);

        gameFacade.undo();
        int sizeListPlayed = gameFacade.getGame().getValidatorPlayed().size();
        int size2 = 1;

        assertEquals(size2, sizeListPlayed);
    }

    @Test
    void redo() {
        System.out.println("manche 1");
        gameFacade.enterCode(3, 3, 2);  //test code
        System.out.println("votre code : 332");

        gameFacade.chooseValidator(1);
        gameFacade.chooseValidator(2);
        gameFacade.chooseValidator(3);

        System.out.println("manche 2");
        gameFacade.nextRound();
        gameFacade.enterCode(5,3,4); //testcode
        gameFacade.chooseValidator(3);
        gameFacade.chooseValidator(2);

        int sizeBeforeUndo = gameFacade.getGame().getValidatorPlayed().size();
        int size1 = 2;
        assertEquals(size1, sizeBeforeUndo);

        gameFacade.undo();
        gameFacade.redo();
        int sizeListPlayed = gameFacade.getGame().getValidatorPlayed().size();
        int size2 = 2;

        assertEquals(size2, sizeListPlayed);
    }
    @Test
    void undoWithDifferentCodes() {
        System.out.println("Manche 1");
        gameFacade.enterCode(1, 2, 3);  // Test code
        System.out.println("Votre code : 123");

        gameFacade.chooseValidator(0);
        gameFacade.chooseValidator(1);
        gameFacade.chooseValidator(2);

        System.out.println("Manche 2");
        gameFacade.nextRound();
        gameFacade.enterCode(4, 5, 1);  // Test code
        gameFacade.chooseValidator(2);
        gameFacade.chooseValidator(1);

        int sizeBeforeUndo = gameFacade.getGame().getValidatorPlayed().size();
        int size1 = 2;
        assertEquals(size1, sizeBeforeUndo);

        gameFacade.undo();
        int sizeListPlayed = gameFacade.getGame().getValidatorPlayed().size();
        int size2 = 1;

        assertEquals(size2, sizeListPlayed);
    }

    @Test
    void redoWithDifferentCodes() {
        System.out.println("Manche 1");
        gameFacade.enterCode(5, 4, 3);  // Test code
        System.out.println("Votre code : 543");

        gameFacade.chooseValidator(2);
        gameFacade.chooseValidator(1);
        gameFacade.chooseValidator(0);

        System.out.println("Manche 2");
        gameFacade.nextRound();
        gameFacade.enterCode(2, 1, 5);  // Test code
        gameFacade.chooseValidator(1);
        gameFacade.chooseValidator(0);

        int sizeBeforeUndo = gameFacade.getGame().getValidatorPlayed().size();
        int size1 = 2;
        assertEquals(size1, sizeBeforeUndo);

        gameFacade.undo();
        gameFacade.redo();
        int sizeListPlayed = gameFacade.getGame().getValidatorPlayed().size();
        int size2 = 2;

        assertEquals(size2, sizeListPlayed);
    }
    @Test
    void undoRedoScenario1() {
        System.out.println("Manche 1");
        gameFacade.enterCode(2, 3, 1);
        System.out.println("Votre code : 231");

        gameFacade.chooseValidator(1);
        gameFacade.chooseValidator(0);
        gameFacade.chooseValidator(2);

        System.out.println("Manche 2");
        gameFacade.nextRound();
        gameFacade.enterCode(5, 4, 2);
        gameFacade.chooseValidator(2);
        gameFacade.chooseValidator(1);

        int sizeBeforeUndo = gameFacade.getGame().getValidatorPlayed().size();
        assertEquals(2, sizeBeforeUndo);

        gameFacade.undo();
        int sizeAfterUndo = gameFacade.getGame().getValidatorPlayed().size();
        assertEquals(1, sizeAfterUndo);

        gameFacade.redo();
        int sizeAfterRedo = gameFacade.getGame().getValidatorPlayed().size();
        assertEquals(2, sizeAfterRedo);
    }

    @Test
    void undoRedoScenario2() {
        System.out.println("Manche 1");
        gameFacade.enterCode(4, 1, 3);
        System.out.println("Votre code : 413");

        gameFacade.chooseValidator(0);
        gameFacade.chooseValidator(2);
        gameFacade.chooseValidator(1);

        System.out.println("Manche 2");
        gameFacade.nextRound();
        gameFacade.enterCode(2, 5, 4);
        gameFacade.chooseValidator(1);
        gameFacade.chooseValidator(0);

        int sizeBeforeUndo = gameFacade.getGame().getValidatorPlayed().size();
        assertEquals(2, sizeBeforeUndo);

        gameFacade.undo();
        int sizeAfterUndo = gameFacade.getGame().getValidatorPlayed().size();
        assertEquals(1, sizeAfterUndo);

        gameFacade.redo();
        int sizeAfterRedo = gameFacade.getGame().getValidatorPlayed().size();
        assertEquals(2, sizeAfterRedo);
    }

    @Test
    void undoRedoScenario3() {
        System.out.println("Manche 1");
        gameFacade.enterCode(1, 3, 5);
        System.out.println("Votre code : 135");

        gameFacade.chooseValidator(2);
        gameFacade.chooseValidator(1);
        gameFacade.chooseValidator(0);

        System.out.println("Manche 2");
        gameFacade.nextRound();
        gameFacade.enterCode(2, 4, 5);
        gameFacade.chooseValidator(0);
        gameFacade.chooseValidator(2);

        int sizeBeforeUndo = gameFacade.getGame().getValidatorPlayed().size();
        assertEquals(2, sizeBeforeUndo);

        gameFacade.undo();
        int sizeAfterUndo = gameFacade.getGame().getValidatorPlayed().size();
        assertEquals(1, sizeAfterUndo);

        gameFacade.redo();
        int sizeAfterRedo = gameFacade.getGame().getValidatorPlayed().size();
        assertEquals(2, sizeAfterRedo);
    }

    @Test
    void undoRedoScenario4() {
        System.out.println("Manche 1");
        gameFacade.enterCode(3, 2, 1);
        System.out.println("Votre code : 321");

        gameFacade.chooseValidator(0);
        gameFacade.chooseValidator(1);
        gameFacade.chooseValidator(2);

        System.out.println("Manche 2");
        gameFacade.nextRound();
        gameFacade.enterCode(5, 4, 3);
        gameFacade.chooseValidator(2);
        gameFacade.chooseValidator(1);

        int sizeBeforeUndo = gameFacade.getGame().getValidatorPlayed().size();
        assertEquals(2, sizeBeforeUndo);

        gameFacade.undo();
        int sizeAfterUndo = gameFacade.getGame().getValidatorPlayed().size();
        assertEquals(1, sizeAfterUndo);

        gameFacade.redo();
        int sizeAfterRedo = gameFacade.getGame().getValidatorPlayed().size();
        assertEquals(2, sizeAfterRedo);
    }

    @Test
    void undoRedoScenario5() {
        System.out.println("Manche 1");
        gameFacade.enterCode(5, 3, 4);
        System.out.println("Votre code : 534");

        gameFacade.chooseValidator(1);
        gameFacade.chooseValidator(2);
        gameFacade.chooseValidator(0);

        System.out.println("Manche 2");
        gameFacade.nextRound();
        gameFacade.enterCode(2, 1, 3);
        gameFacade.chooseValidator(0);
        gameFacade.chooseValidator(1);

        int sizeBeforeUndo = gameFacade.getGame().getValidatorPlayed().size();
        assertEquals(2, sizeBeforeUndo);

        gameFacade.undo();
        int sizeAfterUndo = gameFacade.getGame().getValidatorPlayed().size();
        assertEquals(1, sizeAfterUndo);

        gameFacade.redo();
        int sizeAfterRedo = gameFacade.getGame().getValidatorPlayed().size();
        assertEquals(2, sizeAfterRedo);
    }
    @Test
    void invalidValidatorIndex() {
        System.out.println("Manche 1");
        gameFacade.enterCode(2, 3, 1);
        System.out.println("Votre code : 231");

        assertThrows(IndexOutOfBoundsException.class, () -> gameFacade.chooseValidator(5));
    }

    @Test
    void duplicateValidatorSelection() {
        System.out.println("Manche 1");
        gameFacade.enterCode(4, 1, 3);
        System.out.println("Votre code : 413");

        gameFacade.chooseValidator(0);

        assertThrows(IllegalArgumentException.class, () -> {
            gameFacade.chooseValidator(0);
        });
    }


    @Test
    void invalidCodeValue() {
        System.out.println("Manche 1");
        assertThrows(IllegalArgumentException.class, () -> {
            gameFacade.enterCode(6, 3, 2);
        });
    }

    @Test
    void invalidCodeStructure() {
        System.out.println("Manche 1");
        assertThrows(IllegalArgumentException.class, () -> {
            gameFacade.enterCode(1, 2, 6);
        });
    }

    @Test
    void invalidPlayerCodeValue() {
        System.out.println("Manche 1");
        gameFacade.enterCode(1, 2, 3);
        gameFacade.chooseValidator(1);
        gameFacade.chooseValidator(0);
        gameFacade.chooseValidator(2);

        System.out.println("Manche 2");
        gameFacade.nextRound();

        assertThrows(IllegalArgumentException.class, () -> {
            gameFacade.enterCode(4, 5, 6);
        });
    }

    @Test
    void invalidPlayerCodeStructure() {
        System.out.println("Manche 1");
        gameFacade.enterCode(1, 2, 3);
        gameFacade.chooseValidator(1);
        gameFacade.chooseValidator(0);
        gameFacade.chooseValidator(2);

        System.out.println("Manche 2");
        gameFacade.nextRound();

        assertThrows(IllegalArgumentException.class, () -> {
            gameFacade.enterCode(4, 5, 6);
        });
    }


    @Test
    void multipleUndoRedo() {
        System.out.println("Manche 1");
        gameFacade.enterCode(2, 3, 1);
        System.out.println("Votre code : 231");

        gameFacade.chooseValidator(1);
        gameFacade.chooseValidator(0);
        gameFacade.chooseValidator(2);

        System.out.println("Manche 2");
        gameFacade.nextRound();
        gameFacade.enterCode(5, 4, 2);
        gameFacade.chooseValidator(2);
        gameFacade.chooseValidator(1);

        int sizeBeforeUndo = gameFacade.getGame().getValidatorPlayed().size();
        assertEquals(2, sizeBeforeUndo);

        gameFacade.undo();
        int sizeAfterFirstUndo = gameFacade.getGame().getValidatorPlayed().size();
        assertEquals(1, sizeAfterFirstUndo);

        gameFacade.redo();
        int sizeAfterRedo = gameFacade.getGame().getValidatorPlayed().size();
        assertEquals(2, sizeAfterRedo);

        gameFacade.undo();
        int sizeAfterSecondUndo = gameFacade.getGame().getValidatorPlayed().size();
        assertEquals(1, sizeAfterSecondUndo);

        gameFacade.redo();
        int sizeAfterSecondRedo = gameFacade.getGame().getValidatorPlayed().size();
        assertEquals(2, sizeAfterSecondRedo);
    }

}