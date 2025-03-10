package com.real_software.chapter_03;

import com.real_software.chapter_02.bank_transaction.BankTransaction;

import java.time.Month;

public class BankTransactionIsInFebruaryAndExpensive implements BankTransactionFilter{
    @Override
    public boolean test(final BankTransaction bankTransaction) {
        return bankTransaction.getDate().getMonth() == Month.FEBRUARY &&
                bankTransaction.getAmount() > 1000;
    }
}
