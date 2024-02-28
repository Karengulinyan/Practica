package ru.btpit.nmedia.viewmodel

import androidx.lifecycle.ViewModel
import ru.btpit.nmedia.Repository.PostRepository
import ru.btpit.nmedia.Repository.PostRepositoryInMemoryImpl

class PostViewModel: ViewModel() {
    private val repository: PostRepository = PostRepositoryInMemoryImpl()
    val data = repository.get()
    fun like() = repository.like()
    fun share() = repository.share()
}
