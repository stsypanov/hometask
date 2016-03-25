package com.tsypanov;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.regex.Pattern;

public final class CsvUtils {

    private static Pattern numberPattern;

    private CsvUtils() {
    }

    public static List<LotteryPerson> parseInputFile(File file) {

        if (!file.exists()) throw new RuntimeException("Input file does not exist");

        List<String> lines = readFile(file);

        return convertIntoEntities(lines);
    }

    private static List<LotteryPerson> convertIntoEntities(List<String> lines) {
        List<LotteryPerson> lotteryPeople = new ArrayList<>(lines.size());
        for (int i = 0; i < lines.size(); i++) {
            Optional<LotteryPerson> lotteryPersonHolder = convertLineIntoEntity(lines.get(i), i + 1);
            if (lotteryPersonHolder.isPresent()) {
                lotteryPeople.add(lotteryPersonHolder.get());
            }
        }
        return lotteryPeople;
    }

    private static Optional<LotteryPerson> convertLineIntoEntity(String line, int lineNumber) {
        String[] chunks = line.split(",");

        Optional<LotteryPerson> lotteryPersonHolder = Optional.empty();
        if (checkIfCsvLineInvalid(line, chunks)) {
            printError(lineNumber);
        } else {
            lotteryPersonHolder = buildPerson(chunks);
        }
        return lotteryPersonHolder;
    }

    private static Optional<LotteryPerson> buildPerson(String[] chunks) {
        Optional<LotteryPerson> lotteryPersonHolder;
        LotteryPerson lotteryPerson = new LotteryPerson();
        lotteryPerson.setLastName(chunks[0]);
        lotteryPerson.setFirstName(chunks[1]);
        lotteryPerson.setCountry(chunks[2]);
        lotteryPerson.setTicket(chunks[3]);
        lotteryPersonHolder = Optional.of(lotteryPerson);
        return lotteryPersonHolder;
    }

    private static void printError(int lineNumber) {
        System.err.println("Line " + lineNumber + " is invalid and cannot be converted into entity. Check input file");
    }

    private static boolean checkIfCsvLineInvalid(String line, String[] chunks) {
        boolean result;
        try {
            result = line.trim().isEmpty() || chunks.length != 4;

            for (String chunk : chunks) {
                result = result || chunk.isEmpty();
            }
            if (!result && isTicketInValid(chunks[3])) {
                result = true;
            }

        } catch (ArrayIndexOutOfBoundsException e) {
            result = true;
        }
        return result;
    }

    private static boolean isTicketInValid(String chunk) {
        return !getNumberPattern().matcher(chunk).matches();
    }

    private static List<String> readFile(File file) {
        try {
            return Files.readAllLines(file.toPath());
        } catch (IOException e) {
            throw new RuntimeException("Check file format: error while reading lines", e);
        }
    }

    public static Pattern getNumberPattern() {
        if (numberPattern == null) {
            numberPattern = Pattern.compile("\\d++");
        }
        return numberPattern;
    }
}
