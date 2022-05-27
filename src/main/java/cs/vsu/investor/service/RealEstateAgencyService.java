package cs.vsu.investor.service;

import cs.vsu.investor.entity.ForSale;
import cs.vsu.investor.entity.Game;
import cs.vsu.investor.entity.House;
import cs.vsu.investor.entity.Player;
import cs.vsu.investor.entity.enumeration.DealStatus;
import cs.vsu.investor.repository.ForSaleRepository;
import cs.vsu.investor.repository.GameRepository;
import cs.vsu.investor.repository.HouseRepository;
import cs.vsu.investor.repository.PlayerRepository;
import cs.vsu.investor.service.exception.GameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class RealEstateAgencyService {
    final PlayerRepository playerRepository;
    final HouseRepository houseRepository;
    final GameRepository gameRepository;
    final ForSaleRepository forSaleRepository;

    public RealEstateAgencyService(HouseRepository houseRepository,
                                   PlayerRepository playerRepository,
                                   GameRepository gameRepository,
                                   ForSaleRepository forSaleRepository) {
        this.playerRepository = playerRepository;
        this.houseRepository = houseRepository;
        this.gameRepository = gameRepository;
        this.forSaleRepository = forSaleRepository;
    }

    void sellHouses(String gameId) throws GameNotFoundException {
        if (gameRepository.findById(gameId).isEmpty()) {
            throw new GameNotFoundException("Game not found.");
        }
        Game game = gameRepository.findById(gameId).get();
        List<ForSale> forSaleList = new ArrayList<>();
        for (Player player : game.getPlayers()) {
            forSaleList.addAll(player.getForSales());
        }
        forSaleList.sort((o1, o2) -> (int) (o1.getCost() * (1 -
                o1.getAdvertisingCount() * 0.05 - o1.getShopCount() * 0.05) -
                o2.getCost() * (1 - o2.getAdvertisingCount() * 0.05 - o2.getShopCount() * 0.05)));
        int panelDemand = Constants.HOUSING_DEMAND[game.getMonth() - 1] + (int) (Math.random() * 20 - 10) - 20;
        int monolithicDemand = Constants.HOUSING_DEMAND[game.getMonth() - 1] + (int) (Math.random() * 20 - 10) - 40;
        int brickDemand = Constants.HOUSING_DEMAND[game.getMonth() - 1] + (int) (Math.random() * 20 - 10);
        for (ForSale forSale : forSaleList) {
            switch (forSale.getHouseType()) {
                case BRICK:
                    if (forSale.getCount() <= brickDemand) {
                        brickDemand -= forSale.getCount();
                        completeDeal(forSale);
                    } else {
                        participateCompleteDeal(forSale, forSale.getCount() - brickDemand);
                        brickDemand = 0;
                    }
                    break;
                case MONOLITHIC:
                    if (forSale.getCount() <= monolithicDemand) {
                        monolithicDemand -= forSale.getCount();
                        completeDeal(forSale);
                    } else {
                        participateCompleteDeal(forSale, forSale.getCount() - monolithicDemand);
                        panelDemand = 0;
                    }
                    break;
                case PANEL:
                    if (forSale.getCount() <= panelDemand) {
                        panelDemand -= forSale.getCount();
                        completeDeal(forSale);
                    } else {
                        participateCompleteDeal(forSale, forSale.getCount() - panelDemand);
                        panelDemand = 0;
                    }
                    break;
            }
        }
    }

    void completeDeal(ForSale forSale) {
        forSale.setDealStatus(DealStatus.CLOSE);
        Player player = forSale.getPlayer();
        player.increaseMoney(forSale.getCount() * forSale.getCost());
        House house = forSale.getHouse();
        house.setSoldFlatNumber(house.getSoldFlatNumber() + forSale.getCount());
        houseRepository.save(house);
        playerRepository.save(player);
        forSaleRepository.save(forSale);
    }

    void participateCompleteDeal(ForSale forSale, int countSoled) {
        Player player = forSale.getPlayer();
        player.increaseMoney(countSoled * forSale.getCost());
        forSale.setCount(forSale.getCount() - countSoled);
        House house = forSale.getHouse();
        house.setSoldFlatNumber(house.getSoldFlatNumber() + countSoled);
        houseRepository.save(house);
        playerRepository.save(player);
        forSaleRepository.save(forSale);
    }
}
