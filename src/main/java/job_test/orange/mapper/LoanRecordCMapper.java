package job_test.orange.mapper;

import java.util.List;

import job_test.orange.domain.LoanRecord;

public interface LoanRecordCMapper extends LoanRecordMapper{

	List<LoanRecord> getLoanRecordByLoanId(String loanId);
}
