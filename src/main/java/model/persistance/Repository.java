package model.persistance;

import model.Month;
import model.Transaction;

import java.util.List;

public interface Repository {
    List<Transaction> getTransactionsForMonth(Month month);
}
