package fr.univ_lyon1.info.m1.elizagpt.model.SearchStrategies;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Substring extends SearchStrategy {
    public static final String name = "Substring";

    public boolean match(String str, String pattern) {
        Pattern regexPattern = Pattern.compile(pattern, Pattern.CASE_INSENSITIVE);
        Matcher matcher = regexPattern.matcher(str);
        return (matcher.matches());
    }
}