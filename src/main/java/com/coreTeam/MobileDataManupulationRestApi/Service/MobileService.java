package com.coreTeam.MobileDataManupulationRestApi.Service;

import com.coreTeam.MobileDataManupulationRestApi.Model.MobileModel;

import java.time.LocalDate;
import java.util.List;

public interface MobileService {

    List<MobileModel> getAllMobileList();
    MobileModel addMobileData(MobileModel mobileModel);

    MobileModel findById(int id);
    void deleteAllMobileData();

    void deleteMobileDataById(int id);

    MobileModel updateMobileData(MobileModel mobileModel,int id);

    MobileModel updateMobileCompanyName(MobileModel mobileModel,int id);

    MobileModel updateMobileModelName(MobileModel mobileModel,int id);

    MobileModel updateMobilePrice(MobileModel mobileModel,int id);

    MobileModel updateMobileIMEI(MobileModel mobileModel,int id);

    List<MobileModel> listOfMobileBetweenTwoDates(LocalDate startDate,LocalDate endDate);

    List<MobileModel> listOfMobileCreatedOnSameDate(LocalDate localDate);

    List<MobileModel> listOfMobileSortedByCompanyName();

    List<MobileModel> listOfMobileSortedByCompanyNameInDesc();
}
