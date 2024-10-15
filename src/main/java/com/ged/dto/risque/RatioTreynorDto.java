package com.ged.dto.risque;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.time.LocalDateTime;

@JsonIgnoreProperties(ignoreUnknown = true)
public class RatioTreynorDto {
    private double rtp;

    public RatioTreynorDto() {
    }

    public double getRtp() {
        return rtp;
    }

    public void setRtp(double rtp) {
        this.rtp = rtp;
    }
}
