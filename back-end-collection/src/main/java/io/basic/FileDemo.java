package io.basic;

/**
 * @author qiaoyihan
 * @date 2020/5/28
 */
public class FileDemo {
    public static void main(String[] args) {
        FileHandler fileHandler = new FileHandler("/Users/qiaoyihan/IdeaProjects/WordCount");
        fileHandler.processFiles();
    }
}
