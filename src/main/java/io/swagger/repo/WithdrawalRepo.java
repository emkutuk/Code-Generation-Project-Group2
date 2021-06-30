package io.swagger.repo;

import io.swagger.model.Withdrawal;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.UUID;

public interface WithdrawalRepo extends JpaRepository<Withdrawal, UUID> {
  List<Withdrawal> findAllByAccountFrom(String iban);

  @Query(value = "SELECT SUM(w.amount) FROM Withdrawal as w WHERE w.transactionDate = current_date AND w.accountFrom = :iban")
  double findWithdrawalByAccountFrom(String iban);
}
