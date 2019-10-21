package com.lss.teacher_manager.utils;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.beanutils.ConversionException;
import org.apache.commons.beanutils.ConvertUtils;
import org.apache.commons.beanutils.Converter;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.springframework.util.StringUtils;

import javax.servlet.http.HttpServletRequest;
import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class SysUtils {

    public static Map<String, Object> agentMap = new HashMap<String, Object>();
    public static Map<String, Object> agentEmailMap = new HashMap<String, Object>();
    public static Map<String, Object> polMap = new HashMap<String, Object>();
    public static AtomicInteger REQUEST_COUNT = new AtomicInteger(0);

    static {
        agentMap.put("blue freight", "Blue Freight Service GmbH \n\r" +
                "Conline GmbH, Grossmannstrasse 129, 20459 Hamburg \n\r " +
                "Contactor: Nils Braumann\n\r" +
                "Tel: +49 30 5213 5498 109\n\r" +
                "Email: nils.braumann@bluefreightservice.com");
        agentMap.put("OTS", "O.T.S. ASTRACON International air + sea forwarder GmbH \n\r" +
                "Am Speicher XI, Nr. 3 28217 Bremen / Germany \n\r" +
                "felix.hadzic@bre.astracon.de \n\r" +
                "CC:bartek.szyluk@nbwestrail.com");
        agentMap.put("Samskip logistics", "Samskip GmbH\n\r" +
                "Samskip Logistics Benelux BV\n\r" +
                "Waalhaven Oostzijde 81 3087 BM Rotterdam\n\r" +
                "Emma Jin\n\r" +
                "westrail@samskip.com ");
        agentMap.put("LSH", "LANGOWSKI SHIPPING \r\n" +
                "ul. Hutnicza 16, 81-061 Gdynia, Poland \r\n" +
                "email: Poland@trainfromchina.eu \r\n" +
                "tel.: +48 58 625-92-16 ext. 21");
        agentMap.put("PEKAS", "PEKAES SA\n\r" +
                "Pekaes:Stanislaw  \n\r" +
                "+48 600 007 046 \n\r" +
                "stanislaw.ostrowski@pekaes.pl;    \n" +
                "CC:train@pekaes.pl &amp; bartek.szyluk@nbwestrail.com");
        agentMap.put("VGL", "VGL  Sp. z o. o.  \n\r" +
                "Contactor:Marta Masiak\n\r" +
                "M: +48 665 667 807\n\r" +
                "E: Vgl_rail@vgl-group.com\n\r" +
                "CC: Piotr Kowalski, M: +48 669 770 994\n\r" +
                "CC: Kamil Chroscielewski , M: +48 607 760 513");
        agentMap.put("CFS", "CFS Česká Třebová s.r.o.  \n\r" +
                "Contactor:Pavel Švanda\n\r" +
                "Moravská 929|560 02 Česká Třebová\n\r" +
                "M: +420 725 243 144\n\r" +
                "E:  svanda@cfsct.cz\n\r");
        agentMap.put("Austromar", "Austromar spol. s r. o.  \n\r" +
                "Frantiska Divise 988|104 00 Praha 10 – Uhrineves|Czech republic\n\r " +
                "Phone: +420 226 201 033|Fax: +420 226 201 027 \n\r " +
                "E-mail: dagmar.micova@austromar.cz ");
        agentMap.put("SOLID", "Solid Logistics \n\r " +
                "Peutestrasse 51 – 20539 Hamburg" +
                "MOBILE: +491704496584 \n\r " +
                "E-mail: rail.hamburg@solid-logistics.com ");
        agentMap.put("Austromar Kft", "Austromar Kft. \n\r" +
                "Lorántffy Zsuzsanna utca 15/b. | H-1043 Budapest | Hungary\n\r " +
                "Phone: +36-1-437-0603 | Mobile:+36-70-364-31-74 \n\r " +
                "E-mail:  attila.almasi@austromar.hu ");
        agentMap.put("CIS", "CIS Poland Sp. z o.o. \n\r" +
                "ul. Twarda 18, 00-105 Warszawa, Poland.\n\r " +
                "contact person: Marek Pomian-Biesiekierski \n\r " +
                "email: sped_waw@cislogistics.pl \n\r " +
                "tel: +48 790 364 292  ");
        agentMap.put("FPS", "FPS Famous Pacific Shipping BV \n\r" +
                "Cargo Terminal Gadering BV Propaanweg 533196KH Rotterdam\n\r " +
                "contact person: Teddy \n\r " +
                "email:  Teddy@fpsrtm.com    \n\r ");
        agentMap.put("TDD", "Ningbo TDD E-Commerce company Limited \n\r" +
                "Room,078,079,FL 3,No. 10 Building international Exhibition Center ,\n\r "
                + "No.128 Huizhan Road , Jiangdong district ,Ningbo,China \n\r ");
    }

    static {
        agentEmailMap.put("blue freight", "nils.braumann@bluefreightservice.com");
        agentEmailMap.put("OTS", "felix.hadzic@bre.astracon.de");
        agentEmailMap.put("Samskip logistics", "westrail@samskip.com");
        agentEmailMap.put("LSH", "Poland@trainfromchina.eu");
        agentEmailMap.put("PEKAS", "stanislaw.ostrowski@pekaes.pl");
        agentEmailMap.put("VGL", "Vgl_rail@vgl-group.com");
        agentEmailMap.put("CFS", "svanda@cfsct.cz");
        agentEmailMap.put("Austromar", "dagmar.micova@austromar.cz");
        agentEmailMap.put("Austromar Kft", "attila.almasi@austromar.hu");
        agentEmailMap.put("SOLID", "rail.hamburg@solid-logistics.com");
        agentEmailMap.put("CIS", "sped_waw@cislogistics.pl");
        agentEmailMap.put("FPS", "Teddy@fpsrtm.com");
        agentEmailMap.put("TDD", "");
    }


    public final static synchronized String getSysUUID() {
        return UUID.randomUUID().toString().replaceAll("-", "");
    }

    public static String getEnglishNum(Integer num) {
        String[] numsAry = {"zero", "one", "two", "three", "four", "five", "six", "seven", "eight", "nine"};
        StringBuilder sb = new StringBuilder();
        String numString = num.toString();
        for (int i = 0; i < numString.length(); i++) {
            sb.append(" " + numsAry[Integer.parseInt(numString.substring(i, i + 1))].toUpperCase());
        }
        sb.append(" ");
        return sb.toString();
    }


    public static void populate(Object obj, Map<String, Object> map) {
        ConvertUtils.register(new Converter() {
            @SuppressWarnings("rawtypes")
            @Override
            public Object convert(Class arg0, Object arg1) {
                if (arg1 == null) {
                    return null;
                }
                if (!(arg1 instanceof String)) {
                    throw new ConversionException("只支持字符串转换 !");
                }
                String str = (String) arg1;
                if (str.trim().equals("")) {
                    return null;
                }
                SimpleDateFormat sd = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                try {
                    return sd.parse(str);
                } catch (Exception e) {
                    throw new ConversionException(str + "  require yyyy-MM-dd HH:mm:ss");
                }

            }

        }, Date.class);
        if (map == null || obj == null) {
            return;
        }
        try {
            BeanUtils.populate(obj, map);
        } catch (Exception e) {
            throw new RuntimeException("Transfer Bean Exception " + e.getMessage());
        }
    }


    public static void populate(Object obj, Map<String, Object> map, String format) {
        ConvertUtils.register(new Converter() {
            @SuppressWarnings("rawtypes")
            @Override
            public Object convert(Class arg0, Object arg1) {
                if (arg1 == null) {
                    return null;
                }
                if (!(arg1 instanceof String)) {
                    throw new ConversionException("只支持字符串转换 !");
                }
                String str = (String) arg1;
                if (str.trim().equals("")) {
                    return null;
                }
                SimpleDateFormat sd = null;
                if (StringUtils.isEmpty(format)) {
                    sd = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                } else {
                    sd = new SimpleDateFormat(format);
                }
                try {
                    return sd.parse(str);
                } catch (Exception e) {
                    throw new ConversionException(str + " Date error ");
                }

            }

        }, Date.class);
        if (map == null || obj == null) {
            return;
        }
        try {
            BeanUtils.populate(obj, map);
        } catch (Exception e) {
            throw new RuntimeException("Transfer Bean Exception " + e.getMessage());
        }
    }


    public static void populate(Object obj, Map<String, Object> map, String dateFormat, String[] ignoreKey) {
        ConvertUtils.register(new Converter() {
            @SuppressWarnings("rawtypes")
            @Override
            public Object convert(Class arg0, Object arg1) {
                if (arg1 == null) {
                    return null;
                }
                if (!(arg1 instanceof String)) {
                    throw new ConversionException("只支持字符串转换 !");
                }
                String str = (String) arg1;
                if (str.trim().equals("")) {
                    return null;
                }
                SimpleDateFormat sd = null;
                if (StringUtils.isEmpty(dateFormat)) {
                    sd = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                } else {
                    sd = new SimpleDateFormat(dateFormat);
                }
                try {
                    return sd.parse(str);
                } catch (Exception e) {
                    throw new ConversionException(str + " Date error ");
                }

            }

        }, Date.class);
        if (map == null || obj == null) {
            return;
        }
        if (ignoreKey != null) {
            for (int i = 0; i < ignoreKey.length; i++) {
                map.remove(ignoreKey[i]);
            }
        }
        try {
            BeanUtils.populate(obj, map);
        } catch (Exception e) {
            throw new RuntimeException("Transfer Bean Exception " + e.getMessage());
        }
    }

    public static void populate(Object obj, Map<String, Object> map, String[] ignoreKey) {
        if (map == null || obj == null) {
            return;
        }
        if (ignoreKey != null) {
            for (int i = 0; i < ignoreKey.length; i++) {
                map.remove(ignoreKey[i]);
            }
        }
        try {
            BeanUtils.populate(obj, map);
        } catch (Exception e) {
            throw new RuntimeException("Transfer Bean Exception " + e.getMessage());
        }
    }

    public static String getRandomNumber() {
        String str = "0123456789";
        StringBuilder sb = new StringBuilder(4);
        for (int i = 0; i < 4; i++) {
            char ch = str.charAt(new Random().nextInt(str.length()));
            sb.append(ch);
        }
        String coad = sb.toString();
        return coad;
    }


    public static void setMapDefaultValue(Map map, String key, String defaultValue) {
        if (!map.containsKey(key)) {
            map.put(key, defaultValue);
        }
    }

    private static Date secondToDate(long second) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(second * 1000);//转换为毫秒
        Date date = calendar.getTime();
        return date;

    }

    public static Integer getDividend(BigDecimal yuan) {
        int fen = yuan.setScale(2, BigDecimal.ROUND_HALF_UP).multiply(new BigDecimal(100)).intValue();
        return fen;
    }

    public static String getWxPayTimeExpire() {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");
        Date now = new Date();
        Calendar nowTime = Calendar.getInstance();
        nowTime.add(Calendar.MINUTE, 15);
        String format = sdf.format(nowTime.getTime());
        return format;
    }

    public   static  Map<String, String> getAllRequestParam(final HttpServletRequest request) {
        Map<String, String> res = new HashMap<String, String>();
        Enumeration<?> temp = request.getParameterNames();
        if (null != temp) {
            while (temp.hasMoreElements()) {
                String en = (String) temp.nextElement();
                String value = request.getParameter(en);
                res.put(en, value);
            //如果字段的值为空，判断若值为空，则删除这个字段>
                if (null == res.get(en) || "".equals(res.get(en))) {
                    res.remove(en);
                }
            }
        }
        return res;
    }


    public static boolean isPhone(String phone) {
        String regex = "^((13[0-9])|(14[5,7,9])|(15([0-3]|[5-9]))|(166)|(17[0,1,3,5,6,7,8])|(18[0-9])|(19[8|9]))\\d{8}$";
        if (phone.length() != 11) {
            return false;
        } else {
            Pattern p = Pattern.compile(regex);
            Matcher m = p.matcher(phone);
            boolean isMatch = m.matches();
            return isMatch;
        }
    }

    public static Map<String,String> readStringXmlOut(String xml) {
        Map<String,String> map = new HashMap<String,String>();
        Document doc = null;
        try {
            doc = DocumentHelper.parseText(xml); // 将字符串转为XML
            Element rootElt = doc.getRootElement(); // 获取根节点
            List<Element> list = rootElt.elements();//获取根节点下所有节点
            for (Element element : list) {  //遍历节点
                map.put(element.getName(), element.getText()); //节点的name为map的key，text为map的value
            }
        } catch (DocumentException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return map;
    }


    public static void main (String[]args){

        Integer a = 1;
        System.out.println(a==1);
    }


    public static Date changeStrToDate(String timeEnd) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyyMMddHHmmss");
        Date date = null;
        try {
            date = dateFormat.parse(timeEnd);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return date;
    }
}
