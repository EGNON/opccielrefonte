package com.ged.datatable;

public class Column {
    String data = "";
    String name = "";
    Boolean orderable = false;
    Search search = new Search();
    Boolean searchable = false;

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Boolean getOrderable() {
        return orderable;
    }

    public void setOrderable(Boolean orderable) {
        this.orderable = orderable;
    }

    public Search getSearch() {
        return search;
    }

    public void setSearch(Search search) {
        this.search = search;
    }

    public Boolean getSearchable() {
        return searchable;
    }

    public void setSearchable(Boolean searchable) {
        this.searchable = searchable;
    }

    @Override
    public String toString() {
        return "Column{" +
                "data='" + data + '\'' +
                ", name='" + name + '\'' +
                ", orderable=" + orderable +
                ", search=" + search +
                ", searchable=" + searchable +
                '}';
    }
}
