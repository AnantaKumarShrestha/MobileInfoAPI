package com.coreTeam.MobileDataManupulationRestApi.Exception;

public class OwnerNotFoundException extends RuntimeException{
    public OwnerNotFoundException(){
        super("Owner not found ID: ");
    }
}
