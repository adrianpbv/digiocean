package com.digiocean.examp.digitalocean;

import com.digiocean.examp.digitalocean.data.UserDataMapper;
import com.digiocean.examp.digitalocean.data.UserDemoDto;
import com.digiocean.examp.digitalocean.data.UserDemoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("hello")
@RequiredArgsConstructor
public class HelloController {
    private final UserDemoRepository userDemoRepository;


    @GetMapping
    public String hello(@RequestParam(value = "name", defaultValue = "World") String name) {
        return "Hello " + name + "!. Welcome to Spring Boot on DigitalOcean";
    }

    @GetMapping("all")
    public List<UserDemoDto> getAllUser() {
        return userDemoRepository.findAll().stream()
                .map(UserDataMapper::toDto)
                .toList();
    }
}
