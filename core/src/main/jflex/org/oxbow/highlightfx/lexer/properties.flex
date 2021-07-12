/*
 * Copyright 2008 Ayman Al-Sairafi ayman.alsairafi@gmail.com
 * Copyright 2021 Eugene Ryzhikov eryzhikov@hotmail.com
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License
 *       at http://www.apache.org/licenses/LICENSE-2.0
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.oxbow.highlightfx.lexer;

%%

%public
%class PropertiesLexer
%extends DefaultJFlexLexer
%final
%unicode
%char
%type Token


%{
    /**
     * Create an empty lexer, yyrset will be called later to reset and assign
     * the reader
     */
    public PropertiesLexer() {
        super();
    }

    @Override
    public int yychar() {
        return yychar;
    }
%}

StartComment = #
WhiteSpace = [ \t]
LineTerminator = \r|\n|\r\n
InputCharacter = [^\r\n]
KeyCharacter = [a-zA-Z0-9._ ]

%%

<YYINITIAL> 
{
	{KeyCharacter}+{WhiteSpace}*=    { return token(TokenType.KEYWORD); }
        {StartComment} {InputCharacter}* {LineTerminator}?         
                                         { return token(TokenType.COMMENT); }
        . | {LineTerminator}             { /* skip */ }
}

<<EOF>>                   { return null; }