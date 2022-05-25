package cs.vsu.investor.entity;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import java.io.Serializable;

/**
 * A ForSale
 */
@Document(collection = "game")
@Data
public class ForSale implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    private String id;

    @DBRef
    @Field("player")
    @JsonIgnoreProperties(value = { "region", "game" }, allowSetters = true)
    private Player player;

    @DBRef
    @Field("house")
    @JsonIgnoreProperties(value = { "region" }, allowSetters = true)
    private House house;

    @Field("count")
    private Integer count;

    @Field("cost")
    private Integer cost;

    @Field("advertising_cost")
    private Integer advertisingCost;
}
