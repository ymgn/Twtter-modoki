package com.example.twittermodoki.usecases.user

import com.example.twittermodoki.domain.User.User
import com.example.twittermodoki.domain.User.UserRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Component

@Component
class UserLogin(
        @Autowired private val userRepository: UserRepository
) {
    fun login(id: String, password: String): User? {
        return userRepository.find(id, password)
    }
}
