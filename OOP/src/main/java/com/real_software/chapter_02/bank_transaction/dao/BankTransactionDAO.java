package com.real_software.chapter_02.bank_transaction.dao;

import com.real_software.chapter_02.bank_transaction.BankTransaction;

import java.time.LocalDate;

public class BankTransactionDAO {

    public BankTransaction create(final LocalDate date, final double amount, final String description) {
        // 아직 구현되지 않아서 예외를 던짐
        throw new UnsupportedOperationException();
    }

    public BankTransaction read(final long id) {
        throw new UnsupportedOperationException();
    }

    public BankTransaction update(final long id) {
        throw new UnsupportedOperationException();
    }

    public void delete(final BankTransaction bankTransaction) {
        throw new UnsupportedOperationException();
    }
}
