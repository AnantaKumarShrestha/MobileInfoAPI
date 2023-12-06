package com.coreTeam.MobileDataManupulationRestApi.Service.ServiceImpl;

import com.coreTeam.MobileDataManupulationRestApi.Exception.MobileNotFoundException;
import com.coreTeam.MobileDataManupulationRestApi.Model.MobileModel;
import com.coreTeam.MobileDataManupulationRestApi.Service.MobileService;
import com.coreTeam.MobileDataManupulationRestApi.db.MobileDataRepo;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class MobileServiceImpl implements MobileService {

    @Autowired
    private MobileDataRepo mobileDataRepo;

    @Override
    public List<MobileModel> getAllMobileList(){
        return  mobileDataRepo.findAll();
    }

    @Override
    public MobileModel addMobileData(MobileModel mobileModel) {
      return mobileDataRepo.save(mobileModel);
    }

    @Override
    public MobileModel  findById(int id){
            return mobileDataRepo.findById(id).orElseThrow(()->new MobileNotFoundException(id));
    }

    @Override
    public void deleteAllMobileData() {
        mobileDataRepo.deleteAll();
    }

    @Override
    public void deleteMobileDataById(int id) {
      mobileDataRepo.deleteById(id);
    }

    @Override
    public MobileModel updateMobileData(MobileModel mobileModel, int id){
            if(findById(id)!=null){
            mobileModel.setId(id);
            return addMobileData(mobileModel);
            }else {
                return null;
            }
    }

    @Override
    public MobileModel updateMobileCompanyName(MobileModel mobileModel, int id){
        MobileModel mobileInDB=findById(id);
        if(mobileInDB!=null){
            mobileInDB.setCompanyName(mobileModel.getCompanyName());
            return addMobileData(mobileInDB);
        }else {
            return null;
        }
    }

    @Override
    public MobileModel updateMobileModelName(MobileModel mobileModel, int id){
        MobileModel mobileInDB=findById(id);
        if(mobileInDB!=null){
            mobileInDB.setModelName(mobileModel.getModelName());
            return addMobileData(mobileInDB);
        }else {
            return null;
        }
    }

    @Override
    public MobileModel updateMobilePrice(MobileModel mobileModel, int id){
        MobileModel mobileInDB=findById(id);
        if(mobileInDB!=null){
            mobileInDB.setPrice(mobileModel.getPrice());
            return addMobileData(mobileInDB);
        }else {
            return null;
        }
    }

    @Override
    public MobileModel updateMobileIMEI(MobileModel mobileModel, int id){
        MobileModel mobileInDB=findById(id);
        if(mobileInDB!=null){
            mobileInDB.setImei(mobileModel.getImei());
            return addMobileData(mobileInDB);
        }else {
            return null;
        }
    }

    @Override
    public List<MobileModel> listOfMobileBetweenTwoDates(LocalDate startDate, LocalDate endDate) {
        return mobileDataRepo.findByDateCreatedBetween(startDate,endDate);
    }

    @Override
    public List<MobileModel> listOfMobileCreatedOnSameDate(LocalDate localDate) {
        return mobileDataRepo.findByDateCreated(localDate);
    }

    @Override
    public List<MobileModel> listOfMobileSortedByCompanyName() {
        return  mobileDataRepo.findAll(Sort.by(Sort.Direction.ASC, "companyName"));
    }

    @Override
    public List<MobileModel> listOfMobileSortedByCompanyNameInDesc() {
        return  mobileDataRepo.findAll(Sort.by(Sort.Direction.DESC, "companyName"));
    }


}
