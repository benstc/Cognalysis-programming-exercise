package policy;

public class Policy {
    private String policyName;
    private Date startDate;
    private Date endDate;
    private int cost;
    private int daysSpanned;
    
    public Policy(String policyName, Date startDate, Date endDate, int cost) {
        this.policyName = policyName;
        this.startDate = startDate;
        this.endDate = endDate;
        this.cost = cost;
        this.daysSpanned = startDate.daysBetween(endDate);
    }

    public String getPolicyName() {
        return policyName;
    }

    public Date getStartDate() {
        return startDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public int getCost() {
        return cost;
    }
    
    public int getDaysSpanned() {
        return daysSpanned;
    }

    public float getDailyPremium() {
        float fcost = cost;
        float dailyPremium = fcost/daysSpanned;
        return dailyPremium;
    }
}
