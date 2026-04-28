package com.richard.meetup.management.participant.entity;

import com.richard.meetup.management.shared.entity.BaseEntity;
import com.richard.meetup.management.user.entity.User;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.UUID;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "tb_participants")
public class Participant extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String linkedin;

    @OneToOne
    @JoinColumn(name = "id_user")
    private User user;

}
