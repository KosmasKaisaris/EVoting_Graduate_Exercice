/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package forms;

import api.ServerApi;
import entities.UserEntity;
import java.util.Stack;
import javax.swing.JFrame;

/**
 *
 * @author kosma
 */
public class UIApplication
{
    private ServerApi api;
    
    private JFrame rootForm;
    private UserEntity activeUser;
    
    private Stack<JFrame> navigationStack = new Stack();
    
    public UIApplication(ServerApi api)
    {
        this.api = api;
        rootForm = new Login(this);
    }

    public ServerApi getApi() {
        return api;
    }

    public UserEntity getActiveUser() {
        return activeUser;
    }
    
    public void logout()
    {
        this.activeUser = null;
    }

    public void setActiveUser(UserEntity activeUser) {
        this.activeUser = activeUser;
    }
    
    
    
    public void start()
    {
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(Login.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Login.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Login.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Login.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                rootForm.setVisible(true);
                navigationStack.push(rootForm);
            }
        });
    }
    
    // Go to vote screen
    public void showVoteForm(){
        rootForm.setVisible(false);
        
        VoteForm form = new VoteForm(this);
        form.setVisible(true);
        
        navigationStack.push(form);
    }
    
    public void back()
    {
        if(!navigationStack.isEmpty())
        {
            JFrame toClose = navigationStack.pop();
            
            toClose.setVisible(false);
            toClose.dispose();
            
            if(!navigationStack.isEmpty())
            {
                JFrame toShow = navigationStack.peek();
                toShow.setVisible(true);
                
                return;
            }
        }
        
        System.exit(0);
    }
    
    
    
}
