package test_data;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.log4j.Logger;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;

public class DataCSVParser {

    private static final Logger LOGGER = Logger.getLogger(DataCSVParser.class);

    public static CSVParser getCsvRecords(String filepath) {
        Reader reader = null;
        try {
            reader = new FileReader(filepath);
        } catch (FileNotFoundException e) {
            LOGGER.fatal("Impossible to open document by following link: " + filepath);
        }
        CSVParser records = null;
        try {
            records = CSVFormat.DEFAULT
                    .withFirstRecordAsHeader()
                    .parse(reader);
        } catch (IOException e) {
            LOGGER.fatal("Impossible to parse following document: " + filepath);
        }

        return records;
    }
}