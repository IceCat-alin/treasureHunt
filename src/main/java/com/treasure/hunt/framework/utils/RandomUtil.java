package com.treasure.hunt.framework.utils;

import java.util.Random;

/**
 * @description 类描述：随机编码产生工具
 * @author 创建人：liny
 * @author 修改人：liny
 * @date 修改时间：2016年10月18日 下午5:25:10
 * @description 修改备注：
 * @version 1.0.0
 */
public class RandomUtil {

    /**
     * @description 从英文字母和数字中产生随机编码
     * @param number
     *            几位
     * @return 产生的随机编码
     */
    public static String getRandomByCharAndNum(Integer number) {
        String source = "0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        Random rom = new Random();
        StringBuffer sb = new StringBuffer(100);
        for (int i = 0; i < number; i++) {
            int num = rom.nextInt(source.length());
            sb.append(source.charAt(num));
        }
        return sb.toString();
    }
    
    /**
     * @description 从数字中产生随机编码
     * @param number
     *            几位
     * @return 产生的随机编码
     */
    public static String getRandomByNum(Integer number) {
        String source = "0123456789";
        Random rom = new Random();
        StringBuffer sb = new StringBuffer(100);
        for (int i = 0; i < number; i++) {
            int num = rom.nextInt(source.length());
            sb.append(source.charAt(num));
        }
        return sb.toString();
    }
}
