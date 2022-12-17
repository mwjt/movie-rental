package pl.pwr.movierental.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import pl.pwr.movierental.model.Order;
import pl.pwr.movierental.repository.OrderRepository;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Slf4j
@Service
@RequiredArgsConstructor
public class OrderService {

    @Autowired
    private OrderRepository orderRepository;

    public ResponseEntity<List<Order>> getAll() {
        List<Order> orderList = orderRepository.findAll();
        return ResponseEntity.ok(orderList);
    }

    public ResponseEntity<?> getById(Integer id) {
        Optional<Order> order = orderRepository.findById(id);
        if (order.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NO_CONTENT).body("ID not found");
        }
        return ResponseEntity.ok(order.get());
    }

    @Transactional
    public ResponseEntity<?> add(Order order) {
        if (order == null) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Data not provided");
        }
        Order newOrder = orderRepository.saveAndFlush(order);
        return ResponseEntity.status(HttpStatus.CREATED).body(newOrder);
    }

    @Transactional
    public ResponseEntity<?> change(Integer id, Order newOrder) {
        Optional<Order> order = orderRepository.findById(id);
        if (order.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NO_CONTENT).body("ID not found");
        }
        newOrder.setOrderId(id);
        orderRepository.saveAndFlush(newOrder);
        return ResponseEntity.status(HttpStatus.CREATED).body(newOrder);
    }

    @Transactional
    public ResponseEntity<?> delete(Integer id) {
        Optional<Order> order = orderRepository.findById(id);
        order.ifPresent(orderRepository::delete);
        return ResponseEntity.ok("");
    }
}
