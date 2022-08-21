package com.example.demo.dto;

import java.text.SimpleDateFormat;
import com.example.demo.model.Anggota;
import com.fasterxml.jackson.annotation.JsonProperty;

public class AnggotaReq {
    
    @JsonProperty("namaAnggota") 
    private String namaAnggota;
    @JsonProperty("nik") 
    private String nik;
    @JsonProperty("tempatLahir") 
    private String tempatLahir;
    @JsonProperty("tanggalLahir") 
    private String tanggalLahir;
    @JsonProperty("alamat") 
    private String alamat;
    @JsonProperty("createdAt") 
    private String createdAt;
    @JsonProperty("status") 
    private int status;

    public AnggotaReq(){

    }

    public AnggotaReq(Anggota anggota){
        this.namaAnggota = anggota.getNamaAnggota();
        this.nik = anggota.getNik();
        this.tempatLahir = anggota.getTempatLahir();
        this.tanggalLahir = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss").format(anggota.getTanggalLahir());
        this.alamat = anggota.getAlamat();
        this.createdAt = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss").format(anggota.getCreatedAt());
        this.status = anggota.getStatus();
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

    public String getTanggalLahir() {
        return this.tanggalLahir;
    }

    public void setTanggalLahir(String tanggalLahir) {
        this.tanggalLahir = tanggalLahir;
    }

    public String getAlamat() {
        return this.alamat;
    }

    public void setAlamat(String alamat) {
        this.alamat = alamat;
    }

    public String getCreatedAt() {
        return this.createdAt;
    }

    public void setCreatedAt(String createdAt) {
        this.createdAt = createdAt;
    }

    public int getStatus() {
        return this.status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "{" +
            " namaAnggota='" + getNamaAnggota() + "'" +
            ", nik='" + getNik() + "'" +
            ", tempatLahir='" + getTempatLahir() + "'" +
            ", tanggalLahir='" + getTanggalLahir() + "'" +
            ", alamat='" + getAlamat() + "'" +
            ", createdAt='" + getCreatedAt() + "'" +
            ", status='" + getStatus() + "'" +
            "}";
    }

}
