package com.bioskop.bioskop.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.bioskop.bioskop.model.Film;

@Repository
public interface FilmRepository extends JpaRepository<Film, String> {

}
