package com.fiap.patyalves.firebaseapp

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import com.google.firebase.auth.FirebaseAuth
import kotlinx.android.synthetic.main.activity_login.*
import android.widget.Toast
import com.fiap.patyalves.firebaseapp.extensions.getText
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.auth.AuthResult
import com.google.android.gms.tasks.Task
import android.support.annotation.NonNull
import com.google.android.gms.tasks.OnCompleteListener
import android.R.attr.password




class LoginActivity : AppCompatActivity() {

    //private var mAuth: FirebaseAuth? = null
    private lateinit var mAuth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        mAuth = FirebaseAuth.getInstance()


        btnCriar.setOnClickListener {
            //v ->
            mAuth.createUserWithEmailAndPassword(inputEmail.getText(), inputSenha.getText())
                    .addOnCompleteListener(this) { task ->
                        if (task.isSuccessful) {
                            val user = mAuth.currentUser
                            Toast.makeText(this, "Sucesso!!!!.",
                                    Toast.LENGTH_SHORT).show()
                           // updateUI(user)
                        } else {
                            Toast.makeText(this, "Authentication failed.",
                                    Toast.LENGTH_SHORT).show()
                            //updateUI(null)
                        }

                    }
        }

        btnLogin.setOnClickListener(){
            mAuth.signInWithEmailAndPassword(inputEmail.getText(), inputSenha.getText())
                    .addOnCompleteListener(this) { task ->
                        if (task.isSuccessful) {
                            val user = mAuth.currentUser
                          //  updateUI(user)
                            Toast.makeText(this, "Sucesso!!!!.",
                                    Toast.LENGTH_SHORT).show()
                        } else {
                            Toast.makeText(this, "Authentication failed.",
                                    Toast.LENGTH_SHORT).show()
                           // updateUI(null)
                        }

                    }
        }

        btnEmail.setOnClickListener(){
            val user = mAuth.currentUser
            user?.sendEmailVerification()
                    ?.addOnCompleteListener(this, OnCompleteListener {
                task -> if (task.isSuccessful){
                        Toast.makeText(this, "Enviado com Sucesso!!!!.",
                                Toast.LENGTH_SHORT).show()

                }else{

                }
            })
        }
    }



}
