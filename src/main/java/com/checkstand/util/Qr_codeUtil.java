package com.checkstand.util;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

/**
 * Created by 11723 on 2017/5/9.
 */
public class Qr_codeUtil {
    public static String qr_code() throws IOException {
        File file=new File("E:\\_kiss2017031803090238_z.png");//图片路径
        BufferedImage bufIma= ImageIO.read(file);//
        int width=bufIma.getWidth();
        int height=bufIma.getHeight();
        int imaRGB[][]=new int[width][height];//存放RGB信息数组，重点
        //从bufIma读取RGB到数组中
        for(int i=0;i<width;i++)
            for(int j=0;j<height;j++)
                imaRGB[i][j]=bufIma.getRGB(i,j)&0xffffff;
        char chars[][] = new char[33][33];
        char _chars[][] = new char[33][33];
        for(int i=0;i<width-8;i++)  {
            for(int j=0;j<height-8;j++) {
                if (imaRGB[i+4][j+4] == 0) {//0黑色
//		    		System.out.print("1");
                    chars[i][j/8] = name(chars[i][j/8], j);
                    _chars[i][j] = '1';
                }else {
//					System.out.print("0");
                    _chars[i][j] = '0';
                }
            }
//		    System.out.println();
        }
//		String content = "";
//		int c = 0,count = 0;
//		char[] data = {0xff,0xff,0xff};
//		String space = String.valueOf(data);
//		for(int i = 0;i < 33;i ++){
//			int[] k = {0,0};
//			for(int j = 0;j < 33; j ++){
//				if (_chars[i][j] == '1') {
//					if (k[0]+1 == j || k[0] == 0) {
//						k[0] = j;
//						k[1]++;
//					}
//					c = 0;
//					if (j == 32) {
//						content +="fill " +(50+(k[0]-k[1]+1)*5) + "," + (50+i*5) + "," + k[1]*5 + ",5,BLACK" + space;
//						System.out.println("fill " +(50+(k[0]-k[1]+1)*5) + "," + (50+i*5) + "," + k[1]*5 + ",5,BLACK" + space);
//						k[0] = 0;
//						k[1] = 0;
//						c = 1;
//						count ++;
//					}
//				}else {
//					if (c == 0) {
//						content +="fill " +(50+(k[0]-k[1]+1)*5) + "," + (50+i*5) + "," + k[1]*5 + ",5,BLACK" + space;
//						System.out.println("fill " +(50+(k[0]-k[1]+1)*5) + "," + (50+i*5) + "," + k[1]*5 + ",5,BLACK" + space);
//						k[0] = 0;
//						k[1] = 0;
//						c = 1;
//						count ++;
//					}
//				}
//			}
//		}
//		System.out.println(count);
        for(int i = 0;i < 33;i ++){
//            System.out.print("{");
            for(int j = 0;j < 5;j ++){
                if (j == 4) {
					System.out.print("0x"+Integer.toHexString(chars[i][j]) + " ");
//                    System.out.print("0x"+Integer.toHexString(chars[i][j]));
                }else
					System.out.print("0x"+Integer.toHexString(chars[i][j]) + " ");
//                    System.out.print("0x"+Integer.toHexString(chars[i][j])+",");
            }
            System.out.println();
//            System.out.println("},");
        }
        return String.valueOf(chars);
    }
    public static char name(char _char,int j) {
        switch(j%8){
            case 0:_char|=0x80;break;
            case 1:_char|=0x40;break;
            case 2:_char|=0x20;break;
            case 3:_char|=0x10;break;
            case 4:_char|=0x08;break;
            case 5:_char|=0x04;break;
            case 6:_char|=0x02;break;
            case 7:_char|=0x01;break;
        }
        return  _char;
    }
}
