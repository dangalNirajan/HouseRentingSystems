package com.example.houserentingsystem.dto.admin;


import com.example.houserentingsystem.enums.RoomStatus;
import com.example.houserentingsystem.enums.RoomType;
import com.example.houserentingsystem.model.admin.AdminRegister;
import com.example.houserentingsystem.model.register.Register;
import lombok.*;

import javax.validation.Valid;
import javax.validation.constraints.NotEmpty;
import java.util.Date;


@Getter
@Setter
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class AdminRoomDto {
    private Integer id;
    @NotEmpty(message = "Name Cannot be Empty")
    private String name;

    private String email;
    private String contact;

    @NotEmpty(message = "Address Cannot be empty!!")
    private String address;
//    @NotEmpty(message = "Type of Room can't be empty")
    private RoomType roomType;

    private String adminRoomDate;
//    private LocalDate localDate;

//    private String filePath;
//
//
//    private MultipartFile multipartFile;

    private RoomStatus roomStatus;

    @NotEmpty()
    private String price;
    @NotEmpty(message = "Description Cannot be empty!!")
    private String description;

    private AdminRegister adminRegister;
}
