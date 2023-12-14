package com.coreTeam.MobileDataManupulationRestApi.Exception;

import java.util.UUID;

public class OwnerNotFoundException extends RuntimeException{
    public OwnerNotFoundException(UUID id){
        super(String.format("Owner not found ID: %s",id));
    }
}
