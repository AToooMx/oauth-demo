package com.shashok.oauth.demo.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.oauth2.client.authentication.OAuth2AuthenticationToken;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;
import java.util.Map;

@RestController
public class DemoController {

    private final Logger logger = LoggerFactory.getLogger(DemoController.class);

    @GetMapping("/memory/user")
    public String getUserInfo(Principal principal){
        String username = principal.getName();
        logger.info("Username = {}", username);
        return username;
    }

    @GetMapping("/oauth/user")
    public Map<String, Object> getUserInfo(OAuth2AuthenticationToken token){
        logger.info("User details = {}", token.getPrincipal().getAttributes());
        return token.getPrincipal().getAttributes();
    }
}
