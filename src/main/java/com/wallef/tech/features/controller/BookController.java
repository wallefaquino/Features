package com.wallef.tech.features.controller;

import java.io.IOException;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.wallef.tech.features.model.Book;
import com.wallef.tech.features.retrofit.BookService;

import retrofit2.Call;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

@RestController
@RequestMapping("/v1/library")
public class BookController {
	
	public Retrofit buildRetrofit() {
		return new Retrofit.Builder()
				.baseUrl("http://localhost:8080/")
				.addConverterFactory(GsonConverterFactory.create())
				.build();
	}
	
	@GetMapping(value = "/book/{id}")
	public ResponseEntity<Book> getBook(@PathVariable("id")Long id) throws IOException {
		
		
		Retrofit retrofit = buildRetrofit();
		BookService service = retrofit.create(BookService.class);
		Call<Book> bookCall = service.getBook(id);
		Response<Book> response = bookCall.execute();
		
		if(response.isSuccessful())
			return new ResponseEntity<Book>(response.body(), HttpStatus.OK);
		else 
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
	}
	
}



