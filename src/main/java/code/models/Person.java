package code.models;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

public class Person {

    private int personId;
    @NotEmpty(message = "Name should not be empty")
    @Size(min = 2, max = 100, message = "Name should be between 2 and 30 characters")
    private String SNP;
    @Min(value = 0, message = "Year of birth should be greater than 0")
    private int yearOfBirth;

    public int getPersonId() {
        return personId;
    }

    public void setPersonId(int personId) {
        this.personId = personId;
    }

    public String getSNP() {
        return SNP;
    }

    public void setSNP(String SNP) {
        this.SNP = SNP;
    }

    public int getYearOfBirth() {
        return yearOfBirth;
    }

    public void setYearOfBirth(int yearOfBirth) {
        this.yearOfBirth = yearOfBirth;
    }
    public Person(){

    }

    public Person(String SNP, int yearOfBirth) {
        this.SNP = SNP;
        this.yearOfBirth = yearOfBirth;
    }
}
