package com.tsypanov;

import java.util.List;

public class Main {

    public static void main(String[] args) {
        String inputFile = getInputFileName(args);
        String winner = getTicketNumber(args);

        Lottery lottery = new Lottery(inputFile, winner);
        List<LotteryPerson> winners = lottery.defineWinners();

        Utils.printResults(winners);
    }

    private static String getInputFileName(String[] arg) {
        try {
            return arg[0];
        } catch (ArrayIndexOutOfBoundsException e) {
            throw new RuntimeException("Please provide input file");
        }
    }

    private static String getTicketNumber(String[] arg) {
        try {
            return arg[1];
        } catch (ArrayIndexOutOfBoundsException e) {
            throw new RuntimeException("Please provide the number of winner's ticket");
        }
    }


}
