package com.coreTeam.MobileDataManupulationRestApi.Model;

import com.coreTeam.MobileDataManupulationRestApi.dto.MobileDTO;
import jakarta.persistence.*;
import lombok.Data;
import lombok.Generated;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@NamedQuery(name = "getAllOwner",query = "SELECT o FROM OwnerModel o")
@Data
@Entity
public class OwnerModel {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;
    private String name;
    private String dob;

    @OneToOne(cascade = CascadeType.ALL)
    private AddressModel address;

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "mobileId")
    private List<MobileModel> mobileList;



}
