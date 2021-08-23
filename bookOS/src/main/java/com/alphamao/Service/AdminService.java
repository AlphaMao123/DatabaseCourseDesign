package com.alphamao.Service;

import com.alphamao.Entity.Admin;
import com.alphamao.Entity.Book;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;

@Component
public class AdminService {
    @Autowired
    JdbcTemplate jdbcTemplate;

    public String login(Admin admin){
        try{
            String sql="SELECT * FROM admin WHERE name=?";
            Map<String,Object> stu=jdbcTemplate.queryForMap(sql,admin.getName());
            if(!stu.get("passward").equals(admin.getPassward()))
                return "passworderr";
        }catch (Exception e){
            System.out.println(e.toString());
            return "noninform";
        }
        return "success";
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

    public Boolean addBook(Book book){
        try {
            String sql="INSERT INTO book VALUES(?,?,?,?,?,?,?)";
            jdbcTemplate.update(sql,book.getName(),book.getAuthor(),book.getValue(),book.getEdition(),
                    book.getIsbn(),book.getPublisher(),book.getGrade());
            return true;
        }
        catch (Exception e){
            System.out.println(e.toString());
        }
        return false;
    }

    public Boolean updateBook(Book book){
        try {
            String sql="UPDATE book SET name=?,author=?,value=?,edition=?,isbn=?,publisher=?,grade=? WHERE isbn=?";
            jdbcTemplate.update(sql,book.getName(),book.getAuthor(),book.getValue(),book.getEdition(),
                    book.getIsbn(),book.getPublisher(),book.getGrade(),book.getIsbn());
            return true;
        }
        catch (Exception e){
            System.out.println(e.toString());
        }
        return false;
    }

    public Boolean deleteBook(List<Book> bookList){
        try {
            String sql="DELETE FROM book WHERE isbn=?";
            for(Book book:bookList){
                jdbcTemplate.update(sql,book.getIsbn());
            }
            return true;
        }
        catch (Exception e){
            System.out.println(e.toString());
        }
        return false;
    }

    public List<Map<String,Object>> getNotice(){
        try {
            String sql="SELECT user,type,text FROM inform";
            return jdbcTemplate.queryForList(sql);
        }catch (Exception e){
            System.out.println(e.toString());
        }
        return null;
    }

    public Boolean dealNotice(List<Map<String,Object>> form){
        try {
            String sql="DELETE FROM inform WHERE text=?";
            for(Map<String,Object> item:form){
                jdbcTemplate.update(sql,item.get("text"));
            }
            return true;
        }
        catch (Exception e){
            System.out.println(e.toString());
        }
        return false;
    }
}
