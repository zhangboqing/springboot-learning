package com.zbq;

import org.junit.Test;

import java.math.BigDecimal;
import java.util.*;

/**
 * Created by zhangboqing on 2017/9/19.
 */
public class CommonTest {

    @Test
    public void listNullIteratorTest() {

        //list为null时，遍历报空指针异常
        List<String> list = null;

        for (String s : list) {
            System.out.println(s);
        }
    }


    @Test
    public void linkedHashMapTest() {

        //LinkedHashMap 记录插入顺序

        System.out.println("*************************LinkedHashMap*************");
        Map<Integer,String> map = new LinkedHashMap<Integer,String>();
        map.put(6, "apple");
        map.put(3, "banana");
        map.put(2,"pear");

        for (Iterator it = map.keySet().iterator(); it.hasNext();)
        {
            Object key = it.next();
            System.out.println( key+"="+ map.get(key));
        }

        System.out.println("*************************HashMap*************");
        Map<Integer,String> map1 = new HashMap<Integer,String>();
        map1.put(6, "apple");
        map1.put(3, "banana");
        map1.put(2,"pear");

        for (Iterator it =  map1.keySet().iterator();it.hasNext();)
        {
            Object key = it.next();
            System.out.println( key+"="+ map1.get(key));
        }
    }


    @Test
    public void run() {

        BigDecimal bigDecimal = new BigDecimal("-8.86");


//        BigDecimal discount = bigDecimal.divide(new BigDecimal(10)).setScale(2, BigDecimal.ROUND_UP);  //进位
        BigDecimal discount = bigDecimal.divide(new BigDecimal(10)).setScale(2, BigDecimal.ROUND_DOWN);  //截取
//        BigDecimal discount = bigDecimal.divide(new BigDecimal(10)).setScale(2, BigDecimal.ROUND_CEILING);   //正数 进位  负数截取
//        BigDecimal discount = bigDecimal.divide(new BigDecimal(10)).setScale(2, BigDecimal.ROUND_FLOOR);  //负数 进位  正数截取
//        BigDecimal discount = bigDecimal.divide(new BigDecimal(10)).setScale(2, BigDecimal.ROUND_HALF_UP);  //>=5 进位
//        BigDecimal discount = bigDecimal.divide(new BigDecimal(10)).setScale(2, BigDecimal.ROUND_HALF_DOWN);  //>5 进位
//        BigDecimal discount = bigDecimal.divide(new BigDecimal(10)).setScale(2, BigDecimal.ROUND_HALF_EVEN);  //偶数进位 奇数截取

        System.out.println(discount);
    }


    @Test
    public void run2() {

        ArrayList<String> list = new ArrayList<>();

        list.add("a");
        list.add("b");
        list.add(null);


        System.out.println(list.contains(null));


    }



}
