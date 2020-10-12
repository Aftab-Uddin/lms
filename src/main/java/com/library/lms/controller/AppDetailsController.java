package com.library.lms.controller;

import com.library.lms.utils.AppDetails;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/details")
public class AppDetailsController {

    @GetMapping("")
    public AppDetails getAppDetails() {

        return new AppDetails("Library Management", "1.0.0", "Aftab Uddin", "2017000000140");
    }
}
