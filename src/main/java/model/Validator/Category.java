package model.Validator;

/**
 * The Category enum represents various categories that validators might belong to.
 */
public enum Category {
    PAIR, IMPAIR, // Even and odd categories
    PLUS_PETIT, PLUS_GRAND, EGAL, // Comparison categories: smaller, greater, equal

    AUCUNE_FOIS, UNE_FOIS, DEUX_FOiS, TROIS_FOIS, // Frequency categories: none, once, twice, thrice

    MAJORITE_PAIR, MAJORITE_IMPAIR, // Majority categories: majority even, majority odd

    AUCUN_CHIFFRE, PREM_CHIFFRE, SEC_CHIFFRE, TROIS_CHIFRE, // Digit occurrence categories: none, first, second, third

    PAS_DOUBLON, DOUBLON, CHIFFRE_TRIPLE, // Duplication categories: no duplicate, duplicate, triple digit

    CROISSANT, DECROISSANT, AUCUN; // Order categories: ascending, descending, no particular order
}
