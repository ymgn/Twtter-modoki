package com.example.twittermodoki.domain.User

interface UserRepository {

    /**
     * Userを保存する
     */
    fun save(user: User): Boolean

    /**
     * 保存されているUserを取得
     */
    fun find(id: String, password: String): User?

    fun findById(id: String): User?
}
