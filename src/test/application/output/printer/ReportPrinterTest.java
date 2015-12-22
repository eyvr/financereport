package application.output.printer;

import model.persistence.Repository;
import model.report.DefaultMonthlyReport;
import model.report.MonthlyReport;
import model.value.TransactionType;
import org.junit.Before;
import org.junit.Test;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class ReportPrinterTest {

    private ReportPrinter printer;
    private List<MonthlyReport> reports;
    private FakeOutput output;

    @Before
    public void setUp() throws Exception {
        List<TransactionType> types = new ArrayList<>();
        TransactionType entertainment = new TransactionType("entertainment");
        types.add(entertainment);
        TransactionType food = new TransactionType("food");
        types.add(food);

        Repository repositoryMock = mock(Repository.class);
        when(repositoryMock.getTypes()).thenReturn(types);

        List<MonthlyReport> reports = new ArrayList<>();

        DefaultMonthlyReport report1 = getMonthlyReportMock(13f, "2015-02");
        when(report1.getTotal(food)).thenReturn(2f);
        when(report1.getTotal(entertainment)).thenReturn(11f);

        DefaultMonthlyReport report2 = getMonthlyReportMock(4f, "2015-03");
        when(report2.getTotal(food)).thenReturn(3f);
        when(report2.getTotal(entertainment)).thenReturn(1f);

        reports.add(report1);
        reports.add(report2);

        this.reports = reports;
        this.output = new FakeOutput();
        this.printer = new ReportPrinter(this.output, repositoryMock);
    }

    @Test
    public void testPrint() throws SQLException {
        this.printer.print(this.reports);
        assertEquals(
                        "               2015-02  2015-03\n" +
                        "entertainment    11.00     1.00\n" +
                        "         food     2.00     3.00\n" +
                        "        TOTAL    13.00     4.00\n",
                this.output.getOutput()
        );
    }

    private DefaultMonthlyReport getMonthlyReportMock(float total, String monthLabel) {
        DefaultMonthlyReport report1 = mock(DefaultMonthlyReport.class);
        when(report1.getTotal()).thenReturn(total);
        when(report1.getMonthLabel()).thenReturn(monthLabel);
        return report1;
    }
}