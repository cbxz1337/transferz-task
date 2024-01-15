package com.transferz.repository;

public class RepositoryUtils {

    public static String WILD_CARD_MARK = "%";
    public static String EMPTY_STRING = "";

    public static String surroundWithWildCards(String param) {
        return WILD_CARD_MARK + param + WILD_CARD_MARK;
    }
}
