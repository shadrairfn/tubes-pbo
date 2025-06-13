package com.bioskop.bioskop.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonBackReference;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;

@Entity
public class Jadwal {
    @Id
    private String idJadwal;
    private Integer totalKursi;
    private Integer sisaKursi;
    private Double harga;

    @ManyToOne
    @JoinColumn(name = "film_id")
    @JsonBackReference
    private Film film;

    @ManyToOne
    @JoinColumn(name = "bioskop_id")
    private Bioskop bioskop;

    private Date waktu;

    @OneToMany(mappedBy = "jadwal", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Kursi> kursiList = new ArrayList<>();


    public Jadwal() {

    }
    
    public Jadwal(Bioskop idBioskop, Film idFilm, String idJadwal, Date waktu) {
        this.bioskop = idBioskop;
        this.film = idFilm;
        this.idJadwal = idJadwal;
        this.waktu = waktu;
    }

    public Bioskop getBioskop() { return bioskop; }
    public void setBioskop(Bioskop bioskop) { this.bioskop = bioskop; }

    public Film getFilm() { return film; }
    public void setFilm(Film film) { this.film = film; }

    

    public Date getWaktu() {
        return waktu;
    }

    public Integer getTotalKursi() {
        return totalKursi;
    }

    public void setTotalKursi(Integer totalKursi) {
        this.totalKursi = totalKursi;
        this.sisaKursi = totalKursi; // saat awal, sisa = total
    }

    public Integer getSisaKursi() {
        return sisaKursi;
    }

    public void setSisaKursi(Integer sisaKursi) {
        this.sisaKursi = sisaKursi;
    }

    public Double getHarga() {
        return harga;
    }

    public void setHarga(Double harga) {
        this.harga = harga;
    }

    public String getIdJadwal() {
        return idJadwal;
    }

    public void setIdJadwal(String idJadwal) {
        this.idJadwal = idJadwal;
    }

    public List<Kursi> getKursiList() {
        return kursiList;
    }


}
