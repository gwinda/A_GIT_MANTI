package com.workspace.server.configuration

import org.python.util.PythonInterpreter

/**
 * Created by CHENMA11 on 4/15/2018.
 */
class testPython {
    public static void main(String[] args) {
        try {
            Process proc =Runtime.getRuntime().exec("D:/OLLB2B_Migration/Scraping-master1/Scraping-master/pp2.bat  https://item.jd.com/4613591.html");
            def jj=proc.waitFor();
            println proc.exitValue()
            println proc.errorStream
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("执行命令：" + "notepad" + "错误");
        }
    }
    private static String getPara(String string) {
        // TODO Auto-generated method stub
        return null;
    }


}
