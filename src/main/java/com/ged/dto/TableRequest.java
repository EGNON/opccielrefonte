package com.ged.dto;

import com.ged.datatable.DatatableParameters;
import com.ged.dto.lab.reportings.BeginEndDateParameter;

public class TableRequest {
    DatatableParameters datatableParameters;
    BeginEndDateParameter beginEndDateParameter;

    public DatatableParameters getDatatableParameters() {
        return datatableParameters;
    }

    public void setDatatableParameters(DatatableParameters datatableParameters) {
        this.datatableParameters = datatableParameters;
    }

    public BeginEndDateParameter getBeginEndDateParameter() {
        return beginEndDateParameter;
    }

    public void setBeginEndDateParameter(BeginEndDateParameter beginEndDateParameter) {
        this.beginEndDateParameter = beginEndDateParameter;
    }
}
