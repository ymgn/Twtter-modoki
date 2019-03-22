package com.example.twittermodoki.usecases

import com.example.twittermodoki.domain.Tweet.Tweet
import com.example.twittermodoki.domain.Tweet.TweetRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Component

/**
 * 投稿されている全てのTweetを取得する
 */
@Component
class GetTweets(
        @Autowired private val tweetRepository: TweetRepository
) {
    fun get(): ArrayList<Tweet?> {
        return this.tweetRepository.findAll()
    }
}
