package com.main.entites;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Entity
@Table(name="admins")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Admin {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer adminId;

    @Column(name="user_id")
    private Integer userId;
    @OneToOne
    @JoinColumn(name = "user_id", referencedColumnName = "userId", unique = true,insertable = false,updatable = false)
    private User user;

    private String adminName;
    private Boolean status;

}
