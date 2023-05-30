package org.mobydigital.marias.testbackenddeveloper.model.view;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class TechnologyDto {
    private String name;
    private String version;
}
