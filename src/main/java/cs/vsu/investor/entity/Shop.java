package cs.vsu.investor.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import java.io.Serializable;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

/**
 * A Shop.
 */
@Document(collection = "shop")
@Data
public class Shop implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    private String id;

    @Field("cost")
    private Integer cost;

    @Field("build_time")
    private Integer buildTime;

    @Field("construction_month")
    private Integer constructionMonth;

    @Field("is_build")
    private Boolean isBuild;

    @DBRef
    @Field("region")
    @JsonIgnoreProperties(value = { "houses", "shops" }, allowSetters = true)
    private Region region;
}
