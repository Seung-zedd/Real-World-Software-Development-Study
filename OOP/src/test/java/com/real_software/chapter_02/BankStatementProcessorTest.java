package com.real_software.chapter_02;

import com.real_software.chapter_02.bank_transaction.BankTransaction;
import org.junit.jupiter.api.Test;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.Month;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

class BankStatementProcessorTest {

    private final BankStatementParser statementParser = new BankStatementCSVParser();

    @Test
    void shouldSummarizeExpensesByDescription() throws Exception {
        // given
        final String RESOURCES = "src/main/resources/";
        final Path path = Paths.get(RESOURCES + "bank-data-simple.csv");
        final List<String> lines = Files.readAllLines(path);

        // when
        List<BankTransaction> bankTransactions = statementParser.parseLinesFrom(lines);
        BankStatementProcessor bankStatementProcessor = new BankStatementProcessor(bankTransactions);
        Map<String, Double> expensesByCategory = bankStatementProcessor.summarizeExpensesByDescription();

        // then
        assertEquals(2950, expensesByCategory.get("Tesco"));
        assertEquals(-30, expensesByCategory.get("Cinema"));
    }

    @Test
    void shouldSummarizeExpensesByMonth() throws Exception {
        // given
        final String RESOURCES = "src/main/resources/";
        final Path path = Paths.get(RESOURCES + "bank-data-simple.csv");
        final List<String> lines = Files.readAllLines(path);

        // when
        List<BankTransaction> bankTransactions = statementParser.parseLinesFrom(lines);
        BankStatementProcessor bankStatementProcessor = new BankStatementProcessor(bankTransactions);
        Map<Month, Double> expensesByMonth = bankStatementProcessor.summarizeExpensesByMonth();

        // then
        assertEquals(-150, expensesByMonth.get(Month.JANUARY));
        assertEquals(6970, expensesByMonth.get(Month.FEBRUARY));
    }


    @Test
    void shouldReturnTotalResult() throws Exception {
        // given
        final String RESOURCES = "src/main/resources/";
        final Path path = Paths.get(RESOURCES + "bank-data-simple.csv");
        final List<String> lines = Files.readAllLines(path);

        // when
        List<BankTransaction> bankTransactions = statementParser.parseLinesFrom(lines);
        BankStatementProcessor bankStatementProcessor = new BankStatementProcessor(bankTransactions);
        double total = bankStatementProcessor.calculateTotalAmount();

        // then
        assertEquals(6820, total);
    }


    @Test
    void shouldReturnMaxResult() throws Exception {
        // given
        final String RESOURCES = "src/main/resources/";
        final Path path = Paths.get(RESOURCES + "bank-data-simple.csv");
        final List<String> lines = Files.readAllLines(path);

        // when
        List<BankTransaction> bankTransactions = statementParser.parseLinesFrom(lines);
        BankStatementProcessor bankStatementProcessor = new BankStatementProcessor(bankTransactions);
        double max = bankStatementProcessor.calculateMaxInMonth(Month.FEBRUARY);

        // then
        assertEquals(6000, max);
    }

    @Test
    void shouldReturnMinResult() throws Exception {
        // given
        final String RESOURCES = "src/main/resources/";
        final Path path = Paths.get(RESOURCES + "bank-data-simple.csv");
        final List<String> lines = Files.readAllLines(path);

        // when
        List<BankTransaction> bankTransactions = statementParser.parseLinesFrom(lines);
        BankStatementProcessor bankStatementProcessor = new BankStatementProcessor(bankTransactions);
        double min = bankStatementProcessor.calculateMinInMonth(Month.FEBRUARY);

        // then
        assertEquals(-4000, min);

    }

}