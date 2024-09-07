package model.Validator;

import model.Code;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ValidatorTest {

    @Test
    public void valid1 (){
        Code playerCode = new Code(2, 3, 3);
        Code secret = new Code(3, 3, 3);
        Validator v  = new CompareValue(secret, 0, 3);
        boolean ok = v.check(playerCode);
        boolean ok2 = false;
        assertEquals(ok, ok2);

    }
    @Test
    public void testCompareValueValidator() {
        Code playerCode = new Code(1, 2, 3);
        Code secret = new Code(4, 5, 6);
        Validator validator = new CompareValue(secret, 0, 3);
        boolean isValid = validator.check(playerCode);
        assertFalse(isValid);
    }

    @Test
    public void testPariteValidator() {
        Code playerCode = new Code(2, 4, 6);
        Code secret = new Code(3, 5, 7);
        Validator validator = new Parite(secret, 1);
        boolean isValid = validator.check(playerCode);
        assertFalse(isValid);
    }
    @Test
    public void testPariteValidator2() {
        Code playerCode = new Code(2, 4, 6);
        Code secret = new Code(3, 2, 7);
        Validator validator = new Parite(secret, 1);
        boolean isValid = validator.check(playerCode);
        assertTrue(isValid); // Changez cette assertion selon votre validation attendue
    }
    @Test
    public void testCompareValueValidator4() {
        Code playerCode = new Code(1, 2, 3);
        Code secret = new Code(4, 5, 6);
        Validator validator = new CompareValue(secret, 0, 3);
        boolean isValid = validator.check(playerCode);
        assertFalse(isValid);
    }

    @Test
    public void testPariteValidator22() {
        Code playerCode = new Code(2, 4, 6);
        Code secret = new Code(3, 5, 7);
        Validator validator = new SumParite(secret);
        boolean isValid = validator.check(playerCode);
        assertFalse(isValid);
    }

    @Test
    public void testPariteValidator32() {
        Code playerCode = new Code(2, 4, 6);
        Code secret = new Code(3, 2, 7);
        Validator validator = new SumParite(secret);
        boolean isValid = validator.check(playerCode);
        assertTrue(isValid);
    }
    @Test
    public void testSumPariteIsPairSum() {
        Code code = new Code(1, 2, 3);
        SumParite sumParite = new SumParite(code);

        // La somme de 1 + 2 + 3 est 6, qui est pair
        assertTrue(sumParite.isPairSum(code));

        // Modifiez le code pour avoir une somme impair
        code = new Code(1, 2, 4);
        assertFalse(sumParite.isPairSum(code));
    }
    @Test
    public void testParityMajorityChecker() {
        // Test avec un secret contenant une majorité de chiffres pairs
        Code secretWithMajorityEven = new Code(2, 4, 5);
        ParityMajorityChecker checkerEven = new ParityMajorityChecker(secretWithMajorityEven);
        Code playerCodeEven = new Code(2, 4, 5);
        assertTrue(checkerEven.check(playerCodeEven));

        // Le joueur a une majorité de chiffres impairs
        Code playerCodeOdd = new Code(1, 3, 5);
        assertFalse(checkerEven.check(playerCodeOdd));

        // Test avec un secret contenant une majorité de chiffres impairs
        Code secretWithMajorityOdd = new Code(1, 3, 5);
        ParityMajorityChecker checkerOdd = new ParityMajorityChecker(secretWithMajorityOdd);

        // Le joueur a également une majorité de chiffres impairs
        assertTrue(checkerOdd.check(playerCodeOdd));

        // Le joueur a une majorité de chiffres pairs
        assertFalse(checkerOdd.check(playerCodeEven));

        // Test avec un secret contenant une égalité de chiffres pairs et impairs
        Code secretWithEqualEvenOdd = new Code(2, 4, 1);
        ParityMajorityChecker checkerEqual = new ParityMajorityChecker(secretWithEqualEvenOdd);

        // Le joueur a une majorité de chiffres pairs
        assertTrue(checkerEqual.check(playerCodeEven));

        // Le joueur a une majorité de chiffres impairs
        assertFalse(checkerEqual.check(playerCodeOdd));

        // Test avec un secret vide
        Code emptySecret = new Code(playerCodeEven);
        ParityMajorityChecker checkerEmpty = new ParityMajorityChecker(emptySecret);

        // Le joueur a également une majorité de chiffres pairs (car il n'y a pas de chiffres)
        assertTrue(checkerEmpty.check(new Code(playerCodeEven)));

        // Test avec un secret et un joueur vides
        assertTrue(checkerEmpty.check(emptySecret));
    }
    @Test
    public void testNumberTwins() {
        // Test avec un code joueur qui a exactement deux chiffres identiques
        Code playerCodeTwins = new Code(2, 4, 2);
        NumberTwins numberTwins = new NumberTwins(playerCodeTwins);

        // Le code secret a également exactement deux chiffres identiques
        assertTrue(numberTwins.check(playerCodeTwins));

        // Test avec un code joueur qui a trois chiffres identiques
        Code playerCodeTriplets = new Code(1, 1, 1);
        assertFalse(numberTwins.check(playerCodeTriplets));

        // Test avec un code joueur qui a zéro chiffre identique
        Code playerCodeNoTwins = new Code(1, 2, 3);
        assertFalse(numberTwins.check(playerCodeNoTwins));

        // Test avec un code joueur vide
        Code emptyPlayerCode = new Code(playerCodeNoTwins);
        assertFalse(numberTwins.check(emptyPlayerCode));

        // Test avec un secret vide
        Code emptySecret = new Code(playerCodeNoTwins);
        NumberTwins numberTwinsEmpty = new NumberTwins(emptySecret);

        // Le joueur a également zéro chiffre identique (car il n'y a pas de chiffres)
        assertTrue(numberTwinsEmpty.check(new Code(playerCodeNoTwins)));

        // Test avec un secret et un joueur vides
        assertTrue(numberTwinsEmpty.check(emptySecret));
    }

    @Test
    public void testExtremum() {
        // Test avec un secret où le premier chiffre est le maximum
        Code secretFirstMax = new Code(5, 3, 2);
        Extremum extremumFirstMax = new Extremum(secretFirstMax, 0);

        // Le joueur a également le premier chiffre comme maximum
        Code playerCodeFirstMax = new Code(5, 2, 1);
        assertTrue(extremumFirstMax.check(playerCodeFirstMax));

        // Le joueur a un maximum dans les deuxième ou troisième chiffres
        Code playerCodeNotFirstMax = new Code(4, 5, 6);
        assertFalse(extremumFirstMax.check(playerCodeNotFirstMax));

        // Test avec un secret où deux chiffres sont les maximums
        Code secretTwoMax = new Code(3, 5, 3);
        Extremum extremumTwoMax = new Extremum(secretTwoMax, 1);

        // Le joueur a également deux chiffres comme maximums
        Code playerCodeTwoMax = new Code(1, 5, 5);
        assertFalse(extremumTwoMax.check(playerCodeTwoMax));

        // Le joueur a un maximum dans le premier ou troisième chiffre
        Code playerCodeNotTwoMax = new Code(6, 3, 2);
        assertFalse(extremumTwoMax.check(playerCodeNotTwoMax));

        // Test avec un secret où tous les chiffres sont les maximums
        Code secretAllMax = new Code(4, 4, 4);
        Extremum extremumAllMax = new Extremum(secretAllMax, 1);

        // Le joueur a également tous les chiffres comme maximums
        Code playerCodeAllMax = new Code(4, 4, 4);
        assertTrue(extremumAllMax.check(playerCodeAllMax));

        // Le joueur a un maximum dans un seul chiffre
        Code playerCodeNotAllMax = new Code(4, 3, 4);
        assertTrue(extremumAllMax.check(playerCodeNotAllMax));

        // Test avec un secret où tous les chiffres sont égaux
        Code secretNoMax = new Code(2, 2, 2);
        Extremum extremumNoMax = new Extremum(secretNoMax, 0);

        // Le joueur a également tous les chiffres égaux
        assertTrue(extremumNoMax.check(secretNoMax));

        // Le joueur a un maximum dans le deuxième ou troisième chiffre
        Code playerCodeNotNoMax = new Code(1, 3, 2);
        assertFalse(extremumNoMax.check(playerCodeNotNoMax));
    }
    @Test
    public void testDoublon() {
        // Test avec un code joueur sans doublon
        Code playerCodeNoDoublon = new Code(1, 2, 3);
        Doublon doublonNoDoublon = new Doublon(playerCodeNoDoublon);

        // Le code secret n'a également aucun doublon
        assertTrue(doublonNoDoublon.check(playerCodeNoDoublon));

        // Test avec un code joueur avec un doublon
        Code playerCodeOneDoublon = new Code(1, 2, 2);
        Doublon doublonOneDoublon = new Doublon(playerCodeOneDoublon);

        // Le code secret a également un doublon
        assertTrue(doublonOneDoublon.check(playerCodeOneDoublon));

        // Test avec un code joueur avec un doublon répété deux fois
        Code playerCodeTwoDoublon = new Code(1, 2, 1);
        Doublon doublonTwoDoublon = new Doublon(playerCodeTwoDoublon);

        // Le code secret a également un doublon répété deux fois
        assertTrue(doublonTwoDoublon.check(playerCodeTwoDoublon));

        // Test avec un code joueur avec un chiffre répété trois fois
        Code playerCodeTriple = new Code(1, 1, 1);
        Doublon doublonTriple = new Doublon(playerCodeTriple);

        // Le code secret a également un chiffre répété trois fois
        assertTrue(doublonTriple.check(playerCodeTriple));

        // Test avec un code joueur vide
        Code emptyPlayerCode = new Code(playerCodeNoDoublon);
        Doublon doublonEmpty = new Doublon(emptyPlayerCode);

        // Le code secret est également vide, donc il n'y a pas de doublon
        assertTrue(doublonEmpty.check(emptyPlayerCode));

        // Test avec un secret vide et un joueur avec un chiffre répété trois fois
        Code emptySecret = new Code(playerCodeNoDoublon);
        Code playerCodeTripleInEmpty = new Code(1, 1, 1);
        Doublon doublonTripleInEmpty = new Doublon(emptySecret);

        // Le joueur a un chiffre répété trois fois, mais le secret est vide
        assertFalse(doublonTripleInEmpty.check(playerCodeTripleInEmpty));
    }
    @Test
    public void testCroissant() {
        // Test avec un code joueur en ordre croissant
        Code playerCodeCroissant = new Code(1, 2, 3);
        Croissant croissant = new Croissant(playerCodeCroissant);

        // Le code secret a également un ordre croissant
        assertTrue(croissant.check(playerCodeCroissant));

        // Test avec un code joueur en ordre décroissant
        Code playerCodeDecroissant = new Code(3, 2, 1);
        Croissant decroissant = new Croissant(playerCodeDecroissant);

        // Le code secret a également un ordre décroissant
        assertTrue(decroissant.check(playerCodeDecroissant));

        // Test avec un code joueur en ordre quelconque
        Code playerCodeRandom = new Code(2, 1, 3);
        Croissant random = new Croissant(playerCodeRandom);

        // Le code secret n'a pas d'ordre particulier
        assertTrue(random.check(playerCodeRandom));

        // Test avec un code joueur vide
        Code emptyPlayerCode = new Code(playerCodeCroissant);
        Croissant croissantEmpty = new Croissant(emptyPlayerCode);

        // Le code secret est également vide, donc il n'y a pas d'ordre particulier
        assertTrue(croissantEmpty.check(emptyPlayerCode));

        // Test avec un secret vide et un joueur en ordre décroissant
        Code emptySecret = new Code(playerCodeDecroissant);
        Code playerCodeDecroissantInEmpty = new Code(3, 2, 1);
        Croissant decroissantInEmpty = new Croissant(emptySecret);

        // Le joueur a un ordre décroissant, mais le secret est vide
        assertTrue(decroissantInEmpty.check(playerCodeDecroissantInEmpty));
    }
    @Test
    public void testCountPaire() {
        // Test avec un code joueur sans chiffre pair
        Code playerCodeNoPair = new Code(1, 3, 5);
        ComptPair countPaireNoPair = new ComptPair(playerCodeNoPair);

        // Le code secret n'a également aucun chiffre pair
        assertTrue(countPaireNoPair.check(playerCodeNoPair));

        // Test avec un code joueur avec un chiffre pair
        Code playerCodeOnePair = new Code(1, 2, 5);
        ComptPair countPaireOnePair = new ComptPair(playerCodeOnePair);

        // Le code secret a également un chiffre pair
        assertTrue(countPaireOnePair.check(playerCodeOnePair));

        // Test avec un code joueur avec deux chiffres pairs
        Code playerCodeTwoPairs = new Code(2, 4, 1);
        ComptPair countPaireTwoPairs = new ComptPair(playerCodeTwoPairs);

        // Le code secret a également deux chiffres pairs
        assertTrue(countPaireTwoPairs.check(playerCodeTwoPairs));

        // Test avec un code joueur avec trois chiffres pairs
        Code playerCodeThreePairs = new Code(2, 4, 6);
        ComptPair countPaireThreePairs = new ComptPair(playerCodeThreePairs);

        // Le code secret a également trois chiffres pairs
        assertTrue(countPaireThreePairs.check(playerCodeThreePairs));

        // Test avec un code joueur vide
        Code emptyPlayerCode = new Code(playerCodeNoPair);
        ComptPair countPaireEmpty = new ComptPair(emptyPlayerCode);

        // Le code secret est également vide, donc il n'y a pas de chiffre pair
        assertTrue(countPaireEmpty.check(emptyPlayerCode));

        // Test avec un secret vide et un joueur avec trois chiffres pairs
        Code emptySecret = new Code(emptyPlayerCode);
        Code playerCodeThreePairsInEmpty = new Code(2, 4, 6);
        ComptPair countPaireThreePairsInEmpty = new ComptPair(emptySecret);

        // Le joueur a trois chiffres pairs, mais le secret est vide
        assertFalse(countPaireThreePairsInEmpty.check(playerCodeThreePairsInEmpty));
    }

    @Test
    public void testComptValue() {
        // Test avec un code joueur sans occurrence de la valeur 3
        Code playerCodeNoThree = new Code(1, 2, 4);
        ComptValue comptValueNoThree = new ComptValue(playerCodeNoThree, 3);

        // Le code secret n'a également aucune occurrence de la valeur 3
        assertTrue(comptValueNoThree.check(playerCodeNoThree));

        // Test avec un code joueur avec une occurrence de la valeur 3
        Code playerCodeOneThree = new Code(1, 2, 3);
        ComptValue comptValueOneThree = new ComptValue(playerCodeOneThree, 3);

        // Le code secret a également une occurrence de la valeur 3
        assertTrue(comptValueOneThree.check(playerCodeOneThree));

        // Test avec un code joueur avec deux occurrences de la valeur 3
        Code playerCodeTwoThrees = new Code(3, 2, 3);
        ComptValue comptValueTwoThrees = new ComptValue(playerCodeTwoThrees, 3);

        // Le code secret a également deux occurrences de la valeur 3
        assertTrue(comptValueTwoThrees.check(playerCodeTwoThrees));

        // Test avec un code joueur avec trois occurrences de la valeur 3
        Code playerCodeThreeThrees = new Code(3, 3, 3);
        ComptValue comptValueThreeThrees = new ComptValue(playerCodeThreeThrees, 3);

        // Le code secret a également trois occurrences de la valeur 3
        assertTrue(comptValueThreeThrees.check(playerCodeThreeThrees));

        // Test avec un code joueur vide
        Code emptyPlayerCode = new Code(playerCodeNoThree);
        ComptValue comptValueEmpty = new ComptValue(emptyPlayerCode, 3);

        // Le code secret est également vide, donc il n'y a aucune occurrence de la valeur 3
        assertTrue(comptValueEmpty.check(emptyPlayerCode));

        // Test avec un secret vide et un joueur avec trois occurrences de la valeur 3
        Code emptySecret = new Code(emptyPlayerCode);
        Code playerCodeThreeThreesInEmpty = new Code(3, 3, 3);
        ComptValue comptValueThreeThreesInEmpty = new ComptValue(emptySecret, 3);

        // Le joueur a trois occurrences de la valeur 3, mais le secret est vide
        assertFalse(comptValueThreeThreesInEmpty.check(playerCodeThreeThreesInEmpty));
    }

    @Test
    public void testCompareValue() {
        // Test avec un code joueur où le deuxième chiffre est plus petit que 4
        Code playerCodeSmallerThan4 = new Code(1, 3, 5);
        CompareValue compareValueSmallerThan4 = new CompareValue(playerCodeSmallerThan4, 1, 4);

        // Le code secret a également le deuxième chiffre plus petit que 4
        assertTrue(compareValueSmallerThan4.check(playerCodeSmallerThan4));

        // Test avec un code joueur où le deuxième chiffre est égal à 4
        Code playerCodeEquals4 = new Code(1, 4, 5);
        CompareValue compareValueEquals4 = new CompareValue(playerCodeEquals4, 1, 4);

        // Le code secret a également le deuxième chiffre égal à 4
        assertTrue(compareValueEquals4.check(playerCodeEquals4));

        // Test avec un code joueur où le deuxième chiffre est plus grand que 4
        Code playerCodeGreaterThan4 = new Code(1, 5, 6);
        CompareValue compareValueGreaterThan4 = new CompareValue(playerCodeGreaterThan4, 1, 4);

        // Le code secret a également le deuxième chiffre plus grand que 4
        assertTrue(compareValueGreaterThan4.check(playerCodeGreaterThan4));

        // Test avec un code joueur vide
        Code emptyPlayerCode = new Code(playerCodeEquals4);
        CompareValue compareValueEmpty = new CompareValue(emptyPlayerCode, 1, 4);

        // Le code secret est également vide, donc la comparaison est indéterminée
        assertTrue(compareValueEmpty.check(emptyPlayerCode));

        // Test avec un secret vide et un joueur où le deuxième chiffre est plus grand que 4
        Code emptySecret = new Code(playerCodeEquals4);
        Code playerCodeGreaterThan4InEmpty = new Code(1, 5, 6);
        CompareValue compareValueGreaterThan4InEmpty = new CompareValue(emptySecret, 1, 4);

        // Le joueur a le deuxième chiffre plus grand que 4, mais le secret est vide
        assertFalse(compareValueGreaterThan4InEmpty.check(playerCodeGreaterThan4InEmpty));
    }
    @Test
    public void testCompareTwoNumbersValidator() {
        // Test avec un code joueur où le premier chiffre est plus petit que le deuxième
        Code playerCodeSmallerFirst = new Code(1, 3, 5);
        CompareTwoNumbersValidator compareTwoNumbersSmallerFirst = new CompareTwoNumbersValidator(playerCodeSmallerFirst, 0, 1);

        // Le code secret a également le premier chiffre plus petit que le deuxième
        assertTrue(compareTwoNumbersSmallerFirst.check(playerCodeSmallerFirst));

        // Test avec un code joueur où le premier chiffre est égal au deuxième
        Code playerCodeEqualsFirst = new Code(1, 1, 5);
        CompareTwoNumbersValidator compareTwoNumbersEqualsFirst = new CompareTwoNumbersValidator(playerCodeEqualsFirst, 0, 1);

        // Le code secret a également le premier chiffre égal au deuxième
        assertTrue(compareTwoNumbersEqualsFirst.check(playerCodeEqualsFirst));

        // Test avec un code joueur où le premier chiffre est plus grand que le deuxième
        Code playerCodeGreaterThanFirst = new Code(3, 1, 5);
        CompareTwoNumbersValidator compareTwoNumbersGreaterThanFirst = new CompareTwoNumbersValidator(playerCodeGreaterThanFirst, 0, 1);

        // Le code secret a également le premier chiffre plus grand que le deuxième
        assertTrue(compareTwoNumbersGreaterThanFirst.check(playerCodeGreaterThanFirst));

        // Test avec un code joueur vide
        Code emptyPlayerCode = new Code(playerCodeEqualsFirst);
        CompareTwoNumbersValidator compareTwoNumbersEmpty = new CompareTwoNumbersValidator(emptyPlayerCode, 0, 1);

        // Le code secret est également vide, donc la comparaison est indéterminée
        assertTrue(compareTwoNumbersEmpty.check(emptyPlayerCode));

        // Test avec un secret vide et un joueur où le premier chiffre est plus grand que le deuxième
        Code emptySecret = new Code(emptyPlayerCode);
        Code playerCodeGreaterThanFirstInEmpty = new Code(3, 1, 5);
        CompareTwoNumbersValidator compareTwoNumbersGreaterThanFirstInEmpty = new CompareTwoNumbersValidator(emptySecret, 0, 1);

        // Le joueur a le premier chiffre plus grand que le deuxième, mais le secret est vide
        assertFalse(compareTwoNumbersGreaterThanFirstInEmpty.check(playerCodeGreaterThanFirstInEmpty));
    }

    @Test
    public void testCompareSumTwoNumber() {
        // Test avec un code joueur où la somme des deux premiers chiffres est plus petite que 7
        Code playerCodeSmallerSum = new Code(1, 3, 5);
        CompareSumTwoNumber compareSumTwoNumberSmaller = new CompareSumTwoNumber(playerCodeSmallerSum, 0, 1, 7);

        // Le code secret a également une somme des deux premiers chiffres plus petite que 7
        assertTrue(compareSumTwoNumberSmaller.check(playerCodeSmallerSum));

        // Test avec un code joueur où la somme des deux premiers chiffres est égale à 7
        Code playerCodeEqualsSum = new Code(3, 4, 3);
        CompareSumTwoNumber compareSumTwoNumberEquals = new CompareSumTwoNumber(playerCodeEqualsSum, 0, 1, 7);

        // Le code secret a également une somme des deux premiers chiffres égale à 7
        assertTrue(compareSumTwoNumberEquals.check(playerCodeEqualsSum));

        // Test avec un code joueur où la somme des deux premiers chiffres est plus grande que 7
        Code playerCodeGreaterThanSum = new Code(4, 4, 5);
        CompareSumTwoNumber compareSumTwoNumberGreaterThan = new CompareSumTwoNumber(playerCodeGreaterThanSum, 0, 1, 7);

        // Le code secret a également une somme des deux premiers chiffres plus grande que 7
        assertTrue(compareSumTwoNumberGreaterThan.check(playerCodeGreaterThanSum));

        // Test avec un code joueur vide
        Code emptyPlayerCode = new Code(playerCodeEqualsSum);
        CompareSumTwoNumber compareSumTwoNumberEmpty = new CompareSumTwoNumber(emptyPlayerCode, 0, 1, 7);

        // Le code secret est également vide, donc la comparaison est indéterminée
        assertTrue(compareSumTwoNumberEmpty.check(emptyPlayerCode));

        // Test avec un secret vide et un joueur où la somme des deux premiers chiffres est plus grande que 7
        Code emptySecret = new Code(emptyPlayerCode);
        Code playerCodeGreaterThanSumInEmpty = new Code(4, 4, 5);
        CompareSumTwoNumber compareSumTwoNumberGreaterThanInEmpty = new CompareSumTwoNumber(emptySecret, 0, 1, 7);

        // Le joueur a une somme des deux premiers chiffres plus grande que 7, mais le secret est vide
        assertFalse(compareSumTwoNumberGreaterThanInEmpty.check(playerCodeGreaterThanSumInEmpty));
    }

}

