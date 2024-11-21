package com.caulicons;

import com.caulicons.controllers.BankMenu;
import com.caulicons.models.client.Client;

public class TheBankOfBanks {

    public static void main(String[] args) {

        BankMenu menu = new BankMenu();
        Client client = new Client("4654654465546546", "Vítor Oliveira");
        menu.start(client);

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