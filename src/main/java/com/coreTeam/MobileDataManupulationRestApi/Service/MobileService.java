package com.coreTeam.MobileDataManupulationRestApi.Service;

import com.coreTeam.MobileDataManupulationRestApi.Model.MobileModel;
import com.coreTeam.MobileDataManupulationRestApi.dto.MobileDTO;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

public interface MobileService {

    List<MobileDTO> getAllMobileList();
    MobileDTO addMobile(MobileDTO mobileDTO);

    MobileDTO updateMobile(MobileDTO mobileDTO);

    MobileDTO findById(UUID id);
    void deleteAllMobile();

    void deleteMobileByID(UUID id);

    MobileDTO updateMobile(MobileDTO mobileDTO, UUID id);

    MobileDTO updateMobileCompanyName(MobileDTO mobileDTO,UUID id);

    MobileDTO updateMobileModelName(MobileDTO mobileDTO,UUID id);

    MobileDTO updateMobilePrice(MobileDTO mobileDTO,UUID id);

    MobileDTO updateMobileIMEI(MobileDTO mobileDTO,UUID id);

    List<MobileDTO> listOfMobileBetweenTwoDates(LocalDate startDate,LocalDate endDate);

    List<MobileDTO> listOfMobileCreatedOnSameDate(LocalDate sameDate);

    List<MobileDTO> listOfMobileSortedByCompanyName();

    List<MobileDTO> listOfMobileSortedByCompanyNameInDesc();

    MobileDTO deactivateMobile(UUID id);

    MobileDTO activateMobile(UUID id);

    MobileModel getMobileById(UUID id);

    MobileModel mobileDtoIntoMobileModel(MobileDTO mobileDTO);

    MobileDTO mobileModelIntoMobileDto(MobileModel mobileModel);
}
