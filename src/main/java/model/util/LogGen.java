package model.util;

import org.apache.log4j.Logger;

public final class LogGen {
    private static Logger log;

    private LogGen() {
    }

    public static Logger getInstance() {
        if (log == null) {
            log = Logger.getLogger(LogGen.class);
        }
        return log;
    }
}
