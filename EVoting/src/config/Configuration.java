/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package config;

/**
 *
 * @author kosma
 */
public class Configuration
{
    public static final String API_ENDPOINT = "//localhost/evoting";
    public static final String SERVER_ARG = "--server";
    public static final String CLIENT_ARG = "--client";
    
    public static final String DB_URL = "jdbc:derby://localhost:1527/EVoting";
    public static final String DB_USERNAME = "root";
    public static final String DB_PASSWORD = "root";  
    
    private Configuration(){}
    
}
