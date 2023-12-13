package com.coreTeam.MobileDataManupulationRestApi.Service;

import com.coreTeam.MobileDataManupulationRestApi.Model.OwnerModel;
import com.coreTeam.MobileDataManupulationRestApi.dto.MobileDTO;
import com.coreTeam.MobileDataManupulationRestApi.dto.OwnerDTO;

import java.io.IOException;
import java.util.List;
import java.util.UUID;
import java.util.stream.Stream;

public interface OwnerService {

    OwnerDTO addOwner(OwnerDTO owner);

    List<OwnerDTO> getOwnerlist();

    OwnerDTO getOwnerByID(UUID id);

    OwnerDTO updateOwner(OwnerDTO owner, UUID id);

    OwnerDTO assignMobileToOwner(UUID ownerId, MobileDTO ownerDTO) throws IOException;

    OwnerModel ownerDtoIntoOwnerModel(OwnerDTO owner);

    OwnerDTO ownerModelIntoOwnerDto(OwnerModel owner);

    String deleteAllOwner();

    String deleteOwnerById(UUID id);

    String deleteOwnersMobileById(UUID ownerId,UUID mobileId);



}
