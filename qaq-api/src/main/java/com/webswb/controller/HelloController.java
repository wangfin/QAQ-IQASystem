package com.webswb.controller;

import com.webswb.api.CommonResult;
import io.swagger.annotations.Api;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author wangfin
 * @date 2023/9/12
 * @desc HelloController
 */
@Api(value = "Hello", tags = {"测试接口用例"})
@RestController
@RequestMapping("/hello")
public class HelloController {

    @GetMapping("/helloQaq")
    public CommonResult<String> helloQaq() {
        String res = "Hello QAQ~";
        return CommonResult.success(res);
    }
}
