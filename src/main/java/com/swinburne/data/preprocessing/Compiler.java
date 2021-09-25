package com.swinburne.data.preprocessing;

import com.swinburne.data.preprocessing.ctype.Struct;
import com.swinburne.data.preprocessing.gen.CLexer;
import com.swinburne.data.preprocessing.gen.CParser;
import org.antlr.v4.runtime.*;
import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.util.List;
import java.util.Map;

public class Compiler {

    private String cmdline = null;
    private String directory = null;

    public Compiler(String cmdline, String directory) {
        this.cmdline = cmdline;
        this.directory = directory;
    }

    public void compile() {

        List<File> files = Utils.getFiles(this.directory);

        System.out.println("--------All Files Loaded--------");

        int success = 0;
        try {
            for(File file : files) {

                Runtime run = Runtime.getRuntime();

                String[] cmd = {this.cmdline, "-c", this.directory
                        + file.getName(), "-o", this.directory + file.getName() + ".o"};

                Process pr = run.exec(cmd);

                int exitValue = pr.waitFor();

                if (exitValue == 0) {
                    success++;
//                    System.out.println("----------------- Exec successfull ----------------- ");
                    boolean isFileDeleted = FileUtils.deleteQuietly(file);
                    if (!isFileDeleted)
                        System.out.println("Is file deleted: " + isFileDeleted);
                } else {
                    run.exec("clear");

                    Parser parser = new Parser(false);
                    StringBuffer sb = parser.doParse(file);

                    File fixedFile = new File(this.directory + file.getName() + ".fixed.c");
                    FileUtils.copyFile(file, fixedFile);
                    String fixedFileString = FileUtils.readFileToString(fixedFile, "UTF-8");

                    String finalString = sb.toString() + fixedFileString;
                    FileUtils.writeStringToFile(fixedFile, finalString, "UTF-8");

                    String[] cmdFixed = {this.cmdline, "-c", this.directory
                            + fixedFile.getName(), "-o", this.directory + fixedFile.getName() + ".o"};
                    Process prcessFixed = run.exec(cmdFixed);

                    int exitValueFixed = prcessFixed.waitFor();
                    if (exitValueFixed == 0) {
                        success++;
                        System.out.println("===============############### Exec fixed file successfull ###############===============");
                        boolean isFileDeleted = FileUtils.deleteQuietly(file);
                        boolean isFixedFileDeleted = FileUtils.deleteQuietly(fixedFile);
                        if (!isFileDeleted || !isFixedFileDeleted)
                            System.out.println("Is file deleted: " + isFileDeleted);
                    } else {
                            Process clearProcess1 = run.exec("clear");
                            clearProcess1.waitFor();
                    }
//                    System.out.println(success + " files have been compiled successfully");
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        System.out.println(success + " files have been compiled successfully");
    }

    private StringBuffer parseCFile(File file) {

        StringBuffer sb = null;

        try {
            InputStream isForStruct = new FileInputStream(file);
            CharStream inputForStruct = CharStreams.fromStream(isForStruct);
            CLexer lexerForStruct = new CLexer(inputForStruct);
            CommonTokenStream tokensForStruct = new CommonTokenStream(lexerForStruct);
            CParser parserForStruct = new CParser(tokensForStruct);
            ParserRuleContext ctxForStruct = parserForStruct.translationUnit();

            lexerForStruct.removeErrorListener(ConsoleErrorListener.INSTANCE);
            parserForStruct.removeErrorListener(ConsoleErrorListener.INSTANCE);

            StructParser structParser = new StructParser(true);
            Map<String, Struct> structMap = structParser.parseStruct(ctxForStruct, false, 0);
            sb = structParser.buildStructStatement(structMap);


        } catch (Exception e) {
            e.printStackTrace();
        }

        return sb;
    }

    public static void main(String[] args) {

        try {
//            Compiler compiler = new Compiler("mipsel-linux-musl-gcc", CommonConstant.NON_VULNERABLE_FUNCTIONS_LESSTHAN_20LINES_DIRECTORY);
            Compiler compiler = new Compiler("gcc", "/Users/zhangxiaowei/Downloads/data/9_projects/OpenSSL/Non_vulnerable_functions_lessthan_20_lines/");
            compiler.compile();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
