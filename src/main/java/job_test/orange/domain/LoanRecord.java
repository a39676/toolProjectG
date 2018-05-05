package job_test.orange.domain;

public class LoanRecord {
	private String loanId;

    private Long uid;

    private String phone;

    private String realName;

    private String identityNo;

    private Long loanAmount;

    private Float loanRate;

    private Long feeAmount;

    private Float interestRate;

    private Long interestAmount;

    private Float riskcServiceRate;

    private Long riskcServiceAmount;

    private Float riskReserveRate;

    private Long riskReserveAmount;

    private Float afterloanServiceRate;

    private Long afterloanServiceAmount;

    private Float platformServiceRate;

    private Long platformServiceAmount;

    private String couponId;

    private Long couponAmount;

    private Integer loanDay;

    private Long shouldRefundTime;

    private Long bankAmount;

    private Long applyTime;

    private String bankCardNo;

    private String bankCode;

    private String bankName;

    private Integer bankDepositCity;

    private String bankReservedPhone;

    private Byte loanStatus;

    private Byte reviewStatus;

    private Long reviewTime;

    private Byte reviewFaildType;

    private String reviewFaildReason;

    private String reviewAdminId;

    private Byte bankStatus;

    private Long bankTime;

    private Byte refundStatus;

    private Long refundTime;

    private Long refundAmount;

    private Long refundPrincipalAmount;

    private Long refundOverdueAmount;

    private String refundFaildReason;

    private Integer overdueDay;

    private Long overdueAmount;

    private String safeKey;

    private Integer dataVersion;

    private String extData;

    private String registerChannel;

    private Byte clientType;

    private String ip;

    private String deviceId;

    private Byte platform;

    private String appVersion;

    private String loginPhone;

    private Integer loanCount;

    private Integer loanSuccessCount;

    public String getLoanId() {
        return loanId;
    }

    public void setLoanId(String loanId) {
        this.loanId = loanId == null ? null : loanId.trim();
    }

    public Long getUid() {
        return uid;
    }

    public void setUid(Long uid) {
        this.uid = uid;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone == null ? null : phone.trim();
    }

    public String getRealName() {
        return realName;
    }

    public void setRealName(String realName) {
        this.realName = realName == null ? null : realName.trim();
    }

    public String getIdentityNo() {
        return identityNo;
    }

    public void setIdentityNo(String identityNo) {
        this.identityNo = identityNo == null ? null : identityNo.trim();
    }

    public Long getLoanAmount() {
        return loanAmount;
    }

    public void setLoanAmount(Long loanAmount) {
        this.loanAmount = loanAmount;
    }

    public Float getLoanRate() {
        return loanRate;
    }

    public void setLoanRate(Float loanRate) {
        this.loanRate = loanRate;
    }

    public Long getFeeAmount() {
        return feeAmount;
    }

    public void setFeeAmount(Long feeAmount) {
        this.feeAmount = feeAmount;
    }

    public Float getInterestRate() {
        return interestRate;
    }

    public void setInterestRate(Float interestRate) {
        this.interestRate = interestRate;
    }

    public Long getInterestAmount() {
        return interestAmount;
    }

    public void setInterestAmount(Long interestAmount) {
        this.interestAmount = interestAmount;
    }

    public Float getRiskcServiceRate() {
        return riskcServiceRate;
    }

    public void setRiskcServiceRate(Float riskcServiceRate) {
        this.riskcServiceRate = riskcServiceRate;
    }

    public Long getRiskcServiceAmount() {
        return riskcServiceAmount;
    }

    public void setRiskcServiceAmount(Long riskcServiceAmount) {
        this.riskcServiceAmount = riskcServiceAmount;
    }

    public Float getRiskReserveRate() {
        return riskReserveRate;
    }

    public void setRiskReserveRate(Float riskReserveRate) {
        this.riskReserveRate = riskReserveRate;
    }

    public Long getRiskReserveAmount() {
        return riskReserveAmount;
    }

    public void setRiskReserveAmount(Long riskReserveAmount) {
        this.riskReserveAmount = riskReserveAmount;
    }

    public Float getAfterloanServiceRate() {
        return afterloanServiceRate;
    }

    public void setAfterloanServiceRate(Float afterloanServiceRate) {
        this.afterloanServiceRate = afterloanServiceRate;
    }

    public Long getAfterloanServiceAmount() {
        return afterloanServiceAmount;
    }

    public void setAfterloanServiceAmount(Long afterloanServiceAmount) {
        this.afterloanServiceAmount = afterloanServiceAmount;
    }

    public Float getPlatformServiceRate() {
        return platformServiceRate;
    }

    public void setPlatformServiceRate(Float platformServiceRate) {
        this.platformServiceRate = platformServiceRate;
    }

    public Long getPlatformServiceAmount() {
        return platformServiceAmount;
    }

    public void setPlatformServiceAmount(Long platformServiceAmount) {
        this.platformServiceAmount = platformServiceAmount;
    }

    public String getCouponId() {
        return couponId;
    }

    public void setCouponId(String couponId) {
        this.couponId = couponId == null ? null : couponId.trim();
    }

    public Long getCouponAmount() {
        return couponAmount;
    }

    public void setCouponAmount(Long couponAmount) {
        this.couponAmount = couponAmount;
    }

    public Integer getLoanDay() {
        return loanDay;
    }

    public void setLoanDay(Integer loanDay) {
        this.loanDay = loanDay;
    }

    public Long getShouldRefundTime() {
        return shouldRefundTime;
    }

    public void setShouldRefundTime(Long shouldRefundTime) {
        this.shouldRefundTime = shouldRefundTime;
    }

    public Long getBankAmount() {
        return bankAmount;
    }

    public void setBankAmount(Long bankAmount) {
        this.bankAmount = bankAmount;
    }

    public Long getApplyTime() {
        return applyTime;
    }

    public void setApplyTime(Long applyTime) {
        this.applyTime = applyTime;
    }

    public String getBankCardNo() {
        return bankCardNo;
    }

    public void setBankCardNo(String bankCardNo) {
        this.bankCardNo = bankCardNo == null ? null : bankCardNo.trim();
    }

    public String getBankCode() {
        return bankCode;
    }

    public void setBankCode(String bankCode) {
        this.bankCode = bankCode == null ? null : bankCode.trim();
    }

    public String getBankName() {
        return bankName;
    }

    public void setBankName(String bankName) {
        this.bankName = bankName == null ? null : bankName.trim();
    }

    public Integer getBankDepositCity() {
        return bankDepositCity;
    }

    public void setBankDepositCity(Integer bankDepositCity) {
        this.bankDepositCity = bankDepositCity;
    }

    public String getBankReservedPhone() {
        return bankReservedPhone;
    }

    public void setBankReservedPhone(String bankReservedPhone) {
        this.bankReservedPhone = bankReservedPhone == null ? null : bankReservedPhone.trim();
    }

    public Byte getLoanStatus() {
        return loanStatus;
    }

    public void setLoanStatus(Byte loanStatus) {
        this.loanStatus = loanStatus;
    }

    public Byte getReviewStatus() {
        return reviewStatus;
    }

    public void setReviewStatus(Byte reviewStatus) {
        this.reviewStatus = reviewStatus;
    }

    public Long getReviewTime() {
        return reviewTime;
    }

    public void setReviewTime(Long reviewTime) {
        this.reviewTime = reviewTime;
    }

    public Byte getReviewFaildType() {
        return reviewFaildType;
    }

    public void setReviewFaildType(Byte reviewFaildType) {
        this.reviewFaildType = reviewFaildType;
    }

    public String getReviewFaildReason() {
        return reviewFaildReason;
    }

    public void setReviewFaildReason(String reviewFaildReason) {
        this.reviewFaildReason = reviewFaildReason == null ? null : reviewFaildReason.trim();
    }

    public String getReviewAdminId() {
        return reviewAdminId;
    }

    public void setReviewAdminId(String reviewAdminId) {
        this.reviewAdminId = reviewAdminId == null ? null : reviewAdminId.trim();
    }

    public Byte getBankStatus() {
        return bankStatus;
    }

    public void setBankStatus(Byte bankStatus) {
        this.bankStatus = bankStatus;
    }

    public Long getBankTime() {
        return bankTime;
    }

    public void setBankTime(Long bankTime) {
        this.bankTime = bankTime;
    }

    public Byte getRefundStatus() {
        return refundStatus;
    }

    public void setRefundStatus(Byte refundStatus) {
        this.refundStatus = refundStatus;
    }

    public Long getRefundTime() {
        return refundTime;
    }

    public void setRefundTime(Long refundTime) {
        this.refundTime = refundTime;
    }

    public Long getRefundAmount() {
        return refundAmount;
    }

    public void setRefundAmount(Long refundAmount) {
        this.refundAmount = refundAmount;
    }

    public Long getRefundPrincipalAmount() {
        return refundPrincipalAmount;
    }

    public void setRefundPrincipalAmount(Long refundPrincipalAmount) {
        this.refundPrincipalAmount = refundPrincipalAmount;
    }

    public Long getRefundOverdueAmount() {
        return refundOverdueAmount;
    }

    public void setRefundOverdueAmount(Long refundOverdueAmount) {
        this.refundOverdueAmount = refundOverdueAmount;
    }

    public String getRefundFaildReason() {
        return refundFaildReason;
    }

    public void setRefundFaildReason(String refundFaildReason) {
        this.refundFaildReason = refundFaildReason == null ? null : refundFaildReason.trim();
    }

    public Integer getOverdueDay() {
        return overdueDay;
    }

    public void setOverdueDay(Integer overdueDay) {
        this.overdueDay = overdueDay;
    }

    public Long getOverdueAmount() {
        return overdueAmount;
    }

    public void setOverdueAmount(Long overdueAmount) {
        this.overdueAmount = overdueAmount;
    }

    public String getSafeKey() {
        return safeKey;
    }

    public void setSafeKey(String safeKey) {
        this.safeKey = safeKey == null ? null : safeKey.trim();
    }

    public Integer getDataVersion() {
        return dataVersion;
    }

    public void setDataVersion(Integer dataVersion) {
        this.dataVersion = dataVersion;
    }

    public String getExtData() {
        return extData;
    }

    public void setExtData(String extData) {
        this.extData = extData == null ? null : extData.trim();
    }

    public String getRegisterChannel() {
        return registerChannel;
    }

    public void setRegisterChannel(String registerChannel) {
        this.registerChannel = registerChannel == null ? null : registerChannel.trim();
    }

    public Byte getClientType() {
        return clientType;
    }

    public void setClientType(Byte clientType) {
        this.clientType = clientType;
    }

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip == null ? null : ip.trim();
    }

    public String getDeviceId() {
        return deviceId;
    }

    public void setDeviceId(String deviceId) {
        this.deviceId = deviceId == null ? null : deviceId.trim();
    }

    public Byte getPlatform() {
        return platform;
    }

    public void setPlatform(Byte platform) {
        this.platform = platform;
    }

    public String getAppVersion() {
        return appVersion;
    }

    public void setAppVersion(String appVersion) {
        this.appVersion = appVersion == null ? null : appVersion.trim();
    }

    public String getLoginPhone() {
        return loginPhone;
    }

    public void setLoginPhone(String loginPhone) {
        this.loginPhone = loginPhone == null ? null : loginPhone.trim();
    }

    public Integer getLoanCount() {
        return loanCount;
    }

    public void setLoanCount(Integer loanCount) {
        this.loanCount = loanCount;
    }

    public Integer getLoanSuccessCount() {
        return loanSuccessCount;
    }

    public void setLoanSuccessCount(Integer loanSuccessCount) {
        this.loanSuccessCount = loanSuccessCount;
    }
}