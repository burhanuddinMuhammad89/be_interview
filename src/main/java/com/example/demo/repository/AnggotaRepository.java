package com.example.demo.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.example.demo.model.Anggota;

public interface AnggotaRepository extends CrudRepository<Anggota, Long>{
    List<Anggota> findByNikContaining(String nik);    
    List<Anggota> findBynamaAnggotaContaining(String name);
    Anggota findByNik(String nik);

}
