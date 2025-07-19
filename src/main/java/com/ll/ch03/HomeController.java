package com.ll.ch03;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.time.LocalDateTime;
import java.util.ArrayList;
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

    @Getter
    @Setter
    @ToString
    @AllArgsConstructor //모든 필드 생성자
    public static class Person{
        private String name;
        private int age;
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

    @AllArgsConstructor
    @Getter
    @Builder
    @ToString
    @EqualsAndHashCode(onlyExplicitlyIncluded = true) //post 객체끼리 필드가 똑같은지 확인..?
    public static class Post{
        @ToString.Exclude
        @JsonIgnore
        @EqualsAndHashCode.Include //id만 비교! (둘이 같은지)
        private Long id; //id 제외하고 toString
        private LocalDateTime createDate;
        private LocalDateTime modifyDate;
        @Builder.Default
        private String subject = "제목입니다.";
        private String body;
    }

    @GetMapping("/posts")
    @ResponseBody
    public List<Post> getPosts(){
        List<Post> posts = new ArrayList<>() {{
            add(new Post(1L,LocalDateTime.now(), LocalDateTime.now(), "제목 1", "내용 1"));
            add(new Post(2L,LocalDateTime.now(), LocalDateTime.now(), "제목 2", "내용 2"));
            add(new Post(3L,LocalDateTime.now(), LocalDateTime.now(), "제목 3", "내용 3"));
            add(new Post(4L,LocalDateTime.now(), LocalDateTime.now(), "제목 4", "내용 4"));
            add(new Post(5L,LocalDateTime.now(), LocalDateTime.now(), "제목 5", "내용 5"));
        }}; //Long이면 L 붙여야함

        return posts;
    }

    @GetMapping("/posts2")
    @ResponseBody
    public List<Post> getPosts2(){
        List<Post> posts = new ArrayList<>() {{
            add( //builder는 순서를 바꿔도 됨
                    Post
                            .builder()
                            .id(1L)
                            .body("내용 1")
                            .subject("제목 1")
                            .createDate(LocalDateTime.now())
                            .modifyDate(LocalDateTime.now())
                            .build()
            );
            add( //builder는 순서를 바꿔도 됨
                    Post
                            .builder()
                            .id(2L)
                            .body("내용 2")
                            .subject("제목 2")
                            .createDate(LocalDateTime.now())
                            .modifyDate(LocalDateTime.now())
                            .build()
            );
            add( //builder는 순서를 바꿔도 됨
                    Post
                            .builder()
                            .id(3L)
                            .body("내용 3")
                            .createDate(LocalDateTime.now())
                            .modifyDate(LocalDateTime.now())
                            .build()
            );
        }};

        return posts;
    }

    @GetMapping("/posts/1")
    @ResponseBody
    public Post getPost(){

        Post post = Post
                .builder()
                .id(1L)
                .body("내용 1")
                .subject("제목 1")
                .createDate(LocalDateTime.now())
                .modifyDate(LocalDateTime.now())
                .build();

        System.out.println(post);
        return post;
    }

    @SneakyThrows
    @GetMapping("/posts/2")
    @ResponseBody
    public Post getPost2(){

        Post post = Post
                .builder()
                .id(1L)
                .body("내용 2")
                .subject("제목 2")
                .createDate(LocalDateTime.now())
                .modifyDate(LocalDateTime.now())
                .build();

        Thread.sleep(5000);

        System.out.println(post);
        return post;
    }
}
