package com.cash.util;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import java.util.List;

@Data
@Component
@NoArgsConstructor
@ConfigurationProperties(prefix = "register")
public class RegisterPropertiesUtil {

    private String title;
    private String moduleName;
    private String moduleDescription;
    private List<String> typeRegister;
    private List<String> categoryRegister;
    private List<String> statusRegister;

}
