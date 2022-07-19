/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package api;

import entities.UserEntity;
import entities.VoteEntity;
import java.rmi.Remote;
import java.rmi.RemoteException;

/**
 *
 * @author kosma
 */
public interface ServerApi extends Remote
{   
    //Login
    Long login(String userToken) throws RemoteException;
    
    // Users
    UserEntity getUser(long id) throws RemoteException;
    
    UserEntity getUserWithToken(String token) throws RemoteException;
    
    //Votes
    
    boolean hasVoted(long userId) throws RemoteException;
    
    VoteEntity getVote(long voteId) throws RemoteException;
    
    VoteEntity getVoteWithUserId(long userId) throws RemoteException;
    
    VoteEntity registerVote(long userId, int vote) throws RemoteException;
    
}
