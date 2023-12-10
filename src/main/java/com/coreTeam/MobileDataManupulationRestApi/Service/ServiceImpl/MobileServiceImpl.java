package com.coreTeam.MobileDataManupulationRestApi.Service.ServiceImpl;

import com.coreTeam.MobileDataManupulationRestApi.Exception.MobileNotFoundException;
import com.coreTeam.MobileDataManupulationRestApi.Model.MobileModel;
import com.coreTeam.MobileDataManupulationRestApi.Service.MobileService;
import com.coreTeam.MobileDataManupulationRestApi.db.MobileDataRepo;
import com.coreTeam.MobileDataManupulationRestApi.dto.MobileDTO;
import jakarta.persistence.EntityNotFoundException;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class MobileServiceImpl implements MobileService {

    @Autowired
    private MobileDataRepo mobileDataRepo;

    @Autowired
    private ModelMapper modelMapper;

//    @Override
//    public List<MobileModel> getAllMobileList(){
//        return  mobileDataRepo.findAll();
//    }
//
//    @Override
//    public MobileModel addMobileData(MobileModel mobileModel) {
//      return mobileDataRepo.save(mobileModel);
//    }
//
//    @Override
//    public MobileModel  findById(int id){
//            return mobileDataRepo.findById(id).orElseThrow(()->new MobileNotFoundException(id));
//    }
//
//    @Override
//    public void deleteAllMobileData() {
//        mobileDataRepo.deleteAll();
//    }
//
//    @Override
//    public void deleteMobileDataById(int id) {
//      mobileDataRepo.deleteById(id);
//    }
//
//    @Override
//    public MobileModel updateMobileData(MobileModel mobileModel, int id){
//            if(findById(id)!=null){
//            mobileModel.setId(id);
//            return addMobileData(mobileModel);
//            }else {
//                return null;
//            }
//    }
//
//    @Override
//    public MobileModel updateMobileCompanyName(MobileModel mobileModel, int id){
//        MobileModel mobileInDB=findById(id);
//        if(mobileInDB!=null){
//            mobileInDB.setCompanyName(mobileModel.getCompanyName());
//            return addMobileData(mobileInDB);
//        }else {
//            return null;
//        }
//    }
//
//    @Override
//    public MobileModel updateMobileModelName(MobileModel mobileModel, int id){
//        MobileModel mobileInDB=findById(id);
//        if(mobileInDB!=null){
//            mobileInDB.setModelName(mobileModel.getModelName());
//            return addMobileData(mobileInDB);
//        }else {
//            return null;
//        }
//    }
//
//    @Override
//    public MobileModel updateMobilePrice(MobileModel mobileModel, int id){
//        MobileModel mobileInDB=findById(id);
//        if(mobileInDB!=null){
//            mobileInDB.setPrice(mobileModel.getPrice());
//            return addMobileData(mobileInDB);
//        }else {
//            return null;
//        }
//    }
//
//    @Override
//    public MobileModel updateMobileIMEI(MobileModel mobileModel, int id){
//        MobileModel mobileInDB=findById(id);
//        if(mobileInDB!=null){
//            mobileInDB.setImei(mobileModel.getImei());
//            return addMobileData(mobileInDB);
//        }else {
//            return null;
//        }
//    }
//
//    @Override
//    public List<MobileModel> listOfMobileBetweenTwoDates(LocalDate startDate, LocalDate endDate) {
//        return mobileDataRepo.findByDateCreatedBetween(startDate,endDate);
//    }
//
//    @Override
//    public List<MobileModel> listOfMobileCreatedOnSameDate(LocalDate localDate) {
//        return mobileDataRepo.findByDateCreated(localDate);
//    }
//
//    @Override
//    public List<MobileModel> listOfMobileSortedByCompanyName() {
//        return  mobileDataRepo.findAll(Sort.by(Sort.Direction.ASC, "companyName"));
//    }
//
//    @Override
//    public List<MobileModel> listOfMobileSortedByCompanyNameInDesc() {
//        return  mobileDataRepo.findAll(Sort.by(Sort.Direction.DESC, "companyName"));
//    }



    //======================================================================================



    @Override
    public List<MobileDTO> getAllMobileList(){
        return  mobileDataRepo.findAll().stream().map(mobile->mobileModelIntoMobileDto(mobile)).collect(Collectors.toList());
    }

    @Override
    public MobileDTO addMobileData(MobileDTO mobileDTO) {
        MobileModel mobileModel=mobileDtoIntoMobileModel(mobileDTO);
        mobileModel.setStatus("InActive");
        MobileModel savedMobile=mobileDataRepo.save(mobileModel);
        return mobileModelIntoMobileDto(savedMobile);
    }

    @Override
    public MobileDTO updateMobileData(MobileDTO mobileDTO) {
        MobileModel mobileModel=mobileDtoIntoMobileModel(mobileDTO);
        MobileModel savedMobile=mobileDataRepo.save(mobileModel);
        return mobileModelIntoMobileDto(savedMobile);
    }

    @Override
    public MobileDTO  findById(UUID id){
        return mobileModelIntoMobileDto(mobileDataRepo.findById(id).orElseThrow(()->new MobileNotFoundException(id)));
    }

    @Override
    public void deleteAllMobileData() {
        mobileDataRepo.deleteAll();
    }

    @Override
    public void deleteMobileDataById(UUID id) {
        mobileDataRepo.deleteById(id);
    }

    @Override
    public MobileDTO updateMobileData(MobileDTO mobileDTO, UUID id){
        if(findById(id)!=null){
            mobileDTO.setId(id);
            return updateMobileData(mobileDTO);
        }else {
            return null;
        }
    }

    @Override
    public MobileDTO updateMobileCompanyName(MobileDTO mobileDTO, UUID id){
        MobileDTO mobileInDB=findById(id);
        if(mobileInDB!=null){
            mobileInDB.setCompanyName(mobileDTO.getCompanyName());
            return updateMobileData(mobileInDB);
        }else {
            return null;
        }
    }

    @Override
    public MobileDTO updateMobileModelName(MobileDTO mobileDTO, UUID id){
        MobileDTO mobileInDB=findById(id);
        if(mobileInDB!=null){
            mobileInDB.setModelName(mobileDTO.getModelName());
            return updateMobileData(mobileInDB);
        }else {
            return null;
        }
    }

    @Override
    public MobileDTO updateMobilePrice(MobileDTO mobileDTO, UUID id){
        MobileDTO mobileInDB=findById(id);
        if(mobileInDB!=null){
            mobileInDB.setPrice(mobileDTO.getPrice());
            return updateMobileData(mobileInDB);
        }else {
            return null;
        }
    }

    @Override
    public MobileDTO updateMobileIMEI(MobileDTO mobileDTO, UUID id){
        MobileDTO mobileInDB=findById(id);
        if(mobileInDB!=null){
            mobileInDB.setImei(mobileDTO.getImei());
            return updateMobileData(mobileInDB);
        }else {
            return null;
        }
    }

    @Override
    public List<MobileDTO> listOfMobileBetweenTwoDates(LocalDate startDate, LocalDate endDate) {
        return mobileDataRepo.findByDateCreatedBetween(startDate,endDate).stream().map(this::mobileModelIntoMobileDto).collect(Collectors.toList());
    }

    @Override
    public List<MobileDTO> listOfMobileCreatedOnSameDate(LocalDate localDate) {
        return mobileDataRepo.findByDateCreated(localDate).stream().map(this::mobileModelIntoMobileDto).collect(Collectors.toList());
    }

    @Override
    public List<MobileDTO> listOfMobileSortedByCompanyName() {
        return  mobileDataRepo.findAll(Sort.by(Sort.Direction.ASC, "companyName")).stream().map(this::mobileModelIntoMobileDto).collect(Collectors.toList());
    }

    @Override
    public List<MobileDTO> listOfMobileSortedByCompanyNameInDesc() {
        return  mobileDataRepo.findAll(Sort.by(Sort.Direction.DESC, "companyName")).stream().map(this::mobileModelIntoMobileDto).collect(Collectors.toList());
    }

    @Override
    public MobileDTO deactivateMobile(UUID id) {
        MobileDTO mobileDTO=findById(id);
        mobileDTO.setStatus("Deactivated");
        return updateMobileData(mobileDTO);
    }

    @Override
    public MobileDTO activateMobile(UUID id) {
        MobileDTO mobileDTO=findById(id);
        mobileDTO.setStatus("active");
        return updateMobileData(mobileDTO);
    }

    @Override
    public MobileModel mobileDtoIntoMobileModel(MobileDTO mobileDTO) {

//        MobileModel mobileModel=new MobileModel();
//        mobileModel.setId(mobileDTO.getId());
//        mobileModel.setCompanyName(mobileDTO.getCompanyName());
//        mobileModel.setModelName(mobileDTO.getModelName());
//        mobileModel.setPrice(mobileDTO.getPrice());
//        mobileModel.setImei(mobileDTO.getImei());
//        mobileModel.setDateCreated(mobileDTO.getDateCreated());
//        mobileModel.setLastUpdate(mobileDTO.getLastUpdate());

   ;     MobileModel mobileModel=modelMapper.map(mobileDTO,MobileModel.class);

        return mobileModel;
    }

    @Override
    public MobileDTO mobileModelIntoMobileDto(MobileModel mobileModel) {

//        MobileDTO mobileDTO=new MobileDTO();
//        mobileDTO.setId(mobileModel.getId());
//        mobileDTO.setCompanyName(mobileModel.getCompanyName());
//        mobileDTO.setModelName(mobileModel.getModelName());
//        mobileDTO.setPrice(mobileModel.getPrice());
//        mobileDTO.setImei(mobileModel.getImei());
//        mobileDTO.setDateCreated(mobileModel.getDateCreated());
//        mobileModel.setLastUpdate(mobileModel.getLastUpdate());

        MobileDTO mobileDTO=modelMapper.map(mobileModel,MobileDTO.class);

        return mobileDTO;
    }


}
