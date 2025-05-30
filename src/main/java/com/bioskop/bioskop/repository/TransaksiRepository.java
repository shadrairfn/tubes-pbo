package com.bioskop.bioskop.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.bioskop.bioskop.model.Transaksi;

@Repository
public interface TransaksiRepository extends JpaRepository<Transaksi, Long> {
}
