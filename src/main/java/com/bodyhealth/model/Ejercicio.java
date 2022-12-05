package com.bodyhealth.model;

import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;
@Entity
@Data
@Table(name = "ejercicio")
public class Ejercicio implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id_ejercicio;

    @ManyToOne
    @JoinColumn(name = "id_musculo")
    private Musculo id_musculo;

    private String descripcion;
    private String series;

    private int repeticiones;


    private String url_video;
}
