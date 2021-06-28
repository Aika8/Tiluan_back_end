package com.ttrelloapi.ttrellorestapi.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;

@Entity
@Table(name = "boards")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Board{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    private String name;

    private String addedDate;

    @ManyToOne(fetch = FetchType.EAGER, cascade={})
    @OnDelete(action = OnDeleteAction.CASCADE)
    private Users user;

}
