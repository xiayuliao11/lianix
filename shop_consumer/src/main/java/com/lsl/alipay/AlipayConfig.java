package com.lsl.alipay;

import java.io.FileWriter;
import java.io.IOException;

public class AlipayConfig {
    // 应用ID,您的APPID，收款账号既是您的APPID对应支付宝账号
    public static String app_id = "2016092800615453";

    // 商户私钥，您的PKCS8格式RSA2私钥
    public static String merchant_private_key = "MIIEvQIBADANBgkqhkiG9w0BAQEFAASCBKcwggSjAgEAAoIBAQCB/RMruBhzjixG6CEUiJkQ1ojh9sz5QOReap9rEkNJAHOD3KyUqa5POKQFStk7KSDwXHY8LiF0xOr8EqEtt80r2zaPe93bGbp4IFRGB9CD1OWpgBP9tHERBMheIxi/xHH8ISVrsju/g0woLBkw1aM8AXBloHRPjhArRjAJNlnTN5wo7Eov6pqwDsF48zAxf8OzUCVTwVrpCkoHI+Z474bLLm9+XL2toKQXNkF7cvw2dmRU67W457czUOVNnwy90rw8Jt1kQlOTUWQuIbPH31VaxDyqEfmY/KUVdEfqrKefvklpTk/JZ8cGLc4EIq7q9PqFdXbahhyW0aLtJ+81dIjPAgMBAAECggEAHXEw8moUP7goFzdkpr35zXKKNoQsERi9oRUNXrcYPmS3xVoqTNHSBWeS3xAA/AFo0Ihgz2izcztW7hlaIHzNH+3bAczQh1vgfGz2KZaDiBiknCvCW2iPY4dtysSwSl84Yh8v1qDcH5ihm0fBvOnmuySoTJx3O721s9eF8dA5sDlE+PfVXs0ONt9T2169PlkxpQG28riVyN7cP60TPuRfTkL+r+MI/+32t71Y+K8KAsHVu+hcj7hSi2IUsqOax4aeqZNvYYWzBc3/GzMzVhoUms4kN2n1yfcLocJADScSzO8b/40Tp01JV9gJWJsvCaT8/3Y2ZLhJibuuBUCuZLgcwQKBgQD+/WpgEq/YpOV6KaSiFonqJagyEpsmr+/ePef8vW2S8Yyx2arlp2QujhrJLhnykrp4StcfrpQzTLyGvV7QkByBlvU6xODRKVsLX167t/jF7cAHiRDF7s0FEAca95zg6ElfG7nw0sk0n3NnFj+RS1Mvw0FMe1KbXkADeYGmbMcshwKBgQCCgOVZaLmr1PttEQnWGyARdiS+RGZIELyDrggOOcFPn16aV79rHccSTpuPpxiPXmhfshbsaDLHGKa3v1yhzqCrFgQFLzGxrTRHZy060PZy644N/PUb3dc6mcdgJV26pO+fM9R4jMgW1M2PIsnxUkzlYPMmhvVy+bQM2C6ItT/beQKBgQD2H3FADomBWZjOk8d/VLYGNhblTiRH8WZcY/C6ebV8Mn8VY/6a8Tm33h5otYECLTKjzclboU0XPyhx/fO46IE7bJSEB2jhO0PP9FwiYW9UzSmfnGN+SgPGsTPKidr5E/1OOhr+ItdnXcksZcMx4T0ISzWKNzcDFLRBJlVIaJLPnQKBgEZVIUXmYupwhnBpVlvFkqrWeUbxpg4AtxhgmobQKZa31UxpLgiUq8z6AGyTKXFa9/VbDW+rWHJknrU4tLufmIeb/d6taKLRLfOtb3rbU3br7EstoQu7q1sxtXnjqXgFm/64+DFf7hYf6gjSPvOH31lfvqts4KtuV9L0T1/uDtRBAoGAPK4WcIBkwOb+bVGjyV9yb7MgeHsHMEHHZQkcCKrOX/2qhhMLoDMWsqi+QDu3DXAeL9TKWfDzL7Gqf7J1YfCYsoT3WyVj1ebIGOZoA5xyF//QgrmD8Hsr4IcAt8ewAT93k5wV68m9pU4LUmXrjTNhihXgaJdbvAVfg2brB+e5xxo=";

    // 支付宝公钥,查看地址：https://openhome.alipay.com/platform/keyManage.htm 对应APPID下的支付宝公钥。
    public static String alipay_public_key = "MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEAsmV3slsEZE9HMMReA7qB7HmJTztcUWouocsYyU04nv3868lVXxcUQR9n+mkAndaATzVpofew+zV/S1RtVz1BVd+jojKcv40MbCLVTMwGavACVPKQ45hiHZ6XAfJwD0NkKQs89N00tFRKAIPOLcEpHoFwRNgdDH9IvrDnWTb/9ZEPbKJTKR4FOPYj8hFhSCR8KRSl7xpkotp7Ef8Ma9I3qg7xxQLRfhzavPABpmFiBNfqGpXKjLHMHNMfjOE5p8dDLuj/cUaSFhMuD85i9qAFvufUyLzxspE+V6s1f94k52OiL2gccQvpfwkyROJeZUEItX+uv2j2ioJ3zMmr3uFtwQIDAQAB";

    // 服务器异步通知页面路径  需http://格式的完整路径，不能加?id=123这类自定义参数，必须外网可以正常访问
    public static String notify_url = "http://yangjian.free.idcfengye.com/notify";

    // 页面跳转同步通知页面路径 需http://格式的完整路径，不能加?id=123这类自定义参数，必须外网可以正常访问
    public static String return_url = "http://yangjian.free.idcfengye.com/returnUrl";

    // 签名方式
    public static String sign_type = "RSA2";

    // 字符编码格式
    public static String charset = "utf-8";

    // 支付宝网关
    public static String gatewayUrl = "https://openapi.alipaydev.com/gateway.do";

    // 支付宝网关
    public static String log_path = "D:\\";


//↑↑↑↑↑↑↑↑↑↑请在这里配置您的基本信息↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑

    /**
     * 写日志，方便测试（看网站需求，也可以改成把记录存入数据库）
     * @param sWord 要写入日志里的文本内容
     */
    public static void logResult(String sWord) {
        FileWriter writer = null;
        try {
            writer = new FileWriter(log_path + "alipay_log_" + System.currentTimeMillis()+".txt");
            writer.write(sWord);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (writer != null) {
                try {
                    writer.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
