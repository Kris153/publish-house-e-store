package com.example.publish_house_online_shop.web;

import com.example.publish_house_online_shop.model.dtos.AddMessageDTO;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;

@Controller
public class ContactController {
    @ModelAttribute("messageData")
    private AddMessageDTO addAddMessageDTOToModel(){
        return new AddMessageDTO();
    }
    @GetMapping("/contact")
    public String viewContact(){
        return "contact";
    }
}
