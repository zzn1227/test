package com.test;
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.regex.Pattern;

public class FilterHtml {

    /**
     * @param args
     * @throws IOException
     */
    public static void main(String[] args) throws IOException {
        String file = "d:\\test1.txt";
        BufferedReader obr = new BufferedReader(new InputStreamReader(new FileInputStream(file)));
        String line = obr.readLine();
        while (line != null && !line.equals("")) {
            String desc = Html2Text(line);
            System.out.println(desc);
            line = obr.readLine();
        }

    }

    public static String Html2Text(String inputString) {
        char data[] = { 1 };
        String split = new String(data);

        String htmlStr = inputString; // 含html标签的字符串
        String textStr = "";
        java.util.regex.Pattern p_script;
        java.util.regex.Matcher m_script;
        java.util.regex.Pattern p_style;
        java.util.regex.Matcher m_style;
        java.util.regex.Pattern p_html;
        java.util.regex.Matcher m_html;

        java.util.regex.Pattern p_html1;
        java.util.regex.Matcher m_html1;

        try {
            String regEx_script = "<[//s]*?script[^>]*?>[//s//S]*?<[//s]*?///[//s]*?script[//s]*?>"; // 定义script的正则表达式{或<script[^>]*?>[//s//S]*?<///script>
            String regEx_style = "<[//s]*?style[^>]*?>[//s//S]*?<[//s]*?///[//s]*?style[//s]*?>"; // 定义style的正则表达式{或<style[^>]*?>[//s//S]*?<///style>
            String regEx_html = "<[^>]+>"; // 定义HTML标签的正则表达式
            String regEx_html1 = "<[^>]+";
            p_script = Pattern.compile(regEx_script, Pattern.CASE_INSENSITIVE);
            m_script = p_script.matcher(htmlStr);
            htmlStr = m_script.replaceAll(split); // 过滤script标签

            p_style = Pattern.compile(regEx_style, Pattern.CASE_INSENSITIVE);
            m_style = p_style.matcher(htmlStr);
            htmlStr = m_style.replaceAll(split); // 过滤style标签

            p_html = Pattern.compile(regEx_html, Pattern.CASE_INSENSITIVE);
            m_html = p_html.matcher(htmlStr);
            htmlStr = m_html.replaceAll(split); // 过滤html标签

            p_html1 = Pattern.compile(regEx_html1, Pattern.CASE_INSENSITIVE);
            m_html1 = p_html1.matcher(htmlStr);
            htmlStr = m_html1.replaceAll(split); // 过滤html标签

            textStr = htmlStr;

        } catch (Exception e) {
            System.err.println("Html2Text: " + e.getMessage());
        }

        textStr = textStr.replaceAll("&nbsp;", split);

        java.util.regex.Pattern p_last;
        java.util.regex.Matcher m_last;

        p_last = Pattern.compile("" + split + "[ ]+");
        m_last = p_last.matcher(textStr);
        textStr = m_last.replaceAll(split); // 过滤script标签

        p_last = Pattern.compile("[" + split + "]+[ ]*");
        m_last = p_last.matcher(textStr);
        textStr = m_last.replaceAll(split); // 过滤script标签

        textStr = textStr.replaceAll("\\s+", " ");

        p_last = Pattern.compile("[" + split + "]+");
        m_last = p_last.matcher(textStr);
        textStr = m_last.replaceAll("   "); // 过滤script标签

        return textStr;// 返回文本字符串
    }

}
