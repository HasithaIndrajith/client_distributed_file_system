package com.example.client.service;

import java.io.IOException;
import java.rmi.Remote;
import java.rmi.RemoteException;

public interface NameNodeI extends Remote {
     void uploadFile(byte[] fileData) throws RemoteException,IOException;
     byte[] getFile(int fileId) throws RemoteException;
}
