package com.example.twittermodoki.controllers

import com.example.twittermodoki.domain.Tweet.Tweet
import com.example.twittermodoki.domain.User.User
import com.example.twittermodoki.usecases.GetTweets
import com.example.twittermodoki.usecases.RegistrationTweet
import com.example.twittermodoki.usecases.user.SignupNewUser
import com.example.twittermodoki.usecases.user.UserLogin
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.MediaType
import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.web.bind.annotation.*
import javax.servlet.http.HttpSession

@Controller
class IndexController(
        @Autowired val signupNewUser: SignupNewUser,
        @Autowired val userLogin: UserLogin,
        @Autowired val getTweets: GetTweets,
        @Autowired val registrationTweet: RegistrationTweet,
        @Autowired val session: HttpSession
        )
{
    // TOP
    @GetMapping("/")
    fun index(model: Model): String? {

        if (session.getAttribute("loginUser") == null) {
            return "redirect:/login"
        }

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

    @GetMapping("/login")
    fun loginPage(model: Model): String {
        return "login"
    }

    @PostMapping("/login")
    fun login(model: Model, @RequestParam requestParam: Map<String,String>): String {

        val userId = requestParam["userId"]
        val userPassword = requestParam["userPassword"]
        if (userId == null || userPassword == null) {
            return "redirect:/login"
        }
        val loginUser = userLogin.login(userId, userPassword)
        session.setAttribute("loginUser", loginUser)

        return "redirect:/"
    }

    @GetMapping("/signup")
    fun signupPage(model: Model): String {
        return "signup"
    }

    @PostMapping("/signup")
    fun signup(model: Model, @RequestParam requestParam: Map<String,String>): String {
        val userId = requestParam["userId"]
        val userPassWord = requestParam["userPassWord"]

        if (userId == null || userPassWord == null) {
            return "redirect:/signup"
        }

        val signupResult = signupNewUser.signup(userId, userPassWord)

        if (!signupResult) {
            // 既に同じIDのユーザーがいて保存に失敗
            return "redirect:/signup"
        }
        val loginUser = userLogin.login(userId, userPassWord)
        session.setAttribute("loginUser", loginUser)

        return "redirect:/"
    }
}
