package com.bioskop.bioskop.controller;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URLConnection;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import com.bioskop.bioskop.model.Bioskop;
import com.bioskop.bioskop.model.Film;
import com.bioskop.bioskop.repository.BioskopRepository;
import com.bioskop.bioskop.repository.FilmRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ContentDisposition;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/film")
@CrossOrigin(origins = "http://localhost:3000")
public class FilmController {

    @Autowired
    private FilmRepository filmRepository;

    @Autowired
    private BioskopRepository bioskopRepository;

    @GetMapping
    public List<Film> getAllFilms() {
        return filmRepository.findAll();
    }

    @GetMapping("/{idFilm}")
    public ResponseEntity<Film> getFilmById(@PathVariable String idFilm) {
        Optional<Film> film = filmRepository.findById(idFilm);
        return film.map(ResponseEntity::ok)
                   .orElseGet(() -> ResponseEntity.status(HttpStatus.NOT_FOUND).build());
    }

    @GetMapping("/{idFilm}/poster")
    public ResponseEntity<byte[]> getFilmPoster(@PathVariable String idFilm) {
        Optional<Film> filmOpt = filmRepository.findById(idFilm);
        if (filmOpt.isEmpty() || filmOpt.get().getPoster() == null) {
            return ResponseEntity.notFound().build();
        }

        byte[] image = filmOpt.get().getPoster();
        String contentType = "application/octet-stream"; // default kalau tidak ketebak

        try (InputStream is = new ByteArrayInputStream(image)) {
            String guessed = URLConnection.guessContentTypeFromStream(is);
            if (guessed != null) {
                contentType = guessed;
            }
        } catch (IOException e) {
            e.printStackTrace(); // log error
        }

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.parseMediaType(contentType));
        headers.setContentDisposition(ContentDisposition.inline().filename("poster").build());

        return new ResponseEntity<>(image, headers, HttpStatus.OK);
    }



    @DeleteMapping("/{idFilm}")
    public ResponseEntity<?> deleteFilm(@PathVariable String idFilm) {
        if (filmRepository.existsById(idFilm)) {
            filmRepository.deleteById(idFilm);
            return ResponseEntity.ok("Film dengan ID " + idFilm + " berhasil dihapus.");
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Film tidak ditemukan.");
        }
    }

    @PostMapping("/upload")
    public ResponseEntity<?> uploadFilm(
        @RequestParam("id_film") String idFilm,
        @RequestParam("judul") String judul,
        @RequestParam("genre") String genre,
        @RequestParam("durasi") Integer durasi,
        @RequestParam("deskripsi") String deskripsi,
        @RequestParam("tanggal_rilis") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) Date tanggalRilis,
        @RequestParam("bioskop_id") String bioskopId,
        @RequestParam("dimensi") String dimensi,
        @RequestParam("batas_umur") Integer batasUmur,
        @RequestParam("poster") MultipartFile poster
    ) {
        try {
            Optional<Bioskop> optionalBioskop = bioskopRepository.findById(bioskopId);
            if (optionalBioskop.isEmpty()) {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Bioskop tidak ditemukan");
            }

            Film film = new Film();
            film.setIdFilm(idFilm);
            film.setJudul(judul);
            film.setGenre(genre);
            film.setDurasi(durasi);
            film.setDeskripsi(deskripsi);
            film.setTanggalRilis(tanggalRilis);
            film.setBioskop(optionalBioskop.get());
            film.setDimensi(dimensi);
            film.setBatasUmur(batasUmur);
            film.setPoster(poster.getBytes());

            filmRepository.save(film);
            return ResponseEntity.ok("Film berhasil ditambahkan.");
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Gagal upload film: " + e.getMessage());
        }
    }
}
