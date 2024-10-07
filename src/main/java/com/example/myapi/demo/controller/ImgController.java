package com.example.myapi.demo.controller;

import com.example.myapi.demo.model.Image;
import com.example.myapi.demo.repository.ImgRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;


@RestController
public class ImgController {

    ImgRepository imgRepository = new ImgRepository();
    public ImgController() {
    }

    @CrossOrigin
    @PostMapping("/img/add")
    public ResponseEntity<String> addImg (@RequestParam("image") MultipartFile img) {
        try {
            Image image = new Image();
            image.setImage(img.getBytes());   // convert the image to a byte array
            return  (imgRepository.addImg(image)) ? ResponseEntity
                                                        .status(HttpStatus.OK)
                                                        .body("success")
                                                  : ResponseEntity
                                                        .status(HttpStatus.BAD_REQUEST)
                                                        .body("faild");
        } catch (IOException e) {
            System.out.println("Convert to bytes error.");
        }
        return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .body("faild");
    }

}
