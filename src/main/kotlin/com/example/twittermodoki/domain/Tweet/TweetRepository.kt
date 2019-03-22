package com.example.twittermodoki.domain.Tweet

interface TweetRepository {

    /**
     * Tweetを保存する
     */
    fun save(tweet: Tweet): Boolean

    /**
     * 保存されているTweetを全て取得
     */
    fun findAll(): ArrayList<Tweet?>
}
