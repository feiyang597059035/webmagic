package com.carwel.webmagic.util;


import com.alibaba.fastjson.JSONObject;
import org.apache.http.HttpEntity;
import org.apache.http.HttpStatus;

import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;

import org.apache.http.util.EntityUtils;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.nodes.Node;
import org.jsoup.select.Elements;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.util.StringUtils;


import java.io.IOException;

import java.net.URISyntaxException;

import java.util.List;
import java.util.Map;


public class HttpClientUtils {

    private static Logger logger = LoggerFactory.getLogger(HttpClientUtils.class); // 日志记录

    private static RequestConfig requestConfig = null;

    static
    {
        // 设置请求和传输超时时间
        requestConfig = RequestConfig.custom().setSocketTimeout(2000).setConnectTimeout(2000).build();
    }

    /**
     * post请求传输json参数
     * @param url  url地址
     * @param
     * @return
     */
    public static String httpPost(String url, JSONObject jsonParam)
    {
        // post请求返回结果
        CloseableHttpClient httpClient = HttpClients.createDefault();
        String str = "";
        HttpPost httpPost = new HttpPost(url);
        // 设置请求和传输超时时间
        httpPost.setConfig(requestConfig);
        try
        {
            if (null != jsonParam)
            {
                // 解决中文乱码问题
                StringEntity entity = new StringEntity(jsonParam.toString(), "utf-8");
                entity.setContentEncoding("UTF-8");
                entity.setContentType("application/json");
                httpPost.setEntity(entity);
            }
            CloseableHttpResponse result = httpClient.execute(httpPost);
            // 请求发送成功，并得到响应
            if (result.getStatusLine().getStatusCode() == HttpStatus.SC_OK)
            {

                try
                {
                    // 读取服务器返回过来的json字符串数据
                    str = EntityUtils.toString(result.getEntity(), "utf-8");


                }
                catch (Exception e)
                {
                    logger.error("post请求提交失败:" + url, e);
                }
            }
        }
        catch (IOException e)
        {
            logger.error("post请求提交失败:" + url, e);
        }
        finally
        {
            httpPost.releaseConnection();
        }
        return str;
    }

    /**
     * post请求传输String参数 例如：name=Jack&sex=1&type=2
     * Content-type:application/x-www-form-urlencoded
     * @param url            url地址
     * @param strParam       参数
     * @return
     */
    public static String httpPost(String url, String strParam)
    {
        // post请求返回结果
        CloseableHttpClient httpClient = HttpClients.createDefault();
        String str = "";
        HttpPost httpPost = new HttpPost(url);
        httpPost.setConfig(requestConfig);
        try
        {
            if (null != strParam)
            {
                // 解决中文乱码问题
                StringEntity entity = new StringEntity(strParam, "utf-8");
                entity.setContentEncoding("UTF-8");
                entity.setContentType("application/x-www-form-urlencoded");
                httpPost.setEntity(entity);
            }
            CloseableHttpResponse result = httpClient.execute(httpPost);
            // 请求发送成功，并得到响应
            if (result.getStatusLine().getStatusCode() == HttpStatus.SC_OK)
            {

                try
                {
                    // 读取服务器返回过来的json字符串数据
                    str = EntityUtils.toString(result.getEntity(), "utf-8");

                }
                catch (Exception e)
                {
                    logger.error("post请求提交失败:" + url, e);
                }
            }
        }
        catch (IOException e)
        {
            logger.error("post请求提交失败:" + url, e);
        }
        finally
        {
            httpPost.releaseConnection();
        }
        return str;
    }

    /**
     * 发送get请求
     * @param url 路径
     * @return
     */
    public static String httpGet(String url, Map<String,Object> paramMap)
    {
        String str="";
        // get请求返回结果
        CloseableHttpClient client = HttpClients.createDefault();
        URIBuilder uriBuilder = null;
        try {
            uriBuilder = new URIBuilder(url);
            HttpGet request = new HttpGet(uriBuilder.build());
            request.setConfig(requestConfig);
            try
            {
                CloseableHttpResponse response = client.execute(request);

                // 请求发送成功，并得到响应
                if (response.getStatusLine().getStatusCode() == HttpStatus.SC_OK)
                {
                    // 读取服务器返回过来的json字符串数据
                    HttpEntity entity = response.getEntity();
                    str  = EntityUtils.toString(entity, "utf-8");
                }
                else
                {
                    logger.error("get请求提交失败:" + url);
                }
            }
            catch (IOException e)
            {
                logger.error("get请求提交失败:" + url, e);
            }
            finally
            {
                request.releaseConnection();
            }
        } catch (URISyntaxException e) {
            e.printStackTrace();
        }

        return str;
    }

    public  static  void main (String[] args){

       String url="http://www.jianlaixiaoshuo.com/";
      String html=  httpGet(url,null);
        if (!StringUtils.isEmpty(html)){
            //解析html获取Document
            Document doc = Jsoup.parse(html);
            String title=doc.title();
            String updateTime=doc.head().select("meta[property=og:novel:update_time]").attr("content");
            String latestChapterName=doc.head().select("meta[property=og:novel:latest_chapter_name]").attr("content");
            String latestChapterUrl=doc.head().select("meta[property=og:novel:latest_chapter_url]").attr("content");
           /* //获取某个元素
            Elements dl=doc.select("dl.chapterlist");*/
            Elements dd=doc.select("dl.chapterlist dd");
            //遍历 dd
            //获取迭代器
            for (int i=49;i<dd.size();i++){
                Element element = dd.get(i);
                 List<Node> childNodes=element.childNodes();
               String attributes= childNodes.get(0).attr("href");
               String subUrl=url+attributes;
               String subhtml=httpGet(subUrl,null);
                Document subDoc = Jsoup.parse(subhtml);
               String subTitle=subDoc.select("#BookCon h1").text();
               String subContext=subDoc.select("#BookText ").html();

                break;


            }



            String yy="33";
        }

    }



}
