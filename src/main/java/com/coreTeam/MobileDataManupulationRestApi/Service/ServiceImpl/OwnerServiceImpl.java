package com.coreTeam.MobileDataManupulationRestApi.Service.ServiceImpl;

import com.coreTeam.MobileDataManupulationRestApi.Model.MobileModel;
import com.coreTeam.MobileDataManupulationRestApi.Model.OwnerModel;
import com.coreTeam.MobileDataManupulationRestApi.Service.MobileService;
import com.coreTeam.MobileDataManupulationRestApi.Service.OwnerService;
import com.coreTeam.MobileDataManupulationRestApi.db.OwnerRepo;
import com.coreTeam.MobileDataManupulationRestApi.dto.MobileDTO;
import com.coreTeam.MobileDataManupulationRestApi.dto.OwnerDTO;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class OwnerServiceImpl implements OwnerService {

    @Autowired
    private OwnerRepo ownerRepo;

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private MobileService mobileService;

    @Override
    public OwnerDTO addOwner(OwnerDTO ownerDTO) {
        OwnerModel ownerModel=ownerDtoIntoOwnerModel(ownerDTO);

        return ownerModelIntoOwnerDto(ownerRepo.save(ownerModel));
    }

    @Override
    public List<OwnerDTO> getOwnerlist() {
        return ownerRepo.findAll().stream().map(owner->ownerModelIntoOwnerDto(owner)).collect(Collectors.toList());
    }

    @Override
    public OwnerDTO getOwnerByID(UUID id) {
        return ownerModelIntoOwnerDto(ownerRepo.findById(id).get());
    }

    @Override
    public OwnerDTO updateOwnerData(OwnerDTO owner,UUID id) {
        owner.setId(id);
        return ownerModelIntoOwnerDto(ownerDtoIntoOwnerModel(owner));
    }

    @Override
    public OwnerDTO assigningMobileToOwner(UUID ownerId, UUID mobileId) {
        OwnerDTO owner=getOwnerByID(ownerId);
        MobileDTO mobile=mobileService.findById(mobileId);
        if (owner.getMobileList() == null) {
            owner.setMobileList(new ArrayList<>());
        }
        owner.getMobileList().add(mobile);
        OwnerModel ownerModel=ownerDtoIntoOwnerModel(owner);
        return ownerModelIntoOwnerDto(ownerRepo.save(ownerModel));
    }

    @Override
    public OwnerModel ownerDtoIntoOwnerModel(OwnerDTO owner) {

//        OwnerModel ownerModel=new OwnerModel();
//     //   ownerModel.setId(owner.getId());
//        ownerModel.setName(owner.getName());
//        ownerModel.setDob(owner.getDob());
//        ownerModel.setAddress(owner.getAddress());
//      //  ownerModel.setMobileList(new ArrayList<>(owner.getMobileList()));

        OwnerModel ownerModel=modelMapper.map(owner,OwnerModel.class);

        return ownerModel;
    }

    @Override
    public OwnerDTO ownerModelIntoOwnerDto(OwnerModel owner) {

//        OwnerDTO ownerDTO=new OwnerDTO();
//        ownerDTO.setId(owner.getId());
//        ownerDTO.setName(owner.getName());
//        ownerDTO.setDob(owner.getDob());
//        ownerDTO.setAddress(owner.getAddress());
//      //  List<MobileModel> A=owner.getMobileList();
//
//        if (owner.getMobileList() != null) {
//         //   owner.setMobileList(new ArrayList<>());
//            List<MobileDTO> k = owner.getMobileList().stream().map(s -> mobileService.mobileModelIntoMobileDto(s)).collect(Collectors.toList());
//            ownerDTO.setMobileList(new ArrayList<>(k));
//        }
        OwnerDTO ownerDTO=modelMapper.map(owner,OwnerDTO.class);
        return ownerDTO;
    }


}
