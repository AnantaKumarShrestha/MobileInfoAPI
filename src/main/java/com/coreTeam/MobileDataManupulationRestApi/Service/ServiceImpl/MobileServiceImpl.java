package com.coreTeam.MobileDataManupulationRestApi.Service.ServiceImpl;

import com.coreTeam.MobileDataManupulationRestApi.Exception.MobileNotFoundException;
import com.coreTeam.MobileDataManupulationRestApi.Model.MobileModel;
import com.coreTeam.MobileDataManupulationRestApi.Service.MobileService;
import com.coreTeam.MobileDataManupulationRestApi.db.MobileRepo;
import com.coreTeam.MobileDataManupulationRestApi.dto.MobileDTO;
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
    private MobileRepo mobileRepo;

    @Autowired
    private ModelMapper modelMapper;


    @Override
    public List<MobileDTO> getAllMobileList(){
     //   return  mobileRepo.findAll().stream().map(mobile->mobileModelIntoMobileDto(mobile)).collect(Collectors.toList());
        return  mobileRepo.getAllMobile().stream().map(mobile->mobileModelIntoMobileDto(mobile)).collect(Collectors.toList());
    }

    @Override
    public MobileDTO addMobile(MobileDTO mobileDTO) {
        MobileModel mobile=mobileDtoIntoMobileModel(mobileDTO);
        mobile.setStatus("InActive");
        MobileModel savedMobile= mobileRepo.save(mobile);
        return mobileModelIntoMobileDto(savedMobile);
    }

        @Override
    public MobileDTO updateMobile(MobileDTO mobileDTO) {
        MobileModel mobile=mobileDtoIntoMobileModel(mobileDTO);
        MobileModel savedMobile= mobileRepo.save(mobile);
        return mobileModelIntoMobileDto(savedMobile);
    }

    @Override
    public MobileDTO  findById(UUID id){
       // return mobileModelIntoMobileDto(mobileRepo.findById(id).orElseThrow(()->new MobileNotFoundException(id)));
        return mobileModelIntoMobileDto(mobileRepo.findById(id).orElseThrow(()->new MobileNotFoundException(id)));
    }

    @Override
    public void deleteAllMobile() {
        mobileRepo.deleteAll();
      //  return "Deleted Successfully";
    }

    @Override
    public void deleteMobileByID(UUID id) {
        mobileRepo.deleteById(id);
    }

    @Override
    public MobileDTO updateMobile(MobileDTO mobileDTO, UUID id){
        if(findById(id)!=null){
            mobileDTO.setId(id);
            return updateMobile(mobileDTO);
        }else {
            return null;
        }
    }

    @Override
    public MobileDTO updateMobileCompanyName(MobileDTO mobileDTO, UUID id){
        MobileModel mobileInDB=getMobileById(id);
        if(mobileInDB!=null){
            mobileInDB.setCompanyName(mobileDTO.getCompanyName());
            return mobileModelIntoMobileDto(mobileRepo.save(mobileInDB));
        }else {
            return null;
        }
    }

    @Override
    public MobileDTO updateMobileModelName(MobileDTO mobileDTO, UUID id){
        MobileModel mobileInDB=getMobileById(id);
        if(mobileInDB!=null){
            mobileInDB.setModelName(mobileDTO.getModelName());
            return mobileModelIntoMobileDto(mobileRepo.save(mobileInDB));
        }else {
            return null;
        }
    }

    @Override
    public MobileDTO updateMobilePrice(MobileDTO mobileDTO, UUID id){

        MobileModel mobileInDB=getMobileById(id);
        if(mobileInDB!=null){
            mobileInDB.setPrice(mobileDTO.getPrice());
            return mobileModelIntoMobileDto(mobileRepo.save(mobileInDB));
        }else {
            return null;
        }
    }

    @Override
    public MobileDTO updateMobileIMEI(MobileDTO mobileDTO, UUID id){

        MobileModel mobileInDB=getMobileById(id);
        if(mobileInDB!=null){
            mobileInDB.setImei(mobileDTO.getImei());
            return mobileModelIntoMobileDto(mobileRepo.save(mobileInDB));
        }else {
            return null;
        }

    }

    @Override
    public List<MobileDTO> listOfMobileBetweenTwoDates(LocalDate startDate, LocalDate endDate) {
      //  return mobileRepo.findByDateCreatedBetween(startDate,endDate).stream().map(this::mobileModelIntoMobileDto).collect(Collectors.toList());
        return mobileRepo.getMobileListCratedBetweenTwoDates(startDate,endDate).stream().map(this::mobileModelIntoMobileDto).collect(Collectors.toList());
    }

    @Override
    public List<MobileDTO> listOfMobileCreatedOnSameDate(LocalDate dateCreated) {
      //  return mobileRepo.findByDateCreated(localDate).stream().map(this::mobileModelIntoMobileDto).collect(Collectors.toList());
        return mobileRepo.getMobileListCratedOnSameDay(dateCreated).stream().map(this::mobileModelIntoMobileDto).collect(Collectors.toList());
    }

    @Override
    public List<MobileDTO> listOfMobileSortedByCompanyName() {
    //    return  mobileRepo.findAll(Sort.by(Sort.Direction.ASC, "companyName")).stream().map(this::mobileModelIntoMobileDto).collect(Collectors.toList());
        return  mobileRepo.getMobileAscOrder().stream().map(this::mobileModelIntoMobileDto).collect(Collectors.toList());
    }

    @Override
    public List<MobileDTO> listOfMobileSortedByCompanyNameInDesc() {
      //  return  mobileRepo.findAll(Sort.by(Sort.Direction.DESC, "companyName")).stream().map(this::mobileModelIntoMobileDto).collect(Collectors.toList());
        return  mobileRepo.getMobileListDesOrder().stream().map(this::mobileModelIntoMobileDto).collect(Collectors.toList());
    }

    @Override
    public MobileDTO deactivateMobile(UUID id) {
//        MobileDTO mobileDTO=findById(id);
//        mobileDTO.setStatus("Deactivated");
//        return updateMobileData(mobileDTO);
        MobileModel mobile=getMobileById(id);
        mobile.setStatus("Deactivated");
        return mobileModelIntoMobileDto(mobileRepo.save(mobile));
    }

    @Override
    public MobileDTO activateMobile(UUID id) {
 //       MobileDTO mobileDTO=findById(id);
//        mobileDTO.setStatus("active");
//        return updateMobileData(mobileDTO);
        MobileModel mobile=getMobileById(id);
        mobile.setStatus("active");
        return mobileModelIntoMobileDto(mobileRepo.save(mobile));
    }

    @Override
    public MobileModel getMobileById(UUID id) {
        return mobileRepo.findById(id).orElseThrow(()->new MobileNotFoundException(id));
    }

    @Override
    public MobileModel mobileDtoIntoMobileModel(MobileDTO mobileDTO) {
   ;     return modelMapper.map(mobileDTO,MobileModel.class);
    }

    @Override
    public MobileDTO mobileModelIntoMobileDto(MobileModel mobileModel) {
        return modelMapper.map(mobileModel,MobileDTO.class);
    }


}
