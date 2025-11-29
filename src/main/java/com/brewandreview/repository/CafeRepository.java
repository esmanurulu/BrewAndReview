package com.brewandreview.repository;

import com.brewandreview.model.Cafe;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface CafeRepository extends JpaRepository<Cafe, Long> {
    List<Cafe> findByCityContainingIgnoreCase(String city);

    List<Cafe> findByHasDessertTrue();
}