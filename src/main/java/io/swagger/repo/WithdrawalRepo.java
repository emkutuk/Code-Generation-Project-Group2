package io.swagger.repo;

import io.swagger.model.Withdrawal;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface WithdrawalRepo extends JpaRepository<Withdrawal, UUID> {
  List<Withdrawal> findAllByAccountFrom(String iban);
}
