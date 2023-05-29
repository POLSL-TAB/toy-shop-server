package com.example.toyshopserver.service;

import com.example.toyshopserver.data.ComplaintStatus;
import com.example.toyshopserver.dto.ComplaintDTO;
import com.example.toyshopserver.dto.OrderDto;
import com.example.toyshopserver.dto.OrderItemDto;
import com.example.toyshopserver.model.CartItem;
import com.example.toyshopserver.model.Complaint;
import com.example.toyshopserver.model.Order;
import com.example.toyshopserver.model.OrderItem;
import com.example.toyshopserver.model.User;
import com.example.toyshopserver.repository.CartRepository;
import com.example.toyshopserver.repository.ComplaintRepository;
import com.example.toyshopserver.repository.OrderRepository;
import jakarta.transaction.Transactional;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@Transactional
@RequiredArgsConstructor
public class OrderService {

  private final CartRepository cartRepository;
  private final OrderRepository orderRepository;
  private final ComplaintRepository complaintRepository;
  private final UserService userService;
  private final ProductService productService;

  public void createOrder(String userEmail) {
    User user = userService.getByEmail(userEmail)
        .orElseThrow(() -> new UsernameNotFoundException(userEmail + " user not found"));

    List<CartItem> cartItems = cartRepository.findAllByUser(user);

    if (cartItems.isEmpty()) {
      throw new IllegalArgumentException("no products in cart");
    }

    cartRepository.deleteAllByUser(user);

    cartItems.forEach(item -> productService.decreaseStock(item.getId(), item.getQuantity()));

    Order order = new Order();
    order.setCreated(LocalDateTime.now());
    order.setUser(user);

    List<OrderItem> orderItems = cartItems.stream()
        .map((CartItem cartItem) -> createOrderItem(cartItem, order))
        .toList();
    order.setOrderItems(orderItems);

    orderRepository.save(order);
  }

  public List<OrderDto> getAllOrders(String userEmail) {
    User user = userService.getByEmail(userEmail)
        .orElseThrow(() -> new UsernameNotFoundException(userEmail + " user not found"));
    return orderRepository.findAllByUser(user).stream()
        .map(this::mapOrderToDto)
        .toList();
  }

  public List<ComplaintDTO> getAllComplaints() {
    return complaintRepository.findAll().stream()
        .map(this::mapComplaintToDto)
        .toList();
  }

  public Optional<ComplaintDTO> getComplaintForOrder(Long orderId) {
    return orderRepository.findById(orderId)
        .map(complaintRepository::findByOrder)
        .filter(Optional::isPresent)
        .map(Optional::get)
        .map(this::mapComplaintToDto);
  }

  public void createComplaint(ComplaintDTO complaintDto) {
    Order order = orderRepository.findById(complaintDto.orderId())
        .orElseThrow();
    Complaint complaint = new Complaint();
    complaint.setOrder(order);
    LocalDateTime now = LocalDateTime.now();
    complaint.setCreated(now);
    complaint.setUpdated(now);
    complaint.setStatus(ComplaintStatus.CREATED);
    complaint.setReason(complaintDto.reason());
    complaintRepository.save(complaint);
  }

  public void updateComplaint(ComplaintDTO complaintDto) {
    Complaint complaint = complaintRepository.findById(complaintDto.id())
        .orElseThrow(() -> new IllegalArgumentException("No complaint with id " + complaintDto.id() + " in database"));
    complaint.setStatus(ComplaintStatus.fromName(complaintDto.status()));
    complaint.setUpdated(LocalDateTime.now());
    complaintRepository.save(complaint);
  }

  private OrderItem createOrderItem(CartItem cartItem, Order order) {
    OrderItem orderItem = new OrderItem();
    orderItem.setOrder(order);
    orderItem.setProduct(cartItem.getProduct());
    orderItem.setQuantity(cartItem.getQuantity());
    return orderItem;
  }

  private OrderDto mapOrderToDto(Order order) {
    return new OrderDto(
        order.getId(),
        order.getUser().getEmail(),
        order.isPaid(),
        toStringSafe(order.getCreated()),
        toStringSafe(order.getCompleted()),
        toStringSafe(order.getPaymentType()),
        order.getOrderItems().stream()
            .map(this::mapOrderItemToDto)
            .toList()
    );
  }

  private OrderItemDto mapOrderItemToDto(OrderItem orderItem) {
    return new OrderItemDto(orderItem.getProduct().getId(), orderItem.getQuantity());
  }

  private ComplaintDTO mapComplaintToDto(Complaint complaint) {
    return new ComplaintDTO(
        complaint.getId(),
        complaint.getOrder().getId(),
        complaint.getStatus().name(),
        complaint.getReason(),
        Optional.ofNullable(complaint.getCreated()).map(LocalDateTime::toString).orElse(null),
        Optional.ofNullable(complaint.getUpdated()).map(LocalDateTime::toString).orElse(null)
    );
  }

  private String toStringSafe(Object object) {
    return Optional.ofNullable(object)
        .map(Object::toString)
        .orElse(null);
  }
}
