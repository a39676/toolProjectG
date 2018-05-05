package job_test.orange.mapper;

import job_test.orange.domain.RecoverAssign;

public interface RecoverAssignMapper {
    int insert(RecoverAssign record);

    int insertSelective(RecoverAssign record);
}