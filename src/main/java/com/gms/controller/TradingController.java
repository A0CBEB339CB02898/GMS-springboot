package com.gms.controller;

import com.alibaba.fastjson.JSONObject;
import com.gms.entity.Trading;
import com.gms.entity.User;
import com.gms.mapper.TradingMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.DigestUtils;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Created by Orion on 2020/6/9 16:05
 */
///**
// ####################################################################################
// ####################################################################################
// #####################|';&#####@|:;&#######|';&########|:|###########################
// ####################@!':$#####$:':%######&;''%#######$:'!@##########################
// ####################################################################################
// ####################################################################################
// ####################################################################################
// ####################################################################################
// #######################%;;;;;;;;;!$####@%;;;;;;;;;;%################################
// ####################&!;;!$#####@%;;;%&|;;!%#####&|;;;|@#############################
// ###################$;;|@##########&!;;;|@##########@|;!&############################
// ##################&!;|@######@|;!$#&!;|@##########&@#%;!&###########################
// ##############&%%$|;;$######%'''''!|;;$########$:'''';;;$####@$|!%@#################
// #########%;;;;;;;;;;;$######|''''';|;;$########|'''''::;;;;;;;;;;;|@################
// #######$;;;|%%%%%%|;;%#######$;':%#$;;|@########%:''!!;;|%%|;;;;;;|@################
// ######&!;!%%%%%%%%%|;;!&##########$;;;;!&##########&!;;|%%%%%%|!;;|@################
// ######%;!|%%%%%%%%%%%!;;;|$@@@&%!;;;|%!;;;!%&@@$%!;;;!%%%%%%%%%%|;;;;;;%############
// ######%;!%%%%%%%%%%%%%%|!;;;;;;;;!%%%%%%|!;;;;;;;;!%%%%%%%%%%%%%%|;;;;;!&###########
// ######$;;|%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%|;;;;$############
// ######@|;!%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%|!;|@#############
// #######@|;!%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%|!|%%%%%%%%%%%!;!&#############
// ########@|;;|%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%!;!|%%%%%%%%%%%!;;;|@###########
// ##########&!;;!%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%|;;!%%%%%%%%%%%|;;;;;$###########
// ############&!;;;!|%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%!;;!%%%%%%%%%%%!;;;;%############
// ###############&!;;;;;;!||%%%%%%%%%%%%%%%%%%|!;;;;;;;;;|%%%%%%%%%!;;!&##############
// ##################$;;;;;;;;;;;;;;;;;;;;;;;;;;;;;!|%%%!;;;|%%%%%|;;;$################
// ####################$!;;|%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%|%%%%%!;;!@#################
// ######################@%;;;;;|%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%|;!&##################
// #########################$;;;!|%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%|!;;;;%###############
// ########################$;;|%%%%%%%%%%%%%%%%%%%|!|%%%%%|;|%%%%%|;;;;|###############
// #######################@!;!%%%%%%%%%%%%%%%%%%%%%!;|%%%%|;!%%%%%%|;;|&&$&############
// #######################%;!|%%%%%%%%%%%%%%%%%%%%%%!;!%%%!;|%%%%%%%|;;;;;;%###########
// ######################&!;!%%%%%%%%%%%%%%%%%%%%%%%%|!;;;;|%%%%%%%%%%!;;;|@###########
// ######################&||%$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$%|||&###########
// */
@RestController
public class TradingController {
    @Autowired
    TradingMapper tradingMapper;

    /**
     * 新增交易
     * @param body 请求体
     * @return json
     */
    @PostMapping("/trading/add")
    public JSONObject tradingAdd(@RequestBody Map body,HttpSession session){
        /**
         * 新增交易事件
         */
        JSONObject response = new JSONObject();
        Trading trading = new Trading();
        User user =new User();

        try{
            user = (User) session.getAttribute("user");
            if(body.get("tradingType")==null||body.get("counterParty")==null||body.get("transactionAmount")==null){
                response.put("msc","fail! "+" 参数缺失，请检查！");
                response.put("code","400");
                return response;
            }
            else {
                //7个参数 4个必须 2个自动生成 1个delete位
                int userId = user.getUserId();
                int tradingType = Integer.parseInt(body.get("tradingType").toString());
                String counterParty =(String)body.get("counterParty");
                int transactionAmount = Integer.parseInt(body.get("transactionAmount").toString());
                int isDelete=0;
                int tradingTime = (int) (System.currentTimeMillis() / 1000);
                String tradingContent = "无";

                //判断非必要参数
                if(body.get("tradingTime")!=null){
                    tradingTime = Integer.parseInt(body.get("tradingTime").toString());
                }
                if(body.get("tradingContent")!=null){
                    tradingContent=(String)body.get("tradingContent");
                }

                try {
                    //注入数据
                    trading.setUserId(userId);
                    trading.setTradingType(tradingType);
                    trading.setTradingTime(tradingTime);
                    trading.setCounterParty(counterParty);
                    trading.setTransactionAmount(transactionAmount);
                    trading.setIsDelete(isDelete);
                    trading.setTradingContent(tradingContent);

                    tradingMapper.insertTrading(trading);

                    response.put("msg","增加成功");
                    response.put("code",200);
                }
                catch (Exception e){
                    response.put("msg",e);
                    response.put("code",400);
                }
            }
            return response;
        }catch (NullPointerException e){
            response.put("msg","请登录");
            response.put("code",400);
            return response;
        }



        //判断必要请求参数

    }

    /**
     * 删除交易 传入格式{"tradingId":#{tradingId}}
     * 会自动从session中获取当前操作的用户
     * @param body 请求体
     * @param session 会话
     * @return json
     */
    @PostMapping("/trading/delete")
    public JSONObject tradingDelete(@RequestBody Map body,HttpSession session){
        JSONObject response = new JSONObject();
        Trading trading = new Trading();
        User user = new User();
        try{
            user=(User) session.getAttribute("user");

        if (isRightUser(Integer.parseInt(body.get("tradingId").toString()),user.getUserId(),user.getPosId())){
            try{
                //传入tradingId
                trading.setTradingId(Integer.parseInt(body.get("tradingId").toString()));
                tradingMapper.deleteTrading(trading.getTradingId());

                response.put("msg","删除成功");
                response.put("code",200);
            }catch (Exception e){
                response.put("msg","fail"+e);
                response.put("code",400);
            }
        }
        else {
            response.put("msg","权限不足");
            response.put("code",400);
        }
        }catch (NullPointerException e){
            response.put("msg","请登录");
            response.put("code",400);
            return response;
        }

        return response;
    }



    /**
     * 动态查询 value=-1即不作限制(不查询)
     * @param tradingId 交易Id
     * @param tradingTimeBegin 交易时间起始
     * @param tradingTimeEnd 交易时间结束
     * @param userId 用户Id
     * @param tradingType 交易类型
     * @param count 查询的页数
     * @return json 查询结果
     */
//    @GetMapping("/trading/search")
    public JSONObject tradingSearch(int tradingId,int userId,int tradingType,int tradingTimeBegin,int tradingTimeEnd,int count){

        JSONObject jsonObject=new JSONObject();
        Trading trading=new Trading();
        final int notSearch=-1;
        final int pageSize=8;

        trading.setTradingId(tradingId);
        trading.setUserId(userId);
        trading.setTradingType(tradingType);

        List<Trading> listTradingIdSearch=new ArrayList<Trading>();
        List<Integer> listTimeSearch=new ArrayList<Integer>();
        List<Integer> listUserIdSearch=new ArrayList<Integer>();
        List<Integer> listTypeSearch=new ArrayList<Integer>();
        List<Integer> listIdResult=new ArrayList<Integer>();
        List<Trading> listResult=new ArrayList<Trading>();

        //根据tradingId查询
        if (tradingId!=notSearch){
            Trading trading1=new Trading();
            try{
                trading1=tradingMapper.getTradingByID(trading);
                listTradingIdSearch.add(trading1);
            }catch (NullPointerException e){
                jsonObject.put("tradingIdMsg",e);
                jsonObject.put("code",400);
            }
            if (listTradingIdSearch.get(0)==null){
                jsonObject.put("tradingList",listTradingIdSearch);
                jsonObject.put("page",(int)(listTradingIdSearch.size()/10)+1);
                jsonObject.put("msg","搜索失败");
                jsonObject.put("code",400);
            }else{
                jsonObject.put("tradingList",listTradingIdSearch);
                jsonObject.put("page",(int)(listTradingIdSearch.size()/10)+1);
                jsonObject.put("msg","搜索成功");
                jsonObject.put("code",200);
            }
            return jsonObject;
        }

        // TODO: 2020/6/16 优化重复结构
        //根据userId查询
        if (userId!=notSearch&&tradingId==notSearch){
            try{
                listUserIdSearch= tradingMapper.getTradingIdByUserId(trading);
            }catch (NullPointerException e){
                jsonObject.put("userIdMsg",e);
                jsonObject.put("code",400);
            }

            if(listIdResult.size()==0){
                listIdResult=listUserIdSearch;
            }
            else if (listIdResult.get(0)==notSearch){
                return searchNull();
            }
            else if (listUserIdSearch.size()==0){
                listIdResult.add(0,-1);
            }
            else {
                listIdResult.retainAll(listUserIdSearch);
            }

        }

        //根据tradingType查询
        if (tradingType!=notSearch&&tradingId==notSearch){
            try{
                listTypeSearch= tradingMapper.getTradingIdByTradingType(trading);
            }catch (NullPointerException e){
                jsonObject.put("tradingTypeMsg",e);
                jsonObject.put("code",400);
            }

            if(listIdResult.size()==0){
                listIdResult=listTypeSearch;
            }
            else if (listIdResult.get(0)==notSearch){
                return searchNull();
            }
            else if (listTypeSearch.size()==0){
                listIdResult.add(0,-1);
            }
            else {
                listIdResult.retainAll(listTypeSearch);
            }

        }

        //根据tradingTime查询
        if (tradingTimeBegin!=notSearch&&tradingTimeEnd!=notSearch&&tradingId==notSearch){
            try{
                listTimeSearch=tradingMapper.getTradingIdByTradingTime(getTheDayZero(tradingTimeBegin),getTheDayTwelve(tradingTimeEnd));
            }catch (NullPointerException e){
                jsonObject.put("tradingTimeMsg",e);
                jsonObject.put("code",400);
            }

            if(listIdResult.size()==0){
                listIdResult=listTimeSearch;
            }
            else if (listIdResult.get(0)==notSearch){
                return searchNull();
            }
            else if (listTimeSearch.size()==0){
                listIdResult.add(0,-1);
            }
            else {
                listIdResult.retainAll(listTimeSearch);
            }
        }

        //查询所有
        if(tradingId==notSearch&&userId==notSearch&&tradingType==notSearch&&tradingTimeBegin==notSearch&&tradingTimeEnd==notSearch){
            try{
                listResult=tradingMapper.getAllTradingNotDelete();
                int pageBegin=count;
                int pageEnd=count+pageSize;
                //分页参数大小限制 如果count+pageSize超出大小，则返回最后一页
                if (pageBegin>=listResult.size()){
                    //不足一页
                    if(listResult.size()-pageSize<0){
                        pageBegin=0;
                    }else {
                        pageBegin=listResult.size()-pageSize;
                    }
                    //sublist的toIndex不包含所以不需要-1
                    pageEnd=listResult.size();
                }
                else if (count+pageSize>listResult.size()){
                    pageEnd=listResult.size();
                }
                jsonObject.put("page",Math.ceil((double)listResult.size()/pageSize));
                listResult=listResult.subList(pageBegin,pageEnd);
                jsonObject.put("tradingList",listResult);
                jsonObject.put("msg","搜索成功");
                jsonObject.put("code",200);
                return jsonObject;

            }catch (NullPointerException e){
                jsonObject.put("msg",e);
                jsonObject.put("code",400);
            }


        }

        //将混合查询得到的ID数组重新查询为trading数组
        if (listIdResult.size()==0){
            jsonObject.put("page",Math.ceil((double)listResult.size()/pageSize));
            jsonObject.put("tradingList",listResult);
            jsonObject.put("msg","无结果");
            jsonObject.put("code",201);
        } else if (listIdResult.get(0)!=notSearch){
                try{
                    for (int i=0;i<listIdResult.size();i++){
                    Trading trading1=new Trading();
                    trading1.setTradingId(listIdResult.get(i));
                    listResult.add(tradingMapper.getTradingByID(trading1));
                    }

                    int pageBegin=count;
                    int pageEnd=count+pageSize;
                    //分页参数大小限制 如果count+pageSize超出大小，则返回最后一页
                    if (pageBegin>=listResult.size()){

                        //不足一页
                        if(listResult.size()-pageSize<0){
                            pageBegin=0;
                        }else {
                            pageBegin=listResult.size()-pageSize;
                        }
                        //sublist的toIndex不包含所以不需要-1
                        pageEnd=listResult.size();

                    }
                    else if (count+pageSize>listResult.size()){
                        pageEnd=listResult.size();
                    }
                    jsonObject.put("page",Math.ceil((double)listResult.size()/pageSize));
                    listResult=listResult.subList(pageBegin,pageEnd);
                    jsonObject.put("tradingList",listResult);
                    jsonObject.put("msg","搜索成功");
                    jsonObject.put("code",200);
                }catch (NullPointerException e){
                    jsonObject.put("msg",e);
                    jsonObject.put("code",400);
                }
        }

        return jsonObject;
    }

    /**
     * 封装查询方法
     */
    @GetMapping("/trading/search")
    public JSONObject tradingSearch(String  tradingId,String userId,String tradingType,String tradingTimeBegin,String tradingTimeEnd,String count,HttpSession session){
        JSONObject jsonObject =new JSONObject();
        User user =(User) session.getAttribute("user");
        int  intTradingId=-1;
        int intUserId=-1;
        int intTradingType=-1;
        int intTradingTimeBegin=-1;
        int intTradingTimeEnd=-1;
        int intCount=0;
        try{
            int i=user.getUserId();
        }catch (NullPointerException e){
            jsonObject.put("msg","请登录");
            jsonObject.put("code",400);
            return jsonObject;
        }
        try{
            intTradingId=Integer.parseInt(tradingId);
        }catch (NumberFormatException e){
            intTradingId=-1;
        }
        try{
            intUserId=Integer.parseInt(userId);
        }catch (NumberFormatException e){
            intUserId=-1;
        }
        try{
            intTradingType=Integer.parseInt(tradingType);
        }catch (NumberFormatException e){
            intTradingType=-1;
        }
        try{
            intTradingTimeBegin=Integer.parseInt(tradingTimeBegin);
            System.out.println(intTradingTimeBegin);
        }catch (NumberFormatException e){
            intTradingTimeBegin=-1;
        }
        try{
            intTradingTimeEnd=Integer.parseInt(tradingTimeEnd);
            System.out.println(intTradingTimeEnd);
        }catch (NumberFormatException e){
            intTradingTimeEnd=-1;
        }

        try{
            intCount=Integer.parseInt(count);
        }catch (NumberFormatException e){
            intCount=0;
        }
        jsonObject=tradingSearch(intTradingId,intUserId,intTradingType,intTradingTimeBegin,intTradingTimeEnd,intCount);

        return jsonObject;
    }

    //只能改用户、金额、交易方、金额、内容；
    @PostMapping("/trading/change")
    public JSONObject tradingChange(@RequestBody Map body,HttpSession session){
        JSONObject jsonObject=new JSONObject();
        Trading trading=new Trading();
        User user=new User();
        try {
            user = (User) session.getAttribute("user");
            if(isRightUser(Integer.parseInt(body.get("tradingId").toString()),user.getUserId(),user.getPosId())){
                try{
                    int tradingId= Integer.parseInt(body.get("tradingId").toString());
                    int userId = user.getUserId();
                    int tradingType = Integer.parseInt(body.get("tradingType").toString());
                    String counterParty =(String)body.get("counterParty");
                    int transactionAmount = Integer.parseInt(body.get("transactionAmount").toString());
                    String tradingContent = body.get("tradingContent").toString();


                    trading.setTradingId(tradingId);
                    trading.setUserId(userId);
                    trading.setTradingType(tradingType);
                    trading.setCounterParty(counterParty);
                    trading.setTransactionAmount(transactionAmount);
                    trading.setTradingContent(tradingContent);
                    tradingMapper.changeTrading(trading);

                    jsonObject.put("msg","修改成功");
                    jsonObject.put("code",200);
                }catch (NullPointerException|NumberFormatException e){
                    jsonObject.put("msg",e);
                    jsonObject.put("code",400);
                    return jsonObject;
                }
            }
            else {
                jsonObject.put("msg","权限不足");
                jsonObject.put("code",400);
            }
        }catch (NullPointerException e){
            jsonObject.put("msg","未登录");
            jsonObject.put("code",400);
            return jsonObject;
        }
        return jsonObject;
    }



    /**
     * 查询总收入支出
     * @param tradingType 1支出 2收入
     * @return json
     */
    @GetMapping("/trading/total")
    public JSONObject tradingTotal(int tradingType){
        JSONObject jsonObject =new JSONObject();
        List<Trading> tradingList=new ArrayList<>();
        int total=0;
        try{
            tradingList=tradingMapper.getTradingByTradingType(tradingType);
        }catch (NullPointerException e){
            jsonObject.put("msg",e);
            jsonObject.put("code",400);
            return jsonObject;
        }

        for (int i=0;i<tradingList.size();i++){
            total=total+tradingList.get(i).getTransactionAmount();
        }
        jsonObject.put("total",total);
        jsonObject.put("msg","suc");
        jsonObject.put("code",200);
        return jsonObject;
    }


    /**
     * 测试接口
     *
     * @return v 测试参数
     */
    @GetMapping("/trading/test")
    public String tradingTest(){
//        System.out.println(isRightUser(Integer.parseInt(body.get("tradingId").toString()),Integer.parseInt(body.get("userId").toString()),Integer.parseInt(body.get("posId").toString())));
//        User user=(User)session.getAttribute("user");
//        System.out.println(user.getUsername());
//        System.out.println("k==="+k);
//        System.out.println("v==="+v);
//        System.out.println(getTheDayZero(time));
////        System.out.println(getTheDayTwelve(time));
        return "habi";

    }



    // TODO: 2020/6/15
    //  职位表未完善，到时候再优化鉴权。
    /*
      鉴权开始
     */
    /**
     * 鉴权 需要传入要鉴定的用户的userId和posId
     * @param tradingId 需要鉴权的订单
     * @param userId 需要鉴权的用户
     * @param posId 用户的职位id
     * @return bool 是否有权限
     */
    public boolean isRightUser(int tradingId, int userId, int posId){
        boolean isRightUser=false;
        Trading trading = new Trading();
        trading.setTradingId(tradingId);

        Trading trading_result=new Trading();
        //查询传入的交易
        try{
            trading_result=tradingMapper.getTradingByID(trading);
        }catch (Exception e){
            System.out.println("鉴权失败！detail==="+e);
        }

        if (trading_result!=null){
            //鉴定权限
            if(posId==2){
                //鉴定本人
                if (trading_result.getUserId()==userId){
                    isRightUser=true;
                }
                else{
                    System.out.println("非创建者");
                    isRightUser=false;
                }
            }
            else if(posId==1){
                System.out.println("超级管理员");
                isRightUser=true;
            }
            else {
                isRightUser=false;
            }
        }
        else {
            isRightUser=false;
            System.out.println("鉴权失败！detail==="+"查询不到该交易");
        }

        return isRightUser;
    }
    /*
      鉴权结束
     */
//扫码支付
    @GetMapping("/trading/qrcode")
    public JSONObject qrCodeVerification(String paymentUid,int step) {
        JSONObject jsonObject =new JSONObject();
        String baseLink="localhost:8080";
        if (step==1){
            String Uid = DigestUtils.md5DigestAsHex((Long.toString((666+System.currentTimeMillis())/ 1000)).getBytes());
            try{
                tradingMapper.insertPayment(Uid,false);
                jsonObject.put("payLink",baseLink+"/trading/mobile?paymentUid="+Uid+"&step=2");
                jsonObject.put("msg","支付单号已生成");
                jsonObject.put("code",200);
            }catch (NullPointerException e){
                jsonObject.put("msg","支付生成失败");
                jsonObject.put("code",500);
            }
            return jsonObject;
        }
        if (step==2){
            try{
                tradingMapper.pay(paymentUid);
                jsonObject.put("code",200);
                jsonObject.put("msg","支付成功");
                return jsonObject;
            }catch (NullPointerException e){
                jsonObject.put("code",400);
                jsonObject.put("msg","支付失败");
            }
        }
        if (step==3){
            try{
                boolean isPay=tradingMapper.getIsPay(paymentUid);
                if (isPay){
                    jsonObject.put("msg","支付成功！");
                    jsonObject.put("code",200);
                }else {
                    jsonObject.put("msg","您还未扫码支付！");
                    jsonObject.put("code",400);
                }
            }catch (NullPointerException e){
                jsonObject.put("msg","失败！");
                jsonObject.put("code",400);
                return jsonObject;
            }
        }
        return jsonObject;
    }

    @GetMapping("/trading/mobile")
    public String showInMobile(String paymentUid,int step){
        if (step==2){
            JSONObject jsonObject=new JSONObject();
            jsonObject=qrCodeVerification(paymentUid,step);
            if ((int)jsonObject.get("code")==200){
                return "支付成功";
            }else {
                return "支付失败";
            }
        }
        return "错误";
    }
    //查询空返回
    private JSONObject searchNull(){
        JSONObject jsonObject =new JSONObject();
        List<Trading> nullList =new ArrayList<Trading>();
        jsonObject.put("tradingList",nullList);
        jsonObject.put("page",0);
        jsonObject.put("msg","suc");
        jsonObject.put("code",200);
        return jsonObject;
    }

    //获取当天零点
    public int getTheDayZero(int time){
        int theDayZero = 0;
        theDayZero=(time/86400)*86400-28800;
        return theDayZero;
    }

//    获取当天23:59:59
    public int getTheDayTwelve(int time){
        int theDayTwelve = 86399;
        theDayTwelve=theDayTwelve+(time/86400)*86400-28800;
        return theDayTwelve;
    }
}
