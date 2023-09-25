package academy.learnprogramming.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.WebApplicationInitializer;
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;
import org.springframework.web.servlet.DispatcherServlet;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRegistration;

@Slf4j
public class WebAppInitialiser implements WebApplicationInitializer {

    private static final String DISPATCHER_SERVLET_NAME = "dispatcher";

    @Override
    public void onStartup(ServletContext servletContext) throws ServletException {

        /* Summary:

            - Spring will look for any class which implements the WebApplicationInitializer interface.
            - Spring will call this method onStartup() to initialise the web application.
            - Structure of the program is as follows:

                Spring Context:                         (context)
                    Servlet Context (Tomcat):           (servletContext)
                        Servlet                         (dispatcherServlet)

            - dispatcherServlet is the front controller and used to dispatch HTTP requests to other controllers (presumably within the same or other containers).
            - servletContext defines an API (set of methods) that the servlet (dispatcherServlet) can use to communicate with its servlet container e.g. Tomcat
            - servletContext should not be confused with the overall Spring container (AnnotationConfigWebApplicationContext).

        */
        log.info("onStartup");

        // create the spring application context i.e. container for housing a web application
        AnnotationConfigWebApplicationContext context = new AnnotationConfigWebApplicationContext();

        // pass the @Configuration class that is used to configure the web application
        context.register(WebConfig.class);

        // create the dispatcher servlet passing reference to the Spring Container that it will be housed within
        DispatcherServlet dispatcherServlet = new DispatcherServlet(context);

        // register the dispatcher servlet with the Servlet Container servletContext that was passed as an argument to this onStartup() method
        ServletRegistration.Dynamic registration = servletContext.addServlet(DISPATCHER_SERVLET_NAME, dispatcherServlet);

        // the returned ServletRegistration.Dynamic registration can then be used to interact and configure the Dispatcher Servlet within Tomcat.
        registration.setLoadOnStartup(1);           //Dispatcher Servlet will be instantiated by Tomcat upon startup of Tomcat.
        registration.addMapping("/");       //Dispatcher Servlet will map the URL pattern "/" (which defines 'webapp' as the root) i.e. the actual web application (rather than the default homepage of the Tomcat application server)
    }
}
