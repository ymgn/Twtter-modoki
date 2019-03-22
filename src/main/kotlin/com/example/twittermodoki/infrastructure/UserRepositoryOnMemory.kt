package com.example.twittermodoki.infrastructure

import com.example.twittermodoki.domain.User.User
import com.example.twittermodoki.domain.User.UserRepository
import org.springframework.stereotype.Component

@Component
class UserRepositoryOnMemory : UserRepository {
    private var data: ArrayList<User> = arrayListOf()

    override fun save(user: User): Boolean {

        // 既に同じIDが存在すれば保存失敗
        this.data.forEach{ datum ->
            if (datum.id == user.id) {
                return false
            }
        }

        this.data.add(user)
        return true
    }

    override fun find(id: String, password: String): User? {
        this.data.forEach{ datum ->
            if (datum.id === id && datum.password === password) {
                return datum
            }
        }
        return null
    }

    override fun findById(id: String): User? {
        this.data.forEach{ datum ->
            if (datum.id === id) {
                return datum
            }
        }
        return null
    }
}
