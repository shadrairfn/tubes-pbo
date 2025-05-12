package com.bioskop.bioskop.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.bioskop.bioskop.model.Jadwal;
@Repository
public interface JadwalRepository extends JpaRepository<Jadwal, String>{

}
