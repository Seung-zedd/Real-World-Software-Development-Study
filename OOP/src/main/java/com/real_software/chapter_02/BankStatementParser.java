package com.real_software.chapter_02;

import com.real_software.chapter_02.bank_transaction.BankTransaction;

import java.util.List;

public interface BankStatementParser {
    BankTransaction parseFrom(final String line);
    List<BankTransaction> parseLinesFrom(List<String> lines);
}
