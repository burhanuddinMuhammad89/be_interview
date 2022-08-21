package com.example.demo.model;

import java.math.BigDecimal;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import com.example.demo.dto.TransaksiReq;

@Entity
@Table(name = "transaksi")
public class Transaksi {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(columnDefinition = "serial")
    private long id;
    @Column(name = "no_transaksi", columnDefinition = "VARCHAR(20)")
    private String noTrans;
    @Column(name = "amount", columnDefinition = "int")
    private BigDecimal amount;
    @Column(name = "action", columnDefinition = "varchar(10)")
    private String action;
    @Column(name = "createdAt", columnDefinition = "TIMESTAMP")
    private Date createdAt;
    @Column(name = "nik_anggota", columnDefinition = "varchar(17)")
    private String nikAnggota;

    public Transaksi(){}

    public Transaksi(TransaksiReq transaksiReq){
        this.noTrans = transaksiReq.getNoTrans();
        this.amount = new BigDecimal(transaksiReq.getAmount()).abs();
        this.action = transaksiReq.getAction();
        this.createdAt = new Date();
        this.nikAnggota = transaksiReq.getAnggotaReq().getNik();
    }
    
    public long getId() {
        return this.id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getNoTrans() {
        return this.noTrans;
    }

    public void setNoTrans(String noTrans) {
        this.noTrans = noTrans;
    }

    public BigDecimal getAmount() {
        return this.amount;
    }

    public void setAmount(BigDecimal amount) {
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

    public String getNikAnggota() {
        return this.nikAnggota;
    }

    public void setNikAnggota(String nikAnggota) {
        this.nikAnggota = nikAnggota;
    }

}
