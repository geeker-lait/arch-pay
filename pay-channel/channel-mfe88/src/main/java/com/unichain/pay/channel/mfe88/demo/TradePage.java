package com.unichain.pay.channel.mfe88.demo;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import com.google.zxing.BarcodeFormat;
import com.google.zxing.EncodeHintType;
import com.google.zxing.WriterException;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.QRCodeWriter;
import com.google.zxing.qrcode.decoder.ErrorCorrectionLevel;
import com.pisgah.gatewayeg.util.RSA;
import com.unichain.pay.channel.mfe88.demo.entity.*;
import com.unichain.pay.channel.mfe88.utils.FileUtil;
import com.unichain.pay.channel.mfe88.utils.HttpClientUtil;
import com.unichain.pay.channel.mfe88.utils.ObjectMapperUtil;
import com.unichain.pay.channel.mfe88.utils.Tools;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Service;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.*;

@Service
@Controller
@RequestMapping(value = "/trade")
public class TradePage {
    private static final Logger log = LoggerFactory.getLogger(TradePage.class.getName());
    static BASE64Decoder decode = new BASE64Decoder();
    static BASE64Encoder ben = new BASE64Encoder();
    ObjectMapper m = new ObjectMapper();

    public static void generateQrode(String myCodeText, String filePath) {
        int size = 250;
        String fileType = "png";
        File myFile = new File(filePath);
        try {

            Map<EncodeHintType, Object> hintMap = new EnumMap<EncodeHintType, Object>(EncodeHintType.class);
            hintMap.put(EncodeHintType.CHARACTER_SET, "UTF-8");

            // Now with zxing version 3.2.1 you could change border size (white border size to just 1)
            hintMap.put(EncodeHintType.MARGIN, 1); /* default = 4 */
            hintMap.put(EncodeHintType.ERROR_CORRECTION, ErrorCorrectionLevel.L);

            QRCodeWriter qrCodeWriter = new QRCodeWriter();
            BitMatrix byteMatrix = qrCodeWriter.encode(myCodeText, BarcodeFormat.QR_CODE, size,
                    size, hintMap);
            int CrunchifyWidth = byteMatrix.getWidth();
            BufferedImage image = new BufferedImage(CrunchifyWidth, CrunchifyWidth,
                    BufferedImage.TYPE_INT_RGB);
            image.createGraphics();

            Graphics2D graphics = (Graphics2D) image.getGraphics();
            graphics.setColor(Color.WHITE);
            graphics.fillRect(0, 0, CrunchifyWidth, CrunchifyWidth);
            graphics.setColor(Color.BLACK);

            for (int i = 0; i < CrunchifyWidth; i++) {
                for (int j = 0; j < CrunchifyWidth; j++) {
                    if (byteMatrix.get(i, j)) {
                        graphics.fillRect(i, j, 1, 1);
                    }
                }
            }
            ImageIO.write(image, fileType, myFile);
        } catch (WriterException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println("\n\nYou have successfully created QR Code.");
    }

    @ResponseBody
    @RequestMapping("/offPayGate")
    public Object offPayGate(HttpServletResponse httpResponse, HttpServletRequest request, Trade bean, HttpSession session) throws Exception {
        Map map = new HashMap();
//		HttpResponse response = httpclient.execute(httpPost);
//		log.info("status code:" + (response != null ? (response.getStatusLine() != null ? response.getStatusLine().getStatusCode() : null) : null));
        String post = "";
        String record = "";
        Map<String, Object> requestMap = null;
        String check = "";
        Response response = null;
        try {
//			ObjectMapper m = new ObjectMapper();
//			Map<String,Object> props = m.convertValue(myBean, Map.class);
//			MyBean anotherBean = m.convertValue(props, MyBean.class)
            Map<String, Object> requestParams = new HashMap<>();
            if (bean.getService().equals("payForAnotherOne")) {
                if (!Tools.isEmpty(bean.getAccountNo()))
                    bean.setAccountNo(ben.encode(bean.getAccountNo().replace(" ", "").getBytes()));
                if (!Tools.isEmpty(bean.getAccountName()))
                    bean.setAccountName(ben.encode(bean.getAccountName().replace(" ", "").getBytes()));
            }
            if (!bean.getService().equals("verifyTheTruth")) {
                if (bean.getBankCardNo() != null)
                    bean.setBankCardNo(Demo.AESEncode(bean.getAesKey(), bean.getBankCardNo().replace(" ", "")));
                if (bean.getUserName() != null)
                    bean.setUserName(Demo.AESEncode(bean.getAesKey(), bean.getUserName().replace(" ", "")));
                if (bean.getIdCode() != null)
                    bean.setIdCode(Demo.AESEncode(bean.getAesKey(), bean.getIdCode().replace(" ", "")));
                if (bean.getPhone() != null)
                    bean.setPhone(Demo.AESEncode(bean.getAesKey(), bean.getPhone().replace(" ", "")));
                if (bean.getCvv2() != null && bean.getCvv2() != "")
                    bean.setCvv2(Demo.AESEncode(bean.getAesKey(), bean.getCvv2().replace(" ", "")));
                if (bean.getValidPeriod() != null && bean.getValidPeriod() != "")
                    bean.setValidPeriod(Demo.AESEncode(bean.getAesKey(), bean.getValidPeriod().replace(" ", "")));
            }
            requestParams = m.convertValue(bean, Map.class);
            requestParams.remove("scustomerName");
            requestParams.put("sCustomerName", bean.getSCustomerName());
            requestParams = Demo.removeNull(requestParams);
            String sb = Demo.createLinkString(requestParams);
            String sign = Demo.generateSign(bean, sb);
            requestParams.put("sign", sign);
            requestParams.remove("relativePath");
            log.info("-----------------????????????:--------------------");
            log.info(String.valueOf(requestParams));
            post = HttpClientUtil.post(bean.getPostUrl().replace(" ", ""), requestParams);
            log.info("api??????:" + post);
            record = FontColorContent.getColorStr("??????api?????????:" + sb, ColorEnum.INFO);
            if (!Tools.isEmpty(post) && post.contains("submit();")) {
                String newS = "";
                if (post.contains("<form")) {
                    int index = post.indexOf("<form") + 5;
                    newS = post.substring(0, index) + " target='_blank'";
                    newS += post.substring(index);
                }
                map.put("link", newS);
            } else {
                map.put("link", post);
            }
//			Tools.redirect(httpResponse, map.get("link")+"");
            if (Tools.isEmpty(post))
                record += "??????????????????";
            else if (!post.contains("sign") || post.contains("sign????????????"))
                record += "?????????sign";
            else {
                if (!Tools.isEmpty(post) && !post.contains("submit();")) {
                    requestMap = ObjectMapperUtil.readValue(post, Map.class);
                    check = Demo.createLinkString(requestMap);
                }
            }
            /**
             * ??????bankpay?????????dealcode???dealmsg
             */
            if (!bean.getPostUrl().contains("bankPay")) {
                response = !Tools.isEmpty(post) ? new Gson().fromJson(post, Response.class) : null;
            }
            if (!Tools.isEmpty(post) && !post.isEmpty() && bean.getService().equals("getCodeUrl")) {
                if (response.getCodeUrl() != null) {
                    String genrateBasePath = session.getServletContext().getRealPath("/"); // ??????????????????
                    generateQrode(response.getCodeUrl(), genrateBasePath + "qrCode.png");
                    String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()
                            + request.getContextPath() + "/";
                    map.put("codePath", basePath + "qrCode.png");
                } else {
                }
            }
            String orderNo = bean.getOrderNo().replace(" ", "");
            if (bean.getService().equals("refundOrder"))
                orderNo = "?????????" + bean.getRefundNo().trim();
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            record += FontColorContent.getColorStr(e.toString(), ColorEnum.INFO);
        } finally {
            record = FontColorContent.getColorStr("????????????:" + bean.getPostUrl() + "-------?????????" + new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()) + "------???????????????:" + bean.getOrderNo() + record, ColorEnum.INFO);
            if (bean.getPostUrl().contains("bankPay")) {
                record += FontColorContent.getColorStr("??????????????????:" + post.replace("submit();", "--------"), ColorEnum.INFO);
            } else {
                record += FontColorContent.getColorStr("?????????????????????" + post, ColorEnum.INFO);
                if (!Tools.isEmpty(requestMap) && requestMap.get("sign") != null)
                    record += FontColorContent.getColorStr("?????????????????????:" + check + (requestMap != null ? Demo.checkSign(bean, check, requestMap.get("sign") + "") == true ? "<br>??????????????????" : "<br>??????????????????" : ""), ColorEnum.INFO);
                if (response != null && response.getDealCode() != null) {
                    record += FontColorContent.getColorStr(RespCode.getCode(response.getDealCode()) + (bean.getPostUrl().contains("quickPay") ? response.getNeedSms() : ""), ColorEnum.INFO);
                }
            }
            map.put("result", record);
            if (!Tools.isEmpty(record))
                FileUtil.write(session.getServletContext().getRealPath("/") + "WEB-INF/testHisoty.txt", record + "<br/>");
            return new Gson().toJson(map);
        }
    }

    @ResponseBody
    @RequestMapping("/uppoffPayGate")
    public Object uppoffPayGate(Trade bean, HttpSession session) throws Exception {
        Map map = new HashMap();
        String post = "";
        String record = "";
        String basePath = session.getServletContext().getRealPath("/"); // ??????????????????
        Map<String, Object> requestMap = null;
        String check = "";
        String orderNo = "";
        String json = "";
        Response response = null;
        try {
            Map<String, Object> parma = new HashMap<>();
            Map<String, Object> requestParams = new HashMap<>();
            if (bean.getPostUrl() != null && !bean.getPostUrl().contains("findPwd") && !bean.getPostUrl().contains("registerUser") && !bean.getPostUrl().contains("sendMsgApi") && bean.getPhone() != null)
                bean.setPhone(Demo.AESEncode(bean.getAesKey(), bean.getPhone().replace(" ", "")));
            requestParams = m.convertValue(bean, Map.class);
            requestParams.remove("morderNo");
            requestParams.put("mOrderNo", bean.getMOrderNo());
            parma.put("signType", bean.getSignType());
            parma.put("charSet", bean.getCharSet());// ????????????????????????
            parma.put("timestamp", new Date().getTime() + "");
            parma.put("version", bean.getVersion());// ?????????????????????
            requestParams = Demo.uppRemoveNull(requestParams);
            Gson gson = new Gson();
            if (bean.getService() != null && bean.getService().contains("addSplitOrder")) {
                List list = new ArrayList<>();
                list.add(requestParams);
                json = gson.toJson(list);
            } else
                json = gson.toJson(requestParams);
            parma.put("bizContent", URLEncoder.encode(json, "UTF-8"));
            if (bean.getService() != null)
                parma.put("service", bean.getService());
            String prestr = Demo.createLinkString(parma); // ???????????????????????????????????????=???????????????????????????&???????????????????????????
            String sign = null;
            if (bean.getSignType().equals("2"))
                sign = RSA.sign(prestr, bean.getPrivateKey(), "UTF-8");
            parma.put("sign", sign);
            requestParams.remove("relativePath");
            post = HttpClientUtil.post(bean.getPostUrl(), parma);
            log.info("api??????:" + post);
            orderNo = bean.getMOrderNo() != null ? bean.getMOrderNo() : (bean.getBankCardNo() != null ? bean.getBankCardNo() : bean.getIdCardNo());
            if (bean.getPostUrl().contains("manageSplitOrder"))
                orderNo = bean.getStageOrderId() + "  " + bean.getOrgNo();
            //bankPay
            if (!Tools.isEmpty(post) && post.contains("submit();")) {
                String newS = "";
                if (post.contains("<form")) {
                    int index = post.indexOf("<form") + 5;
                    newS = post.substring(0, index) + " target='_blank'";
                    newS += post.substring(index);
                }
                map.put("link", newS);
            }
            if (Tools.isEmpty(post))
                record += "??????????????????";
            else if (!Tools.isEmpty(post) && !post.contains("sign") || post.contains("sign????????????"))
                record += "?????????sign";
            else {
                if (!Tools.isEmpty(post) && !post.contains("submit();"))
                    requestMap = ObjectMapperUtil.readValue(post, Map.class);
            }
            response = new Gson().fromJson(post, Response.class);
            //QR code
            if (bean.getPostUrl().equals("nativePay")) {
                generateQrode(response.getCodeUrl(), basePath + "qrCode.png");
                map.put("codePath", bean.getRelativePath() + "qrCode.png");
            }
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            record += FontColorContent.getColorStr(e.toString(), ColorEnum.INFO);
        } finally {
            record = FontColorContent.getColorStr("????????????:" + bean.getPostUrl() + "-------?????????" + new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()) + "------???????????????:" + orderNo + record, ColorEnum.INFO);
            record += FontColorContent.getColorStr("??????api bizContent:" + json, ColorEnum.INFO);
            if (!Tools.isEmpty(post) && post.contains("submit();")) {
                record += FontColorContent.getColorStr("??????????????????:" + post.replace("submit();", "--------"), ColorEnum.INFO);
            } else {
                String publicKey = bean.getPublicKey().replaceAll(" ", "+");
                String singStr = Demo.createLinkString(UppDemo.paraFilter(requestMap)); //???????????????????????????????????????=???????????????????????????&???????????????????????????
                record += FontColorContent.getColorStr("?????????????????????" + post, ColorEnum.INFO);
                if (!Tools.isEmpty(requestMap) && requestMap.get("sign") != null)
                    record += FontColorContent.getColorStr("?????????????????????:" + singStr + (UppDemo.verifySign(URLDecoder.decode(requestMap.get("sign") + "", "utf-8"), publicKey, singStr) == true ? "<br>??????????????????" : "<br>??????????????????"), ColorEnum.INFO);
                if (response != null && response.getCode() != null)
                    record += FontColorContent.getColorStr(RespCode.getCode(response.getCode()).toString(), ColorEnum.INFO);
            }
            if (!Tools.isEmpty(record))
                FileUtil.write(basePath + "WEB-INF/upptestHistory.txt", record + "<br/>");
            map.put("result", record);
            return new Gson().toJson(map);
        }
    }

    @ResponseBody
    @RequestMapping("/uppgetAccount")
    public String uppgetAccount(ModelMap map, Trade bean) throws Exception {
        List<String> list;
        list = new ArrayList<>();
        String post = "";
        try {
            Map<String, Object> parma = new HashMap<>();
            Map<String, Object> requestParams = new HashMap<>();
            requestParams = m.convertValue(bean, Map.class);
            requestParams.put("mOrderNo", bean.getMOrderNo());
            parma.put("signType", bean.getSignType());
            parma.put("charSet", bean.getCharSet());// ????????????????????????
            parma.put("timestamp", new Date().getTime() + "");
            parma.put("version", bean.getVersion());// ?????????????????????
            requestParams = Demo.uppRemoveNull(requestParams);
            Gson gson = new Gson();
            String json = gson.toJson(requestParams);
            parma.put("bizContent", URLEncoder.encode(json, "UTF-8"));
            String prestr = Demo.createLinkString(parma); // ???????????????????????????????????????=???????????????????????????&???????????????????????????
            String privateKey = bean.getPrivateKey().replaceAll(" ", "+");
            String sign = null;
            if (bean.getSignType().equals("2"))
                sign = RSA.sign(prestr, bean.getPrivateKey(), "UTF-8");
            parma.put("sign", sign);
            post = HttpClientUtil.post(bean.getPostUrl(), parma);
            if (bean.getPostUrl().contains("orderSearch")) {
                Response response;
                response = !Tools.isEmpty(post) ? new Gson().fromJson(post, Response.class) : null;
                if (response != null && response.getDealCode() != null)
                    post += "<br>" + RespCode.getCode(response.getCode()) + response.getOrderFinishStatus();
            }
            if (Tools.isEmpty(post))
                post = "??????????????????";
            else if (!post.contains("sign"))
                post += "?????????sign";
            else {
                Map<String, Object> requestMap = ObjectMapperUtil.readValue(post, Map.class);
                String publicKey = bean.getPublicKey().replaceAll(" ", "+");
                String singStr = Demo.createLinkString(UppDemo.paraFilter(requestMap)); //???????????????????????????????????????=???????????????????????????&???????????????????????????
                if (!Tools.isEmpty(requestMap) && requestMap.get("sign") != null)
                    post += "<br>?????????????????????:" + singStr + (UppDemo.verifySign(URLDecoder.decode(requestMap.get("sign") + "", "utf-8"), publicKey, singStr) == true ? "<br>??????????????????" : "<br>??????????????????");
            }
            post += FontColorContent.getColorStr("<br>??????api????????????:" + json, ColorEnum.INFO);
            list.add(post);
        } catch (Exception e) {
            list.add(post + e.toString());
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return Tools.getGsonDisableHtml().toJson(list);
    }

    @ResponseBody
    @RequestMapping("/orderSearch")
    public String orderSearch(ModelMap map, Trade bean) throws Exception {
        List<String> list;
        list = new ArrayList<>();
        String post = "";
        try {
            Map<String, Object> requestParams = new HashMap<>();
            requestParams = m.convertValue(bean, Map.class);
            if (bean.getBankCardNo() != null)
                requestParams.put("bankCardNo", Demo.AESEncode(bean.getAesKey(), bean.getBankCardNo().replace(" ", "")));
            if (bean.getUserName() != null)
                requestParams.put("userName", Demo.AESEncode(bean.getAesKey(), bean.getUserName().replace(" ", "")));
            if (bean.getPhone() != null)
                requestParams.put("phone", Demo.AESEncode(bean.getAesKey(), bean.getPhone().replace(" ", "")));
            requestParams.remove("mrefundOrderNo");
            requestParams.put("mRefundOrderNo", bean.getMRefundOrderNo());
            requestParams = Demo.removeNull(requestParams);
            String sb = Demo.createLinkString(requestParams);
            requestParams.put("sign", Demo.generateSign(bean, sb));
            long time = new Date().getTime();
//			bean.setSignType("null");
            post = HttpClientUtil.post(bean.getPostUrl().replace(" ", ""), requestParams);
            Response response;
            response = !Tools.isEmpty(post) ? new Gson().fromJson(post, Response.class) : null;
            if (Tools.isEmpty(post))
                post = "??????????????????";
            else if (!post.contains("sign") || post.contains("sign????????????"))
                post += "?????????sign";
            else {
                Map<String, Object> requestMap = ObjectMapperUtil.readValue(post, Map.class);
                String check = Demo.createLinkString(requestMap);
                if (!Tools.isEmpty(requestMap) && requestMap.get("sign") != null)
                    post += "<br>api????????????????????????:" + check + (Demo.checkSign(bean, check, requestMap.get("sign").toString()) == true ? "<br>??????????????????" : "<br>??????????????????");
            }
            post += FontColorContent.getColorStr("<br>??????api?????????:" + sb, ColorEnum.INFO);
            if (response != null && response.getDealCode() != null)
                post += "<br>" + (bean.getService().equals("quickPaySameSearch") || bean.getService().equals("paySearchOrder") || bean.getService().equals("payForAnotherOneSearch")
                        ? RespCode.getCode(response.getDealCode()) +
                        (bean.getService().equals("paySearchOrder") ? response.getOrderStatus() : (bean.getService().equals("payForAnotherOneSearch") ? response.getPayForAnotherOneStatus() : "")) : "");
            list.add(post);
        } catch (Exception e) {
            list.add(post + e.toString());
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return Tools.getGsonDisableHtml().toJson(list);
    }

    @ResponseBody
    @RequestMapping(value = "/testHistory")
    public String testHistory(HttpSession session, ModelMap map) {
//		????????????war?????????????????????????????????tomcat?????????
        String basePath = session.getServletContext().getRealPath("/"); // ??????????????????
        String content = "";
        try {
            content = FileUtil.readToList(basePath + "WEB-INF/testHisoty.txt");
        } catch (FileNotFoundException e) {
            content = Tools.getGsonDisableHtml().toJson(e.toString());
        } catch (IOException e) {
            e.printStackTrace();
            content = Tools.getGsonDisableHtml().toJson(e.toString());
        }
        return content;
    }

    @ResponseBody
    @RequestMapping(value = "/upptestHistory")
    public String upptestHistory(HttpSession session, ModelMap map) {
//		????????????war?????????????????????????????????tomcat?????????
        String basePath = session.getServletContext().getRealPath("/"); // ??????????????????
        String content = "";
        try {
            content = FileUtil.readToList(basePath + "WEB-INF/upptestHistory.txt");
        } catch (FileNotFoundException e) {
            content = Tools.getGsonDisableHtml().toJson(e.toString());
        } catch (IOException e) {
            e.printStackTrace();
            content = Tools.getGsonDisableHtml().toJson(e.toString());
        }
        return content;
    }
}
