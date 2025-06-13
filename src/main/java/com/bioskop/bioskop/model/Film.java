package com.bioskop.bioskop.model;

import java.util.Date;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.Lob;
import jakarta.persistence.ManyToOne;

@Entity
public class Film {

    @Id
    private String idFilm;
    private String judul;
    private String genre;
    private Integer durasi;
    private String deskripsi;
    private Date tanggalRilis;

    private String dimensi;
    private Integer batasUmur;

    @Lob
    private byte[] poster;

   

    @ManyToOne
    @JoinColumn(name = "bioskop_id")
    private Bioskop bioskop;
    // Constructor kosong
    public Film() {

    }
    public Film(String deskripsi, Integer durasi, String genre, String idFilm, String judul, Date tanggalRilis, String dimensi, Integer batasUmur) {
        this.deskripsi = deskripsi;
        this.durasi = durasi;
        this.genre = genre;
        this.idFilm = idFilm;
        this.judul = judul;
        this.tanggalRilis = tanggalRilis;
        this.dimensi = dimensi;
        this.batasUmur = batasUmur;
        
    }

    // Getter dan Setter

    public String getDimensi() { return dimensi; }
    public void setDimensi(String dimensi) { this.dimensi = dimensi; }

    public Integer getBatasUmur() { return batasUmur; }
    public void setBatasUmur(Integer batasUmur) { this.batasUmur = batasUmur; }

    public byte[] getPoster() { return poster; }
    public void setPoster(byte[] poster) { this.poster = poster; }

    public Bioskop getBioskop() { return bioskop; }
    public void setBioskop(Bioskop bioskop) { this.bioskop = bioskop; }

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

   


}
