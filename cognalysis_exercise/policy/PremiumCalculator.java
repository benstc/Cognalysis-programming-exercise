package policy;

import policy.Policy;
import policy.Date;

public class PremiumCalculator {
    private Policy policy;
    private Date valuationDate;
    
    public PremiumCalculator(Policy policy, Date valuationDate) {
        this.policy = policy;
        this.valuationDate = valuationDate;
    }

    public void calculatePremium() {
        Date startDate = policy.getStartDate();
        Date endDate = policy.getEndDate();
        // if the valuation date is after the end date of the policy, raise some erorr
        if (startDate.isAfter(valuationDate)) {
            System.out.println("Error: Start date of policy is after valuation date");
            return;
        }
        else {
            float dailyPremium = policy.getDailyPremium();
            // if the start of the policy is after the evaluation date, set valuationDate to endDate
            // so that premium is calculated up until the end of the policy
            if (valuationDate.isAfter(endDate)) {
                valuationDate = endDate;
            }
            dailyPremium = policy.getDailyPremium();
            
            Date tempDate = startDate;
            int year = tempDate.getYear();
            while (tempDate.getMonth() <= 12) { // iterate through until the end of the year from the startDate
                // this is the only while loop that will be called for the given valuation date
                if (tempDate.getMonth() == valuationDate.getMonth() && tempDate.getYear() == valuationDate.getYear()) {
                    float amount = amountForMonth(tempDate.getDay(), valuationDate.getDay(), year, dailyPremium);
                    printResults(tempDate.getMonth(), tempDate.getYear(), amount);
                    return;
                }
                else {
                    int daysInMonth = tempDate.daysInMonth();
                    float amount = amountForMonth(tempDate.getDay(), daysInMonth, year, dailyPremium);
                    printResults(tempDate.getMonth(), tempDate.getYear(), amount);
                    tempDate = new Date(1, tempDate.getMonth() + 1, tempDate.getYear());
                }
            }
            tempDate = new Date(1, 1, startDate.getYear() + 1);
            year += 1;
            while (tempDate.getMonth() <= startDate.getMonth()) {
                 // if the valuation date is in the year after the start date, this while loop will complete the job
                if (tempDate.getMonth() == valuationDate.getMonth()) {
                    float amount = amountForMonth(tempDate.getDay(), valuationDate.getDay(), year, dailyPremium);
                    printResults(tempDate.getMonth(), tempDate.getYear(), amount);
                    return;
                }
                else {
                    int daysInMonth = tempDate.daysInMonth();
                    float amount = amountForMonth(tempDate.getDay(), daysInMonth, year, dailyPremium);
                    printResults(tempDate.getMonth(), tempDate.getYear(), amount);
                    tempDate = new Date(1, tempDate.getMonth() + 1, tempDate.getYear());
                }
            }
            System.out.println("Error in calculating premium");
        }
    }

    private void printResults(int month, int year, float amount) {
        // prints resuls for each month, called within calculatePremium
        System.out.printf("%s    %d %d    $%.2f\n", policy.getPolicyName(), month, year, amount);
    }

    private float amountForMonth(int firstDay, int lastDay, int year, float dailyPremium) {
        int days = lastDay - firstDay + 1;
        float amount = days*dailyPremium;
        return amount;
    }
}
