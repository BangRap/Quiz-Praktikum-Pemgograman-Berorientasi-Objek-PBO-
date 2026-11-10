package com.pbo.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class AritmatikaController {

    @Autowired
    private AritmatikaRepository aritmatikaRepository;

    // Menampilkan halaman utama (Form dan Tabel dalam 1 halaman)
    @GetMapping("/aritmatika")
    public String tampilkanHalaman(Model model) {
        model.addAttribute("operator", "+");
        model.addAttribute("listAritmatika", aritmatikaRepository.findAll(Sort.by("id")));
        return "Aritmatika";
    }

    // Mengisi form dengan data yang mau diedit
    @GetMapping("/aritmatika/edit/{id}")
    public String edit(@PathVariable Long id, Model model) {
        Aritmatika data = aritmatikaRepository.findById(id).orElse(null);
        if (data == null) {
            return "redirect:/aritmatika";
        }
        model.addAttribute("id", data.getId());
        model.addAttribute("bilangan1", data.getBilangan1Teks());
        model.addAttribute("operator", data.getOperator());
        model.addAttribute("bilangan2", data.getBilangan2Teks());
        model.addAttribute("listAritmatika", aritmatikaRepository.findAll(Sort.by("id")));
        return "Aritmatika";
    }

    // Menyimpan data baru (id kosong) atau mengubah data lama (id terisi)
    @PostMapping("/aritmatika/simpan")
    public String simpan(@RequestParam(required = false) Long id,
                         @RequestParam String bilangan1,
                         @RequestParam String operator,
                         @RequestParam String bilangan2,
                         Model model) {

        String pesan = null;
        Double angka1 = null;
        Double angka2 = null;

        // Validasi: bilangan harus angka, tidak boleh huruf
        if (!validasiAngka(bilangan1) || !validasiAngka(bilangan2)) {
            pesan = "Bilangan harus berupa angka, tidak boleh huruf.";
        } else {
            angka1 = Double.valueOf(bilangan1.trim());
            angka2 = Double.valueOf(bilangan2.trim());
            if (!operator.matches("[+\\-*/]")) {
                pesan = "Operator tidak valid.";
            } else if (operator.equals("/") && angka2 == 0) {
                pesan = "Tidak bisa dibagi dengan nol.";
            }
        }

        // Kalau ada kesalahan, tampilkan lagi halaman beserta pesannya
        if (pesan != null) {
            model.addAttribute("pesan", pesan);
            model.addAttribute("id", id);
            model.addAttribute("bilangan1", bilangan1);
            model.addAttribute("operator", operator);
            model.addAttribute("bilangan2", bilangan2);
            model.addAttribute("listAritmatika", aritmatikaRepository.findAll(Sort.by("id")));
            return "Aritmatika";
        }

        Aritmatika data = new Aritmatika();
        if (id != null) {
            data = aritmatikaRepository.findById(id).orElse(new Aritmatika());
        }
        data.setBilangan1(angka1);
        data.setOperator(operator);
        data.setBilangan2(angka2);
        aritmatikaRepository.save(data);
        return "redirect:/aritmatika";
    }

    @GetMapping("/aritmatika/hapus/{id}")
    public String hapus(@PathVariable Long id) {
        aritmatikaRepository.deleteById(id);
        return "redirect:/aritmatika";
    }

    // Hanya angka (boleh minus dan desimal dengan titik)
    private boolean validasiAngka(String teks) {
        return teks != null && teks.trim().matches("-?[0-9]{1,10}([.][0-9]{1,4})?");
    }
}
