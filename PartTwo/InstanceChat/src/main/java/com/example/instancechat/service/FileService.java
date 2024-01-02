package com.example.instancechat.service;

import com.example.instancechat.repository.FileRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.IOException;

@Service
public class FileService {
    @Autowired
    private FileRepository fileRepository;
    private final static String path = "src/main/resources/storage";

    public boolean isExistFile(String fileName) {
        //Nối đường dẫn với tên file để kiểm tra file
        File file = new File(path + File.separator + fileName);
        return file.exists() && file.isFile();
    }

    public void addPermission(String sender, String receiver, String fileName) throws IOException {
        fileRepository.save(sender, receiver, fileName);
    }

    public boolean hasPermission(String username, String filename) {
        return fileRepository.checkPermission(username, filename);
    }
}
