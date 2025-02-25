package com.example.eventregistration.service;

import com.example.eventregistration.model.Registration;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class RegistrationService {

    private static final Logger logger = LoggerFactory.getLogger(RegistrationService.class);

    private final RedisTemplate<String, Registration> redisTemplate;

    public RegistrationService(RedisTemplate<String, Registration> redisTemplate) {
        this.redisTemplate = redisTemplate;
    }

    public void saveRegistration(Registration registration) {
        try {
            logger.info("Saving registration for email: {}", registration.getWorkEmail());
            redisTemplate.opsForValue().set(registration.getWorkEmail(), registration);
        } catch (Exception e) {
            logger.error("Error saving registration: {}", e.getMessage(), e);
            throw e;
        }
    }

    public Registration getRegistration(String workEmail) {
        logger.info("Getting registration for email: {}", workEmail);
        return redisTemplate.opsForValue().get(workEmail);
    }

    public List<Registration> getAllRegistrations() {
        logger.info("Getting all registrations");
        return redisTemplate.keys("*").stream()
                .map(key -> redisTemplate.opsForValue().get(key))
                .collect(Collectors.toList());
    }
}