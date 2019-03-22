package com.example.twittermodoki.infrastructure

import com.example.twittermodoki.domain.Tweet.Tweet
import com.example.twittermodoki.domain.Tweet.TweetRepository
import org.springframework.stereotype.Component

@Component
class TweetRepositoryOnMemory : TweetRepository {
    private var datas: ArrayList<Tweet?> = arrayListOf()

    override fun save(tweet: Tweet): Boolean {
        this.datas.add(tweet)
        return true
    }

    override fun findAll(): ArrayList<Tweet?> {
        return  this.datas
    }
}
