package com.example.publish_house_online_shop.service.impl;

import com.example.publish_house_online_shop.model.dtos.AddMessageDTO;
import com.example.publish_house_online_shop.model.entities.MessageEntity;
import com.example.publish_house_online_shop.model.entities.UserEntity;
import com.example.publish_house_online_shop.model.enums.MessageStatusEnum;
import com.example.publish_house_online_shop.repository.MessageRepository;
import com.example.publish_house_online_shop.service.MessageService;
import com.example.publish_house_online_shop.service.UserService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.modelmapper.ModelMapper;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class MessageServiceImplTest {
    private MessageService toTest;
    private final Integer TEST_ID = 5;
    @Captor
    private ArgumentCaptor<MessageEntity> messageEntityCaptor;
    @Mock
    private MessageRepository mockMessageRepository;
    @Mock
    private UserService mockUserService;

    @BeforeEach
    void setUp(){
        toTest = new MessageServiceImpl(mockMessageRepository, mockUserService, new ModelMapper());
    }
    @Test
    void testAddMessage(){
        UserEntity testUser = new UserEntity(TEST_ID, "username", "email", "password", null, null);
        when(mockUserService.getCurrentUser()).thenReturn(testUser);

        toTest.addMessage(new AddMessageDTO("Test", "test"));

        verify(mockMessageRepository).saveAndFlush(messageEntityCaptor.capture());
        MessageEntity actualSavedMessage = messageEntityCaptor.getValue();
        Assertions.assertNotNull(actualSavedMessage);
        Assertions.assertEquals("test", actualSavedMessage.getText());
        Assertions.assertEquals("Test", actualSavedMessage.getTitle());
        Assertions.assertEquals(MessageStatusEnum.NOT_SEEN, actualSavedMessage.getStatus());
        Assertions.assertNotNull(actualSavedMessage.getUser());
        Assertions.assertEquals("username", actualSavedMessage.getUser().getUsername());
        Assertions.assertEquals("email", actualSavedMessage.getUser().getEmail());
        Assertions.assertEquals("password", actualSavedMessage.getUser().getPassword());
        Assertions.assertNull(actualSavedMessage.getUser().getRoles());
        Assertions.assertNull(actualSavedMessage.getUser().getOrders());
        Assertions.assertNull(actualSavedMessage.getUser().getCart());
    }
}
