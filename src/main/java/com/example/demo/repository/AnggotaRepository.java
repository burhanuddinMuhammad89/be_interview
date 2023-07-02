package com.example.demo.repository;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import com.example.demo.model.Anggota;

public interface AnggotaRepository extends CrudRepository<Anggota, Long>{
    List<Anggota> findByNikContaining(String nik);    
    List<Anggota> findBynamaAnggotaContaining(String name);
    List<Anggota> findByEmail(String email);
    Anggota findByNik(String nik);
    @Query("select a from Anggota a join fetch a.tList s where (s.createdAt >= :startDate and s.createdAt <= :endDate) and a.nik = :nik")
    List<Anggota> findBycreatedAtBetweenAndNik_anggota(@Param("startDate")Date startDate, @Param("endDate")Date endDate, @Param("nik")String nik);

}
