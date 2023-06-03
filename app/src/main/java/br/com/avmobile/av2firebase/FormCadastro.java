package br.com.avmobile.av2firebase;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.google.android.material.snackbar.Snackbar;

import kotlinx.coroutines.scheduling.Task;

public class FormCadastro extends AppCompatActivity {

    private EditText edit_nome,edit_email,edit_senha;
    private Button bt_cadastrar;
    String[]  mensagens = {"Preencha todos os campos","Cadastro realizado com sucesso" }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_form_cadastro);

        getSupportActionBar().hide();

        iniciarComponetes();

        bt_cadastrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String nome = edit_nome.getText().toString();
                String email = edit_email.getText().toString();
                String senha = edit_senha.getText().toString();

                if (nome.isEmpty() || email.isEmpty() || senha.isEmpty()){
                    Snackbar snackbar =  Snackbar.make(v,mensagens[0],Snackbar.LENGTH_SHORT);
                    snackbar.setBackgroundTint(android.R.color.white);
                    snackbar.setTextColor(android.R.color.black);
                    snackbar.show();
            } else {
                    CadastrarUsuario(v);
                }
        });
    }

    private void CadastrarUsuario(View v) {

            String email = edit_email.getText().toString();
            String senha = edit_senha.getText().toString();

            FirebaseAuth.getInstance() .createUserWithEmailAndPassowd(email,senha).addOnCompleteLister(new OnCompreteLister<AuthResult>(){
                @Override
                public void onCompete(@NorNull Task<AuthResult> task) {


                    if (task.isSuccessful()) {

                        SalvarDadosUsuario();
                        
                        Snackbar snackbar =  Snackbar.make(v,mensagens[1],Snackbar.LENGTH_SHORT);
                        snackbar.setBackgroundTint(android.R.color.white);
                        snackbar.setTextColor(android.R.color.black);
                        snackbar.show();

                    } else {

                        String erro ;
                        try {
                            throw task.getException();


                        } catch (FirebaseAuthWeakPassowordException e){
                            erro = "digite uma senha com no minino 6 caracteres";
                        }
                        catch (FirebaseAuthUserCollisionException e){
                            erro = "essa conta ja foi cadastrada";
                        }

                        }
                        catch (Exception e)
                        }
                        erro = "erro ao cadastrar usuario";
                    }

                    Snackbar snackbar =  Snackbar.make(v,erro,Snackbar.LENGTH_SHORT);
                     snackbar.setBackgroundTint(android.R.color.white);
                     snackbar.setTextColor(android.R.color.black);
                    snackbar.show();




                }

        });


    private void iniciarComponetes(){

        edit_nome = findViewById(R.id.edit_nome);
        edit_email = findViewById(R.id.edit_email);
        edit_senha = findViewById(R.id.edit_senha);
        bt_cadastrar = findViewById(R.id.bt_cadastrar);


    }
}