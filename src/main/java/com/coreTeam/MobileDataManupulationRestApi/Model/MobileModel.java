package com.coreTeam.MobileDataManupulationRestApi.Model;

import com.coreTeam.MobileDataManupulationRestApi.Convertor.IMEIConvertor;
import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.beans.factory.annotation.Value;

import java.time.LocalDate;
import java.util.UUID;

@Data
@Entity
public class MobileModel {


    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;
    private String companyName;
    private String modelName;
    private String price;
    private String status;

    @Convert(converter = IMEIConvertor.class)
    private String imei;

    @CreationTimestamp
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    @Column(updatable = false)
    private LocalDate dateCreated;

    @UpdateTimestamp
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    private LocalDate lastUpdate;

    @Lob
    private String image;



}
