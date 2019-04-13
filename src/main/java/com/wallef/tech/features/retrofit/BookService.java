package com.wallef.tech.features.retrofit;

import com.wallef.tech.features.model.Book;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface BookService {

	@GET("products/{id}")
	Call<Book> getBook(@Path("id") Long id);
	
}
