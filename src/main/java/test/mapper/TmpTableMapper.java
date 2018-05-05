package test.mapper;

import test.domain.TmpTable;

public interface TmpTableMapper {
    int insert(TmpTable record);

    int insertSelective(TmpTable record);
}