package pl.pwr.movierental.service;

import org.springframework.http.ResponseEntity;
import pl.pwr.movierental.model.dto.OrderDTO;

import java.util.List;

public interface OrderService {
    ResponseEntity<List<OrderDTO>> getAll();
    ResponseEntity<?> getById(Integer id);
    ResponseEntity<?> add(OrderDTO order);
    ResponseEntity<?> change(Integer id, OrderDTO newOrder);
    ResponseEntity<String> delete(Integer id);
}
