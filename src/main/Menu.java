//package main;
//
//import javafx.application.Application;
//import javafx.embed.swing.JFXPanel;
//import javafx.scene.Scene;
//import javafx.scene.control.Button;
//import javafx.scene.layout.VBox;
//import javafx.stage.Stage;
//
//public class Menu extends Application {
//
//    @Override
//    public void start(Stage primaryStage) throws Exception {
//
//        // Erstellen der Buttons
//        Button button1 = new Button("Button 1");
//        Button button2 = new Button("Button 2");
//        Button button3 = new Button("Button 3");
//
//        // Erstellen des Layouts
//        VBox layout = new VBox(10); // 10 Pixel Abstand zwischen den Elementen
//        layout.getChildren().addAll(button1, button2, button3);
//
//        // Erstellen der Szene und Anzeigen des Fensters
//        Scene scene = new Scene(layout, 300, 200);
//        primaryStage.setScene(scene);
//        primaryStage.show();
//    }
//
//
//    public Scene getLayout() {
//        Button button1 = new Button("Button 1");
//        Button button2 = new Button("Button 2");
//        Button button3 = new Button("Button 3");
//
//        // Erstellen des Layouts
//        VBox layout = new VBox(10); // 10 Pixel Abstand zwischen den Elementen
//        layout.getChildren().addAll(button1, button2, button3);
//
//        // Erstellen der Szene und Anzeigen des Fensters
//        Scene scene = new Scene(layout, 300, 200);
//        JFXPanel primaryStage = new JFXPanel();
//        primaryStage.setScene(scene);
//        primaryStage.show();
//
//        return scene;
//    }
//}
