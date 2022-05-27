package cs.vsu.investor.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;
import javax.validation.constraints.*;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.HashIndexed;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

/**
 * A Player.
 */
@Document(collection = "player")
@Data
public class Player implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    private String id;

    @NotNull
    @Field("username")
    private String username;

    @Field("password")
    private String password;

    @Field("money")
    private Integer money;

    @Field("is_complete_move")
    private Boolean isCompleteMove;

    @DBRef
    @Field("region")
    private Region region;

    @DBRef
    @Field("game")
    @JsonIgnoreProperties(value = { "players" }, allowSetters = true)
    @ToString.Exclude
    private Game game;

    @DBRef
    @Field("for_sales")
    @EqualsAndHashCode.Exclude
    private Set<ForSale> forSales = new HashSet<>();

    public void addForSale(ForSale forSale) {
        this.forSales.add(forSale);
        forSale.setPlayer(this);
    }

    public void increaseMoney(Integer number) {
        this.money += number;
    }
}
