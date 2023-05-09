package br.com.estudos.register.service;

import br.com.estudos.register.domain.User;
import br.com.estudos.register.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.Period;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public User register(User user) {
        var age = Period.between(user.getDateOfBirth(), java.time.LocalDate.now()).getYears();
        if (age < 18 || age > 60) {
            throw new RuntimeException("Idade não permitida");
        }
        return userRepository.save(user);
    }

}
