package com.webswb.controller;

import com.webswb.api.CommonResult;
import com.webswb.service.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author wangfin
 * @date 2023/9/18
 * @desc 用户模块
 */
@Api(value = "user", tags = {"用户模块"})
@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    /**
     * 检测用户名是否存在
     *
     * @param username 用户名
     */
    @ApiOperation(value = "用户名是否存在", notes = "用户名是否存在", httpMethod = "GET")
    @GetMapping("/usernameIsExist")
    public CommonResult<String> usernameIsExist(@RequestParam String username) {
        // @RequestParam 表示username是一种请求参数而不是请求路径
        // 这里使用的是apache commons-lang3中的StringUtils
        // 1. 判断用户名是否为空，或者空字符串
        if (StringUtils.isBlank(username)) {
            // 返回HTTP状态码，500
            return CommonResult.failed("用户名不能为空！");
        }

        // 2. 查找注册的用户名是否存在
        boolean isExist = userService.queryUsernameIsExist(username);
        if (isExist) {
            return CommonResult.failed("用户名已经存在！");
        }

        // 3. 请求成功，用户名没有重复
        return CommonResult.success();
    }

    /**
     * 用户注册
     *
     * @param userBO 信息BO
     * @param request request
     * @param response response
     * @return
     */
    @ApiOperation(value = "用户注册", notes = "用户注册", httpMethod = "POST")
    @PostMapping("/regist")
    public CommonResult regist(@RequestBody UserBO userBO,
                                  HttpServletRequest request,
                                  HttpServletResponse response) {
        String username = userBO.getUsername();
        String password = userBO.getPassword();
        String confirmPwd = userBO.getConfirmPassword();

        // 1. 判断用户名和密码必须不为空
        if (StringUtils.isBlank(username) ||
                StringUtils.isBlank(username) ||
                StringUtils.isBlank(username)) {
            return IMOOCJSONResult.errorMsg("用户名或密码不能为空");
        }
        // 2. 查询用户名是否存在
        boolean isExist = userService.queryUsernameIsExist(username);
        if (isExist) {
            return IMOOCJSONResult.errorMsg("用户名已经存在");
        }
        // 3. 密码长度不能少于6位
        if (password.length() < 6) {
            return IMOOCJSONResult.errorMsg("密码长度不能少于6");
        }
        // 4. 判断两次密码是否一致
        if (!password.equals(confirmPwd)) {
            return IMOOCJSONResult.errorMsg("两次密码输入不一致");
        }
        // 5. 实现注册
        Users userResult = userService.createUser(userBO);

        // 实现用户的redis会话
        UsersVo usersVo = conventUsersVo(userResult);

        // 在注册完成之后，同样设置cookie
        CookieUtils.setCookie(request, response, BaseController.USER_COOKIE,
                JsonUtils.objectToJson(usersVo), true);

        // 同步购物车数据
        syncShopCartData(userResult.getId(), request, response);

        return IMOOCJSONResult.ok();
    }




}
