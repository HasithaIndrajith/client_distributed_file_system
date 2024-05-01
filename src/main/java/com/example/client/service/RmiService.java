package com.example.client.service;

import com.example.client.utils.Configuration;
import org.springframework.stereotype.Service;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

@Service
public class RmiService {

   private Configuration config;

    public RmiService() {
        this.config = Configuration.getInstance();;
    }

    public void uploadFile(File file) {
        try {
            Registry registry = LocateRegistry.getRegistry(config.getProperty("nameServer.ip"), Integer.parseInt(config.getProperty("nameServer.port")));
            NameNodeI nameNodeI = (NameNodeI) registry.lookup("NameNodeStub");
            byte[] fileData = Files.readAllBytes(file.toPath());
            nameNodeI.uploadFile(fileData);
        } catch (IOException | NotBoundException  e) {
            throw new RuntimeException("Error uploading file", e);
        }
    }

    public byte[] getFile(int fileId) {
        try {
            Registry registry = LocateRegistry.getRegistry(config.getProperty("nameServer.ip"), Integer.parseInt(config.getProperty("nameServer.port")));
            NameNodeI nameNodeI = (NameNodeI) registry.lookup("NameNodeStub");
            return nameNodeI.getFile(fileId);
        } catch (RemoteException | NotBoundException e) {
            throw new RuntimeException("Error getting file", e);
        }
    }
}
