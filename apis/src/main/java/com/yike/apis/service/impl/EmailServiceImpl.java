package com.yike.apis.service.impl;

import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.util.ObjectUtil;
import cn.hutool.extra.mail.MailAccount;
import cn.hutool.extra.mail.MailUtil;
import com.yike.apis.service.EmailService;
import com.yike.apis.utils.CodeUtil;
import com.yike.apis.utils.reponseUtil.ResponseData;
import com.yike.apis.utils.reponseUtil.ResponseDataUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.util.concurrent.TimeUnit;

@Service
public class EmailServiceImpl implements EmailService {

    @Autowired
    private RedisTemplate redisTemplate;

    @Override
    public ResponseData setEmailCode(String email) {
        //Generate captcha
        String code = CodeUtil.getCode();
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
                "              Verify your account\n" +
                "            </div>\n" +
                "            <div\n" +
                "              style=\"\n" +
                "                width: 322px;\n" +
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
                "              Here is your verification code for \"Sign up\"\n" +
                "            </div>\n" +
                "            <div\n" +
                "              style=\"\n" +
                "                width: 566px;\n" +
                "                height: 86px;\n" +
                "                background: #f1f2f6;\n" +
                "                border-radius: 10px;\n" +
                "                margin: auto;\n" +
                "                margin-top: 30px;\n" +
                "              \"\n" +
                "            >\n" +
                "              <p\n" +
                "                style=\"\n" +
                "                  font-style: normal;\n" +
                "                  font-weight: 400;\n" +
                "                  font-size: 40px;\n" +
                "                  line-height: 89px;\n" +
                "                  text-align: center;\n" +
                "                  letter-spacing: -0.48px;\n" +
                "                  color: #010101;\n" +
                "                \"\n" +
                "              >\n" +
                "                "+code+"\n" +
                "              </p>\n" +
                "            </div>\n" +
                "            <div\n" +
                "              style=\"\n" +
                "                font-style: normal;\n" +
                "                font-weight: 400;\n" +
                "                font-size: 24px;\n" +
                "                line-height: 29px;\n" +
                "                text-align: center;\n" +
                "                letter-spacing: -0.48px;\n" +
                "                color: #010101;\n" +
                "                margin-top: 50px;\n" +
                "              \"\n" +
                "            >\n" +
                "              Please enter this code to verify your identity and sign in.\n" +
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
        String b = MailUtil.send(email, "APIS", html,true);
        redisTemplate.opsForValue().set(email,code,5, TimeUnit.MINUTES);
        return ResponseDataUtil.buildSuccess();
    }

    @Override
    public ResponseData verifyEmailCode(String email, String code) {
        Integer i = verifyCode(email,code);
        if(i == 0){
            return ResponseDataUtil.buildError("Verification code error");
        }
        return ResponseDataUtil.buildSuccess();
    }

    @Override
    public Integer verifyCode(String email, String code){
        Object o = redisTemplate.opsForValue().get(email);
        if(ObjectUtil.isEmpty(o)){
            return 0;
        }else {
            String codeNew = o.toString();
            if(!code.equals(codeNew)){
                return 0;
            }
        }
        return 1;
    }

    public static void main(String[] args) {
        MailAccount account = new MailAccount();
        account.setHost("smtp.gmail.com");
        account.setPort(465);
        account.setAuth(true);
        account.setFrom("Derek Dev <0x28@theapis.xyz>");
        account.setUser("0x28@theapis.xyz");
        account.setPass("Family@12345");
        account.setSslEnable(true);
        String url = "https://hook.cool/#/auth/lock/default/618b4c5d2fc9de2ae220df203e7aae79a1ed0033bc5a869ee8a3dfa2e79213280097874d765ad6aa1f96195705a99d8f";
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
                "              Verify your account\n" +
                "            </div>\n" +
                "            <div\n" +
                "              style=\"\n" +
                "                width: 322px;\n" +
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
                "              Here is your verification code for \"Sign up\"\n" +
                "            </div>\n" +
                "            <div\n" +
                "              style=\"\n" +
                "                width: 566px;\n" +
                "                height: 86px;\n" +
                "                background: #f1f2f6;\n" +
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
                "                  line-height: 89px;\n" +
                "                  text-align: center;\n" +
                "                  letter-spacing: -0.48px;\n" +
                "                  color: #010101;\n" +
                "                \"\n" +
                "              >\n" +
                "                <a\n" +
                "                  href=\"\n" +
                url+
                "                  \"\n" +
                "                  style=\"display: block;\n" +
                "                  width: 80%;\n" +
                "                  height: 100%;\n" +
                "                  margin-left: 10%;\n" +
                "                  overflow:hidden;\n" +
                "                  \"\n" +
                "                  >\n" +
                url+
                "                  </a>\n" +
                "              </p>\n" +
                "            </div>\n" +
                "            <div\n" +
                "              style=\"\n" +
                "                font-style: normal;\n" +
                "                font-weight: 400;\n" +
                "                font-size: 24px;\n" +
                "                line-height: 29px;\n" +
                "                text-align: center;\n" +
                "                letter-spacing: -0.48px;\n" +
                "                color: #010101;\n" +
                "                margin-top: 50px;\n" +
                "              \"\n" +
                "            >\n" +
                "              Please enter this code to verify your identity and sign in.\n" +
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
        String oi = MailUtil.send(account, CollUtil.newArrayList("1137051645@qq.com","lp17608100258@163.com","liulplss@gmail.com","kangfaliao@gmail.com"), "APIS",
                html,
                true);
    }
}
