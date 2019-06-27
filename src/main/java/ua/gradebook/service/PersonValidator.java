package ua.gradebook.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;
import ua.gradebook.model.beans.Person;

@Service
public class PersonValidator implements Validator {
    private final AppServicePerson<Person> personService;

    @Autowired
    public PersonValidator(AppServicePerson<Person> personService) {
        this.personService = personService;
    }

    @Override
    public boolean supports(Class<?> aClass) {
        return Person.class.equals(aClass);
    }

    @Override
    public void validate(Object o, Errors errors) {
        Person person = (Person) o;
        if (personService.findByEmail(person.getEmail())!=null) {
            errors.rejectValue("email", "", "This email already exists!");
        }
/*        if (personService.findByPhone(person.getPhone())!=null) {
            errors.rejectValue("phone", "", "This phone already exists!");
        }
        if (personService.findByLogin(person.getLogin())!=null) {
            errors.rejectValue("login", "", "This login already exists!");
        }*/

    }
}
