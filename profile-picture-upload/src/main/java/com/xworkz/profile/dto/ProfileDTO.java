package com.xworkz.profile.dto;


import lombok.Data;
import org.springframework.stereotype.Component;

@Data
@Component
public class ProfileDTO {

    private int id;

    private String name;

    private String profilePic;


}
