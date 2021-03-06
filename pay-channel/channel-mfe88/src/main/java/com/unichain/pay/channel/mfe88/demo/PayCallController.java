//package com.unichain.pay.channel.mfe88.demo;
//
//import java.io.BufferedInputStream;
//import java.io.BufferedOutputStream;
//import java.io.File;
//import java.io.FileInputStream;
//import java.io.FileNotFoundException;
//import java.io.IOException;
//import java.io.InputStream;
//import java.io.OutputStream;
//import java.io.UnsupportedEncodingException;
//import java.net.InetAddress;
//import java.net.URLDecoder;
//import java.net.UnknownHostException;
//import java.text.SimpleDateFormat;
//import java.time.LocalDateTime;
//import java.time.format.DateTimeFormatter;
//import java.util.ArrayList;
//import java.util.Date;
//import java.util.List;
//import java.util.Map;
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
//import javax.servlet.http.HttpSession;
//
//import com.unichain.pay.channel.mfe88.demo.entity.PostCode;
//import com.unichain.pay.channel.mfe88.demo.entity.Trade;
//import com.unichain.pay.channel.mfe88.demo.entity.UppOrderLogs;
//import com.unichain.pay.channel.mfe88.mybatisGenerator.dao.UppOrderLogsMapper;
//import com.unichain.pay.channel.mfe88.utils.FileUtil;
//import com.unichain.pay.channel.mfe88.utils.ObjectMapperUtil;
//import com.unichain.pay.channel.mfe88.utils.Tools;
//import lombok.extern.slf4j.Slf4j;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.core.env.Environment;
//import org.springframework.stereotype.Controller;
//import org.springframework.stereotype.Service;
//import org.springframework.ui.ModelMap;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.ResponseBody;
//
//@Controller
//@RequestMapping(value = "/demo")
//@Service
//@Slf4j
//public class PayCallController {
//    @Autowired
//    UppOrderLogsMapper uppOrderLogsMapper;
//    @Autowired
//    private Environment env;
//
//    @ResponseBody
//    @RequestMapping(value = "/payCall")
//    public String payCall(HttpServletRequest request, HttpSession session) {
//        String content = "<br/><br/>" + getIpAddress(request);
//        content += new Date() + ":?????????????????????????????????:  ";
//        boolean flagString = false;
//        try {
//            Map<String, Object> requestMap = ObjectMapperUtil.readValue(request, Map.class);
//            System.out.print("??????????????????" + requestMap);
//            if (requestMap == null || requestMap.isEmpty())
//                content += "??????????????????????????????";
//            else {
//                String keyType = env.getProperty("keyType");
//                Trade trade = new Trade();
//                trade.setSignType(keyType);
//                if (keyType.equals("1"))
//                    trade.setMd5Key(env.getProperty("md5Key"));
//                if (keyType.equals("2")) {
//                    trade.setPublicKey(env.getProperty("rsaPublicKey"));
//                    trade.setPrivateKey(env.getProperty("rsaPrivateKey"));
//                }
//                if (keyType.equals("3")) {
//                    trade.setBankPublicKey(env.getProperty("RSACertCer"));
//                }
//                String sb = Demo.createLinkString(requestMap);
//                content += requestMap + "<br>????????????????????????????????????:" + sb;
//                flagString = Demo.checkSign(trade, sb, requestMap.get("sign") + "");
//                content += flagString == true ? " ??????????????????" : "  ??????????????????";
//
//            }
//        } catch (Exception e) {
//            content = e.toString();
//            // TODO Auto-generated catch block
//            e.printStackTrace();
//        } finally {
//            String basePath = session.getServletContext().getRealPath("/"); // ??????????????????
//            FileUtil.write(basePath + "WEB-INF/payCall.txt", content);
//            return flagString == true ? Tools.getGsonDisableHtml().toJson("SUCCESS") : Tools.getGsonDisableHtml().toJson("FAILED");
//        }
//    }
//
//    @ResponseBody
//    @RequestMapping(value = "/upppayCall")
//    public String upppayCall(HttpServletRequest request, HttpSession session) {
//        String content = "<br/><br/>" + getIpAddress(request);
//        content += new Date() + ":?????????????????????????????????:  ";
//        boolean flagString = false;
//        try {
//            Map<String, Object> requestMap = ObjectMapperUtil.readValue(request, Map.class);
//            System.out.print("??????????????????" + requestMap);
//            if (requestMap == null || requestMap.isEmpty())
//                content += "??????????????????????????????";
//            else {
//                String keyType = env.getProperty("keyType");
//                Trade trade = new Trade();
//                trade.setKeyType(keyType);
//                if (keyType.equals("1"))
//                    trade.setMd5Key(env.getProperty("md5Key"));
//                if (keyType.equals("2")) {
//                    trade.setPublicKey(env.getProperty("m_rsaPublicKey"));
//                    trade.setPrivateKey(env.getProperty("m_rsaPrivateKey"));
//                }
//                if (keyType.equals("3")) {
//                    trade.setBankPublicKey(env.getProperty("RSACertCer"));
//                }
//                content += requestMap + "<br>???????????????????????????";
//                String singStr = Demo.createLinkString(UppDemo.paraFilter(requestMap)); //???????????????????????????????????????=???????????????????????????&???????????????????????????
//                flagString = UppDemo.verifySign(URLDecoder.decode(requestMap.get("sign") + "", "utf-8"), trade.getPublicKey(), singStr);
//                content += "  ?????????:" + singStr + (flagString == true ? "??????????????????" : "??????????????????");
//            }
//        } catch (NumberFormatException e) {
//            content = e.toString();
//            // TODO Auto-generated catch block
//            e.printStackTrace();
//        } catch (UnsupportedEncodingException e) {
//            content = e.toString();
//            // TODO Auto-generated catch block
//            e.printStackTrace();
//        }
//        String basePath = session.getServletContext().getRealPath("/"); // ??????????????????
//        FileUtil.write(basePath + "WEB-INF/upppayCall.txt", content);
//        return flagString == true ? Tools.getGsonDisableHtml().toJson("SUCCESS") : Tools.getGsonDisableHtml().toJson("FAILED");
//    }
//
//    public static String getIpAddress(HttpServletRequest request) {
//        String ip = request.getHeader("x-forwarded-for");
//        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
//            ip = request.getHeader("Proxy-Client-IP");
//        }
//        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
//            ip = request.getHeader("WL-Proxy-Client-IP");
//        }
//        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
//            ip = request.getHeader("HTTP_CLIENT_IP");
//        }
//        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
//            ip = request.getHeader("HTTP_X_FORWARDED_FOR");
//        }
//        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
//            ip = request.getRemoteAddr();
//        }
//        return ip;
//    }
//
//    @ResponseBody
//    @RequestMapping(value = "/checkPayCall")
//    public String checkPayCall(ModelMap map, String mOrderNo, HttpSession session) {
//        String basePath = session.getServletContext().getRealPath("/"); // ??????????????????
//        String content = "";
//        try {
//            if (mOrderNo.isEmpty())
//                content = FileUtil.readToList(basePath + "WEB-INF/payCall.txt");
//            else
//                content = FileUtil.readForString(basePath + "WEB-INF/payCall.txt", mOrderNo.replace(" ", ""));
//        } catch (FileNotFoundException e) {
//            content = e.toString();
//        } catch (IOException e) {
//            e.printStackTrace();
//            content = e.toString();
//        }
//        return content;
//    }
//
//    @ResponseBody
//    @RequestMapping(value = "/uppcheckPayCall")
//    public String uppcheckPayCall(ModelMap map, String mOrderNo, HttpSession session) {
//        String basePath = session.getServletContext().getRealPath("/"); // ??????????????????
//        String content = "";
//        try {
//            if (mOrderNo.isEmpty())
//                content = FileUtil.readToList(basePath + "WEB-INF/upppayCall.txt");
//            else
//                content = FileUtil.readForString(basePath + "WEB-INF/upppayCall.txt", mOrderNo.replace(" ", ""));
//        } catch (FileNotFoundException e) {
//            content = e.toString();
//        } catch (IOException e) {
//            e.printStackTrace();
//            content = e.toString();
//        }
//        return content;
//    }
//
//    @RequestMapping("/toPage")
//    public String toPage(ModelMap map, String pageName, String url, Long needSubmit, Long merge) throws Exception {
//        String apiUrl = env.getProperty("apiUrl");
//        getCommonData(apiUrl,map,pageName,url,needSubmit);
//        //three environment
//        String toPage;
//        if (apiUrl.contains("uppApi")) {
//            //upp
//            getUppParameter(map, pageName, url);
//            toPage = "demo/upp/" + pageName;
//        } else if (apiUrl.equals("http://139.129.206.79:29606/PayApi/")) {
//            //ali
//            toPage = "demo/ali/" + pageName;
//        } else {
//            //?????????
//            toPage = "demo/saas/" + pageName;
//        }
//
//        return toPage;
//    }
//
//    @ResponseBody
//    @RequestMapping(value = "/getLogs")
//    public String getLogs(String mOrderNo, String cxOrderNo) {
//        String outStr = "";
//        UppOrderLogs bean = new UppOrderLogs();
//        long DAY_IN_MS = 1000 * 60 * 60 * 24;
////		bean.setCreateTime(new Date(System.currentTimeMillis() - (2 * DAY_IN_MS)));//four days
//        SimpleDateFormat sdfr = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
//        String dateString = sdfr.format(new Date(System.currentTimeMillis() - (1 * DAY_IN_MS)));
//        bean.setStartCreateTime(dateString);
//        List<UppOrderLogs> list = new ArrayList<UppOrderLogs>();
//        if (!Tools.isEmpty(mOrderNo) && !Tools.isEmpty(cxOrderNo)) {
//            bean.setOrderNo(mOrderNo.replace(" ", ""));
//            list = uppOrderLogsMapper.findAll(bean);
//            bean.setOrderNo(cxOrderNo.replace(" ", ""));
//            List<UppOrderLogs> list2 = new ArrayList<UppOrderLogs>();
//            list2 = uppOrderLogsMapper.findAll(bean);
//            if (null != list && list.size() > 0) {
//                list.addAll(list2);
//            }
//            outStr += Tools.getGsonDisableHtml().toJson(list);
//        } else if (Tools.isEmpty(mOrderNo) && !Tools.isEmpty(cxOrderNo)) {
//            bean.setOrderNo(cxOrderNo.replace(" ", ""));
//            list = uppOrderLogsMapper.findAll(bean);
//            if (null != list && list.size() > 0) {
//                outStr += Tools.getGsonDisableHtml().toJson(list);
//            }
//        } else if (!Tools.isEmpty(mOrderNo) && Tools.isEmpty(cxOrderNo)) {
//            bean.setOrderNo(mOrderNo.replace(" ", ""));
//            list = uppOrderLogsMapper.findAll(bean);
//            if (null != list && list.size() > 0) {
//                outStr += Tools.getGsonDisableHtml().toJson(list);
//            }
//        } else {
//            return Tools.getGsonDisableHtml().toJson("");
//        }
//        return outStr.isEmpty() ? Tools.getGsonDisableHtml().toJson("") : outStr;
//    }
//
//    @ResponseBody
//    @RequestMapping(value = "download")
//    public void download(String fileurl, HttpSession session, HttpServletResponse response) throws IOException {
//        String basePath = session.getServletContext().getRealPath("/"); // ??????????????????
//        if (null != fileurl && !fileurl.equals("")) {
//            /* ?????????:?????????????????????????????? */
//            File file = new File(basePath + fileurl);
//            if (file.exists()) { // ????????????
//                /* ???????????????????????????????????????????????????????????? */
//                InputStream inputStream = new BufferedInputStream(new FileInputStream(file));
//                /* ???????????????????????????????????????????????????????????? */
//                byte[] buffer = new byte[inputStream.available()]; // int
//                /* ?????????????????????????????????????????????????????? */
//                inputStream.read(buffer);
//                /* ???????????? ??????????????? */
//                inputStream.close();
//                String fileName = file.getName();// ???????????????
//                response.reset();
//                response.addHeader("Content-Disposition",
//                        "attachment;filename=" + new String(fileName.getBytes("utf-8"), "iso8859-1"));
//                response.addHeader("Content-Length", "" + file.length());
//                /* ????????????????????????????????? */
//                OutputStream outputStream = new BufferedOutputStream(response.getOutputStream());
//                response.setContentType("application/octet-stream");
//                /* ?????????????????????????????????????????????????????? */
//                outputStream.write(buffer);
//                /* ??????????????????????????????????????????????????????????????? */
//                outputStream.flush();
//                /* ??????????????????????????? */
//                outputStream.close();
//            } // end if (file.exists())
//        } else {
//            return;
//        }
//    }
//    public void getCommonData(String apiUrl,ModelMap map,String pageName,String url,Long needSubmit) throws UnknownHostException {
//        String postUrl;
//        postUrl = getPageName(apiUrl, pageName, url);
//        map.put("postUrl", postUrl);
//        map.put("url", url);
//        map.put("needSubmit", "" + needSubmit);
//        if (needSubmit == null) {
//            map.put("orderTime", LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyyMMddHHmmss")));// ????????????);
//            map.put("orderNo", Demo.nextGeneratorOrderNo());
//            map.put("payerIp", InetAddress.getLocalHost().getHostAddress());
//        }
//        if (pageName.equals("log"))
//            map.put("logUrl", env.getProperty("logUrl"));
//        map.put("rsaPrivateKey", env.getProperty("rsaPrivateKey"));
//        map.put("rsaPublicKey", env.getProperty("rsaPublicKey"));
//        map.put("merchantNo", env.getProperty("merchantNo"));
//        map.put("md5Key", env.getProperty("md5Key"));
//        map.put("RSACertPfx", env.getProperty("RSACertPfx"));
//        map.put("RSACertCer", env.getProperty("RSACertCer"));
//        map.put("certKeyPassword", env.getProperty("certKeyPassword"));
//        map.put("apiUrl", env.getProperty("apiUrl"));
//        map.put("wwwUrl", env.getProperty("wwwUrl"));
//        map.put("pageUrl", env.getProperty("pageUrl"));
//        map.put("bgUrl", env.getProperty("bgUrl"));
//        map.put("aesKey", env.getProperty("aesKey"));
//        map.put("openid", env.getProperty("openid"));
//        map.put("bankCardNo", env.getProperty("bankCardNo"));
//        map.put("idCode", env.getProperty("idCode"));
//        map.put("phone", env.getProperty("phone"));
//        map.put("userName", env.getProperty("userName"));
//        map.put("cvv2", env.getProperty("cvv2"));
//        map.put("validPeriod", env.getProperty("validPeriod"));
//        map.put("keyType", env.getProperty("keyType"));
//        //wechat
//        map.put("appId", env.getProperty("appId"));
//        map.put("timeStamp", env.getProperty("timeStamp"));
//        map.put("nonceStr", env.getProperty("nonceStr"));
//        map.put("package1", env.getProperty("package"));
//        map.put("signType", env.getProperty("signType"));
//        map.put("paySign", env.getProperty("paySign"));
//    }
//    public void getUppParameter(ModelMap map, String pageName, String url) {
//        if (pageName.equals("uppcontent") && url.equals("orderListSearch")) {
//            long DAY_IN_MS = 1000 * 60 * 60 * 24;
//            SimpleDateFormat sdfr = new SimpleDateFormat("yyyy-MM-dd");
//            String dateString = sdfr.format(new Date(System.currentTimeMillis() + (1 * DAY_IN_MS)));
//            map.put("order_time_start", sdfr.format(new Date(System.currentTimeMillis())) + " 00:00:00");
//            map.put("order_time_end", dateString + " 00:00:00");
//        }
//        map.put("orgNo", env.getProperty("orgNo"));
//        map.put("userId", env.getProperty("userId"));
//        map.put("payPassWord", env.getProperty("payPassWord"));
//        map.put("merchantOrgNo", env.getProperty("merchantOrgNo"));
//        map.put("payUserId", env.getProperty("payUserId"));
//        map.put("m_rsaPrivateKey", env.getProperty("m_rsaPrivateKey"));
//        map.put("m_rsaPublicKey", env.getProperty("m_rsaPublicKey"));
//        map.put("m_md5Key", env.getProperty("m_md5Key"));
//        map.put("m_aesKey", env.getProperty("m_aesKey"));
//    }
//
//    public String getPageName(String apiUrl, String pageName, String url) {
//        String postUrl;
//        String name;
//        if (apiUrl.contains("uppApi")) {
//            name = "upp_" + pageName;
//        } else {
//            name = "cashier_" + pageName;
//
//        }
//        if (pageName.equals("content")) {
//            name += "_" + url;
//        }
//        postUrl = PostCode.getPostUrl(apiUrl,name);
//        return postUrl;
//    }
//    //	@ResponseBody
////	@RequestMapping(value = "/changeProperty")
////	public String changeProperty(String property) {
////		if (property != null) {
////			try {
//////				PropertyPlaceholderConfigurer props = new PropertyPlaceholderConfigurer();
//////				props.setSystemPropertiesMode(PropertyPlaceholderConfigurer.SYSTEM_PROPERTIES_MODE_OVERRIDE);
//////				props.setLocations(new PathMatchingResourcePatternResolver()
//////						.getResources("classpath:/application-" + property + ".properties"));
//////				SpringApplication.exit(new ApplicationContext(StartApplication.class),0);
////				ConfigurableApplicationContext ctx = SpringApplication.run(StartApplication.class);
////				ctx.close();
////				SpringApplication  app = new SpringApplication(StartApplication.class);
////				app.setAdditionalProfiles(property);
////				app.run();
////				return "????????????";
////			} catch (Exception e) {
////				// TODO Auto-generated catch block
////				e.printStackTrace();
////				return e.toString();
////			}
////		} else
////			return "property????????????";
////	}
//}
