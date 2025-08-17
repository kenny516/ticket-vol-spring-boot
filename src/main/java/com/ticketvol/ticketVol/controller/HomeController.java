package com.ticketvol.ticketVol.controller;

import com.ticketvol.ticketVol.model.Utilisateur;
import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {

    @GetMapping("/")
    public String home() {
        return "index";
    }

    @GetMapping("/test-reservation")
    public String testReservation(HttpSession session) {
        // Créer un utilisateur de test CLIENT pour la session
        Utilisateur testUser = new Utilisateur();
        testUser.setId(1);
        testUser.setNom("Test");
        testUser.setPrenom("User");
        testUser.setPseudo("testuser");
        testUser.setRole("client"); // Définir comme client
        session.setAttribute("utilisateur", testUser);

        return "redirect:/reservation";
    }

    @GetMapping("/test-admin")
    public String testAdmin(HttpSession session) {
        // Créer un utilisateur de test ADMIN pour la session
        Utilisateur testAdmin = new Utilisateur();
        testAdmin.setId(2);
        testAdmin.setNom("Admin");
        testAdmin.setPrenom("Test");
        testAdmin.setPseudo("testadmin");
        testAdmin.setRole("admin"); // Définir comme admin
        session.setAttribute("utilisateur", testAdmin);

        return "redirect:/";
    }

    @GetMapping("/logout-test")
    public String logoutTest(HttpSession session) {
        session.removeAttribute("utilisateur");
        return "redirect:/";
    }

    @GetMapping("/logout")
    public String logout(HttpSession session) {
        // Supprimer l'utilisateur de la session
        session.removeAttribute("utilisateur");
        // Invalider complètement la session pour plus de sécurité
        session.invalidate();
        return "redirect:/";
    }

    @GetMapping("/profile")
    public String profile(HttpSession session) {
        // Vérifier si l'utilisateur est connecté
        Utilisateur utilisateur = (Utilisateur) session.getAttribute("utilisateur");
        if (utilisateur == null) {
            return "redirect:/login";
        }
        return "profile"; // TODO: créer la page de profil
    }
}
