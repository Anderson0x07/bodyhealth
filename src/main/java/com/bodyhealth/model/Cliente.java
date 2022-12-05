package com.bodyhealth.model;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Date;

@Entity
@Data
@DiscriminatorValue(value="CLIENTE")
public class Cliente extends Usuario implements Serializable {

    private String foto;

    private String jornada;

    private String comentario;

    private boolean estado;
}
