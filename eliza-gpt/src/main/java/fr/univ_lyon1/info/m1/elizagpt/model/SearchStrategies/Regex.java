package fr.univ_lyon1.info.m1.elizagpt.model.SearchStrategies;

public class Regex extends SearchStrategy
{
    public static final String name = "Substring";

    public boolean match(String str, String pattern)
    {
        return str.contains(pattern);
    }
}
