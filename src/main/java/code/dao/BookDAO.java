package code.dao;

import code.models.Book;
import code.models.Person;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component

public class BookDAO {
    private PersonDAO personDAO;

    private final JdbcTemplate jdbcTemplate;
@Autowired
    public BookDAO(JdbcTemplate jdbcTemplate,PersonDAO personDAO) {
        this.jdbcTemplate = jdbcTemplate;
        this.personDAO=personDAO;
    }
public List<Book> index(){
    return jdbcTemplate.query("SELECT * FROM Book",new BeanPropertyRowMapper<>(Book.class));
}
    public Book show(int bookId){
        return jdbcTemplate.query("SELECT * FROM Book WHERE bookId=?",new Object[]{bookId},
                new BeanPropertyRowMapper<>(Book.class)).stream().findAny().orElse(null);
    }

    public void save(Book book) {
        jdbcTemplate.update("INSERT INTO Book(title,author,year) VALUES(?,?,?)", book.getTitle(), book.getAuthor(),book.getYear());
    }


    public void update(int bookId, Book updatedBook) {
        jdbcTemplate.update("UPDATE Book SET title=?, author=?,year=? WHERE bookId=?", updatedBook.getTitle(),
                updatedBook.getAuthor(),updatedBook.getYear(),bookId);
    }
    public void delete(int bookId) {
        jdbcTemplate.update("DELETE FROM Book WHERE bookId=?", bookId);
    }

 public Optional<Person> getBookOwner(int bookId){
    return jdbcTemplate.query("SELECT Person.* From Book JOIN Person ON Book.personId=Person.personId"+" Where Book.bookId=?",new Object[]{bookId},
            new BeanPropertyRowMapper<>(Person.class)).stream().findAny();

 }
 public void release(int bookId){
    jdbcTemplate.update("UPDATE Book SET personId=NULL Where bookId=?",bookId);
 }
    public void assign(int bookId,Person selectedPerson){
        jdbcTemplate.update("UPDATE Book SET personId=? Where bookId=?",selectedPerson.getPersonId(),bookId);
    }

}
