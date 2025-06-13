package com.bioskop.bioskop.controller;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.bioskop.bioskop.model.Jadwal;
import com.bioskop.bioskop.model.Kursi;
import com.bioskop.bioskop.repository.JadwalRepository;

@RestController
@RequestMapping("/jadwal")
public class JadwalController {

    @Autowired
    private JadwalRepository jadwalRepository;

    @GetMapping("/{id}/kursi-tersedia")
    public List<Kursi> getKursiTersedia(@PathVariable String id) {
        Jadwal jadwal = jadwalRepository.findById(id)
            .orElseThrow(() -> new RuntimeException("Jadwal tidak ditemukan"));
        
        return jadwal.getKursiList().stream()
            .filter(Kursi::isTersedia)
            .collect(Collectors.toList());
    }
}
