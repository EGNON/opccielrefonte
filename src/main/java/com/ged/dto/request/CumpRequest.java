package com.ged.dto.request;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.*;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class CumpRequest {
    private Long idOpcvm;
    private Long idActionnaire;
    private LocalDateTime dateEstimation;
}
