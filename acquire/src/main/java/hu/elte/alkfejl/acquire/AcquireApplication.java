package hu.elte.alkfejl.acquire;

import hu.elte.alkfejl.acquire.annotation.AuthInterceptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

@SpringBootApplication
public class AcquireApplication extends WebMvcConfigurerAdapter{

        @Autowired
        private AuthInterceptor autInterceptor;
        
        @Override
        public void addInterceptors(InterceptorRegistry registry)
        {
            registry.addInterceptor(autInterceptor);
        }
    
	public static void main(String[] args) {
		SpringApplication.run(AcquireApplication.class, args);
	}
}
