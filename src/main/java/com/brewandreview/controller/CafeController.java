package com.brewandreview.controller;

import com.brewandreview.model.Cafe;
import com.brewandreview.repository.CafeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
public class CafeController {

    @Autowired
    private CafeRepository cafeRepository;

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
}