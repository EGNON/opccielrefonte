package com.ged.dto.request;

import com.ged.datatable.DatatableParameters;
import lombok.*;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class TransfertPartListeRequest {
    private Long idOpcvm;
    private Long idSeance;
    private DatatableParameters datatableParameters;
}
