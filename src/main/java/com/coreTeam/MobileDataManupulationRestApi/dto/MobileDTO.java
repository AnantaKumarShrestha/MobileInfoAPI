package com.coreTeam.MobileDataManupulationRestApi.dto;

import lombok.Data;

import java.time.LocalDate;

@Data
public class MobileDTO {

    private int id;
    private String companyName;
    private String modelName;
    private String price;
    private String imei;
    private LocalDate dateCreated;
    private LocalDate lastUpdate;

}
