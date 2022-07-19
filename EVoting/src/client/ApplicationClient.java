/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package client;

import api.ServerApi;
import config.Configuration;
import java.rmi.Naming;

/**
 *
 * @author kosma
 */
public class ApplicationClient {
    
    private ApplicationClient(){}
    
    public static ServerApi Init() throws Exception {
        System.out.println("Starting client...");
        ServerApi api = (ServerApi) Naming.lookup(Configuration.API_ENDPOINT);
        System.out.println("Started client.");
        
        return api;
    }
    
}
