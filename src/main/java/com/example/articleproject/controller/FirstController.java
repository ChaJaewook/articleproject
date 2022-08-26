package com.example.articleproject.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class FirstController {
        //~~Controller로 이름을 짓자.
        // mustache 파일과 연결

    //접속할 URL을 적어준다.
    //hi 입력시 greetings를 반환해준다.
    @GetMapping("/hi")
    public String niceToMeetYou(Model model)
    {
        //username에 값을 넣어줄 Model선언
        //파일 이름만 리턴값으로 적어주면 templates안의 greetings.mustache파일을 전송해준다.

        // greetings 파일의 변수에 값 추가
        model.addAttribute("username","Jaewook");
        return "greetings";
    }

    @GetMapping("/bye")
    public String seeYouNext(Model model)
    {
        model.addAttribute("nickname","CJW");
        return "goodbye";
    }

}
