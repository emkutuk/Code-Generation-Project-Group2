package io.swagger.service;

import io.swagger.repo.UserRepo;
import org.springframework.stereotype.Service;

@Service
public class UserService
{
  private final UserRepo userRepo;

  public UserService(UserRepo userRepo) {
    this.userRepo = userRepo;
  }
}
