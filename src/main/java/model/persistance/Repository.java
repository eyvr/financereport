package model.persistance;

import model.value.Month;
import model.value.Transaction;
import model.value.TransactionType;

import java.sql.SQLException;
import java.util.List;

public interface Repository {
    List<Transaction> getTransactionsForMonth(Month month) throws SQLException;

    List<TransactionType> getTypes() throws SQLException;
}
