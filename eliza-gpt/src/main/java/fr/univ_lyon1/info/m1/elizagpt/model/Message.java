package fr.univ_lyon1.info.m1.elizagpt.model;

/**
 * Logic for a single message.
 */
public class Message {

    private static int staticId = 0;
    public final int id;
    public final String content;
    public final boolean isFromUser;

    public Message(String text, boolean isFromUser){
        content = text;
        this.isFromUser = isFromUser;
        id = staticId++;
    }
}
