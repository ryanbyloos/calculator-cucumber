// Generated from /home/akira/IdeaProjects/NoteApp/calculator-cucumber/src/main/resources/Expression.g4 by ANTLR 4.9.1
package parser;
import org.antlr.v4.runtime.atn.*;
import org.antlr.v4.runtime.dfa.DFA;
import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.misc.*;
import org.antlr.v4.runtime.tree.*;
import java.util.List;
import java.util.Iterator;
import java.util.ArrayList;

@SuppressWarnings({"all", "warnings", "unchecked", "unused", "cast"})
public class ExpressionParser extends Parser {
	static { RuntimeMetaData.checkVersion("4.9.1", RuntimeMetaData.VERSION); }

	protected static final DFA[] _decisionToDFA;
	protected static final PredictionContextCache _sharedContextCache =
		new PredictionContextCache();
	public static final int
		T__0=1, T__1=2, T__2=3, T__3=4, INT=5, DECIMAL=6, NAME=7, PLUS=8, MINUS=9, 
		MULT=10, DIV=11, WS=12;
	public static final int
		RULE_exp = 0, RULE_plusMinus = 1, RULE_multDiv = 2, RULE_value = 3, RULE_parenth = 4, 
		RULE_fun = 5, RULE_nb = 6, RULE_deffun = 7, RULE_plusMinusf = 8, RULE_multDivf = 9, 
		RULE_valuef = 10, RULE_var = 11;
	private static String[] makeRuleNames() {
		return new String[] {
			"exp", "plusMinus", "multDiv", "value", "parenth", "fun", "nb", "deffun", 
			"plusMinusf", "multDivf", "valuef", "var"
		};
	}
	public static final String[] ruleNames = makeRuleNames();

	private static String[] makeLiteralNames() {
		return new String[] {
			null, "'('", "')'", "'->'", "'x'", null, null, null, "'+'", "'-'", "'*'", 
			"'/'"
		};
	}
	private static final String[] _LITERAL_NAMES = makeLiteralNames();
	private static String[] makeSymbolicNames() {
		return new String[] {
			null, null, null, null, null, "INT", "DECIMAL", "NAME", "PLUS", "MINUS", 
			"MULT", "DIV", "WS"
		};
	}
	private static final String[] _SYMBOLIC_NAMES = makeSymbolicNames();
	public static final Vocabulary VOCABULARY = new VocabularyImpl(_LITERAL_NAMES, _SYMBOLIC_NAMES);

	/**
	 * @deprecated Use {@link #VOCABULARY} instead.
	 */
	@Deprecated
	public static final String[] tokenNames;
	static {
		tokenNames = new String[_SYMBOLIC_NAMES.length];
		for (int i = 0; i < tokenNames.length; i++) {
			tokenNames[i] = VOCABULARY.getLiteralName(i);
			if (tokenNames[i] == null) {
				tokenNames[i] = VOCABULARY.getSymbolicName(i);
			}

			if (tokenNames[i] == null) {
				tokenNames[i] = "<INVALID>";
			}
		}
	}

	@Override
	@Deprecated
	public String[] getTokenNames() {
		return tokenNames;
	}

	@Override

	public Vocabulary getVocabulary() {
		return VOCABULARY;
	}

	@Override
	public String getGrammarFileName() { return "Expression.g4"; }

	@Override
	public String[] getRuleNames() { return ruleNames; }

	@Override
	public String getSerializedATN() { return _serializedATN; }

	@Override
	public ATN getATN() { return _ATN; }

	public ExpressionParser(TokenStream input) {
		super(input);
		_interp = new ParserATNSimulator(this,_ATN,_decisionToDFA,_sharedContextCache);
	}

	public static class ExpContext extends ParserRuleContext {
		public PlusMinusContext plusMinus() {
			return getRuleContext(PlusMinusContext.class,0);
		}
		public DeffunContext deffun() {
			return getRuleContext(DeffunContext.class,0);
		}
		public ExpContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_exp; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof ExpressionListener ) ((ExpressionListener)listener).enterExp(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof ExpressionListener ) ((ExpressionListener)listener).exitExp(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof ExpressionVisitor ) return ((ExpressionVisitor<? extends T>)visitor).visitExp(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ExpContext exp() throws RecognitionException {
		ExpContext _localctx = new ExpContext(_ctx, getState());
		enterRule(_localctx, 0, RULE_exp);
		try {
			setState(26);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,0,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(24);
				plusMinus(0);
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(25);
				deffun();
				}
				break;
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class PlusMinusContext extends ParserRuleContext {
		public MultDivContext multDiv() {
			return getRuleContext(MultDivContext.class,0);
		}
		public PlusMinusContext plusMinus() {
			return getRuleContext(PlusMinusContext.class,0);
		}
		public TerminalNode PLUS() { return getToken(ExpressionParser.PLUS, 0); }
		public TerminalNode MINUS() { return getToken(ExpressionParser.MINUS, 0); }
		public PlusMinusContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_plusMinus; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof ExpressionListener ) ((ExpressionListener)listener).enterPlusMinus(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof ExpressionListener ) ((ExpressionListener)listener).exitPlusMinus(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof ExpressionVisitor ) return ((ExpressionVisitor<? extends T>)visitor).visitPlusMinus(this);
			else return visitor.visitChildren(this);
		}
	}

	public final PlusMinusContext plusMinus() throws RecognitionException {
		return plusMinus(0);
	}

	private PlusMinusContext plusMinus(int _p) throws RecognitionException {
		ParserRuleContext _parentctx = _ctx;
		int _parentState = getState();
		PlusMinusContext _localctx = new PlusMinusContext(_ctx, _parentState);
		PlusMinusContext _prevctx = _localctx;
		int _startState = 2;
		enterRecursionRule(_localctx, 2, RULE_plusMinus, _p);
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			{
			setState(29);
			multDiv(0);
			}
			_ctx.stop = _input.LT(-1);
			setState(39);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,2,_ctx);
			while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
				if ( _alt==1 ) {
					if ( _parseListeners!=null ) triggerExitRuleEvent();
					_prevctx = _localctx;
					{
					setState(37);
					_errHandler.sync(this);
					switch ( getInterpreter().adaptivePredict(_input,1,_ctx) ) {
					case 1:
						{
						_localctx = new PlusMinusContext(_parentctx, _parentState);
						pushNewRecursionContext(_localctx, _startState, RULE_plusMinus);
						setState(31);
						if (!(precpred(_ctx, 3))) throw new FailedPredicateException(this, "precpred(_ctx, 3)");
						setState(32);
						match(PLUS);
						setState(33);
						multDiv(0);
						}
						break;
					case 2:
						{
						_localctx = new PlusMinusContext(_parentctx, _parentState);
						pushNewRecursionContext(_localctx, _startState, RULE_plusMinus);
						setState(34);
						if (!(precpred(_ctx, 2))) throw new FailedPredicateException(this, "precpred(_ctx, 2)");
						setState(35);
						match(MINUS);
						setState(36);
						multDiv(0);
						}
						break;
					}
					} 
				}
				setState(41);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,2,_ctx);
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			unrollRecursionContexts(_parentctx);
		}
		return _localctx;
	}

	public static class MultDivContext extends ParserRuleContext {
		public ValueContext value() {
			return getRuleContext(ValueContext.class,0);
		}
		public MultDivContext multDiv() {
			return getRuleContext(MultDivContext.class,0);
		}
		public TerminalNode MULT() { return getToken(ExpressionParser.MULT, 0); }
		public TerminalNode DIV() { return getToken(ExpressionParser.DIV, 0); }
		public MultDivContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_multDiv; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof ExpressionListener ) ((ExpressionListener)listener).enterMultDiv(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof ExpressionListener ) ((ExpressionListener)listener).exitMultDiv(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof ExpressionVisitor ) return ((ExpressionVisitor<? extends T>)visitor).visitMultDiv(this);
			else return visitor.visitChildren(this);
		}
	}

	public final MultDivContext multDiv() throws RecognitionException {
		return multDiv(0);
	}

	private MultDivContext multDiv(int _p) throws RecognitionException {
		ParserRuleContext _parentctx = _ctx;
		int _parentState = getState();
		MultDivContext _localctx = new MultDivContext(_ctx, _parentState);
		MultDivContext _prevctx = _localctx;
		int _startState = 4;
		enterRecursionRule(_localctx, 4, RULE_multDiv, _p);
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			{
			setState(43);
			value();
			}
			_ctx.stop = _input.LT(-1);
			setState(53);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,4,_ctx);
			while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
				if ( _alt==1 ) {
					if ( _parseListeners!=null ) triggerExitRuleEvent();
					_prevctx = _localctx;
					{
					setState(51);
					_errHandler.sync(this);
					switch ( getInterpreter().adaptivePredict(_input,3,_ctx) ) {
					case 1:
						{
						_localctx = new MultDivContext(_parentctx, _parentState);
						pushNewRecursionContext(_localctx, _startState, RULE_multDiv);
						setState(45);
						if (!(precpred(_ctx, 3))) throw new FailedPredicateException(this, "precpred(_ctx, 3)");
						setState(46);
						match(MULT);
						setState(47);
						value();
						}
						break;
					case 2:
						{
						_localctx = new MultDivContext(_parentctx, _parentState);
						pushNewRecursionContext(_localctx, _startState, RULE_multDiv);
						setState(48);
						if (!(precpred(_ctx, 2))) throw new FailedPredicateException(this, "precpred(_ctx, 2)");
						setState(49);
						match(DIV);
						setState(50);
						value();
						}
						break;
					}
					} 
				}
				setState(55);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,4,_ctx);
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			unrollRecursionContexts(_parentctx);
		}
		return _localctx;
	}

	public static class ValueContext extends ParserRuleContext {
		public NbContext nb() {
			return getRuleContext(NbContext.class,0);
		}
		public FunContext fun() {
			return getRuleContext(FunContext.class,0);
		}
		public ParenthContext parenth() {
			return getRuleContext(ParenthContext.class,0);
		}
		public ValueContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_value; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof ExpressionListener ) ((ExpressionListener)listener).enterValue(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof ExpressionListener ) ((ExpressionListener)listener).exitValue(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof ExpressionVisitor ) return ((ExpressionVisitor<? extends T>)visitor).visitValue(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ValueContext value() throws RecognitionException {
		ValueContext _localctx = new ValueContext(_ctx, getState());
		enterRule(_localctx, 6, RULE_value);
		try {
			setState(59);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case INT:
			case DECIMAL:
				enterOuterAlt(_localctx, 1);
				{
				setState(56);
				nb();
				}
				break;
			case NAME:
				enterOuterAlt(_localctx, 2);
				{
				setState(57);
				fun();
				}
				break;
			case T__0:
				enterOuterAlt(_localctx, 3);
				{
				setState(58);
				parenth();
				}
				break;
			default:
				throw new NoViableAltException(this);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class ParenthContext extends ParserRuleContext {
		public PlusMinusContext plusMinus() {
			return getRuleContext(PlusMinusContext.class,0);
		}
		public ParenthContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_parenth; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof ExpressionListener ) ((ExpressionListener)listener).enterParenth(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof ExpressionListener ) ((ExpressionListener)listener).exitParenth(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof ExpressionVisitor ) return ((ExpressionVisitor<? extends T>)visitor).visitParenth(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ParenthContext parenth() throws RecognitionException {
		ParenthContext _localctx = new ParenthContext(_ctx, getState());
		enterRule(_localctx, 8, RULE_parenth);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(61);
			match(T__0);
			setState(62);
			plusMinus(0);
			setState(63);
			match(T__1);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class FunContext extends ParserRuleContext {
		public TerminalNode NAME() { return getToken(ExpressionParser.NAME, 0); }
		public PlusMinusContext plusMinus() {
			return getRuleContext(PlusMinusContext.class,0);
		}
		public FunContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_fun; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof ExpressionListener ) ((ExpressionListener)listener).enterFun(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof ExpressionListener ) ((ExpressionListener)listener).exitFun(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof ExpressionVisitor ) return ((ExpressionVisitor<? extends T>)visitor).visitFun(this);
			else return visitor.visitChildren(this);
		}
	}

	public final FunContext fun() throws RecognitionException {
		FunContext _localctx = new FunContext(_ctx, getState());
		enterRule(_localctx, 10, RULE_fun);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(65);
			match(NAME);
			setState(66);
			match(T__0);
			setState(67);
			plusMinus(0);
			setState(68);
			match(T__1);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class NbContext extends ParserRuleContext {
		public TerminalNode INT() { return getToken(ExpressionParser.INT, 0); }
		public TerminalNode DECIMAL() { return getToken(ExpressionParser.DECIMAL, 0); }
		public NbContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_nb; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof ExpressionListener ) ((ExpressionListener)listener).enterNb(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof ExpressionListener ) ((ExpressionListener)listener).exitNb(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof ExpressionVisitor ) return ((ExpressionVisitor<? extends T>)visitor).visitNb(this);
			else return visitor.visitChildren(this);
		}
	}

	public final NbContext nb() throws RecognitionException {
		NbContext _localctx = new NbContext(_ctx, getState());
		enterRule(_localctx, 12, RULE_nb);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(70);
			_la = _input.LA(1);
			if ( !(_la==INT || _la==DECIMAL) ) {
			_errHandler.recoverInline(this);
			}
			else {
				if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
				_errHandler.reportMatch(this);
				consume();
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class DeffunContext extends ParserRuleContext {
		public TerminalNode NAME() { return getToken(ExpressionParser.NAME, 0); }
		public PlusMinusfContext plusMinusf() {
			return getRuleContext(PlusMinusfContext.class,0);
		}
		public DeffunContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_deffun; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof ExpressionListener ) ((ExpressionListener)listener).enterDeffun(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof ExpressionListener ) ((ExpressionListener)listener).exitDeffun(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof ExpressionVisitor ) return ((ExpressionVisitor<? extends T>)visitor).visitDeffun(this);
			else return visitor.visitChildren(this);
		}
	}

	public final DeffunContext deffun() throws RecognitionException {
		DeffunContext _localctx = new DeffunContext(_ctx, getState());
		enterRule(_localctx, 14, RULE_deffun);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(72);
			match(NAME);
			setState(73);
			match(T__2);
			setState(74);
			match(T__0);
			setState(75);
			plusMinusf(0);
			setState(76);
			match(T__1);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class PlusMinusfContext extends ParserRuleContext {
		public MultDivfContext multDivf() {
			return getRuleContext(MultDivfContext.class,0);
		}
		public PlusMinusfContext plusMinusf() {
			return getRuleContext(PlusMinusfContext.class,0);
		}
		public TerminalNode PLUS() { return getToken(ExpressionParser.PLUS, 0); }
		public TerminalNode MINUS() { return getToken(ExpressionParser.MINUS, 0); }
		public PlusMinusfContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_plusMinusf; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof ExpressionListener ) ((ExpressionListener)listener).enterPlusMinusf(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof ExpressionListener ) ((ExpressionListener)listener).exitPlusMinusf(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof ExpressionVisitor ) return ((ExpressionVisitor<? extends T>)visitor).visitPlusMinusf(this);
			else return visitor.visitChildren(this);
		}
	}

	public final PlusMinusfContext plusMinusf() throws RecognitionException {
		return plusMinusf(0);
	}

	private PlusMinusfContext plusMinusf(int _p) throws RecognitionException {
		ParserRuleContext _parentctx = _ctx;
		int _parentState = getState();
		PlusMinusfContext _localctx = new PlusMinusfContext(_ctx, _parentState);
		PlusMinusfContext _prevctx = _localctx;
		int _startState = 16;
		enterRecursionRule(_localctx, 16, RULE_plusMinusf, _p);
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			{
			setState(79);
			multDivf(0);
			}
			_ctx.stop = _input.LT(-1);
			setState(89);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,7,_ctx);
			while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
				if ( _alt==1 ) {
					if ( _parseListeners!=null ) triggerExitRuleEvent();
					_prevctx = _localctx;
					{
					setState(87);
					_errHandler.sync(this);
					switch ( getInterpreter().adaptivePredict(_input,6,_ctx) ) {
					case 1:
						{
						_localctx = new PlusMinusfContext(_parentctx, _parentState);
						pushNewRecursionContext(_localctx, _startState, RULE_plusMinusf);
						setState(81);
						if (!(precpred(_ctx, 3))) throw new FailedPredicateException(this, "precpred(_ctx, 3)");
						setState(82);
						match(PLUS);
						setState(83);
						multDivf(0);
						}
						break;
					case 2:
						{
						_localctx = new PlusMinusfContext(_parentctx, _parentState);
						pushNewRecursionContext(_localctx, _startState, RULE_plusMinusf);
						setState(84);
						if (!(precpred(_ctx, 2))) throw new FailedPredicateException(this, "precpred(_ctx, 2)");
						setState(85);
						match(MINUS);
						setState(86);
						multDivf(0);
						}
						break;
					}
					} 
				}
				setState(91);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,7,_ctx);
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			unrollRecursionContexts(_parentctx);
		}
		return _localctx;
	}

	public static class MultDivfContext extends ParserRuleContext {
		public ValuefContext valuef() {
			return getRuleContext(ValuefContext.class,0);
		}
		public MultDivfContext multDivf() {
			return getRuleContext(MultDivfContext.class,0);
		}
		public TerminalNode MULT() { return getToken(ExpressionParser.MULT, 0); }
		public TerminalNode DIV() { return getToken(ExpressionParser.DIV, 0); }
		public MultDivfContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_multDivf; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof ExpressionListener ) ((ExpressionListener)listener).enterMultDivf(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof ExpressionListener ) ((ExpressionListener)listener).exitMultDivf(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof ExpressionVisitor ) return ((ExpressionVisitor<? extends T>)visitor).visitMultDivf(this);
			else return visitor.visitChildren(this);
		}
	}

	public final MultDivfContext multDivf() throws RecognitionException {
		return multDivf(0);
	}

	private MultDivfContext multDivf(int _p) throws RecognitionException {
		ParserRuleContext _parentctx = _ctx;
		int _parentState = getState();
		MultDivfContext _localctx = new MultDivfContext(_ctx, _parentState);
		MultDivfContext _prevctx = _localctx;
		int _startState = 18;
		enterRecursionRule(_localctx, 18, RULE_multDivf, _p);
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			{
			setState(93);
			valuef();
			}
			_ctx.stop = _input.LT(-1);
			setState(103);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,9,_ctx);
			while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
				if ( _alt==1 ) {
					if ( _parseListeners!=null ) triggerExitRuleEvent();
					_prevctx = _localctx;
					{
					setState(101);
					_errHandler.sync(this);
					switch ( getInterpreter().adaptivePredict(_input,8,_ctx) ) {
					case 1:
						{
						_localctx = new MultDivfContext(_parentctx, _parentState);
						pushNewRecursionContext(_localctx, _startState, RULE_multDivf);
						setState(95);
						if (!(precpred(_ctx, 3))) throw new FailedPredicateException(this, "precpred(_ctx, 3)");
						setState(96);
						match(MULT);
						setState(97);
						valuef();
						}
						break;
					case 2:
						{
						_localctx = new MultDivfContext(_parentctx, _parentState);
						pushNewRecursionContext(_localctx, _startState, RULE_multDivf);
						setState(98);
						if (!(precpred(_ctx, 2))) throw new FailedPredicateException(this, "precpred(_ctx, 2)");
						setState(99);
						match(DIV);
						setState(100);
						valuef();
						}
						break;
					}
					} 
				}
				setState(105);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,9,_ctx);
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			unrollRecursionContexts(_parentctx);
		}
		return _localctx;
	}

	public static class ValuefContext extends ParserRuleContext {
		public NbContext nb() {
			return getRuleContext(NbContext.class,0);
		}
		public FunContext fun() {
			return getRuleContext(FunContext.class,0);
		}
		public ParenthContext parenth() {
			return getRuleContext(ParenthContext.class,0);
		}
		public VarContext var() {
			return getRuleContext(VarContext.class,0);
		}
		public ValuefContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_valuef; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof ExpressionListener ) ((ExpressionListener)listener).enterValuef(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof ExpressionListener ) ((ExpressionListener)listener).exitValuef(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof ExpressionVisitor ) return ((ExpressionVisitor<? extends T>)visitor).visitValuef(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ValuefContext valuef() throws RecognitionException {
		ValuefContext _localctx = new ValuefContext(_ctx, getState());
		enterRule(_localctx, 20, RULE_valuef);
		try {
			setState(110);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case INT:
			case DECIMAL:
				enterOuterAlt(_localctx, 1);
				{
				setState(106);
				nb();
				}
				break;
			case NAME:
				enterOuterAlt(_localctx, 2);
				{
				setState(107);
				fun();
				}
				break;
			case T__0:
				enterOuterAlt(_localctx, 3);
				{
				setState(108);
				parenth();
				}
				break;
			case T__3:
				enterOuterAlt(_localctx, 4);
				{
				setState(109);
				var();
				}
				break;
			default:
				throw new NoViableAltException(this);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class VarContext extends ParserRuleContext {
		public VarContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_var; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof ExpressionListener ) ((ExpressionListener)listener).enterVar(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof ExpressionListener ) ((ExpressionListener)listener).exitVar(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof ExpressionVisitor ) return ((ExpressionVisitor<? extends T>)visitor).visitVar(this);
			else return visitor.visitChildren(this);
		}
	}

	public final VarContext var() throws RecognitionException {
		VarContext _localctx = new VarContext(_ctx, getState());
		enterRule(_localctx, 22, RULE_var);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(112);
			match(T__3);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public boolean sempred(RuleContext _localctx, int ruleIndex, int predIndex) {
		switch (ruleIndex) {
		case 1:
			return plusMinus_sempred((PlusMinusContext)_localctx, predIndex);
		case 2:
			return multDiv_sempred((MultDivContext)_localctx, predIndex);
		case 8:
			return plusMinusf_sempred((PlusMinusfContext)_localctx, predIndex);
		case 9:
			return multDivf_sempred((MultDivfContext)_localctx, predIndex);
		}
		return true;
	}
	private boolean plusMinus_sempred(PlusMinusContext _localctx, int predIndex) {
		switch (predIndex) {
		case 0:
			return precpred(_ctx, 3);
		case 1:
			return precpred(_ctx, 2);
		}
		return true;
	}
	private boolean multDiv_sempred(MultDivContext _localctx, int predIndex) {
		switch (predIndex) {
		case 2:
			return precpred(_ctx, 3);
		case 3:
			return precpred(_ctx, 2);
		}
		return true;
	}
	private boolean plusMinusf_sempred(PlusMinusfContext _localctx, int predIndex) {
		switch (predIndex) {
		case 4:
			return precpred(_ctx, 3);
		case 5:
			return precpred(_ctx, 2);
		}
		return true;
	}
	private boolean multDivf_sempred(MultDivfContext _localctx, int predIndex) {
		switch (predIndex) {
		case 6:
			return precpred(_ctx, 3);
		case 7:
			return precpred(_ctx, 2);
		}
		return true;
	}

	public static final String _serializedATN =
		"\3\u608b\ua72a\u8133\ub9ed\u417c\u3be7\u7786\u5964\3\16u\4\2\t\2\4\3\t"+
		"\3\4\4\t\4\4\5\t\5\4\6\t\6\4\7\t\7\4\b\t\b\4\t\t\t\4\n\t\n\4\13\t\13\4"+
		"\f\t\f\4\r\t\r\3\2\3\2\5\2\35\n\2\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3"+
		"\7\3(\n\3\f\3\16\3+\13\3\3\4\3\4\3\4\3\4\3\4\3\4\3\4\3\4\3\4\7\4\66\n"+
		"\4\f\4\16\49\13\4\3\5\3\5\3\5\5\5>\n\5\3\6\3\6\3\6\3\6\3\7\3\7\3\7\3\7"+
		"\3\7\3\b\3\b\3\t\3\t\3\t\3\t\3\t\3\t\3\n\3\n\3\n\3\n\3\n\3\n\3\n\3\n\3"+
		"\n\7\nZ\n\n\f\n\16\n]\13\n\3\13\3\13\3\13\3\13\3\13\3\13\3\13\3\13\3\13"+
		"\7\13h\n\13\f\13\16\13k\13\13\3\f\3\f\3\f\3\f\5\fq\n\f\3\r\3\r\3\r\2\6"+
		"\4\6\22\24\16\2\4\6\b\n\f\16\20\22\24\26\30\2\3\3\2\7\b\2v\2\34\3\2\2"+
		"\2\4\36\3\2\2\2\6,\3\2\2\2\b=\3\2\2\2\n?\3\2\2\2\fC\3\2\2\2\16H\3\2\2"+
		"\2\20J\3\2\2\2\22P\3\2\2\2\24^\3\2\2\2\26p\3\2\2\2\30r\3\2\2\2\32\35\5"+
		"\4\3\2\33\35\5\20\t\2\34\32\3\2\2\2\34\33\3\2\2\2\35\3\3\2\2\2\36\37\b"+
		"\3\1\2\37 \5\6\4\2 )\3\2\2\2!\"\f\5\2\2\"#\7\n\2\2#(\5\6\4\2$%\f\4\2\2"+
		"%&\7\13\2\2&(\5\6\4\2\'!\3\2\2\2\'$\3\2\2\2(+\3\2\2\2)\'\3\2\2\2)*\3\2"+
		"\2\2*\5\3\2\2\2+)\3\2\2\2,-\b\4\1\2-.\5\b\5\2.\67\3\2\2\2/\60\f\5\2\2"+
		"\60\61\7\f\2\2\61\66\5\b\5\2\62\63\f\4\2\2\63\64\7\r\2\2\64\66\5\b\5\2"+
		"\65/\3\2\2\2\65\62\3\2\2\2\669\3\2\2\2\67\65\3\2\2\2\678\3\2\2\28\7\3"+
		"\2\2\29\67\3\2\2\2:>\5\16\b\2;>\5\f\7\2<>\5\n\6\2=:\3\2\2\2=;\3\2\2\2"+
		"=<\3\2\2\2>\t\3\2\2\2?@\7\3\2\2@A\5\4\3\2AB\7\4\2\2B\13\3\2\2\2CD\7\t"+
		"\2\2DE\7\3\2\2EF\5\4\3\2FG\7\4\2\2G\r\3\2\2\2HI\t\2\2\2I\17\3\2\2\2JK"+
		"\7\t\2\2KL\7\5\2\2LM\7\3\2\2MN\5\22\n\2NO\7\4\2\2O\21\3\2\2\2PQ\b\n\1"+
		"\2QR\5\24\13\2R[\3\2\2\2ST\f\5\2\2TU\7\n\2\2UZ\5\24\13\2VW\f\4\2\2WX\7"+
		"\13\2\2XZ\5\24\13\2YS\3\2\2\2YV\3\2\2\2Z]\3\2\2\2[Y\3\2\2\2[\\\3\2\2\2"+
		"\\\23\3\2\2\2][\3\2\2\2^_\b\13\1\2_`\5\26\f\2`i\3\2\2\2ab\f\5\2\2bc\7"+
		"\f\2\2ch\5\26\f\2de\f\4\2\2ef\7\r\2\2fh\5\26\f\2ga\3\2\2\2gd\3\2\2\2h"+
		"k\3\2\2\2ig\3\2\2\2ij\3\2\2\2j\25\3\2\2\2ki\3\2\2\2lq\5\16\b\2mq\5\f\7"+
		"\2nq\5\n\6\2oq\5\30\r\2pl\3\2\2\2pm\3\2\2\2pn\3\2\2\2po\3\2\2\2q\27\3"+
		"\2\2\2rs\7\6\2\2s\31\3\2\2\2\r\34\')\65\67=Y[gip";
	public static final ATN _ATN =
		new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}