package competition.controller;

import competition.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by Administrator on 2017/8/7/007.
 */
@Controller
@RequestMapping("user")
public class UserController {
    @Resource(name = "userService")
    private UserService userService;


    /**
     * 001.账户注册
     * @param username
     * @param password
     * @param name
     * @return
     */
    @RequestMapping(value = "enroll",method = RequestMethod.POST)
    @ResponseBody
    public Map enroll(@RequestParam String username,@RequestParam String password,@RequestParam String name){
        Map map;
        map=userService.enroll(username, password, name);
        return map;
    }

    /**
     * 002.账户登录
     * @param username
     * @param password
     * @return
     */
    @RequestMapping(value = "login",method = RequestMethod.POST)
    @ResponseBody
    public Map login(@RequestParam String username,@RequestParam String password){
        Map map;
        map=userService.login(username, password);
        return map;
    }

    /**
     * 003.设置新密码
     * @param username
     * @param password
     * @param newPassword
     * @return
     */
    @RequestMapping(value = "modify",method = RequestMethod.POST)
    @ResponseBody
    public Map modify(@RequestParam String username,@RequestParam String password,@RequestParam String newPassword){
        Map map;
        map=userService.login(username, password);
        if(map.get("status").equals("200")){
            //账号,密码正确,设置新密码
            return userService.modify(username, newPassword);
        }else{
            return map;
        }

    }

    /**
     * 004.查看用户交易总次数
     * @param username
     * @return
     */
    @RequestMapping(value = "consumeTimes",method = RequestMethod.POST)
    @ResponseBody
    public Map consumeTimes(@RequestParam String username){
        return userService.consumeTimes(username);
    }

    /**
     * 005.返回个人信息
     * @param username
     * @return
     */
    @RequestMapping(value = "personMessage",method = RequestMethod.POST)
    @ResponseBody
    public Map personMessage(@RequestParam String username){
        return userService.personMessage(username);
    }

    /**
     * 006.查询用户已发布过的交易以及发布交易次数
     * @param username
     * @return
     */
    @RequestMapping(value = "queryHadPublishTrade",method = RequestMethod.POST)
    @ResponseBody
    public Map queryHadPublishTrade(@RequestParam String username){
        return userService.queryHadPublishTrade(username);
    }

    @RequestMapping(value = "uploadIcon",method = RequestMethod.POST)
    @ResponseBody
    public Map uploadIcon(@RequestParam MultipartFile img, HttpServletRequest request,@RequestParam String username){

        return userService.uploadIcon(img, request,username);
    }
































}
