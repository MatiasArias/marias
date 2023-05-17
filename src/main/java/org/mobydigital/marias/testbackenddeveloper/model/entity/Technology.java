package org.mobydigital.marias.testbackenddeveloper.model.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name="technology")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Technology {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idTechnology;
    private String name;
    private String version;
}
