package com.pbo.demo;

import jakarta.persistence.*;
import java.math.BigDecimal;

@Entity
@Table(name = "aritmatika")
public class Aritmatika {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "bil1", nullable = false)
    private Double bilangan1;

    @Column(name = "opt", nullable = false, length = 1)
    private String operator;

    @Column(name = "bil2", nullable = false)
    private Double bilangan2;

    // --- Getter dan Setter ---

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public Double getBilangan1() { return bilangan1; }
    public void setBilangan1(Double bilangan1) { this.bilangan1 = bilangan1; }

    public String getOperator() { return operator; }
    public void setOperator(String operator) { this.operator = operator; }

    public Double getBilangan2() { return bilangan2; }
    public void setBilangan2(Double bilangan2) { this.bilangan2 = bilangan2; }

    // Untuk tampilan: 4.0 ditampilkan sebagai 4
    public String getBilangan1Teks() { return rapi(bilangan1); }
    public String getBilangan2Teks() { return rapi(bilangan2); }

    private static String rapi(Double angka) {
        if (angka == null) return "";
        return BigDecimal.valueOf(angka).stripTrailingZeros().toPlainString();
    }
}
