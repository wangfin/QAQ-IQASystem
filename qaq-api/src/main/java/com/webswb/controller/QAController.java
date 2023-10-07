package com.webswb.controller;

import com.webswb.api.CommonResult;
import com.webswb.service.QAService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author wangfin
 * @date 2023/9/18
 * @desc 用户问答模块
 */
@Api(value = "qa", tags = {"用户问答模块"})
@RestController
@RequestMapping("/qa")
public class QAController {

    @Autowired
    private QAService qaService;

    /**
     * 用户提问回答模块，包含功能
     * 1. 输入提示，在输入的过程中提示有关问题
     * 2. 输入问句，进行回答
     * 3. 回答完成后提供相似问句
     * 4. 记录提问内容
     * 5. 展示提问次数最多的问题
     */

    /**
     * 用户补完查询
     * 当用户开始输入字的时候，调用补完查询，返回查询的准确问题
     * @param question 用户输入的精确问句
     * @return 答案
     */
    @ApiOperation(value = "用户注册", notes = "用户注册", httpMethod = "POST")
    @PostMapping("/completionQuery")
    public CommonResult completionQuery(@RequestParam String question) {
        return qaService.completionQuery(question);
    }


    /**
     * 用户点击的发送按钮的查询
     * 返回的结果分为两种，第一种是当某一个答案的评分超过其他几个答案的评分，那么就返回一个
     * 如果评分相同，那么就返回五个
     * @param question 用户输入的精确问句
     * @return 答案
     */
    @ApiOperation(value = "用户注册", notes = "用户注册", httpMethod = "POST")
    @PostMapping("/enterQuery")
    public CommonResult enterQuery(@RequestParam String question) {
        return qaService.enterQuery(question);
    }




}
