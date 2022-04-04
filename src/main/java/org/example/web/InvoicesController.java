package org.example.web;

import org.example.Dto.InvoiceDto;
import org.example.model.Invoice;
import org.example.service.InvoiceService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.List;

@RestController // this annotation is equivalent @Controller + @ResponseBody
public class InvoicesController {
    
    private final InvoiceService invoiceService; 
    
    public InvoicesController(InvoiceService invoiceService){
        this.invoiceService=invoiceService;
    }
    
    @GetMapping("/invoices")
    public List<Invoice> index(){
        return invoiceService.findAll();  
    }
    
    @PostMapping("/invoices/{userId}/{amount}")
    public Invoice addInvoice(@PathVariable String userId, @PathVariable Integer amount){
        return invoiceService.create(userId, amount); 
    }

    @PostMapping("/invoices")
    public Invoice createInvoice(@RequestBody @Valid InvoiceDto invoiceDto){
        return invoiceService.create(invoiceDto.getUserId(), invoiceDto.getAmount());
    }
}
