package ru.btpit.nmedia.Repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import ru.btpit.nmedia.MainActivity
import ru.btpit.nmedia.Post

class PostRepositoryInMemoryImpl: PostRepository {
    private var post = Post(
        id = 1,
        author = "NIKE",
        content = "Найди свой стиль танца на регулярных классах в Nike Box MSK. От хип-хопа до вога и от дэнсхолла до хауса – можно пробовать новые направления хоть каждый день. У нас есть занятия не только для взрослых, но и для самых юных танцоров. Все классы бесплатные, а уровень подготовки не имеет значения.",
        published = "21 мая в 18:36",
        likedByMe = false,
        shareByMe = false
    )
    private val data = MutableLiveData(post)

    override fun get(): LiveData<Post> = data
    override fun like() {
        post = post.copy(likedByMe = !post.likedByMe)
        if (post.likedByMe) post.likes++ else post.likes--
        data.value = post
    }

    override fun share()
    {
        post = post.copy(shareByMe = !post.shareByMe)
        if (post.shareByMe) post.share++
        data.value = post
    }
}