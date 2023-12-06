package com.coreTeam.MobileDataManupulationRestApi.Exception;

public class MobileNotFoundException extends RuntimeException{
    public MobileNotFoundException(int id){
        super(String.format("Mobile Not Found, Mobile ID: %d",id));
    }

}
