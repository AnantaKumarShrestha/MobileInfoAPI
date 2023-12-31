package com.coreTeam.MobileDataManupulationRestApi.RestController;

import com.coreTeam.MobileDataManupulationRestApi.Service.MobileService;
import com.coreTeam.MobileDataManupulationRestApi.dto.MobileDTO;
import com.coreTeam.MobileDataManupulationRestApi.playLoad.ApiResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/mobile-api")
public class MobileDataRestController{


    @Autowired
    private MobileService mobileService;

    @GetMapping("/mobiles")
    public List<MobileDTO> getAllMobile(){
      return mobileService.getAllMobileList();
    }

    @GetMapping("/mobiles/{mobileId}")
    public MobileDTO getMobileById(@PathVariable("mobileId") UUID id){
        return mobileService.findById(id);
    }


    @DeleteMapping("/mobiles")
    public ApiResponse deleteAllMobile(){
         mobileService.deleteAllMobile();
         return new ApiResponse("Deleted All Mobile Successfully","Success");
    }

    @DeleteMapping("/mobiles/{mobileId}")
    public ApiResponse deleteMobileById(@PathVariable("mobileId") UUID id){
        mobileService.deleteMobileByID(id);
        return new ApiResponse("Deleted Mobile Successfully.","Success");
    }

    @PostMapping("/mobile")
    public ResponseEntity<MobileDTO> addMobile(@RequestBody MobileDTO mobileDTO) throws IOException {
        MobileDTO mobile=mobileService.addMobile(mobileDTO);
        return new ResponseEntity<>(mobile, HttpStatus.CREATED);
    }


    @PutMapping("/mobiles/{mobileId}")
    public MobileDTO updateMobile(@RequestBody MobileDTO mobileDTO,@PathVariable("mobileId") UUID id){
        return mobileService.updateMobile(mobileDTO,id);
    }

    @PatchMapping("/mobiles/company-name/{mobileId}")
    public MobileDTO updateMobileCompanyName(@RequestBody MobileDTO mobileDTO,@PathVariable("mobileId") UUID id){
        return mobileService.updateMobileCompanyName(mobileDTO,id);
    }

    @PatchMapping("/mobiles/model-name/{mobileId}")
    public MobileDTO updateMobileModelName(@RequestBody MobileDTO mobileDTO,@PathVariable("mobileId") UUID id){
        return mobileService.updateMobileModelName(mobileDTO,id);
    }

    @PatchMapping("/mobiles/price/{mobileId}")
    public MobileDTO updateMobilePrice(@RequestBody MobileDTO mobileDTO,@PathVariable("mobileId") UUID id){
        return mobileService.updateMobilePrice(mobileDTO,id);
    }

    @PatchMapping("/mobiles/imei/{mobileId}")
    public MobileDTO updateMobileImei(@RequestBody MobileDTO mobileDTO,@PathVariable("mobileId") UUID id){
        return mobileService.updateMobileIMEI(mobileDTO,id);
    }

    @GetMapping("/mobiles/betweenTwoDates/{startDate}/{endDate}")
    public List<MobileDTO> ListOfMobileBetweenTwoDates(@PathVariable("startDate") @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate startDate,@PathVariable("endDate") @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate endDate){
        return mobileService.listOfMobileBetweenTwoDates(startDate,endDate);
    }

    @GetMapping("/mobiles/sameDate/{createdDate}")
    public List<MobileDTO> listOfMobileCreatedOnSameDate(@PathVariable("createdDate") @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate cratedDate){
        return mobileService.listOfMobileCreatedOnSameDate(cratedDate);
    }

    @GetMapping("/mobiles/sorted")
    public List<MobileDTO> listOfMobileSortedByName(){
        return mobileService.listOfMobileSortedByCompanyName();
    }

    @GetMapping("/mobiles/sorted/dis")
    public List<MobileDTO> listOfMobileSortedByNameInDesc(){
        return mobileService.listOfMobileSortedByCompanyNameInDesc();
    }

    @PutMapping("/mobiles/deactivate")
    public MobileDTO deactivateMobile(@RequestParam("id") UUID id){
        return mobileService.deactivateMobile(id);
    }

    @PutMapping("/mobiles/activate")
    public MobileDTO activateMobile(@RequestParam("id") UUID id){
        return mobileService.activateMobile(id);
    }




}
