package com.ged.datatable;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.ArrayList;
import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public class DatatableParameters {
    List<Column> columns = new ArrayList<>();
    Integer draw = 0;
    Integer start = 0;
    Integer length = 0;
    List<Order> order = new ArrayList<>();
    Search search = new Search();

    public List<Column> getColumns() {
        return columns;
    }

    public void setColumns(List<Column> columns) {
        this.columns = columns;
    }

    public Integer getDraw() {
        return draw;
    }

    public void setDraw(Integer draw) {
        this.draw = draw;
    }

    public Integer getStart() {
        return start;
    }

    public void setStart(Integer start) {
        this.start = start;
    }

    public Integer getLength() {
        return length;
    }

    public void setLength(Integer length) {
        this.length = length;
    }

    public List<Order> getOrder() {
        return order;
    }

    public void setOrder(List<Order> order) {
        this.order = order;
    }

    public Search getSearch() {
        return search;
    }

    public void setSearch(Search search) {
        this.search = search;
    }

    @Override
    public String toString() {
        return "DatatableParameters{" +
                "columns=" + columns +
                ", draw=" + draw +
                ", start=" + start +
                ", length=" + length +
                ", order=" + order +
                ", search=" + search +
                '}';
    }
}

