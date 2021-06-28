package com.ttrelloapi.ttrellorestapi.entities;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;

@Entity
@Table(name = "tasks")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class CardTasks{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;


  private String description;

  private String addedDate;

  private boolean done;

    //https://stackoverflow.com/a/43775434
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "card_id")
    @JsonBackReference
    private Card card;

}
