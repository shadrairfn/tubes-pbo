package com.bioskop.bioskop.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.bioskop.bioskop.model.Kursi;
@Repository
public interface KursiRepository extends JpaRepository<Kursi, Integer> {
    List<Kursi> findByJadwal_IdJadwal(String idJadwal);
}
