package io.swagger.repo;

import io.swagger.model.Transaction;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface TransactionRepo extends CrudRepository<Transaction, UUID>
{
  Transaction getTransactionByTransactionId(UUID id);

  Transaction getTransactionsByAccountFrom(String Iban);

//  List<Transaction>getTransactionsBetweenDates(LocalDateTime earlyDate, LocalDateTime lateDate);
}
