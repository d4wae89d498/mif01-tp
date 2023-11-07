package fr.univ_lyon1.info.m1.elizagpt.model;

import javafx.scene.layout.HBox;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Logic for all discussion between user and eliza
 */
public class Discussion {

    private final List<Message> messages = new ArrayList<>();

    public void addMessage(Message message) {
        messages.add(message);
    }

    public void removeMessage() {

    }

    public List<Message> searchAllMatchingMessages(Pattern pattern) {
        List<Message> searchResult = new ArrayList<>();

        for (Message m: messages) {
            Matcher matcher = pattern.matcher(m.content);
            if (matcher.matches()) {
                searchResult.add(m);
            }
        }

        return searchResult;
    }

    public List<Message> getFullDiscussion() {
        return messages;
    }

    /**
     * Extract the name of the user from the dialog.
     * @return the name if it is found, null if not.
     */
    public String getUserName() {
        for (Message m: messages){
            Pattern pattern = Pattern.compile("Je m'appelle (.*)\\.",
                    Pattern.CASE_INSENSITIVE);
            Matcher matcher = pattern.matcher(m.content);
            if (matcher.matches()) {
                return matcher.group(1);
            }
        }
        return null;
    }

    public String processMessage(final String text) {

        MessageProcessor processor = new MessageProcessor();

        String normalizedText = processor.normalize(text);
        Pattern pattern;
        Matcher matcher;

        // First, try to answer specifically to what the user said
        pattern = Pattern.compile(".*Je m'appelle (.*)\\.", Pattern.CASE_INSENSITIVE);
        matcher = pattern.matcher(normalizedText);
        if (matcher.matches()) {
            return ("Bonjour " + matcher.group(1) + ".");

        }
        pattern = Pattern.compile("Quel est mon nom \\?", Pattern.CASE_INSENSITIVE);
        matcher = pattern.matcher(normalizedText);
        if (matcher.matches()) {
            if (getUserName() != null) {
                return ("Votre nom est " + getUserName() + ".");
            } else {
                return ("Je ne connais pas votre nom.");
            }
        }
        pattern = Pattern.compile("Qui est le plus (.*) \\?", Pattern.CASE_INSENSITIVE);
        matcher = pattern.matcher(normalizedText);
        if (matcher.matches()) {
            return ("Le plus " + matcher.group(1)
                    + " est bien sûr votre enseignant de MIF01 !");
        }
        pattern = Pattern.compile("(Je .*)\\.", Pattern.CASE_INSENSITIVE);
        matcher = pattern.matcher(normalizedText);
        if (matcher.matches()) {
            final String startQuestion = processor.pickRandom(new String[] {
                    "Pourquoi dites-vous que ",
                    "Pourquoi pensez-vous que ",
                    "Êtes-vous sûr que ",
            });
            return (startQuestion + processor.firstToSecondPerson(matcher.group(1)) + " ?");
        }
        pattern = Pattern.compile(".*\\?", Pattern.CASE_INSENSITIVE);
        matcher = pattern.matcher(normalizedText);
        if (matcher.matches()) {
            return (processor.pickRandom(new String[] {
                    "Je vous renvoie la question.",
                    "Ici, c'est moi qui pose les questions."
            }));
        }

        // Nothing clever to say, answer randomly
        String[] randomAnswers = new String[] {
                "Il faut beau aujourd'hui, vous ne trouvez pas ?",
                "Je ne comprends pas.",
                "Hmmm, hmm ...",
                "Qu'est-ce qui vous fait dire cela" + (getUserName() != null ? ", " + getUserName() : "") + " ?"
        };

        return processor.pickRandom(randomAnswers);
    }
}
