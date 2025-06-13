package com.bioskop.bioskop.model;

import java.time.LocalDateTime;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;

@Entity
public class Transaksi implements QRGenerator {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String idTransaksi;
    private LocalDateTime tanggalTransaksi;
    private Integer totalHarga;
    private String kodeTiket;

    @ManyToOne
    private Film film;

    @ManyToOne
    private Bioskop bioskop;

    @ManyToOne
    private Jadwal jadwal;

    @ManyToOne
    private Kursi kursi;

    // Default constructor (dibutuhkan JPA)
    public Transaksi() {}

    public Transaksi(String idTransaksi, LocalDateTime tanggalTransaksi, Integer totalHarga) {
        this.idTransaksi = idTransaksi;
        this.tanggalTransaksi = tanggalTransaksi;
        this.totalHarga = totalHarga;
    }

    public String getIdTransaksi() {
        return idTransaksi;
    }

    public void setIdTransaksi(String idTransaksi) {
        this.idTransaksi = idTransaksi;
    }

    public LocalDateTime getTanggalTransaksi() {
        return tanggalTransaksi;
    }

    public void setTanggalTransaksi(LocalDateTime tanggalTransaksi) {
        this.tanggalTransaksi = tanggalTransaksi;
    }

    public Integer getTotalHarga() {
        return totalHarga;
    }

    public void setTotalHarga(Integer totalHarga) {
        this.totalHarga = totalHarga;
    }

    public String getKodeTiket() {
        return kodeTiket;
    }

    public void setKodeTiket(String kodeTiket) {
        this.kodeTiket = kodeTiket;
    }

    public Film getFilm() {
        return film;
    }

    public void setFilm(Film film) {
        this.film = film;
    }

    public Bioskop getBioskop() {
        return bioskop;
    }

    public void setBioskop(Bioskop bioskop) {
        this.bioskop = bioskop;
    }

    @Override
    public String generateQR() {
        return "QR CODE";
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Jadwal getJadwal() {
        return jadwal;
    }

    public void setJadwal(Jadwal jadwal) {
        this.jadwal = jadwal;
    }

    public Kursi getKursi() {
        return kursi;
    }

    public void setKursi(Kursi kursi) {
        this.kursi = kursi;
    }
}
