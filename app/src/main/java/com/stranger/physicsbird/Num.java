package com.stranger.physicsbird;

import java.math.BigDecimal;

/**
 * Created by stranger on 2016/1/30.
 */
public class Num {
    private double data;
    private String result;
    private int flag = 0;

    Num(double dou) {
        if (dou==0){
            result="0.0";
            return;
        }
        if (dou > 0)
            data = dou;
        else {
            flag = 1;
            data = -1 * dou;
        }
        /**
         * 分割 .(未采用方法)
         * 判断整数大于0，小数大于五位 取五位小数
         * 整数小于0，小数大于五位 判断分位 计算幂指数 for 转化科学计数法5位小数   || 高精度转化 ||数位直算
         */
        //原始判断
//			StringBuffer str1=new StringBuffer();
//			BigDecimal bigDecimal = new BigDecimal(data);
//			str1.append(bigDecimal.toString());
//			String[] str=str1.toString().split("//.");
//			if (str[1].equals("0")||str[1].equals(null))
//				return;
//			int t;
//			try{
//			t=Integer.parseInt(str[0].trim());
//			}catch(Exception e)
//			{
//				return;
//			}
        if (data >= 1)
        //(t>0&&str[1].length()>=5)
        {
            data = (((double) Math.round(data * 100000000)) / 100000000);
            result = String.valueOf(data);
            return;
        }
        if (data < 1)//(t<0)
        {//原始判断
            //if (str[1].length()<=5)
            //	return;
//				int p,j,flag=1;
//				for(j=0;flag==1&&j<str[1].length();)
//				{
//					if (str[1].charAt(j)!='0')
//						flag=0;
//					j++;
//				}
            //高精度
//				BigDecimal b = new BigDecimal(String.valueOf(data));
//				BigDecimal divisor = BigDecimal.ONE;
//				MathContext mc = new MathContext(2);
//				//result=b.divide(divisor, mc).toString();
//				result=b.toString();


            int j = 0, k;
            double temp = data;
            while (temp < 1) {
                temp = temp * 10;
                j++;
            }
            temp = (((double) Math.round(temp * 1000000)) / 1000000);
            BigDecimal a = new BigDecimal(String.valueOf(temp));
            for (k = 0; k < j; k++)
                a = a.divide(new BigDecimal(10));
            result = a.toString();


        }
    }

    public String show() {
        if (flag == 1)
            return "-" + result;
        else
            return result;
    }
}
