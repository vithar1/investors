package cs.vsu.investor.entity;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import cs.vsu.investor.entity.enumeration.DealStatus;
import cs.vsu.investor.entity.enumeration.HouseType;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import java.io.Serializable;

/**
 * A ForSale
 */
@Document(collection = "for_sale")
@Data
public class ForSale implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    private String id;

    @DBRef
    @Field("player")
    @JsonIgnoreProperties(value = { "region", "game" }, allowSetters = true)
    @ToString.Exclude
    private Player player;

    @DBRef
    @Field("house")
    @JsonIgnoreProperties(value = { "region" }, allowSetters = true)
    private House house;

    @Field("count")
    private Integer count;

    @Field("count_accepted")
    private Integer countAccepted;

    @Field("cost")
    private Integer cost;

    @Field("advertising_cost")
    private Integer advertisingCount;

    @Field("shop_count")
    private Integer shopCount;

    @Field("deal_status")
    private DealStatus dealStatus;

    @Field("house_type")
    private HouseType houseType;
}
