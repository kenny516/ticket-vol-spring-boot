package com.ticketvol.ticketVol.controller;

import com.ticketvol.ticketVol.DTO.VolDTO;
import com.ticketvol.ticketVol.model.Vol;
import com.ticketvol.ticketVol.service.VolService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/vol")
@AllArgsConstructor
public class VolController {
    private final VolService volService;

//    @GetMapping("")
//    public String listVolValid(Model model) {
//        List<VolDTO> volDTOS = volService.findAllVolDTOValid();
//        model.addAttribute("volDTOS", volDTOS);
//        return "vol/listVolValid";
//    }
}
