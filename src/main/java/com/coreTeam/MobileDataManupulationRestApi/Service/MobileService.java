package com.coreTeam.MobileDataManupulationRestApi.Service;

import com.coreTeam.MobileDataManupulationRestApi.Model.MobileModel;
import com.coreTeam.MobileDataManupulationRestApi.dto.MobileDTO;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

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

    MobileDTO updateMobileData(MobileDTO mobileDTO);

    MobileDTO findById(UUID id);
    void deleteAllMobileData();

    void deleteMobileDataById(UUID id);

    MobileDTO updateMobileData(MobileDTO mobileDTO, UUID id);

    MobileDTO updateMobileCompanyName(MobileDTO mobileDTO,UUID id);

    MobileDTO updateMobileModelName(MobileDTO mobileDTO,UUID id);

    MobileDTO updateMobilePrice(MobileDTO mobileDTO,UUID id);

    MobileDTO updateMobileIMEI(MobileDTO mobileDTO,UUID id);

    List<MobileDTO> listOfMobileBetweenTwoDates(LocalDate startDate,LocalDate endDate);

    List<MobileDTO> listOfMobileCreatedOnSameDate(LocalDate localDate);

    List<MobileDTO> listOfMobileSortedByCompanyName();

    List<MobileDTO> listOfMobileSortedByCompanyNameInDesc();

    MobileDTO deactivateMobile(UUID id);

    MobileDTO activateMobile(UUID id);

    MobileModel mobileDtoIntoMobileModel(MobileDTO mobileDTO);

    MobileDTO mobileModelIntoMobileDto(MobileModel mobileModel);
}
