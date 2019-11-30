package cn.sunline.ssm.bean;

public class Customer {
    private Integer custId;

    private String custName;

    private Integer custAge;

    public Integer getCustId() {
        return custId;
    }

    public void setCustId(Integer custId) {
        this.custId = custId;
    }

    public String getCustName() {
        return custName;
    }

    public void setCustName(String custName) {
        this.custName = custName;
    }

    public Integer getCustAge() {
        return custAge;
    }

    public void setCustAge(Integer custAge) {
        this.custAge = custAge;
    }
}