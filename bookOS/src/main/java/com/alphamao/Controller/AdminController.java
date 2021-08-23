package com.alphamao.Controller;

import com.alphamao.Entity.Admin;
import com.alphamao.Entity.Book;
import com.alphamao.Entity.Student;
import com.alphamao.Service.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/admin")
public class AdminController {
    @Autowired
    AdminService admin;
    private static Admin adm;

    @GetMapping("/login")
    public String adminLogin(Admin admins){
        String status = admin.login(admins);
        if(status.equals("success"))
            adm=admins;
        return status;
    }

    @GetMapping("/allbook")
    public List<Map<String,Object>> adminAllBook(){
        return admin.allBook();
    }

    @PostMapping("/addbook")
    public List<Map<String,Object>> adminAddBook(@RequestBody Book book){
        if(admin.addBook(book))
            return admin.allBook();
        else
            return null;
    }

    @PutMapping("/updatebook")
    public List<Map<String,Object>> adminUpdateBook(@RequestBody Book book){
        if(admin.updateBook(book))
            return admin.allBook();
        else
            return null;
    }

    @DeleteMapping("/deletebook")
    public List<Map<String,Object>> adminDeleteBook(@RequestBody List<Book> bookList){
        if(admin.deleteBook(bookList))
            return admin.allBook();
        else
            return null;
    }

    @GetMapping("/getnotice")
    public List<Map<String,Object>> adminGetNotice(){
        return admin.getNotice();
    }

    @DeleteMapping("/dealnotice")
    public List<Map<String,Object>> adminDealNotice(@RequestBody List<Map<String,Object>> form){
        if(admin.dealNotice(form))
            return admin.getNotice();
        else
            return null;
    }
}
