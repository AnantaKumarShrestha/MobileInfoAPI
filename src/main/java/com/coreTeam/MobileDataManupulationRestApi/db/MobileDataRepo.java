package com.coreTeam.MobileDataManupulationRestApi.db;

import com.coreTeam.MobileDataManupulationRestApi.Model.MobileModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface MobileDataRepo extends JpaRepository<MobileModel,Integer> {

    List<MobileModel> findByDateCreatedBetween(LocalDate startDate,LocalDate endDate);
    List<MobileModel> findByDateCreated(LocalDate createdDate);

}
