package com.castgroup.assignment3;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.castgroup.assignment3.repository.PessoaData;

@SpringBootApplication
public class Assignment3Application {

	public static void main(String[] args) {
		PessoaData.inicializar();
		SpringApplication.run(Assignment3Application.class, args);
	}

}
