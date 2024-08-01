package com.example.publish_house_online_shop.service;

import com.example.publish_house_online_shop.model.dtos.AddMessageDTO;
import com.example.publish_house_online_shop.model.dtos.MessageDetailsDTO;

import java.util.List;

public interface MessageService {
    void addMessage(AddMessageDTO addMessageDTO);
    List<MessageDetailsDTO> getAllMessages();

    MessageDetailsDTO getMessageById(Integer messageId);

    void changeMessageStatusToSeenById(Integer messageId);
}
