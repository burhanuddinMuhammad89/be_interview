package com.example.demo.service;

import java.util.List;

import com.example.demo.dto.AnggotaReq;

public interface AnggotaService {
    String createAnggota(AnggotaReq anggotaReq);
    List<AnggotaReq> getAllAnggota();
    List<AnggotaReq> getAnggotaByNik(String nik);
    List<AnggotaReq> getAnggotaByName(String name);
}
