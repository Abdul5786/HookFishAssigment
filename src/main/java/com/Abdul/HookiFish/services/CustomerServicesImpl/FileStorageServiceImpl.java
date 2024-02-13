package com.Abdul.HookiFish.services.CustomerServicesImpl;

import com.Abdul.HookiFish.dtos.ImageUtils;
import com.Abdul.HookiFish.entities.ImageData;
import com.Abdul.HookiFish.repositories.FileStorageRepo;
import com.Abdul.HookiFish.services.FileStorageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Optional;

@Service
public class FileStorageServiceImpl implements FileStorageService
{
    @Autowired
    private FileStorageRepo  fileStorageRepo;

    @Override
    public String uploadImage(MultipartFile file) throws IOException
    {
        ImageData savedImage = fileStorageRepo.save(ImageData.builder()
                .name(file.getOriginalFilename())
                .type(file.getContentType())
                .imageData(ImageUtils.compressImage(file.getBytes())).build());

                if (savedImage!=null)
                {
                    return "Image uploaded successfully" +file.getOriginalFilename();
                }
                else return null;

    }

    public byte[] downloadImage(String fileName) {
        Optional<ImageData> dbImageData = fileStorageRepo.findByName(fileName);
        byte[] images=ImageUtils.decompressImage(dbImageData.get().getImageData());
        return images;
    }

}
