import policy.*;
import java.util.Scanner;
import java.util.List;
import java.io.*;

public class PolicyRunner {
    public static void main(String[] args) {
        String filepath = "input.txt";
        try {
            File file = new File(filepath);
            Scanner s = new Scanner(file);

            RecordParser parser = new RecordParser(s);
            List<Policy> policies = parser.ParseRecord();
            
            String dateString = "12/31/2020";
            Date valuationDate = new Date(dateString); // valuation date to use for this exercise 
            int size = policies.size();
            for (int i = 0; i < size; i++) {
                System.out.println("policy     month     amount");
                PremiumCalculator calculator = new PremiumCalculator(policies.get(i), valuationDate);
                calculator.calculatePremium(); // calculates premium and returns result
                System.out.printf("\n");
            }
        }
        catch (FileNotFoundException exception) {
            System.out.println("File not found");
        }
    }
}
