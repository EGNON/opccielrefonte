package com.ged.service.standard.impl;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;
@Service
public class CustomMultipartFile {

    private byte[] input;

    public String getName() {
        return null;
    }

//    @Override
    public String getOriginalFilename() {
        return null;
    }

//    @Override
    public String getContentType() {
        return null;
    }
//    @Override
    public boolean isEmpty() {
        return input == null || input.length == 0;
    }

//    @Override
    public long getSize() {
        return input.length;
    }

//    @Override
    public byte[] getBytes() throws IOException {
        return input;
    }

//    @Override
    public InputStream getInputStream() throws IOException {
        return new ByteArrayInputStream(input);
    }

//    @Override
    public void transferTo(String destination, byte[] fToByte){
        try(FileOutputStream fos = new FileOutputStream(destination)) {
            fos.write(fToByte);
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}