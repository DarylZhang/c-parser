package com.swinburne.data.preprocessing;

import com.swinburne.data.preprocessing.constant.CommonConstant;
import com.swinburne.data.preprocessing.gen.CBaseVisitor;
import com.swinburne.data.preprocessing.gen.CLexer;
import com.swinburne.data.preprocessing.gen.CParser;
import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.tree.ParseTree;

import java.io.*;

public class Test2 {

    public static void main(String[] args) {

        File testFile = new File(CommonConstant.TEST_FILE_DIRECTORY
                + "resource_recordings.c_ast_ari_recordings_get_stored_file.c");

        try {
            InputStream is = new FileInputStream(testFile);

            CharStream input = CharStreams.fromStream(is);
            CLexer lexer = new CLexer(input);
            CommonTokenStream tokens = new CommonTokenStream(lexer);
            CParser parser = new CParser(tokens);
//            SyntaxTree syntaxTree = parser.declaration();;
//            ParseTree parseTree = parser.getContext();
//            System.out.println(syntaxTree.toStringTree());
//            ParserRuleContext ctx = parser.declaration();
//            ParserRuleContext ctx = parser.pointer();
//            parser.expression()
//            ParseTree tree = parser.; // parse
//            MathVisitorTest vt=new MathVisitorTest();
//            vt.visit(tree);

//            TestVisitor visitorTest = new TestVisitor();
//            Object obj = visitorTest.visitStructDeclarationList(parser.structDeclarationList());
//            System.out.print(obj);

            InputStream is1 = new FileInputStream(testFile);
            CharStream input1 = CharStreams.fromStream(is1);
            CLexer lexer1 = new CLexer(input1);
            CommonTokenStream tokens1 = new CommonTokenStream(lexer1);
            CParser parser1 = new CParser(tokens1);
            ParserRuleContext ctx1 = parser1.assignmentExpression();
            System.out.println("=========== parser.assignmentExpression() ===========");
            printAST(ctx1, false, 0);
            System.out.println("=========== ******************** ===========");
            System.out.println("");
            System.out.println("");

            InputStream is2 = new FileInputStream(testFile);
            CharStream input2 = CharStreams.fromStream(is2);
            CLexer lexer2 = new CLexer(input2);
            CommonTokenStream tokens2 = new CommonTokenStream(lexer2);
            CParser parser2 = new CParser(tokens2);
            ParserRuleContext ctx2 = parser2.declarationSpecifiers2();
            System.out.println("=========== parser.declarationSpecifiers2() ===========");
            printAST(ctx2, false, 0);
            System.out.println("=========== ******************** ===========");
            System.out.println("");
            System.out.println("");

            InputStream is3 = new FileInputStream(testFile);
            CharStream input3 = CharStreams.fromStream(is3);
            CLexer lexer3 = new CLexer(input3);
            CommonTokenStream tokens3 = new CommonTokenStream(lexer3);
            CParser parser3 = new CParser(tokens3);
            ParserRuleContext ctx3 = parser3.abstractDeclarator();
            System.out.println("=========== parser.abstractDeclarator() ===========");
            printAST(ctx3, false, 0);
            System.out.println("=========== ******************** ===========");
            System.out.println("");
            System.out.println("");

            InputStream is4 = new FileInputStream(testFile);
            CharStream input4 = CharStreams.fromStream(is4);
            CLexer lexer4 = new CLexer(input4);
            CommonTokenStream tokens4 = new CommonTokenStream(lexer4);
            CParser parser4 = new CParser(tokens4);
            ParserRuleContext ctx4 = parser4.additiveExpression();
            System.out.println("=========== parser.additiveExpression() ===========");
            printAST(ctx4, false, 0);
            System.out.println("=========== ******************** ===========");
            System.out.println("");
            System.out.println("");

            InputStream is5 = new FileInputStream(testFile);
            CharStream input5 = CharStreams.fromStream(is5);
            CLexer lexer5 = new CLexer(input5);
            CommonTokenStream tokens5 = new CommonTokenStream(lexer5);
            CParser parser5 = new CParser(tokens5);
            ParserRuleContext ctx5 = parser5.alignmentSpecifier();
            System.out.println("=========== parser.alignmentSpecifier() ===========");
            printAST(ctx5, false, 0);
            System.out.println("=========== ******************** ===========");
            System.out.println("");
            System.out.println("");

            InputStream is6 = new FileInputStream(testFile);
            CharStream input6 = CharStreams.fromStream(is6);
            CLexer lexer6 = new CLexer(input6);
            CommonTokenStream tokens6 = new CommonTokenStream(lexer6);
            CParser parser6 = new CParser(tokens6);
            ParserRuleContext ctx6 = parser6.andExpression();
            System.out.println("=========== parser.andExpression() ===========");
            printAST(ctx6, false, 0);
            System.out.println("=========== ******************** ===========");
            System.out.println("");
            System.out.println("");

            InputStream is7 = new FileInputStream(testFile);
            CharStream input7 = CharStreams.fromStream(is7);
            CLexer lexer7 = new CLexer(input7);
            CommonTokenStream tokens7 = new CommonTokenStream(lexer7);
            CParser parser7 = new CParser(tokens7);
            ParserRuleContext ctx7 = parser7.argumentExpressionList();
            System.out.println("=========== parser.argumentExpressionList() ===========");
            printAST(ctx7, false, 0);
            System.out.println("=========== ******************** ===========");
            System.out.println("");
            System.out.println("");

            InputStream is8 = new FileInputStream(testFile);
            CharStream input8 = CharStreams.fromStream(is8);
            CLexer lexer8 = new CLexer(input8);
            CommonTokenStream tokens8 = new CommonTokenStream(lexer8);
            CParser parser8 = new CParser(tokens8);
            ParserRuleContext ctx8 = parser8.assignmentOperator();
            System.out.println("=========== parser.assignmentOperator() ===========");
            printAST(ctx8, false, 0);
            System.out.println("=========== ******************** ===========");
            System.out.println("");
            System.out.println("");

            InputStream is9 = new FileInputStream(testFile);
            CharStream input9 = CharStreams.fromStream(is9);
            CLexer lexer9 = new CLexer(input9);
            CommonTokenStream tokens9 = new CommonTokenStream(lexer9);
            CParser parser9 = new CParser(tokens9);
            ParserRuleContext ctx9 = parser9.atomicTypeSpecifier();
            System.out.println("=========== parser.atomicTypeSpecifier() ===========");
            printAST(ctx9, false, 0);
            System.out.println("=========== ******************** ===========");
            System.out.println("");
            System.out.println("");

            InputStream is10 = new FileInputStream(testFile);
            CharStream input10 = CharStreams.fromStream(is10);
            CLexer lexer10 = new CLexer(input10);
            CommonTokenStream tokens10 = new CommonTokenStream(lexer10);
            CParser parser10 = new CParser(tokens10);
            ParserRuleContext ctx10 = parser10.blockItem();
            System.out.println("=========== parser.blockItem() ===========");
            printAST(ctx10, false, 0);
            System.out.println("=========== ******************** ===========");
            System.out.println("");
            System.out.println("");

            InputStream is11 = new FileInputStream(testFile);
            CharStream input11 = CharStreams.fromStream(is11);
            CLexer lexer11 = new CLexer(input11);
            CommonTokenStream tokens11 = new CommonTokenStream(lexer11);
            CParser parser11 = new CParser(tokens11);
            ParserRuleContext ctx11 = parser11.blockItemList();
            System.out.println("=========== parser.blockItemList() ===========");
            printAST(ctx11, false, 0);
            System.out.println("=========== ******************** ===========");
            System.out.println("");
            System.out.println("");

            InputStream is12 = new FileInputStream(testFile);
            CharStream input12 = CharStreams.fromStream(is12);
            CLexer lexer12 = new CLexer(input12);
            CommonTokenStream tokens12 = new CommonTokenStream(lexer12);
            CParser parser12 = new CParser(tokens12);
            ParserRuleContext ctx12 = parser12.castExpression();
            System.out.println("=========== parser.castExpression() ===========");
            printAST(ctx12, false, 0);
            System.out.println("=========== ******************** ===========");
            System.out.println("");

            InputStream is13 = new FileInputStream(testFile);
            CharStream input13 = CharStreams.fromStream(is13);
            CLexer lexer13 = new CLexer(input13);
            CommonTokenStream tokens13 = new CommonTokenStream(lexer13);
            CParser parser13 = new CParser(tokens13);
            ParserRuleContext ctx13 = parser13.compilationUnit();
            System.out.println("=========== parser.compilationUnit() ===========");
            printAST(ctx13, false, 0);
            System.out.println("=========== ******************** ===========");
            System.out.println("");

            InputStream is14 = new FileInputStream(testFile);
            CharStream input14 = CharStreams.fromStream(is14);
            CLexer lexer14 = new CLexer(input14);
            CommonTokenStream tokens14 = new CommonTokenStream(lexer14);
            CParser parser14 = new CParser(tokens14);
            ParserRuleContext ctx14 = parser14.compoundStatement();
            System.out.println("=========== parser.compoundStatement() ===========");
            printAST(ctx14, false, 0);
            System.out.println("=========== ******************** ===========");
            System.out.println("");

            InputStream is15 = new FileInputStream(testFile);
            CharStream input15 = CharStreams.fromStream(is15);
            CLexer lexer15 = new CLexer(input15);
            CommonTokenStream tokens15 = new CommonTokenStream(lexer15);
            CParser parser15 = new CParser(tokens15);
            ParserRuleContext ctx15 = parser15.conditionalExpression();
            System.out.println("=========== parser.conditionalExpression() ===========");
            printAST(ctx15, false, 0);
            System.out.println("=========== ******************** ===========");
            System.out.println("");

            InputStream is16 = new FileInputStream(testFile);
            CharStream input16 = CharStreams.fromStream(is16);
            CLexer lexer16 = new CLexer(input16);
            CommonTokenStream tokens16 = new CommonTokenStream(lexer16);
            CParser parser16 = new CParser(tokens16);
            ParserRuleContext ctx16 = parser16.constantExpression();
            System.out.println("=========== parser.constantExpression() ===========");
            printAST(ctx16, false, 0);
            System.out.println("=========== ******************** ===========");
            System.out.println("");

            InputStream is17 = new FileInputStream(testFile);
            CharStream input17 = CharStreams.fromStream(is17);
            CLexer lexer17 = new CLexer(input17);
            CommonTokenStream tokens17 = new CommonTokenStream(lexer17);
            CParser parser17 = new CParser(tokens17);
            ParserRuleContext ctx17 = parser17.declaration();
            System.out.println("=========== parser.declaration() ===========");
            printAST(ctx17, false, 0);
            System.out.println("=========== ******************** ===========");
            System.out.println("");

            InputStream is0 = new FileInputStream(testFile);
            CharStream input0 = CharStreams.fromStream(is0);
            CLexer lexer0 = new CLexer(input0);
            CommonTokenStream tokens0 = new CommonTokenStream(lexer0);
            CParser parser0 = new CParser(tokens0);
            ParserRuleContext ctx0 = parser0.declarationList();
            System.out.println("=========== parser.declarationList() ===========");
            printAST(ctx0, false, 0);
            System.out.println("=========== ******************** ===========");
            System.out.println("");

            InputStream is18 = new FileInputStream(testFile);
            CharStream input18 = CharStreams.fromStream(is18);
            CLexer lexer18 = new CLexer(input18);
            CommonTokenStream tokens18 = new CommonTokenStream(lexer18);
            CParser parser18 = new CParser(tokens18);
            ParserRuleContext ctx18 = parser18.declarationSpecifier();
            System.out.println("=========== parser.declarationSpecifier() ===========");
            printAST(ctx18, false, 0);
            System.out.println("=========== ******************** ===========");
            System.out.println("");

            InputStream is19 = new FileInputStream(testFile);
            CharStream input19 = CharStreams.fromStream(is19);
            CLexer lexer19 = new CLexer(input19);
            CommonTokenStream tokens19 = new CommonTokenStream(lexer19);
            CParser parser19 = new CParser(tokens19);
            ParserRuleContext ctx19 = parser19.declarationSpecifiers();
            System.out.println("=========== parser.declarationSpecifiers() ===========");
            printAST(ctx19, false, 0);
            System.out.println("=========== ******************** ===========");
            System.out.println("");

            InputStream is20 = new FileInputStream(testFile);
            CharStream input20 = CharStreams.fromStream(is20);
            CLexer lexer20 = new CLexer(input20);
            CommonTokenStream tokens20 = new CommonTokenStream(lexer20);
            CParser parser20 = new CParser(tokens20);
            ParserRuleContext ctx20 = parser20.declarator();
            System.out.println("=========== parser.declarator() ===========");
            printAST(ctx20, false, 0);
            System.out.println("=========== ******************** ===========");
            System.out.println("");

            InputStream is21 = new FileInputStream(testFile);
            CharStream input21 = CharStreams.fromStream(is21);
            CLexer lexer21 = new CLexer(input21);
            CommonTokenStream tokens21 = new CommonTokenStream(lexer21);
            CParser parser21 = new CParser(tokens21);
            ParserRuleContext ctx21 = parser21.declarationSpecifiers2();
            System.out.println("=========== parser.declarationSpecifiers2() ===========");
            printAST(ctx21, false, 0);
            System.out.println("=========== ******************** ===========");
            System.out.println("");

            InputStream is22 = new FileInputStream(testFile);
            CharStream input22 = CharStreams.fromStream(is22);
            CLexer lexer22 = new CLexer(input22);
            CommonTokenStream tokens22 = new CommonTokenStream(lexer22);
            CParser parser22 = new CParser(tokens22);
            ParserRuleContext ctx22 = parser22.designation();
            System.out.println("=========== parser.designation() ===========");
            printAST(ctx22, false, 0);
            System.out.println("=========== ******************** ===========");
            System.out.println("");

            InputStream is23 = new FileInputStream(testFile);
            CharStream input23 = CharStreams.fromStream(is23);
            CLexer lexer23 = new CLexer(input23);
            CommonTokenStream tokens23 = new CommonTokenStream(lexer23);
            CParser parser23 = new CParser(tokens23);
            ParserRuleContext ctx23 = parser23.designator();
            System.out.println("=========== parser.designator() ===========");
            printAST(ctx23, false, 0);
            System.out.println("=========== ******************** ===========");
            System.out.println("");

            InputStream is24 = new FileInputStream(testFile);
            CharStream input24 = CharStreams.fromStream(is24);
            CLexer lexer24 = new CLexer(input24);
            CommonTokenStream tokens24 = new CommonTokenStream(lexer24);
            CParser parser24 = new CParser(tokens24);
            ParserRuleContext ctx24 = parser24.designatorList();
            System.out.println("=========== parser.designatorList() ===========");
            printAST(ctx24, false, 0);
            System.out.println("=========== ******************** ===========");
            System.out.println("");

            InputStream is25 = new FileInputStream(testFile);
            CharStream input25 = CharStreams.fromStream(is25);
            CLexer lexer25 = new CLexer(input25);
            CommonTokenStream tokens25 = new CommonTokenStream(lexer25);
            CParser parser25 = new CParser(tokens25);
            ParserRuleContext ctx25 = parser25.directAbstractDeclarator();
            System.out.println("=========== parser.directAbstractDeclarator() ===========");
            printAST(ctx25, false, 0);
            System.out.println("=========== ******************** ===========");
            System.out.println("");

            InputStream is26 = new FileInputStream(testFile);
            CharStream input26 = CharStreams.fromStream(is26);
            CLexer lexer26 = new CLexer(input26);
            CommonTokenStream tokens26 = new CommonTokenStream(lexer26);
            CParser parser26 = new CParser(tokens26);
            ParserRuleContext ctx26 = parser26.directDeclarator();
            System.out.println("=========== parser.directDeclarator() ===========");
            printAST(ctx26, false, 0);
            System.out.println("=========== ******************** ===========");
            System.out.println("");

            InputStream is27 = new FileInputStream(testFile);
            CharStream input27 = CharStreams.fromStream(is27);
            CLexer lexer27 = new CLexer(input27);
            CommonTokenStream tokens27 = new CommonTokenStream(lexer27);
            CParser parser27 = new CParser(tokens27);
            ParserRuleContext ctx27 = parser27.enumerationConstant();
            System.out.println("=========== parser.enumerationConstant() ===========");
            printAST(ctx27, false, 0);
            System.out.println("=========== ******************** ===========");
            System.out.println("");

            InputStream is28 = new FileInputStream(testFile);
            CharStream input28 = CharStreams.fromStream(is28);
            CLexer lexer28 = new CLexer(input28);
            CommonTokenStream tokens28 = new CommonTokenStream(lexer28);
            CParser parser28 = new CParser(tokens28);
            ParserRuleContext ctx28 = parser28.enumerator();
            System.out.println("=========== parser.enumerator() ===========");
            printAST(ctx28, false, 0);
            System.out.println("=========== ******************** ===========");
            System.out.println("");

            InputStream is29 = new FileInputStream(testFile);
            CharStream input29 = CharStreams.fromStream(is29);
            CLexer lexer29 = new CLexer(input29);
            CommonTokenStream tokens29 = new CommonTokenStream(lexer29);
            CParser parser29 = new CParser(tokens29);
            ParserRuleContext ctx29 = parser29.enumeratorList();
            System.out.println("=========== parser.enumeratorList() ===========");
            printAST(ctx29, false, 0);
            System.out.println("=========== ******************** ===========");
            System.out.println("");

            InputStream is30 = new FileInputStream(testFile);
            CharStream input30 = CharStreams.fromStream(is30);
            CLexer lexer30 = new CLexer(input30);
            CommonTokenStream tokens30 = new CommonTokenStream(lexer30);
            CParser parser30 = new CParser(tokens30);
            ParserRuleContext ctx30 = parser30.enumSpecifier();
            System.out.println("=========== parser.enumSpecifier() ===========");
            printAST(ctx30, false, 0);
            System.out.println("=========== ******************** ===========");
            System.out.println("");

            InputStream is31 = new FileInputStream(testFile);
            CharStream input31 = CharStreams.fromStream(is31);
            CLexer lexer31 = new CLexer(input31);
            CommonTokenStream tokens31 = new CommonTokenStream(lexer31);
            CParser parser31 = new CParser(tokens31);
            ParserRuleContext ctx31 = parser31.equalityExpression();
            System.out.println("=========== parser.equalityExpression() ===========");
            printAST(ctx31, false, 0);
            System.out.println("=========== ******************** ===========");
            System.out.println("");

            InputStream is32 = new FileInputStream(testFile);
            CharStream input32 = CharStreams.fromStream(is32);
            CLexer lexer32 = new CLexer(input32);
            CommonTokenStream tokens32 = new CommonTokenStream(lexer32);
            CParser parser32 = new CParser(tokens32);
            ParserRuleContext ctx32 = parser32.exclusiveOrExpression();
            System.out.println("=========== parser.exclusiveOrExpression() ===========");
            printAST(ctx32, false, 0);
            System.out.println("=========== ******************** ===========");
            System.out.println("");

            InputStream is33 = new FileInputStream(testFile);
            CharStream input33 = CharStreams.fromStream(is33);
            CLexer lexer33 = new CLexer(input33);
            CommonTokenStream tokens33 = new CommonTokenStream(lexer33);
            CParser parser33 = new CParser(tokens33);
            ParserRuleContext ctx33 = parser33.expressionStatement();
            System.out.println("=========== parser.expressionStatement() ===========");
            printAST(ctx33, false, 0);
            System.out.println("=========== ******************** ===========");
            System.out.println("");

            InputStream is34 = new FileInputStream(testFile);
            CharStream input34 = CharStreams.fromStream(is34);
            CLexer lexer34 = new CLexer(input34);
            CommonTokenStream tokens34 = new CommonTokenStream(lexer34);
            CParser parser34 = new CParser(tokens34);
            ParserRuleContext ctx34 = parser34.externalDeclaration();
            System.out.println("=========== parser.externalDeclaration() ===========");
            printAST(ctx34, false, 0);
            System.out.println("=========== ******************** ===========");
            System.out.println("");

            InputStream is35 = new FileInputStream(testFile);
            CharStream input35 = CharStreams.fromStream(is35);
            CLexer lexer35 = new CLexer(input35);
            CommonTokenStream tokens35 = new CommonTokenStream(lexer35);
            CParser parser35 = new CParser(tokens35);
            ParserRuleContext ctx35 = parser35.forCondition();
            System.out.println("=========== parser.forCondition() ===========");
            printAST(ctx35, false, 0);
            System.out.println("=========== ******************** ===========");
            System.out.println("");

            InputStream is36 = new FileInputStream(testFile);
            CharStream input36 = CharStreams.fromStream(is36);
            CLexer lexer36 = new CLexer(input36);
            CommonTokenStream tokens36 = new CommonTokenStream(lexer36);
            CParser parser36 = new CParser(tokens36);
            ParserRuleContext ctx36 = parser36.forDeclaration();
            System.out.println("=========== parser.forDeclaration() ===========");
            printAST(ctx36, false, 0);
            System.out.println("=========== ******************** ===========");
            System.out.println("");

            InputStream is37 = new FileInputStream(testFile);
            CharStream input37 = CharStreams.fromStream(is37);
            CLexer lexer37 = new CLexer(input37);
            CommonTokenStream tokens37 = new CommonTokenStream(lexer37);
            CParser parser37 = new CParser(tokens37);
            ParserRuleContext ctx37 = parser37.forExpression();
            System.out.println("=========== parser.forExpression() ===========");
            printAST(ctx37, false, 0);
            System.out.println("=========== ******************** ===========");
            System.out.println("");

            InputStream is38 = new FileInputStream(testFile);
            CharStream input38 = CharStreams.fromStream(is38);
            CLexer lexer38 = new CLexer(input38);
            CommonTokenStream tokens38 = new CommonTokenStream(lexer38);
            CParser parser38 = new CParser(tokens38);
            ParserRuleContext ctx38 = parser38.functionDefinition();
            System.out.println("=========== parser.functionDefinition() ===========");
            printAST(ctx38, false, 0);
            System.out.println("=========== ******************** ===========");
            System.out.println("");

            InputStream is39 = new FileInputStream(testFile);
            CharStream input39 = CharStreams.fromStream(is39);
            CLexer lexer39 = new CLexer(input39);
            CommonTokenStream tokens39 = new CommonTokenStream(lexer39);
            CParser parser39 = new CParser(tokens39);
            ParserRuleContext ctx39 = parser39.functionSpecifier();
            System.out.println("=========== parser.functionSpecifier() ===========");
            printAST(ctx39, false, 0);
            System.out.println("=========== ******************** ===========");
            System.out.println("");

            InputStream is40 = new FileInputStream(testFile);
            CharStream input40 = CharStreams.fromStream(is40);
            CLexer lexer40 = new CLexer(input40);
            CommonTokenStream tokens40 = new CommonTokenStream(lexer40);
            CParser parser40 = new CParser(tokens40);
            ParserRuleContext ctx40 = parser40.gccAttribute();
            System.out.println("=========== parser.gccAttribute() ===========");
            printAST(ctx40, false, 0);
            System.out.println("=========== ******************** ===========");
            System.out.println("");

            InputStream is41 = new FileInputStream(testFile);
            CharStream input41 = CharStreams.fromStream(is41);
            CLexer lexer41 = new CLexer(input41);
            CommonTokenStream tokens41 = new CommonTokenStream(lexer41);
            CParser parser41 = new CParser(tokens41);
            ParserRuleContext ctx41 = parser41.gccAttributeList();
            System.out.println("=========== parser.gccAttributeList() ===========");
            printAST(ctx41, false, 0);
            System.out.println("=========== ******************** ===========");
            System.out.println("");

//            CParser parser42 = new CParser(tokens);
//            ParserRuleContext ctx42 = parser42.getRuleContext();
//            System.out.println("=========== parser.getRuleContext() ===========");
//            printAST(ctx42, false, 0);
//            System.out.println("=========== ******************** ===========");
            System.out.println("");

            InputStream is43 = new FileInputStream(testFile);
            CharStream input43 = CharStreams.fromStream(is43);
            CLexer lexer43 = new CLexer(input43);
            CommonTokenStream tokens43 = new CommonTokenStream(lexer43);
            CParser parser43 = new CParser(tokens43);
            ParserRuleContext ctx43 = parser43.gccAttributeSpecifier();
            System.out.println("=========== parser.gccAttributeSpecifier() ===========");
            printAST(ctx43, false, 0);
            System.out.println("=========== ******************** ===========");
            System.out.println("");

            InputStream is44 = new FileInputStream(testFile);
            CharStream input44 = CharStreams.fromStream(is44);
            CLexer lexer44 = new CLexer(input44);
            CommonTokenStream tokens44 = new CommonTokenStream(lexer44);
            CParser parser44 = new CParser(tokens44);
            ParserRuleContext ctx44 = parser44.gccDeclaratorExtension();
            System.out.println("=========== parser.gccDeclaratorExtension() ===========");
            printAST(ctx44, false, 0);
            System.out.println("=========== ******************** ===========");
            System.out.println("");

            InputStream is45 = new FileInputStream(testFile);
            CharStream input45 = CharStreams.fromStream(is45);
            CLexer lexer45 = new CLexer(input45);
            CommonTokenStream tokens45 = new CommonTokenStream(lexer45);
            CParser parser45 = new CParser(tokens45);
            ParserRuleContext ctx45 = parser45.genericAssociation();
            System.out.println("=========== parser.genericAssociation() ===========");
            printAST(ctx45, false, 0);
            System.out.println("=========== ******************** ===========");
            System.out.println("");

            InputStream is46 = new FileInputStream(testFile);
            CharStream input46 = CharStreams.fromStream(is46);
            CLexer lexer46 = new CLexer(input46);
            CommonTokenStream tokens46 = new CommonTokenStream(lexer46);
            CParser parser46 = new CParser(tokens46);
            ParserRuleContext ctx46 = parser46.genericAssocList();
            System.out.println("=========== parser.genericAssocList() ===========");
            printAST(ctx46, false, 0);
            System.out.println("=========== ******************** ===========");
            System.out.println("");

            InputStream is47 = new FileInputStream(testFile);
            CharStream input47 = CharStreams.fromStream(is47);
            CLexer lexer47 = new CLexer(input47);
            CommonTokenStream tokens47 = new CommonTokenStream(lexer47);
            CParser parser47 = new CParser(tokens47);
            ParserRuleContext ctx47 = parser47.genericSelection();
            System.out.println("=========== parser.genericSelection() ===========");
            printAST(ctx47, false, 0);
            System.out.println("=========== ******************** ===========");
            System.out.println("");

            InputStream is49 = new FileInputStream(testFile);
            CharStream input49 = CharStreams.fromStream(is49);
            CLexer lexer49 = new CLexer(input49);
            CommonTokenStream tokens49 = new CommonTokenStream(lexer49);
            CParser parser49 = new CParser(tokens49);
            ParserRuleContext ctx49 = parser49.identifierList();
            System.out.println("=========== parser.identifierList() ===========");
            printAST(ctx49, false, 0);
            System.out.println("=========== ******************** ===========");
            System.out.println("");

            InputStream is50 = new FileInputStream(testFile);
            CharStream input50 = CharStreams.fromStream(is50);
            CLexer lexer50 = new CLexer(input50);
            CommonTokenStream tokens50 = new CommonTokenStream(lexer50);
            CParser parser50 = new CParser(tokens50);
            ParserRuleContext ctx50 = parser50.inclusiveOrExpression();
            System.out.println("=========== parser.inclusiveOrExpression() ===========");
            printAST(ctx50, false, 0);
            System.out.println("=========== ******************** ===========");
            System.out.println("");

            InputStream is51 = new FileInputStream(testFile);
            CharStream input51 = CharStreams.fromStream(is51);
            CLexer lexer51 = new CLexer(input51);
            CommonTokenStream tokens51 = new CommonTokenStream(lexer51);
            CParser parser51 = new CParser(tokens51);
            ParserRuleContext ctx51 = parser51.initDeclarator();
            System.out.println("=========== parser.initDeclarator() ===========");
            printAST(ctx51, false, 0);
            System.out.println("=========== ******************** ===========");
            System.out.println("");

            InputStream is52 = new FileInputStream(testFile);
            CharStream input52 = CharStreams.fromStream(is52);
            CLexer lexer52 = new CLexer(input52);
            CommonTokenStream tokens52 = new CommonTokenStream(lexer52);
            CParser parser52 = new CParser(tokens52);
            ParserRuleContext ctx52 = parser52.initializer();
            System.out.println("=========== parser.initializer() ===========");
            printAST(ctx52, false, 0);
            System.out.println("=========== ******************** ===========");
            System.out.println("");

            InputStream is53 = new FileInputStream(testFile);
            CharStream input53 = CharStreams.fromStream(is53);
            CLexer lexer53 = new CLexer(input53);
            CommonTokenStream tokens53 = new CommonTokenStream(lexer53);
            CParser parser53 = new CParser(tokens53);
            ParserRuleContext ctx53 = parser53.initializerList();
            System.out.println("=========== parser.initializerList() ===========");
            printAST(ctx53, false, 0);
            System.out.println("=========== ******************** ===========");
            System.out.println("");

            InputStream is54 = new FileInputStream(testFile);
            CharStream input54 = CharStreams.fromStream(is54);
            CLexer lexer54 = new CLexer(input54);
            CommonTokenStream tokens54 = new CommonTokenStream(lexer54);
            CParser parser54 = new CParser(tokens54);
            ParserRuleContext ctx54 = parser54.iterationStatement();
            System.out.println("=========== parser.iterationStatement() ===========");
            printAST(ctx54, false, 0);
            System.out.println("=========== ******************** ===========");
            System.out.println("");

            InputStream is55 = new FileInputStream(testFile);
            CharStream input55 = CharStreams.fromStream(is55);
            CLexer lexer55 = new CLexer(input55);
            CommonTokenStream tokens55 = new CommonTokenStream(lexer55);
            CParser parser55 = new CParser(tokens55);
            ParserRuleContext ctx55 = parser55.jumpStatement();
            System.out.println("=========== parser.jumpStatement() ===========");
            printAST(ctx55, false, 0);
            System.out.println("=========== ******************** ===========");
            System.out.println("");

            InputStream is56 = new FileInputStream(testFile);
            CharStream input56 = CharStreams.fromStream(is56);
            CLexer lexer56 = new CLexer(input56);
            CommonTokenStream tokens56 = new CommonTokenStream(lexer56);
            CParser parser56 = new CParser(tokens56);
            ParserRuleContext ctx56 = parser56.labeledStatement();
            System.out.println("=========== parser.labeledStatement() ===========");
            printAST(ctx56, false, 0);
            System.out.println("=========== ******************** ===========");
            System.out.println("");

            InputStream is57 = new FileInputStream(testFile);
            CharStream input57 = CharStreams.fromStream(is57);
            CLexer lexer57 = new CLexer(input57);
            CommonTokenStream tokens57 = new CommonTokenStream(lexer57);
            CParser parser57 = new CParser(tokens57);
            ParserRuleContext ctx57 = parser57.logicalAndExpression();
            System.out.println("=========== parser.logicalAndExpression() ===========");
            printAST(ctx57, false, 0);
            System.out.println("=========== ******************** ===========");
            System.out.println("");

            InputStream is58 = new FileInputStream(testFile);
            CharStream input58 = CharStreams.fromStream(is58);
            CLexer lexer58 = new CLexer(input58);
            CommonTokenStream tokens58 = new CommonTokenStream(lexer58);
            CParser parser58 = new CParser(tokens58);
            ParserRuleContext ctx58 = parser58.logicalOrExpression();
            System.out.println("=========== parser.logicalOrExpression() ===========");
            printAST(ctx58, false, 0);
            System.out.println("=========== ******************** ===========");
            System.out.println("");

            InputStream is59 = new FileInputStream(testFile);
            CharStream input59 = CharStreams.fromStream(is59);
            CLexer lexer59 = new CLexer(input59);
            CommonTokenStream tokens59 = new CommonTokenStream(lexer59);
            CParser parser59 = new CParser(tokens59);
            ParserRuleContext ctx59 = parser59.multiplicativeExpression();
            System.out.println("=========== parser.multiplicativeExpression() ===========");
            printAST(ctx59, false, 0);
            System.out.println("=========== ******************** ===========");
            System.out.println("");

            InputStream is60 = new FileInputStream(testFile);
            CharStream input60 = CharStreams.fromStream(is60);
            CLexer lexer60 = new CLexer(input60);
            CommonTokenStream tokens60 = new CommonTokenStream(lexer60);
            CParser parser60 = new CParser(tokens60);
            ParserRuleContext ctx60 = parser60.nestedParenthesesBlock();
            System.out.println("=========== parser.nestedParenthesesBlock() ===========");
            printAST(ctx60, false, 0);
            System.out.println("=========== ******************** ===========");
            System.out.println("");

            InputStream is61 = new FileInputStream(testFile);
            CharStream input61 = CharStreams.fromStream(is61);
            CLexer lexer61 = new CLexer(input61);
            CommonTokenStream tokens61 = new CommonTokenStream(lexer61);
            CParser parser61 = new CParser(tokens61);
            ParserRuleContext ctx61 = parser61.parameterDeclaration();
            System.out.println("=========== parser.parameterDeclaration() ===========");
            printAST(ctx61, false, 0);
            System.out.println("=========== ******************** ===========");
            System.out.println("");

            InputStream is62 = new FileInputStream(testFile);
            CharStream input62 = CharStreams.fromStream(is62);
            CLexer lexer62 = new CLexer(input62);
            CommonTokenStream tokens62 = new CommonTokenStream(lexer62);
            CParser parser62 = new CParser(tokens62);
            ParserRuleContext ctx62 = parser62.parameterList();
            System.out.println("=========== parser.parameterList() ===========");
            printAST(ctx62, false, 0);
            System.out.println("=========== ******************** ===========");
            System.out.println("");

            InputStream is63 = new FileInputStream(testFile);
            CharStream input63 = CharStreams.fromStream(is63);
            CLexer lexer63 = new CLexer(input63);
            CommonTokenStream tokens63 = new CommonTokenStream(lexer63);
            CParser parser63 = new CParser(tokens63);
            ParserRuleContext ctx63 = parser63.pointer();
            System.out.println("=========== parser.pointer() ===========");
            printAST(ctx63, false, 0);
            System.out.println("=========== ******************** ===========");
            System.out.println("");

            InputStream is64 = new FileInputStream(testFile);
            CharStream input64 = CharStreams.fromStream(is64);
            CLexer lexer64 = new CLexer(input64);
            CommonTokenStream tokens64 = new CommonTokenStream(lexer64);
            CParser parser64 = new CParser(tokens64);
            ParserRuleContext ctx64 = parser64.postfixExpression();
            System.out.println("=========== parser.postfixExpression() ===========");
            printAST(ctx64, false, 0);
            System.out.println("=========== ******************** ===========");
            System.out.println("");

            InputStream is65 = new FileInputStream(testFile);
            CharStream input65 = CharStreams.fromStream(is65);
            CLexer lexer65 = new CLexer(input65);
            CommonTokenStream tokens65 = new CommonTokenStream(lexer65);
            CParser parser65 = new CParser(tokens65);
            ParserRuleContext ctx65 = parser65.parameterTypeList();
            System.out.println("=========== parser.parameterTypeList() ===========");
            printAST(ctx65, false, 0);
            System.out.println("=========== ******************** ===========");
            System.out.println("");

            InputStream is66 = new FileInputStream(testFile);
            CharStream input66 = CharStreams.fromStream(is66);
            CLexer lexer66 = new CLexer(input66);
            CommonTokenStream tokens66 = new CommonTokenStream(lexer66);
            CParser parser66 = new CParser(tokens66);
            ParserRuleContext ctx66 = parser66.primaryExpression();
            System.out.println("=========== parser.primaryExpression() ===========");
            printAST(ctx66, false, 0);
            System.out.println("=========== ******************** ===========");
            System.out.println("");

            InputStream is67 = new FileInputStream(testFile);
            CharStream input67 = CharStreams.fromStream(is67);
            CLexer lexer67 = new CLexer(input67);
            CommonTokenStream tokens67 = new CommonTokenStream(lexer67);
            CParser parser67 = new CParser(tokens67);
            ParserRuleContext ctx67 = parser67.relationalExpression();
            System.out.println("=========== parser.relationalExpression() ===========");
            printAST(ctx67, false, 0);
            System.out.println("=========== ******************** ===========");
            System.out.println("");

            InputStream is68 = new FileInputStream(testFile);
            CharStream input68 = CharStreams.fromStream(is68);
            CLexer lexer68 = new CLexer(input68);
            CommonTokenStream tokens68 = new CommonTokenStream(lexer68);
            CParser parser68 = new CParser(tokens68);
            ParserRuleContext ctx68 = parser68.selectionStatement();
            System.out.println("=========== parser.selectionStatement() ===========");
            printAST(ctx68, false, 0);
            System.out.println("=========== ******************** ===========");
            System.out.println("");

            InputStream is69 = new FileInputStream(testFile);
            CharStream input69 = CharStreams.fromStream(is69);
            CLexer lexer69 = new CLexer(input69);
            CommonTokenStream tokens69 = new CommonTokenStream(lexer69);
            CParser parser69 = new CParser(tokens69);
            ParserRuleContext ctx69 = parser69.shiftExpression();
            System.out.println("=========== parser.shiftExpression() ===========");
            printAST(ctx69, false, 0);
            System.out.println("=========== ******************** ===========");
            System.out.println("");

            InputStream is70 = new FileInputStream(testFile);
            CharStream input70 = CharStreams.fromStream(is70);
            CLexer lexer70 = new CLexer(input70);
            CommonTokenStream tokens70 = new CommonTokenStream(lexer70);
            CParser parser70 = new CParser(tokens70);
            ParserRuleContext ctx70 = parser70.statement();
            System.out.println("=========== parser.statement() ===========");
            printAST(ctx70, false, 0);
            System.out.println("=========== ******************** ===========");
            System.out.println("");

            InputStream is71 = new FileInputStream(testFile);
            CharStream input71 = CharStreams.fromStream(is71);
            CLexer lexer71 = new CLexer(input71);
            CommonTokenStream tokens71 = new CommonTokenStream(lexer71);
            CParser parser71 = new CParser(tokens71);
            ParserRuleContext ctx71 = parser71.specifierQualifierList();
            System.out.println("=========== parser.specifierQualifierList() ===========");
            printAST(ctx71, false, 0);
            System.out.println("=========== ******************** ===========");
            System.out.println("");

            InputStream is72 = new FileInputStream(testFile);
            CharStream input72 = CharStreams.fromStream(is72);
            CLexer lexer72 = new CLexer(input72);
            CommonTokenStream tokens72 = new CommonTokenStream(lexer72);
            CParser parser72 = new CParser(tokens72);
            ParserRuleContext ctx72 = parser72.staticAssertDeclaration();
            System.out.println("=========== parser.staticAssertDeclaration() ===========");
            printAST(ctx72, false, 0);
            System.out.println("=========== ******************** ===========");
            System.out.println("");

            InputStream is73 = new FileInputStream(testFile);
            CharStream input73 = CharStreams.fromStream(is73);
            CLexer lexer73 = new CLexer(input73);
            CommonTokenStream tokens73 = new CommonTokenStream(lexer73);
            CParser parser73 = new CParser(tokens73);
            ParserRuleContext ctx73 = parser73.storageClassSpecifier();
            System.out.println("=========== parser.storageClassSpecifier() ===========");
            printAST(ctx73, false, 0);
            System.out.println("=========== ******************** ===========");
            System.out.println("");

            InputStream is74 = new FileInputStream(testFile);
            CharStream input74 = CharStreams.fromStream(is74);
            CLexer lexer74 = new CLexer(input74);
            CommonTokenStream tokens74 = new CommonTokenStream(lexer74);
            CParser parser74 = new CParser(tokens74);
            ParserRuleContext ctx74 = parser74.structDeclaration();
            System.out.println("=========== parser.structDeclaration() ===========");
            printAST(ctx74, false, 0);
            System.out.println("=========== ******************** ===========");
            System.out.println("");

            InputStream is75 = new FileInputStream(testFile);
            CharStream input75 = CharStreams.fromStream(is75);
            CLexer lexer75 = new CLexer(input75);
            CommonTokenStream tokens75 = new CommonTokenStream(lexer75);
            CParser parser75 = new CParser(tokens75);
            ParserRuleContext ctx75 = parser75.structDeclarationList();
            System.out.println("=========== parser.structDeclarationList() ===========");
            printAST(ctx75, false, 0);
            System.out.println("=========== ******************** ===========");
            System.out.println("");

            InputStream is76 = new FileInputStream(testFile);
            CharStream input76 = CharStreams.fromStream(is76);
            CLexer lexer76 = new CLexer(input76);
            CommonTokenStream tokens76 = new CommonTokenStream(lexer76);
            CParser parser76 = new CParser(tokens76);
            ParserRuleContext ctx76 = parser76.structDeclarator();
            System.out.println("=========== parser.structDeclarator() ===========");
            printAST(ctx76, false, 0);
            System.out.println("=========== ******************** ===========");
            System.out.println("");

            InputStream is77 = new FileInputStream(testFile);
            CharStream input77 = CharStreams.fromStream(is77);
            CLexer lexer77 = new CLexer(input77);
            CommonTokenStream tokens77 = new CommonTokenStream(lexer77);
            CParser parser77 = new CParser(tokens77);
            ParserRuleContext ctx77 = parser77.structDeclaratorList();
            System.out.println("=========== parser.structDeclaratorList() ===========");
            printAST(ctx77, false, 0);
            System.out.println("=========== ******************** ===========");
            System.out.println("");

            InputStream is78 = new FileInputStream(testFile);
            CharStream input78 = CharStreams.fromStream(is78);
            CLexer lexer78 = new CLexer(input78);
            CommonTokenStream tokens78 = new CommonTokenStream(lexer78);
            CParser parser78 = new CParser(tokens78);
            ParserRuleContext ctx78 = parser78.structOrUnion();
            System.out.println("=========== parser.structOrUnion() ===========");
            printAST(ctx78, false, 0);
            System.out.println("=========== ******************** ===========");
            System.out.println("");

            InputStream is79 = new FileInputStream(testFile);
            CharStream input79 = CharStreams.fromStream(is79);
            CLexer lexer79 = new CLexer(input79);
            CommonTokenStream tokens79 = new CommonTokenStream(lexer79);
            CParser parser79 = new CParser(tokens79);
            ParserRuleContext ctx79 = parser79.structOrUnionSpecifier();
            System.out.println("=========== parser.structOrUnionSpecifier() ===========");
            printAST(ctx79, false, 0);
            System.out.println("=========== ******************** ===========");
            System.out.println("");

            InputStream is80 = new FileInputStream(testFile);
            CharStream input80 = CharStreams.fromStream(is80);
            CLexer lexer80 = new CLexer(input80);
            CommonTokenStream tokens80 = new CommonTokenStream(lexer80);
            CParser parser80 = new CParser(tokens80);
            ParserRuleContext ctx80 = parser80.translationUnit();
            System.out.println("=========== parser.translationUnit() ===========");
            printAST(ctx80, false, 0);
            System.out.println("=========== ******************** ===========");
            System.out.println("");

            InputStream is81 = new FileInputStream(testFile);
            CharStream input81 = CharStreams.fromStream(is81);
            CLexer lexer81 = new CLexer(input81);
            CommonTokenStream tokens81 = new CommonTokenStream(lexer81);
            CParser parser81 = new CParser(tokens81);
            ParserRuleContext ctx81 = parser81.typedefName();
            System.out.println("=========== parser.typedefName() ===========");
            printAST(ctx81, false, 0);
            System.out.println("=========== ******************** ===========");
            System.out.println("");

            InputStream is82 = new FileInputStream(testFile);
            CharStream input82 = CharStreams.fromStream(is82);
            CLexer lexer82 = new CLexer(input82);
            CommonTokenStream tokens82 = new CommonTokenStream(lexer82);
            CParser parser82 = new CParser(tokens82);
            ParserRuleContext ctx82 = parser82.typeName();
            System.out.println("=========== parser.typeName() ===========");
            printAST(ctx82, false, 0);
            System.out.println("=========== ******************** ===========");
            System.out.println("");

            InputStream is83 = new FileInputStream(testFile);
            CharStream input83 = CharStreams.fromStream(is83);
            CLexer lexer83 = new CLexer(input83);
            CommonTokenStream tokens83 = new CommonTokenStream(lexer83);
            CParser parser83 = new CParser(tokens83);
            ParserRuleContext ctx83 = parser83.typeQualifier();
            System.out.println("=========== parser.typeQualifier() ===========");
            printAST(ctx83, false, 0);
            System.out.println("=========== ******************** ===========");
            System.out.println("");

            InputStream is84 = new FileInputStream(testFile);
            CharStream input84 = CharStreams.fromStream(is84);
            CLexer lexer84 = new CLexer(input84);
            CommonTokenStream tokens84 = new CommonTokenStream(lexer84);
            CParser parser84 = new CParser(tokens84);
            ParserRuleContext ctx84 = parser84.typeSpecifier();
            System.out.println("=========== parser.typeSpecifier() ===========");
            printAST(ctx84, false, 0);
            System.out.println("=========== ******************** ===========");
            System.out.println("");

            InputStream is85 = new FileInputStream(testFile);
            CharStream input85 = CharStreams.fromStream(is85);
            CLexer lexer85 = new CLexer(input85);
            CommonTokenStream tokens85 = new CommonTokenStream(lexer85);
            CParser parser85 = new CParser(tokens85);
            ParserRuleContext ctx85 = parser85.typeSpecifier();
            System.out.println("=========== parser.typeSpecifier() ===========");
            printAST(ctx85, false, 0);
            System.out.println("=========== ******************** ===========");
            System.out.println("");

            InputStream is86 = new FileInputStream(testFile);
            CharStream input86 = CharStreams.fromStream(is86);
            CLexer lexer86 = new CLexer(input86);
            CommonTokenStream tokens86 = new CommonTokenStream(lexer86);
            CParser parser86 = new CParser(tokens86);
            ParserRuleContext ctx86 = parser86.unaryExpression();
            System.out.println("=========== parser.unaryExpression() ===========");
            printAST(ctx86, false, 0);
            System.out.println("=========== ******************** ===========");
            System.out.println("");

            InputStream is87 = new FileInputStream(testFile);
            CharStream input87 = CharStreams.fromStream(is87);
            CLexer lexer87 = new CLexer(input87);
            CommonTokenStream tokens87 = new CommonTokenStream(lexer87);
            CParser parser87 = new CParser(tokens87);
            ParserRuleContext ctx87 = parser87.unaryOperator();
            System.out.println("=========== parser.unaryOperator() ===========");
            printAST(ctx87, false, 0);
            System.out.println("=========== ******************** ===========");
            System.out.println("");

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
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

            if ("struct".equals(ctx.getText())) {
                System.out.println(ruleName + " -> " + ctx.getText());
            } else {
                System.out.println(ruleName + " -> " + ctx.getText());
            }

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
