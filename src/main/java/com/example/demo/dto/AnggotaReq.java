package com.example.demo.dto;

import java.text.SimpleDateFormat;
import java.util.Set;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import com.example.demo.model.Anggota;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;

public class AnggotaReq {

    @JsonProperty("namaAnggota")
    private String namaAnggota;
    @JsonProperty("nik")
    private String nik;
    @JsonProperty("tempatLahir")
    @JsonFormat(shape=JsonFormat.Shape.STRING, pattern="yyyy-MM-dd")
    private String tempatLahir;
    @JsonProperty("tanggalLahir")
    private String tanggalLahir;
    @JsonProperty("alamat")
    private String alamat;
    @JsonProperty("createdAt")
    private String createdAt;
    @JsonProperty("status")
    private int status;
    @NotBlank
    @Size(min = 3, max = 20)
    private String username;

    @NotBlank
    @Size(max = 50)
    @Email
    private String email;

    private Set<String> role;

    @NotBlank
    @Size(min = 6, max = 40)
    private String password;

    public AnggotaReq() {

    }

    public AnggotaReq(Anggota anggota) {
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

    public String getUsername() {
        return this.username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return this.email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Set<String> getRole() {
        return this.role;
    }

    public void setRole(Set<String> role) {
        this.role = role;
    }

    public String getPassword() {
        return this.password;
    }

    public void setPassword(String password) {
        this.password = password;
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
