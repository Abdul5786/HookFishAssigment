package com.Abdul.HookiFish.repositories;

import com.Abdul.HookiFish.entities.ImageData;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface FileStorageRepo extends JpaRepository<ImageData,Long>
{
    Optional<ImageData> findByName(String fileName);
}
