package com.swinburne.data.preprocessing;

import com.swinburne.data.preprocessing.ctype.Record;
import com.swinburne.data.preprocessing.ctype.Struct;
import com.swinburne.data.preprocessing.ctype.StructVariable;
import com.swinburne.data.preprocessing.gen.CParser;
import lombok.Data;
import org.antlr.v4.runtime.ParserRuleContext;
import org.antlr.v4.runtime.RuleContext;
import org.antlr.v4.runtime.tree.ParseTree;

import java.util.*;

@Data
public class StructParser {

    private Map<String, Struct> structMap = new HashMap<>();
    private boolean doParse = false;

    public StructParser(boolean doParse) {
        this.doParse = doParse;
    }

    public Map<String, Struct> parseStruct(RuleContext ctx, boolean verbose, int indentation) throws Exception {
        boolean toBeIgnored = !verbose && ctx.getChildCount() == 1 && ctx.getChild(0) instanceof ParserRuleContext;

        if (!toBeIgnored) {
            String ruleName = CParser.ruleNames[ctx.getRuleIndex()];

            for (int i = 0; i < indentation; i++) {
                System.out.print(" ");
            }

//            System.out.println(ruleName + " -> " + ctx.getText());
        }

        for (int i = 0; i < ctx.getChildCount(); i++) {
            ParseTree element = ctx.getChild(i);

            if (element instanceof RuleContext) {
                parseStructChild((RuleContext) element, verbose, indentation + (toBeIgnored ? 0 : 1));
            }
        }

        return this.structMap;
    }

    public void parseStructChild(RuleContext ctx, boolean verbose, int indentation) throws Exception {
        boolean toBeIgnored = !verbose && ctx.getChildCount() == 1 && ctx.getChild(0) instanceof ParserRuleContext;

        if (!toBeIgnored) {
            String ruleName = CParser.ruleNames[ctx.getRuleIndex()];
            String parentRuleName = CParser.ruleNames[ctx.getParent().getRuleIndex()];

            for (int i = 0; i < indentation; i++) {
                System.out.print(" ");
            }

//            System.out.println(ruleName + " -> " + ctx.getText());

            if (this.doParse) {

                if ("parameterDeclaration".equals(ruleName) && ctx.getText() != null) {
                    if (ctx.getText().contains("struct")) {
                        if (ctx.getText().indexOf("struct") == 0) {
                            boolean found = this.structMap.containsKey(ctx.getText());
                            if (!found)
                                this.structMap.put(ctx.getText(), new Struct(ctx.getText())); //key is "structast_tcptls_session_instance*ser"
                        } else {
                            boolean found = this.structMap.containsKey(ctx.getText().substring(5));
                                if (!found)
                            this.structMap.put(ctx.getText().substring(5), new Struct(ctx.getText())); //key is "structast_tcptls_session_instance*ser"
                        }

                    } else { //处理function中没有struct的结构体
//                        boolean isCType = false;
//                        for (String type : CommonConstant.ALL_CTYPES_ARRAY) {
//                            if (ctx.getText().contains(type)) {
//                                isCType = true;
//                                break;
//                            }
//                        }
//                        if (!isCType) {
//                            this.structMap.put(ctx.getText(), new Struct(ctx.getText()));
//                        }
                    }
                }

//                if (("typeSpecifier".equals(ruleName) && "declarationSpecifier".equals(parentRuleName))
//                        || ("typedefName".equals(ruleName) && "declarationSpecifier".equals(parentRuleName))) {
//                    Iterator<Map.Entry<String, Struct>> it = this.structMap.entrySet().iterator();
//                    while (it.hasNext()) {
//                        Map.Entry<String, Struct> pair = it.next();
//
//                        if (ctx.getText() != null && pair.getKey().contains(ctx.getText())) {
//                            String key = pair.getKey(); //key is "EditLine*el"
//
//                            String structRef = null;
//                            String structName = null;
//                            boolean isPointer = false;
//                            if (key.contains("*")) {
//                                structRef = key.substring(ctx.getText().length());
//                                structName = ctx.getText().substring(0, ctx.getText().indexOf("*"));
//                                isPointer = true;
//                            } else {
//                                structRef = pair.getKey().substring(ctx.getText().length());
//                                structName = ctx.getText();
//                            }
//
//                            Struct struct = new Struct(structName);
//                            struct.setIsPointer(isPointer);
//                            struct.setRef(structRef);
//
//                            this.structMap.remove(key);
//                            if (structRef.isEmpty()) {
//                                this.structMap.put(struct.getName(), struct);
//                            } else {
//                                this.structMap.put(structRef, struct);
//                            }
//
//                        }
//                    }
//                }

                if (("declaration").equals(ruleName) && ctx.getText() != null && ctx.getText().contains("struct")) {

                    String ctxText = ctx.getText();
                    if (ctxText.indexOf("struct") > 0) {
                        if (ctxText.indexOf(",") > 0) {
                            if (ctxText.indexOf("*") > 0) {
                                try {
                                    ctxText = ctxText.substring(ctxText.indexOf("struct"), ctxText.indexOf("*"));
                                } catch (Exception e) {
                                    e.printStackTrace();
                                    return;
                                }
                            } else {
                                ctxText = ctxText.substring(ctxText.indexOf("struct"), ctxText.indexOf(","));
                            }
                        } else {
                            ctxText = ctxText.substring(ctxText.indexOf("struct"));
                        }
                    }

                    if (ctxText.indexOf("=") > 0) {
                        ctxText = ctxText.substring(0, ctxText.indexOf("="));
                    }

                    if (ctxText.indexOf(";") > 0) {
                        ctxText = ctxText.substring(0, ctxText.indexOf(";"));
                    }

                    boolean found = this.structMap.containsKey(ctxText);
                    if (!found)
                        this.structMap.put(ctxText, new Struct(ctxText)); //when value is "conststructcall_queue*q=obj;"
                }

//                if ("structOrUnionSpecifier".equals(ruleName) && ctx.getText() != null && ctx.getText().contains("struct")) {
//                    Iterator<Map.Entry<String, Struct>> it = this.structMap.entrySet().iterator();
//                    while (it.hasNext()) {
//                        Map.Entry<String, Struct> pair = it.next();
//
//                        if (ctx.getText() != null && pair.getKey().contains(ctx.getText())) {
//                            String key = pair.getKey(); //key is "structast_tcptls_session_instance*ser"
//
//                            Struct struct = new Struct(ctx.getText().substring(6));
//                            String structRef = null;
//                            if (key.contains("*")) {
//                                structRef = key.substring(ctx.getText().length() + 1);
//                                struct.setPointer(true);
//                            } else {
//                                structRef = pair.getKey().substring(ctx.getText().length());
//                            }
//                            struct.setRef(structRef);
//
//                            this.structMap.remove(key);
//                            this.structMap.put(structRef, struct);
//                            break;
//                        }
//                    }
//                }

//                if ("declarationSpecifiers".equals(ruleName) && ctx.getText() != null && ctx.getText().contains("struct")) {
//
//                    if (("parameterDeclaration").equals(parentRuleName) || ("declaration").equals(parentRuleName)) {
//
//                        Iterator<Map.Entry<String, Struct>> it = this.structMap.entrySet().iterator();
//                        while (it.hasNext()) {
//                            Map.Entry<String, Struct> pair = it.next();
//                            String ctxText = ctx.getText();
//                            if (ctxText.indexOf("struct") != 0) {
//                                ctxText = ctxText.substring(ctxText.indexOf("struct"));
//                            }
//
//                            if (ctx.getText() != null && pair.getKey().contains(ctxText)) {
//                                String key = pair.getKey(); //key is "structast_format*format"
//                                Struct struct = new Struct(ctx.getText().substring(ctx.getText().indexOf("struct") + 6));
//                                String structRef = null;
//                                if (key.contains("*")) {
//                                    structRef = key.substring(ctxText.length());
//                                    struct.setIsPointer(true);
//                                } else {
//                                    structRef = pair.getKey().substring(ctx.getText().length());
//                                }
//                                struct.setRef(structRef);
//
//                                this.structMap.remove(key);
//                                this.structMap.put(structRef, struct);
//                                break;
//                            }
//                        }
//
//                        return;
//                    }
//                }

                if ("structOrUnionSpecifier".equals(ruleName) && ctx.getText() != null && ctx.getText().contains("struct")) {
                    Iterator<Map.Entry<String, Struct>> it = this.structMap.entrySet().iterator();
                    while (it.hasNext()) {
                        Map.Entry<String, Struct> pair = it.next();

                        if (ctx.getText() != null && pair.getKey().contains(ctx.getText())) {
                            String key = pair.getKey(); //key is "structast_tcptls_session_instance*ser"

                            String structName = ctx.getText().substring(6);
                            Struct struct = new Struct(structName);
                            String structRef = null;
                            if (key.contains("*")) {
                                structRef = key.substring(ctx.getText().length() + 1);
                                struct.setIsPointer(true);
                            } else {
                                structRef = pair.getKey().substring(ctx.getText().length());
                            }
                            struct.setRef(structRef);

                            this.structMap.remove(key);
                            if (structRef.isEmpty()) {
                                boolean found = this.structMap.containsKey(structName);
                                if (!found)
                                    this.structMap.put(structName, struct);
                            } else {
                                boolean found = this.structMap.containsKey(structName);
                                if (!found)
                                    this.structMap.put(structName, struct);
                            }

                            break;
                        }
                    }
                }

                if ("primaryExpression".equals(ruleName) && "postfixExpression".equals(parentRuleName) && ctx.getText() != null) {
                    Iterator<Map.Entry<String, Struct>> it = this.structMap.entrySet().iterator();
                    while (it.hasNext()) {
                        Map.Entry<String, Struct> pair = it.next();
                        String key = pair.getKey();
                        String parentCtxText = ctx.getParent().getText();

                        if (key.equals(ctx.getText()) && !key.equals(parentCtxText)) {

                            if (parentCtxText.contains("->") || parentCtxText.contains(".")) {//处理struct中的变量 e.g. response->fd->test.abc
                                this.buildStructVariable(pair.getValue(), parentCtxText);
                            }
                            break;
                        }

                    }
                }

//                if ("postfixExpression".equals(ruleName) && ctx.getText() != null
//                        && (ctx.getText().contains("->") || ctx.getText().contains("."))) {//处理struct中的变量 e.g. response->fd->test.abc
//                    String[] structChild = new String[2];
//                    if (ctx.getText().contains("->")) {
//                        structChild = ctx.getText().split("->");
//                    } else if (ctx.getText().contains(".")) {
//                        structChild = ctx.getText().split(".");
//                    }
//                }
            }

//            if (this.doParse) {
//                if ("specifierQualifierList".equals(ruleName) && ctx.getText() != null && ctx.getText().substring(0, 6).equals("struct")) {
//                    this.structMap.put(ctx.getText(), new Struct(ctx.getText())); //key is "structast_tcptls_session_instance*ser"
//                }
//
//                if ("structOrUnionSpecifier".equals(ruleName) && ctx.getText() != null && ctx.getText().contains("struct")) {
//                    Iterator<Map.Entry<String, Struct>> it = this.structMap.entrySet().iterator();
//                    while (it.hasNext()) {
//                        Map.Entry<String, Struct> pair = it.next();
//
//                        if (ctx.getText() != null && pair.getKey().contains(ctx.getText())) {
//                            String key = pair.getKey(); //key is "structast_tcptls_session_instance*ser"
//
//                            Struct struct = new Struct(ctx.getText().substring(6));
//                            String structRef = null;
//                            if (key.contains("*")) {
//                                structRef = pair.getKey().substring(ctx.getText().length() + 1);
//                                struct.setPointer(true);
//                            } else {
//                                structRef = pair.getKey().substring(ctx.getText().length());
//                            }
//                            struct.setRef(structRef);
//
//                            this.structMap.remove(key);
//                            this.structMap.put(structRef, struct);
//                            break;
//                        }
//                    }
//                }
//            }
        }

        for (int i = 0; i < ctx.getChildCount(); i++) {
            ParseTree element = ctx.getChild(i);

            if (element instanceof RuleContext) {
                parseStructChild((RuleContext) element, verbose, indentation + (toBeIgnored ? 0 : 1));
            }
        }
    }

    private void buildStructVariable(Struct targetStruct, String targetCtxText) {

        String structRef = targetStruct.getRef();

        Vector<Record> targetStructChildren = null;
        if (targetStruct.getStructChildren() != null) {
            targetStructChildren = targetStruct.getStructChildren();
        } else {
            targetStructChildren = new Vector<>();
        }

        String ctxText = targetCtxText.substring(targetCtxText.indexOf(structRef) + structRef.length());

        List<Integer> symoblLocations = new ArrayList<>();

        for(int i = 0; i < ctxText.length(); i++) {
            char c = ctxText.charAt(i);

            if (c == '>' || c == '.') {
                symoblLocations.add(i);
            }
        }

        Struct parentStruct = null;

        for (int i = 0; i < symoblLocations.size(); i++) {
            Integer symoblLocation = symoblLocations.get(i);
            Integer nextSymoblLocation = i + 1 == symoblLocations.size() ? symoblLocations.get(i) : symoblLocations.get(i + 1);

            char nextSymobl = ctxText.charAt(nextSymoblLocation);

            boolean isArray = false;
            String variable = null;
            if (symoblLocation.intValue() == nextSymoblLocation.intValue()) { //只有一个
                variable = ctxText.substring(symoblLocation + 1);
                String typeName = "int";

                if (variable.contains("name") || variable.contains("message")
                        || variable.contains("address") || variable.contains("header")) {
                    typeName = "char";
                }

                if (variable.indexOf("[") > 0) {
                    variable = variable.substring(0, variable.indexOf("["));
                    isArray = true;
                }
                String finalVariable = variable;
                StructVariable structVariable = new StructVariable(variable, typeName);
                structVariable.setIsArray(isArray);

                if (parentStruct != null) {
                    Vector<Record> structChildren = new Vector<>();
                    structChildren.add(structVariable);

                    if (parentStruct.getStructChildren() == null) {
                        parentStruct.setStructChildren(structChildren);
                    } else {
                        boolean found = parentStruct.getStructChildren().stream().anyMatch(v -> v.getName().equals(finalVariable));
                        if (!found)
                            parentStruct.setStructChildren(structChildren);
                    }
                } else {
                    boolean found = targetStructChildren.stream().anyMatch(v -> v.getName().equals(finalVariable));
                    if (!found)
                        targetStructChildren.add(structVariable);
                }
            } else {
                if (ctxText.charAt(nextSymoblLocation) == '>') {
                    variable = ctxText.substring(symoblLocation + 1, nextSymoblLocation - 1);
                } else {
                    variable = ctxText.substring(symoblLocation + 1, nextSymoblLocation);
                }

                if (variable.indexOf("[") > 0) {
                    variable = variable.substring(0, variable.indexOf("["));
                    isArray = true;
                }
                String finalVariable = variable;

                Struct childStruct = new Struct(variable);
                childStruct.setIsArray(isArray);
                childStruct.setRef(variable);

                if (nextSymobl == '>') {
                    childStruct.setIsPointer(true);
                }

                if (parentStruct != null) {
                    Vector<Record> structChildren = new Vector<>();
                    structChildren.add(childStruct);

                    if (parentStruct.getStructChildren() == null) {
                        parentStruct.setStructChildren(structChildren);
                    } else {
                        boolean found = parentStruct.getStructChildren().stream().anyMatch(v -> v.getName().equals(finalVariable));
                        if (!found)
                            parentStruct.setStructChildren(structChildren);
                    }
                } else {
                    boolean found = targetStructChildren.stream().anyMatch(v -> v.getName().equals(finalVariable));
                    if (!found) {
                        targetStructChildren.add(childStruct);
                    } else {
                        Record existingVariable = targetStructChildren.stream().filter(v -> v.getName().equals(finalVariable)).findFirst().get();

                        if (!existingVariable.getTypename().equals(childStruct.getTypename())) {
                            for (int j = 0; j < targetStructChildren.size(); j++) {
                                if (targetStructChildren.get(j).getName().equals(existingVariable.getName())) {
                                    targetStructChildren.remove(j);
                                    break;
                                }
                            }
                            targetStructChildren.add(childStruct);
                        }
                    }
                }

                parentStruct = childStruct;

            }

            targetStruct.setStructChildren(targetStructChildren);

//            System.out.println("nextSymbol is " + variable);

        }
    }

    private final static String NEW_LINE = "\n\r";
    private final static String BLANK = " ";

    public StringBuffer buildStructStatement(Map<String, Struct> structMap) {

        StringBuffer sb = new StringBuffer();

        Iterator<Map.Entry<String, Struct>> it = this.structMap.entrySet().iterator();
        while (it.hasNext()) {
            Map.Entry<String, Struct> pair = it.next();
            String key = pair.getKey();
            Struct struct = pair.getValue();

            sb = this.buildStruct(struct, sb, 1);
        }

        return sb;
    }

    private StringBuffer buildStruct(Struct struct, StringBuffer sb, int nextLevel) {

        for (int i = 1; i < nextLevel; i++) {
            sb.append("\t");
        }
        sb.append("struct");
        if (nextLevel == 1) {
            sb.append(BLANK).append(struct.getName());
        }
        sb.append(" {").append(NEW_LINE);

        if (struct.getStructChildren() != null) {
            for (Record record : struct.getStructChildren()) {
                if (!record.getTypename().equals("struct")) {
                    for (int i = 1; i < nextLevel + 1; i++) {
                        sb.append("\t");
                    }
                    sb.append(record.getTypename()).append(BLANK).append(record.getName());
                    if (record.getIsArray()) {
                        sb.append("[10]");
                    }
                    sb.append(";").append(NEW_LINE);
                } else {
                    Struct childStruct = (Struct) record;
                    this.buildStruct(childStruct, sb, nextLevel + 1);
                }
            }
        }

        for (int i = 1; i < nextLevel; i++) {
            sb.append("\t");
        }
        sb.append("}");
        if (nextLevel > 1) {
            sb.append(BLANK);

            if (struct.getIsPointer()) {
                sb.append("*");
            }
            sb.append(struct.getRef());

            if (struct.getIsArray()) {
                sb.append("[10]");
            }
        }
        sb.append(";");
        sb.append(NEW_LINE);

        return sb;
    }
}
