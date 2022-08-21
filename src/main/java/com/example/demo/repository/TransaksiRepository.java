package com.example.demo.repository;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.example.demo.model.Transaksi;

public interface TransaksiRepository extends CrudRepository<Transaksi,Long> {

    @Query(value = "SELECT * FROM transaksi WHERE (createdAt between ?1 and ?2) and nik_anggota = ?3", nativeQuery = true)
    List<Transaksi> findBycreatedAtBetweenAndNik_anggota(Date startDate, Date endDate, String nik);

    List<Transaksi> findBycreatedAtBetween(Date startDate, Date endDate);
    List<Transaksi> findBynikAnggota(String Nik);
    
}
