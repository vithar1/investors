package cs.vsu.investor.service;

import cs.vsu.investor.entity.Game;
import cs.vsu.investor.entity.Player;
import cs.vsu.investor.repository.GameRepository;
import cs.vsu.investor.repository.HouseRepository;
import cs.vsu.investor.service.exception.GameNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RealEstateAgency {
    final HouseRepository houseRepository;
    final GameRepository gameRepository;

    public RealEstateAgency(HouseRepository houseRepository, GameRepository gameRepository) {
        this.houseRepository = houseRepository;
        this.gameRepository = gameRepository;
    }

    void sellHouses(String gameId) throws GameNotFoundException {
        if(gameRepository.findById(gameId).isEmpty()) {
            throw new GameNotFoundException("Game not found.");
        }
        Game game = gameRepository.findById(gameId).get();
        /*
        Эта штука продаёт выставленные на продажу дома игроков.
        Спрос разный и зависит от сезона. Могут не купить все дома.
        Продаёт сначала самые выгодные предложения.
        - Получаем игру
        - Из игры получить игроков
        - Из игроков получить их сделки
        - Из сделок получить цены на продажу домов, дома которые выставленны на продажу
          и также получить рекламу на дома
        - составить рейтинговую таблицу продаж и попытаться удовлетворить спрос
        - зачислить игрокам на баланс средства от покупки квартир
         */
        for(Player player:game.getPlayers()) {
            System.out.println(player);
        }
    }
}
