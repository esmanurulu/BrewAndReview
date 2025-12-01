package com.brewandreview.repository;

import com.brewandreview.model.Visit;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface VisitRepository extends JpaRepository<Visit, Long> {
    // Bir kullanıcının ziyaret geçmişini bulmak için (İleride lazım olacak)
    List<Visit> findByUser_UserId(Long userId);

    // Bir kafenin ziyaretçi sayısını bulmak için
    List<Visit> findByCafe_CafeId(Long cafeId);
}