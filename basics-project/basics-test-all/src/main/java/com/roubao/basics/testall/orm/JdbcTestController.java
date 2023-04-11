package com.roubao.basics.testall.orm;

import java.util.ArrayList;

import com.roubao.basics.testall.orm.mapper.DictMapper;
import org.apache.ibatis.session.ExecutorType;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.roubao.orm.jdbc.bean.JdbcAdapter;

/**
 * @author SongYanBin
 * @copyright ©2023-2099 SongYanBin. All rights reserved.
 * @since 2023/3/29
 **/
@RestController
@RequestMapping("/jdbcTest")
public class JdbcTestController {

    @Autowired
    private DictMapper dictMapper;

    @Autowired
    private JdbcAdapter jdbcAdapter;

    @Autowired
    private SqlSessionFactory sqlSessionFactory;

    @PostMapping("/insertDict")
    @Transactional
    public String insertDict(@RequestBody DictInsRequestDTO dto) {
        int i = dictMapper.insertDict(dto);
        return "插入成功: " + i;
    }

    @PostMapping("/batchInsertDict")
    @Transactional
    public String batchInsertDict(@RequestBody DictBatchInsRequestDTO dto) {
        ArrayList<DictInsRequestDTO> insertList = new ArrayList<>();
        DictInsRequestDTO insDto;
        for (int i = 0; i < dto.getBatchNum(); i++) {
            insDto = new DictInsRequestDTO(String.valueOf(i), i + "XXX");
            insertList.add(insDto);
        }

        int i = 0;
        long startTime = System.currentTimeMillis();
        if (dto.getMode() == 0) {
            i = dictMapper.batchInsertDict(insertList);
        } else if (dto.getMode() == 1) {
            i = jdbcAdapter.batchInsert((list) -> {
                return dictMapper.batchInsertDict(list);
            }, insertList, dto.getSubmitNum());
        } else {
            SqlSession sqlSession = null;
            try {
                sqlSession = sqlSessionFactory.openSession(ExecutorType.BATCH, false);
                DictMapper mapper = sqlSession.getMapper(DictMapper.class);
                int commitSize = dto.getSubmitNum();
                for (int j = 0; j < insertList.size(); j++) {
                    mapper.insertDict(insertList.get(j));
                    if (j != 0 && j % commitSize == 0) {
                        sqlSession.commit();
                        sqlSession.clearCache();
                    }
                }
                sqlSession.commit();
                sqlSession.clearCache();
            } catch (Exception e) {
                if (sqlSession != null) {
                    sqlSession.rollback();
                }
            } finally {
                if (sqlSession != null) {
                    sqlSession.close();
                }
            }
        }
        long endTime = System.currentTimeMillis();
        return "批量插入成功: " + i + ", 消耗时间: " + (endTime - startTime) + "ms";
    }
}
