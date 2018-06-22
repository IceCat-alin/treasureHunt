package com.treasure.hunt.task;

import com.treasure.hunt.framework.utils.DateUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.util.Date;

/**
 * @Description 类描述：定时任务范例
 * @Author 创建人：linying
 * @Date 创建时间：2018/4/18 10:07
 * @Version 版本号：v1.0.0
 */
@Component
public class ScheduledTasks {
    private static final Logger LOG = LoggerFactory.getLogger(ScheduledTasks.class);

    /**
     * 单位（毫秒）
     */
    public final static long ONE_MINUTE = 60 * 1000;

    /**
     * corn从左到右（用空格隔开）：秒 分 小时 月份中的日期 月份 星期中的日期 年份(可选)
     */
//    @Scheduled(cron = "0 */1 * * * ?")
    public void reportCurrentTime() {
        LOG.info("任务1 {}", DateUtil.formatDatetimeToStr(new Date()));
    }

    /**
     * 每隔一分钟
     */
//    @Scheduled(fixedDelay = ONE_MINUTE)
    public void fixedDelayJob() {
        LOG.info("任务2 {}", DateUtil.formatDatetimeToStr(new Date()));
    }

    /**
     * 该方法执行完一分钟后再执行
     */
//    @Scheduled(fixedRate = ONE_MINUTE)
    public void fixedRateJob() {
        LOG.info("任务3 {}", DateUtil.formatDatetimeToStr(new Date()));
    }

}
