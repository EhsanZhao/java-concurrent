package com.ehsanzhao.juc.completableFuture;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ThreadLocalRandom;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

/**
 * @author zhaoyuan
 * @date 2023/3/3
 */
public class CompletableFutureMallDemo {

    private static List<NetMall> netMalls = new ArrayList<NetMall>(){{
       add( new NetMall("tb"));
       add( new NetMall("jd"));
       add( new NetMall("dd"));
       add( new NetMall("pdd"));
       add( new NetMall("mt"));
       add( new NetMall("ymx"));
    }};

    private static List<String> stepByStep(String productName){
        List<String> list = netMalls.stream().map(netMall -> String.format("%s in %s's price is %.2f", productName, netMall.getMallName(), netMall.calcPrice(productName))).collect(Collectors.toList());
        return list;
    }

    private static List<String> stepByCompletableFuture(String productName){
        List<String> list = netMalls.stream()
                .map(netMall -> CompletableFuture.supplyAsync(()-> String.format("%s in %s's price is %.2f", productName, netMall.getMallName(), netMall.calcPrice(productName))
        ))
                .collect(Collectors.toList())
                .stream().map(stringCompletableFuture -> stringCompletableFuture.join()).collect(Collectors.toList());
        return list;
    }

    public static void main(String[] args) {
        long startTime = System.currentTimeMillis();
//        List<String> list = stepByStep("《mysql》");  //6s
        List<String> list = stepByCompletableFuture("《mysql》");  //1s
        for (String element : list) {
            System.out.println(element);
        }
        long endTime = System.currentTimeMillis();
        System.out.println("程序运行时间：" + (endTime - startTime) + "ms");

    }

}


@Data
@AllArgsConstructor
@NoArgsConstructor
class NetMall
{
    private String mallName;

    public double calcPrice(String productName) {
        double price = new Random().nextDouble()*2 + productName.charAt(0);
        try {
            TimeUnit.SECONDS.sleep(1);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return price;
    }


}
