// Generated from /home/akira/IdeaProjects/NoteApp/calculator-cucumber/src/main/resources/Expression.g4 by ANTLR 4.9.1
package parser;
import org.antlr.v4.runtime.tree.ParseTreeListener;

/**
 * This interface defines a complete listener for a parse tree produced by
 * {@link ExpressionParser}.
 */
public interface ExpressionListener extends ParseTreeListener {
	/**
	 * Enter a parse tree produced by {@link ExpressionParser#exp}.
	 * @param ctx the parse tree
	 */
	void enterExp(ExpressionParser.ExpContext ctx);
	/**
	 * Exit a parse tree produced by {@link ExpressionParser#exp}.
	 * @param ctx the parse tree
	 */
	void exitExp(ExpressionParser.ExpContext ctx);
	/**
	 * Enter a parse tree produced by {@link ExpressionParser#plusMinus}.
	 * @param ctx the parse tree
	 */
	void enterPlusMinus(ExpressionParser.PlusMinusContext ctx);
	/**
	 * Exit a parse tree produced by {@link ExpressionParser#plusMinus}.
	 * @param ctx the parse tree
	 */
	void exitPlusMinus(ExpressionParser.PlusMinusContext ctx);
	/**
	 * Enter a parse tree produced by {@link ExpressionParser#multDiv}.
	 * @param ctx the parse tree
	 */
	void enterMultDiv(ExpressionParser.MultDivContext ctx);
	/**
	 * Exit a parse tree produced by {@link ExpressionParser#multDiv}.
	 * @param ctx the parse tree
	 */
	void exitMultDiv(ExpressionParser.MultDivContext ctx);
	/**
	 * Enter a parse tree produced by {@link ExpressionParser#value}.
	 * @param ctx the parse tree
	 */
	void enterValue(ExpressionParser.ValueContext ctx);
	/**
	 * Exit a parse tree produced by {@link ExpressionParser#value}.
	 * @param ctx the parse tree
	 */
	void exitValue(ExpressionParser.ValueContext ctx);
	/**
	 * Enter a parse tree produced by {@link ExpressionParser#parenth}.
	 * @param ctx the parse tree
	 */
	void enterParenth(ExpressionParser.ParenthContext ctx);
	/**
	 * Exit a parse tree produced by {@link ExpressionParser#parenth}.
	 * @param ctx the parse tree
	 */
	void exitParenth(ExpressionParser.ParenthContext ctx);
	/**
	 * Enter a parse tree produced by {@link ExpressionParser#fun}.
	 * @param ctx the parse tree
	 */
	void enterFun(ExpressionParser.FunContext ctx);
	/**
	 * Exit a parse tree produced by {@link ExpressionParser#fun}.
	 * @param ctx the parse tree
	 */
	void exitFun(ExpressionParser.FunContext ctx);
	/**
	 * Enter a parse tree produced by {@link ExpressionParser#nb}.
	 * @param ctx the parse tree
	 */
	void enterNb(ExpressionParser.NbContext ctx);
	/**
	 * Exit a parse tree produced by {@link ExpressionParser#nb}.
	 * @param ctx the parse tree
	 */
	void exitNb(ExpressionParser.NbContext ctx);
}