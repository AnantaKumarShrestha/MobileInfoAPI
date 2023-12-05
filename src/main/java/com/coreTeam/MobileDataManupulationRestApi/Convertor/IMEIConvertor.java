package com.coreTeam.MobileDataManupulationRestApi.Convertor;


import jakarta.persistence.AttributeConverter;



public class IMEIConvertor implements AttributeConverter<String,String> {

    @Override
    public String convertToDatabaseColumn(String s) {
        return null;
    }

    @Override
    public String convertToEntityAttribute(String s) {
        return null;
    }
}
