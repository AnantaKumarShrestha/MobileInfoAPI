package com.coreTeam.MobileDataManupulationRestApi.Service;

import com.coreTeam.MobileDataManupulationRestApi.Model.OwnerModel;
import com.coreTeam.MobileDataManupulationRestApi.dto.OwnerDTO;

import java.util.List;
import java.util.UUID;

public interface OwnerService {

    OwnerDTO addOwner(OwnerDTO owner);

    List<OwnerDTO> getOwnerlist();

    OwnerDTO getOwnerByID(UUID id);

    OwnerDTO updateOwnerData(OwnerDTO owner,UUID id);

    OwnerDTO assigningMobileToOwner(UUID ownerId,UUID mobileId);

    OwnerModel ownerDtoIntoOwnerModel(OwnerDTO owner);

    OwnerDTO ownerModelIntoOwnerDto(OwnerModel owner);



}
