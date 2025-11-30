package com.brewandreview.controller;

import com.brewandreview.model.*;
import com.brewandreview.repository.*;

import jakarta.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.sql.Timestamp;
import java.time.Instant;
import java.util.List; // <-- İŞTE EKSİK OLAN BU SATIRDI!

@Controller
public class ReviewController {

    @Autowired
    private ReviewRepository reviewRepository;
    @Autowired
    private CafeRepository cafeRepository;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private EmployeeRepository employeeRepository;
    @Autowired
    private MenuItemRepository menuItemRepository;

    // 1. Yorum Formunu Göster
    @GetMapping("/cafe/{cafeId}/review")
    public String showReviewForm(@PathVariable Long cafeId, Model model) {
        Cafe cafe = cafeRepository.findById(cafeId).orElse(null);
        if (cafe == null)
            return "redirect:/cafes";

        model.addAttribute("cafe", cafe);
        return "review-form"; // review-form.html sayfasına git
    }

    // 2. Yorumu Kaydet (POST)
    @PostMapping("/cafe/{cafeId}/review")
    public String submitReview(@PathVariable Long cafeId,
            @RequestParam(required = false) List<Long> consumedItems, // Çoklu seçim
            @RequestParam Double rating,
            @RequestParam String comment,
            HttpSession session) { // Kullanıcıyı buradan alacağız

        // 1. Oturumdaki kullanıcıyı al
        User currentUser = (User) session.getAttribute("currentUser");
        if (currentUser == null)
            return "redirect:/"; // Giriş yapmamışsa at

        Cafe cafe = cafeRepository.findById(cafeId).get();

        Review review = new Review();
        review.setUser(currentUser); // Otomatik atandı!
        review.setCafe(cafe);
        review.setRatingOverall(BigDecimal.valueOf(rating));
        review.setComment(comment);
        review.setReviewDate(Timestamp.from(Instant.now()));
        review.setReviewType("cafe"); // Varsayılan cafe yorumu

        // 2. Tüketilen Ürünleri Ekle
        if (consumedItems != null && !consumedItems.isEmpty()) {
            List<MenuItem> items = menuItemRepository.findAllById(consumedItems);
            review.setConsumedItems(items);
        }

        reviewRepository.save(review);

        // 3. Kafenin Puanını Güncelle (Basit Ortalama)
        updateCafeRating(cafe);

        return "redirect:/cafe/" + cafeId;
    }

    private void updateCafeRating(Cafe cafe) {
        List<Review> reviews = reviewRepository.findByCafe_CafeId(cafe.getCafeId());
        double sum = 0;
        for (Review r : reviews) {
            sum += r.getRatingOverall().doubleValue();
        }
        if (!reviews.isEmpty()) {
            double average = sum / reviews.size();
            // Virgülden sonra 2 basamak
            cafe.setTotalRating(BigDecimal.valueOf(average).setScale(2, RoundingMode.HALF_UP));
            cafeRepository.save(cafe);
        }
    }
}