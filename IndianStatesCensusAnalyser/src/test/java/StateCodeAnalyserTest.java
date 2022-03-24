import jdk.jfr.Description;
import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.junit.rules.ExpectedException;

import static org.junit.jupiter.api.Assertions.*;

class StateCodeAnalyserTest {

    private static final String INDIA_STATE_CODE_CSV_FILE_PATH = "C:\\Users\\sreen\\Desktop\\Pgms\\IndianStatesCensusAnalyser\\src\\test\\resources\\IndiaStateCode.csv";
    private static final String WRONG_CSV_FILE_PATH = "C:\\Users\\sreen\\Desktop\\Pgms\\IndianStatesCensusAnalyser\\src\\main\\resources\\IndiaStateCode.csv";
    private static final String WRONG_CENSUS_FILE_EXTENSION = "C:\\Users\\sreen\\Desktop\\Pgms\\IndianStatesCensusAnalyser\\src\\test\\resources\\IndiaStateCode.sh";
    private static final String STATE_CODE_CSV_FILE_WRONG_DELIMITER = "C:\\Users\\sreen\\Desktop\\Pgms\\IndianStatesCensusAnalyser\\src\\test\\resources\\WrongDelimiter.csv";

    @Description("Given Indian State Code CSV file, Check to ensure the number of state code matches")
    @Test
    void givenStateCodeCSVFileReturnsCorrectRecords() {
        try {
            StateCodeAnalyser stateCodeAnalyser = new StateCodeAnalyser();
            int numOfStateCode = stateCodeAnalyser.loadIndianStateCodeData(INDIA_STATE_CODE_CSV_FILE_PATH);
            Assert.assertEquals(37, numOfStateCode);
        } catch (CensusAnalyserException e) { }
    }

    @Description("Given Indian State Code CSV file with incorrect path should return an custom exception")
    @Test
    void givenStateCodeData_WithWrongFile_ShouldThrowException() {
        try {
            CensusAnalyser censusAnalyser = new CensusAnalyser();
            ExpectedException exceptionRule = ExpectedException.none();
            exceptionRule.expect(CensusAnalyserException.class);
            censusAnalyser.loadIndiaCensusData(WRONG_CSV_FILE_PATH);
        } catch (CensusAnalyserException e) {
            Assert.assertEquals(CensusAnalyserException.ExceptionType.CENSUS_FILE_PROBLEM,e.type);
        }
    }

    @Description("Given the State Code CSV File with incorrect type, should return Custom Exception")
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

    @Description("Given the State Code CSV File when correct but delimiter incorrect Returns a custom Exception")
    @Test
    void givenIndiaCensusData_WithWrongDelimiter_ShouldThrowException() {
        try {
            CensusAnalyser censusAnalyser = new CensusAnalyser();
            ExpectedException exceptionRule = ExpectedException.none();
            exceptionRule.expect(CensusAnalyserException.class);
            censusAnalyser.loadIndiaCensusData(STATE_CODE_CSV_FILE_WRONG_DELIMITER);
        } catch (CensusAnalyserException e) {
            Assert.assertEquals(CensusAnalyserException.ExceptionType.INVALID_HEADER_AND_DELIMITER, e.type);
        }
    }
}