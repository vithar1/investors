package cs.vsu.investor.repository;

import cs.vsu.investor.entity.ForSale;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

/**
 * Spring Data MongoDB repository for the ForSale entity.
 */
@SuppressWarnings("unused")
@Repository
public interface ForSaleRepository extends MongoRepository<ForSale, String> {}
