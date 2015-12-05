package model.persistance;

import model.value.Month;
import model.value.Transaction;

import java.util.List;

public interface Repository {
    List<Transaction> getTransactionsForMonth(Month month);
}
