package pl.pwr.movierental.controler;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.pwr.movierental.model.Order;
import pl.pwr.movierental.repository.OrderRepository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1/")
public class OrderController {
    @Autowired
    private OrderRepository orderRepository;

    @GetMapping("/orders")
    public List<Order> getAllOrders() {
        return orderRepository.findAll();
    }

    @PostMapping("/orders")
    public Order createOrder(@RequestBody Order order) {
        return orderRepository.save(order);
    }

    @GetMapping("/order/{id}")
    public ResponseEntity<Order> getOrderById(@PathVariable Integer id) {
        Optional<Order> optionalOrder = orderRepository.findById(id);
        if (optionalOrder.isEmpty()) return ResponseEntity.notFound().build();
        return ResponseEntity.ok(optionalOrder.get());
    }


    @PutMapping("/order/{id}")
    public ResponseEntity<Order> updateOrder(@PathVariable Integer id, @RequestBody Order newOrder) {
        Optional<Order> optionalOrder = orderRepository.findById(id);
        if (optionalOrder.isEmpty()) return ResponseEntity.notFound().build();

        Order order = optionalOrder.get();
        order.setEmployee(newOrder.getEmployee());
        order.setClientData(newOrder.getClientData());
        order.setFilm(newOrder.getFilm());
        order.setRentalDate(newOrder.getRentalDate());
        order.setReturnDate(newOrder.getReturnDate());
        order.setStatus(newOrder.getStatus());

        Order updatedOrder = orderRepository.save(order);
        return ResponseEntity.ok(updatedOrder);
    }

    @DeleteMapping("/order/{id}")
    public ResponseEntity<Map<String, Boolean>> deleteOrder(@PathVariable Integer id) {
        Optional<Order> optionalOrder = orderRepository.findById(id);
        if (optionalOrder.isEmpty()) return ResponseEntity.notFound().build();

        orderRepository.delete(optionalOrder.get());
        Map<String, Boolean> response = new HashMap<>();
        response.put("deleted", Boolean.TRUE);
        return ResponseEntity.ok(response);
    }
}
