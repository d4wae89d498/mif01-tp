package fr.univ_lyon1.info.m1.elizagpt.view;

import java.util.ArrayList;
import java.util.List;

import fr.univ_lyon1.info.m1.elizagpt.controller.ActionController;
import fr.univ_lyon1.info.m1.elizagpt.model.Message;
import fr.univ_lyon1.info.m1.elizagpt.model.MessageProcessor;
import fr.univ_lyon1.info.m1.elizagpt.model.SearchStrategies.Regex;
import fr.univ_lyon1.info.m1.elizagpt.model.SearchStrategies.SearchStrategy;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.Random;


/**
 * Main class of the View (GUI) of the application.
 */
public class JfxView {
    private final VBox dialog;
    private TextField text = null;
    private TextField searchText = null;
    private Label searchTextLabel = null;

    private Button regexStreagyBtn = null;

    private Button substringStrategyBtn = null;

    private final ActionController actionController;

    /**
     * Create the main view of the application.
     */
        // TODO: style error in the following line. Check that checkstyle finds it, and then fix it.
        public JfxView(final Stage stage, final ActionController controller, final int width, final int height) {
        stage.setTitle("Eliza GPT");

        actionController = controller;
        controller.addView(this);

        final VBox root = new VBox(10);

        final Pane search = createSearchWidget(controller);
        root.getChildren().add(search);

        ScrollPane dialogScroll = new ScrollPane();
        dialog = new VBox(10);
        dialogScroll.setContent(dialog);
        // scroll to bottom by default:
        dialogScroll.vvalueProperty().bind(dialog.heightProperty());
        root.getChildren().add(dialogScroll);
        dialogScroll.setFitToWidth(true);

        final Pane input = createInputWidget(controller);
        root.getChildren().add(input);

        // Everything's ready: add it to the scene and display it
        final Scene scene = new Scene(root, width, height);
        stage.setScene(scene);
        text.requestFocus();
        stage.show();
    }

    static final String BASE_STYLE = "-fx-padding: 8px; "
            + "-fx-margin: 5px; "
            + "-fx-background-radius: 5px;";
    static final String USER_STYLE = "-fx-background-color: #A0E0A0; " + BASE_STYLE;
    static final String ELIZA_STYLE = "-fx-background-color: #A0A0E0; " + BASE_STYLE;

    public void removeHBoxesWithID(int id) {
        dialog.getChildren().removeAll(dialog.lookupAll("#" + id));
    }


    private void printMessage(final Message message) {
        HBox hBox = new HBox();
        hBox.setId(message.id + "");
        final Label label = new Label(message.content);
        Button deleteButton = new Button("X");
      //  deleteButton.setStyle("-fx-background-color: #DDDDDD;"); // Style du bouton de suppression
        HBox messageContainer = new HBox(label, deleteButton);
        hBox.getChildren().addAll(messageContainer);
        if (message.isFromUser) {
            messageContainer.setStyle(USER_STYLE);
            hBox.setAlignment(Pos.BASELINE_RIGHT);
        } else {
            messageContainer.setStyle(ELIZA_STYLE);
            hBox.setAlignment(Pos.BASELINE_LEFT);
        }
        dialog.getChildren().add(hBox);
        // Ajouter un gestionnaire d'événements pour le bouton de suppression
        deleteButton.setOnAction(event -> {
            actionController.deleteMessage(message.id);
           // hBox.
          //  message.delete();
            // Vous pouvez implémenter ici la logique pour supprimer le message
            // Par exemple, actionController.deleteMessage(message.content);
        });
    }


    private void printManyMessages(List<Message> messages){
        dialog.getChildren().clear();
        for (Message m : messages) {
            printMessage(m);
        }
    }

    private Pane createSearchWidget(ActionController controller) {
        final HBox firstLine = new HBox();
        final HBox secondLine = new HBox();
        firstLine.setAlignment(Pos.BASELINE_LEFT);
        secondLine.setAlignment(Pos.BASELINE_LEFT);
        searchText = new TextField();
        searchText.setOnAction(e -> {
            List<Message> messages = controller.searchFor(searchText.getText());
            printManyMessages(messages);
        });
        firstLine.getChildren().add(searchText);


        regexStreagyBtn = new Button("RegexStrategy");
        firstLine.getChildren().add(regexStreagyBtn);
        regexStreagyBtn.setOnAction(e -> {
            actionController.setSearchStrategyToRegex();
        });
        
        substringStrategyBtn = new Button("Substring");
        firstLine.getChildren().add(substringStrategyBtn);
        substringStrategyBtn.setOnAction(e -> {
            actionController.setSearchStrategyToSubstring();
        });

        final Button send = new Button("Search");
        send.setOnAction(e -> {
            List<Message> messages = controller.searchFor(searchText.getText());
            printManyMessages(messages);
            searchText.clear();
        });
        searchTextLabel = new Label();
        final Button undo = new Button("Undo search");
        undo.setOnAction(e -> {
            List<Message> messages = controller.getAllMessages();
            printManyMessages(messages);
            searchText.clear();
        });
        secondLine.getChildren().addAll(send, searchTextLabel, undo);
        final VBox input = new VBox();
        input.getChildren().addAll(firstLine, secondLine);
        return input;
    }

    private Pane createInputWidget(ActionController controller) {
        final Pane input = new HBox();
        text = new TextField();
        text.setOnAction(e -> {
            controller.answerMessage(text.getText());
            text.setText("");
        });
        final Button send = new Button("Send");
        send.setOnAction(e -> {
            controller.answerMessage(text.getText());
            text.setText("");
        });
        input.getChildren().addAll(text, send);
        return input;
    }

    public void receiveMessage(Message message){
        printMessage(message);
    }
}
