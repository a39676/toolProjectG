package job_test.orange.service.imp;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import job_test.orange.domain.LoanRecord;
import job_test.orange.mapper.LoanRecordCMapper;
import job_test.orange.service.ComplexService;

public class ComplexServiceImpl implements ComplexService{

	@Autowired
	LoanRecordCMapper loanRecordCMapper;
	
	@Override
	public List<LoanRecord> getLoanRecordByLoanId(String loanId) {
		return loanRecordCMapper.getLoanRecordByLoanId(loanId);
	}

}
