import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

public class FileWriter implements AutoCloseable {

    File file;
    FileOutputStream fileOutputStream;
    private static final Logger LOGGER;

    static {
        LOGGER = LogManager.getLogger(FileWriter.class);
    }

    public FileWriter() {
        file = new File("./src/", "1.txt");
        try {
            file.createNewFile();
            fileOutputStream = new FileOutputStream(file, true);
            LOGGER.info("File opened.");
        } catch (IOException exception) {
            LOGGER.error(exception.getMessage());
        }
    }

    public void write(String writeInFile) {
        try {
            fileOutputStream.write(writeInFile.getBytes());
            LOGGER.info("Written in file.");
        } catch (IOException exception) {
            LOGGER.error(exception.getMessage());
        }

    }

    @Override
    public void close() {
        try {
            fileOutputStream.close();
            LOGGER.info("File closed.");
        } catch (IOException exception) {
            LOGGER.error(exception.getMessage());
        }
    }

}
