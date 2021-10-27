package com.swinburne.data.preprocessing;

import com.google.common.util.concurrent.ThreadFactoryBuilder;
import com.swinburne.data.preprocessing.ctype.Struct;
import com.swinburne.data.preprocessing.gen.CLexer;
import com.swinburne.data.preprocessing.gen.CParser;
import lombok.extern.java.Log;
import lombok.extern.log4j.Log4j2;
import org.antlr.v4.runtime.*;
import org.apache.commons.io.FileUtils;

import java.io.*;
import java.util.List;
import java.util.Map;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class Compiler {

    private String cmdline = null;
    private String directory = null;

    private static ThreadPoolExecutor executor;

    static {
        ThreadFactory namedThreadFactory = new ThreadFactoryBuilder().setNameFormat("demo-pool-%d").build();
        //根据实际情况创建线程池
        executor = new ThreadPoolExecutor(6, 10, 5, TimeUnit.SECONDS, new LinkedBlockingQueue<Runnable>(1024), namedThreadFactory, new ThreadPoolExecutor.AbortPolicy());
    }

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

                //消费正常日志
                clearStream(pr.getInputStream());
                //消费错误日志
                clearStream(pr.getErrorStream());

                int exitValue = pr.waitFor();

                if (exitValue == 0) {
                    success++;
//                    System.out.println("----------------- Exec successfull ----------------- ");
                    boolean isFileDeleted = FileUtils.deleteQuietly(file);
                    if (!isFileDeleted)
                        System.out.println("Is file deleted: " + isFileDeleted);
                } else {

                    Parser parser = new Parser(false);
                    StringBuffer sb = parser.doParse(file);

                    File fixedFile = new File(this.directory + file.getName() + ".refined.c");
                    FileUtils.copyFile(file, fixedFile);
                    String fixedFileString = FileUtils.readFileToString(fixedFile, "UTF-8");

                    String finalString = sb.toString() + fixedFileString;
                    FileUtils.writeStringToFile(fixedFile, finalString, "UTF-8");

                    String[] cmdFixed = {this.cmdline, "-c", this.directory
                            + fixedFile.getName(), "-o", this.directory + file.getName() + ".o"};
                    Process prcessFixed = run.exec(cmdFixed);

                    //消费正常日志
                    clearStream(prcessFixed.getInputStream());
                    //消费错误日志
                    clearStream(prcessFixed.getErrorStream());

                    int exitValueFixed = prcessFixed.waitFor();
                    if (exitValueFixed == 0) {
                        success++;
                        System.out.println("===============############### Exec fixed file successfull ###############===============");
                        boolean isFileDeleted = FileUtils.deleteQuietly(file);
                        boolean isFixedFileDeleted = FileUtils.deleteQuietly(fixedFile);
                        if (!isFileDeleted || !isFixedFileDeleted)
                            System.out.println("Is file deleted: " + isFileDeleted);
                    } else {
                        boolean isFileDeleted = FileUtils.deleteQuietly(file);
                    }
//                    System.out.println(success + " files have been compiled successfully");
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        System.out.println(success + " files have been compiled successfully");
    }

    private static void clearStream(InputStream stream) {
        //处理buffer的线程
        executor.execute(new Runnable() {
            @Override
            public void run() {

                String line = null;

                try (BufferedReader in = new BufferedReader(new InputStreamReader(stream));) {
                    while ((line = in.readLine()) != null) {
//                        Log.debug(line);
                    }
                } catch (IOException e) {
//                    logger.error("exec error : {}", e);
                }
            }
        });
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
            Compiler compiler = new Compiler("mipsel-linux-musl-gcc", "/Users/zhangxiaowei/Downloads/data/9_projects/Asterisk/Non_vulnerable_functions_lessthan_20_lines/");
//            Compiler compiler = new Compiler("gcc", "/Users/zhangxiaowei/Downloads/data/9_projects/FFmpeg/Vulnerable_functions_lessthan_20_lines/");
            compiler.compile();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
