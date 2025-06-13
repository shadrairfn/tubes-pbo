package com.bioskop.bioskop.model;

import jakarta.persistence.*;
import java.util.UUID;

@Entity
public class Kursi {

    @Id
    private String idKursi = UUID.randomUUID().toString();

    private String nomorKursi;
    private boolean tersedia;

    @ManyToOne
    @JoinColumn(name = "jadwal_id")
    private Jadwal jadwal;

    // Getter & Setter
    public String getIdKursi() {
        return idKursi;
    }

    public void setIdKursi(String idKursi) {
        this.idKursi = idKursi;
    }

    public String getNomorKursi() {
        return nomorKursi;
    }

    public void setNomorKursi(String nomorKursi) {
        this.nomorKursi = nomorKursi;
    }

    public boolean isTersedia() {
        return tersedia;
    }

    public void setTersedia(boolean tersedia) {
        this.tersedia = tersedia;
    }

    public Jadwal getJadwal() {
        return jadwal;
    }

    public void setJadwal(Jadwal jadwal) {
        this.jadwal = jadwal;
    }
}
