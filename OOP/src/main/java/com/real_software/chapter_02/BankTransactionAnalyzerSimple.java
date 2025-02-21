package com.real_software.chapter_02;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.time.Month;
import java.time.format.DateTimeFormatter;
import java.util.List;

//* 모든 거래 내역의 합 계산하기
public class BankTransactionAnalyzerSimple {
    private static final String RESOURCES = "src/main/resources/";

    public static void main(String[] args) throws IOException {
        //* 예제 2-1
        // 파일 시스템의 경로를 응용프로그램의 명령줄 인수로 전달해 로딩
        Path path = Paths.get(RESOURCES + args[0]);
        // CSV파일의 행 목록을 반환
        List<String> lines = Files.readAllLines(path);
        double total = 0d;

        for (final String line : lines) {
            // CSV파일의 구분자는 콤마라고 가정
            final String[] columns = line.split(","); // 콤마로 열 분리
            final double amount = Double.parseDouble(columns[1]); // 금액 추출 및 double로 파싱
            total += amount;
        }
        System.out.println("The total for all transactions is " + total);

        //* 예제 2-2: 1월 입출금 내역 합계 계산하기
        // 파일 시스템의 경로를 응용프로그램의 명령줄 인수로 전달해 로딩
        path = Paths.get(RESOURCES + args[0]);
        // CSV파일의 행 목록을 반환
        lines = Files.readAllLines(path);
        total = 0d;
        final DateTimeFormatter DATE_PATTERN = DateTimeFormatter.ofPattern("dd-MM-yyyy");

        for (final String line : lines) {
            // CSV파일의 구분자는 콤마라고 가정
            final String[] columns = line.split(","); // 콤마로 열 분리
            LocalDate date = LocalDate.parse(columns[0], DATE_PATTERN);
            if (date.getMonth() == Month.JANUARY) {
                final double amount = Double.parseDouble(columns[1]); // 금액 추출 및 double로 파싱
                total += amount;
            }
        }
        System.out.println("The total for all transactions in January is " + total);
    }

}
