package com.ged.dto;

import java.util.List;

public class RequestDto {
    private List<SearchRequestDto> searchRequestDto;
    private GlobalOperator globalOperator;
    private PageRequestDto pageDto;

    public enum GlobalOperator {
        AND, OR
    }

    public List<SearchRequestDto> getSearchRequestDto() {
        return searchRequestDto;
    }

    public void setSearchRequestDto(List<SearchRequestDto> searchRequestDto) {
        this.searchRequestDto = searchRequestDto;
    }

    public GlobalOperator getGlobalOperator() {
        return globalOperator;
    }

    public void setGlobalOperator(GlobalOperator globalOperator) {
        this.globalOperator = globalOperator;
    }

    public PageRequestDto getPageDto() {
        return pageDto;
    }

    public void setPageDto(PageRequestDto pageDto) {
        this.pageDto = pageDto;
    }
}
