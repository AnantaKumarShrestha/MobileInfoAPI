package com.coreTeam.MobileDataManupulationRestApi.RestController;

import com.coreTeam.MobileDataManupulationRestApi.Service.OwnerService;
import com.coreTeam.MobileDataManupulationRestApi.dto.MobileDTO;
import com.coreTeam.MobileDataManupulationRestApi.dto.OwnerDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/owner-api")
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

    @GetMapping("/owners/{ownerId}")
    public OwnerDTO getOwnerByID(@PathVariable("ownerId") UUID id){
        return ownerService.getOwnerByID(id);
    }

    @PutMapping("/owners/{ownerId}")
    public OwnerDTO updateOwner(@RequestBody OwnerDTO owner,@PathVariable("ownerId") UUID id){
        return ownerService.updateOwner(owner,id);
    }

    @PostMapping("/owners/{ownerId}/mobile")
    public OwnerDTO assignMobileToOwner(@PathVariable("ownerId") UUID ownerId, @RequestBody MobileDTO mobile) throws IOException {
        return ownerService.assignMobileToOwner(ownerId,mobile);
    }

}
