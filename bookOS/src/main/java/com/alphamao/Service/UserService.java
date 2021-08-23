package com.alphamao.Service;

import com.alphamao.Entity.Book;
import com.alphamao.Entity.Student;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;

@Component
public class UserService {
    @Autowired
    private JdbcTemplate jdbcTemplate;

    public Boolean register(Student student){
        try {
            String sql="INSERT INTO student VALUES(?,NULL,NULL,?,?,?)";
            jdbcTemplate.update(sql,student.getId(),student.getEmail(),student.getPassward(),student.getGrade());
            return true;
        }catch (Exception e){
            System.out.println(e.toString());
        }
        return false;
    }

    public String login(Student student){
        try{
            String sql="SELECT * FROM student WHERE id=?";
            Map<String,Object> stu=jdbcTemplate.queryForMap(sql,student.getId());
            if(!stu.get("passward").equals(student.getPassward()))
                return "passworderr";
        }catch (Exception e){
            System.out.println(e.toString());
            return "noninform";
        }
        return "success";
    }

    public List<Map<String,Object>> searchSub(Student student){
        try{
            String sql="SELECT book.* FROM subscribe,book WHERE subscribe.id=? AND subscribe.isbn=book.isbn";
            return jdbcTemplate.queryForList(sql,student.getId());
        }catch (Exception e){
            System.out.println(e.toString());
        }
        return null;
    }

    public List<Map<String,Object>> searchUnsub(Student student){
        try{
            String sql="SELECT DISTINCT book.* " +
                    "FROM book,student " +
                    "WHERE book.isbn NOT IN ( " +
                    "SELECT book.isbn " +
                    "FROM subscribe,student,book " +
                    "WHERE student.id=subscribe.id " +
                    "AND student.id=? " +
                    "AND subscribe.isbn=book.isbn) " +
                    "AND student.id=? " +
                    "AND student.grade=book.grade";
            return jdbcTemplate.queryForList(sql,student.getId(),student.getId());
        }catch (Exception e){
            System.out.println(e.toString());
        }
        return null;
    }

    public Boolean subscribe(List<Book> bookList,Student student){
        try{
            String sql="INSERT INTO subscribe VALUES(?,?)";
            for(Book book:bookList){
                jdbcTemplate.update(sql,student.getId(),book.getIsbn());
            }
            return true;
        }catch (Exception e){
            System.out.println(e.toString());
        }
        return false;
    }

    public List<Map<String,Object>> allBook(){
        try {
            String sql="SELECT * FROM book";
            return jdbcTemplate.queryForList(sql);
        }catch (Exception e){
            System.out.println(e.toString());
        }
        return null;
    }

    public Boolean desubscribe(List<Book> bookList,Student student){
        try{
            String sql="DELETE FROM subscribe WHERE isbn=? AND id=?";
            for(Book book:bookList){
                jdbcTemplate.update(sql,book.getIsbn(),student.getId());
            }
            return true;
        }catch (Exception e){
            System.out.println(e.toString());
        }
        return false;
    }

    public Map<String,Object> getinform(Student student){
        try {
            String sql="SELECT id,name,sex,email,grade FROM student WHERE id=?";
            return jdbcTemplate.queryForMap(sql,student.getId());
        }catch (Exception e){
            System.out.println(e.toString());
        }
        return null;
    }

    public Boolean updateinform(Student form,Student student){
        try{
            String sql="UPDATE student SET name=?,sex=?,grade=?,email=? WHERE id=?";
            jdbcTemplate.update(sql,form.getName(),form.getSex(),form.getGrade(),form.getEmail(),student.getId());
            return true;
        }catch (Exception e){
            System.out.println(e.toString());
        }
        return false;
    }

    public String updatecypher(Student passward,Student student){
        try{
            String sql="UPDATE student SET passward=? WHERE id=?";
            jdbcTemplate.update(sql,passward.getPassward(),student.getId());
            return "success";
        }catch (Exception e){
            System.out.println(e.toString());
        }
        return "failed";
    }

    public String sendinform(Map<String,Object> form,Student student){
        try{
            String sql="INSERT INTO inform VALUES(NULL,?,?,?)";
            jdbcTemplate.update(sql,student.getId(),form.get("type"),form.get("text"));
            return "success";
        }catch (Exception e){
            System.out.println(e.toString());
        }
        return "error";
    }
}
