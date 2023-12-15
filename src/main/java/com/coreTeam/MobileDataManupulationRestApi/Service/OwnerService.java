package com.coreTeam.MobileDataManupulationRestApi.Service;

import com.coreTeam.MobileDataManupulationRestApi.Model.OwnerModel;
import com.coreTeam.MobileDataManupulationRestApi.dto.MobileDTO;
import com.coreTeam.MobileDataManupulationRestApi.dto.OwnerDTO;

import java.io.IOException;
import java.util.List;
import java.util.UUID;
import java.util.stream.Stream;

public interface OwnerService {

    OwnerDTO addOwner(OwnerDTO ownerDTO);

    List<OwnerDTO> getOwnerlist();

    OwnerDTO getOwnerByID(UUID id);

    OwnerDTO updateOwner(OwnerDTO ownerDTO, UUID id);

    OwnerDTO assignMobileToOwner(UUID ownerId, MobileDTO ownerDTO) throws IOException;

    OwnerModel ownerDtoIntoOwnerModel(OwnerDTO ownerDTO);

    OwnerDTO ownerModelIntoOwnerDto(OwnerModel ownerModel);

    void deleteAllOwner();

    void deleteOwnerById(UUID id);

    void deleteOwnersMobileById(UUID ownerId,UUID mobileId);

    OwnerDTO assignMadeMobileToOwner(UUID ownerId,UUID mobileId);



}
