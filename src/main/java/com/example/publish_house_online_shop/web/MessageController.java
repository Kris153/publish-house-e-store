package com.example.publish_house_online_shop.web;

import com.example.publish_house_online_shop.model.dtos.AddMessageDTO;
import com.example.publish_house_online_shop.model.dtos.MessageDetailsDTO;
import com.example.publish_house_online_shop.model.enums.UserRoleEnum;
import com.example.publish_house_online_shop.service.MessageService;
import com.example.publish_house_online_shop.service.UserService;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
public class MessageController {
    private final MessageService messageService;
    private final UserService userService;

    public MessageController(MessageService messageService, UserService userService) {
        this.messageService = messageService;
        this.userService = userService;
    }

    @GetMapping("/messages")
    public String viewMessages(Model model){
        if(this.userService.getCurrentUser().get().getRoles().get(0).getRole().equals(UserRoleEnum.USER)){
            return "redirect:/";
        }
        model.addAttribute("messages", this.messageService.getAllMessages());
        return "messages";
    }
    @GetMapping("/messages/{id}")
    public String viewMessage(@PathVariable("id") Integer messageId, Model model){
        if(this.userService.getCurrentUser().get().getRoles().get(0).getRole().equals(UserRoleEnum.USER)){
            return "redirect:/";
        }
        MessageDetailsDTO message = this.messageService.getMessageById(messageId);
        model.addAttribute("message", message);
        model.addAttribute("isStatusSeen", message.getStatus().equals("SEEN"));
        return "message";
    }
    @PostMapping("/add-message")
    public String addMessage(@Valid AddMessageDTO messageData, BindingResult bindingResult, RedirectAttributes redirectAttributes){
        if(bindingResult.hasErrors()){
            redirectAttributes.addFlashAttribute("messageData", messageData);
            redirectAttributes.addFlashAttribute("org.springframework.validation.BindingResult.messageData", bindingResult);
            return "redirect:/contact";
        }
        redirectAttributes.addFlashAttribute("successfulAddMessage", true);
        this.messageService.addMessage(messageData);
        return "redirect:/";
    }
    @PatchMapping("/messages/change-status/{id}")
    public String changeMessageStatusToSeen(@PathVariable("id") Integer messageId){
        this.messageService.changeMessageStatusToSeenById(messageId);
        return "redirect:/messages/{id}";
    }
}
