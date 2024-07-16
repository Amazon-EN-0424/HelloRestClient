# ☕ Exercise: Brewing a Coffee Shop System with Microservices

## Objective
Craft two Spring Boot applications that blend together to create a delightful Coffee Shop management system.

## Setup
1. Brew two Spring Boot applications:
    - CoffeeService (percolating on port 8080)
    - OrderService (steaming on port 8081)

## Requirements

### CoffeeService (Port 8080)
1. Roast a `Coffee` model with fields:
    - id (Long)
    - name (String)
    - origin (String)
    - roastLevel (String) // Light, Medium, Dark
    - price (BigDecimal)

2. Grind out the following endpoints:
    - GET `/api/coffees`: List all coffee varieties
    - GET `/api/coffees/{id}`: Get a specific coffee
    - POST `/api/coffees`: Add a new coffee variety
    - GET `/api/coffees/{id}/with-orders`: Get a coffee with its order history (this will call the OrderService)

3. Use an in-memory database (H2) as your coffee bean storage.

### OrderService (Port 8081)
1. Brew an `Order` model with fields:
    - id (Long)
    - coffeeId (Long)
    - quantity (Integer)
    - customerName (String)
    - orderStatus (String) // Pending, Brewing, Ready, Served

2. Pour out the following endpoints:
    - GET `/api/orders`: List all orders
    - GET `/api/orders/{id}`: Get a specific order
    - POST `/api/orders`: Place a new order
    - GET `/api/orders/coffee/{coffeeId}`: Get all orders for a specific coffee
    - PUT `/api/orders/{id}/status`: Update an order's status

3. Use an in-memory database (H2) to store the orders.

## Inter-Service Communication
- In the CoffeeService, use RestClient to call the OrderService when fetching a coffee with its order history.
- Implement proper error handling for cases where the OrderService is unavailable (maybe the espresso machine is down?).

## Bonus Challenges
1. Implement a "Barista Recommendation" feature that suggests coffees based on a customer's order history.
2. Add a caching layer in the CoffeeService for storing recent order information to reduce calls to the OrderService.
3. Create a "Coffee of the Day" feature that automatically rotates and applies a discount.

## Conceptual Implementation Hints

Here's a high-level pseudocode to get your beans grinding:

```
// In CoffeeService

class CoffeeService {
    initialize RestClient for OrderService

    function getCoffeeWithOrders(coffeeId) {
        coffee = fetch coffee from database
        orders = call OrderService to get orders for coffee
        return blend coffee and orders
    }

    function callOrderService(coffeeId) {
        use RestClient to GET "/api/orders/coffee/{coffeeId}"
        handle potential errors (service down, network issues, etc.)
        return order data
    }
}

// In OrderService

class OrderController {
    function getOrdersForCoffee(coffeeId) {
        orders = fetch orders from database where coffeeId matches
        return orders
    }

    function updateOrderStatus(orderId, newStatus) {
        order = fetch order from database
        update order status
        save updated order
        return updated order
    }
}
```

## Taste Testing Your System
1. Start both services
2. Add some coffee varieties to the CoffeeService
3. Place orders using the OrderService
4. Try fetching a coffee with its order history from the CoffeeService

May your microservices brew in perfect harmony! ☕🚀