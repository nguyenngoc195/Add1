/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package test;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

/**
 *
 * @author Lan
 */
public class MethodFromFar extends UnicastRemoteObject implements RemoteFormFar{
    public MethodFromFar() throws RemoteException {
    super();
    }

    @Override
    public float TinhKM(float a) throws RemoteException {
      return a*0.621371f;
    }
    
    
}
