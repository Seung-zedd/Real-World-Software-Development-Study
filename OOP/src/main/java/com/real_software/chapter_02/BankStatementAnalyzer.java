package com.real_software.chapter_02;

import com.real_software.chapter_02.bank_transaction.BankTransaction;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.Month;
import java.util.List;

public class BankStatementAnalyzer {
    //fields: 파일의 경로와 파서
    private static final String RESOURCES = "src/main/resources/";

    protected BankStatementAnalyzer() {
    }

    public static void analyze(final String fileName, final BankStatementParser bankStatementParser) throws IOException {
        // 파일 입력 및 행 읽기
        final Path path = Paths.get(RESOURCES + fileName);
        final List<String> lines = Files.readAllLines(path);

        // 파서로 읽은 파일들의 행의 결과를 추출
        final List<BankTransaction> bankTransactions = bankStatementParser.parseLinesFrom(lines);
        // 입출력 목록을 인자로 넣어서 프로세서 객체 준비
        final BankStatementProcessor bankStatementProcessor = new BankStatementProcessor(bankTransactions);

        //* 메서드 호출 부분을 람다식으로 구현
        final List<BankTransaction> transactions = bankStatementProcessor.findTransactions(bankTransaction -> bankTransaction.getDate().getMonth() == Month.FEBRUARY &&
                bankTransaction.getAmount() > 1000);


        collectSummary(bankStatementProcessor);

    }

    private static void collectSummary(final BankStatementProcessor bankStatementProcessor) {
        System.out.println("The total for all transactions is " + bankStatementProcessor.calculateTotalAmount());
        System.out.println("The total for all transactions in January is " + bankStatementProcessor.calculateTotalInMonth(Month.JANUARY));
        System.out.println("The total for all transactions in February is " + bankStatementProcessor.calculateTotalInMonth(Month.FEBRUARY));
        System.out.println("The total salary received is " + bankStatementProcessor.calculateTotalForCategory("salary"));
    }
}
