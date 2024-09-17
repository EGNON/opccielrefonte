package com.ged.datatable;

import java.util.ArrayList;
import java.util.List;

public class Search {
    Boolean regex = false;
    String value = "";
    List<String> fixed = new ArrayList<>();

    public Boolean getRegex() {
        return regex;
    }

    public void setRegex(Boolean regex) {
        this.regex = regex;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public List<String> getFixed() {
        return fixed;
    }

    public void setFixed(List<String> fixed) {
        this.fixed = fixed;
    }

    @Override
    public String toString() {
        return "Search{" +
                "regex=" + regex +
                ", value='" + value + '\'' +
                '}';
    }
}
