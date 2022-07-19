
package main;

import api.ServerApi;
import client.ApplicationClient;
import config.Configuration;
import forms.UIApplication;
import server.ApplicationServer;

public class MainClass
{
    
    public static void main(String[] args)
    {
      
       
        boolean shouldStartServerInstance = false;
        
        if(args.length == 1)
        {
            String mode = args[0];
            
            if(Configuration.SERVER_ARG.equals(mode))
            {
                shouldStartServerInstance = true;
            }
            else if(Configuration.CLIENT_ARG.equals(mode))
            {
                shouldStartServerInstance = false;
            }
            else
            {
                System.err.println("No mode argument is provided. Terminating...");
                System.exit(1);
            }
        }
        else
        {
            shouldStartServerInstance = false;
        }
        
        boolean applicationStarted = false;
        
        try
        {
            if(shouldStartServerInstance)
            {
                ApplicationServer.Init();
                applicationStarted = true;
            }
            else
            {
                ServerApi api = ApplicationClient.Init();
                UIApplication app = new UIApplication(api);
                app.start();
                
                applicationStarted = true;
            }
        }
        catch(Exception e)
        {
            String message = applicationStarted ? "Unhandled exception." : "Application failed to start.";
            System.err.println(message);
            System.err.println(e.getLocalizedMessage());
            e.printStackTrace(System.err);
        }
        
    }
    
}
