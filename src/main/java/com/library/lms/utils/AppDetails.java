package com.library.lms.utils;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class AppDetails {
    private String appName;
    private String appVersion;
    private String developerName;
    private String developerId;
}
