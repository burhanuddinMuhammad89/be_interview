package com.example.demo.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.dto.AnggotaReq;
import com.example.demo.model.Anggota;
import com.example.demo.repository.AnggotaRepository;
import com.example.demo.service.AnggotaService;

@Service
public class AnggotaServiceImpl implements AnggotaService{

    @Autowired
    AnggotaRepository anggotaRepository;

    Logger logger = Logger.getLogger(AnggotaServiceImpl.class.getName());

    @Override
    public String createAnggota(AnggotaReq anggotaReq) {
        try {
            Anggota anggota = anggotaRepository.findByNik(anggotaReq.getNik());
            if (anggota != null) {
                return "error-anggota sudah ada";
            }

            if (StringUtils.isEmpty(anggotaReq.getNik())) {
                return "error-nik wajib diisi";
            }

            if (anggotaReq.getNamaAnggota().length() < 3) {
                return "error-nama tidak boleh kurang dari atau sama dengan 3 huruf";
            }

            anggota = new Anggota(anggotaReq);

            if (anggota.getTanggalLahir() == null) {
                return "error-format tanggal lahir salah, seharusnya (dd/MM/yyyy)";
            }

            anggotaRepository.save(anggota);
        } catch (Exception e) {
            return "error-" + e.getMessage();
        }
        return "success menambah data";
    }

    @Override
    public List<AnggotaReq> getAllAnggota() {
        List<Anggota> anggotas =  (List<Anggota>) anggotaRepository.findAll();
        List<AnggotaReq> anggotaReqs = new ArrayList<>();
        for(Anggota anggota : anggotas){
            AnggotaReq anggotaReq = new AnggotaReq(anggota);
            anggotaReqs.add(anggotaReq);
        }
        return anggotaReqs;
    }

    @Override
    public List<AnggotaReq> getAnggotaByNik(String nik) {
        List<Anggota> anggotas = (List<Anggota>) anggotaRepository.findByNikContaining(nik);
        List<AnggotaReq> anggotaReqs = new ArrayList<>();
        for(Anggota anggota : anggotas){
            AnggotaReq anggotaReq = new AnggotaReq(anggota);
            anggotaReqs.add(anggotaReq);
        }
        return anggotaReqs;
    }

    @Override
    public List<AnggotaReq> getAnggotaByName(String name) {
        List<Anggota> anggotas = (List<Anggota>) anggotaRepository.findBynamaAnggotaContaining(name);
        List<AnggotaReq> anggotaReqs = new ArrayList<>();
        for(Anggota anggota : anggotas){
            AnggotaReq anggotaReq = new AnggotaReq(anggota);
            anggotaReqs.add(anggotaReq);
        }
        return anggotaReqs;
    }
    
}
