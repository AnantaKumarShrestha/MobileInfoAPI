package com.coreTeam.MobileDataManupulationRestApi.Service.ServiceImpl;

import com.coreTeam.MobileDataManupulationRestApi.Model.MobileModel;
import com.coreTeam.MobileDataManupulationRestApi.Service.MobileService;
import com.coreTeam.MobileDataManupulationRestApi.db.MobileDataRepo;
import io.micrometer.observation.annotation.Observed;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Base64;
import java.util.List;

@Service
public class MobileServiceImpl implements MobileService {

    @Autowired
    private MobileDataRepo mobileDataRepo;

    static void decodingIMEI(MobileModel mobileModel){
        mobileModel.setImei(new String(Base64.getDecoder().decode(mobileModel.getImei())));
    }

    static void encodingIMEI(MobileModel mobileModel){
        mobileModel.setImei(Base64.getEncoder().encodeToString(mobileModel.getImei().getBytes()));
    }

    @Override
    public List<MobileModel> getAllMobileList(){
        List<MobileModel> mobileModelList=mobileDataRepo.findAll();
        for(MobileModel mobileModel:mobileModelList){
            decodingIMEI(mobileModel);
        }
        return  mobileModelList;
    }

    @Override
    public MobileModel addMobileData(MobileModel mobileModel) {
        encodingIMEI(mobileModel);
      return mobileDataRepo.save(mobileModel);
    }

    @Override
    public MobileModel findById(int id) {
        MobileModel mobileModel=mobileDataRepo.findById(id).get();
        decodingIMEI(mobileModel);
        return mobileModel;
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
    public MobileModel updateMobileData(MobileModel mobileModel, int id) {
            if(findById(id)!=null){
            mobileModel.setId(id);
            return addMobileData(mobileModel);
            }else {
                return null;
            }
    }

    @Override
    public MobileModel updateMobileCompanyName(MobileModel mobileModel, int id) {
        MobileModel mobileInDB=findById(id);
        if(mobileInDB!=null){
            mobileInDB.setCompanyName(mobileModel.getCompanyName());
            return addMobileData(mobileInDB);
        }else {
            return null;
        }
    }

    @Override
    public MobileModel updateMobileModelName(MobileModel mobileModel, int id) {
        MobileModel mobileInDB=findById(id);
        if(mobileInDB!=null){
            mobileInDB.setModelName(mobileModel.getModelName());
            return addMobileData(mobileInDB);
        }else {
            return null;
        }
    }

    @Override
    public MobileModel updateMobilePrice(MobileModel mobileModel, int id) {
        MobileModel mobileInDB=findById(id);
        if(mobileInDB!=null){
            mobileInDB.setPrice(mobileModel.getPrice());
            return addMobileData(mobileInDB);
        }else {
            return null;
        }
    }

    @Override
    public MobileModel updateMobileIMEI(MobileModel mobileModel, int id) {
        MobileModel mobileInDB=findById(id);
        if(mobileInDB!=null){
            mobileInDB.setImei(mobileModel.getImei());
            return addMobileData(mobileInDB);
        }else {
            return null;
        }
    }



}
