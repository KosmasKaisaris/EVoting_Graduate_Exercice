
package entities;

import java.io.Serializable;

/**
 *
 * @author kosma
 * 
 */

public class UserEntity implements Serializable
{
    private Long id;
    private String userToken;
    
    public UserEntity(){}

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUserToken() {
        return userToken;
    }

    public void setUserToken(String userToken) {
        this.userToken = userToken;
    }
    
}
