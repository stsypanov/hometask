package com.tsypanov;

import org.junit.Test;

import java.io.File;

import static org.junit.Assert.*;

public class UtilsTest {

    @Test
    public void getFileFromArgument() throws Exception {
        String fileName = "input.csv";
        File file = Utils.getFileFromArgument(fileName);
        assertTrue(file.exists());
    }

    @Test
    public void testLcs() throws Exception {
        String s1 = "1234567890";
        String s2 = "13153137531";

        String lcs = Utils.lcs(s1, s2);
        assertEquals("1357", lcs);

        s1 = "";
        s2 = "";

        lcs = Utils.lcs(s1, s2);
        assertEquals("", lcs);
    }

    @SuppressWarnings("ConstantConditions")
    @Test(expected = NullPointerException.class)
    public void testLcs1() throws Exception {
        String s1 = null;
        String s2 = "13153137531";

        String lcs = Utils.lcs(s1, s2);
        assertEquals(lcs, "1357");
    }

    @SuppressWarnings("ConstantConditions")
    @Test(expected = NullPointerException.class)
    public void testLcs2() throws Exception {
        String s1 = "13153137531";
        String s2 = null;

        String lcs = Utils.lcs(s1, s2);
        assertEquals(lcs, "1357");
    }

    @SuppressWarnings("ConstantConditions")
    @Test(expected = NullPointerException.class)
    public void testLcs3() throws Exception {
        String s1 = null;
        String s2 = null;

        String lcs = Utils.lcs(s1, s2);
        assertEquals(lcs, "1357");
    }

    @Test
    public void testLcs4() throws Exception {
        String s1 = "1234567890";
        String s2 = "";

        String lcs = Utils.lcs(s1, s2);
        assertEquals("", lcs);

    }
}