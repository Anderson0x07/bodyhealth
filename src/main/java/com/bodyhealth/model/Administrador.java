package com.bodyhealth.model;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Entity
@Data
@DiscriminatorValue(value="ADMIN")
public class Administrador extends Usuario implements Serializable{


}
