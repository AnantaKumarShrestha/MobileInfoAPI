package com.coreTeam.MobileDataManupulationRestApi.Model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;
import lombok.Generated;

import java.util.UUID;

@Data
@Entity
public class AddressModel {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;
    private String state;
    private String area;
}
