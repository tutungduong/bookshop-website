package com.bookshop.service.general.impl;

import com.bookshop.dto.general.UploadedImageResponse;
import com.bookshop.exception.FileStorageException;
import com.bookshop.exception.StorageFileNotFoundException;
import com.bookshop.service.general.ImageService;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.Objects;

// Reference: https://github.com/int28h/Server/blob/master/src/main/java/com/int28h/server/service/FileStorageService.java#L22

@Service
public class ImageServiceImpl implements ImageService {

    private static final Path IMAGE_DIR = Paths.get(System.getProperty("user.dir")).resolve("image-dir");

    @Override
    public UploadedImageResponse store(MultipartFile image) {

        // Normalize file name
        String imageName = StringUtils.cleanPath(Objects.requireNonNull(image.getOriginalFilename()));

        try {
            // Check if the file's name contains invalid characters
            if (imageName.contains("..")) {
                throw new FileStorageException("Sorry! Filename contains invalid path sequence " + imageName);
            }
             // Copy file to the target location (Replacing existing file with the same name)
            Path targetLocation = Files.createTempFile(IMAGE_DIR, "img-", ".jpg");
            try (InputStream fileContent = image.getInputStream()) {
                Files.copy(fileContent, targetLocation, StandardCopyOption.REPLACE_EXISTING);
            }

            String uploadedImageName = targetLocation.getFileName().toString();

            String uploadedImagePath = ServletUriComponentsBuilder.fromCurrentContextPath()
                    .path("/images/")
                    .path(uploadedImageName)
                    .toUriString();

            return new UploadedImageResponse(uploadedImageName, uploadedImagePath, image.getContentType(), image.getSize());
        } catch (IOException e) {
            throw new FileStorageException("Could not store file " + imageName + ". Please try again!", e);
        }
    }

    @Override
    public Resource load(String imageName) {
       try {
            Path imagePath = IMAGE_DIR.resolve(imageName).normalize();
            Resource resource = new UrlResource(imagePath.toUri());
            if (resource.exists() || resource.isReadable()) {
                return resource;
            } else {
                throw new StorageFileNotFoundException("File not found " + imageName);
            }
        } catch (MalformedURLException e) {
            throw new StorageFileNotFoundException("File not found " + imageName, e);
        }
    }

    @Override
    public void delete(String imageName) {
         try {
            Path imagePath = IMAGE_DIR.resolve(imageName).normalize();
            Resource resource = new UrlResource(imagePath.toUri());
            if (resource.exists() || resource.isReadable()) {
                Files.delete(imagePath);
            } else {
                throw new StorageFileNotFoundException("File not found " + imageName);
            }
        } catch (MalformedURLException e) {
            throw new StorageFileNotFoundException("File not found " + imageName, e);
        } catch (IOException e) {
            throw new FileStorageException("File not found " + imageName + ". Please try again!", e);
        }
    }

    public ImageServiceImpl() {
        if (!Files.exists(IMAGE_DIR)) {
            try {
                Files.createDirectories(IMAGE_DIR);
            } catch (IOException e) {
                throw new FileStorageException("Could not create the directory where the uploaded files will be stored.", e);
            }
        }
    }
}
