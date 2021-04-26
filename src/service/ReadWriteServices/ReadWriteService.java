package service.ReadWriteServices;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

abstract public class ReadWriteService {
    protected static final String DIRECTORY_PATH = "resources/app_data/";

    protected void checkIfDirectoryAndFileExist(String file_path) throws IOException {
        if (!Files.exists(Paths.get(DIRECTORY_PATH))){
            Files.createDirectories(Paths.get(DIRECTORY_PATH));
        }
        if (!Files.exists(Paths.get(file_path))){
            Files.createFile(Paths.get(file_path));
        }
    }
}
