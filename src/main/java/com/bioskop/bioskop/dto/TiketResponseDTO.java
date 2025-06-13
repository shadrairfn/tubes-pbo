package com.bioskop.bioskop.dto;

import java.util.Date;
import java.util.List;


public class TiketResponseDTO {
    private String idTransaksi;
    private String kodeTiket;
    private String namaFilm;
    private String namaBioskop;
    private String jamTayang;
    private List<Integer> daftarKursi;

    // constructor, getter, setter

    public TiketResponseDTO() {
        // kosong, dibutuhkan untuk serialisasi/deserialisasi
    }

    public TiketResponseDTO(List<Integer> daftarKursi, String idTransaksi, String jamTayang, String kodeTiket, String namaBioskop, String namaFilm) {
        this.daftarKursi = daftarKursi;
        this.idTransaksi = idTransaksi;
        this.jamTayang = jamTayang;
        this.kodeTiket = kodeTiket;
        this.namaBioskop = namaBioskop;
        this.namaFilm = namaFilm;
    }


    public String getIdTransaksi() {
        return idTransaksi;
    }

    public void setIdTransaksi(String idTransaksi) {
        this.idTransaksi = idTransaksi;
    }

    public String getKodeTiket() {
        return kodeTiket;
    }

    public void setKodeTiket(String kodeTiket) {
        this.kodeTiket = kodeTiket;
    }

    public String getNamaFilm() {
        return namaFilm;
    }

    public void setNamaFilm(String namaFilm) {
        this.namaFilm = namaFilm;
    }

    public String getNamaBioskop() {
        return namaBioskop;
    }

    public void setNamaBioskop(String namaBioskop) {
        this.namaBioskop = namaBioskop;
    }

    public String getJamTayang() {
        return jamTayang;
    }

    public void setJamTayang(String jamTayang) {
        this.jamTayang = jamTayang;
    }

    public List<Integer> getDaftarKursi() {
        return daftarKursi;
    }

    public void setDaftarKursi(List<Integer> daftarKursi) {
        this.daftarKursi = daftarKursi;
    }

    
}
