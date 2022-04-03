package org.example.service;

import org.example.model.Invoice;
import org.example.model.User;

import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

public class InvoiceService {
    
    private final UserService userService; 
    
    public InvoiceService(UserService userService){
        this.userService=userService; 
    }
    List<Invoice> invoices = new CopyOnWriteArrayList<>(); //(1) // threadsafe 

    public List<Invoice> findAll(){
        return invoices;
    }

    public Invoice create(String userId, Integer amount){
        
        User user = userService.findById(userId); 
        
        if (user==null){
            throw new IllegalArgumentException(); 
        }
        
        //TODO real PDF creation and storing it on network server
        Invoice invoice =  new Invoice(userId, amount, "http://www.africau.edu/images/default/sample.pdf");
        invoices.add(invoice);
        return invoice; 
    }
}
