package com.hbox.medicalmanagementsystem.entity;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
public class Doctor {

   @Id
   @GeneratedValue(strategy = GenerationType.IDENTITY)
   private Long doctorId;

   private String firstName;
   private String lastName;
   private String speciality;
   @ManyToOne
   @JoinColumn(name = "id")
   private Clinic clinic;

}
