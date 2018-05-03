package org.yee.learning.springboot.filehanding.single.forkjoin;


import org.yee.learning.springboot.filehanding.FileSplitter;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.RecursiveTask;

/**
 * ForkJoinTask
 *
 * @author daoqidelv
 * @CreateDate 2017年5月4日
 */
public class ForkFileReadTask extends RecursiveTask<Boolean> {

    private static final long serialVersionUID = 1L;

    /**
     * 带读取文件的全路径
     */
    private String fullFileName;

    /**
     * 文件切割器
     */
    private FileSplitter fileSplitter;

    public ForkFileReadTask(String fullFileName, FileSplitter fileSplitter) {
        this.fullFileName = fullFileName;
        this.fileSplitter = fileSplitter;
    }

    /**
     * 使用fork/join模式完成文件读/写的并行操作。
     *
     * @return true —— 操作成功
     * false —— 操作失败
     */
    @Override
    protected Boolean compute() {
        File file = new File(fullFileName);
        BufferedReader reader = null;
        List<ForkFileWriteTask> tasks = new ArrayList<ForkFileWriteTask>();

        //读取的源文件大小
        int sumReadedSize = 0;
        try {
            reader = new BufferedReader(new FileReader(file));
            String lineContent = "";
            while (reader.ready() && (lineContent = reader.readLine()) != null) {
//				if(ForkFileReadTask.getQueuedTaskCount() > 10 || ForkFileReadTask.getPool().getActiveThreadCount() > 100) {
//					try {
//						Thread.sleep(10);
//					} catch (InterruptedException e) {
//						e.printStackTrace();
//					}
//				}
                ForkFileWriteTask fileWriteTask = this.fileSplitter.spiltForFork(lineContent);//将任务提交pool处理
                if (fileWriteTask != null) {
                    tasks.add(fileWriteTask);
                    fileWriteTask.fork();
                    sumReadedSize += fileWriteTask.getFileSize();
                }
            }
        } catch (FileNotFoundException e) {
            System.err.println("读取源文件错误！");
            e.printStackTrace();
        } catch (IOException e) {
            System.err.println("读取源文件错误！");
            e.printStackTrace();
        } finally {
            if (reader != null) {
                try {
                    reader.close();
                } catch (IOException e) {
                    System.err.println("关闭源文件流错误！");
                    e.printStackTrace();
                }
            }
        }

        //实际写入的所有子文件大小之和
        int totalWrittenSize = 0;
        for (ForkFileWriteTask task : tasks) {
            totalWrittenSize += task.join();
        }

        System.out.println("源文件大小为：" + sumReadedSize + ",实际写入文件大小为：" + totalWrittenSize);
        if (sumReadedSize == totalWrittenSize) {
            return true;
        } else {
            return false;
        }
    }


}
