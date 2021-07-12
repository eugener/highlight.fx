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


    /**
     * Chosen {@link Syntax} for control's text. Change of syntax will trigger re-rendering of the control
     * @return syntax property
     */
    public final ObjectProperty<Syntax> syntaxProperty() {
       return syntaxProperty;
    }

    /**
     * Returns currently selected {@link Syntax}
     * @return current syntax
     */
    public final Syntax getSyntax() {
       return syntaxProperty.get();
    }

    /**
     * Sets new {@link Syntax}. Triggers controls rendering
     * @param syntax new syntax
     */
    public final void setSyntax(Syntax syntax) {
        syntaxProperty.set(syntax);
    }


    // textProperty
    private final StringProperty textProperty = new SimpleStringProperty(this, "text", "");

    /**
     * Text to highlight. Change of text will trigger re-rendering of the control
     * @return string property
     */
    public final StringProperty textProperty() {
       return textProperty;
    }

    /**
     * Returns current text
     * @return current text
     */
    public final String getText() {
       return textProperty.get();
    }


    /**
     * Sets new text. Will trigger re-rendering of the control
     * @param text current text
     */
    public final void setText(String text) {
        textProperty.set(text);
    }




}
