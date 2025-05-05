package com.myorganisation.traceboard.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "invoice")
@Data
public class Invoice {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Double amount;

    //This is the non-owning (or inverse) side.
    @OneToOne(mappedBy = "invoice")
    @JsonIgnore
    private Ticket ticket;


}
