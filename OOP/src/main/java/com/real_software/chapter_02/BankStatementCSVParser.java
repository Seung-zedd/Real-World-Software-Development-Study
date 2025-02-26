package com.real_software.chapter_02;

import com.real_software.chapter_02.bank_transaction.BankTransaction;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

//* 파싱 로직을 추출해 한 클래스로 만듦
//* (Updated) 커플링을 줄이기 위해 인터페이스로 강제 구현
public class BankStatementCSVParser implements BankStatementParser{

    private static final DateTimeFormatter DATE_PATTERN = DateTimeFormatter.ofPattern("dd-MM-yyyy");

    @Override
    public BankTransaction parseFrom(final String line) {
        // 콤마로 구분
        final String[] columns = line.split(",");

        // 구분한 열들을 파싱해서 date, amount, description 변수에 저장
        final LocalDate date = LocalDate.parse(columns[0], DATE_PATTERN);
        final double amount = Double.parseDouble(columns[1]); // 금액 추출 및 double로 파싱
        final String description = columns[2];

        return new BankTransaction(date, amount, description);

    }

    @Override
    public List<BankTransaction> parseLinesFrom(List<String> lines) {
        final List<BankTransaction> bankTransactions = new ArrayList<>();
        for (String line : lines) {
            bankTransactions.add(parseFrom(line));
        }
        return bankTransactions;

    }
}
