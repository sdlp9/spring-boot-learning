package org.yee.learning.springboot.filehanding;

import org.yee.learning.springboot.filehanding.disruptor.DisruptorMaster;
import org.yee.learning.springboot.filehanding.mutil.MutilThreadReadMaster;
import org.yee.learning.springboot.filehanding.single.forkjoin.ForkJoinPoolMaster;
import org.yee.learning.springboot.filehanding.single.normal.NormalPoolMaster;

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
public abstract class Master {

    private String fileDir;

    private String fileName;

    private int subFileSizeLimit;

    private FileSplitter fileSplitter;


    public Master(String fileDir, String fileName, int subFileSizeLimit) {
        this.fileDir = fileDir;
        this.fileName = fileName;
        //TODO 更好的做法：根据上送的单位值进行换算
        this.subFileSizeLimit = subFileSizeLimit * 1024 * 1024;
        this.fileSplitter = new FileSplitter(this.subFileSizeLimit, this.fileDir, this.fileName);
    }

    /**
     * 工厂方法，根据masterType产生master示例
     *
     * @param masterType master类型
     *                   NORMAL —— 返回NormalPoolMaster，使用普通线程池 ThreadPoolExcutor
     *                   FORKJOIN —— 返回使用ForkJoinPoll
     *                   PRODUCERCONSUMER —— PRODUCER/CONSUMER模式实现
     * @return
     */
    public static Master getMasterInstance(
            String masterType,
            String fileDir,
            String fileName,
            int subFileSizeLimit,
            int readTaskNum,
            int writeTaskNum,
            int queueSize,
            int bufferSize) {
        if (masterType.equals(Constants.MASTER_TYPE_NORMAL_POOL)) {
            return new NormalPoolMaster(fileDir, fileName, subFileSizeLimit);
            //默认使用NormalPoolMaster
        } else if (masterType.equals(Constants.MASTER_TYPE_PRODUCER_CONSUMER)) {
            return new MutilThreadReadMaster(fileDir, fileName, subFileSizeLimit, readTaskNum, writeTaskNum, queueSize);
        } else if (masterType.equals(Constants.MASTER_TYPE_DISRUPTOR)) {
            return new DisruptorMaster(fileDir, fileName, subFileSizeLimit, readTaskNum, writeTaskNum, queueSize, bufferSize);
        } else {
            return new ForkJoinPoolMaster(fileDir, fileName, subFileSizeLimit);
        }
    }

    /**
     * 其他初始化操作
     */
    public void init() {

    }

    /**
     * 业务逻辑执行
     */
    public abstract void execute();

    public String getFileDir() {
        return fileDir;
    }

    public void setFileDir(String fileDir) {
        this.fileDir = fileDir;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public int getSubFileSizeLimit() {
        return subFileSizeLimit;
    }

    public void setSubFileSizeLimit(int subFileSizeLimit) {
        this.subFileSizeLimit = subFileSizeLimit;
    }

    public FileSplitter getFileSplitter() {
        return fileSplitter;
    }

    public void setFileSplitter(FileSplitter fileSplitter) {
        this.fileSplitter = fileSplitter;
    }


}
