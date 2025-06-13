package com.bioskop.bioskop.controller;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.bioskop.bioskop.dto.JadwalSummaryDTO;
import com.bioskop.bioskop.model.Jadwal;
import com.bioskop.bioskop.model.Kursi;
import com.bioskop.bioskop.repository.BioskopRepository;
import com.bioskop.bioskop.repository.FilmRepository;
import com.bioskop.bioskop.repository.JadwalRepository;

@RestController
@RequestMapping("/jadwal")
public class JadwalController {

    @Autowired
    private JadwalRepository jadwalRepository;

    @Autowired
    private FilmRepository filmRepository;

    @Autowired
    private BioskopRepository bioskopRepository;

    @GetMapping("/{id}/kursi-tersedia")
    public List<Kursi> getKursiTersedia(@PathVariable String id) {
        Jadwal jadwal = jadwalRepository.findById(id)
            .orElseThrow(() -> new RuntimeException("Jadwal tidak ditemukan"));
        
        return jadwal.getKursiList().stream()
            .filter(Kursi::isTersedia)
            .collect(Collectors.toList());
    }

    @GetMapping("/film/{idFilm}/bioskop/{idBioskop}")
    public List<JadwalSummaryDTO> getJadwalByFilmAndBioskop(
            @PathVariable String idFilm,
            @PathVariable String idBioskop) {

        return jadwalRepository.findByFilm_IdFilmAndBioskop_IdBioskop(idFilm, idBioskop)
            .stream()
            .map(jadwal -> new JadwalSummaryDTO(
                jadwal.getIdJadwal(),
                jadwal.getHarga(),
                jadwal.getTotalKursi(),
                jadwal.getSisaKursi(),
                jadwal.getWaktu()))
            .collect(Collectors.toList());
    }
}
