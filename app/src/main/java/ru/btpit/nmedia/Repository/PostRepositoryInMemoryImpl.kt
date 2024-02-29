package ru.btpit.nmedia.Repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import ru.btpit.nmedia.Post


class PostRepositoryInMemoryImpl: PostRepository {
    private var nextId = 1L
    private var posts = listOf(
        Post(
            id = 2,
            author = "NIKE",
            content = "Найди свой стиль танца на регулярных классах в Nike Box MSK. От хип-хопа до вога и от дэнсхолла до хауса – можно пробовать новые направления хоть каждый день. У нас есть занятия не только для взрослых, но и для самых юных танцоров. Все классы бесплатные, а уровень подготовки не имеет значения.",
            published = "5 мая в 18:36",
            likedByMe = false,
            shareByMe = false
        ),
       Post(
            id = 1,
            author = "NIKE",
            content = "Танец – это язык твоего тела. И теперь ты сможешь овладеть им в совершенстве в Nike Box MSK. Здесь ты найдешь площадку для своих самых смелых танцевальных высказываний.",
            published = "21 мая в 18:36",
            likedByMe = false,
            shareByMe = false
        ),
        )
    private val data = MutableLiveData(posts)

    override fun getAll(): LiveData<List<Post>> = data
    override fun likeById(id : Long)
    {
         posts = posts.map{
             if (it.id == id) {
                 val likedByMe = !it.likedByMe
                 val newLikesCount = if (likedByMe) it.likes + 1 else it.likes - 1
                 it.copy(likedByMe = likedByMe, likes = newLikesCount)
             } else {
             it
             }
         }
         data.value = posts

    }

    override fun shareById(id: Long)
    {

        posts = posts.map{
            if (it.id == id) {
                val shareByMe = !it.shareByMe
                val newshareCount = it.share + 1
                it.copy(shareByMe = shareByMe, share = newshareCount)
            } else {
                it
            }
        }
        data.value = posts
    }

    override fun save(post: Post)
    {
        if (post.id == 0L) {
            posts = listOf(
                post.copy(
                    id = nextId++,
                    author = "Me",
                    likedByMe = false,
                    published = "now"
                )
            ) + posts
            data.value = posts
            return
        }

        posts = posts.map {
            if (it.id != post.id) it else it.copy(content = post.content)
        }
        data.value = posts
    }

    override fun removeById(id: Long)
    {
        posts = posts.filter { it.id != id }
        data.value = posts
    }

}