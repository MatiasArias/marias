package org.mobydigital.marias.testbackenddeveloper.model.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.Getter;
import lombok.Data;


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
