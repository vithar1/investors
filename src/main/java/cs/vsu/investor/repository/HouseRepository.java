package cs.vsu.investor.repository;

import cs.vsu.investor.entity.House;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

/**
 * Spring Data MongoDB repository for the House entity.
 */
@SuppressWarnings("unused")
@Repository
public interface HouseRepository extends MongoRepository<House, String> {}
