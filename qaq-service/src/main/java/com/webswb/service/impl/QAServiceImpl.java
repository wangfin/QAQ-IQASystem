package com.webswb.service.impl;

import com.webswb.api.CommonResult;
import com.webswb.service.QAService;
import org.springframework.stereotype.Service;

/**
 * @author wangfin
 * @date 2023/9/18
 * @desc 用户问答模块
 */
@Service
public class QAServiceImpl implements QAService {

    /**
     * 补全查询
     * 用户的补全查询都是精确的问句，所以直接去数据库中匹配即可
     */
    @Override
    public CommonResult completionQuery(String question) {
        
        return CommonResult.success();
    }

    @Override
    public CommonResult enterQuery(String question) {

        return CommonResult.success();
    }
}
