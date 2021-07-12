package org.oxbow.highlightfx.skin;

import javafx.scene.control.SkinBase;
import javafx.scene.text.Text;
import javafx.scene.text.TextFlow;
import org.oxbow.highlightfx.Syntax;
import org.oxbow.highlightfx.SyntaxPane;
import org.oxbow.highlightfx.lexer.Token;
import org.oxbow.highlightfx.lexer.TokenType;

import java.io.StringReader;

public class SyntaxPaneSkin extends SkinBase<SyntaxPane> {

    private final TextFlow textFlow = new TextFlow();

    public SyntaxPaneSkin(SyntaxPane syntaxPane) {
        super(syntaxPane);
        textFlow.getChildren().clear();
        textFlow.setTabSize(4);
        syntaxPane.syntaxProperty().addListener( x -> refresh());
        syntaxPane.textProperty().addListener( x -> refresh());
        getChildren().add(textFlow);
        refresh();
    }


    private void refresh() {

        String text = getSkinnable().getText();
        Syntax syntax = getSkinnable().getSyntax();
        textFlow.getChildren().clear();
        int textPos = 0;

        for (Token token: syntax.parse( new StringReader(text))) {

            try {

                if ( textPos < token.start) {
                    addText( textPos, token.start, TokenType.OPERATOR);
                }

                addText( token.start, token.end(), token.type);
                textPos = token.end();

            } catch ( Exception ex ) {
                ex.printStackTrace();
            }
        }

        int lastIndex = text.length()-1;
        if (textPos < lastIndex) {
            addText( textPos, lastIndex, TokenType.DEFAULT);
        }

    }


    private void addText( int start, int end, TokenType type ) {
        Text text = new Text(getSkinnable().getText().substring(start, end));
        text.getStyleClass().setAll(type.name().toLowerCase());
        textFlow.getChildren().add(text);
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
