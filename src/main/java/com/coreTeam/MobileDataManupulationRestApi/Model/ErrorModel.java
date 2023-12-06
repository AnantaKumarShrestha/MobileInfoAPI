package com.coreTeam.MobileDataManupulationRestApi.Model;

import lombok.Data;
import org.springframework.http.HttpStatus;

@Data
public class ErrorModel {

    private String errorMessage;
    private String status;

    public ErrorModel(String message, String httpStatus) {
        this.errorMessage=message;
        this.status=httpStatus;
    }
}
