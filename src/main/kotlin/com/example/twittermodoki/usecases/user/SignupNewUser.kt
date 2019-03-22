package com.example.twittermodoki.usecases.user

import com.example.twittermodoki.domain.User.User
import com.example.twittermodoki.domain.User.UserRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Component

@Component
class SignupNewUser(
        @Autowired private val userRepository: UserRepository
) {
    fun signup(id: String, password: String): Boolean {
        val findUser = this.userRepository.findById(id)

        // 既にIDが存在する場合
        if (findUser != null) {
            return false
        }

        val newUser = User(id, password)

        return this.userRepository.save(newUser)
    }
}