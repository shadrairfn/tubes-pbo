package com.bioskop.bioskop.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.bioskop.bioskop.model.Jadwal;
@Repository
public interface JadwalRepository extends JpaRepository<Jadwal, String>{
    List<Jadwal> findByFilm_IdFilmAndBioskop_IdBioskop(String idFilm, String idBioskop);
}
