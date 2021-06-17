package io.swagger.service;

import io.swagger.model.Account;
import io.swagger.model.AccountStatus;
import io.swagger.model.User;
import io.swagger.repo.UserRepo;
import io.swagger.security.JwtTokenProvider;
import lombok.extern.java.Log;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import javax.persistence.Transient;
import java.util.List;
import java.util.Locale;
import java.util.UUID;

@Log
@Service
public class UserService
{

    @Autowired
    private AuthenticationManager manager;

    @Autowired
    private JwtTokenProvider jwtTokenProvider;

    @Autowired
    private UserRepo userRepo;

    public String login(String email, String password)
    {
        try
        {
            manager.authenticate(new UsernamePasswordAuthenticationToken(email, password));
            return jwtTokenProvider.createToken(email, io.swagger.security.Role.valueOf(userRepo.findUserByEmail(email).getRole().toString().toUpperCase(Locale.ROOT)));
        } catch (AuthenticationException ae)
        {
            throw new ResponseStatusException(HttpStatus.UNPROCESSABLE_ENTITY, "Invalid credentials");
        }
    }

    public String register(User user)
    {
        if (userRepo.findUserByEmail(user.getEmail()) == null)
        {
            //noinspection SpringConfigurationProxyMethods
            user.setPassword(passwordEncoder().encode(user.getPassword()));

            if (user.getRole() != null)
                user.setRole(io.swagger.security.Role.ROLE_EMPLOYEE);

            log.info(user.toString());
            userRepo.save(user);
            String token = jwtTokenProvider.createToken(user.getEmail(), io.swagger.security.Role.valueOf(user.getRole().toString().toUpperCase(Locale.ROOT)));
            return token;
        } else
        {
            throw new ResponseStatusException(HttpStatus.UNPROCESSABLE_ENTITY, "Email address already in use.");
        }
    }

    @Bean
    public PasswordEncoder passwordEncoder()
    {
        return new BCryptPasswordEncoder(12);
    }

    public User getUserById(UUID id)
    {
        return userRepo.getOne(id);
    }

    public List<User> getAllUsers()
    {
        return userRepo.findAll();
    }

    public User getUserByIban(Account account)
    {
        return userRepo.findUserByAccountsContaining(account);
    }

    public User createUser(User user)
    {
        return userRepo.save(user);
    }

    // Sets account to inactive
    @Transient
    public User deleteUserById(UUID id)
    {
        User userToDelete = userRepo.getOne(id);
        userToDelete.setAccountStatus(AccountStatus.DISABLED);
        return userRepo.save(userToDelete);
    }

    @Transient
    public User updateUser(User user)
    {
        User userToUpdate = userRepo.getOne(user.getId());
        // userToUpdate.setStuff(user.getStuff)

        return userRepo.save(userToUpdate);
    }
}
