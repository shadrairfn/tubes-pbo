package com.bioskop.bioskop.dto;

import java.util.List;

public class TiketResponseDTO {

    private List<Integer> daftarKursi;
    private String idTransaksi;
    private String jamTayang;
    private String kodeTiket;
    private String namaBioskop;
    private String namaFilm;

    public TiketResponseDTO(
        List<Integer> daftarKursi,
        String idTransaksi,
        String jamTayang,
        String kodeTiket,
        String namaBioskop,
        String namaFilm
    ) {
        this.daftarKursi = daftarKursi;
        this.idTransaksi = idTransaksi;
        this.jamTayang = jamTayang;
        this.kodeTiket = kodeTiket;
        this.namaBioskop = namaBioskop;
        this.namaFilm = namaFilm;
    }

    // Getter & Setter
    public List<Integer> getDaftarKursi() {
        return daftarKursi;
    }

    public void setDaftarKursi(List<Integer> daftarKursi) {
        this.daftarKursi = daftarKursi;
    }

    public String getIdTransaksi() {
        return idTransaksi;
    }

    public void setIdTransaksi(String idTransaksi) {
        this.idTransaksi = idTransaksi;
    }

    public String getJamTayang() {
        return jamTayang;
    }

    public void setJamTayang(String jamTayang) {
        this.jamTayang = jamTayang;
    }

    public String getKodeTiket() {
        return kodeTiket;
    }

    public void setKodeTiket(String kodeTiket) {
        this.kodeTiket = kodeTiket;
    }

    public String getNamaBioskop() {
        return namaBioskop;
    }

    public void setNamaBioskop(String namaBioskop) {
        this.namaBioskop = namaBioskop;
    }

    public String getNamaFilm() {
        return namaFilm;
    }

    public void setNamaFilm(String namaFilm) {
        this.namaFilm = namaFilm;
    }
}
