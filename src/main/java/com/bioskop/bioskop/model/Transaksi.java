package com.bioskop.bioskop.model;

import java.util.Date;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;

@Entity
public class Transaksi implements QRGenerator{
    @Id
    @GeneratedValue
    private String idTransaksi;
    private Date tanggalTransaksi;
    private Integer totalHarga;

    public Transaksi(String idTransaksi, Date tanggalTransaksi, Integer totalHarga) {
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

    public Date getTanggalTransaksi() {
        return tanggalTransaksi;
    }

    public void setTanggalTransaksi(Date tanggalTransaksi) {
        this.tanggalTransaksi = tanggalTransaksi;
    }

    public Integer getTotalHarga() {
        return totalHarga;
    }

    public void setTotalHarga(Integer totalHarga) {
        this.totalHarga = totalHarga;
    }

    public Integer hitungTotal() {
        return totalHarga;
    }

    public void generateTiket(){

    }

    @Override
    public String generateQR() {
        return "QR CODE";
    }
}
