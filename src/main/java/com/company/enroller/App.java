package com.company.enroller;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@SpringBootApplication
public class App {

    public static void main(String[] args) {
        SpringApplication.run(App.class, args);
    }
    
//    Adnotacja Bean dotyczy naszego kontenera IoC
//    W naszym kontenerze IoC jesli ktos zapyta o PasswordEncoder to ma sie wyciagnac (wstrzyknac) BCryptPasswordEncoder
//	  Usluga, z ktorej mozna korzystac w naszej aplikacji
    @Bean			 
    public PasswordEncoder passwordEncoder() {
    	return new BCryptPasswordEncoder();
    }
    
    
}