package com.bodyhealth.model;

import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Data
@Table(name = "rutina_ejercicio")
public class RutinaEjercicio implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id_rutina_ejercicio;

    @ManyToOne
    @JoinColumn(name = "id_rutina")
    private Rutina id_rutina;

    @ManyToOne
    @JoinColumn(name = "id_ejercicio")
    private Ejercicio id_ejercicio;
}
