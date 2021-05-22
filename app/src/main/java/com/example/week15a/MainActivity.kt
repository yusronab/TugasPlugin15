package com.example.week15a

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.week15a.adapter.MainAdapter
import com.example.week15a.api.APIClient
import com.example.week15a.api.ListResponse
import com.example.week15a.databinding.ActivityMainBinding
import com.example.week15a.model.Post
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    lateinit var mainAdapter: MainAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        getAllPost()
    }

    fun showToRecycle(post: List<Post>){
        mainAdapter = MainAdapter(post, object : MainAdapter.onAdapterClick{
            override fun onClick(post: Post) {
                startActivity(Intent(this@MainActivity, DetailActivity::class.java).apply {
                    putExtra("id", post.id)
                    finish()
                })
            }
        })
        binding.recyclerView.apply {
            adapter = mainAdapter
            layoutManager = LinearLayoutManager(this@MainActivity)
        }
    }

    fun getAllPost(){
        APIClient.APIendPoint().getAllPost().enqueue(object : Callback<ListResponse<Post>>{
            override fun onResponse(
                call: Call<ListResponse<Post>>,
                response: Response<ListResponse<Post>>
            ) {
                if (response.isSuccessful){
                    val body = response.body()
                    if (body != null){
                        showToRecycle(body.data)
                        Toast.makeText(applicationContext, "Data Didapatkan", Toast.LENGTH_SHORT).show()
                    }
                }
            }

            override fun onFailure(call: Call<ListResponse<Post>>, t: Throwable) {
                println(t.message)
            }
        })
    }
}