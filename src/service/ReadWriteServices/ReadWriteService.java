package service.ReadWriteServices;

import main.application.AppData;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public interface ReadWriteService{
    default void checkDirectoryAndFileExist(String directory_path, String file_path) throws IOException{
        if (!Files.exists(Paths.get(directory_path))){
            Files.createDirectories(Paths.get(directory_path));
        }
        if (!Files.exists(Paths.get(file_path))){
            Files.createFile(Paths.get(file_path));
        }
    }

    void read(AppData appData);
    void write(AppData appData);
}
