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

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.util.Base64;
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
        return  mobileRepo.getAllMobile().stream().map(mobile->mobileModelIntoMobileDto(mobile)).collect(Collectors.toList());
    }

    @Override
    public MobileDTO addMobile(MobileDTO mobileDTO) throws IOException {
        MobileModel mobile=mobileDtoIntoMobileModel(mobileDTO);
        mobile.setStatus("InActive");
        MobileModel savedMobile= mobileRepo.save(mobile);
        savedMobile.setImage("/owner-api/owener/mobile/productphoto/"+savedMobile.getId()+savedMobile.getDateCreated()+".png");
        byte[] decodedBytes= Base64.getDecoder().decode(mobileDTO.getImage());
        Files.write(Path.of("src/main/resources/static/images/mobileimage/" + savedMobile.getId()+savedMobile.getDateCreated()+".png"), decodedBytes);
        return mobileModelIntoMobileDto(mobileRepo.save(savedMobile));
    }

        @Override
    public MobileDTO updateMobile(MobileDTO mobileDTO) {
        MobileModel mobile=mobileDtoIntoMobileModel(mobileDTO);
        MobileModel savedMobile= mobileRepo.save(mobile);
        return mobileModelIntoMobileDto(savedMobile);
    }

    @Override
    public MobileDTO  findById(UUID id){
        return mobileModelIntoMobileDto(mobileRepo.findById(id).orElseThrow(()->new MobileNotFoundException(id)));
    }

    private void deletePhoto(MobileModel mobile){
        String pathname="src/main/resources/static/images/mobileimage/";
        String photo=""+mobile.getId()+mobile.getDateCreated()+".png";
        try {
            Files.delete(Paths.get(pathname,photo));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public String deleteAllMobile() {
        mobileRepo.findAll().stream().forEach(mobile->deletePhoto(mobile));
        mobileRepo.deleteAll();
        return "Deleted All Mobile Successfully";
    }

    @Override
    public String deleteMobileByID(UUID id) {
       return mobileRepo.findById(id).map(mobile->{
            deletePhoto(mobile);
            mobileRepo.deleteById(id);
            return "Deleted Successfully";
        }).orElseThrow(()->new MobileNotFoundException(id));

    }

    @Override
    public MobileDTO updateMobile(MobileDTO mobileDTO, UUID id){
            findById(id);
            mobileDTO.setId(id);
            return updateMobile(mobileDTO);

    }

    @Override
    public MobileDTO updateMobileCompanyName(MobileDTO mobileDTO, UUID id){

            MobileModel mobileInDB=getMobileById(id);
            mobileInDB.setCompanyName(mobileDTO.getCompanyName());
            return mobileModelIntoMobileDto(mobileRepo.save(mobileInDB));

    }

    @Override
    public MobileDTO updateMobileModelName(MobileDTO mobileDTO, UUID id){

            MobileModel mobileInDB=getMobileById(id);
            mobileInDB.setModelName(mobileDTO.getModelName());
            return mobileModelIntoMobileDto(mobileRepo.save(mobileInDB));

    }

    @Override
    public MobileDTO updateMobilePrice(MobileDTO mobileDTO, UUID id){

            MobileModel mobileInDB=getMobileById(id);
            mobileInDB.setPrice(mobileDTO.getPrice());
            return mobileModelIntoMobileDto(mobileRepo.save(mobileInDB));

    }

    @Override
    public MobileDTO updateMobileIMEI(MobileDTO mobileDTO, UUID id){

            MobileModel mobileInDB=getMobileById(id);
            mobileInDB.setImei(mobileDTO.getImei());
            return mobileModelIntoMobileDto(mobileRepo.save(mobileInDB));

    }

    @Override
    public List<MobileDTO> listOfMobileBetweenTwoDates(LocalDate startDate, LocalDate endDate) {
        return mobileRepo.getMobileListCratedBetweenTwoDates(startDate,endDate).stream().map(this::mobileModelIntoMobileDto).collect(Collectors.toList());
    }

    @Override
    public List<MobileDTO> listOfMobileCreatedOnSameDate(LocalDate dateCreated) {
        return mobileRepo.getMobileListCratedOnSameDay(dateCreated).stream().map(this::mobileModelIntoMobileDto).collect(Collectors.toList());
    }

    @Override
    public List<MobileDTO> listOfMobileSortedByCompanyName() {
        return  mobileRepo.getMobileAscOrder().stream().map(this::mobileModelIntoMobileDto).collect(Collectors.toList());
    }

    @Override
    public List<MobileDTO> listOfMobileSortedByCompanyNameInDesc() {
        return  mobileRepo.getMobileListDesOrder().stream().map(this::mobileModelIntoMobileDto).collect(Collectors.toList());
    }

    @Override
    public MobileDTO deactivateMobile(UUID id) {
        MobileModel mobile=getMobileById(id);
        mobile.setStatus("Deactivated");
        return mobileModelIntoMobileDto(mobileRepo.save(mobile));
    }

    @Override
    public MobileDTO activateMobile(UUID id) {
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
