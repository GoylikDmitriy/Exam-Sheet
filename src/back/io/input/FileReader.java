package back.io.input;

/**
 * Represents an abstract class
 * to read data from file.
 * Needs to implement one method - read.
 * @author Goylik D.V.
 * @see Reader
 */
public abstract class FileReader implements Reader {
    protected String filePath;

    /**
     * Constructor of FileReader.
     * @param filePath path where file to read is located.
     */
    public FileReader(String filePath) {
        this.filePath = filePath;
    }

    public String getFilePath() {
        return filePath;
    }
}