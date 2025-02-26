package com.real_software.chapter_02;

import java.io.IOException;

public class MainApplication {

    public static void main(String[] args) throws IOException {
        final BankStatementAnalyzer bankStatementAnalyzer = new BankStatementAnalyzer();
        //! 인터페이스는 추상 메서드의 집합체라 추상 클래스처럼 그 자체로는 인스턴스를 생성할 수 없다
        final BankStatementParser bankStatementParser = new BankStatementCSVParser();

        bankStatementAnalyzer.analyze(args[0], bankStatementParser);
    }
}
