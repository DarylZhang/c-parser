package com.swinburne.data.preprocessing;

import com.swinburne.data.preprocessing.constant.CommonConstant;
import com.swinburne.data.preprocessing.ctype.Record;
import com.swinburne.data.preprocessing.ctype.Struct;
import com.swinburne.data.preprocessing.gen.CLexer;
import com.swinburne.data.preprocessing.gen.CParser;
import com.swinburne.data.preprocessing.parse.*;
import com.swinburne.data.preprocessing.treenode.TreeNode;
import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.tree.ParseTree;

import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class Test {

    public static void main(String[] args) {

        File testFile = new File(CommonConstant.TEST_FILE_DIRECTORY
                + "callerid.c_ast_connected_line_source_describe.c");

        try {
//            InputStream is = new FileInputStream(testFile);
//
//            CharStream input = CharStreams.fromStream(is);
//            CLexer lexer = new CLexer(input);
//            CommonTokenStream tokens = new CommonTokenStream(lexer);
//            CParser parser = new CParser(tokens);

            InputStream isForStruct = new FileInputStream(testFile);
            CharStream inputForStruct = CharStreams.fromStream(isForStruct);
            CLexer lexerForStruct = new CLexer(inputForStruct);
            CommonTokenStream tokensForStruct = new CommonTokenStream(lexerForStruct);
            CParser parserForStruct = new CParser(tokensForStruct);
            ParserRuleContext ctxForStruct = parserForStruct.translationUnit();
//            ParserRuleContext ctxForStruct = parserForStruct.structDeclarationList();
            System.out.println("=========== parser        struct ===========");

            Parser parser = new Parser(true);
            TreeNode treeNode = parser.buildTree(ctxForStruct, false, 0);
            Map<String, Record> variableMap = new HashMap<>();

            ParseFunctionParam parseFunctionParam = new ParseFunctionParam();
            parseFunctionParam.parseFunctionParams(treeNode, variableMap);

            ParseDeclaration parseDeclaration = new ParseDeclaration();
            parseDeclaration.parseDeclaration(treeNode, variableMap);

            ParseStructVariable parseStructVariable = new ParseStructVariable();
            StringBuffer structSb = parseStructVariable.parseStructVariable(treeNode, variableMap);

            ParsePrimaryParam primaryParam = new ParsePrimaryParam(variableMap);
            StringBuffer undefinedNonStructVarSb = primaryParam.findUndefinedNonStructVar(treeNode);

            ParseFunction parseFunction = new ParseFunction();
            StringBuffer undefinedFunctionSb = parseFunction.parseFunction(treeNode);

                    //            ArrayList<TreeNode> parameterDeclarations = treeNode.bfsSearchByCtxName(treeNode, "parameterDeclaration", new ArrayList<>());

//            StructParser structParser = new StructParser(false);
//            Map<String, Struct> structMap = structParser.parseStruct(ctxForStruct, false, 0);

            System.out.println("###############################");
//            StringBuffer sb = structParser.buildStructStatement(structMap);
//            System.out.println(sb);
            System.out.println("=========== ******************** ===========");


        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static void printAST(RuleContext ctx, boolean verbose, int indentation) {
        boolean toBeIgnored = !verbose && ctx.getChildCount() == 1 && ctx.getChild(0) instanceof ParserRuleContext;

        if (!toBeIgnored) {
            String ruleName = CParser.ruleNames[ctx.getRuleIndex()];

            for (int i = 0; i < indentation; i++) {
                System.out.print(" ");

            }

            System.out.println(ruleName + " -> " + ctx.getText());
        }

        for (int i = 0; i < ctx.getChildCount(); i++) {
            ParseTree element = ctx.getChild(i);

            if (element instanceof RuleContext) {
                printAST((RuleContext) element, verbose, indentation + (toBeIgnored ? 0 : 1));

            }
        }
    }





//    static class TestVisitor extends CBaseVisitor<Void> {
//
//        public Void visitAdd(CParser.ExpressionContext ctx) {
//            System.out.println("========= test add");
//            System.out.println("first arg: " + ctx.getText());
//            System.out.println("second arg: " + ctx.expr(1).getText());
//            return super.visit.visitAdd(ctx);
//        }
//    }
}
