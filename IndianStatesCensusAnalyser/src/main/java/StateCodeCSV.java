import com.opencsv.bean.CsvBindByName;

public class StateCodeCSV {

    @CsvBindByName(column = "State Name", required = true)
    public String State;

    @CsvBindByName(column = "StateCode", required = true)
    public String StateCode;

    @Override
    public String toString() {
        return "StateCodeCSV{" +
                ", state='" + State + '\'' +
                ", StateCode='" + StateCode + '\'' +
                '}';
    }
}
