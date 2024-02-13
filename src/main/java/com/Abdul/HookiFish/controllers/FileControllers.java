package com.Abdul.HookiFish.controllers;

import com.Abdul.HookiFish.services.FileStorageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@RestController
@RequestMapping("/api/file")
public class FileControllers
{

        @Autowired
        private FileStorageService fileStorageService;

       @PostMapping(value = "/addImage")
      public ResponseEntity<?> uploadImage(@RequestParam("image")MultipartFile file) throws IOException {
          String uploaded = fileStorageService.uploadImage(file);
          return ResponseEntity.status(HttpStatus.OK)
                  .body(uploaded);
      }

      @GetMapping(value ="/{fileName}")
     public ResponseEntity<?> downloadImage(@PathVariable String fileName)
     {
         byte[] images = fileStorageService.downloadImage(fileName);
         return ResponseEntity.status(HttpStatus.OK)
                 .contentType(MediaType.valueOf("image/png"))
                 .body(images);
     }

}
