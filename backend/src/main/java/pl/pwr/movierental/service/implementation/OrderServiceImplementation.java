package pl.pwr.movierental.service.implementation;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import pl.pwr.movierental.mapper.OrderMapper;
import pl.pwr.movierental.model.dto.OrderDTO;
import pl.pwr.movierental.model.entity.Order;
import pl.pwr.movierental.repository.OrderRepository;
import pl.pwr.movierental.service.OrderService;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Slf4j
@Service
@RequiredArgsConstructor
public class OrderServiceImplementation implements OrderService {

    private final OrderRepository orderRepository;
    private final OrderMapper orderMapper = OrderMapper.INSTANCE;

    public ResponseEntity<List<OrderDTO>> getAll() {
        List<Order> orderList = orderRepository.findAll();
        return ResponseEntity.ok(orderMapper.orderListToOrderDTOList(orderList));
    }

    public ResponseEntity<?> getById(Integer id) {
        Optional<Order> order = orderRepository.findById(id);
        if (order.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NO_CONTENT).body("ID not found");
        }
        return ResponseEntity.ok(orderMapper.orderToOrderDTO(order.get()));
    }

    @Transactional
    public ResponseEntity<?> add(OrderDTO order) {
        if (order == null) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Data not provided");
        }
        Order newOrder = orderRepository.saveAndFlush(orderMapper.orderDTOToOrder(order));
        return ResponseEntity.status(HttpStatus.CREATED).body(orderMapper.orderToOrderDTO(newOrder));
    }

    @Transactional
    public ResponseEntity<?> change(Integer id, OrderDTO newOrder) {
        Optional<Order> order = orderRepository.findById(id);
        if (order.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NO_CONTENT).body("ID not found");
        }
        newOrder.setId(id);
        orderRepository.saveAndFlush(orderMapper.orderDTOToOrder(newOrder));
        return ResponseEntity.status(HttpStatus.CREATED).body(newOrder);
    }

    @Transactional
    public ResponseEntity<String> delete(Integer id) {
        Optional<Order> order = orderRepository.findById(id);
        order.ifPresent(orderRepository::delete);
        return ResponseEntity.ok("");
    }
}
