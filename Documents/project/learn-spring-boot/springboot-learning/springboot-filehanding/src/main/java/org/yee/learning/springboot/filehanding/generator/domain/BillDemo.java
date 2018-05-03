package org.yee.learning.springboot.filehanding.generator.domain;

import java.io.Serializable;

public class BillDemo implements Serializable {

    private static final long serialVersionUID = -6457699076807865875L;
    private String cooperator;
    private String merchantCode;
    private String smzfMsgId;
    private String reqMsgId;
    private String amount;
    private String settleDate;
    private String respType;
    private String respCode;
    private String respMsg;
    private String transactionType;
    private String oriReqMsgId;
    private String fee;
    private String payWay;
    private String payType;
    private String drawBatchNo;
    private String shfee;
    private String payScene;
    private String storeId;
    private String terminalId;

    public String getCooperator() {
        return cooperator;
    }

    public void setCooperator(String cooperator) {
        this.cooperator = cooperator;
    }

    public String getMerchantCode() {
        return merchantCode;
    }

    public void setMerchantCode(String merchantCode) {
        this.merchantCode = merchantCode;
    }

    public String getSmzfMsgId() {
        return smzfMsgId;
    }

    public void setSmzfMsgId(String smzfMsgId) {
        this.smzfMsgId = smzfMsgId;
    }

    public String getReqMsgId() {
        return reqMsgId;
    }

    public void setReqMsgId(String reqMsgId) {
        this.reqMsgId = reqMsgId;
    }

    public String getAmount() {
        return amount;
    }

    public void setAmount(String amount) {
        this.amount = amount;
    }

    public String getSettleDate() {
        return settleDate;
    }

    public void setSettleDate(String settleDate) {
        this.settleDate = settleDate;
    }

    public String getRespType() {
        return respType;
    }

    public void setRespType(String respType) {
        this.respType = respType;
    }

    public String getRespCode() {
        return respCode;
    }

    public void setRespCode(String respCode) {
        this.respCode = respCode;
    }

    public String getRespMsg() {
        return respMsg;
    }

    public void setRespMsg(String respMsg) {
        this.respMsg = respMsg;
    }

    public String getTransactionType() {
        return transactionType;
    }

    public void setTransactionType(String transactionType) {
        this.transactionType = transactionType;
    }

    public String getOriReqMsgId() {
        return oriReqMsgId;
    }

    public void setOriReqMsgId(String oriReqMsgId) {
        this.oriReqMsgId = oriReqMsgId;
    }

    public String getFee() {
        return fee;
    }

    public void setFee(String fee) {
        this.fee = fee;
    }

    public String getPayWay() {
        return payWay;
    }

    public void setPayWay(String payWay) {
        this.payWay = payWay;
    }

    public String getPayType() {
        return payType;
    }

    public void setPayType(String payType) {
        this.payType = payType;
    }

    public String getDrawBatchNo() {
        return drawBatchNo;
    }

    public void setDrawBatchNo(String drawBatchNo) {
        this.drawBatchNo = drawBatchNo;
    }

    public String getShfee() {
        return shfee;
    }

    public void setShfee(String shfee) {
        this.shfee = shfee;
    }

    public String getPayScene() {
        return payScene;
    }

    public void setPayScene(String payScene) {
        this.payScene = payScene;
    }

    public String getStoreId() {
        return storeId;
    }

    public void setStoreId(String storeId) {
        this.storeId = storeId;
    }

    public String getTerminalId() {
        return terminalId;
    }

    public void setTerminalId(String terminalId) {
        this.terminalId = terminalId;
    }
}