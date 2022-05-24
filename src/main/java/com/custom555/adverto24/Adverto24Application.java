package com.custom555.adverto24;

import com.custom555.adverto24.Advertisment.Advertisment;
import com.custom555.adverto24.Advertisment.AdvertismentRepository;
import com.custom555.adverto24.Category.Category;
import com.custom555.adverto24.Category.CategoryRepository;
import com.custom555.adverto24.Conversation.Conversation;
import com.custom555.adverto24.Conversation.ConversationRepository;
import com.custom555.adverto24.Message.Message;
import com.custom555.adverto24.Message.MessageRepository;
import com.custom555.adverto24.User.User;
import com.custom555.adverto24.User.UserRepository;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

import java.time.LocalDateTime;

@SpringBootApplication
public class Adverto24Application {

    public static void main(String[] args) {
//        SpringApplication.run(Adverto24Application.class, args);

        ConfigurableApplicationContext context = SpringApplication.run(Adverto24Application.class, args);
        UserRepository userRepository = context.getBean(UserRepository.class);
        AdvertismentRepository advertismentRepository = context.getBean(AdvertismentRepository.class);
        CategoryRepository categoryRepository = context.getBean(CategoryRepository.class);
        ConversationRepository conversationRepository = context.getBean(ConversationRepository.class);
        MessageRepository messageRepository = context.getBean(MessageRepository.class);

        User user1 = new User("Jan","Kowalski","jan.kowalski@gmail.com");
        User user2 = new User("Paweł","Nowak","paweł.nowak@gmail.com");
        User user3 = new User("Robert","Mazur","robert.mazur@gmail.com");

        userRepository.save(user1);
        userRepository.save(user2);
        userRepository.save(user3);

        Category cars = new Category("Cars");
        categoryRepository.save(cars);

        Advertisment polonezAd = new Advertisment("Polonez","Sprzedam poloneza", LocalDateTime.now(),user1,cars);
        advertismentRepository.save(polonezAd);

        // user2 zadaje pytanie do polonezAd (user1)
        // find conversationByAdId if null create new Conversation
        Conversation conversation1 = new Conversation(polonezAd,polonezAd.getOwner(),user2);
        conversationRepository.save(conversation1);
        Message message1 = new Message("Tu user 2, chciałem kupić poloneza",conversation1,user2);
        messageRepository.save(message1);
        //user 1 odpowiada
        Message message2 = new Message("Tu user 1, spoko mogę sprzedać",conversation1,user1);
        messageRepository.save(message2);

        // user 3 zadaje pytanie
        Conversation conversation2 = new Conversation(polonezAd,polonezAd.getOwner(),user3);
        conversationRepository.save(conversation2);
        Message message3 = new Message("Tu user 3, chciałem kupić poloneza",conversation2,user2);
        messageRepository.save(message3);
        //user 1 odpowiada
        Message message4 = new Message("Tu user 1, spoko mogę sprzedać",conversation2,user1);
        messageRepository.save(message4);







    }

}
