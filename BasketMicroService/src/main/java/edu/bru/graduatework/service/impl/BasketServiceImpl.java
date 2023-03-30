package edu.bru.graduatework.service.impl;

import edu.bru.graduatework.converter.BasketWithCaloriesDtoConverter;
import edu.bru.graduatework.dto.BasketWithCaloriesDto;
import edu.bru.graduatework.model.Basket;
import edu.bru.graduatework.model.Product;
import edu.bru.graduatework.model.User;
import edu.bru.graduatework.repository.BasketRepository;
import edu.bru.graduatework.repository.ProductRepository;
import edu.bru.graduatework.repository.UserRepository;
import edu.bru.graduatework.service.BasketService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Flux;

import java.math.BigDecimal;
import java.util.List;

@Service
@RequiredArgsConstructor
public class BasketServiceImpl implements BasketService {

    private final BasketRepository basketRepository;

    private final UserRepository userRepository;

    private final ProductRepository productRepository;

    private final WebClient webClient;

    private final BasketWithCaloriesDtoConverter basketWithCaloriesDtoConverter;

    @Override
    @Transactional
    public BasketWithCaloriesDto findBasketByUserId(String username) {
        User user = userRepository.findByEmail(username);

        BasketWithCaloriesDto basketWithCaloriesDto = basketWithCaloriesDtoConverter.
                convertFromDto(basketRepository.findByUserId(user.getId()).orElseThrow(() -> new RuntimeException("Корзина пуста")));

        double numberCalories = basketWithCaloriesDto.getProducts().stream().mapToDouble(Product::getNumberCalories)
                        .sum();
        double numberProteins = basketWithCaloriesDto.getProducts().stream().mapToDouble(Product::getNumberProteins)
                .sum();
        double numberFats = basketWithCaloriesDto.getProducts().stream().mapToDouble(Product::getNumberFats)
                .sum();
        double numberCarbohydrates = basketWithCaloriesDto.getProducts().stream().mapToDouble(Product::getNumberCarbohydrates)
                .sum();
        BigDecimal basketAmount = basketWithCaloriesDto.getProducts().stream()
                .map(Product::getPrice)
                .reduce(BigDecimal.ZERO, BigDecimal::add);

        basketWithCaloriesDto.setNumberCalories(numberCalories);
        basketWithCaloriesDto.setNumberProteins(numberProteins);
        basketWithCaloriesDto.setNumberFats(numberFats);
        basketWithCaloriesDto.setNumberCarbohydrates(numberCarbohydrates);
        basketWithCaloriesDto.setPrice(basketAmount);

        return basketWithCaloriesDto;
    }

    @Override
    @Transactional
    public void add(String username) {
        User user = userRepository.findByEmail(username);

        Basket basket = Basket
                .builder()
                .id(user.getId())
                .price(BigDecimal.ZERO)
                .numberOfProducts(0)
                .user(user)
                .build();

        basketRepository.saveAndFlush(basket);
    }

    @Override
    @Transactional
    public void addProductToBasket(Long productId, String username) {
        User user = userRepository.findByEmail(username);

        Flux<Product> productForSave = webClient.get()
                .uri("http://localhost:8081/product/" + productRepository.findById(productId).get().getName())
                .retrieve()
                .bodyToFlux(Product.class);

        Product product = productForSave.blockFirst();

        Basket basket = basketRepository.findByUserId(user.getId()).get();

        List<Product> oldProducts = basket.getProducts();
        oldProducts.add(product);

        basket.setProducts(oldProducts);
        basket.setPrice(basket.getPrice().add(product.getPrice()));

        basketRepository.saveAndFlush(basket);
    }

    @Override
    @Transactional
    public void delete(String username) {
        User user = userRepository.findByEmail(username);

        Basket basket = basketRepository.findByUserId(user.getId()).get();

        basket.setProducts(List.of());
        basket.setNumberOfProducts(0);
        basket.setPrice(BigDecimal.ZERO);

        basketRepository.delete(basket);

        basketRepository.saveAndFlush(basket);
    }

    @Override
    @Transactional
    public void deleteProductById(Long id, String username) {
        User user = userRepository.findByEmail(username);

        Basket basket = basketRepository.findByUserId(user.getId()).get();

        List<Product> products = basket.getProducts();
        System.out.println(products.size());
        Product product = products.get(id.intValue() - 1);
        products.remove(product);
        basket.setProducts(products);
        basket.setNumberOfProducts(basket.getNumberOfProducts() - 1);
        basket.setPrice(basket.getPrice().subtract(product.getPrice()));

        basketRepository.saveAndFlush(basket);
    }
}
