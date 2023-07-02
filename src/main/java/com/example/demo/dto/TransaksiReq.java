package com.example.demo.dto;

import java.util.Date;

import com.example.demo.model.Transaksi;
import com.fasterxml.jackson.annotation.JsonProperty;

public class TransaksiReq {

    @JsonProperty("noTrans")
    private String noTrans;
    @JsonProperty("amount")
    private String amount;
    @JsonProperty("action")
    private String action;
    @JsonProperty("createdAt")
    private Date createdAt;
    @JsonProperty("anggotaReq")
    private AnggotaReq anggotaReq;
    
    public TransaksiReq(){}
    
    public TransaksiReq(Transaksi transaksi) {
        this.noTrans = transaksi.getNoTrans();
        this.amount = String.valueOf(transaksi.getAmount());
        this.action = transaksi.getAction();
    }

    public String getNoTrans() {
        return this.noTrans;
    }

    public void setNoTrans(String noTrans) {
        this.noTrans = noTrans;
    }

    public String getAmount() {
        return this.amount;
    }

    public void setAmount(String amount) {
        this.amount = amount;
    }

    public String getAction() {
        return this.action;
    }

    public void setAction(String action) {
        this.action = action;
    }

    public Date getCreatedAt() {
        return this.createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    public AnggotaReq getAnggotaReq() {
        return this.anggotaReq;
    }

    public void setAnggotaReq(AnggotaReq anggotaReq) {
        this.anggotaReq = anggotaReq;
    }

}
