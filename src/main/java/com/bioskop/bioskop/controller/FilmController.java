package com.bioskop.bioskop.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.bioskop.bioskop.model.Film;
import com.bioskop.bioskop.repository.FilmRepository;

@RestController
@RequestMapping("/film")
public class FilmController {

    @Autowired
    private FilmRepository filmRepository;

    // Endpoint untuk membuat film baru
    @PostMapping
    public Film createFilm(@RequestBody Film film) {
        return filmRepository.save(film);
    }

    // Endpoint untuk mendapatkan semua film
    @GetMapping
    public List<Film> getAllFilms() {
        return filmRepository.findAll();
    }

    // Endpoint untuk mendapatkan film berdasarkan ID
    @GetMapping("/{idFilm}")
    public Optional<Film> getFilmById(@PathVariable String idFilm) {
        return filmRepository.findById(idFilm);
    }
}