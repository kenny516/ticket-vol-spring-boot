package com.ticketvol.ticketVol.controller;

import com.ticketvol.ticketVol.model.Parametres;
import com.ticketvol.ticketVol.service.ParametresService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@AllArgsConstructor
@Controller
@RequestMapping("/parametres")
public class ParametresController {
    private final ParametresService parametresService;

    @GetMapping("/")
    public String getParametresRepository(Model model) {
        List<Parametres> parametres = parametresService.getAllParametres();
        model.addAttribute("parametres", parametres);
        return "parametres/list";
    }

    @GetMapping("/form_parametre/{cle}")
    public String formParametre(@PathVariable(name = "cle") String cle, Model model) {
        Parametres parametres = new Parametres();
        if (cle != null) {
            parametres = parametresService.getParametresByCle(cle);
        }
        model.addAttribute("parametres",parametres);
        return "parametres/form";
    }

    @PostMapping("/update")
    public String updateParametres(Parametres parametres) {
        parametresService.updateParametres(parametres);
        return "redirect:/parametres/";
    }



}
