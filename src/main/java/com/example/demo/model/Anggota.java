package com.example.demo.model;

import java.io.Serializable;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.example.demo.dto.AnggotaReq;

@Entity
@Table(name = "anggota")
public class Anggota implements Serializable{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(columnDefinition = "serial")
    private long id;
    @Column(name = "nama_anggota", columnDefinition = "VARCHAR(50)")
    private String namaAnggota;
    @Column(name = "nik", columnDefinition = "VARCHAR(17)")
    private String nik;
    @Column(name = "tempat_lahir", columnDefinition = "VARCHAR(17)")
    private String tempatLahir;
    @Column(name = "tanggal_lahir", columnDefinition = "DATE")
    private Date tanggalLahir;
    @Column(name = "alamat", columnDefinition = "VARCHAR(200)")
    private String alamat;
    @Column(name = "created_at", columnDefinition = "DATE")
    private Date createdAt;
    @Column(name = "status", columnDefinition = "INT")
    private int status;

    public Anggota(){}
    
    public Anggota(AnggotaReq anggotaReq){
        this.namaAnggota = anggotaReq.getNamaAnggota();
        this.nik = anggotaReq.getNik();
        this.tempatLahir = anggotaReq.getTempatLahir();
        this.tanggalLahir = this.parsingDate(anggotaReq.getTanggalLahir());
        this.alamat = anggotaReq.getAlamat();
        this.createdAt = new Date();
        this.status = 1;
    }

    public long getId() {
        return this.id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getNamaAnggota() {
        return this.namaAnggota;
    }

    public void setNamaAnggota(String namaAnggota) {
        this.namaAnggota = namaAnggota;
    }

    public String getNik() {
        return this.nik;
    }

    public void setNik(String nik) {
        this.nik = nik;
    }

    public String getTempatLahir() {
        return this.tempatLahir;
    }

    public void setTempatLahir(String tempatLahir) {
        this.tempatLahir = tempatLahir;
    }

    public Date getTanggalLahir() {
        return this.tanggalLahir;
    }

    public void setTanggalLahir(Date tanggalLahir) {
        this.tanggalLahir = tanggalLahir;
    }

    public String getAlamat() {
        return this.alamat;
    }

    public void setAlamat(String alamat) {
        this.alamat = alamat;
    }

    public Date getCreatedAt() {
        return this.createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    public int getStatus() {
        return this.status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    private Date parsingDate(String dateString){
        Date tanggalLahir = new Date();
        try {
            tanggalLahir = new SimpleDateFormat("dd/MM/yyyy").parse(dateString);
        } catch (ParseException e) {
            tanggalLahir = null;
        }
        return tanggalLahir;
    }

}
