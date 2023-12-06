package com.coreTeam.MobileDataManupulationRestApi.Service;

import com.coreTeam.MobileDataManupulationRestApi.Model.MobileModel;
import com.coreTeam.MobileDataManupulationRestApi.dto.MobileDTO;

import java.time.LocalDate;
import java.util.List;

public interface MobileService {

//    List<MobileModel> getAllMobileList();
//    MobileModel addMobileData(MobileModel mobileModel);
//
//    MobileModel findById(int id);
//    void deleteAllMobileData();
//
//    void deleteMobileDataById(int id);
//
//    MobileModel updateMobileData(MobileModel mobileModel,int id);
//
//    MobileModel updateMobileCompanyName(MobileModel mobileModel,int id);
//
//    MobileModel updateMobileModelName(MobileModel mobileModel,int id);
//
//    MobileModel updateMobilePrice(MobileModel mobileModel,int id);
//
//    MobileModel updateMobileIMEI(MobileModel mobileModel,int id);
//
//    List<MobileModel> listOfMobileBetweenTwoDates(LocalDate startDate,LocalDate endDate);
//
//    List<MobileModel> listOfMobileCreatedOnSameDate(LocalDate localDate);
//
//    List<MobileModel> listOfMobileSortedByCompanyName();
//
//    List<MobileModel> listOfMobileSortedByCompanyNameInDesc();
//
//    MobileModel mobileDtoIntoMobileModel(MobileDTO mobileDTO);
//
//    MobileDTO mobileModelIntoMobileDto(MobileModel mobileModel);






  //  ================================================================

    List<MobileDTO> getAllMobileList();
    MobileDTO addMobileData(MobileDTO mobileDTO);

    MobileDTO findById(int id);
    void deleteAllMobileData();

    void deleteMobileDataById(int id);

    MobileDTO updateMobileData(MobileDTO mobileDTO,int id);

    MobileDTO updateMobileCompanyName(MobileDTO mobileDTO,int id);

    MobileDTO updateMobileModelName(MobileDTO mobileDTO,int id);

    MobileDTO updateMobilePrice(MobileDTO mobileDTO,int id);

    MobileDTO updateMobileIMEI(MobileDTO mobileDTO,int id);

    List<MobileDTO> listOfMobileBetweenTwoDates(LocalDate startDate,LocalDate endDate);

    List<MobileDTO> listOfMobileCreatedOnSameDate(LocalDate localDate);

    List<MobileDTO> listOfMobileSortedByCompanyName();

    List<MobileDTO> listOfMobileSortedByCompanyNameInDesc();

    MobileModel mobileDtoIntoMobileModel(MobileDTO mobileDTO);

    MobileDTO mobileModelIntoMobileDto(MobileModel mobileModel);
}
