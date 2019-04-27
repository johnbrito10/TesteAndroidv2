package com.example.bankappzup.Model;

import com.example.bankappzup.View.MainActivity;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface BankApiClient {

String BASE_URL =  " https://bank-app-test.herokuapp.com/api/statements/";
String Pass = "";


@GET(Pass)
    Call<List<ListItens>> getItens();


}
