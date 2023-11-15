package fr.univ_lyon1.info.m1.elizagpt.model.SearchStrategies;

import fr.univ_lyon1.info.m1.elizagpt.model.Message;

import java.util.List;
import java.util.regex.Pattern;

abstract public class SearchStrategy {
    static public String name = "_UnamedSearchStrategy_";

    abstract public boolean match(String string, String pattern);
}