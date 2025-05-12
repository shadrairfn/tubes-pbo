package com.bioskop.bioskop.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;

@Entity
public class Bioskop {

    @Id
    @GeneratedValue
    private String idBioskop;
    private String namaBioskop;
    private String lokasi;
    private Integer totalLayar;
    

    @OneToMany(mappedBy = "bioskop", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Film> filmList;

    public Bioskop() {
    // Default constructor
    }

    public Bioskop(String idBioskop, String lokasi, String namaBioskop, Integer totalLayar) {
        this.idBioskop = idBioskop;
        this.lokasi = lokasi;
        this.namaBioskop = namaBioskop;
        this.totalLayar = totalLayar;
        this.filmList = new ArrayList<>();
    }

    public String getIdBioskop() {
        return idBioskop;
    }

    public void setIdBioskop(String idBioskop) {
        this.idBioskop = idBioskop;
    }

    public String getNamaBioskop() {
        return namaBioskop;
    }

    public void setNamaBioskop(String namaBioskop) {
        this.namaBioskop = namaBioskop;
    }

    public String getLokasi() {
        return lokasi;
    }

    public void setLokasi(String lokasi) {
        this.lokasi = lokasi;
    }

    public Integer getTotalLayar() {
        return totalLayar;
    }

    public void setTotalLayar(Integer totalLayar) {
        this.totalLayar = totalLayar;
    }

    public void displayInfo(){

    }

    public void tambahFilm(Film film, Date waktuTayang){
        filmList.add(film);
    }
}
