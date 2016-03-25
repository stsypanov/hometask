package com.tsypanov;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import static com.tsypanov.Utils.getFileFromArgument;

public class Lottery {
    private String file;
    private String winner;

    public Lottery(String file, String winner) {
        this.file = file;
        this.winner = winner;
    }

    public List<LotteryPerson> defineWinners() {
        File fileFromArgument = getFileFromArgument(file);

        List<LotteryPerson> lotteryPeople = CsvUtils.parseInputFile(fileFromArgument);

        return calculateCreditsForParticipants(lotteryPeople);
    }

    private List<LotteryPerson> calculateCreditsForParticipants(List<LotteryPerson> lotteryPeople) {
        List<LotteryPerson> uniqueLotteryPeople = new ArrayList<>(lotteryPeople.size());

        for (LotteryPerson person : lotteryPeople){
            final int credits = calculateCredits(person);
            if (uniqueLotteryPeople.contains(person)) {
                int indexOfAPerson = uniqueLotteryPeople.indexOf(person);
                uniqueLotteryPeople.get(indexOfAPerson).addCredits(credits);
            } else {
                person.setCredits(credits);
                uniqueLotteryPeople.add(person);
            }
        }
        return uniqueLotteryPeople;
    }

    private int calculateCredits(LotteryPerson person) {
        String ticket = person.getTicket();
        String lcs = Utils.lcs(ticket, winner);
        return lcs.length();
    }
}
