package org.example.eventmanagement.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

import java.util.Arrays;
import java.util.Map;

@RestController
@RequestMapping("/api/user/payment")
public class PaymentController {

    // Load sensitive data from configuration (e.g., application.properties)
    @Value("${cashfree.app.id}")
    private String cashfreeAppId;

    @Value("${cashfree.secret.key}")
    private String cashfreeSecretKey;

    @Value("${cashfree.api.url}")
    private String cashfreeApiUrl;

    // Reuse ObjectMapper for better performance
    private static final ObjectMapper objectMapper = new ObjectMapper();

    /**
     * Endpoint to create a payment order.
     *
     * @param requestBody A map containing the payment details (orderAmount and customerEmail).
     * @return ResponseEntity containing the response from Cashfree.
     */
    @PostMapping("/createOrder")
    public ResponseEntity<String> createOrder(@RequestBody Map<String, Object> requestBody) {
        try {
            // Step 1: Extract fields from the request body
            System.out.println("Received Request Body: " + requestBody.toString()); // Log the input request body

            String orderId = "ORDER_" + System.currentTimeMillis(); // Generate a unique order ID
            double orderAmount = Double.parseDouble(requestBody.get("orderAmount").toString());
            String orderCurrency = "INR"; // Hardcoded for simplicity
            String customerEmail = "email@gmail.com";
            String customerPhone = "9999999999"; // Hardcoded for simplicity (replace with actual phone number if available)
            String customerId = requestBody.get("userId").toString(); // Hardcoded for simplicity (replace with actual customer ID if available)

            // Step 2: Create request body for Cashfree
            ObjectNode cashfreeRequestBody = objectMapper.createObjectNode();
            cashfreeRequestBody.put("order_id", orderId); // Use snake_case
            cashfreeRequestBody.put("order_amount", orderAmount); // Use snake_case
            cashfreeRequestBody.put("order_currency", orderCurrency); // Use snake_case

            // Add customer_details object
            ObjectNode customerDetails = objectMapper.createObjectNode();
            customerDetails.put("customer_id", customerId); // Mandatory field
            customerDetails.put("customer_email", customerEmail); // Mandatory field
            customerDetails.put("customer_phone", customerPhone); // Mandatory field
            cashfreeRequestBody.set("customer_details", customerDetails);

            // Log request body
            System.out.println("Cashfree Request Body: " + cashfreeRequestBody.toString());

            // Step 3: Create HTTP request
            HttpPost httpPost = new HttpPost(cashfreeApiUrl);
            httpPost.setHeader("x-client-id", cashfreeAppId);
            httpPost.setHeader("x-client-secret", cashfreeSecretKey);
            httpPost.setHeader("Content-Type", "application/json");
            httpPost.setHeader("x-api-version", "2023-08-01"); // Add version header
            httpPost.setEntity(new StringEntity(cashfreeRequestBody.toString()));

            // Log headers
            System.out.println("Headers: " + Arrays.toString(httpPost.getAllHeaders()));

            // Step 4: Send request
            try (CloseableHttpClient httpClient = HttpClients.createDefault();
                 CloseableHttpResponse response = httpClient.execute(httpPost)) {

                // Step 5: Parse response
                String responseBody = EntityUtils.toString(response.getEntity());

                // Log response
                System.out.println("Cashfree Response: " + responseBody);

                // Step 6: Return the response to the client
                return new ResponseEntity<>(responseBody, HttpStatus.OK);
            }
        } catch (Exception e) {
            // Log the error
            e.printStackTrace();

            // Return a detailed error message
            return new ResponseEntity<>("An error occurred while processing your request: " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}