package com.ged.datatable;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.List;

public class DataTablesResponse<T> {
    private Integer draw = 0;
    private Integer recordsTotal = 0;
    private Integer recordsFiltered = 0;
    @JsonIgnoreProperties("costData")
    List<T> data;
    public DataTablesResponse(){}

    public Integer getDraw() {
        return draw;
    }

    public void setDraw(Integer draw) {
        this.draw = draw;
    }

    public Integer getRecordsTotal() {
        return recordsTotal;
    }

    public void setRecordsTotal(Integer recordsTotal) {
        this.recordsTotal = recordsTotal;
    }

    public Integer getRecordsFiltered() {
        return recordsFiltered;
    }

    public void setRecordsFiltered(Integer recordsFiltered) {
        this.recordsFiltered = recordsFiltered;
    }

    public List<T> getData() {
        return data;
    }

    public void setData(List<T> data) {
        this.data = data;
    }
}
