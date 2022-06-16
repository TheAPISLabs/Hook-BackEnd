package com.yike.apis.service.impl;

import cn.dev33.satoken.stp.SaLoginModel;
import cn.dev33.satoken.stp.StpUtil;
import cn.hutool.core.util.CharsetUtil;
import cn.hutool.core.util.IdUtil;
import cn.hutool.core.util.ObjectUtil;
import cn.hutool.crypto.SecureUtil;
import cn.hutool.crypto.symmetric.SymmetricAlgorithm;
import cn.hutool.crypto.symmetric.SymmetricCrypto;
import cn.hutool.extra.mail.MailUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.yike.apis.dao.UserDao;
import com.yike.apis.pojo.user.User;
import com.yike.apis.pojo.user.vo.UserVo;
import com.yike.apis.service.EmailService;
import com.yike.apis.service.UserService;
import com.yike.apis.utils.IpHelper;
import com.yike.apis.utils.reponseUtil.ResponseData;
import com.yike.apis.utils.reponseUtil.ResponseDataUtil;
import com.yike.apis.utils.reponseUtil.ResultEnums;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.util.Arrays;
import java.util.concurrent.TimeUnit;

@Service
public class UserServiceImpl implements UserService {

    private final static String pwd_salt = "5c1a70b7a5e34296ac1579da6f2b5d83";
    private final static byte[] key = {-67, -118, -32, -84, -86, 97, -76, 100, 18, -59, -35, 25, -47, 119, 47, -58};

    @Value("${forgotPasswordLink}")
    private String forgotPasswordLink;

    @Autowired
    private UserDao userDao;

    @Autowired
    private EmailService emailService;

    @Autowired
    private RedisTemplate redisTemplate;

    @Override
    public ResponseData register(UserVo userVo, HttpServletRequest request) {
        QueryWrapper<User> wrapper = new QueryWrapper<>();
        wrapper.eq("email",userVo.getEmail());
        User user = userDao.selectOne(wrapper);
        if(ObjectUtil.isNotEmpty(user)){
            return ResponseDataUtil.buildError("Email to repeat");
        }
        if(ObjectUtil.isEmpty(userVo.getUserName())){
            return ResponseDataUtil.buildError("UserName don't empty");
        }
        if(ObjectUtil.isEmpty(userVo.getEmail())){
            return ResponseDataUtil.buildError("Email don't empty");
        }
        if(ObjectUtil.isEmpty(userVo.getPassword())){
            return ResponseDataUtil.buildError("Password don't empty");
        }
        if(ObjectUtil.isEmpty(userVo.getCode())){
            return ResponseDataUtil.buildError("Code don't empty");
        }
        Integer i = emailService.verifyCode(userVo.getEmail(),userVo.getCode());
        if(i != 1){
            return ResponseDataUtil.buildError("Verification code error");
        }
        User user1 = new User();
        String ip = IpHelper.getIpAddr(request);
        user1.setIcon("https://storage.googleapis.com/bimboss/hook_game_img/duitang.webp");
        user1.setUId(IdUtil.simpleUUID());
        user1.setLastLoginTime(System.currentTimeMillis());
        user1.setAddress(userVo.getAddress());
        user1.setUserName(userVo.getUserName());
        user1.setEmail(userVo.getEmail());
        user1.setLastLoginIp(ip);
        user1.setPassword(SecureUtil.md5(userVo.getPassword() + pwd_salt));
        user1.setVip(0);
        Integer j = userDao.insert(user1);
        if(j < 1){
            return ResponseDataUtil.buildError("ERROR");
        }
        return ResponseDataUtil.buildSuccess(ResultEnums.SUCCESS);
    }

    @Override
    public ResponseData updataUser(User user) {
        if(ObjectUtil.isNotEmpty(user.getUId())){
            return ResponseDataUtil.buildError("UId do not empty");
        }
        if(ObjectUtil.isNotEmpty(user.getAddress())){
            return ResponseDataUtil.buildError("Address do not modify the");
        }
        if(ObjectUtil.isNotEmpty(user.getEmail())){
            return ResponseDataUtil.buildError("Email do not modify the");
        }
        if(ObjectUtil.isNotEmpty(user.getPassword())){
            return ResponseDataUtil.buildError("Use the official interface to change the password");
        }
        if(ObjectUtil.isNotEmpty(user.getLastLoginIp())){
            return ResponseDataUtil.buildError("LastLoginIp do not modify the");
        }
        if(ObjectUtil.isNotEmpty(user.getLastLoginTime())){
            return ResponseDataUtil.buildError("LastLoginTime do not modify the");
        }
        User user1 = userDao.selectById(user.getUId());
        if(ObjectUtil.isNotEmpty(user1)){
            return ResponseDataUtil.buildError("The user does not exist");
        }
        Integer i = userDao.updateById(user);
        if(i < 1){
            return ResponseDataUtil.buildError("ERROR");
        }
        return ResponseDataUtil.buildSuccess("SUCCESS");
    }

    @Override
    public ResponseData updataLoginPwdByPwd(UserVo userVo) {
        User user = userDao.selectById(userVo.getUId());
        if(ObjectUtil.isNotEmpty(user)){
            return ResponseDataUtil.buildError("The user does not exist");
        }
        if(ObjectUtil.isEmpty(userVo.getPasswordNew())){
            return ResponseDataUtil.buildError("The new password cannot be empty");
        }
        if(userVo.getPassword().equalsIgnoreCase(userVo.getPasswordNew())){
            return ResponseDataUtil.buildError("The old and new passwords must be different");
        }
        if(userVo.getPassword().equals(user.getPassword())){
            return ResponseDataUtil.buildError("Password mistake");
        }
        user.setPassword(SecureUtil.md5(userVo.getPasswordNew() + pwd_salt));
        Integer i = userDao.updateById(user);
        if(i < 1){
            return ResponseDataUtil.buildError("ERROR");
        }
        return ResponseDataUtil.buildSuccess("SUCCESS");
    }

    @Override
    public ResponseData updataLoginPwdByCode(UserVo userVo) {
        User user = userDao.selectById(userVo.getUId());
        if(ObjectUtil.isNotEmpty(user)){
            return ResponseDataUtil.buildError("The user does not exist");
        }
        if(ObjectUtil.isEmpty(userVo.getPasswordNew())){
            return ResponseDataUtil.buildError("The new password cannot be empty");
        }
        if(userVo.getPassword().equalsIgnoreCase(userVo.getPasswordNew())){
            return ResponseDataUtil.buildError("The old and new passwords must be different");
        }
        Integer i = emailService.verifyCode(user.getEmail(),userVo.getCode());
        if(i != 1){
            return ResponseDataUtil.buildError("Verification code error");
        }
        user.setPassword(SecureUtil.md5(userVo.getPasswordNew() + pwd_salt));
        Integer j = userDao.updateById(user);
        if(j < 1){
            return ResponseDataUtil.buildError("ERROR");
        }
        return ResponseDataUtil.buildSuccess("SUCCESS");
    }

    @Override
    public ResponseData setAddress(UserVo userVo) {
        User user = userDao.selectById(userVo.getUId());
        if(ObjectUtil.isNotEmpty(user)){
            return ResponseDataUtil.buildError("The user does not exist");
        }
        if(ObjectUtil.isEmpty(userVo.getAddress())){
            return ResponseDataUtil.buildError("The new address cannot be empty");
        }
        if(ObjectUtil.isNotEmpty(user.getAddress()) || !user.getAddress().equals("")){
            return ResponseDataUtil.buildError("Duplicate bound address");
        }
        if(user.getAddress().equalsIgnoreCase(userVo.getAddress())){
            return ResponseDataUtil.buildError("Different addresses");
        }
        user.setAddress(userVo.getAddress());
        Integer j = userDao.updateById(user);
        if(j < 1){
            return ResponseDataUtil.buildError("ERROR");
        }
        return ResponseDataUtil.buildSuccess("SUCCESS");
    }

    @Override
    public ResponseData getUser(String email) {
        QueryWrapper<User> wrapper = new QueryWrapper<>();
        wrapper.eq("email",email);
        User user = userDao.selectOne(wrapper);
        if(ObjectUtil.isEmpty(user)){
            return ResponseDataUtil.buildError("The user does not exist");
        }
        return ResponseDataUtil.buildSuccess(user);
    }

    @Override
    public ResponseData loginByPwd(UserVo userVo, HttpServletRequest request) {
        if(ObjectUtil.isEmpty(userVo.getEmail()) || ObjectUtil.isEmpty(userVo.getPassword())){
            return ResponseDataUtil.buildError("The user name or password is abnormal");
        }
        QueryWrapper<User> wrapper = new QueryWrapper<>();
        wrapper.eq("email",userVo.getEmail());
        User user = userDao.selectOne(wrapper);
        if(ObjectUtil.isEmpty(user)){
            return ResponseDataUtil.buildError("User not registered");
        }
        String pwd = SecureUtil.md5(userVo.getPassword() + pwd_salt);
        if(!user.getPassword().equals(pwd)){
            return ResponseDataUtil.buildError("The user name or password is abnormal");
        }
        StpUtil.login(user.getUId(), new SaLoginModel()
                .setIsLastingCookie(true)        // Whether it is a persistent Cookie (temporary cookies are automatically deleted when the browser closes, and persistent cookies remain after re-opening)
        );
        String ip = IpHelper.getIpAddr(request);
        user.setLastLoginIp(ip);
        userDao.updateById(user);
        String token = StpUtil.getTokenValue();
        return ResponseDataUtil.buildSuccess(ResultEnums.SUCCESS.getCode(),ResultEnums.SUCCESS.getMsg(),token);
    }

    @Override
    public ResponseData loginByCode(UserVo userVo, HttpServletRequest request) {
        if(ObjectUtil.isEmpty(userVo.getEmail()) || ObjectUtil.isEmpty(userVo.getCode())){
            return ResponseDataUtil.buildError("The user name or code is abnormal");
        }
        QueryWrapper<User> wrapper = new QueryWrapper<>();
        wrapper.eq("email",userVo.getEmail());
        User user = userDao.selectOne(wrapper);
        if(ObjectUtil.isEmpty(user)){
            return ResponseDataUtil.buildError("User not registered");
        }
        Integer i = emailService.verifyCode(userVo.getEmail(),userVo.getCode());
        if(i != 1){
            return ResponseDataUtil.buildError("The user name or code is abnormal");
        }
        StpUtil.login(user.getUId(), new SaLoginModel()
                .setIsLastingCookie(true)        // Whether it is a persistent Cookie (temporary cookies are automatically deleted when the browser closes, and persistent cookies remain after re-opening)
        );
        String ip = IpHelper.getIpAddr(request);
        user.setLastLoginIp(ip);
        userDao.updateById(user);
        String token = StpUtil.getTokenValue();
        return ResponseDataUtil.buildSuccess(ResultEnums.SUCCESS.getCode(),ResultEnums.SUCCESS.getMsg(),token);
    }

    @Override
    public ResponseData logout(UserVo userVo) {
        User user = userDao.selectById(userVo.getUId());
        if(ObjectUtil.isEmpty(user)){
            return ResponseDataUtil.buildError("Abnormal number");
        }
        StpUtil.logout(user.getUId());
        return ResponseDataUtil.buildSuccess("SUCCESS");
    }

    @Override
    public ResponseData isLogin() {
        Boolean flang = StpUtil.isLogin();
        return ResponseDataUtil.buildSuccess(ResultEnums.SUCCESS.getCode(),ResultEnums.SUCCESS.getMsg(),flang);
    }

    @Override
    public ResponseData forgotPassword(UserVo userVo) {
        QueryWrapper<User> wrapper = new QueryWrapper<>();
        wrapper.eq("email",userVo.getEmail());
        User user = userDao.selectOne(wrapper);
        if(ObjectUtil.isEmpty(user)){
            return ResponseDataUtil.buildError("No user");
        }
        //构建
        SymmetricCrypto aes = new SymmetricCrypto(SymmetricAlgorithm.AES, key);
        String url = forgotPasswordLink+"/"+aes.encryptHex(user.getUId());
        String html = "<!DOCTYPE html>\n" +
                "<html lang=\"en\">\n" +
                "  <head>\n" +
                "    <meta charset=\"UTF-8\" />\n" +
                "    <meta http-equiv=\"X-UA-Compatible\" content=\"IE=edge\" />\n" +
                "    <meta name=\"viewport\" content=\"width=device-width, initial-scale=1.0\" />\n" +
                "    <title>Document</title>\n" +
                "  </head>\n" +
                "\n" +
                "  <body>\n" +
                "    <div>\n" +
                "      <includetail>\n" +
                "        <div style=\"background-color:#E5E5E5 ; padding-top: 96px; padding-bottom: 96px;\">\n" +
                "          <div\n" +
                "            style=\"\n" +
                "              background-color: #fff;\n" +
                "              margin: auto;\n" +
                "              border-radius: 17px;\n" +
                "              \n" +
                "              width: 923px;\n" +
                "              height: 722px;\n" +
                "            \"\n" +
                "          >\n" +
                "            <div style=\"text-align: center;\">\n" +
                "              <img\n" +
                "                style=\"width: 101px; height: 101px;\n" +
                "                margin-top: 61px;\n" +
                "            \"\n" +
                "                src=\"https://storage.googleapis.com/bimboss/hook_game_img/457b77d60e431c8b5fca797fc10702c.png\"\n" +
                "                alt=\"\"\n" +
                "              />\n" +
                "            </div>\n" +
                "            <div\n" +
                "              style=\"\n" +
                "                font-style: normal;\n" +
                "                font-weight: 700;\n" +
                "                font-size: 42px;\n" +
                "                line-height: 51px;\n" +
                "                text-align: center;\n" +
                "                letter-spacing: -0.84px;\n" +
                "                color: #010101;\n" +
                "                margin-top: 50px;\n" +
                "                margin-bottom: 50px;\n" +
                "              \"\n" +
                "            >\n" +
                "              Password Reset\n" +
                "            </div>\n" +
                "            <div\n" +
                "              style=\"\n" +
                "                width: 600px;\n" +
                "                font-style: normal;\n" +
                "                font-weight: 400;\n" +
                "                font-size: 24px;\n" +
                "                line-height: 29px;\n" +
                "                text-align: center;\n" +
                "                letter-spacing: -0.48px;\n" +
                "                color: #010101;\n" +
                "                margin: auto;\n" +
                "\n" +
                "              \"\n" +
                "            >\n" +
                "              If you've lost your password or wish to reset it,use the link below to get started\n" +
                "            </div>\n" +
                "            <div\n" +
                "              style=\"\n" +
                "                width: 200px;\n" +
                "                height: 50px;\n" +
                "                background: #7551ff;\n" +
                "                border-radius: 10px;\n" +
                "                margin: auto;\n" +
                "                margin-top: 30px;\n" +
                "              \"\n" +
                "            >\n" +
                "              <p\n" +
                "                style=\"\n" +
                "                  font-style: normal;\n" +
                "                  font-weight: 400;\n" +
                "                  font-size: 15px;\n" +
                "                  line-height: 50px;\n" +
                "                  text-align: center;\n" +
                "                  letter-spacing: -0.48px;\n" +
                "                  color: #7551ff;\n" +
                "                \"\n" +
                "              >\n" +
                "                <a\n" +
                "                  href=\"\n" +
                                    url+
                "                  \"\n" +
                "                  style=\"display: block;\n" +
                "                  width: 80%;\n" +
                "                  text-decoration: none;\n" +
                "                  height: 100%;\n" +
                "                  margin-left: 10%;\n" +
                "                  font-weight: 800;\n" +
                "                  color: white;\n" +
                "                  overflow:hidden;\n" +
                "                  \"\n" +
                "                  >\n" +
                "                    Reset Your Password\n" +
                "                  </a>\n" +
                "              </p>\n" +
                "            </div>\n" +
                "            <div\n" +
                "              style=\"\n" +
                "                font-style: normal;\n" +
                "                font-weight: 400;\n" +
                "                font-size: 18px;\n" +
                "                line-height: 29px;\n" +
                "                text-align: center;\n" +
                "                letter-spacing: -0.48px;\n" +
                "                color: #00000091;\n" +
                "                width: 92%;\n" +
                "                margin: auto;\n" +
                "                margin-top: 50px;\n" +
                "              \"\n" +
                "            >\n" +
                "              If you did not requset a password reset,you can safely ignore this wmail. Only a person with access to ypur email can reset your account password.\n" +
                "            </div>\n" +
                "            <div\n" +
                "              style=\"\n" +
                "                font-weight: 400;\n" +
                "                font-size: 24px;\n" +
                "                line-height: 29px;\n" +
                "                text-align: center;\n" +
                "                letter-spacing: -0.48px;\n" +
                "                color: #4112d1;\n" +
                "                margin-top: 50px;\n" +
                "              \"\n" +
                "            >\n" +
                "              <a href=\"https://www.theapis.xyz/contact\">Contact us </a>\n" +
                "            </div>\n" +
                "          </div>\n" +
                "          <div\n" +
                "            style=\"\n" +
                "              margin: auto;\n" +
                "              margin-top: 57px;\n" +
                "              width: 1062px;\n" +
                "              display: flex;\n" +
                "              justify-content: space-around;\n" +
                "              \n" +
                "            \"\n" +
                "          >\n" +
                "            <div\n" +
                "              style=\"\n" +
                "                font-style: normal;\n" +
                "                font-weight: 400;\n" +
                "                font-size: 24px;\n" +
                "                line-height: 29px;\n" +
                "                text-align: center;\n" +
                "                letter-spacing: -0.48px;\n" +
                "                color: #010101;\n" +
                "              \"\n" +
                "            >\n" +
                "              @ copyright 2022 Hook\n" +
                "            </div>\n" +
                "            <div style=\"color: #aaaaaa; font-size: 24px\">|</div>\n" +
                "            <div\n" +
                "              style=\"\n" +
                "                font-style: normal;\n" +
                "                font-weight: 400;\n" +
                "                font-size: 24px;\n" +
                "                line-height: 29px;\n" +
                "                text-align: center;\n" +
                "                letter-spacing: -0.48px;\n" +
                "                color: #4112d1;\n" +
                "              \"\n" +
                "            >\n" +
                "              <a href=\"https://docs.theapis.xyz/readme/docs/terms-and-conditions\">terms of service</a>\n" +
                "            </div>\n" +
                "            <div style=\"color: #aaaaaa; font-size: 24px\">|</div>\n" +
                "            <div\n" +
                "              style=\"\n" +
                "                font-style: normal;\n" +
                "                font-weight: 400;\n" +
                "                font-size: 24px;\n" +
                "                line-height: 29px;\n" +
                "                text-align: center;\n" +
                "                letter-spacing: -0.48px;\n" +
                "                color: #4112d1;\n" +
                "              \"\n" +
                "            >\n" +
                "              <a href=\"https://docs.theapis.xyz/readme/docs/privacy-policy\">privacy policy.</a>\n" +
                "            </div>\n" +
                "            <div style=\"color: #aaaaaa; font-size: 24px\">|</div>\n" +
                "            <div\n" +
                "              style=\"\n" +
                "                font-style: normal;\n" +
                "                font-weight: 400;\n" +
                "                font-size: 24px;\n" +
                "                line-height: 29px;\n" +
                "                text-align: center;\n" +
                "                letter-spacing: -0.48px;\n" +
                "                color: #4112d1;\n" +
                "              \"\n" +
                "            >\n" +
                "              <a href=\"https://www.hook.cool/\">about Hook</a>\n" +
                "            </div>\n" +
                "            <div style=\"color: #aaaaaa; font-size: 24px\">|</div>\n" +
                "            <div\n" +
                "              style=\"\n" +
                "                font-style: normal;\n" +
                "                font-weight: 400;\n" +
                "                font-size: 24px;\n" +
                "                line-height: 29px;\n" +
                "                text-align: center;\n" +
                "                letter-spacing: -0.48px;\n" +
                "                color: #4112d1;\n" +
                "              \"\n" +
                "            >\n" +
                "              <a href=\"\">support</a>\n" +
                "            </div>\n" +
                "          </div>\n" +
                "        </div>\n" +
                "      </includetail>\n" +
                "    </div>\n" +
                "  </body>\n" +
                "</html>\n";
        String b = MailUtil.send(userVo.getEmail(),
                "APIS",
                html,
                true);
        return ResponseDataUtil.buildSuccess();
    }

    @Override
    public ResponseData resetPassword(UserVo userVo) {
        SymmetricCrypto aes = new SymmetricCrypto(SymmetricAlgorithm.AES, key);
        //解密为字符串
        String decryptStr = aes.decryptStr(userVo.getUId(), CharsetUtil.CHARSET_UTF_8);
        User user = userDao.selectById(decryptStr);
        if(ObjectUtil.isEmpty(user)){
            return ResponseDataUtil.buildError("No user");
        }
        user.setPassword(SecureUtil.md5(userVo.getPassword() + pwd_salt));
        Integer i = userDao.updateById(user);
        if(i != 1){
            return ResponseDataUtil.buildError();
        }
        return ResponseDataUtil.buildSuccess();
    }
}
