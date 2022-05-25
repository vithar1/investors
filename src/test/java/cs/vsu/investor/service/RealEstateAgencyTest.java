package cs.vsu.investor.service;

import cs.vsu.investor.entity.Game;
import cs.vsu.investor.entity.House;
import cs.vsu.investor.entity.Player;
import cs.vsu.investor.entity.Region;
import cs.vsu.investor.repository.GameRepository;
import cs.vsu.investor.repository.HouseRepository;
import cs.vsu.investor.repository.PlayerRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import static org.junit.jupiter.api.Assertions.*;

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

    @Autowired
    GameRepository gameRepository;
    @Autowired
    PlayerRepository playerRepository;
    @Autowired
    HouseRepository houseRepository;


    public Game createGame() {
        Game game = new Game();
        game.setTitle(GAME_TITLE);
        return game;
    }

    public Player createPlayer() {
        Player player = new Player();
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

    @BeforeEach
    public void initTest() {
        game = createGame();
        player1 = createPlayer();
        player2 = createPlayer();
        region1 = createRegion();
        region2 = createRegion();
    }


    @Test
    void sellHouses() {
    }
}