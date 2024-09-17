package com.ged.service.standard;

import com.ged.dto.standard.DocumentDto;
import org.springframework.web.multipart.MultipartFile;

import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.List;
import java.util.Set;

public interface FileService {
    <T> Set<T> uploadFile(String path, List<MultipartFile> files, Set<T> entities) throws Throwable;
    Set<DocumentDto> uploadMedia(String path, List<MultipartFile> files, Set<DocumentDto> documents) throws Throwable;
    InputStream getResourceFile(String path, String fileName) throws FileNotFoundException;
    byte[] compressBytes(byte[] data);
    byte[] decompressBytes(byte[] data);
}
