package com.pbo.demo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AritmatikaRepository extends JpaRepository<Aritmatika, Long> {
    // Menyediakan method findAll(), save(), findById(), deleteById(), dll secara otomatis
}
