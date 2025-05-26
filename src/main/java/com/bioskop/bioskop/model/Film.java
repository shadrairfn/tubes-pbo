package com.bioskop.bioskop.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
@Entity
public class Film {

    @Id
    private String idFilm;
    private String judul;
    private String genre;
    private Integer durasi;
    private String deskripsi;
    private Date tanggalRilis;
    @OneToMany(mappedBy = "film", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Jadwal> jadwalList;

    @ManyToOne
    @JoinColumn(name = "bioskop_id")
    private Bioskop bioskop;

    public Film() {

    }
    public Film(String deskripsi, Integer durasi, String genre, String idFilm, String judul, Date tanggalRilis) {
        this.deskripsi = deskripsi;
        this.durasi = durasi;
        this.genre = genre;
        this.idFilm = idFilm;
        this.judul = judul;
        this.tanggalRilis = tanggalRilis;
        this.jadwalList = new ArrayList<>();
    }

    public String getIdFilm() {
        return idFilm;
    }

    public void setIdFilm(String idFilm) {
        this.idFilm = idFilm;
    }

    public String getJudul() {
        return judul;
    }

    public void setJudul(String judul) {
        this.judul = judul;
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public Integer getDurasi() {
        return durasi;
    }

    public void setDurasi(Integer durasi) {
        this.durasi = durasi;
    }

    public String getDeskripsi() {
        return deskripsi;
    }

    public void setDeskripsi(String deskripsi) {
        this.deskripsi = deskripsi;
    }

    public Date getTanggalRilis() {
        return tanggalRilis;
    }

    public void setTanggalRilis(Date tanggalRilis) {
        this.tanggalRilis = tanggalRilis;
    }

    public void showFilm(){

    }
    
    public void showJadwal(){

    }

    public void cekJadwalTayang(){

    }

    public void tambahJadwal(Jadwal jadwal){
        jadwalList.add(jadwal);
    }
}
