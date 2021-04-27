// Generated from /home/akira/IdeaProjects/NoteApp/calculator-cucumber/src/main/resources/Expression.g4 by ANTLR 4.9.1
package parser;
import org.antlr.v4.runtime.tree.ParseTreeVisitor;

/**
 * This interface defines a complete generic visitor for a parse tree produced
 * by {@link ExpressionParser}.
 *
 * @param <T> The return type of the visit operation. Use {@link Void} for
 * operations with no return type.
 */
public interface ExpressionVisitor<T> extends ParseTreeVisitor<T> {
	/**
	 * Visit a parse tree produced by {@link ExpressionParser#exp}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitExp(ExpressionParser.ExpContext ctx);
	/**
	 * Visit a parse tree produced by {@link ExpressionParser#plusMinus}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitPlusMinus(ExpressionParser.PlusMinusContext ctx);
	/**
	 * Visit a parse tree produced by {@link ExpressionParser#multDiv}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitMultDiv(ExpressionParser.MultDivContext ctx);
	/**
	 * Visit a parse tree produced by {@link ExpressionParser#value}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitValue(ExpressionParser.ValueContext ctx);
	/**
	 * Visit a parse tree produced by {@link ExpressionParser#parenth}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitParenth(ExpressionParser.ParenthContext ctx);
	/**
	 * Visit a parse tree produced by {@link ExpressionParser#fun}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitFun(ExpressionParser.FunContext ctx);
	/**
	 * Visit a parse tree produced by {@link ExpressionParser#nb}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitNb(ExpressionParser.NbContext ctx);
	/**
	 * Visit a parse tree produced by {@link ExpressionParser#deffun}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitDeffun(ExpressionParser.DeffunContext ctx);
	/**
	 * Visit a parse tree produced by {@link ExpressionParser#plusMinusf}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitPlusMinusf(ExpressionParser.PlusMinusfContext ctx);
	/**
	 * Visit a parse tree produced by {@link ExpressionParser#multDivf}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitMultDivf(ExpressionParser.MultDivfContext ctx);
	/**
	 * Visit a parse tree produced by {@link ExpressionParser#valuef}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitValuef(ExpressionParser.ValuefContext ctx);
	/**
	 * Visit a parse tree produced by {@link ExpressionParser#var}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitVar(ExpressionParser.VarContext ctx);
}