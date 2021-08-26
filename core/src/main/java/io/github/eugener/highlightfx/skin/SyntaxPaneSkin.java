package io.github.eugener.highlightfx.skin;

import javafx.scene.control.SkinBase;
import javafx.scene.text.Text;
import javafx.scene.text.TextFlow;
import io.github.eugener.highlightfx.SyntaxPane;
import io.github.eugener.highlightfx.lexer.Token;
import io.github.eugener.highlightfx.lexer.TokenType;

import java.io.StringReader;

public class SyntaxPaneSkin extends SkinBase<SyntaxPane> {

    private final TextFlow textFlow = new TextFlow();

    public SyntaxPaneSkin(SyntaxPane syntaxPane) {
        super(syntaxPane);
        syntaxPane.syntaxProperty().addListener( x -> refresh());
        syntaxPane.textProperty().addListener( x -> refresh());
        syntaxPane.tabSizeProperty().bind(textFlow.tabSizeProperty());
        getChildren().add(textFlow);
        refresh();
    }


    private void refresh() {

        String text = getSkinnable().getText();
        textFlow.getChildren().clear();
        int textPos = 0;

        for (Token token: getSkinnable().getSyntax().parse( new StringReader(text))) {
            try {

                // try to add non-tokenized text if exists
                addText( textPos, token.start, TokenType.OPERATOR);

                // add current tokenized text
                addText( token.start, token.end(), token.type);

                // update current position
                textPos = token.end();

            } catch ( Exception ex ) {
                ex.printStackTrace();
            }
        }

        // try to add any leftover non-tokenized text
        addText( textPos, text.length()-1, TokenType.DEFAULT);


    }


    private void addText( int start, int end, TokenType type ) {
        if (start < end) {
            Text text = new Text(getSkinnable().getText().substring(start, end));
            text.getStyleClass().setAll(type.name().toLowerCase());
            textFlow.getChildren().add(text);
        }
    }

    @Override
    protected double computeMinWidth(double height, double topInset, double rightInset, double bottomInset, double leftInset) {
        return textFlow.minWidth(height);
    }

    @Override
    protected double computeMinHeight(double width, double topInset, double rightInset, double bottomInset, double leftInset) {
        return textFlow.minHeight(width);
    }

    @Override
    protected double computePrefWidth(double height, double topInset, double rightInset, double bottomInset, double leftInset) {
        return textFlow.prefWidth(height) + leftInset + rightInset;
    }

    @Override
    protected double computePrefHeight(double width, double topInset, double rightInset, double bottomInset, double leftInset) {
        return textFlow.prefHeight(width) + topInset + bottomInset;
    }

    @Override
    protected double computeMaxWidth(double height, double topInset, double rightInset, double bottomInset, double leftInset) {
        return super.computeMaxWidth(height, topInset, rightInset, bottomInset, leftInset);
    }

    @Override
    protected double computeMaxHeight(double width, double topInset, double rightInset, double bottomInset, double leftInset) {
        return super.computeMaxHeight(width, topInset, rightInset, bottomInset, leftInset);
    }

    @Override
    protected void layoutChildren(double x, double y, double w, double h) {
        textFlow.resizeRelocate(x,y,w,h);
    }
}
