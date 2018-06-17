package com.mr.bomkpi.config;

import ch.qos.logback.classic.LoggerContext;
import ch.qos.logback.classic.joran.JoranConfigurator;
//import cn.fcgyl.utils.SkuClientSetting;
//import cn.fcgyl.zookeeper.ZookeeperManager;
//import cn.fcgyl.zookeeper.config.ConfigService;
//import cn.fcgyl.zookeeper.config.IExternalConfig;
import com.mr.bomkpi.manager.RedisManager;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.StringUtils;

import java.lang.management.ManagementFactory;
import java.net.URL;
import java.util.Properties;

//public class AppConfig implements IExternalConfig {
public class AppConfig {

    private Logger logger = LoggerFactory.getLogger("AppConfig");

    private static AppConfig appConfig = new AppConfig();
    private String ENV = "dev";
    private Properties configProp;
//    private ConfigService zooKeeperConfigService;
//
//    public synchronized ConfigService getZooKeeperConfigService() {
//        if (zooKeeperConfigService == null) {
//            zooKeeperConfigService = new ConfigService(ZookeeperManager.getInstance(), this);
//        }
//        return zooKeeperConfigService;
//    }

    private AppConfig() {
        this.ENV = System.getProperty("fc.env", "dev");
        configProp = new Properties();
        try {
            configProp.load(getClass().getClassLoader().getResourceAsStream("AppConfig." + this.ENV + ".properties"));
//            SkuClientSetting.setZkHost(this.getConfigFromPropertyFile("zookeeper.host"));
//            SkuClientSetting.setZkPath(this.getConfigFromPropertyFile("zookeeper.path"));
        } catch (Exception e) {
            logger.error("AppConfig error => {}", e.getMessage(), e);
            e.printStackTrace();
        }
    }

    public static AppConfig getInstance() {
        return appConfig;
    }

    public String getEnv() {
        return ENV;
    }

    public String getConfigFromPropertyFile(String key) {
        String v = System.getProperty(key);
        if (v == null) {
            v = configProp.getProperty(key);
        }

        return v;
    }

    public String getProperty(String key) {
//        return getZooKeeperConfigService().getConfig(key);
        return null;
    }

    public String getPropertyOrDefault(String key, String defaultValue) {
        String val = getProperty(key);
        if (StringUtils.isEmpty(val)) return defaultValue;

        return val;
    }

    public void configLogback() {
        try {
//            printBanner();
            LoggerContext context = (LoggerContext) LoggerFactory.getILoggerFactory();

            String resource = "logback-config-" + this.ENV + ".xml";
            URL logbackConfig = getClass().getClassLoader().getResource(resource);
            if (logbackConfig != null) {
                JoranConfigurator jc = new JoranConfigurator();
                jc.setContext(context);
                context.reset();
                jc.doConfigure(logbackConfig);
            }
        } catch (Exception e) {
            e.printStackTrace();
            logger.error("Logger初始化失败: " + e.getMessage(), e);
        }
    }

    private void printBanner() {
        long st = System.currentTimeMillis();
        String containerName = ManagementFactory.getRuntimeMXBean().getName();
        String warningMsg = "";
        if ((System.currentTimeMillis() - st) > 500) {
            warningMsg = "InetAddress.getLocalHost().getHostName()慢，检查hosts文件/路由表。";
        }
        String id = "BANNER_" + containerName;
        if (!RedisManager.getInstance().exists(id)) {
            System.out.println();
            System.out.println();
            System.out.println(" _______   ______   ___________    ____  __          ___      .______   .______   ");
            System.out.println("|   ____| /      | /  _____\\   \\  /   / |  |        /   \\     |   _  \\  |   _  \\  ");
            System.out.println("|  |__   |  ,----'|  |  __  \\   \\/   /  |  |       /  ^  \\    |  |_)  | |  |_)  | ");
            System.out.println("|   __|  |  |     |  | |_ |  \\_    _/   |  |      /  /_\\  \\   |   ___/  |   ___/  ");
            System.out.println("|  |     |  `----.|  |__| |    |  |     |  `----./  _____  \\  |  |      |  |      ");
            System.out.println("|__|      \\______| \\______|    |__|     |_______/__/     \\__\\ | _|      | _|              Starting...       [" + containerName + ", " + this.ENV + "]");
            System.out.println();
            System.out.println(warningMsg);
            System.out.println();
            RedisManager.getInstance().setex(id, 60, "startup");
        }
    }


}
