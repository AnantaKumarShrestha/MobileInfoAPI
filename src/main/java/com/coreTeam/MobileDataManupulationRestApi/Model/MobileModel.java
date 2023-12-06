package com.coreTeam.MobileDataManupulationRestApi.Model;

import com.coreTeam.MobileDataManupulationRestApi.Convertor.IMEIConvertor;
import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
import lombok.Data;
import lombok.Generated;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDate;

@Data
@Entity
public class MobileModel {


    @Id
    @Generated
    private int id;
    private String companyName;
    private String modelName;
    private String price;

    @Convert(converter = IMEIConvertor.class)
    private String imei;

    @CreationTimestamp
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    @Column(updatable = false)
    private LocalDate dateCreated;

    @UpdateTimestamp
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    private LocalDate lastUpdate;



}
