/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package server;

import api.ServerApi;
import config.Configuration;
import db.DBConnect;
import entities.UserEntity;
import entities.VoteEntity;
import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.server.UnicastRemoteObject;

/**
 *
 * @author kosma
 */
public class ApplicationServer extends UnicastRemoteObject implements ServerApi
{
    
    public ApplicationServer() throws RemoteException {
        super(0);    
    }
    
    @Override
    public Long login(String userId) {
        UserEntity user = DBConnect.findUserByToken(userId);
        return user != null ? user.getId() : null;
    }

    @Override
    public UserEntity getUser(long id) {
        UserEntity user = DBConnect.findUserById(id);
        return user;
    }

    @Override
    public UserEntity getUserWithToken(String token) throws RemoteException {
        UserEntity user = DBConnect.findUserByToken(token);
        return user;
    }

    @Override
    public boolean hasVoted(long userId) {
        VoteEntity vote = DBConnect.findVoteByUserId(userId);
        return vote != null;
    }

    @Override
    public VoteEntity getVote(long voteId) {
        VoteEntity vote = DBConnect.findVoteById(voteId);
        return vote;
    }
    
    @Override
    public VoteEntity getVoteWithUserId(long userId) {
        VoteEntity vote = DBConnect.findVoteByUserId(userId);
        return vote;
    }

    @Override
    public VoteEntity registerVote(long userId, int vote) {
        VoteEntity voteEntity = new VoteEntity();
        voteEntity.setId(null);
        voteEntity.setUserId(userId);
        voteEntity.setVote(vote);
        
        return DBConnect.saveVote(voteEntity);
    }
    
    //// RMI init
    
    public static void Init() throws RemoteException, MalformedURLException {
        System.out.println("Starting e-voting RMI server...");

        try { //special exception handler for registry creation
            LocateRegistry.createRegistry(1099); 
            System.out.println("RMI registry created.");
        } catch (RemoteException ignored) {
            //do nothing, error means registry already exists
            System.out.println("RMI registry already exists.");
        }
           
        //Instantiate RmiServer
        ApplicationServer server = new ApplicationServer();
        
        // Bind this object instance to the name "RmiServer"
        Naming.rebind(Configuration.API_ENDPOINT, server);
        System.out.println("Started e-voting server.");
    }
    
    
}
