package competition.controller;

import competition.BaseDao;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2017/8/19/019.
 */
@Controller
@RequestMapping("sort")
public class SortController extends BaseDao {

    //升序排列
    @RequestMapping(value = "sortAsc",method = RequestMethod.POST)
    @ResponseBody
    public Map sortAsc(@RequestParam String tableName,@RequestParam String filed,@RequestParam int page,@RequestParam int num){
        Session session=getCurrentSession();
        Transaction transaction=session.beginTransaction();
        String sqlQuery="from "+tableName+"  order by "+filed;
        Map map=new HashMap();
        try{
            List list=session.createQuery(sqlQuery).setFirstResult((page-1)*num).setMaxResults(num).list();

            map.put("result",list);
            map.put("length",list.size());
        }catch (Exception e){
            map.put("message","查询错误哦");
        }
        return map;
    }

    //降序排列
    @RequestMapping(value = "sortDesc",method = RequestMethod.POST)
    @ResponseBody
    public Map sortDesc(@RequestParam String tableName,@RequestParam String filed,@RequestParam int page,@RequestParam int num){
        Session session=getCurrentSession();
        Transaction transaction=session.beginTransaction();
        String sqlQuery="from "+tableName+" order by "+filed+" desc";
        Map map=new HashMap();
        try{
            List list=session.createQuery(sqlQuery).setFirstResult((page-1)*num).setMaxResults(num).list();
            map.put("result",list);
            map.put("length",list.size());
        }catch (Exception e){
            map.put("message","查询错误哦");
        }


        return map;
    }

}
