package com.example.bankappzup.View;

import android.icu.text.TimeZoneFormat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.Adapter;
import android.widget.TextView;
import android.widget.Toast;

import com.example.bankappzup.Controller.ListAdapter;
import com.example.bankappzup.Model.BankApiClient;
import com.example.bankappzup.Model.ListItens;
import com.example.bankappzup.R;
import com.parse.FindCallback;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseQuery;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ContentActivity extends AppCompatActivity {


    private RecyclerView recyclerView;
    private RecyclerView.Adapter adapter;

    public TextView Nome,Conta,Saldo;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_content);

        recyclerView = (RecyclerView) findViewById(R.id.recyclerview);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        Nome =  (TextView) findViewById(R.id.Nome);
        Conta =  (TextView) findViewById(R.id.Conta);
        Saldo  =  (TextView) findViewById(R.id.Saldo);


        CarregarConteudo();
    }



    private void CarregarConteudo(){

        //HeaderContent
        ParseQuery<ParseObject> filtro = ParseQuery.getQuery("item");

        filtro.findInBackground(new FindCallback<ParseObject>() {
            @Override
            public void done(List<ParseObject> objects, ParseException e) {
                if (e == null) {//efetuada a listagem sem erro
                    for(ParseObject object : objects){

                     Nome.setText(object.get("nome").toString());
                     Conta.setText(object.get("conta").toString());
                     Saldo.setText(object.get("saldo").toString());




                    }

                     }

                    else{

                        Log.i("ListarDados","Erro" + e.getMessage());
                }

            }
        });




        //Recycler View Content
        Retrofit retrofit =  new Retrofit.Builder()
                .baseUrl(BankApiClient.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        BankApiClient Api = retrofit.create(BankApiClient.class);

        Call<List<ListItens>> call = Api.getItens();

        call.enqueue(new Callback<List<ListItens>>() {
            @Override
            public void onResponse(Call<List<ListItens>> call, Response<List<ListItens>> response) {

                List<ListItens> Itens = response.body();

                for (ListItens I: Itens){
                    Log.d("title",I.getTitle());
                    Log.d("desc",I.getDesc());
                    Log.d("date",I.getDate());
                    Log.d("value",I.getValue());
                }


                adapter = new ListAdapter(Itens,getApplicationContext());
                recyclerView.setAdapter(adapter);

            }

            @Override
            public void onFailure(Call<List<ListItens>> call, Throwable t) {

                Toast.makeText(getApplicationContext(),t.getMessage(),Toast.LENGTH_LONG).show();
            }
        });



    }
}
