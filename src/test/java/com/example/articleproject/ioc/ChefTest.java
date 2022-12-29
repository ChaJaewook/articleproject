package com.example.articleproject.ioc;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest

class ChefTest {
    //IngredientFactory와 Chef를 IoC에 등록해 가져오기
    @Autowired
    IngredientFactory ingredientFactory;
    @Autowired
    Chef chef;

    @Test
    void 돈가스_요리하기()
    {
        //IngredientFactory ingredientFactory=new IngredientFactory();
        // 준비
        //Chef chef=new Chef(ingredientFactory); //의존성 주입
        String menu="돈가스";

        // 수행
        String food=chef.cook(menu);

        // 예상
        String expected="한돈 등심으로 만든 돈가스";

        // 검증
        assertEquals(expected,food);
        System.out.println(food);
    }

    @Test
    void 스테이크_요리하기()
    {
        // 준비
        //IngredientFactory ingredientFactory=new IngredientFactory();
        //Chef chef=new Chef(ingredientFactory);
        //Chef chef=new Chef();
        String menu="스테이크";

        // 수행
        String food=chef.cook(menu);

        // 예상
        String expected="한우 꽃등심으로 만든 스테이크";

        // 검증
        assertEquals(expected,food);
        System.out.println(food);
    }

    // 돈가스 스테이크 각각 바꿔줘야하나?
    // Chef를 바꾸는건 너무 비효율적이다.
    // Dependency Injection을 통해 개선한다.

    @Test
    void 크리스피_치킨_요리하기()
    {
        // 준비
        //IngredientFactory ingredientFactory=new IngredientFactory();
        //Chef chef=new Chef(ingredientFactory);
        //Chef chef=new Chef();
        String menu="크리스피 치킨";

        // 수행
        String food=chef.cook(menu);

        // 예상
        String expected="국내산 10호 닭으로 만든 크리스피 치킨";

        // 검증
        assertEquals(expected,food);
        System.out.println(food);
    }

}