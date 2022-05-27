package cs.vsu.investor.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import cs.vsu.investor.entity.enumeration.HouseType;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

/**
 * A House.
 */
@Document(collection = "house")
@Data
public class House implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    private String id;

    @Field("build_time")
    private Integer buildTime;

    @Field("flat_number")
    private Integer flatNumber;

    @Field("construction_month")
    private Integer constructionMonth;

    @Field("is_build")
    private Boolean isBuild;

    @Field("sold_flat_number")
    private Integer soldFlatNumber;

    @Field("house_type")
    private HouseType houseType;

    @DBRef
    @Field("region")
    @JsonIgnoreProperties(value = { "houses", "shops" }, allowSetters = true)
    private Region region;

    @DBRef
    @Field("for_sales")
    private Set<ForSale> forSales = new HashSet<>();
}
