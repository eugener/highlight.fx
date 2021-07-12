package org.oxbow.highlightfx;

import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.scene.control.Control;
import javafx.scene.control.Skin;
import org.oxbow.highlightfx.skin.SyntaxPaneSkin;

import java.util.Objects;

public class SyntaxPane extends Control {

    public SyntaxPane() {
        getStyleClass().add("syntax-pane");
    }

    @Override
    protected Skin<?> createDefaultSkin() {
        return new SyntaxPaneSkin(this);
    }

    @Override
    public String getUserAgentStylesheet() {
        return Objects.requireNonNull(SyntaxPane.class.getResource("darcula.css")).toExternalForm();
    }

    // syntaxProperty
    private final ObjectProperty<Syntax> syntaxProperty = new SimpleObjectProperty<>(this, "syntax", Syntax.JAVA);
    public final ObjectProperty<Syntax> syntaxProperty() {
       return syntaxProperty;
    }
    public final Syntax getSyntax() {
       return syntaxProperty.get();
    }
    public final void setSyntax(Syntax value) {
        syntaxProperty.set(value);
    }


    // textProperty
    private final StringProperty textProperty = new SimpleStringProperty(this, "text", "");
    public final StringProperty textProperty() {
       return textProperty;
    }
    public final String getText() {
       return textProperty.get();
    }
    public final void setText(String value) {
        textProperty.set(value);
    }




}
