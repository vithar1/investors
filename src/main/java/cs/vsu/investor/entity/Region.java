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
 * A Region.
 */
@Document(collection = "region")
@Data
public class Region implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    private String id;

    @Field("title")
    private String title;

    @Field("house_advertising_cost_this_month")
    private String houseAdvertisingCostThisMonth;

    @Field("house_advertising_cost_next_month")
    private String houseAdvertisingCostNextMonth;

    @Field("shop_advertising_cost_this_month")
    private String shopAdvertisingCostThisMonth;

    @Field("shop_advertising_cost_next_month")
    private String shopAdvertisingCostNextMonth;

    @DBRef
    @Field("house")
    @JsonIgnoreProperties(value = { "houses" }, allowSetters = true)
    private Set<House> houses = new HashSet<>();

    @DBRef
    @Field("shop")
    @JsonIgnoreProperties(value = { "shops" }, allowSetters = true)
    private Set<Shop> shops = new HashSet<>();

    public void setHouses(Set<House> houses) {
        if (this.houses != null) {
            this.houses.forEach(i -> i.setRegion(null));
        }
        if (houses != null) {
            houses.forEach(i -> i.setRegion(this));
        }
        this.houses = houses;
    }

    public void addShop(House house) {
        this.houses.add(house);
        house.setRegion(this);
    }

    public void removeHouse(House house) {
        this.houses.remove(house);
        house.setRegion(null);
    }

    public void setShops(Set<Shop> shops) {
        if (this.shops != null) {
            this.shops.forEach(i -> i.setRegion(null));
        }
        if (shops != null) {
            shops.forEach(i -> i.setRegion(this));
        }
        this.shops = shops;
    }

    public void addShop(Shop shop) {
        this.shops.add(shop);
        shop.setRegion(this);
    }

    public void removeShop(Shop shop) {
        this.shops.remove(shop);
        shop.setRegion(null);
    }
}
