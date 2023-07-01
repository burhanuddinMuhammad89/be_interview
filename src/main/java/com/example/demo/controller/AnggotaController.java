package com.example.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.example.demo.dto.AnggotaReq;
import com.example.demo.dto.TransaksiReq;
import com.example.demo.service.AnggotaService;
import com.example.demo.service.TransaksiService;

@RestController
@RequestMapping("/api/anggota")
public class AnggotaController {

    @Autowired
    private AnggotaService anggotaService;

    @Autowired
    private TransaksiService transaksiService;

    /**
     * @return
     */
    @GetMapping
    public List<AnggotaReq> findAllAnggota() {
        return anggotaService.getAllAnggota();
    }

    @GetMapping("/getNik")
    public List<AnggotaReq> findAnggotaByNik(@Param(value = "nik") String nik) {
        return anggotaService.getAnggotaByNik(nik);
    }

    @GetMapping("/name")
    public List<AnggotaReq> findAnggotaByName(@Param(value = "name") String name) {
        return anggotaService.getAnggotaByName(name);
    }

    @PostMapping
    public ResponseEntity<String> createAnggota(@Validated @RequestBody AnggotaReq anggotaReq) {
        String anggotaReqResult = anggotaService.createAnggota(anggotaReq);
        return anggotaReqResult.contains("error") ? ResponseEntity.badRequest().body(anggotaReqResult)
                : ResponseEntity.ok().body(anggotaReqResult);
    }

    @PutMapping
    public ResponseEntity<String> updateAnggota(@Validated @RequestBody AnggotaReq anggotaReq) {
        String anggotaReqResult = anggotaService.createAnggota(anggotaReq);
        return anggotaReqResult.contains("error") ? ResponseEntity.badRequest().body(anggotaReqResult)
                : ResponseEntity.ok().body(anggotaReqResult);
    }

    @PostMapping("/transaksi")
    public ResponseEntity<String> createTransaksi(@Validated @RequestBody TransaksiReq transaksiReq) {
        String transaksiResult = transaksiService.createTransaction(transaksiReq);
        return transaksiResult.contains("error") ? ResponseEntity.badRequest().body(transaksiResult)
                : ResponseEntity.ok().body(transaksiResult);
    }

    @GetMapping("/transaksi")
    public List<TransaksiReq> findTransaksiByDate(@Param(value = "dateStart") String dateStart,
            @Param(value = "dateEnd") String dateEnd) {
        return transaksiService.getTransactionByDate(dateStart, dateEnd);
    }

    @GetMapping("/transaksi/{nik}")
    public List<TransaksiReq> findTransaksiByNik(@PathVariable  String nik) {
        return transaksiService.getTransactionByAnggotaNik(nik);
    }
}
