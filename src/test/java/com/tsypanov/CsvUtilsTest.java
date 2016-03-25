package com.tsypanov;

import org.junit.Test;

import java.io.File;
import java.util.List;

import static org.junit.Assert.*;

public class CsvUtilsTest {

    @Test
    public void parseInputFile() throws Exception {
        String fileName =  "input.csv";
        File file = Utils.getFileFromArgument(fileName);
        List<LotteryPerson> lotteryPersons = CsvUtils.parseInputFile(file);

        assertEquals(8, lotteryPersons.size());
    }

    @Test(expected = RuntimeException.class)
    public void parseNonExistingInputFile() throws Exception {
        String fileName =  "non_existing_file.csv";
        File file = Utils.getFileFromArgument(fileName);
        CsvUtils.parseInputFile(file);
    }

    @Test
    public void testEmptyFile() throws Exception {
        String fileName =  "empty_input.csv";
        File file = Utils.getFileFromArgument(fileName);
        List<LotteryPerson> lotteryPersons = CsvUtils.parseInputFile(file);

        assertTrue(lotteryPersons.isEmpty());
    }

    @Test
    public void testCorruptFile() throws Exception {
        String fileName =  "corrupt_input.csv";
        File file = Utils.getFileFromArgument(fileName);
        List<LotteryPerson> lotteryPersons = CsvUtils.parseInputFile(file);

        assertEquals(9, lotteryPersons.size());
    }
}