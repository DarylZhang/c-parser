package com.swinburne.data.preprocessing;

import org.apache.commons.io.FileUtils;

import java.io.File;
import java.util.List;

public class Preprocessing {

    public static void preprocessing(String path, String less20Path, String more20Path) throws Exception {

        List<File> files = Utils.getFiles(path);
        System.out.println("--------All Files Loaded--------");
        for(File file : files){

            List<String> contentLines = FileUtils.readLines(file, "UTF-8");
//            if(file.getName().equals("conf2ael.c_ast_remove_lock_info.c"))
//                System.out.println("--------debug--------" + contentLines.get(0));
            String fileString = FileUtils.readFileToString(file, "UTF-8");

            try {
                if (contentLines.get(0).contentEquals("}") || contentLines.get(0).contentEquals("};")
                        || contentLines.get(0).contentEquals(" */") || contentLines.get(0).contentEquals("*/")
                        || contentLines.get(0).contentEquals("#else") || contentLines.get(0).contentEquals("#endif")) {

                    if (contentLines.get(0).contentEquals("*/")) {
                        fileString = fileString.substring(2, fileString.length());
                    } else if (contentLines.get(0).contentEquals(" */")) {
                        fileString = fileString.substring(3, fileString.length());
                    } else if (contentLines.get(0).contentEquals("}")) {
                        fileString = fileString.replaceFirst("}", "");
                    } else if (contentLines.get(0).contentEquals("#else")) {
                        fileString = fileString.substring(5, fileString.length());
                    } else if (contentLines.get(0).contentEquals("#endif")) {
                        fileString = fileString.substring(6, fileString.length());
                    }else {
                        fileString = fileString.replaceFirst("};", "");
                    }

                    String finalString = fileString.replaceFirst("\n", "").replaceFirst("\r", "");

                    FileUtils.writeStringToFile(file, finalString, "UTF-8");
                }
            } catch (Exception e) {
                e.printStackTrace();
                System.out.println("########## Error file name is " + file.getName() + " ##########");
            }

            File newFile = null;
            if (!contentLines.isEmpty() && contentLines.size() <= 20) {
                newFile = new File(less20Path, file.getName());
            } else {
                newFile = new File(more20Path, file.getName());
            }
            FileUtils.copyFile(file, newFile);
        }
        System.out.println("--------Copy Completed--------");
    }

    public static void main(String[] args) {

        try {
            String path = "/Users/zhangxiaowei/Downloads/data/9_projects/OpenSSL/Non_vulnerable_functions";
            String less20Path = "/Users/zhangxiaowei/Downloads/data/9_projects/OpenSSL/Non_vulnerable_functions_lessthan_20_lines/";
            String more20Path = "/Users/zhangxiaowei/Downloads/data/9_projects/OpenSSL/Non_vulnerable_functions_morethan_20_lines/";

//            String path = "/Users/zhangxiaowei/Downloads/data/9_projects/Asterisk/Non_vulnerable_functions_lessthan_20_lines";
//            String less20Path = null;
//            String more20Path = null;

            preprocessing(path, less20Path, more20Path);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
