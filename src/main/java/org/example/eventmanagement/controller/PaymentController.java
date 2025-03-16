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

@RestController
@RequestMapping("/payment")
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
     * @param amount        The amount to be paid.
     * @param customerEmail The customer's email address.
     * @return ResponseEntity containing the response from Cashfree.
     */
    @PostMapping("/createOrder")
    public ResponseEntity<String> createOrder(@RequestParam double amount, @RequestParam String customerEmail) {
        try {
            // Step 1: Create request body
            ObjectNode requestBody = objectMapper.createObjectNode();
            requestBody.put("orderId", "ORDER_" + System.currentTimeMillis()); // Generate a unique order ID
            requestBody.put("orderAmount", amount);
            requestBody.put("orderCurrency", "INR");
            requestBody.put("customerEmail", customerEmail);

            // Step 2: Create HTTP request
            HttpPost httpPost = new HttpPost(cashfreeApiUrl);
            httpPost.setHeader("x-client-id", cashfreeAppId);
            httpPost.setHeader("x-client-secret", cashfreeSecretKey);
            httpPost.setHeader("Content-Type", "application/json");
            httpPost.setEntity(new StringEntity(requestBody.toString()));

            // Step 3: Send request and handle response
            try (CloseableHttpClient httpClient = HttpClients.createDefault();
                 CloseableHttpResponse response = httpClient.execute(httpPost)) {

                // Step 4: Parse response
                String responseBody = EntityUtils.toString(response.getEntity());

                // Step 5: Return the response to the client
                return new ResponseEntity<>(responseBody, HttpStatus.OK);
            }
        } catch (Exception e) {
            // Log the error for debugging
            e.printStackTrace();

            // Return a generic error message
            return new ResponseEntity<>("An error occurred while processing your request.", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}