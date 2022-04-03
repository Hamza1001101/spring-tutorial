package org.example.context;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.example.service.InvoiceService;
import org.example.service.UserService;

public class Application {
    public static final ObjectMapper objectMapper = new ObjectMapper();
    public static final UserService userService = new UserService();
    public static final InvoiceService invoiceService = new InvoiceService(userService);
}
