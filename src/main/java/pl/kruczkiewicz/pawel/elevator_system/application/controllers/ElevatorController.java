package pl.kruczkiewicz.pawel.elevator_system.application.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class ElevatorController {

    @GetMapping("/elevators/index")
    public String greeting(@RequestParam(name="name", required = false, defaultValue = "World") String name, Model model){
        model.addAttribute("name", name);
        return "greeting";
    }
}
