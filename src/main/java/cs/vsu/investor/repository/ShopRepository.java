package cs.vsu.investor.repository;

import cs.vsu.investor.entity.Shop;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

/**
 * Spring Data MongoDB repository for the Shop entity.
 */
@SuppressWarnings("unused")
@Repository
public interface ShopRepository extends MongoRepository<Shop, String> {}
