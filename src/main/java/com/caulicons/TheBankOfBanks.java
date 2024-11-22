package com.caulicons;

import com.caulicons.utils.Utils;

public class TheBankOfBanks {

    public static void main(String[] args) {

        Utils.bootstrap();
        Runtime.getRuntime().addShutdownHook(new Thread(() -> about()));
    }

    public static void about() {

        System.out.println("""
                The Bank of Banks is a simple banking system developed by the Caulicons team.
                It was created to demonstrate the use of Java and Git in a collaborative environment.
                We hope you enjoy it!
                """);
    }

}