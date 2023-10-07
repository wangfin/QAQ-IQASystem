package com.webswb.service;

import com.webswb.api.CommonResult;

/**
 * @author wangfin
 * @date 2023/9/18
 * @desc 用户问答模块
 */

public interface QAService {

    CommonResult completionQuery(String question);

    CommonResult enterQuery(String question);
}
