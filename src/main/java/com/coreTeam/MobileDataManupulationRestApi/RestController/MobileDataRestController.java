package com.coreTeam.MobileDataManupulationRestApi.RestController;


import com.coreTeam.MobileDataManupulationRestApi.Model.MobileModel;
import com.coreTeam.MobileDataManupulationRestApi.Service.MobileService;
import com.coreTeam.MobileDataManupulationRestApi.dto.MobileDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/mobileAPI")
public class MobileDataRestController{


    @Autowired
    private MobileService mobileService;

    @GetMapping("/getAllMobileData")
    public List<MobileDTO> getAllMobileData(){
      return mobileService.getAllMobileList();

    }

    // Using Request Param

    @GetMapping("/getMobileById")
    public MobileDTO getMobileByIdParam(@RequestParam("id") UUID id){
        return mobileService.findById(id);
    }



    @DeleteMapping("/deleteAllMobileData")
    public void deleteAllMobileData(){
        mobileService.deleteAllMobileData();
    }

    @DeleteMapping("/deleteMobileData")
    public void deleteMobileDataByIdParam(@RequestParam("id") UUID id){
        mobileService.deleteMobileDataById(id);
    }

    @PostMapping("/addMobileData")
    public MobileDTO addMobileData(@RequestBody MobileDTO mobileDTO){
        return mobileService.addMobileData(mobileDTO);
    }

    @PutMapping("/updateMobileData")
    public MobileDTO updateMobileDataUsingPutParam(@RequestBody MobileDTO mobileDTO,@RequestParam("id") UUID id){
        return mobileService.updateMobileData(mobileDTO,id);
    }

    @PatchMapping("/patchMobileCompanyName")
    public MobileDTO updateMobileCompanyNameUsingPatchParam(@RequestBody MobileDTO mobileDTO,@RequestParam("id") UUID id){
        return mobileService.updateMobileCompanyName(mobileDTO,id);
    }

    @PatchMapping("/patchMobileModelName")
    public MobileDTO updateMobileModelNameUsingPatchParam(@RequestBody MobileDTO mobileDTO,@RequestParam("id") UUID id){
        return mobileService.updateMobileModelName(mobileDTO,id);
    }

    @PatchMapping("/patchMobilePrice")
    public MobileDTO updateMobilePriceUsingPatchParam(@RequestBody MobileDTO mobileDTO,@RequestParam("id") UUID id){
        return mobileService.updateMobilePrice(mobileDTO,id);
    }

    @PatchMapping("/patchMobileImei")
    public MobileDTO updateMobileImeiUsingPatchParam(@RequestBody MobileDTO mobileDTO,@RequestParam("id") UUID id){
        return mobileService.updateMobileIMEI(mobileDTO,id);
    }

    @GetMapping("/ListOfMobileBetweenTwoDate")
    public List<MobileDTO> ListOfMobileBetweenTwoDateParam(@RequestParam("startDate") @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate startDate,@RequestParam("endDate") @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate endDate){
        return mobileService.listOfMobileBetweenTwoDates(startDate,endDate);
    }

    @GetMapping("/ListOfMobileCreatedOnSameDate")
    public List<MobileDTO> listOfMobileCreatedOnSameDateParam(@RequestParam("createdDate") @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate cratedDate){
        return mobileService.listOfMobileCreatedOnSameDate(cratedDate);
    }

    @GetMapping("/listOfMobileSortedByName")
    public List<MobileDTO> listOfMobileSortedByName(){
        return mobileService.listOfMobileSortedByCompanyName();
    }

    @GetMapping("/listOfMobileSortedByNameIndesc")
    public List<MobileDTO> listOfMobileSortedByNameInDesc(){
        return mobileService.listOfMobileSortedByCompanyNameInDesc();
    }

    @GetMapping("/deactivateMobile")
    public MobileDTO deactivateMobile(@RequestParam("id") UUID id){
        return mobileService.deactivateMobile(id);
    }

    @PutMapping("/activateMobile")
    public MobileDTO activateMobile(@RequestParam("id") UUID id){
        return mobileService.activateMobile(id);
    }


}
