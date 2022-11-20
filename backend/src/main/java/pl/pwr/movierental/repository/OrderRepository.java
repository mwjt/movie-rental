package pl.pwr.movierental.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.pwr.movierental.model.Order;

public interface OrderRepository extends JpaRepository<Order, Integer> {
}
