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

import com.bioskop.bioskop.model.Bioskop;
import com.bioskop.bioskop.model.Film;
import com.bioskop.bioskop.model.Transaksi;
import com.bioskop.bioskop.repository.BioskopRepository;
import com.bioskop.bioskop.repository.FilmRepository;
import com.bioskop.bioskop.repository.TransaksiRepository;


@RestController
@RequestMapping("/transaksi")
public class TransaksiController {

    @Autowired
    private TransaksiRepository transaksiRepository;
    @Autowired
    private FilmRepository filmRepository;  // repository untuk Film

    @Autowired
    private BioskopRepository bioskopRepository;  // repository untuk Bioskop
    @PostMapping
    public Transaksi buatTransaksi(@RequestBody Transaksi transaksi) {
    // Set tanggal dan kode tiket
    transaksi.setTanggalTransaksi(LocalDateTime.now());
    transaksi.setKodeTiket(UUID.randomUUID().toString().substring(0, 8).toUpperCase());

    // Ambil film berdasarkan idFilm dari transaksi.getFilm()
    String idFilm = transaksi.getFilm().getIdFilm();
    Film film = filmRepository.findById(idFilm)
                 .orElseThrow(() -> new RuntimeException("Film tidak ditemukan dengan id: " + idFilm));
    transaksi.setFilm(film);

    // Ambil bioskop berdasarkan idBioskop dari transaksi.getBioskop()
    String idBioskop = transaksi.getBioskop().getIdBioskop();
    Bioskop bioskop = bioskopRepository.findById(idBioskop)
                     .orElseThrow(() -> new RuntimeException("Bioskop tidak ditemukan dengan id: " + idBioskop));
    transaksi.setBioskop(bioskop);

    // Simpan transaksi ke DB
    return transaksiRepository.save(transaksi);
    }

    @GetMapping
    public List<Transaksi> getAll() {
        return transaksiRepository.findAll();
    }
}
