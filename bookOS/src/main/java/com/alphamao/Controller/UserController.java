package com.alphamao.Controller;

import com.alphamao.Entity.Book;
import com.alphamao.Entity.Student;
import com.alphamao.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/")
public class UserController {
    @Autowired
    UserService user;
    private static Student stu;

    @PostMapping("/register")
    public Boolean userRegister(@RequestBody Student student){
        //System.out.println(student.toString());
        return user.register(student);
    }

    @GetMapping("/login")
    public String userLogin(Student student){
        //System.out.println(student.toString());
        String status = user.login(student);
        if(status.equals("success"))
            stu=student;
        return status;
    }

    @GetMapping("/searchsub")
    public List<Map<String,Object>> userSearchsub(){
        return user.searchSub(stu);
    }

    @GetMapping("/searchunsub")
    public List<Map<String,Object>> userSearchunsub(){
        return user.searchUnsub(stu);
    }

    @PostMapping("/subscribe")
    public List<Map<String,Object>> userSubscribe(@RequestBody List<Book> bookList){
        if(user.subscribe(bookList,stu))
            return user.searchUnsub(stu);
        else
            return null;
    }

    @GetMapping("/allbook")
    public List<Map<String,Object>> userAllBook(){
        return user.allBook();
    }

    @DeleteMapping("/desubscribe")
    public List<Map<String,Object>> userDesubscribe(@RequestBody List<Book> bookList){
        if(user.desubscribe(bookList,stu))
            return user.searchSub(stu);
        else
            return null;
    }

    @GetMapping("/getinform")
    public Map<String,Object> userGetinform(){
        return user.getinform(stu);
    }

    @PutMapping("/updateinform")
    public Map<String,Object> userUpdateinform(@RequestBody Student form){
        if(user.updateinform(form,stu))
            return user.getinform(stu);
        else
            return null;
    }

    @PutMapping("/updatecypher")
    public String userUpdatecypher(@RequestBody Student passward){//可以用其他方式实现
        return user.updatecypher(passward,stu);
    }

    @PostMapping("/sendinform")
    public String userSendinform(@RequestBody Map<String,Object> form){
        return user.sendinform(form,stu);
    }

}
