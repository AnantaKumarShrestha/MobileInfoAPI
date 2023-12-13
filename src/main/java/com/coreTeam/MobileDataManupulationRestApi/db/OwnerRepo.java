package com.coreTeam.MobileDataManupulationRestApi.db;

import com.coreTeam.MobileDataManupulationRestApi.Model.OwnerModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface OwnerRepo extends JpaRepository<OwnerModel, UUID> {

}
