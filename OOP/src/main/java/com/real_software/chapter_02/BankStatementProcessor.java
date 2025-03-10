package com.real_software.chapter_02;

import com.real_software.chapter_02.bank_transaction.BankTransaction;
import com.real_software.chapter_03.BankTransactionFilter;
import com.real_software.chapter_03.BankTransactionSummarizer;

import java.time.Month;
import java.util.*;

//* 계산 연산 그룹화
public class BankStatementProcessor {

    // 입출금 내역 목록 도메인을 공유하기 때문에 필드로 선언
    private final List<BankTransaction> bankTransactions;

    public BankStatementProcessor(List<BankTransaction> bankTransactions) {
        this.bankTransactions = bankTransactions;
    }

    public double summarizeTransactions(final BankTransactionSummarizer bankTransactionSummarizer) {
        double result = 0;
        for (final BankTransaction bankTransaction : bankTransactions) {
            result = bankTransactionSummarizer.summarize(result, bankTransaction);
        }
        return result;
    }

    public double calculateTotalInMonth(final Month month) {
        return summarizeTransactions((acc, bankTransaction) ->
                (bankTransaction.getDate().getMonth() == month) ? (acc + bankTransaction.getAmount()) : acc
        );
    }


    public double calculateTotalAmount() {
        double total = 0d;
        for (final BankTransaction bankTransaction : bankTransactions) {
            total += bankTransaction.getAmount();
        }
        return total;
    }

    // 숙제로 구현한 부분
    public double calculateMaxInMonth(final Month month) {
        double max = 0d;
        for (final BankTransaction bankTransaction : bankTransactions) {
            if (bankTransaction.getDate().getMonth() == month && bankTransaction.getAmount() > max) {
                max = bankTransaction.getAmount();
            }

        }
        return max;
    }

    // 숙제로 구현한 부분
    public double calculateMinInMonth(final Month month) {
        double min = 0d;
        for (final BankTransaction bankTransaction : bankTransactions) {
            if (bankTransaction.getDate().getMonth() == month && bankTransaction.getAmount() < min) {
                min = bankTransaction.getAmount();
            }

        }
        return min;
    }

    public double calculateTotalForCategory(final String category) {
        double total = 0d;
        for (final BankTransaction bankTransaction : bankTransactions) {
            if (bankTransaction.getDescription().equals(category))
                total += bankTransaction.getAmount();
        }
        return total;
    }

    // 월별 및 설명별로 지출을 그룹화한 히스토그램을 반환한다는 것은 각 월과 각 설명(카테고리)별로 지출 금액을 집계하여 시각적으로 표현하는 것을 의미합니다. 이를 위해 각 월과 설명별로 지출 금액을 합산한 후, 이를 히스토그램 형태로 반환
    //* getOrDefault(key, value): null-safety한 메서드
    // 숙제로 구현한 부분
    public Map<Month, Double> summarizeExpensesByMonth() {
        Map<Month, Double> expensesByMonth = new EnumMap<>(Month.class);

        for (BankTransaction bankTransaction : bankTransactions) {
            Month month = bankTransaction.getDate().getMonth();
            double amount = bankTransaction.getAmount();
            expensesByMonth.put(month, expensesByMonth.getOrDefault(month, 0.0) + amount);
        }
        return expensesByMonth;
    }

    // 숙제로 구현한 부분
    public Map<String, Double> summarizeExpensesByDescription() {
        Map<String, Double> expensesByDescription = new HashMap<>();

        for (BankTransaction bankTransaction : bankTransactions) {
            String description = bankTransaction.getDescription();
            double amount = bankTransaction.getAmount();
            expensesByDescription.put(description, expensesByDescription.getOrDefault(description, 0.0) + amount);
        }
        return expensesByDescription;
    }

    public List<BankTransaction> findTransactionsGreaterThanEqual(final int amount) {
        return findTransactions(bankTransaction ->
                bankTransaction.getAmount() >= amount);
    }

    //* 인터페이스를 통해 반복 로직과 비즈니스 로직의 결합을 제거
    //todo 호출부분은 테스트 파일에서 할 예정
    public List<BankTransaction> findTransactions(BankTransactionFilter bankTransactionFilter) {
        final List<BankTransaction> result = new ArrayList<>();
        for (final BankTransaction bankTransaction : bankTransactions) {
            if (bankTransactionFilter.test(bankTransaction)) {
                result.add(bankTransaction);
            }
        }
        return result;
    }

    public List<BankTransaction> findTransactionInMonth(final Month month) {
        final List<BankTransaction> result = new ArrayList<>();

        for (BankTransaction bankTransaction : bankTransactions) {
            if (bankTransaction.getDate().getMonth() == month) {
                result.add(bankTransaction);
            }
        }
        return result;

    }

    //! bad code due to redundant and repetitive logic
    /*public List<BankTransaction> findTransactionsInMonthAndGreater(final Month month, final int amount) {
        final List<BankTransaction> result = new ArrayList<>();

        for (BankTransaction bankTransaction : bankTransactions) {
            if (bankTransaction.getDate().getMonth() == month && bankTransaction.getAmount() >= amount) {
                result.add(bankTransaction);
            }
        }
        return result;
    }*/



}
