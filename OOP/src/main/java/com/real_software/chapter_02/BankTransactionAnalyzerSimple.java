package com.real_software.chapter_02;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

//* 모든 거래 내역의 합 계산하기
public class BankTransactionAnalyzerSimple {
    private static final String RESOURCES = "src/main/resources/";

    public static void main(String[] args) throws IOException {
        // 파일 시스템의 경로를 응용프로그램의 명령줄 인수로 전달해 로딩
        final Path path = Paths.get(RESOURCES + args[0]);
        // CSV파일의 행 목록을 반환
        List<String> lines = Files.readAllLines(path);
        double total = 0d;

        for (final String line : lines) {
            // CSV파일의 구분자는 콤마라고 가정
            final String[] columns = line.split(",");
            final double amount = Double.parseDouble(columns[1]); // 1 col에 가격이 있음
            total += amount;
        }
        System.out.println("The total for all transactions is " + total);
    }
}
