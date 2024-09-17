package com.ged.dto.standard;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class EnvoiMailDto {
    private PersonneDto personneDto;
    private MailDto mailDto;

    public EnvoiMailDto() {
    }

    public EnvoiMailDto(PersonneDto personneDto, MailDto mailDto) {

        this.personneDto = personneDto;
        this.mailDto = mailDto;
    }

    public PersonneDto getPersonneDto() {
        return personneDto;
    }

    public void setPersonneDto(PersonneDto personneDto) {
        this.personneDto = personneDto;
    }

    public MailDto getMailDto() {
        return mailDto;
    }

    public void setMailDto(MailDto mailDto) {
        this.mailDto = mailDto;
    }
}
