package io.basic;

import com.google.common.util.concurrent.ThreadFactoryBuilder;
import java.io.File;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Objects;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * @author qiaoyihan
 * @date 2020/5/30
 */
public class FileHandler {
    private static ThreadFactory threadFactory =
            new ThreadFactoryBuilder().setNameFormat("FileHandler").build();

    private static final ThreadPoolExecutor executor =
            new ThreadPoolExecutor(
                    5,
                    20,
                    100,
                    TimeUnit.MILLISECONDS,
                    new LinkedBlockingQueue<>(1024),
                    threadFactory,
                    new ThreadPoolExecutor.AbortPolicy());
    private String pathName;

    public FileHandler(String name) {
        pathName = name;
    }

    public void processFiles() {
        Path path = Paths.get(pathName);
        File file = path.toFile();

        if (!file.exists()) {
            return;
        }
        process(file, 0);
    }

    private void process(File file, Integer level) {
        if (Objects.isNull(file)) {
            return;
        }
        printFile(file.getName(), level);
        if (!file.isDirectory()) {
            return;
        }
        File[] files = file.listFiles(fileName -> !fileName.isHidden());
        if (Objects.nonNull(files)) {
            for (File f : files) {
                process(f, level + 1);
            }
        }
    }

    private void printFile(String name, Integer level) {
        for (int i = 0; i < level; i++) {
            System.out.print("\t");
        }
        System.out.println(name);
    }
}
