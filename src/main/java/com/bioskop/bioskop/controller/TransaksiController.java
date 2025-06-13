package com.bioskop.bioskop.controller;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.bioskop.bioskop.model.Bioskop;
import com.bioskop.bioskop.model.Film;
import com.bioskop.bioskop.model.Jadwal;
import com.bioskop.bioskop.model.Kursi;
import com.bioskop.bioskop.model.Transaksi;
import com.bioskop.bioskop.repository.BioskopRepository;
import com.bioskop.bioskop.repository.FilmRepository;
import com.bioskop.bioskop.repository.JadwalRepository;
import com.bioskop.bioskop.repository.KursiRepository;
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

    @Autowired
    private JadwalRepository jadwalRepository;

    @Autowired
    private KursiRepository kursiRepository;

    @PostMapping("/buat")
    public ResponseEntity<String> buatTransaksiDenganKursi(@RequestBody Transaksi transaksi) {
        // Cari entitas yang valid
        Film film = filmRepository.findById(transaksi.getFilm().getIdFilm())
            .orElseThrow(() -> new RuntimeException("Film tidak ditemukan"));
        Bioskop bioskop = bioskopRepository.findById(transaksi.getBioskop().getIdBioskop())
            .orElseThrow(() -> new RuntimeException("Bioskop tidak ditemukan"));
        Jadwal jadwal = jadwalRepository.findById(transaksi.getJadwal().getIdJadwal())
            .orElseThrow(() -> new RuntimeException("Jadwal tidak ditemukan"));
        Kursi kursi = kursiRepository.findById(transaksi.getKursi().getIdKursi())
            .orElseThrow(() -> new RuntimeException("Kursi tidak ditemukan"));

        // Validasi kursi tersedia
        if (!kursi.isTersedia()) {
            return ResponseEntity.badRequest().body("Kursi sudah dipilih oleh orang lain.");
        }

        // Set informasi transaksi
        transaksi.setIdTransaksi(UUID.randomUUID().toString());
        transaksi.setTanggalTransaksi(LocalDateTime.now());
        transaksi.setKodeTiket(UUID.randomUUID().toString().substring(0, 8).toUpperCase());
        transaksi.setFilm(film);
        transaksi.setBioskop(bioskop);
        transaksi.setJadwal(jadwal);
        transaksi.setKursi(kursi);

        // Tandai kursi tidak tersedia
        kursi.setTersedia(false);
        kursiRepository.save(kursi);

        // Simpan transaksi
        transaksiRepository.save(transaksi);

        return ResponseEntity.ok("Transaksi berhasil! Kode Tiket: " + transaksi.getKodeTiket());
    }


    @GetMapping
    public List<Transaksi> getAll() {
        return transaksiRepository.findAll();
    }
}
