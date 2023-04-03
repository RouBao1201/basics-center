package com.roubao.basics.testall.orm.mapper;


import com.roubao.basics.testall.orm.dto.DictInsRequestDTO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;


@Mapper
public interface DictMapper extends BatchSessionMapper {
    /**
     * 插入字典配置
     *
     * @param dictDto dictDto
     * @return int
     */
    int insertDict(@Param("dictDto") DictInsRequestDTO dictDto);

    int batchInsertDict(@Param("dictDtoList") List<DictInsRequestDTO> dictDtoList);
}
