package com.example.articleproject.ioc;

import org.springframework.stereotype.Component;

@Component
public class Chef {
    //셰프는 식재료 공장을 알고있다.
    private IngredientFactory ingredientFactory;
    //셰프가 식재료 공장과 협업하기 위한 DI
    //동작에 필요한 객체를 외부에서 받아오기
    public Chef(IngredientFactory ingredientFactory) {
        this.ingredientFactory=ingredientFactory;
    }

    public String cook(String menu) {
        // 재료 준지
        /*Pork pork=new Pork("한돈 등심");
        Beef beef=new Beef("한우 꽃등심");*/

        //공장에서 알아서 메뉴를 가져다준다.
        Ingredient ingredient=ingredientFactory.get(menu);

        // 요리 반환
        //return pork.getName()+"으로 만든 "+menu;
        //return beef.getName()+"으로 만든 "+menu;
        return ingredient.getName()+"으로 만든 "+menu;
    }
}
