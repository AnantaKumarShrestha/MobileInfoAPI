package com.coreTeam.MobileDataManupulationRestApi.db;

import com.coreTeam.MobileDataManupulationRestApi.Model.MobileModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

@Repository
public interface MobileRepo extends JpaRepository<MobileModel, UUID> {

    @Query(name = "getAllMobile")
    List<MobileModel> getAllMobile();

    @Query(name = "getMobileListCreatedOnSameDay")
    List<MobileModel> getMobileListCratedOnSameDay(@Param("dateCreated") LocalDate dateCreated);

    @Query(name = "getMobileListCreatedBetweenTwoDates")
    List<MobileModel> getMobileListCratedBetweenTwoDates(@Param("startDate") LocalDate startDate,@Param("endDate") LocalDate endDate);

    @Query(name = "AscOrderListByCompanyName")
    List<MobileModel> getMobileAscOrder();

    @Query(name = "DesOrderListByCompanyName")
    List<MobileModel> getMobileListDesOrder();


}
