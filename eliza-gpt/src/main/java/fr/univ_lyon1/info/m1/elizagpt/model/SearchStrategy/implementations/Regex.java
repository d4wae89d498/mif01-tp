package fr.univ_lyon1.info.m1.elizagpt.model.SearchStrategies.implementations;

import fr.univ_lyon1.info.m1.elizagpt.model.SearchStrategies.SearchStrategyInterface;
import fr.univ_lyon1.info.m1.elizagpt.model.SearchStrategies.SearchStrategyName;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

@SearchStrategyName("Regex")
public class Regex implements SearchStrategyInterface
{

    public boolean match(String str, String pattern) {
        Pattern regexPattern = Pattern.compile(pattern, Pattern.CASE_INSENSITIVE);
        Matcher matcher = regexPattern.matcher(str);
        return (matcher.matches());
    }
}
