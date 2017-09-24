package competition.controller;

import competition.po.TTradesPublishEntity;
import competition.service.TradeService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2017/8/11/011.
 */
@Controller
@RequestMapping("trade")
public class TradeController {
    @Resource(name = "tradeService")
    private TradeService tradeService;

    /**
     * 001.增加场次
     * @param screen
     * @return
     */
    @RequestMapping(value = "addScreen",method = RequestMethod.POST)
    @ResponseBody
    public Map addScreen(@RequestParam String screen){
        return tradeService.addScreen(screen);
    }

    /**
     * 002.列举所有场次
     * @return
     */
    @RequestMapping(value = "listScreen",method = RequestMethod.POST)
    @ResponseBody
    public List listScreen(){
        return tradeService.listScreen();
    }

//    /**
//     * 003.发布交易
//     * @return
//     */
//    @RequestMapping(value = "publishTrade",method = RequestMethod.POST)
//    @ResponseBody
//    public Map publishTrade(@RequestParam String screen,@RequestParam String username,@RequestParam String money,@RequestParam String message,@RequestParam String zhukehe,@RequestParam String banchangzhukehe,@RequestParam String rangqiuzhukehe,@RequestParam String rangqiu,@RequestParam String rangqiudaxi,@RequestParam String banchangrangqiudaxi,@RequestParam String jiaoqiudaxi,@RequestParam String bodan,@RequestParam String banchangbodan,@RequestParam String diyiqiuruqiu,@RequestParam String zongruqiu ,@RequestParam String ruqiudanshuang,@RequestParam String banquanchang ){
//        return tradeService.publishTrade(screen, username, money,message, zhukehe, banchangzhukehe, rangqiuzhukehe, rangqiu, rangqiudaxi, banchangrangqiudaxi, jiaoqiudaxi, bodan, banchangbodan, diyiqiuruqiu, zongruqiu, ruqiudanshuang, banquanchang);
//    }


    /**
     * 003.发布交易
     * @return
     */
    @RequestMapping(value = "publishTrade",method = RequestMethod.POST)
    @ResponseBody
    public Map publishTrade(@RequestBody TTradesPublishEntity tTradesPublishEntity){

        return tradeService.publishTrade(tTradesPublishEntity);
    }

    /**
     * 004.列出已发布交易
     * @return
     */
    @RequestMapping(value = "listTrade",method = RequestMethod.POST)
    @ResponseBody
    public Map listTrade(@RequestParam int page,@RequestParam int num){
        Map map=new HashMap();
        List list=tradeService.listTrade(page,num);
        map.put("length",list.size());
        map.put("result",list);

        return map ;
    }

    /**
     * 005.购买交易
     * @param username
     * @param trade_id
     * @return
     */
    @RequestMapping(value = "buyTrade",method = RequestMethod.POST)
    @ResponseBody
    public Map buyTrade(@RequestParam String username,@RequestParam int trade_id){
        return tradeService.buyTrade(username, trade_id);
    }

    /**
     * 006.查询传入用户的购买记录等
     * @return
     */
    @RequestMapping(value = "queryTradeRecord",method = RequestMethod.POST)
    @ResponseBody
    public Map queryTradeRecord(@RequestParam  String username){
        Map map=new HashMap();
        List list=tradeService.queryTradeRecord(username);
        map.put("result",list);
        map.put("length",list.size());
        return map;
    }

    /**
     * 007.查看个人消费情况（收入支出等）
     * @param username
     * @return
     */
    @RequestMapping(value = "queryPersonRecord",method = RequestMethod.POST)
    @ResponseBody
    public Map queryPersonRecord(@RequestParam String username){
        Map map=new HashMap();
        List list=tradeService.queryPersonRecord(username);
        map.put("result",list);
        map.put("length",list.size());
        return map;
    }

    /**
     * 008.查看已购买的交易详情
     * @param trade_id
     * @param user_id
     * @return
     */
    @RequestMapping(value = "queryBuyDetail",method = RequestMethod.POST)
    @ResponseBody
    public Map queryBuyDetail(@RequestParam int trade_id,@RequestParam int user_id){
        return tradeService.queryBuyDetail(trade_id, user_id);
    }


    /**
     * 交易评价（增加信誉）
     * @param trade_id
     * @param comment
     * @return
     */
    @RequestMapping(value = "tradeComment",method = RequestMethod.POST)
    @ResponseBody
    public Map tradeComment(@RequestParam int trade_id,@RequestParam String comment,@RequestParam String username){
        return tradeService.tradeComment(trade_id, comment,username);
//        return null;
    }


    @RequestMapping(value = "queryTradeById",method = RequestMethod.POST)
    @ResponseBody
    public Map queryTradeById(@RequestParam int user_id){
        Map map=tradeService.queryTradeById(user_id);
        return map;
    }



}
