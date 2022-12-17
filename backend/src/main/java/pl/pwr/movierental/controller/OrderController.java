package pl.pwr.movierental.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.pwr.movierental.model.Order;
import pl.pwr.movierental.service.OrderService;

import java.util.List;

@RestController
@RequestMapping("/api/v1/")
public class OrderController {
    @Autowired
    private OrderService orderService;

    @GetMapping("/orders")
    public ResponseEntity<List<Order>> getAllOrders() {
        return orderService.getAll();
    }

    @PostMapping("/orders")
    public ResponseEntity<?> createOrder(@RequestBody Order order) {
        return orderService.add(order);
    }

    @GetMapping("/order/{id}")
    public ResponseEntity<?> getOrderById(@PathVariable Integer id) {
        return orderService.getById(id);
    }

    @PutMapping("/order/{id}")
    public ResponseEntity<?> updateOrder(@PathVariable Integer id, @RequestBody Order newOrder) {
        return orderService.change(id, newOrder);
    }

    @DeleteMapping("/order/{id}")
    public ResponseEntity<?> deleteOrder(@PathVariable Integer id) {
        return orderService.delete(id);
    }
}
