package com.coreTeam.MobileDataManupulationRestApi.RestController;

import com.coreTeam.MobileDataManupulationRestApi.Service.OwnerService;
import com.coreTeam.MobileDataManupulationRestApi.dto.MobileDTO;
import com.coreTeam.MobileDataManupulationRestApi.dto.OwnerDTO;
import com.coreTeam.MobileDataManupulationRestApi.playLoad.ApiResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/owner-api")
public class OwnerRestController {

    @Autowired
    private OwnerService ownerService;


    @PostMapping("/owner")
    public ResponseEntity<OwnerDTO> addOwner(@RequestBody OwnerDTO ownerDTO){
         OwnerDTO owner=ownerService.addOwner(ownerDTO);
        return new ResponseEntity<>(owner,HttpStatus.CREATED);
    }

    @GetMapping("/owners")
    public ResponseEntity<List<OwnerDTO>> getOwnerList(){
        List<OwnerDTO> ownerList=ownerService.getOwnerlist();
        return new ResponseEntity<>(ownerList,HttpStatus.OK);
    }

    @GetMapping("/owners/{ownerId}")
    public ResponseEntity<OwnerDTO> getOwnerByID(@PathVariable("ownerId") UUID id){
        OwnerDTO owner= ownerService.getOwnerByID(id);
        return new ResponseEntity<>(owner,HttpStatus.OK);
    }

    @PutMapping("/owners/{ownerId}")
    public OwnerDTO updateOwner(@RequestBody OwnerDTO ownerDTO,@PathVariable("ownerId") UUID id){
        return ownerService.updateOwner(ownerDTO,id);
    }

    @PostMapping("/owners/{ownerId}/mobile")
    public ResponseEntity<OwnerDTO> assignMobileToOwner(@PathVariable("ownerId") UUID ownerId, @RequestBody MobileDTO mobile) throws IOException {
        OwnerDTO owner=ownerService.assignMobileToOwner(ownerId,mobile);
        return new ResponseEntity<>(owner,HttpStatus.OK);
    }

    @DeleteMapping("/owners")
    public ApiResponse deleteAllOwner(){
        ownerService.deleteAllOwner();
        return new ApiResponse("Deleted All Owner successfully","Success");
    }

    @DeleteMapping("/owners/{ownerId}/mobiles/{mobileId}")
    public ApiResponse deleteOwnersMobileById(@PathVariable("ownerId") UUID ownerId,@PathVariable("mobileId") UUID mobileId){
       ownerService.deleteOwnersMobileById(ownerId,mobileId);
       return new ApiResponse("Deleted mobile successfully","Success");
    }

    @DeleteMapping("/owners/{ownerId}")
    public ApiResponse deleteOwnerById(@PathVariable("ownerId") UUID id){
         ownerService.deleteOwnerById(id);
         return new ApiResponse("Deleted mobile successfully id : "+id,"Success");
    }

    @GetMapping("/owner/{ownerId}/mobile/{mobileId}")
    public ResponseEntity<OwnerDTO> assignMobileToOwner(@PathVariable("ownerId") UUID ownerId,@PathVariable("mobileId") UUID mobileId){
       OwnerDTO owner=ownerService.assignMadeMobileToOwner(ownerId,mobileId);
       return new ResponseEntity<>(owner,HttpStatus.OK);
    }

    @GetMapping("/owener/mobile/productphoto/{imagename}")
    public ResponseEntity<?> getphoto(@PathVariable("imagename") String imageName) throws IOException {
        Path path = Path.of("src/main/resources/static/images/mobileimage/" + imageName);
        byte[] file = Files.readAllBytes(path);
            return ResponseEntity.status(HttpStatus.OK).contentType(MediaType.valueOf("image/png")).body(file);
        }


    }




