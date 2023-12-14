package com.coreTeam.MobileDataManupulationRestApi.playLoad;

import jakarta.persistence.Entity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ApiResponse {

    private String message;
    private String status;
}
