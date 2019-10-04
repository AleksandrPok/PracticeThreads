package main;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;

public class Main {
    public static void main(String[] args) {
        File dir = new File("C://Users/"+ System.getProperty("user.name") +"/Desktop/dirForThread");
        File newDir = new File("C://Users/"+ System.getProperty("user.name") +"/Desktop/newDirForThread");
        if (!newDir.exists()){
            newDir.mkdir();
        }

        copyDir(dir, newDir);
    }
    static void copyDir(File oldDirPath, File newDirPath){
        File[] dirContent = oldDirPath.listFiles();

        try {
            for (File f: dirContent) {
                Thread mThread = new Thread(() -> {
                    try {
                        Files.copy(f.toPath(), new File(newDirPath.getAbsolutePath() +"/"+f.getName()).toPath());
                    } catch (IOException e) {
                        System.out.println("Что-то не так с копированием файла");
                    }
                });
                mThread.start();
            }
        } catch (NullPointerException e){
            System.out.println("Папка источник не сущесвует");
        }
    }
}
