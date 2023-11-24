import configuration.DataFilesPath;
import configuration.MyDBConnection;
import core.Employee;
import core.Supplier;
import infrastructure.*;

public class App {
    public static void main(String[] args){
        MyDBConnection myDB = new MyDBConnection();
        //insert from Object
        InsertFromObject.insertEmployee(new Employee("molly", "Butter"), myDB);
        InsertFromObject.insertSupplier(new Supplier("Sample Company", "sample street 22, 00-000 sample City", 111555666), myDB);
        //insert from CSV
        InsertFromCSV.insertProducts(DataFilesPath.PRODUCTS_FILE,myDB);
        //insert from JSON
        InsertFromJson.loadCustomers(
                InsertFromJson.readJSON(DataFilesPath.JSON_CUSTOMER_FILE),
                myDB);

        CreateQuote.insertFromFile(DataFilesPath.SAMPLE_QUOTE, myDB);

        //read from Postgres
        ReadQuote.readByID(myDB);

    }
}
