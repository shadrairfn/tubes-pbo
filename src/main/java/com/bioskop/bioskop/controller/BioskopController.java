package com.bioskop.bioskop.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.bioskop.bioskop.model.Bioskop;
import com.bioskop.bioskop.model.Film;
import com.bioskop.bioskop.model.Jadwal;
import com.bioskop.bioskop.model.Kursi;
import com.bioskop.bioskop.repository.BioskopRepository;
import com.bioskop.bioskop.repository.FilmRepository;
import com.bioskop.bioskop.repository.JadwalRepository;
import com.bioskop.bioskop.repository.KursiRepository;

@RestController
@RequestMapping("/bioskop")
public class BioskopController {

    @Autowired
    private BioskopRepository bioskopRepository;

    @Autowired
    private FilmRepository filmRepository;

    @Autowired
    private JadwalRepository jadwalRepository;

    @Autowired
    private KursiRepository kursiRepository;


    @PostMapping
    public Bioskop createBioskop(@RequestBody Bioskop bioskop) {
        return bioskopRepository.save(bioskop);
    }

    @PostMapping("/{bioskopId}/film/{filmId}/jadwal")
    public ResponseEntity<String> tambahJadwalKeFilm(
        @PathVariable String bioskopId,
        @PathVariable String filmId,
        @RequestBody List<Jadwal> listJadwal) {

        Optional<Bioskop> bioskopOpt = bioskopRepository.findById(bioskopId);
        Optional<Film> filmOpt = filmRepository.findById(filmId);

        if (bioskopOpt.isEmpty() || filmOpt.isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        Bioskop bioskop = bioskopOpt.get();
        Film film = filmOpt.get();

        for (Jadwal jadwal : listJadwal) {
            jadwal.setFilm(film);
            jadwal.setBioskop(bioskop);

            if (jadwal.getSisaKursi() == null) {
                jadwal.setSisaKursi(jadwal.getTotalKursi());
            }

            if (jadwal.getIdJadwal() == null || jadwal.getIdJadwal().isEmpty()) {
                return ResponseEntity.badRequest().body("idJadwal wajib diisi untuk setiap jadwal.");
            }

            // Simpan jadwal terlebih dahulu agar dapat id-nya
            jadwalRepository.save(jadwal);

            // Buat kursi berdasarkan totalKursi
            for (int i = 1; i <= jadwal.getTotalKursi(); i++) {
                Kursi kursi = new Kursi();
                kursi.setNomorKursi("No"+ i); // Bisa disesuaikan formatnya
                kursi.setTersedia(true);
                kursi.setJadwal(jadwal);
                kursiRepository.save(kursi);
            }

            
        }

        filmRepository.save(film); // Akan cascading save ke Jadwal juga

        return ResponseEntity.ok("Jadwal berhasil ditambahkan ke film.");
    }



    @GetMapping("/{idBioskop}")
    public Optional<Bioskop> getBioskop(@PathVariable String idBioskop) {
        return bioskopRepository.findById(idBioskop);
    }

    @GetMapping
    public List<Bioskop> getAllBioskop() {
        return bioskopRepository.findAll();
    }
}
