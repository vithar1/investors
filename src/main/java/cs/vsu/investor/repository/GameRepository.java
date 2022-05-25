package cs.vsu.investor.repository;

import cs.vsu.investor.entity.Game;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

/**
 * Spring Data MongoDB repository for the Game entity.
 */
@SuppressWarnings("unused")
@Repository
public interface GameRepository extends MongoRepository<Game, String> {}
