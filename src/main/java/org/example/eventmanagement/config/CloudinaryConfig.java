package org.example.eventmanagement.config;



import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class CloudinaryConfig {

    @Bean
    public Cloudinary cloudinary() {
        return new Cloudinary(ObjectUtils.asMap(
                "cloud_name", "dpm5ufhkl",
                "api_key", "664511814296786",
                "api_secret", "e7HP7amLewyBY_ZoQzQ6vavdi-o"
        ));
    }
}

