package com.bioskop.bioskop.controller;

import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.bioskop.bioskop.dto.TiketResponseDTO;
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
    public ResponseEntity<TiketResponseDTO> buatTransaksiDenganKursi(@RequestBody Transaksi transaksi) {
        // Validasi dan ambil entitas terkait
        Film film = filmRepository.findById(transaksi.getFilm().getIdFilm())
            .orElseThrow(() -> new RuntimeException("Film tidak ditemukan"));
        Bioskop bioskop = bioskopRepository.findById(transaksi.getBioskop().getIdBioskop())
            .orElseThrow(() -> new RuntimeException("Bioskop tidak ditemukan"));
        Jadwal jadwal = jadwalRepository.findById(transaksi.getJadwal().getIdJadwal())
            .orElseThrow(() -> new RuntimeException("Jadwal tidak ditemukan"));

        List<Kursi> kursiList = transaksi.getKursi();

        // Validasi ketersediaan kursi
        for (Kursi kursi : kursiList) {
            Kursi k = kursiRepository.findById(kursi.getIdKursi())
                .orElseThrow(() -> new RuntimeException("Kursi tidak ditemukan"));

            if (!k.isTersedia()) {
                return ResponseEntity.badRequest().body(null); // Kursi sudah tidak tersedia
            }

            k.setTersedia(false); // tandai kursi tidak tersedia
            kursiRepository.save(k);
        }

        // Simpan transaksi
        transaksi.setIdTransaksi(UUID.randomUUID().toString());
        transaksi.setTanggalTransaksi(LocalDateTime.now());
        transaksi.setKodeTiket(UUID.randomUUID().toString().substring(0, 8).toUpperCase());
        transaksi.setFilm(film);
        transaksi.setBioskop(bioskop);
        transaksi.setJadwal(jadwal);
        transaksi.setKursi(kursiList); // pastikan kursi tersimpan juga
        transaksiRepository.save(transaksi);

        // Format jam tayang
        SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy HH:mm");
        String formattedJam = formatter.format(jadwal.getWaktu());

        // Bangun DTO untuk response
        TiketResponseDTO response = new TiketResponseDTO(
            kursiList.stream().map(Kursi::getNomorKursi).toList(),
            transaksi.getIdTransaksi(),
            formattedJam,
            transaksi.getKodeTiket(),
            bioskop.getNamaBioskop(),
            film.getJudul()
        );

        return ResponseEntity.ok(response);
    }

    @GetMapping("/{id}")
    public ResponseEntity<TiketResponseDTO> getTransaksiById(@PathVariable("id") Long Id) {
        Transaksi transaksi = transaksiRepository.findById(Id)
            .orElseThrow(() -> new RuntimeException("Transaksi tidak ditemukan"));

        Film film = transaksi.getFilm();
        Bioskop bioskop = transaksi.getBioskop();
        Jadwal jadwal = transaksi.getJadwal();
        List<Kursi> kursiList = transaksi.getKursi();

        SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy HH:mm");
        String formattedJam = formatter.format(jadwal.getWaktu());

        TiketResponseDTO response = new TiketResponseDTO(
            kursiList.stream().map(Kursi::getNomorKursi).toList(),
            transaksi.getIdTransaksi(),
            formattedJam,  // tipe Date, langsung dipakai karena DTO pakai Date juga
            transaksi.getKodeTiket(),
            bioskop.getNamaBioskop(),
            film.getJudul()
        );

        return ResponseEntity.ok(response);
    }


    @GetMapping
    public List<Transaksi> getAll() {
        return transaksiRepository.findAll();
    }
}
