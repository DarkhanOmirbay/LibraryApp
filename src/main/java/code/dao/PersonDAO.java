package code.dao;

import code.models.Book;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;
import code.models.Person;

import java.util.List;
import java.util.Optional;

@Component
public class PersonDAO {
    private final JdbcTemplate jdbcTemplate;
    @Autowired
    public PersonDAO(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }
    public List<Person> index(){
    return jdbcTemplate.query("SELECT * FROM Person",new BeanPropertyRowMapper<>(Person.class));
    }
    public Person show(int personId){
        return jdbcTemplate.query("SELECT * FROM Person WHERE personId=?",new Object[]{personId},
                new BeanPropertyRowMapper<>(Person.class)).stream().findAny().orElse(null);
    }

    public void save(Person person) {
        jdbcTemplate.update("INSERT INTO Person(SNP,yearOFBirth) VALUES(?, ?)", person.getSNP(), person.getYearOfBirth());
    }


    public void update(int personId, Person updatedPerson) {
        jdbcTemplate.update("UPDATE Person SET SNP=?, yearOfBirth=? WHERE personId=?", updatedPerson.getSNP(),
                updatedPerson.getYearOfBirth(),personId);
    }
    public void delete(int personId) {
        jdbcTemplate.update("DELETE FROM Person WHERE personId=?", personId);
    }
    public Optional<Person> getPersonBySNP(String snp){
        return jdbcTemplate.query("SELECT * FROM Person Where snp=?",new Object[]{snp},
                new BeanPropertyRowMapper<>(Person.class)).stream().findAny();
    }
    public List<Book> getBooksByPersonId(int personId){
        return jdbcTemplate.query("SELECT * From Book Where personId=?",new Object[]{personId},
                new BeanPropertyRowMapper<>(Book.class));
    }

}
