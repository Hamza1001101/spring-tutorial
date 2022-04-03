package org.example.web;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.example.context.MyFancyPdfInvoicesApplicationConfiguration;
import org.example.model.Invoice;
import org.example.service.InvoiceService;
import org.example.service.UserService;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

public class MyFancyPdfInvoicesServlet extends HttpServlet{
 
    private UserService userService;
    private ObjectMapper objectMapper;
    private InvoiceService invoiceService;

    /**
     * All way of configuring if you used Spring XML configurations. 
     * A lot of legacy code still use this. 
     */
    //TODO FIX MI!
    /*@Override
    public void init(){
        ApplicationContext ctx = 
                new ClassPathXmlApplicationContext("Application-configuration.xml"); 
        this.userService=ctx.getBean(UserService.class); 
        this.objectMapper=  ctx.getBean(ObjectMapper.class); 
        this.invoiceService= ctx.getBean(InvoiceService.class); 
    }*/
    
    @Override
    public void init(){
        AnnotationConfigApplicationContext ctx = 
                new AnnotationConfigApplicationContext(MyFancyPdfInvoicesApplicationConfiguration.class); 
        
        ctx.registerShutdownHook();
        
        this.userService=ctx.getBean(UserService.class);
        this.objectMapper=ctx.getBean(ObjectMapper.class); 
        this.invoiceService=ctx.getBean(InvoiceService.class);
    }
    
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        

        if (request.getRequestURI().equals("/")) {

            response.setContentType("text/html; charset=UTF-8");
            response.getWriter().print(
                    """
                            <html>\n
                            <body>\n
                            <h1>Hello world!</h1>\n
                            <p>This is my very first, embedded Tomcat, HTML page!</p>\n
                            </body>\n
                            </html>
                            """
            );
        } else if (request.getRequestURI().equals("/invoices")) {
            response.setContentType("application/json; charset=UTF-8");
            List<Invoice> invoices= invoiceService.findAll(); //invoiceService.findAll(); 
            response.getWriter().print(objectMapper.writeValueAsString(invoices));
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        if (request.getRequestURI().equals("/invoices")) {
            String userId = request.getParameter("user_id");
            Integer amount = Integer.valueOf(request.getParameter("amount"));

            Invoice invoice = invoiceService.create(userId, amount);
            response.setContentType("application/json; charset=UTF-8");
            String json = objectMapper.writeValueAsString(invoice);
            response.getWriter().print(json);
        } else {
            response.setStatus(HttpServletResponse.SC_NOT_FOUND);
        }
    }
}
