package com.brewandreview.repository;

import com.brewandreview.model.Review;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface ReviewRepository extends JpaRepository<Review, Long> {

    // Bir kafeye ait tüm yorumları getir (cafe_id üzerinden)
    List<Review> findByCafe_CafeId(Long cafeId);

    // Bir çalışana ait yorumları getir
    List<Review> findByEmployee_EmployeeId(Long employeeId);

    // Bir menü öğesine ait yorumları getir
    List<Review> findByMenuItem_MenuId(Long menuId);
}