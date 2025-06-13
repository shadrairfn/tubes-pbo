package com.bioskop.bioskop.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.bioskop.bioskop.model.Kursi;
import com.bioskop.bioskop.repository.KursiRepository;

@RestController
@RequestMapping("/kursi")
public class KursiController {

    @Autowired
    private KursiRepository kursiRepository;

    @GetMapping("/jadwal/{idJadwal}")
    public List<Kursi> getKursiByJadwal(@PathVariable String idJadwal) {
        return kursiRepository.findByJadwal_IdJadwal(idJadwal);
    }
}
