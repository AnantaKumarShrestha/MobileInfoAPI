package com.coreTeam.MobileDataManupulationRestApi.db;

import com.coreTeam.MobileDataManupulationRestApi.Model.MobileModel;
import com.coreTeam.MobileDataManupulationRestApi.Model.OwnerModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface OwnerRepo extends JpaRepository<OwnerModel, UUID> {
    @Query(name = "getAllOwner")
    List<OwnerModel> getAllOwner();
}
