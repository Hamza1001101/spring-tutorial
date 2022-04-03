package org.example.web;

import org.example.context.Application;
import org.example.model.Invoice;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

public class MyFancyPdfInvoicesServlet extends HttpServlet {

    
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
            List<Invoice> invoices= Application.invoiceService.findAll(); //invoiceService.findAll(); 
            response.getWriter().print(Application.objectMapper.writeValueAsString(invoices));
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        if (request.getRequestURI().equals("/invoices")) {
            String userId = request.getParameter("user_id");
            Integer amount = Integer.valueOf(request.getParameter("amount"));

            Invoice invoice = Application.invoiceService.create(userId, amount);
            response.setContentType("application/json; charset=UTF-8");
            String json = Application.objectMapper.writeValueAsString(invoice);
            response.getWriter().print(json);
        } else {
            response.setStatus(HttpServletResponse.SC_NOT_FOUND);
        }
    }
}
