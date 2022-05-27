package cs.vsu.investor.service;


import cs.vsu.investor.repository.ForSaleRepository;
import org.springframework.stereotype.Service;

@Service
public class DealService {
    final
    ForSaleRepository forSaleRepository;

    public DealService(ForSaleRepository forSaleRepository) {
        this.forSaleRepository = forSaleRepository;
    }
}
