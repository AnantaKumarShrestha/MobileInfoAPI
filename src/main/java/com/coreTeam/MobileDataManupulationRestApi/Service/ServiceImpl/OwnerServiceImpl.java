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
import java.nio.file.Paths;
import java.util.Base64;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;
import java.util.stream.Stream;

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
        return ownerRepo.getAllOwner().stream().map(owner->ownerModelIntoOwnerDto(owner)).collect(Collectors.toList());
    }

    @Override
    public OwnerDTO getOwnerByID(UUID id) {
        return ownerModelIntoOwnerDto(ownerRepo.findById(id).orElseThrow(()->new OwnerNotFoundException(id)));
    }

    @Override
    public OwnerDTO updateOwner(OwnerDTO owner, UUID id) {
        owner.setId(id);
        return ownerModelIntoOwnerDto(ownerDtoIntoOwnerModel(owner));
    }

    @Override
    public OwnerDTO assignMobileToOwner(UUID ownerId, MobileDTO mobileDTO) throws IOException {

        MobileModel savedMobile=mobileRepo.save(mobileService.mobileDtoIntoMobileModel(mobileDTO));
        OwnerModel owner =ownerRepo.findById(ownerId).orElseThrow(()->new OwnerNotFoundException(ownerId));
        savedMobile.setStatus("Active");
        savedMobile.setImage("/owner-api/owener/mobile/productphoto/"+savedMobile.getId()+savedMobile.getDateCreated()+".png");
        byte[] decodedBytes= Base64.getDecoder().decode(mobileDTO.getImage());
        Files.write(Path.of("src/main/resources/static/images/mobileimage/" + savedMobile.getId()+savedMobile.getDateCreated()+".png"), decodedBytes);
        mobileRepo.save(savedMobile);
        owner.getMobileList().add(savedMobile);
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

    @Override
    public String deleteAllOwner() {
        ownerRepo.findAll().stream().forEach(owner->deleteOwnerById(owner.getId()));
         return "Deleted successfully";
    }

    private void deletePhoto(MobileModel mobile){
        String pathname="src/main/resources/static/images/mobileimage/";
        String photo=""+mobile.getId()+mobile.getDateCreated()+".png";
        try {
            Files.delete(Paths.get(pathname,photo));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public String deleteOwnerById(UUID id) {
        return ownerRepo.findById(id).map(owner->{
            List<MobileModel> mobileList=owner.getMobileList();

            for(MobileModel mobile:mobileList){
                deletePhoto(mobile);
            }
            ownerRepo.delete(owner);
           return "Deleted successfully";
        }).orElseThrow(()->new OwnerNotFoundException(id));
    }

    @Override
    public String deleteOwnersMobileById(UUID ownerId, UUID mobileId) {
        if(!ownerRepo.existsById(ownerId)){
            throw new OwnerNotFoundException(ownerId);
        }

        return mobileRepo.findById(mobileId).map(mobile->{
            deletePhoto(mobile);
            mobileRepo.deleteById(mobileId);
               return "Deleted Successfully";
        }).orElseThrow(()->new MobileNotFoundException(mobileId));


    }

    @Override
    public OwnerDTO assignMadeMobileToOwner(UUID ownerId, UUID mobileId) {
        OwnerModel owner=ownerRepo.findById(ownerId).orElseThrow(()->new OwnerNotFoundException(ownerId));
        MobileModel mobile=mobileRepo.findById(mobileId).orElseThrow(()->new MobileNotFoundException(mobileId));
        mobile.setStatus("active");
        owner.getMobileList().add(mobile);
        return ownerModelIntoOwnerDto( ownerRepo.save(owner));
    }


}
