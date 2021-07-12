package org.oxbow.highlightfx;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.Scene;
import javafx.scene.control.ListView;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Objects;

public class Main extends Application {

    static ObservableList<Syntax> data = FXCollections.observableArrayList(
        Syntax.C,
        Syntax.CPP,
        Syntax.CLOJURE,
        Syntax.GROOVY,
        Syntax.JAVA,
        Syntax.JAVASCRIPT,
        Syntax.LUA,
        Syntax.PYTHON,
        Syntax.RUBY,
        Syntax.SCALA
    );


    @Override
    public void start(Stage stage) {

        SyntaxPane syntaxPane = new SyntaxPane();

        ListView<Syntax> languageList = new ListView<>();
        languageList.setItems(data);
        languageList.getSelectionModel().selectedItemProperty().addListener( (o, old, syntax) -> {
            syntaxPane.setSyntax(syntax);
            syntaxPane.setText( loadText( String.format("/%s.txt", syntax.name().toLowerCase()) ));
        });
        languageList.setFocusTraversable(false);
        languageList.getSelectionModel().select(0);
        languageList.setPrefWidth(120);


        BorderPane root = new BorderPane( new ScrollPane(syntaxPane));
        root.setLeft(languageList);

        Scene scene = new Scene( root, 800, 600);
        scene.getStylesheets().add(Objects.requireNonNull(Main.class.getResource("/styles.css")).toExternalForm());
        stage.setScene(scene);
        stage.setTitle("Highlight.FX Demo");

        stage.show();

    }

    private String loadText( String resource ) {
        try {
            return new String(Objects.requireNonNull(Main.class.getResourceAsStream(resource)).readAllBytes());
        } catch (IOException e) {
            e.printStackTrace();
            return "";
        }
    }

    public static void main(String[] args) {
        launch(args);
    }
}





