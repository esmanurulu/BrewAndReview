package com.brewandreview.controller;

import com.brewandreview.model.User;
import com.brewandreview.repository.ReviewRepository;
import com.brewandreview.repository.VisitRepository;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ProfileController {

    @Autowired
    private ReviewRepository reviewRepository;

    @Autowired
    private VisitRepository visitRepository;

    @GetMapping("/profile")
    public String showProfile(HttpSession session, Model model) {
        // Oturumdaki kullanıcıyı al
        User currentUser = (User) session.getAttribute("currentUser");
        if (currentUser == null) {
            return "redirect:/";
        }

        // Kullanıcı bilgisini sayfaya gönder
        model.addAttribute("user", currentUser);

        // İstatistikler (Bonus Özellik: Kaç yorum yaptı? Kaç yere gitti?)
        // Bu metodları Repository'lere eklememiş olabiliriz, o yüzden şimdilik basit
        // tutalım
        // İleride: model.addAttribute("visitCount",
        // visitRepository.countByUser(currentUser));

        return "profile"; // profile.html sayfasına git
    }
}