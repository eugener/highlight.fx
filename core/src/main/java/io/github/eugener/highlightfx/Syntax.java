package io.github.eugener.highlightfx;

import io.github.eugener.highlightfx.lexer.*;
import io.github.eugener.highlightfx.lexer.Lexer;
import io.github.eugener.highlightfx.lexer.Token;

import java.io.Reader;
import java.util.Collection;
import java.util.function.Function;

/**
 * Syntax types
 */
public enum Syntax {

    BASH(BashLexer::new),
    C(CLexer::new),
    CLOJURE(ClojureLexer::new),
    CPP(CppLexer::new),
    DOS_BATCH(DOSBatchLexer::new),
    GROOVY(GroovyLexer::new),
    JAVA(JavaLexer::new),
    JAVASCRIPT(JavaScriptLexer::new),
    JFLEX(JFlexLexer::new),
    LUA(LuaLexer::new),
    PROPERTIES(PropertiesLexer::new),
    PYTHON(PythonLexer::new),
    RUBY(RubyLexer::new),
    SCALA(ScalaLexer::new),
    SQL(SqlLexer::new),
    TAL(TALLexer::new),
    XHTML(XHTMLLexer::new),
    XML(XmlLexer::new),
    XPATH(XPathLexer::new),
    ;

    private final Function<Reader, Lexer> lexerProvider;

    Syntax(Function<Reader, Lexer> lexerProvider) {
        this.lexerProvider = lexerProvider;
    }

    public Collection<Token> parse(Reader reader) {
        return lexerProvider.apply(reader).parse();
    }

    @Override
    public String toString() {
        return name().substring(0, 1).toUpperCase() + name().substring(1);
    }
}
