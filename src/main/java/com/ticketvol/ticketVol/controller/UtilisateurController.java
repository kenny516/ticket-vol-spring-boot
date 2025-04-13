package com.ticketvol.ticketVol.controller;

import com.ticketvol.ticketVol.model.Utilisateur;
import com.ticketvol.ticketVol.service.UtilisateurService;
import jakarta.servlet.http.HttpSession;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@AllArgsConstructor
@Controller
public class UtilisateurController {
    private final UtilisateurService utilisateurService;

    @PostMapping("/login")
    public String login(HttpSession session, Model model, @RequestParam(name = "pseudo") String pseudo, @RequestParam(name = "motDePasse") String password) {
        Utilisateur utilisateur = utilisateurService.login(pseudo,password);
        if (utilisateur != null) {
            session.setAttribute("utilisateur", utilisateur);
        }
        else {
            model.addAttribute("loginError", "Invalid username or password");
            return "index";
        }
        return "redirect:/parametres/";
    }

    @GetMapping("/login")
    public String loginFOrm(){
        return "index";
    }


}
