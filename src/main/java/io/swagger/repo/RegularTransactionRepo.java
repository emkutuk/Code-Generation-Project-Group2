package io.swagger.repo;

import io.swagger.model.RegularTransaction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.UUID;

public interface RegularTransactionRepo extends JpaRepository<RegularTransaction, UUID>{
  List<RegularTransaction> findAllByAccountFrom(String iban);

  @Query(value = "SELECT SUM(t.amount) FROM RegularTransaction as t WHERE t.transactionDate = current_date AND t.accountFrom = :iban")
  double findRegularTransactionByAccountFrom(String iban);
}
