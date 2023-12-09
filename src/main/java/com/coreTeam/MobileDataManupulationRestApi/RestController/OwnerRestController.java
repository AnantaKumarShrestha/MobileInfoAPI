package com.coreTeam.MobileDataManupulationRestApi.RestController;

import com.coreTeam.MobileDataManupulationRestApi.Service.OwnerService;
import com.coreTeam.MobileDataManupulationRestApi.dto.OwnerDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/ownerApi")
public class OwnerRestController {

    @Autowired
    private OwnerService ownerService;

    @PostMapping("/owner")
    public OwnerDTO addOwner(@RequestBody OwnerDTO ownerDTO){
        return ownerService.addOwner(ownerDTO);
    }

    @GetMapping("/owners")
    public List<OwnerDTO> getOwnerList(){
        return ownerService.getOwnerlist();
    }

    @GetMapping("/owner")
    public OwnerDTO getOwnerByID(@RequestParam("id") UUID id){
        return ownerService.getOwnerByID(id);
    }

    @PutMapping("/updateOwner")
    public OwnerDTO updateOwnerData(@RequestBody OwnerDTO owner,@RequestParam("id") UUID id){
        return ownerService.updateOwnerData(owner,id);
    }

    @PostMapping("/assigningMobileToUser")
    public OwnerDTO as(@RequestParam("ownerId") UUID ownerId,@RequestParam("mobileId") UUID mobileId){
        return ownerService.assigningMobileToOwner(ownerId,mobileId);
    }

}
