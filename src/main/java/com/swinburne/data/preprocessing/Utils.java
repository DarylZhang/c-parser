package com.swinburne.data.preprocessing;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class Utils {

    private final static String[] BASIC_VARIABLE_ARRAY = {"int", "short", "long" ,"char", "double", "float"};

    public static List<File> getFiles(String path){
        File root = new File(path);
        List<File> files = new ArrayList<File>();
        if(!root.isDirectory())
        {
            files.add(root);
        }
        else
        {
            File[] subFiles = root.listFiles();
            for(File f : subFiles){
                files.addAll(getFiles(f.getAbsolutePath()));
            }
        }
        return files;
    }

    public static boolean isPointer(String value) {

        if (value.contains("*")) {
           return true;
        }

        return false;
    }

    public static boolean isArray(String value) {
        if (value.contains("[") && value.contains("]")) {
            return true;
        }

        return false;
    }

    public static boolean isBasicVariable(String value) {

        boolean isBasic = false;

        for (String basic : BASIC_VARIABLE_ARRAY) {
            if (value.equals(basic) || value.contains(basic)) {
                isBasic = true;
                return isBasic;
            }
        }

        return isBasic;
    }

    public static String getBasicType(String value) {

        String basicType = null;

        for (String basic : BASIC_VARIABLE_ARRAY) {
            if (value.equals(basic)) {
                basicType = value;
                break;
            } else if (value.contains(basic)) {
                if (value.contains("*")) {
                    basicType = value.substring(value.indexOf(basic), value.length() - 1);
                } else {
                    if (value.indexOf(basic) == 0) {
                        basicType = value.substring(value.indexOf(basic));
                    } else {
                        basicType = value.substring(value.indexOf(basic), value.length());
                    }
                }
                break;
            }
        }

        return basicType;
    }

    public static boolean isStruct(String value) {

        boolean isStruct = false;

        if (value.contains("struct"))
            isStruct = true;

        return isStruct;
    }

    public static String removeArrayBrace(String value) { //a[] or a[10]
        String val = value.substring(0, value.indexOf("["));
        return val;
    }

    public static boolean isString(String value) {

        if (value.contains("\"") || value.contains("'")) {
            return true;
        }

        return false;
    }

    public static boolean isNumber(String value) {

        if (value == null) {
            return false;
        }

        try {
            double d = Double.parseDouble(value);
        } catch (NumberFormatException nfe) {
            return false;
        }

        try {
            float f = Float.parseFloat(value);
        } catch (NumberFormatException nfe) {
            return false;
        }

        try {
            int i = Integer.parseInt(value);
        } catch (NumberFormatException nfe) {
            return false;
        }

        try {
            short s = Short.parseShort(value);
        } catch (NumberFormatException nfe) {
            return false;
        }

        try {
            long l = Long.parseLong(value);
        } catch (NumberFormatException nfe) {
            return false;
        }

        return true;
    }

    public static boolean hasParenthesis(String value) {

        if (value.contains("(") || value.contains(")")) {
            return true;
        }

        return false;
    }

    public static boolean checkHex(String value)
    {
//        // Size of string
//        int n = s.length();
//
//        // Iterate over string
//        for (int i = 0; i < n; i++) {
//
//            char ch = s.charAt(i);
//
//            // Check if the character
//            // is invalid
//            if ((ch < '0' || ch > '9')
//                    && (ch < 'A' || ch > 'F')) {
//
//                return false;
//            }
//        }
        if (value.startsWith("0x"))
            return true;

        return false;
    }

    public static void main(String[] args) {
        String a = "a[]";
        String b = "a[10]";

        System.out.println("==== " + a.substring(0, a.indexOf("[")));
    }
}
