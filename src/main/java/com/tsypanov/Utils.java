package com.tsypanov;

import java.io.File;
import java.net.URISyntaxException;
import java.nio.file.Paths;
import java.util.List;

public final class Utils {

    private Utils() {
    }

    public static File getFileFromArgument(String arg) {
        try {
            return Paths.get(Main.class.getClassLoader().getResource(arg).toURI()).toFile();
        } catch (URISyntaxException | NullPointerException e) {
            throw new RuntimeException("Failed to get file for comparison", e);
        }
    }

    public static void printResults(List<LotteryPerson> uniqueLotteryPeople) {
        uniqueLotteryPeople
                .stream()
                .sorted((o1, o2) -> {
                    int comparisonResult = compare(o1.getLastName(), o2.getLastName());
                    if (comparisonResult == 0) {
                        comparisonResult = compare(o1.getFirstName(), o2.getFirstName());
                        if (comparisonResult == 0) {
                            comparisonResult = compare(o1.getCountry(), o2.getCountry());
                        }
                    }
                    return comparisonResult;
                })
                .forEach(lotteryPerson -> {
                            if (lotteryPerson.getCredits() > 0) {
                                System.out.println(lotteryPerson);
                            }
                        }
                );
    }

    private static int compare(String s1, String s2) {
        return String.CASE_INSENSITIVE_ORDER.compare(s1, s2);
    }

    public static String lcs(String s1, String s2) {
        String x;
        String y;

        int alen = s1.length();
        int blen = s2.length();
        if (alen == 0 || blen == 0) {
            return "";
        } else if (s1.charAt(alen - 1) == s2.charAt(blen - 1)) {
            return lcs(s1.substring(0, alen - 1), s2.substring(0, blen - 1)) + s1.charAt(alen - 1);
        } else {
            x = lcs(s1, s2.substring(0, blen - 1));
            y = lcs(s1.substring(0, alen - 1), s2);
        }
        return x.length() > y.length() ? x : y;
    }
}
