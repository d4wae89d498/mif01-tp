package fr.univ_lyon1.info.m1.elizagpt;

import fr.univ_lyon1.info.m1.elizagpt.controller.ActionController;
import fr.univ_lyon1.info.m1.elizagpt.view.JfxView;
import javafx.application.Application;
import javafx.stage.Stage;

/**
 * Main class for the application (structure imposed by JavaFX).
 */
public class App extends Application {

    /**
     * With javafx, start() is called when the application is launched.
     */
    @Override
    public void start(final Stage stage) throws Exception {
        ActionController controller = new ActionController();
        new JfxView(stage, controller, 600, 600);
        // Second view (uncomment to activate)
        new JfxView(new Stage(), controller, 400, 400);
    }


    /**
     * A main method in case the user launches the application using
     * App as the main class.
     */
    public static void main(final String[] args) {
        Application.launch(args);
    }
}
