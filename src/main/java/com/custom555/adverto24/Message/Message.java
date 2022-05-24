package com.custom555.adverto24.Message;

import com.custom555.adverto24.Conversation.Conversation;
import com.custom555.adverto24.User.User;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.hibernate.annotations.Cascade;

import javax.persistence.*;

@Entity
@Data
@NoArgsConstructor
@RequiredArgsConstructor
public class Message {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NonNull
    private String body;
    @ManyToOne
    @Cascade(org.hibernate.annotations.CascadeType.MERGE)
    @NonNull
    private Conversation conversation;
    @ManyToOne
    @NonNull
    private User sender;
}
