package org.example.service;

import org.example.model.Invoice;
import org.example.model.User;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

@Component  //this tells spring to turn this class into a bean. 
public class InvoiceService {
    
    private final UserService userService;
    private final List<Invoice> invoices = new CopyOnWriteArrayList<>(); //(1) // threadsafe 
    private final String cdnUrl;
   
    
    public InvoiceService(UserService userService, @Value("${cdn.url}") String cdnUrl){
        this.userService=userService;
        this.cdnUrl=cdnUrl; 
    }
    
    @PostConstruct
    public void init(){
        System.out.println("Fetching PDF Template from S3....");
    }
    
    @PreDestroy
    public void shutdown(){
        System.out.println("Deleting download tempalates....");
        //TODO actual deletion of PDFs
    }
    
    public List<Invoice> findAll(){
        return invoices;
    }

    public Invoice create(String userId, Integer amount){
        
        User user = userService.findById(userId); 
        
        if (user==null){
            throw new IllegalArgumentException(); 
        }
        
        //TODO real PDF creation and storing it on network server
        Invoice invoice =  new Invoice(userId, amount, cdnUrl+"/images/default/sample.pdf");
        invoices.add(invoice);
        return invoice; 
    }
}
