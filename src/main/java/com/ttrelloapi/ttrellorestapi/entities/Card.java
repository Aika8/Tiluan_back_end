package com.ttrelloapi.ttrellorestapi.entities;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIdentityReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.*;


import javax.persistence.*;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.Table;

import java.util.Collection;
import java.util.Collections;
import java.util.List;


@Entity
@Table(name = "cards")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Card{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    private String name;

    private String addedDate;

    @OneToMany(
            cascade = {CascadeType.REMOVE}, mappedBy = "card")
    @LazyCollection(LazyCollectionOption.FALSE)
    @JsonManagedReference
    private List<CardTasks> tasks;

    @ManyToOne(fetch = FetchType.EAGER, cascade = {})
    @OnDelete(action = OnDeleteAction.CASCADE)
    private Board board;
}
