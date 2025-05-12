package com.bioskop.bioskop.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;

@Entity
public class Customer {

    @Id
    @GeneratedValue
    private String idCustomer;
    private String nama;

    public Customer(String idCustomer, String nama) {
        this.idCustomer = idCustomer;
        this.nama = nama;
    }

    public String getIdCustomer() {
        return idCustomer;
    }

    public void setIdCustomer(String idCustomer) {
        this.idCustomer = idCustomer;
    }

    public String getNama() {
        return nama;
    }

    public void setNama(String nama) {
        this.nama = nama;
    }

    public void pilihFilm(String nama) {

    }

    public void bayar(Transaksi transaksi) {
        
    }
}
