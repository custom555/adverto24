package com.custom555.adverto24.Conversation;

import com.custom555.adverto24.Advertisment.Advertisment;
import com.custom555.adverto24.User.User;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

import javax.persistence.*;

@Entity
@Data
@NoArgsConstructor
@RequiredArgsConstructor
public class Conversation {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne
    @NonNull
    @JoinColumn(name = "advertisment_id")
    private Advertisment advertisment;
    @ManyToOne
    @NonNull
    @JoinColumn(name = "owner_id")
    private User owner;
    @ManyToOne
    @NonNull
    @JoinColumn(name = "asker_id")
    private User asker;
}
