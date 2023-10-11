package fr.univ_lyon1.info.m1;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CharManipulatorTest {

    CharManipulator manipulator;

    @BeforeEach
    void setUp() {
        manipulator = new CharManipulator();
    }

    @Test
    void orderNormalString() {
        assertEquals("A", manipulator.invertOrder("A"));
        assertEquals("DCBA", manipulator.invertOrder("ABCD"));
        assertEquals("321DCBA", manipulator.invertOrder("ABCD123"));
    }

    @Test
    void orderEmptyString() {
        assertEquals("", manipulator.invertOrder(""));
    }

    @Test
    void orderStringWithSpaces()
    {
        assertEquals("   AA ", manipulator.invertOrder(" AA   "));
    }

    @Test
    void invertLowerCase()
    {
        assertEquals("AA", manipulator.invertCase("aa"));
    }

    @Test
    void invertUpperCase()
    {
        assertEquals("aa", manipulator.invertCase("AA"));
    }

    @Test
    void invertMixedCase()
    {
        assertEquals("aBcDeFgHIIIIjkkkkLL", manipulator.invertCase("AbCdEfGhiiiiJKKKKll"));
    }

    @Test
    void invertNonInvertableCharacters()
    {
        assertEquals("12345!", manipulator.invertCase("12345!"));
    }


    @Test
    void removeSimplePattern()
    {
        assertEquals("cc", manipulator.removePattern("coucou", "ou"));
    }

    @Test
    void removePatternEqualToString()
    {
        assertEquals("", manipulator.removePattern("bonjour", "bonjour"));
    }

    @Test
    void removeRecursivePattern()
    {
        assertEquals("jour", manipulator.removePattern("bonbonbonbonjourjourjourjourjour", "bonjour"));
    }
}