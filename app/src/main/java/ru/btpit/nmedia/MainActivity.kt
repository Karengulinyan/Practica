package ru.btpit.nmedia

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.activity.viewModels
import ru.btpit.nmedia.R
import ru.btpit.nmedia.adapter.OnInteractionListener
import ru.btpit.nmedia.adapter.PostsAdapter
import ru.btpit.nmedia.databinding.ActivityMainBinding
import ru.btpit.nmedia.Post
import ru.btpit.nmedia.util.AndroidUtils
import ru.btpit.nmedia.viewmodel.PostViewModel


class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val viewModel: PostViewModel by viewModels()
        val adapter = PostsAdapter(object : OnInteractionListener {
            override fun onEdit(post: Post) {
                viewModel.edit(post)
            }

            override fun onLike(post: Post) {
                viewModel.likeById(post.id)
            }

            override fun onRemove(post: Post) {
                viewModel.removeById(post.id)
            }

            override fun onShare(post: Post) {
                viewModel.shareById((post.id))
            }
        })
        binding.list.adapter = adapter
        viewModel.data.observe(this) { posts ->
            adapter.submitList(posts)
        }

        viewModel.edited.observe(this) { post ->
            if (post.id == 0L) {
                return@observe
            }
            with(binding.content) {
                requestFocus()
                setText(post.content)
            }
        }

        binding.save.setOnClickListener {
            with(binding.content) {
                if (text.isNullOrBlank()) {
                    Toast.makeText(
                        this@MainActivity,
                        "Ошибка",
                        //  context.getString(com.google.android.material.R.string.error_icon_content_description)
                        Toast.LENGTH_SHORT
                    ).show()
                    return@setOnClickListener
                }
                viewModel.changeContent(text.toString())
                viewModel.save()
                setText("")
                clearFocus()
                AndroidUtils.hideKeyboard(this)
            }
        }
    }
}
           //binding.container.removeAllViews()
            /*posts.map { post ->
                CardPostBinding.inflate(layoutInflater, binding.container, true).apply {
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
                    imageView10.setOnClickListener {
                        viewModel.likeById(post.id)
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
                    imageView11.setOnClickListener {
                        viewModel.shareById(post.id)
                    }
                }.root
            }*/

data class Post(
    val id: Long,
    val author: String,
    val content: String,
    val published: String,
    var likes: Int = 99999,
    var share: Int = 995,
    var likedByMe: Boolean = false,
    var shareByMe: Boolean = false

)









