package com.h5200042.hkdtic.network;



import com.h5200042.hkdtic.model.Products;

import java.util.List;

import io.reactivex.Observable;
import retrofit2.http.GET;

public interface ServiceApi {
    @GET("products.json")
    Observable<List<Products>> getProducts();
}
