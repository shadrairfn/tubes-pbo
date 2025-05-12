package com.bioskop.bioskop.controller;

import com.bioskop.bioskop.model.Bioskop;
import com.bioskop.bioskop.model.Film;
import com.bioskop.bioskop.model.Jadwal;
import com.bioskop.bioskop.repository.BioskopRepository;
import com.bioskop.bioskop.repository.FilmRepository;
import com.bioskop.bioskop.repository.JadwalRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/bioskop")
public class BioskopController {

    @Autowired
    private BioskopRepository bioskopRepository;

    @Autowired
    private FilmRepository filmRepository;

    @Autowired
    private JadwalRepository jadwalRepository;

    @PostMapping("/")
    public Bioskop createBioskop(@RequestBody Bioskop bioskop) {
        return bioskopRepository.save(bioskop);
    }

    @PostMapping("/{idBioskop}/film/{idFilm}/jadwal")
    public String tambahFilmKeBioskop(@PathVariable String idBioskop,
                                      @PathVariable String idFilm,
                                      @RequestBody Jadwal jadwal) {
        Optional<Bioskop> bioskopOpt = bioskopRepository.findById(idBioskop);
        Optional<Film> filmOpt = filmRepository.findById(idFilm);
        if (bioskopOpt.isPresent() && filmOpt.isPresent()) {
            Bioskop bioskop = bioskopOpt.get();
            Film film = filmOpt.get();
            // Tambah jadwal ke film
            film.tambahJadwal(jadwal);
            jadwalRepository.save(jadwal);
            // Tambahkan film ke bioskop (buat relasi sesuai kebutuhan)
            bioskop.tambahFilm(film, jadwal.getWaktu());
            bioskopRepository.save(bioskop);
            filmRepository.save(film);
            return "Film dan jadwal berhasil ditambahkan ke bioskop";
        } else {
            return "Bioskop atau Film tidak ditemukan";
        }
    }

    @GetMapping("/{idBioskop}")
    public Optional<Bioskop> getBioskop(@PathVariable String idBioskop) {
        return bioskopRepository.findById(idBioskop);
    }
}
