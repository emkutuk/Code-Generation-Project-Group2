package io.swagger.service;

import io.swagger.model.AccountStatus;
import io.swagger.model.User;
import io.swagger.repo.UserRepo;
import org.springframework.stereotype.Service;

import javax.persistence.Transient;
import java.util.List;
import java.util.UUID;

@Service
public class UserService {
  private final UserRepo userRepo;

  public UserService(UserRepo userRepo) {
    this.userRepo = userRepo;
  }

  public User getUserById(UUID id) {
    return userRepo.getOne(id);
  }

  public List<User> getAllUsers() {
    return userRepo.findAll();
  }

  public User getUserByIban(String iban) {
    return userRepo.findUserByAccounts_iban(iban);
  }

  public User createUser(User user) {
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
  public User updateUser(User user) {
    User userToUpdate = userRepo.getOne(user.getId());
    // userToUpdate.setStuff(user.getStuff)

    return userRepo.save(userToUpdate);
  }
}
