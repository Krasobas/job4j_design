package ru.job4j.io;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class UsageLog4j {

    private static final Logger LOG = LoggerFactory.getLogger(UsageLog4j.class.getName());

    public static void main(String[] args) {
        String ln = System.lineSeparator();
        boolean bool = true;
        byte bt = 1;
        char ch = 2;
        short snum = 3;
        int inum = 4;
        long lnum = 5;
        float fnum = 6.1f;
        double dnum = 7.2;
        LOG.debug("{}boolean = {};{}byte = {};{}char = {};{}short = {};{}int = {};{}long = {};{}float = {};{}double = {}.",
                ln, bool, ln, bt, ln, ch, ln, snum, ln, inum, ln, lnum, ln, fnum, ln, dnum);

    }
}
