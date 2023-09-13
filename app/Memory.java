package app;

import view.*;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Memory extends Application {

    @Override
    public void start(Stage stage){
        Scene scene = new Scene(new MemoryView(8,stage));
        stage.setTitle("MEMORY");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args){
        launch(args);
    }

}