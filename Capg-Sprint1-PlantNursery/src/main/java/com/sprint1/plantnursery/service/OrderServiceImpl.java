package com.sprint1.plantnursery.service;

import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sprint1.plantnursery.entity.OrderTable;


import com.sprint1.plantnursery.exceptions.OrderIdNotFoundException;
import com.sprint1.plantnursery.repository.IOrderRepository;

/*Controller Class for Order Controller
Created By: Sakshi Shah
*/

@Service
public class OrderServiceImpl implements IOrderService {

	@Autowired
	IOrderRepository orderRepository;

	@Autowired
	IPlanterService planterService;

	@Override
	public OrderTable addOrder(OrderTable order) {
		orderRepository.save(order);
		return order;
	}

	@Override
	public OrderTable updateOrder(OrderTable order,int bookingId) {
		Optional<OrderTable> orderToBeUpdated = orderRepository.findById(order.getBookingOrderId());
		if (orderToBeUpdated.isPresent()) {
			orderRepository.save(order);
		}
		return orderToBeUpdated.orElseThrow(
				() -> new OrderIdNotFoundException("Order with id: " + order.getBookingOrderId() + " is not found"));
	}

	@Override
	public OrderTable deleteOrder(int bookingId) throws OrderIdNotFoundException{
		Optional<OrderTable> orderToBeRemoved = orderRepository.findById(bookingId);
		if (orderToBeRemoved.isPresent()) {
			orderRepository.deleteById(bookingId);
		}
		return orderToBeRemoved
				.orElseThrow(() -> new OrderIdNotFoundException("Order with id: " + bookingId + " is not found"));
	}

	@Override
	public OrderTable viewOrder(int bookingId) throws OrderIdNotFoundException{
		Optional<OrderTable> orderOptional = orderRepository.findById(bookingId);
		return orderOptional
				.orElseThrow(() -> new OrderIdNotFoundException("Order with id: " + bookingId + " is not found"));
	}

	@Override
	public List<OrderTable> viewAllOrders() {
		return orderRepository.findAll();
	}
}