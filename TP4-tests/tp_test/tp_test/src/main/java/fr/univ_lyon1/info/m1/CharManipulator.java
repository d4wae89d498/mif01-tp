package fr.univ_lyon1.info.m1;

public class CharManipulator implements ICharManipulator {

    public String invertOrder(String s)
    {
        StringBuilder res = new StringBuilder();
        for (int i = s.length() -1; i >= 0; i--)
        {
            res.append(s.charAt(i));
        }
        return res.toString();
    }

    public String invertCase(String s)
    {
        StringBuilder res = new StringBuilder();
        for (int i = 0; i < s.length(); ++i)
        {
            res.append(invertCase(s.charAt(i)));
        }
        return res.toString();
    }

    private char invertCase(char c)
    {
        if(Character.isUpperCase(c))
        {
            return Character.toLowerCase(c);
        }
        if (Character.isLowerCase(c))
        {
            return Character.toUpperCase(c);
        }
        return c;
    }

    public String removePattern(String str, String pattern) {
        String oldString;
        do {
            oldString = str;
            str = str.replaceAll(pattern, "");
        } while (!oldString.equals(str));
        return str;
    }
}
