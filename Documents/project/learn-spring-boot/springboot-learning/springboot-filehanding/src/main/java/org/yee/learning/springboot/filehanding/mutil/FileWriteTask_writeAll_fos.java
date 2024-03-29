package org.yee.learning.springboot.filehanding.mutil;

import com.daoqidlv.filespilt.FileSplitter;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * 文件写入任务。
 * 将文件内容写入到子文件中，写入时完成子文件自动生成和切换。
 * <p>
 * 子文件一次性写入，使用FileOutputStream写入
 *
 * @author Administrator
 */
public class FileWriteTask_writeAll_fos extends Thread {
    /**
     * 任务序号
     */
    private int taskSeq;
    /**
     * 任务名称
     */
    private String taskName;
    /**
     * 文件切割器
     */
    private FileSplitter fileSplitter;
    /**
     * 实际写入的数据总大小
     */
    private int writenSize;
    /**
     * 用于交换子文件内容的阻塞队列
     */
    private BlockingQueue<FileLine> queue;
    /**
     * 子文件 数量计数器
     */
    private static AtomicInteger subFileCounter = new AtomicInteger(0);
    /**
     * 处理中文件的缓存内容
     */
    private List<FileLine> subFileCache;
    /**
     * 处理中文件的缓存内容的字节数
     */
    private int subFileCacheSize;

    private volatile static boolean isDone = false;

    /**
     * 从queue中读取文件行内容FileLine，写入子文件中，确保子文件大小<10M
     */
    @Override
    public void run() {
        FileLine fileLine = null;

        //这里会多做几次尝试，直到master置位isDone=true之后，再行退出
        while (!isDone) {
            try {
                fileLine = queue.poll(50, TimeUnit.MILLISECONDS);
            } catch (InterruptedException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }

            if (fileLine == null) {
                continue;
            }

            int totalSize = this.subFileCacheSize + fileLine.getLineSize();
            //缓存住的子文件内容已经大于上限
            if (totalSize >= this.fileSplitter.getSubFileSizeLimit()) {
                writeSubFile();
            }
            this.subFileCache.add(fileLine);
            this.subFileCacheSize += fileLine.getLineSize();
        }

        //退出时，将task中剩余的文件缓存写入到磁盘
        writeSubFile();

    }


    /**
     * 集中一次写入整个子文件
     */
    private void writeSubFile() {
        int subFileNo = subFileCounter.getAndIncrement();
        String subFileName = fileSplitter.genSubFileFullName(subFileNo);
        FileOutputStream fos = null;
        try {
            fos = new FileOutputStream(subFileName);
            for (FileLine fileLine : this.subFileCache) {
                fos.write(fileLine.getLineContent());
            }
            fos.flush();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (fos != null) {
                try {
                    fos.close();
                } catch (IOException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
            }
        }

        System.out.println(Thread.currentThread().getName() + " write one sub file to disk, fileName is: " + subFileName);
        this.writenSize += this.subFileCacheSize;

        //reset
        this.subFileCache.clear();
        //下面这行代码会导致线程阻塞 TODO
//		this.subFileCache = null;
        this.subFileCacheSize = 0;
    }

    public FileWriteTask_writeAll_fos(int taskSeq, FileSplitter fileSplitter, BlockingQueue<FileLine> queue) {
        this.taskSeq = taskSeq;
        this.fileSplitter = fileSplitter;
        this.queue = queue;
        this.writenSize = 0;
        this.taskName = "FileWriteTask_" + this.taskSeq;
        this.subFileCache = new ArrayList<FileLine>();
    }


    public int getTaskSeq() {
        return taskSeq;
    }

    public void setTaskSeq(int taskSeq) {
        this.taskSeq = taskSeq;
    }

    public String getTaskName() {
        return taskName;
    }

    public void setTaskName(String taskName) {
        this.taskName = taskName;
    }

    public int getWritenSize() {
        return writenSize;
    }

    public void setWritenSize(int writenSize) {
        this.writenSize = writenSize;
    }

    public static boolean isDone() {
        return isDone;
    }

    public static void setDone(boolean isDone) {
        FileWriteTask_writeAll_fos.isDone = isDone;
    }

    @Override
    public String toString() {
        return "FileWriteTask [taskSeq=" + taskSeq + ", taskName=" + taskName + ", writenSize=" + writenSize
                + ", subFileCacheSize=" + subFileCacheSize + "]";
    }


}
