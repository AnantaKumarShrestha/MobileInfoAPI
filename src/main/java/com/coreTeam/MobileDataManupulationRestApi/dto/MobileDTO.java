package com.coreTeam.MobileDataManupulationRestApi.dto;

import jakarta.persistence.Lob;
import lombok.Data;
import org.springframework.beans.factory.annotation.Value;

import java.time.LocalDate;
import java.util.UUID;

@Data
public class MobileDTO {

    private UUID id;
    private String companyName;
    private String modelName;
    private String price;
    private String imei;
    private LocalDate dateCreated;
    private LocalDate lastUpdate;
    private String status;

    @Lob
    private String image;

}
