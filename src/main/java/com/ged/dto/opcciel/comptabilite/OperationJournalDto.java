package com.ged.dto.opcciel.comptabilite;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class OperationJournalDto {

    private OperationDto operationDto;
    private JournalDto journal;

    public OperationJournalDto() {
    }

    public OperationDto getOperationDto() {
        return operationDto;
    }

    public void setOperationDto(OperationDto operationDto) {
        this.operationDto = operationDto;
    }

    public JournalDto getJournal() {
        return journal;
    }

    public void setJournal(JournalDto journal) {
        this.journal = journal;
    }
}
