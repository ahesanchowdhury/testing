package com.xworkz.profile.controller;

import com.xworkz.profile.entity.UserEntity;
import com.xworkz.profile.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

@Controller
@RequestMapping("/")
public class UserController {

    @Autowired
    private UserService userService;

    // folder to store uploaded images
    private static final String UPLOAD_DIR = "uploads";

    @PostMapping("upload")
    public String upload(@RequestParam("userName") String userName,
                         @RequestParam("profilePic") MultipartFile profilePic,
                         Model model) {
        
        if (profilePic.isEmpty()) {
            model.addAttribute("error", "Please select a file to upload");
            return "index";
        }

        try {
            // create uploads folder if not exists
            File dir = new File(UPLOAD_DIR);
            if (!dir.exists()) {
                dir.mkdirs();
            }

            // create unique filename
            String originalFilename = profilePic.getOriginalFilename();
            String fileExtension = "";
            if (originalFilename != null && originalFilename.contains(".")) {
                fileExtension = originalFilename.substring(originalFilename.lastIndexOf("."));
            }
            String fileName = userName.replaceAll("\\s+", "_") + "_" + System.currentTimeMillis() + fileExtension;
            
            // save file to disk
            Path filePath = Paths.get(UPLOAD_DIR, fileName);
            Files.write(filePath, profilePic.getBytes());

            // Save user info to database
            UserEntity user = new UserEntity();
            user.setUserName(userName);
            user.setProfilePic(filePath.toString());
            userService.save(user);

            model.addAttribute("message", "File uploaded successfully: " + fileName);
            return "redirect:/users";
            
        } catch (IOException e) {
            model.addAttribute("error", "Failed to upload file: " + e.getMessage());
            return "index";
        }
    }

    @GetMapping("users")
    public String listUsers(Model model) {
        model.addAttribute("users", userService.findAll());
        return "users"; // JSP page
    }

    @GetMapping("profile")
    public String profile() {
        return "profile"; // JSP form page
    }
}
