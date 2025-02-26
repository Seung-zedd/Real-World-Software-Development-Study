package com.real_software.chapter_02.bank_transaction;

//* 논리 응집 예제
//! This class isn't recommended due to SRP violation
public class BankTransactionParser {

    public BankTransaction parseFromCSV(final String line) {
        // 아직 구현되지 않아서 예외를 던짐
        throw new UnsupportedOperationException();
    }

    public BankTransaction parseFromJSON(final String line) {
        throw new UnsupportedOperationException();
    }

    public BankTransaction parseFromXML(final String line) {
        throw new UnsupportedOperationException();
    }
}
