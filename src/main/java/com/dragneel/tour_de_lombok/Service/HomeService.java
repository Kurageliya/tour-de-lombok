package com.dragneel.tour_de_lombok.Service;

import org.springframework.stereotype.Service;

@Service
public class HomeService {

    public String getWelcomeMessagae (){
        return "WELCOME TO LOMBOK WITH ME";
    }
}
