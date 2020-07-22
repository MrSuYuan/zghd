package com.util.assign;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class AssignUtil {

    public static int getAssign(List<Upstream> upstreams) {
        Integer probabilitySum = 100;

        int result = 0;

        Random random = new Random();
        Integer n = random.nextInt(probabilitySum); // n in [0, weightSum)
        Integer m = 0;
        for (Upstream u : upstreams) {
            if (m <= n && n < m + u.getProbability()) {
                result = u.getUpstreamType();
                break;
            }
            m += u.getProbability();
        }
        return result;
    }

    public static void main(String[] args){

        //测试数据
        List<Upstream> upstreamList=new ArrayList<>();
        Upstream u1 = new Upstream(1,20);
        Upstream u2 = new Upstream(2,30);
        Upstream u3 = new Upstream(3,50);
        upstreamList.add(u1);
        upstreamList.add(u2);
        upstreamList.add(u3);

        int result= getAssign(upstreamList);


    }


}

class Upstream{

    private int upstreamType;//类别
    private int probability;//权重值

    public Upstream(int upstreamType, int probability) {
        this.upstreamType = upstreamType;
        this.probability = probability;
    }

    public int getUpstreamType() {
        return upstreamType;
    }

    public void setUpstreamType(int upstreamType) {
        this.upstreamType = upstreamType;
    }

    public int getProbability() {
        return probability;
    }

    public void setProbability(int probability) {
        this.probability = probability;
    }
}