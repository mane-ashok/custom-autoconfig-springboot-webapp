package org.ashok.custom.autoconfig;

import org.springframework.boot.context.properties.ConfigurationProperties;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@ConfigurationProperties(prefix = "custom.autoconfig.datasource")
public class CustomAutoConfigDataSourceProperties {
	
    private String username;
    private String password;
    private String url;
        
}
