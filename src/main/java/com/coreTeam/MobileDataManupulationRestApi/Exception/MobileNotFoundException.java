package com.coreTeam.MobileDataManupulationRestApi.Exception;

import java.util.UUID;

public class MobileNotFoundException extends RuntimeException{
    public MobileNotFoundException(UUID id){
        super(String.format("Mobile Not Found, Mobile ID:%s",id));
    }

}
