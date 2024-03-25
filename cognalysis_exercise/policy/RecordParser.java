package policy;

import java.util.Scanner;
import java.io.File;
import java.util.*;

public class RecordParser {
    private Scanner input;

    public RecordParser(Scanner input) {
        this.input = input;
    }

    public List<Policy> ParseRecord() { // parse input.txt into an arrayList of policy objects
        List<Policy> policies = new ArrayList<Policy>();
        while (input.hasNextLine()) {
            String policyRecord = input.nextLine();
            String[] policyComponents = policyRecord.split(",");
            String policyName = policyComponents[0];
            Date startDate = new Date(policyComponents[1]);
            Date endDate = new Date(policyComponents[2]);
            int cost = Integer.parseInt(policyComponents[3]);
            Policy policy = new Policy(policyName, startDate, endDate, cost);
            policies.add(policy);
        }
        input.close();

        return policies;
    }
}
