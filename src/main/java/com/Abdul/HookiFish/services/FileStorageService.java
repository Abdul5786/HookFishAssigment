package com.Abdul.HookiFish.services;

import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

public interface FileStorageService
{
    public String uploadImage(MultipartFile multipartFile) throws IOException;

    public byte[] downloadImage(String fileName);

}
