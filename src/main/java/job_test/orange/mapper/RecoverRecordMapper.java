package job_test.orange.mapper;

import job_test.orange.domain.RecoverRecord;

public interface RecoverRecordMapper {
    int insert(RecoverRecord record);

    int insertSelective(RecoverRecord record);
}