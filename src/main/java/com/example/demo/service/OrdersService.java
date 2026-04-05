package com.example.demo.service;

import java.util.ArrayList;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.stereotype.Service;

import com.example.demo.entity.Orders;
import com.example.demo.entity.Product;
import com.example.demo.producer.DocumentEventProducer;
import com.example.demo.repos.OrdersRepo;
import com.example.demo.repos.Orders_productsRepo;
import com.example.demo.repos.ProductRepo;

@Transactional
@Service
public class OrdersService {

    private final OrdersRepo ordersRepo;
    private final Orders_productsRepo orders_productsRepo;
    private final DocumentEventProducer producer;
    private final CartService cartService;
    private final ProductService productService;

    public OrdersService(DocumentEventProducer producer, OrdersRepo ordersRepo, Orders_productsRepo orders_productsRepo, CartService cartService, ProductService productService) {
        this.producer = producer;
        this.ordersRepo = ordersRepo;
        this.orders_productsRepo = orders_productsRepo;
        this.cartService = cartService;
        this.productService = productService;
    }

    public void placeOrder(String delivery_address, String customer_contact, int order_amount, List<Integer> products) {


        Orders orders = new Orders();
        orders.setCustomer_contact(customer_contact);
        orders.setDelivery_address(delivery_address);
        orders.setOrder_amount(order_amount);
        List<Product> items = productService.markAsPlaced(products);


        orders.setProducts(items);

        ordersRepo.save(orders);

        producer.publishOrderEvent(orders);

        products.forEach(product -> cartService.deleteFromCart(product, customer_contact));

    }

    public List<Product> getAllOrders(String mobile_number) {

        List<Orders> orders = ordersRepo.findBycustomer_contact(mobile_number);
        List<Product> products = new ArrayList<>();
        for (int i = 0; i < orders.size(); i++) {
            products.addAll(orders.get(i).getProducts());

        }

        return products;
    }


    public void cancelOrder(int id, String mobile_number) throws Exception {
        //should call kafka to delete the document
        Orders orders = ordersRepo.findByorder_id(orders_productsRepo.getorderid(id));
        Product product = productService.markAsCancelled(id);
        orders_productsRepo.deleteproduct(orders.getOrder_id(), id);

        if (orders.getOrder_amount() - product.getPrice() > 1) {
            orders.setOrder_amount(orders.getOrder_amount() - product.getPrice());
            ordersRepo.save(orders);
        } else {
            ordersRepo.delete(orders);
        }
    }

}
