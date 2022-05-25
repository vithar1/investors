package cs.vsu.investor.repository;

import cs.vsu.investor.entity.Player;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

/**
 * Spring Data MongoDB repository for the Player entity.
 */
@SuppressWarnings("unused")
@Repository
public interface PlayerRepository extends MongoRepository<Player, String> {}
