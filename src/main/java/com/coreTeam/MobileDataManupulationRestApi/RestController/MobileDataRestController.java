package com.coreTeam.MobileDataManupulationRestApi.RestController;


import com.coreTeam.MobileDataManupulationRestApi.Model.MobileModel;
import com.coreTeam.MobileDataManupulationRestApi.Service.MobileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.Base64;
import java.util.List;

@RestController
@RequestMapping("/mobileAPI")
public class MobileDataRestController {


    @Autowired
    private MobileService mobileService;

    @GetMapping("/getAllMobileData")
    public List<MobileModel> getAllMobileData(){
      return mobileService.getAllMobileList();

    }

    @GetMapping("/getMobileById/{id}")
    public MobileModel getMobileById(@PathVariable("id") int id){
     return mobileService.findById(id);
    }

    @DeleteMapping("/deleteAllMobileData")
    public void deleteAllMobileData(){
        mobileService.deleteAllMobileData();
    }

    @DeleteMapping("/deleteMobileData/{id}")
    public void deleteMobileDataById(@PathVariable("id") int id){
        mobileService.deleteMobileDataById(id);
    }

    @PostMapping("/addMobileData")
    public MobileModel addMobileData(@RequestBody MobileModel mobileModel){
        return mobileService.addMobileData(mobileModel);
    }

    @PutMapping("/updateMobileData/{id}")
    public MobileModel updateMobileDataUsingPut(@RequestBody MobileModel mobileModel,@PathVariable("id") int id){
        return mobileService.updateMobileData(mobileModel,id);
    }

    @PatchMapping("/patchMobileCompanyName/{id}")
    public MobileModel updateMobileCompanyNameUsingPatch(@RequestBody MobileModel mobileModel,@PathVariable("id") int id){
        return mobileService.updateMobileCompanyName(mobileModel,id);
    }

    @PatchMapping("/patchMobileModelName/{id}")
    public MobileModel updateMobileModelNameUsingPatch(@RequestBody MobileModel mobileModel,@PathVariable("id") int id){
        return mobileService.updateMobileModelName(mobileModel,id);
    }

    @PatchMapping("/patchMobilePrice/{id}")
    public MobileModel updateMobilePriceUsingPatch(@RequestBody MobileModel mobileModel,@PathVariable("id") int id){
        return mobileService.updateMobilePrice(mobileModel,id);
    }

    @PatchMapping("/patchMobileImei/{id}")
    public MobileModel updateMobileImeiUsingPatch(@RequestBody MobileModel mobileModel,@PathVariable("id") int id){
        return mobileService.updateMobileIMEI(mobileModel,id);
    }

    @GetMapping("/ListOfMobileBetweenTwoDate/{startDate}/{endDate}")
    public List<MobileModel> ListOfMobileBetweenTwoDate(@PathVariable("startDate") @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate startDate,@PathVariable("endDate") @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate endDate){
        return mobileService.listOfMobileBetweenTwoDates(startDate,endDate);
    }

    @GetMapping("/ListOfMobileCreatedOnSameDate/{createdDate}")
    public List<MobileModel> listOfMobileCreatedOnSameDate(@PathVariable("createdDate") @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate cratedDate){
        return mobileService.listOfMobileCreatedOnSameDate(cratedDate);
    }

    @GetMapping("/listOfMobileSortedByName")
    public List<MobileModel> listOfMobileSortedByName(){
        return mobileService.listOfMobileSortedByCompanyName();
    }

    @GetMapping("/listOfMobileSortedByNameIndesc")
    public List<MobileModel> listOfMobileSortedByNameInDesc(){
        return mobileService.listOfMobileSortedByCompanyNameInDesc();
    }





}
