package cs.vsu.investor.service;

import cs.vsu.investor.entity.*;
import cs.vsu.investor.entity.enumeration.DealStatus;
import cs.vsu.investor.entity.enumeration.HouseType;
import cs.vsu.investor.repository.ForSaleRepository;
import cs.vsu.investor.repository.GameRepository;
import cs.vsu.investor.repository.HouseRepository;
import cs.vsu.investor.repository.PlayerRepository;
import cs.vsu.investor.service.exception.GameNotFoundException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class RealEstateAgencyTest {

    private final String GAME_TITLE = "A1";
    private final String PLAYER_UNAME_1 = "A2";
    private final String PLAYER_UNAME_2 = "A3";
    private final String REGION_TITLE_1 = "A4";
    private final String REGION_TITLE_2 = "A5";
    private Game game;
    private Player player1;
    private Player player2;
    private Region region1;
    private Region region2;
    private House house1;
    private House house2;
    private ForSale forSale1;
    private ForSale forSale2;

    @Autowired
    GameRepository gameRepository;
    @Autowired
    PlayerRepository playerRepository;
    @Autowired
    HouseRepository houseRepository;
    @Autowired
    RealEstateAgencyService realEstateAgency;

    @Autowired
    ForSaleRepository forSaleRepository;


    public Game createGame() {
        Game game = new Game();
        game.setTitle(GAME_TITLE);
        game.setMonth(4);
        return game;
    }

    public Player createPlayer() {
        Player player = new Player();
        player.setMoney(0);
        if(player1 == null) {
            player.setUsername(PLAYER_UNAME_1);
        }else {
            player.setUsername(PLAYER_UNAME_2);
        }
        return player;
    }

    public Region createRegion() {
        Region region = new Region();
        if(region1 == null) {
            region.setTitle(REGION_TITLE_1);
        } else{
            region.setTitle(REGION_TITLE_2);
        }
        return region;
    }

    public House createHouse() {
        House house = new House();
        house.setFlatNumber(10);
        house.setSoldFlatNumber(5);
        return house;
    }

    public ForSale createForSale() {
        ForSale forSale = new ForSale();
        forSale.setCount(10);
        forSale.setCost(324);
        forSale.setHouseType(HouseType.BRICK);
        forSale.setDealStatus(DealStatus.CLOSE);
        return forSale;
    }

    @BeforeEach
    public void initTest() {
        game = createGame();
        player1 = createPlayer();
        player2 = createPlayer();
        region1 = createRegion();
        region2 = createRegion();
        forSale1 = createForSale();
        forSale2 = createForSale();
        house1 = createHouse();
        house2 = createHouse();
    }

    @Test
    void sellHouses() {
        house1 = houseRepository.save(house1);
        house2 = houseRepository.save(house2);
        forSale1.setHouse(house1);
        forSale1 = forSaleRepository.save(forSale1);
        forSale2 = forSaleRepository.save(forSale2);
        player1.addForSale(forSale1);
        player1 = playerRepository.save(player1);
        forSaleRepository.save(forSale1);
        player2 = playerRepository.save(player2);
        game.addPlayer(player1);
        game.addPlayer(player2);
        game = gameRepository.save(game);
        try {
            realEstateAgency.sellHouses(game.getId());
        } catch (GameNotFoundException e) {
            System.out.println(e.getMessage());
        }
    }

    @Test
    void test() {
        Game game = gameRepository.findById("628f6ace3bd30820fe58e51a").get();
        game.getPlayers().forEach(System.out::println);
    }
}