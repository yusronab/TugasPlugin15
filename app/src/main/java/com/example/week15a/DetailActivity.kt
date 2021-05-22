package com.example.week15a

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.week15a.api.APIClient
import com.example.week15a.api.SingleResponse
import com.example.week15a.databinding.ActivityDetailBinding
import com.example.week15a.model.Post
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class DetailActivity : AppCompatActivity() {
    private lateinit var binding: ActivityDetailBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        getPostById()
        iconDelete()
        btnBack()
    }

    fun showPostsToView(post: Post){
        binding.tvFirstName.text = post.first_name
        binding.tvLastName.text = post.last_name
        binding.tvEmail.text = post.email
    }

    fun getPostById(){
        APIClient.APIendPoint().getPostById(intent.getIntExtra("id", 0)).enqueue(object : Callback<SingleResponse<Post>>{
            override fun onResponse(
                call: Call<SingleResponse<Post>>,
                response: Response<SingleResponse<Post>>
            ) {
                if (response.isSuccessful){
                    val body = response.body()
                    if (body != null){
                        showPostsToView(body.data)
                        Toast.makeText(applicationContext, "Data Diterima", Toast.LENGTH_SHORT).show()
                    }
                }
            }

            override fun onFailure(call: Call<SingleResponse<Post>>, t: Throwable) {
                println(t.message)
            }

        })
    }

    private fun deletePost(){
        APIClient.APIendPoint().delete(intent.getIntExtra("id", 0)).enqueue(object : Callback<Void>{
            override fun onResponse(call: Call<Void>, response: Response<Void>) {
                if (response.isSuccessful){
                    Toast.makeText(applicationContext, response.code().toString(), Toast.LENGTH_SHORT).show()
                }
            }

            override fun onFailure(call: Call<Void>, t: Throwable) {
                println(t.message)
            }
        })
    }

    private fun iconDelete(){
        binding.iconDelete.setOnClickListener {
            deletePost()
            finish()
        }
    }

    private fun btnBack(){
        binding.btnBack.setOnClickListener {
            startActivity(Intent(this, MainActivity::class.java))
            finish()
        }
    }
}