package com.example.twittermodoki.controllers

import com.example.twittermodoki.domain.Tweet.Tweet
import com.example.twittermodoki.usecases.GetTweets
import com.example.twittermodoki.usecases.RegistrationTweet
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.MediaType
import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestParam

@Controller
class IndexController(
        @Autowired val getTweets: GetTweets,
        @Autowired val registrationTweet: RegistrationTweet
        )
{
    // TOP
    @GetMapping("/")
    fun index(model: Model): String {
        // 全てのTweetを取得する
        val tweets = getTweets.get()
        model.addAttribute("tweets", tweets)
        return "index"
    }

    @PostMapping("/tweet",
    produces = arrayOf(MediaType.APPLICATION_FORM_URLENCODED_VALUE))
    fun registTweet(model: Model, @RequestParam requestParam: Map<String,String>): String {

        val tweetMessage: String = requestParam["message"] ?: ""

        if(tweetMessage.isEmpty()) {
            // エラーのメッセージだしたい
            return "redirect:/"
        }

        val tweet = Tweet(tweetMessage,"ymgn")
        registrationTweet.registrationTweet(tweet)

        return "redirect:/"
    }
}
