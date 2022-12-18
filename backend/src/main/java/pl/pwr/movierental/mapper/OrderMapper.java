package pl.pwr.movierental.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import pl.pwr.movierental.model.dto.OrderDTO;
import pl.pwr.movierental.model.entity.Order;

import java.util.List;

@Mapper
public interface OrderMapper {
    OrderMapper INSTANCE = Mappers.getMapper(OrderMapper.class);

    OrderDTO orderToOrderDTO(Order order);
    Order orderDTOToOrder(OrderDTO orderDTO);
    List<OrderDTO> orderListToOrderDTOList(List<Order> orderList);
    List<Order> orderDTOListToOrderList(List<OrderDTO> orderDTOList);
}
