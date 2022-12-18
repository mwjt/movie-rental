package pl.pwr.movierental.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.pwr.movierental.model.dto.OrderDTO;
import pl.pwr.movierental.service.OrderService;

import java.util.List;

@RestController
@RequestMapping("/api/v1/")
public class OrderController {
    @Autowired
    private OrderService orderService;

    @Operation(summary = "Get all orders")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "List of orders", content = {
                    @Content(mediaType = "application/json", schema = @Schema(implementation = List.class))
            })
    })
    @GetMapping("/orders")
    public ResponseEntity<List<OrderDTO>> getAllOrders() {
        return orderService.getAll();
    }

    @Operation(summary = "Get order by id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Order", content = {
                    @Content(mediaType = "application/json", schema = @Schema(implementation = OrderDTO.class))
            }),
            @ApiResponse(responseCode = "204", description = "ID not found", content = @Content)
    })
    @GetMapping("/order/{id}")
    public ResponseEntity<?> getOrderById(@PathVariable Integer id) {
        return orderService.getById(id);
    }

    @Operation(summary = "Add a new order")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Order", content = {
                    @Content(mediaType = "application/json", schema = @Schema(implementation = OrderDTO.class))
            }),
            @ApiResponse(responseCode = "400", description = "Order not given", content = @Content)
    })
    @PostMapping("/orders")
    public ResponseEntity<?> createOrder(@RequestBody OrderDTO order) {
        return orderService.add(order);
    }

    @Operation(summary = "Update order")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Order", content = {
                    @Content(mediaType = "application/json", schema = @Schema(implementation = OrderDTO.class))
            }),
            @ApiResponse(responseCode = "204", description = "ID not found", content = @Content),
            @ApiResponse(responseCode = "400", description = "Order not given", content = @Content)
    })
    @PutMapping("/order/{id}")
    public ResponseEntity<?> updateOrder(@PathVariable Integer id, @RequestBody OrderDTO newOrder) {
        return orderService.change(id, newOrder);
    }

    @Operation(summary = "Delete order")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "OK", content = @Content),
            @ApiResponse(responseCode = "204", description = "ID not found", content = @Content)
    })
    @DeleteMapping("/order/{id}")
    public ResponseEntity<String> deleteOrder(@PathVariable Integer id) {
        return orderService.delete(id);
    }
}
