package com.example.twittermodoki.usecases

import com.example.twittermodoki.domain.Tweet.Tweet
import com.example.twittermodoki.domain.Tweet.TweetRepositoryInterface
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Component

/**
 * 投稿されたTweetを保存する際のUseCase
 */
@Component
class RegistrationTweet(
        @Autowired private val tweetRepository: TweetRepositoryInterface
) {
    fun registrationTweet(tweet: Tweet): Boolean {
        this.tweetRepository.save(tweet)
        return true
    }
}
