package com.coreTeam.MobileDataManupulationRestApi.Model;

import com.coreTeam.MobileDataManupulationRestApi.Convertor.IMEIConvertor;
import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.beans.factory.annotation.Value;

import java.time.LocalDate;
import java.util.UUID;
//get
@NamedQuery(name = "getAllMobile",query = "SELECT m FROM MobileModel m")
@NamedQuery(name = "getMobileListCreatedOnSameDay",query = "SELECT m FROM MobileModel m WHERE m.dateCreated= :dateCreated")
@NamedQuery(name = "getMobileListCreatedBetweenTwoDates",query = "SELECT m FROM MobileModel m WHERE m.dateCreated BETWEEN :startDate AND :endDate")
@NamedQuery(name = "AscOrderListByCompanyName",query = "SELECT m FROM MobileModel m ORDER BY m.companyName ASC")
@NamedQuery(name = "DesOrderListByCompanyName",query = "SELECT m FROM MobileModel m ORDER BY m.companyName DESC")
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
