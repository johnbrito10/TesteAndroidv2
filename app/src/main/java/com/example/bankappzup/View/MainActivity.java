package com.example.bankappzup.View;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.bankappzup.Model.BankApiClient;
import com.example.bankappzup.R;
import com.parse.LogInCallback;
import com.parse.ParseException;
import com.parse.ParseUser;

import static com.example.bankappzup.Model.BankApiClient.Pass;

public class MainActivity extends AppCompatActivity {

    EditText Senha;
    Button Login;

    public    String MinhaSenha, MeuUser;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);



        Senha = (EditText)findViewById(R.id.Senha);
        Login = (Button)findViewById(R.id.BtLogin);

        MinhaSenha = Senha.toString();
        MeuUser = Login.toString();



        Login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                if( senhaForte(MinhaSenha)){

                    //Fazer Login do Usuário

                    ParseUser.logInInBackground(MeuUser, MinhaSenha, new LogInCallback() {
                        @Override
                        public void done(ParseUser user, ParseException e) {

                            if(e == null){
                                Log.i("VerificaLoginUsuario","Login Realizado com sucesso");
                                Intent PassarSenha = new Intent(MainActivity.this, BankApiClient.class);
                                PassarSenha.putExtra(Pass,MinhaSenha);
                                Intent NextActivity = new Intent(MainActivity.this,ContentActivity.class);
                                startActivity(NextActivity);
                            }else{
                                Log.i("VerificaLoginUsuario","Erro ao fazer Login do usuário" + e.getMessage());
                            }

                        }
                    });

                }

            }
        });



    }


    //Função que Verifica se a senha possui os atributos mínimos e te retorna um boleano
    public static boolean senhaForte(String senha) {
        if (senha.length() < 6) return false;

        boolean achouNumero = false;
        boolean achouMaiuscula = false;
        boolean achouMinuscula = false;
        boolean achouSimbolo = false;
        for (char c : senha.toCharArray()) {
            if (c >= '0' && c <= '9') {
                achouNumero = true;
            } else if (c >= 'A' && c <= 'Z') {
                achouMaiuscula = true;
            } else if (c >= 'a' && c <= 'z') {
                achouMinuscula = true;
            } else {
                achouSimbolo = true;
            }
        }
        return achouNumero && achouMaiuscula && achouMinuscula && achouSimbolo;
    }

    //Função de Testes Para verificar se o usuario está logado
    public void VerificaUsuarioLogado(){
        if (ParseUser.getCurrentUser() != null){//Logado
            Log.i("LoginUsuario","Usuário está Logado");
            }else{//Não está Logado
            Log.i("LoginUsuario","Usuário não está Logado");
             }
    }
}
