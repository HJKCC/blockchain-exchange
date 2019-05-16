package com.cc;

/**
 * 描述: 启动dubbo服务方法
 * @author chencheng0816@gmail.com
 * @date 2019/4/29 11:26
 */
public class ServiceMain {

    public static void main(String[] args) {
        System.out.println("Dubbo服务正在启动...");
        com.alibaba.dubbo.container.Main.main(args);
    }

}
