package org.example.context;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.example.ApplicationLauncher;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.validation.beanvalidation.MethodValidationPostProcessor;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

/**
 * The order of the PropertySource annotation matters, 
 * with the one specified at the bottom having precedence
 */
@Configuration
@ComponentScan(basePackageClasses = ApplicationLauncher.class)
@PropertySource("classpath:/application.properties")
@PropertySource(value = "classpath:/application-${spring.profiles.active}.properties",
        ignoreResourceNotFound = true) 
@EnableWebMvc
public class ApplicationConfiguration {
    
    /*@Bean
    @Scope(value = ConfigurableBeanFactory.SCOPE_PROTOTYPE) //create multiple instance of ur object. 
    public UserService userService(){
        return new UserService(); 
    }
    
    @Bean
    public InvoiceService invoiceService(UserService userService){
        return new InvoiceService(userService);
    }*/

    @Bean // 
    public MethodValidationPostProcessor methodValidationPostProcessor() {
        return new MethodValidationPostProcessor();
    }

    @Bean
    public ObjectMapper objectMapper() {
        return new ObjectMapper();
    }
}
