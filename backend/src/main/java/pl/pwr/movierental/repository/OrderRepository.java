package pl.pwr.movierental.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.pwr.movierental.model.Order;

@Repository
public interface OrderRepository extends JpaRepository<Order, Integer> {
}
