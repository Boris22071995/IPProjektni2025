package com.etfbl.ip.BackendApp.repository;

import com.etfbl.ip.BackendApp.model.Cv;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CvRepository extends JpaRepository<Cv, Integer> {
}
