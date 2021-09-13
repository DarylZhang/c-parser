package com.swinburne.data.preprocessing;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class Utils {

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
}
