package com.timeflow.timeflow.repository;

import com.timeflow.timeflow.model.BancoHoras;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BancoHorasRepository extends JpaRepository<BancoHoras, Long> {
}
