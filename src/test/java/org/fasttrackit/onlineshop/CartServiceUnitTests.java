package org.fasttrackit.onlineshop;

import org.fasttrackit.onlineshop.domain.Cart;
import org.fasttrackit.onlineshop.domain.Customer;
import org.fasttrackit.onlineshop.domain.Product;
import org.fasttrackit.onlineshop.persistance.CartRepository;
import org.fasttrackit.onlineshop.service.CartService;
import org.fasttrackit.onlineshop.service.CustomerService;
import org.fasttrackit.onlineshop.service.ProductService;
import org.fasttrackit.onlineshop.transfer.AddProductToCartRequest;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class CartServiceUnitTests {

    private CartService cartService;

    @Mock
    private CartRepository cartRepository;

    @Mock
    private CustomerService customerService;

    @Mock
    private ProductService productService;

    @Before
    public void setup(){
        cartService = new CartService(cartRepository, customerService, productService);
    }

    @Test
    public void testAddProductToCart_whenNewCart_thenThrowNoError(){
        when(cartRepository.findById(anyLong())).thenReturn(Optional.empty());

        Customer customer = new Customer();
        customer.setId(1L);
        customer.setFirstName("FirstName");
        customer.setLastName("LastName");

        when(customerService.getCustomer(anyLong())).thenReturn(customer);

        Product product = new Product();
        product.setId(2L);
        product.setName("Product");
        product.setPrice(10.5);
        product.setQuantity(100);

        when(productService.getProduct(anyLong())).thenReturn(product);

        when(cartRepository.save(any(Cart.class))).thenReturn(null);


        AddProductToCartRequest request = new AddProductToCartRequest();
        request.setProductId(2L);
        request.setCustomerId(1L);

        cartService.addProductToCart(request);

        verify(cartRepository).findById(anyLong());
        verify(cartRepository).save(any(Cart.class));
        verify(customerService).getCustomer(anyLong());
        verify(productService).getProduct(anyLong());


    }


}
