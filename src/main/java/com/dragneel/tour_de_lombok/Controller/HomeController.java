package com.dragneel.tour_de_lombok.Controller;

import com.dragneel.tour_de_lombok.Service.HomeService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/home")
public class HomeController {

    private static final Logger logger = LoggerFactory.getLogger(HomeController.class);

    @Autowired
    private HomeService homeService;

    @GetMapping("")
    public String home(Model model) {
        logger.info("Mengakses halaman utama Tour de Lombok");
        model.addAttribute("msg", "WELCOME TO LOMBOK WITH ME");
        return "index";
    }
}