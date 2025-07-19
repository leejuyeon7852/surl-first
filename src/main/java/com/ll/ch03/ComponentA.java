package com.ll.ch03;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ComponentA {
    @Autowired //Component 있으면 사용 가능
    private ComponentB componentB; //Autowired 자동으로 객체 연결

    @Autowired
    private ComponentC componentC; //Configuration 있기 때문에 자동으로 읽힘

    @Autowired
    private ComponentD componentD;

    @Autowired
    private ComponentE componentE;

    public String action() {
       return "ComponentA action / "+ componentB.getAction();
    }
}

//RequiredArgsContructor lombok + 변수에 final 하면 알아서 객체 생성
//@Component
//@RequiredArgsConstructor
//public class ComponentA {
//    private final ComponentB componentB; //Autowired 자동으로 객체 연결
//
//    @Autowired
//    private ComponentC componentC; //Configuration 있기 때문에 자동으로 읽힘
//
//    @Autowired
//    private ComponentD componentD;
//
//    @Autowired
//    private ComponentE componentE;
//
//    public String action() {
//        return "ComponentA action / "+ componentB.getAction();
//    }
//}
