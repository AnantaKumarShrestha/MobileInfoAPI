package com.coreTeam.MobileDataManupulationRestApi.dto;

import com.coreTeam.MobileDataManupulationRestApi.Model.AddressModel;
import lombok.Data;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

@Data
public class OwnerDTO {

    private UUID id;
    private String name;
    private String dob;
    private AddressModel address;
    private List<MobileDTO> mobileList;

}
