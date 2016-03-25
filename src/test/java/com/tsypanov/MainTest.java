package com.tsypanov;

import org.junit.*;

import java.util.List;

import static org.junit.Assert.*;

public class MainTest {

    @Test
    public void test() throws Exception {

        String file = "input.csv";
        String winner = "456000123";

        Lottery lottery = new Lottery(file, winner);
        List<LotteryPerson> lotteryPersons = lottery.defineWinners();

        Utils.printResults(lotteryPersons);

        assertEquals(7, lotteryPersons.size());

    }




}
