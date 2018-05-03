package org.yee.learning.springboot.filehanding.single.forkjoin;


import org.yee.learning.springboot.filehanding.Master;
import org.yee.learning.springboot.filehanding.Util;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.ForkJoinTask;

/**
 * java编程实现：
 * CSV文件有8GB，里面有1亿条数据，每行数据最长不超过1KB，
 * 目前需要将这1亿条数据拆分为10MB一个的CSV写入到同目录下，
 * 要求每一个CSV的数据必须是完整行，所有文件不能大于10MB。
 * <p>
 * 服务器配置：4核CPU、10GB物理内存，请给出虚拟机的大致内存配置
 *
 * @author daoqidelv
 * @CreateDate 2017年5月4日
 */
public class ForkJoinPoolMaster extends Master {

    private ForkJoinPool forkJoinPool;

    public ForkJoinPoolMaster(String fileDir, String fileName, int subFileSizeLimit) {
        super(fileDir, fileName, subFileSizeLimit);
        this.forkJoinPool = new ForkJoinPool();
    }

    @Override
    public void execute() {
        System.out.println("begin to split...");
        long startTime = System.currentTimeMillis();
        String fullFileName = Util.genFullFileName(this.getFileDir(), this.getFileName());
        ForkFileReadTask forkFileReadTask = new ForkFileReadTask(fullFileName, this.getFileSplitter());
        ForkJoinTask<Boolean> result = this.forkJoinPool.submit(forkFileReadTask);

        boolean res = false;
        try {
            res = result.get();
        } catch (InterruptedException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (ExecutionException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }


        //检查源文件大小和最终写入文件大小和是否相等。
        if (res) {
            System.out.println("文件拆分成功！");
        } else {
            System.out.println("文件拆分失败!");
        }

        long endTime = System.currentTimeMillis();
        System.out.println("总耗时（ms）：" + (endTime - startTime));

    }

}
