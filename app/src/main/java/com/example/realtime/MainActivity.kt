package com.example.realtime

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase

class MainActivity : AppCompatActivity() {
    private lateinit var databaseReference: DatabaseReference
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        var txt_edit1 = findViewById<EditText>(R.id.txt_edit1)
        var txt_edit2 = findViewById<EditText>(R.id.txt_edit2)
        var txt_edit3 = findViewById<EditText>(R.id.txt_edit3)
        var button_add = findViewById<Button>(R.id.button_add)
        var button_show = findViewById<Button>(R.id.button_show)
        //getReference is tree here
        //child key should be unique

        button_add.setOnClickListener {

            val name = txt_edit1.text.toString().trim()
            val id = txt_edit2.text.toString().trim()
            val price = txt_edit3.text.toString().trim()


            val product = hashMapOf(
                "name" to name,
                "id" to id,
                "price" to price
            )
            databaseReference = FirebaseDatabase.getInstance().getReference("ProductInfo")
            databaseReference.child(id).setValue(product).addOnSuccessListener {
                Toast.makeText(applicationContext, "saved", Toast.LENGTH_SHORT).show()
            }.addOnFailureListener {
                Toast.makeText(this, "failed", Toast.LENGTH_SHORT).show()
            }
        }
        button_show.setOnClickListener{
            var i = Intent(applicationContext,ReadActivity::class.java)
            startActivity(i)
        }
    }


}
