package com.example.publish_house_online_shop.service.impl;

import com.example.publish_house_online_shop.model.dtos.AddMessageDTO;
import com.example.publish_house_online_shop.model.dtos.MessageDetailsDTO;
import com.example.publish_house_online_shop.model.entities.MessageEntity;
import com.example.publish_house_online_shop.model.enums.MessageStatusEnum;
import com.example.publish_house_online_shop.repository.MessageRepository;
import com.example.publish_house_online_shop.service.MessageService;
import com.example.publish_house_online_shop.service.UserService;
import com.example.publish_house_online_shop.service.exception.BadRequestException;
import com.example.publish_house_online_shop.service.exception.ObjectNotFoundException;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class MessageServiceImpl implements MessageService {
    private final MessageRepository messageRepository;
    private final UserService userService;
    private final ModelMapper modelMapper;

    public MessageServiceImpl(MessageRepository messageRepository, UserService userService, ModelMapper modelMapper) {
        this.messageRepository = messageRepository;
        this.userService = userService;
        this.modelMapper = modelMapper;
    }

    @Override
    public void addMessage(AddMessageDTO addMessageDTO) {
        MessageEntity message = this.modelMapper.map(addMessageDTO, MessageEntity.class);
        message.setStatus(MessageStatusEnum.NOT_SEEN);
        message.setUser(this.userService.getCurrentUser());
        this.messageRepository.saveAndFlush(message);
    }
    @Override
    public List<MessageDetailsDTO> getAllMessages() {
        return this.messageRepository.findAll().stream().map(MessageServiceImpl::map).collect(Collectors.toList());
    }

    @Override
    public MessageDetailsDTO getMessageById(Integer messageId) {
        return this.messageRepository.findById(messageId).map(MessageServiceImpl::map).orElseThrow(ObjectNotFoundException::new);
    }

    @Override
    public void changeMessageStatusToSeenById(Integer messageId) {
        Optional<MessageEntity> messageOpt = this.messageRepository.findById(messageId);
        if(messageOpt.isEmpty()){
            throw new BadRequestException();
        }
        MessageEntity message = messageOpt.get();
        message.setStatus(MessageStatusEnum.SEEN);
        this.messageRepository.saveAndFlush(message);
    }

    private static MessageDetailsDTO map(MessageEntity message){
        ModelMapper modelMapper = new ModelMapper();
        MessageDetailsDTO messageDetailsDTO = modelMapper.map(message, MessageDetailsDTO.class);
        messageDetailsDTO.setUsername(message.getUser().getUsername());
        messageDetailsDTO.setEmail(message.getUser().getEmail());
        return messageDetailsDTO;
    }
}
