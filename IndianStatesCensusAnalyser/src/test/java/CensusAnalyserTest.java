import jdk.jfr.Description;
import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.junit.rules.ExpectedException;

import static org.junit.jupiter.api.Assertions.*;

class CensusAnalyserTest {
    private static final String INDIA_CENSUS_CSV_FILE_PATH = "C:\\Users\\sreen\\Desktop\\Pgms\\IndianStatesCensusAnalyser\\src\\test\\resources\\IndiaStateCensusData.csv";
    private static final String WRONG_CSV_FILE_PATH = "C:\\Users\\sreen\\Desktop\\Pgms\\IndianStatesCensusAnalyser\\src\\main\\resources\\IndiaStateCensusData.csv";
    private static final String WRONG_CENSUS_FILE_EXTENSION = "C:\\Users\\sreen\\Desktop\\Pgms\\IndianStatesCensusAnalyser\\src\\test\\resources\\IndiaStateCensusData.sh";
    private static final String INDIA_CENSUS_CSV_FILE_WRONG_DELIMITER = "C:\\Users\\sreen\\Desktop\\Pgms\\IndianStatesCensusAnalyser\\src\\test\\resources\\WrongDelimiter.csv";
    private static final String INDIA_CENSUS_CSV_FILE_WRONG_HEADER = "C:\\Users\\sreen\\Desktop\\Pgms\\IndianStatesCensusAnalyser\\src\\test\\resources\\WrongHeader.csv";

    @Description("Given Indian States Census CSV file, Check to ensure the number of record matches")
    @Test
    void givenIndianCensusCSVFileReturnsCorrectRecords() {
        try {
            CensusAnalyser censusAnalyser = new CensusAnalyser();
            int numOfRecords = censusAnalyser.loadIndiaCensusData(INDIA_CENSUS_CSV_FILE_PATH);
            Assert.assertEquals(29,numOfRecords);
        } catch (CensusAnalyserException e) { }
    }

    @Description("Given Indian State Census CSV file with incorrect path should return an custom exception")
    @Test
    void givenIndiaCensusData_WithWrongFile_ShouldThrowException() {
        try {
            CensusAnalyser censusAnalyser = new CensusAnalyser();
            ExpectedException exceptionRule = ExpectedException.none();
            exceptionRule.expect(CensusAnalyserException.class);
            censusAnalyser.loadIndiaCensusData(WRONG_CSV_FILE_PATH);
        } catch (CensusAnalyserException e) {
            Assert.assertEquals(CensusAnalyserException.ExceptionType.CENSUS_FILE_PROBLEM,e.type);
        }
    }

    @Description("Given the Indian State Census CSV file with incorrect type, should return Custom Exception")
    @Test
    void givenIndiaCensusData_WithWrongFileType_ShouldThrowException() {
        try {
            CensusAnalyser censusAnalyser = new CensusAnalyser();
            ExpectedException exceptionRule = ExpectedException.none();
            exceptionRule.expect(CensusAnalyserException.class);
            censusAnalyser.loadIndiaCensusData(WRONG_CENSUS_FILE_EXTENSION);
        } catch (CensusAnalyserException e) {
            Assert.assertEquals(CensusAnalyserException.ExceptionType.CENSUS_FILE_PROBLEM,e.type);
        }
    }

    @Description("Given the State Census CSV File when correct but delimiter incorrect Returns a custom Exception")
    @Test
    void givenIndiaCensusData_WithWrongDelimiter_ShouldThrowException() {
        try {
            CensusAnalyser censusAnalyser = new CensusAnalyser();
            ExpectedException exceptionRule = ExpectedException.none();
            exceptionRule.expect(CensusAnalyserException.class);
            censusAnalyser.loadIndiaCensusData(INDIA_CENSUS_CSV_FILE_WRONG_DELIMITER);
        } catch (CensusAnalyserException e) {
            Assert.assertEquals(CensusAnalyserException.ExceptionType.INVALID_HEADER_AND_DELIMITER, e.type);
        }
    }

    @Description("Given the State Census CSV File when correct but header incorrect return custom exception")
    @Test
    void givenIndiaCensusData_WithWrongHeader_ShouldThrowException() {
        try {
            CensusAnalyser censusAnalyser = new CensusAnalyser();
            censusAnalyser.loadIndiaCensusData(INDIA_CENSUS_CSV_FILE_WRONG_HEADER);
        } catch (CensusAnalyserException e) {
            Assert.assertEquals(CensusAnalyserException.ExceptionType.INVALID_HEADER_AND_DELIMITER, e.type);
        }
    }
}