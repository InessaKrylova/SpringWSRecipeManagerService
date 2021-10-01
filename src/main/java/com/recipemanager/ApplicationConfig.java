package com.recipemanager;

import org.springframework.boot.autoconfigure.web.servlet.DispatcherServletAutoConfiguration;
import org.springframework.boot.autoconfigure.web.servlet.DispatcherServletRegistrationBean;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.web.servlet.DispatcherServlet;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.ws.config.annotation.EnableWs;
import org.springframework.ws.transport.http.MessageDispatcherServlet;
import org.springframework.ws.wsdl.wsdl11.DefaultWsdl11Definition;
import org.springframework.xml.xsd.SimpleXsdSchema;
import org.springframework.xml.xsd.XsdSchema;

@EnableWs
@Configuration
@EnableWebMvc
public class ApplicationConfig implements WebMvcConfigurer {//extends WsConfigurerAdapter {

    @Bean
    public ServletRegistrationBean dispatcherServletRegistration(ApplicationContext applicationContext) {
        MessageDispatcherServlet messageDispatcherServlet = new MessageDispatcherServlet();
        messageDispatcherServlet.setApplicationContext(applicationContext);
        messageDispatcherServlet.setTransformWsdlLocations(true);

        return new ServletRegistrationBean(messageDispatcherServlet, "/ws/*");
    }

    @Bean
    public DispatcherServletRegistrationBean dispatcherServletasasas(ApplicationContext applicationContext) {
        DispatcherServlet dispatcherServlet = new DispatcherServlet();
        dispatcherServlet.setApplicationContext(applicationContext);

        DispatcherServletRegistrationBean dispatcherBean = new DispatcherServletRegistrationBean(dispatcherServlet , "/recipes/*");
        dispatcherBean.setName("restDispatcher");
        dispatcherBean.setLoadOnStartup(1);

        return dispatcherBean;
    }

    @Bean(name = "recipeManagerServiceWsdl")
    public DefaultWsdl11Definition defaultWsdl11Definition(XsdSchema recipesSchema) {
        DefaultWsdl11Definition wsdl11Definition = new DefaultWsdl11Definition();

        wsdl11Definition.setPortTypeName("RecipeManagerService");
        wsdl11Definition.setRequestSuffix("Request");
        wsdl11Definition.setResponseSuffix("Response");
        wsdl11Definition.setLocationUri("/ws");
        wsdl11Definition.setTargetNamespace("http://spring.io/guides/gs-producing-web-service");
        wsdl11Definition.setSchema(recipesSchema);

        return wsdl11Definition;
    }

    @Bean
    public XsdSchema recipesSchema() {
        return new SimpleXsdSchema(new ClassPathResource("recipeService.xsd"));
    }
}
