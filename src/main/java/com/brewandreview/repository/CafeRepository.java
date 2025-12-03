package com.brewandreview.repository;

import com.brewandreview.model.Cafe;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface CafeRepository extends JpaRepository<Cafe, Long> {

    //Şehir Arama 
    List<Cafe> findByCityContainingIgnoreCase(String city, Sort sort);

    //İsim Arama 
    List<Cafe> findByNameContainingIgnoreCase(String name, Sort sort);

    //Tatlısı Olanları Getir 
    List<Cafe> findByHasDessertTrue(Sort sort);

    //Şehir Listesi
    @Query("SELECT DISTINCT c.city FROM Cafe c ORDER BY c.city")
    List<String> findDistinctCities();

    // Kafe Ruhsat Numarası Kontrolü (Yeni kafe eklerken çakışma yaratmamak icin)
    Cafe findByLicenseNumber(String licenseNumber);

    // Bir kafede en çok yorumlanan/seçilen 3 ürünü bulur.
    // (Review -> Review_Consumed_Items -> Menu_Item tablolarını birleştirir)
    @Query(value = "SELECT m.name, COUNT(rci.menu_id) as count " +
            "FROM Review r " +
            "JOIN Review_Consumed_Items rci ON r.review_id = rci.review_id " +
            "JOIN Menu_Item m ON rci.menu_id = m.menu_id " +
            "WHERE r.cafe_id = :cafeId " +
            "GROUP BY m.name " +
            "ORDER BY count DESC LIMIT 3", nativeQuery = true)
    List<Object[]> findTopProductsByCafeId(@Param("cafeId") Long cafeId);
}