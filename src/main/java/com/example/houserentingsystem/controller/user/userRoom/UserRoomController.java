package com.example.houserentingsystem.controller.user.userRoom;


import com.example.houserentingsystem.dto.user.UserRoomDto;
import com.example.houserentingsystem.service.impl.admin.AdminRoomServiceImpl;
import com.example.houserentingsystem.service.impl.user.RegisterServiceImpl;
import com.example.houserentingsystem.service.impl.user.UserRoomServiceImpl;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.io.IOException;

@Controller
@RequestMapping("/userRoom")
public class UserRoomController {
    private final UserRoomServiceImpl userRoomService;
    private final RegisterServiceImpl registerService;
    private final AdminRoomServiceImpl adminRoomService;

    public UserRoomController(UserRoomServiceImpl userRoomService, RegisterServiceImpl registerService, AdminRoomServiceImpl adminRoomService) {
        this.userRoomService = userRoomService;
        this.registerService = registerService;
        this.adminRoomService = adminRoomService;
    }

    @GetMapping("/home")
    public String openShowRoom(Model model) throws IOException {
        model.addAttribute("userRoomDto", new UserRoomDto());
        model.addAttribute("userRoomList", userRoomService.findAll());
//        model.addAttribute("userName",registerService.findAll());
        return "user/userRoomHome";
    }

    @GetMapping("/page")
    public String userRoomPage(Model model) {
        model.addAttribute("userRoomDto", new UserRoomDto());
        return "user/userRoomPage";
    }

    @GetMapping("/show")
    public String showRoom(Model model) throws IOException{
        model.addAttribute("adminRoomList",adminRoomService.findAllAdminRoom());
        model.addAttribute("registerList",registerService.findAll());
        return "user/adminRoomHome";
    }
    @PostMapping("/create")
    public String createUserRoom(@Valid @ModelAttribute UserRoomDto userRoomDto,
                                 BindingResult bindingResult, Model model) {
        if (!bindingResult.hasErrors()) {
            try {
                userRoomDto = userRoomService.save(userRoomDto);
                model.addAttribute("message", "userRoom successfully added");
            } catch (Exception e) {
                model.addAttribute("message", "userRoom failed added");
                e.printStackTrace();
            }
        }
        model.addAttribute("userRoomDto", userRoomDto);
        return "user/userRoomPage";
    }
    @GetMapping("/view/{id}")
    public String viewUserRoom(@PathVariable("id")Integer id, Model model) throws IOException {
        model.addAttribute("userRoomView",userRoomService.findById(id));
        return "user/viewUserRoom";
    }

    @GetMapping("/aview/{id}")
    public String viewAdminRoom(@PathVariable("id") Integer id , Model model) throws IOException {
        model.addAttribute("adminRoomView",adminRoomService.findById(id));
        return "user/viewAdminRoom";
    }

    @GetMapping("/update/{id}")
    public String updateUserRoom(@PathVariable ("id") Integer id,Model model) throws IOException {
        UserRoomDto userRoomDto=userRoomService.findById(id);
        model.addAttribute("userRoomDto",userRoomDto);
        return "user/userRoomUpdatePage";
    }
    @PostMapping("/update")
    public String updateUserRoom(@Valid @ModelAttribute UserRoomDto userRoomDto,
                                 BindingResult bindingResult, Model model)
    {
        if(!bindingResult.hasErrors()) {
            try {
                userRoomDto = userRoomService.updateUserRoom(userRoomDto);
                model.addAttribute("message", "User Room update Successfully");
            } catch (Exception e) {
                model.addAttribute("message", "User Room update  failed");
                e.printStackTrace();
            }
        }
        model.addAttribute("userRoomDto", userRoomDto);
        return "redirect:/userRoom/home";
    }

    @GetMapping("/delete/{id}")
    public String deleteUserRoom(@PathVariable("id") Integer id , Model model){
        userRoomService.deleteById(id);
        return "redirect:/userRoom/home";
    }
}
