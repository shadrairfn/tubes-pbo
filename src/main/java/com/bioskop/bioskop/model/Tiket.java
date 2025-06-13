package com.bioskop.bioskop.model;

import jakarta.persistence.Entity;
import jakarta.persistence.PrimaryKeyJoinColumn;

@Entity
@PrimaryKeyJoinColumn(name = "id")
public class Tiket extends Transaksi {

    private String noKursi;
    private String qrCode;

    public Tiket() {
        super(); 
    }

    public Tiket(String idTransaksi, 
                 java.time.LocalDateTime tanggalTransaksi,
                 Integer totalHarga,
                 String noKursi,
                 String qrCode) {
        super(idTransaksi, tanggalTransaksi, totalHarga);
        this.noKursi = noKursi;
        this.qrCode = qrCode;
    }

    public String getNoKursi() {
        return noKursi;
    }

    public void setNoKursi(String noKursi) {
        this.noKursi = noKursi;
    }

    public String getQrCode() {
        return qrCode;
    }

    public void setQrCode(String qrCode) {
        this.qrCode = qrCode;
    }

    public void showTiket() {
        System.out.println("Tiket ID: " + getIdTransaksi());
        System.out.println("No Kursi: " + noKursi);
        System.out.println("QR Code: " + qrCode);
        System.out.println("Film: " + getFilm().getJudul()); 
    }
}
