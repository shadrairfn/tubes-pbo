package com.bioskop.bioskop.controller;

import com.bioskop.bioskop.model.Customer;
import com.bioskop.bioskop.model.Film;
import com.bioskop.bioskop.model.Transaksi;
import com.bioskop.bioskop.repository.CustomerRepository;
import com.bioskop.bioskop.repository.FilmRepository;
import com.bioskop.bioskop.repository.TransaksiRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/customer")
public class CustomerController {

    @Autowired
    private CustomerRepository customerRepository;

    @Autowired
    private FilmRepository filmRepository;

    @Autowired
    private TransaksiRepository transaksiRepository;

    @PostMapping("/")
    public Customer createCustomer(@RequestBody Customer customer) {
        return customerRepository.save(customer);
    }

    @PostMapping("/{idCustomer}/pilihFilm/{idFilm}")
    public String pilihFilm(@PathVariable String idCustomer, @PathVariable String idFilm) {
        Optional<Customer> customerOpt = customerRepository.findById(idCustomer);
        Optional<Film> filmOpt = filmRepository.findById(idFilm);
        if (customerOpt.isPresent() && filmOpt.isPresent()) {
            Customer customer = customerOpt.get();
            Film film = filmOpt.get();
            customer.pilihFilm(film.getJudul());
            customerRepository.save(customer);
            return "Film berhasil dipilih";
        } else {
            return "Customer atau Film tidak ditemukan";
        }
    }

    @PostMapping("/{idCustomer}/bayar")
    public String bayar(@PathVariable String idCustomer, @RequestBody Transaksi transaksi) {
        Optional<Customer> customerOpt = customerRepository.findById(idCustomer);
        if (customerOpt.isPresent()) {
            transaksiRepository.save(transaksi);
            Customer customer = customerOpt.get();
            customer.bayar(transaksi);
            customerRepository.save(customer);
            return transaksi.generateQR();
        } else {
            return "Customer tidak ditemukan";
        }
    }
}
