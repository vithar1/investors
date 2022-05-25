package cs.vsu.investor.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

/**
 * A Game.
 */
@Document(collection = "game")
@Data
public class Game implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    private String id;

    @Field("title")
    private String title;

    @Field("month")
    private Integer month;

    @DBRef
    @Field("player")
    @JsonIgnoreProperties(value = { "region", "players" }, allowSetters = true)
    private Set<Player> players = new HashSet<>();

    public void setPlayers(Set<Player> players) {
        if (this.players != null) {
            this.players.forEach(i -> i.setGame(null));
        }
        if (players != null) {
            players.forEach(i -> i.setGame(this));
        }
        this.players = players;
    }

    public void addPlayer(Player player) {
        this.players.add(player);
        player.setGame(this);
    }

    public void removePlayer(Player player) {
        this.players.remove(player);
        player.setGame(null);
    }
}
