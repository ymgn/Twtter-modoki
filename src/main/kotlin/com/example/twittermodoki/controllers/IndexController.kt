package com.example.twittermodoki.controllers

import com.example.twittermodoki.domain.Tweet.Tweet
import com.example.twittermodoki.usecases.GetTweets
import com.example.twittermodoki.usecases.RegistrationTweet
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.web.bind.annotation.RequestMapping

@Controller
class IndexController(
        @Autowired val getTweets: GetTweets,
        @Autowired val registrationTweet: RegistrationTweet
        )
{
    // TOP
    @RequestMapping("/")
    fun index(model: Model): String {
        // 投稿実装する前に表示されるように
        val tweet1 = Tweet("本文","ymgn")
        registrationTweet.registrationTweet(tweet1)

        // 全てのTweetを取得する
        val tweets = getTweets.get()
        model.addAttribute("tweets", tweets)
        return "index"
    }
}
