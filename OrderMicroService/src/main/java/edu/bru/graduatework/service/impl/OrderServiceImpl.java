package edu.bru.graduatework.service.impl;

import edu.bru.graduatework.model.Basket;
import edu.bru.graduatework.model.Order;
import edu.bru.graduatework.model.User;
import edu.bru.graduatework.repository.BasketRepository;
import edu.bru.graduatework.repository.OrderRepository;
import edu.bru.graduatework.repository.UserRepository;
import edu.bru.graduatework.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class OrderServiceImpl implements OrderService {

    private UserRepository userRepository;

    private OrderRepository orderRepository;

    private BasketRepository basketRepository;


    @Override
    @Transactional
    public void save(String username) {
        User user = userRepository.findByEmail(username);

        Basket basket = basketRepository.findByUserId(user.getId()).get();

        Order order = Order.builder()
                .numberOfProducts(basket.getNumberOfProducts())
                .products(basket.getProducts())
                .user(basket.getUser())
                .price(basket.getPrice())
                .build();

        orderRepository.saveAndFlush(order);
    }
}
