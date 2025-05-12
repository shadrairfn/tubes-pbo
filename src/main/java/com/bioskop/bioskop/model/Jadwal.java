package com.bioskop.bioskop.model;

import java.util.Date;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

@Entity
public class Jadwal {
    @Id
    private String idJadwal;

    @ManyToOne
    @JoinColumn(name = "film_id")
    private Film film;

    @ManyToOne
    @JoinColumn(name = "bioskop_id")
    private Bioskop bioskop;

    private Date waktu;

    public Jadwal() {

    }
    
    public Jadwal(Bioskop idBioskop, Film idFilm, String idJadwal, Date waktu) {
        this.bioskop = idBioskop;
        this.film = idFilm;
        this.idJadwal = idJadwal;
        this.waktu = waktu;
    }

    public Date getWaktu() {
        return waktu;
    }


}
