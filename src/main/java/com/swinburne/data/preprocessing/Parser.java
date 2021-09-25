package com.swinburne.data.preprocessing;

import com.swinburne.data.preprocessing.constant.CommonConstant;
import com.swinburne.data.preprocessing.ctype.Record;
import com.swinburne.data.preprocessing.ctype.Struct;
import com.swinburne.data.preprocessing.gen.CLexer;
import com.swinburne.data.preprocessing.gen.CParser;
import com.swinburne.data.preprocessing.parse.*;
import com.swinburne.data.preprocessing.treenode.TreeNode;
import lombok.Data;
import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.tree.ParseTree;

import java.io.*;
import java.util.HashMap;
import java.util.Map;

@Data
public class Parser {

    private Map<String, Record> variableMap = new HashMap<>();
    private Map<String, Record> structMap = new HashMap<>();

    private boolean showTreeNode = true;

    public Parser(boolean showTreeNode) {
        this.showTreeNode = showTreeNode;
    }

    public StringBuffer doParse(File file) {

        StringBuffer sbResult = new StringBuffer();
        InputStream inputStream = null;
        try {
            inputStream = new FileInputStream(file);
            CharStream charStream = CharStreams.fromStream(inputStream);
            CLexer lexer = new CLexer(charStream);
            CommonTokenStream tokens = new CommonTokenStream(lexer);
            CParser parser = new CParser(tokens);
            ParserRuleContext context = parser.translationUnit();

            lexer.removeErrorListener(ConsoleErrorListener.INSTANCE);
            parser.removeErrorListener(ConsoleErrorListener.INSTANCE);

            TreeNode treeNode = this.buildTree(context, false, 0);

            ParseFunctionParam parseFunctionParam = new ParseFunctionParam();
            parseFunctionParam.parseFunctionParams(treeNode, this.variableMap);

            ParseDeclaration parseDeclaration = new ParseDeclaration();
            parseDeclaration.parseDeclaration(treeNode, this.variableMap);

            ParseStructVariable parseStructVariable = new ParseStructVariable();
            StringBuffer structSb = parseStructVariable.parseStructVariable(treeNode, this.variableMap);

            ParsePrimaryParam primaryParam = new ParsePrimaryParam(this.variableMap);
            StringBuffer undefinedNonStructVarSb = primaryParam.findUndefinedNonStructVar(treeNode);

            ParseFunction parseFunction = new ParseFunction();
            StringBuffer undefinedFunctionSb = parseFunction.parseFunction(treeNode);

            StringBuffer headerSb = this.buildHeader();

            sbResult.append(headerSb).append(undefinedNonStructVarSb).append(structSb).append(undefinedFunctionSb);
            return sbResult;
        } catch (IOException e) {
            System.out.println("Error from " + file.getName());
            e.printStackTrace();
        } catch (RecognitionException e) {
            System.out.println("Error from " + file.getName());
            e.printStackTrace();
        } catch (Exception e) {
            System.out.println("Error from " + file.getName());
            e.printStackTrace();
        } finally {
            try {
                inputStream.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        return sbResult;
    }

    public TreeNode buildTree(RuleContext ctx, boolean verbose, int indentation) {
        boolean toBeIgnored = !verbose && ctx.getChildCount() == 1 && ctx.getChild(0) instanceof ParserRuleContext;

        if (!toBeIgnored) {
            String ruleName = CParser.ruleNames[ctx.getRuleIndex()];

            if (showTreeNode) {
                for (int i = 0; i < indentation; i++) {
                    System.out.print(" ");
                }

                System.out.println(ruleName + " -> " + ctx.getText());
            }
        }

        String ruleName = CParser.ruleNames[ctx.getRuleIndex()];
        String ruleValue = ctx.getText();

        TreeNode parentTreeNode = new TreeNode(ruleName, ruleValue);

        for (int i = 0; i < ctx.getChildCount(); i++) {
            ParseTree element = ctx.getChild(i);

            if (element instanceof RuleContext) {
                parseChild(parentTreeNode, (RuleContext) element, verbose, indentation + (toBeIgnored ? 0 : 1));
            }
        }

        return parentTreeNode;
    }

    private void parseChild(TreeNode parentTreeNode, RuleContext ctx, boolean verbose, int indentation) {
        boolean toBeIgnored = !verbose && ctx.getChildCount() == 1 && ctx.getChild(0) instanceof ParserRuleContext;

        TreeNode childNode = null;
        if (!toBeIgnored) {
            String ruleName = CParser.ruleNames[ctx.getRuleIndex()];
            String ruleValue = ctx.getText();

            if (showTreeNode) {
                for (int i = 0; i < indentation; i++) {
                    System.out.print(" ");
                }

                System.out.println(ruleName + " -> " + ctx.getText());
            }

            childNode = new TreeNode(ruleName, ruleValue);

            parentTreeNode.addChild(childNode);
        } else {
            childNode = parentTreeNode;
        }

        for (int i = 0; i < ctx.getChildCount(); i++) {
            ParseTree element = ctx.getChild(i);

            if (element instanceof RuleContext) {
                this.parseChild(childNode, (RuleContext) element, verbose, indentation + (toBeIgnored ? 0 : 1));
            }
        }
    }

    private StringBuffer buildHeader() {
        StringBuffer sb = new StringBuffer();
        sb.append("#include <stddef.h>").append(CommonConstant.NEW_LINE);
        sb.append("#include <stdint.h>").append(CommonConstant.NEW_LINE);
        sb.append("#include <time.h>").append(CommonConstant.NEW_LINE);
        sb.append("#include <string.h>").append(CommonConstant.NEW_LINE);
        sb.append("#include <stdlib.h>").append(CommonConstant.NEW_LINE);

        return sb;
    }
}
