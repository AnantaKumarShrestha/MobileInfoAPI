package com.coreTeam.MobileDataManupulationRestApi.db;

import com.coreTeam.MobileDataManupulationRestApi.Model.MobileModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

@Repository
public interface MobileRepo extends JpaRepository<MobileModel, UUID> {

    List<MobileModel> findByDateCreatedBetween(LocalDate startDate,LocalDate endDate);
    List<MobileModel> findByDateCreated(LocalDate createdDate);

}
