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

                    StringBuffer sb = this.parseCFile(file);
                    if (sb.length() > 10)
                        System.out.println("");

                    File fixedFile = new File(CommonConstant.NON_VULNERABLE_FUNCTIONS_LESSTHAN_20LINES_DIRECTORY + file.getName() + ".fixed.c");
                    FileUtils.copyFile(file, fixedFile);
                    String fixedFileString = FileUtils.readFileToString(fixedFile, "UTF-8");

                    //添加头文件
                    File headerFile = new File(CommonConstant.HEADER_DIRECTORY + "header.h");
                    String headerFileString = FileUtils.readFileToString(headerFile, "UTF-8");

                    String finalString = headerFileString + sb.toString() + fixedFileString;
                    FileUtils.writeStringToFile(fixedFile, finalString, "UTF-8");

                    String[] cmdFixed = {"mipsel-linux-musl-gcc", "-c", CommonConstant.NON_VULNERABLE_FUNCTIONS_LESSTHAN_20LINES_DIRECTORY
                            + fixedFile.getName(), "-o", CommonConstant.NON_VULNERABLE_FUNCTIONS_LESSTHAN_20LINES_DIRECTORY + fixedFile.getName() + ".o"};
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
                    System.out.println(success + " files have been compiled successfully");
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
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
            Compiler compiler = new Compiler("mipsel-linux-musl-gcc", CommonConstant.NON_VULNERABLE_FUNCTIONS_LESSTHAN_20LINES_DIRECTORY);
//            Compiler compiler = new Compiler("gcc", CommonConstant.NON_VULNERABLE_FUNCTIONS_LESSTHAN_20LINES_DIRECTORY);
            compiler.compile();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
