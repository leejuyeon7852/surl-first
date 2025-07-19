package com.ll.ch03;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/home2")
public class Home2Controller {
    @Autowired
    private ComponentA componentA;

//    public Home2Controller(){
//        componentA = new ComponentA();
//    }-> Autowired를 하면 componentA가 알아서 객체 생성

    @GetMapping("/action1")
    @ResponseBody
    public String action1(){
        return componentA.action(); //Home2Controller는 conponentA에 대해 의존성이 있다?, 어떤 걸 하려면 componentA가 필요
    }
}
//Home2Controller
