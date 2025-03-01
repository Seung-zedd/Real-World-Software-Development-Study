package com.real_software.chapter_02;

import com.real_software.chapter_02.bank_transaction.BankTransaction;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.Month;
import java.util.ArrayList;
import java.util.List;

public class BankStatementAnalyzerSRP {

    private static final String RESOURCES = "src/main/resources/";

    public static void main(String[] args) throws IOException {

        final BankStatementCSVParser bankStatementCSVParser = new BankStatementCSVParser();

        // 파일 입력 및 행 읽기
        final String fileName = args[0];
        final Path path = Paths.get(RESOURCES + fileName);
        final List<String> lines = Files.readAllLines(path);

        // 파싱
        List<BankTransaction> bankTransactions = bankStatementCSVParser.parseLinesFrom(lines);

        // 화면으로 결과 전송
        System.out.println("The total for all transactions is " + calculateTotalAmount(bankTransactions));
        System.out.println("Transactions in January  " + selectInMonth(bankTransactions, Month.JANUARY));

    }

    // Banktransaction 객체를 읽어서 거래 목록을 처리
    //! 계산 메서드는 파싱이나 결과 전송과는 직접적인 관련이 없으므로 응집도가 떨어짐 => 별도의 클래스로 캡슐화
    private static double calculateTotalAmount(final List<BankTransaction> bankTransactions) {
        double total = 0d;
        for (final BankTransaction bankTransaction : bankTransactions) {
            total += bankTransaction.getAmount();
        }
        return total;
    }

    public static List<BankTransaction> selectInMonth(final List<BankTransaction> bankTransactions, final Month month) {
        final List<BankTransaction> bankTransactionsInMonth = new ArrayList<>();
        for (final BankTransaction bankTransaction : bankTransactions) {
            if (bankTransaction.getDate().getMonth() == month) {
                bankTransactionsInMonth.add(bankTransaction);
            }
        }
        return bankTransactionsInMonth;
    }
}

