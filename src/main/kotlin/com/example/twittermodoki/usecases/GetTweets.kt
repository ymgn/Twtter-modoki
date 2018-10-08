package com.example.twittermodoki.usecases

import com.example.twittermodoki.domain.Tweet.Tweet
import com.example.twittermodoki.domain.Tweet.TweetRepositoryInterface
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Component

/**
 * 投稿されている全てのTweetを取得する
 */
@Component
class GetTweets(
        @Autowired private val tweetRepository: TweetRepositoryInterface
) {
    fun get(): ArrayList<Tweet?> {
        return this.tweetRepository.findAll()
    }
}
