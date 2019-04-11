package model.spec;

import java.util.logging.Logger;

public final class LogGen {
    private static Logger log;

    private LogGen(){}

    public static Logger getInstance(){
        if(log == null)
            synchronized (LogGen.class){
                if(log == null)
                    log = Logger.getLogger(LogGen.class.getName());
                    LogGen.getInstance().getClass().getResourceAsStream("log4j.properties");
            }
            log.info("LALALA!!");
        return log;
    }
}
