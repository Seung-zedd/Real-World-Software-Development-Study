package com.real_software.chapter_02;

import com.real_software.chapter_02.bank_transaction.BankTransaction;
import org.junit.jupiter.api.Test;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.time.Month;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class BankStatementCSVParserTest {

    private final BankStatementParser statementParser = new BankStatementCSVParser();

    @Test
    void shouldParseOneCorrectLine() throws Exception {
        // given
        final String line = "30-01-2017,-50,Tesco";

        // when
        BankTransaction result = statementParser.parseFrom(line);

        // then
        final BankTransaction expected = new BankTransaction(LocalDate.of(2017, Month.JANUARY, 30), -50, "Tesco");
        final double tolerance = 0.0d;

        assertEquals(expected.getDate(), result.getDate());
        assertEquals(expected.getAmount(), result.getAmount(), tolerance);
        assertEquals(expected.getDescription(), result.getDescription());
    }

    @Test
    void shouldParseCSVFiles() throws Exception {
        // given
        final String RESOURCES = "src/main/resources/";
        final Path path = Paths.get(RESOURCES + "bank-data-simple.csv");
        final List<String> lines = Files.readAllLines(path);

        // when
        List<BankTransaction> bankTransactions = statementParser.parseLinesFrom(lines);

        // then
        List<BankTransaction> expectedTransactions = List.of(
            new BankTransaction(LocalDate.of(2017, Month.JANUARY, 30), -100, "Deliveroo"),
            new BankTransaction(LocalDate.of(2017, Month.JANUARY, 30), -50, "Tesco"),
            new BankTransaction(LocalDate.of(2017, Month.FEBRUARY, 1), 6000, "Salary"),
            new BankTransaction(LocalDate.of(2017, Month.FEBRUARY, 2), 2000, "Royalties"),
            new BankTransaction(LocalDate.of(2017, Month.FEBRUARY, 2), -4000, "Rent"),
            new BankTransaction(LocalDate.of(2017, Month.FEBRUARY, 3), 3000, "Tesco"),
            new BankTransaction(LocalDate.of(2017, Month.FEBRUARY, 5), -30, "Cinema")
        );
        final double tolerance = 0.0d;

        for (int i = 0; i < expectedTransactions.size(); i++) {
            BankTransaction expected = expectedTransactions.get(i);
            BankTransaction actual = bankTransactions.get(i);
            assertEquals(expected.getDate(), actual.getDate());
            assertEquals(expected.getAmount(), actual.getAmount(), tolerance);
            assertEquals(expected.getDescription(), actual.getDescription());
        }

    }

    

}