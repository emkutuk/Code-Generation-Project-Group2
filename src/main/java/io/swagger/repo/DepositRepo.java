package io.swagger.repo;

import io.swagger.model.Deposit;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface DepositRepo extends JpaRepository<Deposit, UUID> {
  List<Deposit> findAllByAccountTo(String iban);
}
