package io.swagger.repo;

import io.swagger.model.RegularTransaction;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface RegularTransactionRepo extends JpaRepository<RegularTransaction, UUID>{
  List<RegularTransaction> findAllByAccountFrom(String iban);
}
