package ru.btpit.nmedia

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import ru.btpit.nmedia.databinding.ActivityMainBinding
import ru.btpit.nmedia.viewmodel.PostViewModel

class MainActivity : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val viewModel: PostViewModel by viewModels()
        viewModel.data.observe(this) {post: Post ->
            with(binding) {
                textView1.text = post.author
                textView2.text = post.published
                textView3.text = post.content
                imageView10.setImageResource(
                    if (post.likedByMe) R.drawable.heart_svgrepo_com else R.drawable.hearth_svgrepo_com
                )
                textView9.text = post.likes.toString()

                if (post.likes >= 1000)
                {
                    textView9.text = "${post.likes / 1000}.${post.likes % 1000 / 100}К"
                } else {
                    textView9.text
                }
                if (post.likes >= 10000)
                {
                    textView9.text  ="${post.likes / 1000}.${post.likes % 1000 / 100}К"
                } else{
                    textView9.text
                }
                if (post.likes >= 1000000)
                {
                    textView9.text  = "${post.likes / 1000000}.${post.likes % 1000 / 100}М"
                } else{
                    textView9.text
                }

                binding.imageView10.setOnClickListener {
                    viewModel.like()
                }

                textView10?.text = post.share.toString()
                if (post.share >= 1000)
                {
                    textView10.text = "${post.share / 1000}.${post.share % 1000 / 100}К"
                } else {
                    textView10.text
                }
                if (post.share >= 10000)
                {
                    textView10.text = "${post.share / 1000}.${post.share % 1000 / 100}К"
                } else{
                    textView10.text
                }
                if (post.share >= 1000000)
                {
                    textView10.text = "${post.share / 1000000}.${post.share % 1000 / 100}М"
                } else{
                    textView10.text
                }

                binding.imageView11.setOnClickListener {
                    viewModel.share()
                }
            }
        }

}}
data class Post(
    val id: Long,
    val author: String,
    val content: String,
    val published: String,
    var likes: Int = 9999,
    var share: Int = 9995,
    var likedByMe: Boolean = false,
    var shareByMe: Boolean = false
)









