package com.example.myapi.demo.controller;

import com.example.myapi.demo.model.EmailForm;
import com.example.myapi.demo.repository.EmailRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class EmailController {

    EmailRepository emailRepository = new EmailRepository();

    public EmailController() {
    }

    @CrossOrigin
    @PostMapping("/email/add")
    public ResponseEntity<String> addEmail (@RequestBody EmailForm emailForm) {
        return  (emailRepository.addEmail(emailForm)) ? ResponseEntity
                                                            .status(HttpStatus.OK)
                                                            .body("success")
                                                       : ResponseEntity
                                                            .status(HttpStatus.BAD_REQUEST)
                                                            .body("faild");
    }

}
