package com.bioskop.bioskop.dto;

import java.util.Date;

public class JadwalSummaryDTO {
    private String idJadwal;
    private Integer totalKursi;
    private Integer sisaKursi;
    private Double harga;
    private Date waktu;

    public JadwalSummaryDTO(String idJadwal, Double harga, Integer totalKursi, Integer sisaKursi, Date waktu) {
        this.idJadwal = idJadwal;
        this.harga = harga;
        this.totalKursi = totalKursi;
        this.sisaKursi = sisaKursi;
        this.waktu = waktu;
    }

    public String getIdJadwal() {
        return idJadwal;
    }

    public Double getHarga() {
        return harga;
    }

    public Integer getTotalKursi() {
        return totalKursi;
    }

    public Integer getSisaKursi() {
        return sisaKursi;
    }

    public Date getWaktu() {
        return waktu;
    }

    public void setWaktu(Date waktu) {
        this.waktu = waktu;
    }

}
