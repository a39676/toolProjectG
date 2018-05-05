package job_test.orange.service;

import java.util.List;

import job_test.orange.domain.LoanRecord;

public interface ComplexService {

	List<LoanRecord> getLoanRecordByLoanId(String loanId);
}
