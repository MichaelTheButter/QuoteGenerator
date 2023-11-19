import configuration.MyDBConnection;
import core.Employee;
import infrastructure.InsertData;
import infrastructure.LoadCustomerDirectly;
import infrastructure.LoadProductDirectly;

import java.nio.file.Path;
import java.nio.file.Paths;

public class App {
    public static void main(String[] args){
        MyDBConnection myDB = new MyDBConnection();
        InsertData.insertEmployee(new Employee("molly", "Butter"), myDB);
        //InsertData.insertSupplier(new Supplier("Sample Company", "sample street 22, 00-000 sample City", 111555666), myDB);

        //Path filePath = Paths.get(("src\\rawData\\skincare_products_clean.csv"));
        //LoadProductDirectly.insertProducts(filePath,myDB);

        //LoadCustomerDirectly.readJSON();
    }
}
