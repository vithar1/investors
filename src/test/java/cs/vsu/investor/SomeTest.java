package cs.vsu.investor;

import cs.vsu.investor.entity.Game;
import cs.vsu.investor.entity.Player;
import cs.vsu.investor.entity.Region;
import cs.vsu.investor.repository.GameRepository;
import cs.vsu.investor.repository.PlayerRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;


@SpringBootTest
public class SomeTest {
    @Autowired
    GameRepository gameRepository;

    @Autowired
    PlayerRepository playerRepository;

    @Test
    void test() {
      Game game = new Game();
      Player player = new Player();
      player.setUsername("username");
      game.setTitle("Test title");
      player = playerRepository.save(player);
      game.addPlayer(player);
      gameRepository.save(game);
      System.out.println(gameRepository.findAll());
    }
}
