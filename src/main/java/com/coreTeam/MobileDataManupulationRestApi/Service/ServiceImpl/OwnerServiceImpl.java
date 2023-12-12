package com.coreTeam.MobileDataManupulationRestApi.Service.ServiceImpl;

import com.coreTeam.MobileDataManupulationRestApi.Exception.MobileNotFoundException;
import com.coreTeam.MobileDataManupulationRestApi.Exception.OwnerNotFoundException;
import com.coreTeam.MobileDataManupulationRestApi.Model.MobileModel;
import com.coreTeam.MobileDataManupulationRestApi.Model.OwnerModel;
import com.coreTeam.MobileDataManupulationRestApi.Service.MobileService;
import com.coreTeam.MobileDataManupulationRestApi.Service.OwnerService;
import com.coreTeam.MobileDataManupulationRestApi.db.MobileRepo;
import com.coreTeam.MobileDataManupulationRestApi.db.OwnerRepo;
import com.coreTeam.MobileDataManupulationRestApi.dto.MobileDTO;
import com.coreTeam.MobileDataManupulationRestApi.dto.OwnerDTO;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Base64;
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

    @Autowired
    private MobileRepo mobileRepo;

    @Override
    public OwnerDTO addOwner(OwnerDTO ownerDTO) {
        OwnerModel owner=ownerDtoIntoOwnerModel(ownerDTO);
        return ownerModelIntoOwnerDto(ownerRepo.save(owner));
    }

    @Override
    public List<OwnerDTO> getOwnerlist() {
        return ownerRepo.findAll().stream().map(owner->ownerModelIntoOwnerDto(owner)).collect(Collectors.toList());
    }

    @Override
    public OwnerDTO getOwnerByID(UUID id) {
        return ownerModelIntoOwnerDto(ownerRepo.findById(id).orElseThrow(()->new MobileNotFoundException(id)));
    }

    @Override
    public OwnerDTO updateOwner(OwnerDTO owner, UUID id) {
        owner.setId(id);
        return ownerModelIntoOwnerDto(ownerDtoIntoOwnerModel(owner));
    }

    @Override
    public OwnerDTO assignMobileToOwner(UUID ownerId, MobileDTO mobileDTO) throws IOException {

        //converted mobiledto into mobileModel and saved into databased and valued return is saved into savedMobile variable
        MobileModel savedMobile=mobileRepo.save(mobileService.mobileDtoIntoMobileModel(mobileDTO));
        //Owner is fetched from database by using id and saved into owner
        OwnerModel owner =ownerRepo.findById(ownerId).orElseThrow(()->new OwnerNotFoundException(ownerId));
        //status is set to active
        savedMobile.setStatus("Active");
        savedMobile.setImage("src/main/resources/static/images/mobileimage/"+savedMobile.getId()+savedMobile.getDateCreated()+".png");
        //===========================
        byte[] decodedBytes= Base64.getDecoder().decode(mobileDTO.getImage());
        Files.write(Path.of("src/main/resources/static/images/mobileimage/" + savedMobile.getId()+savedMobile.getDateCreated()+".png"), decodedBytes);

        //=======================
        //mobile is saved into database after setting status active and photo name
        mobileRepo.save(savedMobile);
//        if (owner.getMobileList() == null) {
//            owner.setMobileList(new ArrayList<>());
//        }
        //Mobile is set into owner entity
        owner.getMobileList().add(savedMobile);
        //Owner is  saved ito database and the return valued is converted into ownerdto
        return ownerModelIntoOwnerDto(ownerRepo.save(owner));
    }

    @Override
    public OwnerModel ownerDtoIntoOwnerModel(OwnerDTO owner) {
        return modelMapper.map(owner,OwnerModel.class);
    }

    @Override
    public OwnerDTO ownerModelIntoOwnerDto(OwnerModel owner) {
        return modelMapper.map(owner,OwnerDTO.class);
    }


}
