package com.backend.azkivam.accounts.configs;

import com.backend.azkivam.accounts.services.AccountService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AppConfig {
    @Bean
    public AccountService accountService() {
        return new AccountService();
    }
}
