package com.example.sosproject;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitClientInstance {
    private static Retrofit retrofit;
    private static final String base_URL = "http://10.0.2.2:8080/";

    public static final Retrofit getRetrofitInstance(){

        // 클래스 멤버 변수 retrofit에 아무것도 할당되어 있지 않다면
        if(retrofit == null){
            // base_URL에 적힌 서버 연결
            retrofit = new Retrofit.Builder().baseUrl(base_URL).addConverterFactory(GsonConverterFactory.create()).build();
        }
        return retrofit;
    }
}
