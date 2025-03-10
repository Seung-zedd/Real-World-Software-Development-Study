package com.real_software.chapter_03;

import com.real_software.chapter_02.bank_transaction.BankTransaction;
import jdk.jfr.Category;

import java.time.Month;
import java.util.List;

//! should be banned since it's a God interface
public interface BankTransactionProcessor {
    double calculateTotalAmount();
    double calculateTotalInMonth(Month month);
    double calculateTotalInJanuary();
    double calculateAverageAmount();
    double calculateAverageAmountForCategory(Category category);
    List<BankTransaction> findTransactions(BankTransactionFilter bankTransactionFilter);

}
