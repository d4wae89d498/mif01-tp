package fr.univ_lyon1.info.m1.elizagpt.controller;

import fr.univ_lyon1.info.m1.elizagpt.model.Discussion;
import fr.univ_lyon1.info.m1.elizagpt.model.Message;
import fr.univ_lyon1.info.m1.elizagpt.model.MessageProcessor;
import fr.univ_lyon1.info.m1.elizagpt.view.JfxView;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Controller that handles actions from the user interface.
 */
public class ActionController {

    private final MessageProcessor processor = new MessageProcessor();
    private final Discussion discussion = new Discussion();

    private final List<JfxView> viewsToNotify = new ArrayList<>();

    public void answerMessage(String text) {
        Message fromUser = new Message(text, true);
        discussion.addMessage(fromUser);
        notifyAll(fromUser);

        String answer = discussion.processMessage(text);
        Message fromEliza = new Message(answer, false);
        discussion.addMessage(fromEliza);
        notifyAll(fromEliza);
    }

    public List<Message> searchFor(String text) {
        return discussion.searchAllMatchingMessages(Pattern.compile(text, Pattern.CASE_INSENSITIVE));
    }

    public List<Message> getAllMessages() {
        return discussion.getFullDiscussion();
    }


    public void addView(JfxView view) {
        viewsToNotify.add(view);
    }

    private void notifyAll(Message message) {
        for (JfxView view: viewsToNotify) {
            view.receiveMessage(message);
        }
    }

    public void deleteMessage(int id) {
        //TODO
        discussion.removeMessage(id);
        for (JfxView view: viewsToNotify) {
            view.removeHBoxesWithID(id);
        }
    }
}
