package com.brewandreview;

import com.brewandreview.model.User;
import com.brewandreview.repository.UserRepository;

import jakarta.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.Map;

@Controller
public class LoginController {

    @Autowired
    private UserRepository userRepository;

    // Ana Sayfa (Login Ekranı)
    @GetMapping("/")
    public String index() {
        return "index";
    }

    // Dashboard Sayfası (Giriş yaptıktan sonra gidilen yer)
    @GetMapping("/dashboard")
    public String dashboard(@RequestParam(name = "user", required = false, defaultValue = "Misafir") String user,
            Model model) {
        model.addAttribute("user", user);
        return "dashboard"; // dashboard.html dosyasını arar
    }

    @PostMapping("/login")
    @ResponseBody
    public Map<String, String> login(@RequestParam("username") String username,
            @RequestParam("password") String password,
            HttpSession session) { // Session parametresi eklendi

        Map<String, String> response = new HashMap<>();
        User dbUser = userRepository.findByUsername(username);

        if (dbUser != null && dbUser.getPasswordHash().trim().equals(password.trim())) {
            // KRİTİK NOKTA: Kullanıcıyı hafızaya atıyoruz
            session.setAttribute("currentUser", dbUser);

            response.put("status", "success");
            response.put("message", "Giriş Başarılı!");
        } else {
            response.put("status", "error");
            response.put("message", "Hatalı şifre!");
        }
        return response;
    }
}