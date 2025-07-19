package com.ll.ch03;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
//new HomeController(자동으로 생성)
public class HomeController {
    @GetMapping("a") //외부에서 바로 호출 -> 액션 메소드 현재 2개
    @ResponseBody
    public String hello(
            String age,
            String id
    ){
//        System.out.println("hello!");, 내 콘솔에만 보임
        return "안녕하세요. %s번 사람의 나이는 %s이다.".formatted(id,age);
    }

//    @GetMapping("b") //브라우저에 의해서 호출이 가능
//    @ResponseBody //반환값을 응답으로 삼겠다는 의미
//    public String plus(
//            @RequestParam("a") String num1Str,
//            @RequestParam("b") String num2Str
//    ){
//        int num1 = Integer.parseInt(num1Str);
//        int num2 = Integer.parseInt(num2Str);
//
//        System.out.println("a = "+num1Str);
//        System.out.println("b = "+num2Str);
//
//        return "a + b = %d".formatted(num1+num2);
//    }

    @GetMapping("b") //액션
    @ResponseBody
    public String plus(
            @RequestParam("a") int num1,
            @RequestParam("b") int num2,
            @RequestParam(name="c", defaultValue = "0") int num3
    ){
        System.out.println("a = "+ num1);
        System.out.println("b = "+ num2);
        System.out.println("c = "+ num3);

        return "a + b + c = %d".formatted(num1+num2+num3);
    }

    @GetMapping("c") //액션
    @ResponseBody
    public String c( //Jakson이 알아서 String을 자바로 바꿔줌
            boolean married
    ){
        return married ? "결혼":"미혼";
    }

    @GetMapping("d") //액션
    @ResponseBody
    public String d(
            Boolean married
    ){
        if(married == null) return "정보를 입력해주세요.";
        return married ? "결혼":"미혼";
    }

    public static class Person{
        private String name;
        private int age;

        public Person(String name, int age) {
            this.name = name;
            this.age = age;
        }

        @Override
        public String toString() {
            return "Person{" +
                    "name='" + name + '\'' +
                    ", age=" + age +
                    '}';
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public int getAge() {
            return age;
        }

        public void setAge(int age) {
            this.age = age;
        }

    }

    @GetMapping("person1")
    @ResponseBody
    public String person(
            String name,
            int age
    ){
        Person person = new Person(name, age);

        return person.toString();
    }

    @GetMapping("person2")
    @ResponseBody
    public String person2(
          Person person //알아서 객체로 만들어줌
    ){
        return person.toString();
    }

    @GetMapping("e")
    @ResponseBody
    public int e(){ //알아서 string으로 바꾸고 보여줌
//        return 10;
        int age = 10;
        return age;
    }

    @GetMapping("f")
    @ResponseBody
    public boolean f(){ //알아서 string으로 바꾸고 보여줌
        return true;
    }

    @GetMapping("g")
    @ResponseBody
    public Person g(){
        Person person = new Person("Paul", 23);
        return person;
    }

    @GetMapping("h")
    @ResponseBody
    public int[] h(){
        int [] arr = new int[] {10,20,30};
        return arr;
    }

    @GetMapping("i")
    @ResponseBody
    public List<Integer> i(){
        List<Integer> arr = List.of(10,20,30);

        return arr;
    }

    @GetMapping("j")
    @ResponseBody
    public Map<String, Object> j(){
        Map<String, Object> person = new HashMap<>();
        person.put("age", 22);
        person.put("name", "juyeon");

        return person;
    }
}
