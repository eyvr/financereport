package application.persistance;

import model.value.Month;
import model.value.Transaction;
import model.value.TransactionType;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class Repository implements model.persistance.Repository {
    private final Connection connection;

    public Repository(String host, int port, String username, String password, String dbname) throws SQLException {
        connection = DriverManager
                .getConnection(
                        String.format("jdbc:mysql://%s:%d/%s", host, port, dbname),
                        username,
                        password
                );
    }

    @Override
    public List<Transaction> getTransactionsForMonth(Month month) throws SQLException {
        List<Transaction> list = new ArrayList<>();

        PreparedStatement stmt = this.connection.prepareStatement(
                "select title, date, type, amount from transactions where date >= ? and date < ?"
        );
        stmt.setString(1, month.getMonthStart());
        stmt.setString(2, month.getNextMonth().getMonthStart());
        ResultSet rs = stmt.executeQuery();

        while (rs.next()) {
            list.add(new Transaction(
                    rs.getString(1),
                    rs.getDate(2),
                    new TransactionType(rs.getString(3)),
                    rs.getFloat(4)
            ));
        }
        return list;
    }

    @Override
    public List<TransactionType> getTypes() throws SQLException {
        List<TransactionType> list = new ArrayList<>();

        PreparedStatement stmt = this.connection.prepareStatement(
                "select type from transactions group by type"
        );
        ResultSet rs = stmt.executeQuery();

        while (rs.next()) {
            list.add(new TransactionType(rs.getString(1)));
        }
        return list;
    }
}
