package com.bioskop.bioskop.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

@Entity
public class Kursi {

    @Id
    private Integer idKursi;

    private Integer nomorKursi;
    private boolean tersedia;

    @ManyToOne
    @JoinColumn(name = "jadwal_id")
    private Jadwal jadwal;

    // Getter & Setter
    public Integer getIdKursi() {
        return idKursi;
    }

    public void setIdKursi(Integer idKursi) {
        this.idKursi = idKursi;
    }

    public Integer getNomorKursi() {
        return nomorKursi;
    }

    public void setNomorKursi(Integer nomorKursi) {
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
