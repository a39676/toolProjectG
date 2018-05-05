package job_test.orange.domain;

public class RecoverAssign {
    private Long id;

    private Integer uid;

    private String loanId;

    private Long createTime;

    private Integer overdueLevel;

    private Integer maxOverdueDay;

    private Boolean isRunout;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getUid() {
        return uid;
    }

    public void setUid(Integer uid) {
        this.uid = uid;
    }

    public String getLoanId() {
        return loanId;
    }

    public void setLoanId(String loanId) {
        this.loanId = loanId == null ? null : loanId.trim();
    }

    public Long getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Long createTime) {
        this.createTime = createTime;
    }

    public Integer getOverdueLevel() {
        return overdueLevel;
    }

    public void setOverdueLevel(Integer overdueLevel) {
        this.overdueLevel = overdueLevel;
    }

    public Integer getMaxOverdueDay() {
        return maxOverdueDay;
    }

    public void setMaxOverdueDay(Integer maxOverdueDay) {
        this.maxOverdueDay = maxOverdueDay;
    }

    public Boolean getIsRunout() {
        return isRunout;
    }

    public void setIsRunout(Boolean isRunout) {
        this.isRunout = isRunout;
    }
}