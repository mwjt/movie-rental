package pl.pwr.movierental.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.pwr.movierental.model.entity.Order;

@Repository
public interface OrderRepository extends JpaRepository<Order, Integer> {
}
