package com.brewandreview.controller;

import com.brewandreview.model.Cafe;
import com.brewandreview.repository.CafeRepository;
import com.brewandreview.repository.ReviewRepository; // YENİ EKLENDİ
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
public class CafeController {

    @Autowired
    private CafeRepository cafeRepository;

    @Autowired
    private ReviewRepository reviewRepository; // YENİ EKLENDİ (Yorumları çekmek için)

    // Kafe Listeleme Sayfası
    @GetMapping("/cafes")
    public String listCafes(@RequestParam(required = false) String city,
            @RequestParam(required = false) boolean dessert,
            Model model) {

        List<Cafe> cafes;

        if (city != null && !city.isEmpty()) {
            cafes = cafeRepository.findByCityContainingIgnoreCase(city);
        } else if (dessert) {
            cafes = cafeRepository.findByHasDessertTrue();
        } else {
            cafes = cafeRepository.findAll();
        }

        model.addAttribute("cafes", cafes);
        return "cafes";
    }

    // Kafe Detay Sayfası (GÜNCELLENDİ)
    @GetMapping("/cafe/{id}")
    public String getCafeDetails(@PathVariable Long id, Model model) {
        // 1. Kafeyi Bul
        Cafe cafe = cafeRepository.findById(id).orElse(null);

        if (cafe != null) {
            model.addAttribute("cafe", cafe);

            // 2. BU KAFEYE AİT YORUMLARI BUL VE SAYFAYA GÖNDER (İsteğin burasıydı)
            model.addAttribute("reviews", reviewRepository.findByCafe_CafeId(id));

            return "cafe-detail";
        } else {
            return "redirect:/cafes";
        }
    }
}