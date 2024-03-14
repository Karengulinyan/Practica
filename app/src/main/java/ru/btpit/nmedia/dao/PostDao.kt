package ru.btpit.nmedia.dao

import ru.btpit.nmedia.Post


interface PostDao {
    fun fetAll(): List<Post>
    fun save(post: Post ): Post
    fun likeById(id: Long)
    fun removeById(Id: Long)
    fun getAll(): List<Post>
}