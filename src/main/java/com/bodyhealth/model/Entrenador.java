package com.bodyhealth.model;

import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;


import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;


@Entity
@Data
@DiscriminatorValue(value="TRAINER")
public class Entrenador extends Usuario implements Serializable {

    private String foto;

    private String experiencia;

    private String hoja_vida;

    private String titulo_academico;

    private String jornada;

    private boolean estado;



    /*@ManyToOne
    @JoinColumn(name="id")
    private Usuario id_admin;*/



}