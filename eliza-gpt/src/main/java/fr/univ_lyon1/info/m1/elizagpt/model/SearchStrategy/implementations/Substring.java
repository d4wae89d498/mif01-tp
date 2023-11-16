package fr.univ_lyon1.info.m1.elizagpt.model.SearchStrategies.implementations;

import fr.univ_lyon1.info.m1.elizagpt.model.SearchStrategies.SearchStrategyInterface;
import fr.univ_lyon1.info.m1.elizagpt.model.SearchStrategies.SearchStrategyName;


@SearchStrategyName("Substring")

public class Substring implements SearchStrategyInterface {

    public boolean match(String str, String pattern)
    {
        return str.contains(pattern);
    }

}