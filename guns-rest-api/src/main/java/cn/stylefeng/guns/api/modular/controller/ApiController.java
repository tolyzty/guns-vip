/**
 * Copyright 2018-2020 stylefeng & fengshuonan (https://gitee.com/stylefeng)
 * <p>
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * <p>
 * http://www.apache.org/licenses/LICENSE-2.0
 * <p>
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package cn.stylefeng.guns.api.modular.controller;


import cn.stylefeng.guns.api.core.util.JwtTokenUtil;
import cn.stylefeng.guns.base.shiro.ShiroUser;
import cn.stylefeng.roses.core.base.controller.BaseController;
import cn.stylefeng.roses.core.reqres.response.ErrorResponseData;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authc.credential.HashedCredentialsMatcher;
import org.apache.shiro.crypto.hash.Md5Hash;
import org.apache.shiro.util.ByteSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;

/**
 * 接口控制器提供
 * <p>
 * 所有的api都要以gunsApi开头，可以在cn.stylefeng.guns.config.web.WebConfig中配置这个开头
 *
 * @author stylefeng
 * @Date 2018/7/20 23:39
 */
@RestController
@RequestMapping("/gunsApi")
public class ApiController extends BaseController {

    /**
     * 测试接口是否走鉴权
     */
    @RequestMapping(value = "/test", method = RequestMethod.GET)
    public Object test() {
        return SUCCESS_TIP;
    }


   /* *//**
     * api登录接口，通过账号密码获取token
     * @param username
     * @param password
     * @return
     *//*
    @RequestMapping("/auth")
    public Object auth(@RequestParam("username") String username,
                       @RequestParam("password") String password) {

        //封装请求账号密码为shiro可验证的token
        UsernamePasswordToken usernamePasswordToken = new UsernamePasswordToken(username, password.toCharArray());

        //获取数据库中的账号密码，准备比对
        User user = userMapper.getByAccount(username);

        String credentials = user.getPassword();
        String salt = user.getSalt();
        ByteSource credentialsSalt = new Md5Hash(salt);
        SimpleAuthenticationInfo simpleAuthenticationInfo = new SimpleAuthenticationInfo(
                new ShiroUser(), credentials, credentialsSalt, "");

        //校验用户账号密码
        HashedCredentialsMatcher md5CredentialsMatcher = new HashedCredentialsMatcher();
        md5CredentialsMatcher.setHashAlgorithmName(ShiroKit.hashAlgorithmName);
        md5CredentialsMatcher.setHashIterations(ShiroKit.hashIterations);
        boolean passwordTrueFlag = md5CredentialsMatcher.doCredentialsMatch(
                usernamePasswordToken, simpleAuthenticationInfo);

        if (passwordTrueFlag) {
            HashMap<String, Object> result = new HashMap<>();
            result.put("token", JwtTokenUtil.generateToken(String.valueOf(user.getUserId())));
            return result;
        } else {
            return new ErrorResponseData(500, "账号密码错误！");
        }
    }
*/
}

