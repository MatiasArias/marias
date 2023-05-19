package org.mobydigital.marias.testbackenddeveloper.model.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
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
    @NotBlank(message = "Name is mandatory")
    private String name;
    @NotBlank(message = "Version is mandatory")
    private String version;
}
