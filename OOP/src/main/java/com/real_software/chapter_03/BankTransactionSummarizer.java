package com.real_software.chapter_03;

import com.real_software.chapter_02.bank_transaction.BankTransaction;

@FunctionalInterface
public interface BankTransactionSummarizer {
    double summarize(double accumulator, BankTransaction bankTransaction);
}
