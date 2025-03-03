package dk.sdu.cbse.core;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class Main extends Application {

    @Override
    public void start(Stage stage) throws Exception {
        Pane root = new Pane();
        Text text = new Text(50, 50, "Hello, JavaFX!");
        root.getChildren().add(text);

        Scene scene = new Scene(root, 400, 300);
        stage.setTitle("JavaFX Test Stage");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}