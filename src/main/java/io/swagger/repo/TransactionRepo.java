package io.swagger.repo;

import io.swagger.model.Transaction;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface TransactionRepo extends CrudRepository<Transaction, UUID> {
  Transaction getTransactionByTransactionId(UUID id);

  Transaction getTransactionsByAccountFrom(String Iban);

  List<Transaction> getTransactionsByAccountFromOrderByTransactionDate(String IBAN);
}
