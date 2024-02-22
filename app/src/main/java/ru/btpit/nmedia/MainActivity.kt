package ru.btpit.nmedia
import android.annotation.SuppressLint
import android.icu.number.NumberFormatter
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import ru.btpit.nmedia.databinding.ActivityMainBinding
import android.view.View
import android.widget.ImageView
import android.widget.TextView

class MainActivity : AppCompatActivity() {
    @SuppressLint("MissingInflatedId", "SetTextI18n")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

            val post = Post(
                id = 1,
                author = "NIKE",
                content = "Найди свой стиль танца на регулярных классах в Nike Box MSK. От хип-хопа до вога и от дэнсхолла до хауса – можно пробовать новые направления хоть каждый день. У нас есть занятия не только для взрослых, но и для самых юных танцоров. Все классы бесплатные, а уровень подготовки не имеет значения.",
                published = "21 мая в 18:36",
                likedByMe = false
            )
        with(binding) {
                if (post.likedByMe) {
                    imageView10?.setImageResource(R.drawable.heart_svgrepo_com)
                }
                imageView10?.setOnClickListener {
                    post.likedByMe = !post.likedByMe
                    imageView10.setImageResource(
                        if (post.likedByMe) R.drawable.heart_svgrepo_com else R.drawable.hearth_svgrepo_com)
                    if (post.likedByMe) post.likes++ else post.likes--
                    textView9?.text = post.likes.toString()
                    val s = if (post.likes >= 1000){
                        textView9?.text = "${post.likes / 1000}.${post.likes % 1000 / 100}К"
                    } else {
                        textView9.toString();
                    }
                    if (post.likes >= 10000){
                        textView9?.text = "${post.likes / 1000}.${post.likes % 1000 / 100}К"
                    } else{
                        textView10.toString();
                    }
                    if (post.likes >= 1000000){
                        textView9?.text = "${post.likes / 1000000}.${post.likes % 1000 / 100}М"
                    } else{
                        textView9.toString();
                    }
                }
                }
            with(binding) {
                imageView11?.setOnClickListener {
                    post.shareByMe = !post.shareByMe
                    if (post.shareByMe) post.share++
                    textView10?.text = post.share.toString()
                    val s = if (post.share >= 1000){
                        textView10?.text = "${post.share / 1000}.${post.share % 1000 / 100}К"
                    } else {
                        textView10.toString();
                    }
                    if (post.share >= 10000){
                        textView10?.text = "${post.share / 1000}.${post.share % 1000 / 100}К"
                    } else{
                        textView10.toString();
                    }
                    if (post.share >= 1000000){
                        textView10?.text = "${post.share / 1000000}.${post.share % 1000 / 100}М"
                    } else{
                        textView10.toString();
                    }
                }
            }
    }
}
    data class Post(
        val id: Long,
        val author: String,
        val content: String,
        val published: String,
        var likes: Int = 999999,
        var share: Int = 999995,
        var likedByMe: Boolean = false,
        var shareByMe: Boolean = false
    )





