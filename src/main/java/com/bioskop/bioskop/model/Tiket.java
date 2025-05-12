package com.bioskop.bioskop.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;

@Entity
public class Tiket {
    @Id
    @GeneratedValue
    private String idTiket;
    private String noKursi;
    private String qrCode;

    public Tiket(String idTiket, String noKursi, String qrCode) {
        this.idTiket = idTiket;
        this.noKursi = noKursi;
        this.qrCode = qrCode;
    }

    public void showTiket() {
        System.out.println("Tiket ID: " + idTiket);
        System.out.println("No Kursi: " + noKursi);
        System.out.println("QR Code: " + qrCode);
    }
}
