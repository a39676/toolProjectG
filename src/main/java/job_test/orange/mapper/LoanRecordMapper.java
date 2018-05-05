package job_test.orange.mapper;

import job_test.orange.domain.LoanRecord;

public interface LoanRecordMapper {
    int insert(LoanRecord record);

	int insertSelective(LoanRecord record);

}