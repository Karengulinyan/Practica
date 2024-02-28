package ru.btpit.nmedia.Repository

import androidx.lifecycle.LiveData
import ru.btpit.nmedia.MainActivity
import ru.btpit.nmedia.Post

interface PostRepository {
    fun get(): LiveData<Post>
    fun like()
    fun share()

}