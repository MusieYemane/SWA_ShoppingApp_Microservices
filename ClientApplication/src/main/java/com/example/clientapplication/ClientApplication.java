package com.example.clientapplication;

import com.example.clientapplication.domain.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;


@SpringBootApplication
public class ClientApplication implements CommandLineRunner {

    @Autowired
    Client client;

    private final String baseUrl = "http://localhost:8080";



    public static void main(String[] args) {
        SpringApplication.run(ClientApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {

        System.out.println("=============Get Products=============");
        System.out.println(client.getProducts());

        System.out.println("=============adding Product: Macbook...=============");
        Product product= new Product();
        product.setProductName("MacBook pro");
        product.setProductPrice(2000.0);
        product.setProductDescription("256 gb storage 16 RAM");
        product.setProductNumInStock(10);
        client.addProduct(product);

        System.out.println("=============adding Product: Samsung 21...=============");
        Product prod2= new Product();
        prod2.setProductName("Samsung 21");
        prod2.setProductPrice(1200.0);
        prod2.setProductDescription("128gb 8 RAM");
        prod2.setProductNumInStock(4);
        client.addProduct(prod2);

        System.out.println("============= Get All Products=============");
        var allProducts= client.getProducts();
        System.out.println(allProducts);

        //edit product
        System.out.println("============= Edit Product from Macbook to Hp=============");
        Product product1 = allProducts.getProducts().get(0);
        Product product2 = allProducts.getProducts().get(1);
        product1.setProductName("Hp");
        product1.setProductPrice(1200.0);
        product1.setProductDescription("125 gb storage 16 RAM");
        product1.setProductNumInStock(5);
        client.modifyProduct(product1,product1.getProductNumber());
        var editedProduct= client.getProducts();
        System.out.println(editedProduct);
//
//
        //create and get cutomers
        System.out.println("============= Create Customer ... =============");
        Customer cust1= new Customer();
        cust1.setFirstName("Michale");
        cust1.setLastName("Rezene");
        cust1.setEmail("mike@gmail.com");
        cust1.setPhone("641256646");
        cust1.setAddress(new Address("1000 North st", "Fairfield", "52557"));
        restTemplate().postForLocation(baseUrl+"/customer/save", cust1, Customer.class);
        System.out.println("============= get Customer number one=============");
        var customers= restTemplate().getForObject(baseUrl+"/customer/findall", Customers.class);
        var customer1= customers.getCustomerList().get(0);
        System.out.println(customer1);

//
        //Create shopping cart to a customer
        System.out.println("============= Create cart to Customer1 ...=============");
        restTemplate().postForLocation(baseUrl+"/cart/addCartForACustomer/"+ customer1.getCustomerId(), null, ShoppingCart.class);

        //add product(Hp) to cart of customer1
        System.out.println("============= Put product1 to cart of customer1 .... =============");
        restTemplate().postForLocation(baseUrl+"/cart/addProductToCartWithQuantity/" +customer1.getCustomerId()+"/quantity/"+ 4, product1, Products.class);
        restTemplate().postForLocation(baseUrl+"/cart/addProductToCartWithQuantity/" +customer1.getCustomerId()+"/quantity/"+ 2, product2, Products.class);

        // Show the shopping cart of customer1
        System.out.println("============= Get shopping cart of customer1=============");
        ShoppingCart cart= restTemplate().getForObject(baseUrl+"/cartQuery/getShoppingCart/"+customer1.getCustomerId(), ShoppingCart.class);
        System.out.println(cart);

        // delete product1(Hp) from cart
        System.out.println("============= Deleting product 1(Hp) from cart of customer1..=============");
        restTemplate().delete(baseUrl+ "/cart/removeProductFromCart/"+customer1.getCustomerId()+"/product/"+product2.getProductNumber(), Void.class);

        //Change the quantity of one of the products
        int quantity2= 1;
        restTemplate().delete(baseUrl+"/cart/removeProductFromCartWithQuantity/"+customer1.getCustomerId()+"/product/"+product1.getProductNumber()+"/quantity/"+quantity2, product1, Products.class);

//        Retrieve and show the shoppingcart
        System.out.println(restTemplate().getForObject(baseUrl+"/cartQuery/getShoppingCart/"+customer1.getCustomerId(), ShoppingCart.class));
//

        //Checkout the shoppingcart
        System.out.println("============= Checkout and place an order =============");

        Order order = restTemplate().postForObject(baseUrl+"/cart/checkout/"+customer1.getCustomerId(),null, Order.class);
//
//        //Order palced
        System.out.println("============= Placing an order .... =============");
        restTemplate().postForObject(baseUrl+"/order/placeOrder/orderNumber/" + order.getOrderNumber(),customer1, Void.class);
//
        //Retrieve and show the shoppingcart
        System.out.println("============= Getting shopping cart (should be empty) .... =============");
        System.out.println(restTemplate().getForObject(baseUrl+"/cartQuery/getShoppingCart/"+ customer1.getCustomerId(), ShoppingCart.class));

        System.out.println("============= Get all order =============");
        System.out.println(restTemplate().getForObject(baseUrl+"/order/getOrders", Orders.class));


//
//



    }


    @Bean
    public RestTemplate restTemplate(){
        return new RestTemplate();
    }



}
//
///////Outputs
//=============Get Products=============
//        Products(products=[Product(productNumber=6233b0fe11350e0670b4662e, productName=Hp, productPrice=1200.0, productDescription=125 gb storage 16 RAM, productNumInStock=2), Product(productNumber=6233b0fe335ae96d42dd4555, productName=Samsung 21, productPrice=1200.0, productDescription=128gb 8 RAM, productNumInStock=4), Product(productNumber=6233bcf5335ae96d42dd4556, productName=MacBook pro, productPrice=2000.0, productDescription=256 gb storage 16 RAM, productNumInStock=10), Product(productNumber=6233bcf5ac9a5e4506f46361, productName=Samsung 21, productPrice=1200.0, productDescription=128gb 8 RAM, productNumInStock=4)])
//        =============adding Product: Macbook...=============
//        =============adding Product: Samsung 21...=============
//        ============= Get All Products=============
//        Products(products=[Product(productNumber=6233b0fe11350e0670b4662e, productName=Hp, productPrice=1200.0, productDescription=125 gb storage 16 RAM, productNumInStock=2), Product(productNumber=6233b0fe335ae96d42dd4555, productName=Samsung 21, productPrice=1200.0, productDescription=128gb 8 RAM, productNumInStock=4), Product(productNumber=6233bcf5335ae96d42dd4556, productName=MacBook pro, productPrice=2000.0, productDescription=256 gb storage 16 RAM, productNumInStock=10), Product(productNumber=6233bcf5ac9a5e4506f46361, productName=Samsung 21, productPrice=1200.0, productDescription=128gb 8 RAM, productNumInStock=4), Product(productNumber=6233bf2b11350e0670b4662f, productName=MacBook pro, productPrice=2000.0, productDescription=256 gb storage 16 RAM, productNumInStock=10), Product(productNumber=6233bf2bac9a5e4506f46362, productName=Samsung 21, productPrice=1200.0, productDescription=128gb 8 RAM, productNumInStock=4)])
//        ============= Edit Product from Macbook to Hp=============
//        Products(products=[Product(productNumber=6233b0fe11350e0670b4662e, productName=Hp, productPrice=1200.0, productDescription=125 gb storage 16 RAM, productNumInStock=5), Product(productNumber=6233b0fe335ae96d42dd4555, productName=Samsung 21, productPrice=1200.0, productDescription=128gb 8 RAM, productNumInStock=4), Product(productNumber=6233bcf5335ae96d42dd4556, productName=MacBook pro, productPrice=2000.0, productDescription=256 gb storage 16 RAM, productNumInStock=10), Product(productNumber=6233bcf5ac9a5e4506f46361, productName=Samsung 21, productPrice=1200.0, productDescription=128gb 8 RAM, productNumInStock=4), Product(productNumber=6233bf2b11350e0670b4662f, productName=MacBook pro, productPrice=2000.0, productDescription=256 gb storage 16 RAM, productNumInStock=10), Product(productNumber=6233bf2bac9a5e4506f46362, productName=Samsung 21, productPrice=1200.0, productDescription=128gb 8 RAM, productNumInStock=4)])
//        ============= Create Customer ... =============
//        ============= get Customer number one=============
//        Customer(customerId=6233b0ff60123167b6d5b93c, firstName=Michale, lastName=Rezene, phone=641256646, email=mike@gmail.com, address=Address(street=1000 North st, city=Fairfield, zip=52557))
//        ============= Create cart to Customer1 ...=============
//        ============= Put product1 to cart of customer1 .... =============
//        ============= Get shopping cart of customer1=============
//        ShoppingCart(customerId=null, cartLineList=[CartLine(product=Product(productNumber=6233b0fe11350e0670b4662e, productName=Hp, productPrice=1200.0, productDescription=125 gb storage 16 RAM, productNumInStock=5), quantity=4), CartLine(product=Product(productNumber=6233b0fe335ae96d42dd4555, productName=Samsung 21, productPrice=1200.0, productDescription=128gb 8 RAM, productNumInStock=4), quantity=2)])
//        ============= Deleting product 1(Hp) from cart of customer1..=============
//        ShoppingCart(customerId=null, cartLineList=[CartLine(product=Product(productNumber=6233b0fe11350e0670b4662e, productName=Hp, productPrice=1200.0, productDescription=125 gb storage 16 RAM, productNumInStock=5), quantity=3)])
//        ============= Checkout and place an order =============
//        ============= Placing an order .... =============
//        ============= Getting shopping cart (should be empty) .... =============
//        ShoppingCart(customerId=null, cartLineList=[])
//        ============= Get all order =============
//        Orders(orders=[Order(orderNumber=6233bf2c982cf977e56f1062, customer=Customer(customerId=6233b0ff60123167b6d5b93c, firstName=Michale, lastName=null, phone=641256646, email=mike@gmail.com, address=Address(street=1000 North st, city=Fairfield, zip=52557)), orderLineList=[OrderLine(product=Product(productNumber=6233b0fe11350e0670b4662e, productName=Hp, productPrice=1200.0, productDescription=125 gb storage 16 RAM, productNumInStock=5), quantity=3)])])
