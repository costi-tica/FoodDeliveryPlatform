package service;

import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.sql.Timestamp;
import java.util.Date;

public final class LoggingService{
    private static final String DIRECTORY_PATH = "resources/logging/";
    private static final String LOG_ACTION_FILE_PATH = DIRECTORY_PATH + "actionLog.csv";
    private static LoggingService INSTANCE;

    private LoggingService() { }

    public static LoggingService getInstance() {
        if (INSTANCE == null) INSTANCE = new LoggingService();
        return INSTANCE;
    }

    public void logAction(String action) {
        try {
            if (!Files.exists(Paths.get(DIRECTORY_PATH))){
                Files.createDirectories(Paths.get(DIRECTORY_PATH));
            }
            if (!Files.exists(Paths.get(LOG_ACTION_FILE_PATH))){
                Files.createFile(Paths.get(LOG_ACTION_FILE_PATH));
            }
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }

        try {
            BufferedWriter writer = Files.newBufferedWriter(Paths.get(LOG_ACTION_FILE_PATH), StandardOpenOption.APPEND);
            writer.write(action + "," + new Timestamp((new Date()).getTime()));
            writer.newLine();
            writer.flush();
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }
}
