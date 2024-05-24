package com.example.realtime

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import com.google.firebase.Firebase
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase

class ReadActivity : AppCompatActivity() {
    private lateinit var databaseReference: DatabaseReference
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_read)

            var id_read = findViewById<EditText>(R.id.id_read)
        var sub_button = findViewById<Button>(R.id.sub_button)
        var tvId = findViewById<TextView>(R.id.tvId)
        var tvName = findViewById<TextView>(R.id.tvName)
        var tvPrice = findViewById<TextView>(R.id.tvPrice)
        sub_button.setOnClickListener {
            var get_id = id_read.text.toString().trim()

            if (get_id.isNotEmpty()) {
                databaseReference = FirebaseDatabase.getInstance().getReference("ProductInfo")
                databaseReference.child(get_id).get().addOnSuccessListener {
                    if (it.exists()) {
                        val p_name = it.child("name").value
                        val p_id = it.child("id").value
                        val p_price = it.child("price").value

                        tvId.text = p_id.toString()
                        tvName.text = p_name.toString()
                        tvPrice.text = p_price.toString()
                    } else {
                        Toast.makeText(applicationContext, "Can't find data", Toast.LENGTH_SHORT)
                            .show()
                    }
                }
            } else {
                Toast.makeText(applicationContext, "please enter", Toast.LENGTH_SHORT).show()
            }

        }


    }
}