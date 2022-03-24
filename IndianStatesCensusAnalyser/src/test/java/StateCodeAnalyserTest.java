import jdk.jfr.Description;
import org.junit.Assert;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class StateCodeAnalyserTest {

    private static final String INDIA_STATE_CODE_CSV_FILE_PATH = "C:\\Users\\sreen\\Desktop\\Pgms\\IndianStatesCensusAnalyser\\src\\test\\resources\\IndiaStateCode.csv";

    @Description("Given States Census CSV file, Check to ensure the number of state code matches")
    @Test
    void givenStateCodeCSVFileReturnsCorrectRecords() {
        try {
            StateCodeAnalyser stateCodeAnalyser = new StateCodeAnalyser();
            int numOfStateCode = stateCodeAnalyser.loadIndianStateCodeData(INDIA_STATE_CODE_CSV_FILE_PATH);
            Assert.assertEquals(37, numOfStateCode);
        } catch (CensusAnalyserException e) { }
    }
}