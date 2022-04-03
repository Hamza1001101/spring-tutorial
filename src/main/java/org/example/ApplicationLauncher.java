package org.example;

import org.apache.catalina.Context;
import org.apache.catalina.LifecycleException;
import org.apache.catalina.Wrapper;
import org.apache.catalina.startup.Tomcat;
import org.example.web.MyFancyPdfInvoicesServlet;

public class ApplicationLauncher {
    public static void main(String[] args) throws LifecycleException {

        Tomcat tomcat = new Tomcat();
        tomcat.setPort(8081);
        
        //bootstraps tomcat's HTTP engine
        tomcat.getConnector();
        
        Context ctx = tomcat.addContext("", null);
        Wrapper servlet = Tomcat.addServlet(ctx, "myFirstServlet", new MyFancyPdfInvoicesServlet()); 
        servlet.setLoadOnStartup(1);
        servlet.addMapping("/*");
        
        tomcat.start();
    }
}
