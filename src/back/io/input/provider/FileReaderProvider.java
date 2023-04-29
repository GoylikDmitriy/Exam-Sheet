package back.io.input.provider;

import back.io.input.FileReader;
import back.io.input.impl.XLSXFileReader;

/**
 * Provides with method to define type of file.
 * @author Goylik D.V.
 */
public final class FileReaderProvider {
    private FileReaderProvider() {}

    /**
     * Gives class FileReader by file type.
     * @param path path of file.
     * @return corresponding FileReader.
     */
    public static FileReader getFileReader(String path) {
        return switch (getFileType(path)) {
            case XLSX -> new XLSXFileReader(path);
            case TXT -> null;
            case DOCX -> null;
            case UNDEFINED -> null;
        };
    }

    /**
     * Defines a file type by its extension.
     * @param path path to file.
     * @return file type.
     */
    private static FileType getFileType(String path) {
        String[] sep = path.split("\\.");
        String extension = sep[sep.length - 1];
        return switch (extension) {
            case "xlsx" -> FileType.XLSX;
            case "txt" -> FileType.TXT;
            case "docx" -> FileType.DOCX;
            default -> FileType.UNDEFINED;
        };
    }
}
