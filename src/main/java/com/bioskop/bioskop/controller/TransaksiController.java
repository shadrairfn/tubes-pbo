package com.bioskop.bioskop.controller;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.bioskop.bioskop.model.Transaksi;
import com.bioskop.bioskop.repository.TransaksiRepository;

@RestController
@RequestMapping("/transaksi")
public class TransaksiController {

    @Autowired
    private TransaksiRepository transaksiRepository;

    @PostMapping
    public Transaksi buatTransaksi(@RequestBody Transaksi transaksi) {
        transaksi.setTanggalTransaksi(LocalDateTime.now());
        transaksi.setKodeTiket(UUID.randomUUID().toString().substring(0, 8).toUpperCase());
        return transaksiRepository.save(transaksi);
    }

    @GetMapping
    public List<Transaksi> getAll() {
        return transaksiRepository.findAll();
    }
}
