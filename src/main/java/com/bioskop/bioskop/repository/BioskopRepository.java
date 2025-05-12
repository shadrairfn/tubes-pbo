package com.bioskop.bioskop.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.bioskop.bioskop.model.Bioskop;

@Repository
public interface BioskopRepository extends JpaRepository<Bioskop, String>{

}
