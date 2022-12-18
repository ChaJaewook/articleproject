package com.example.articleproject.api;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController // RestAPI용 컨트롤러!
//JSON을 반환!
public class FirstApiController {
    @GetMapping("/api/hello")
    public String hello()
    {
        return "hello";
    }

    /*

    일반 Controller와 Rest Controller와의 차이
    일반 Controller는 페이지가 반환됐다.
    반환하는 타입이 다르다.

     */
}
