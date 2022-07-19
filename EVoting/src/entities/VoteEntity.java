package entities;

import java.io.Serializable;

/**
 *
 * @author kosma
 */

public class VoteEntity  implements Serializable
{
    private Long id;
    private Long userId;
    private Integer vote;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Integer getVote() {
        return vote;
    }

    public void setVote(Integer vote) {
        this.vote = vote;
    }
}
