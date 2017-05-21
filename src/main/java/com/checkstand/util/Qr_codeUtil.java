package com.checkstand.util;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

/**
 * Created by 11723 on 2017/5/9.
 */
public class Qr_codeUtil {
    private static char[][] chars = new char[33][33];
    private static char[] _chars = new char[165];
    public static String qr_code(String qr_path) throws IOException {
//        File file=new File("E:\\_kiss2017031803090238_z.png");//图片路径
        File file=new File("E:\\" + qr_path + ".png");//图片路径
        BufferedImage bufIma= ImageIO.read(file);//
        int width=bufIma.getWidth();
        int height=bufIma.getHeight();
        int imaRGB[][]=new int[width][height];//存放RGB信息数组，重点
        //从bufIma读取RGB到数组中
        for(int i=0;i<width;i++)
            for(int j=0;j<height;j++)
                imaRGB[i][j]=bufIma.getRGB(i,j)&0xffffff;
        for(int i=0;i<width-8;i++)  {
            for(int j=0;j<height-8;j++) {
                if (imaRGB[i+4][j+4] == 0) {//0黑色
                    chars[i][j/8] = name(chars[i][j/8], j);
                }
            }
        }
        int k = 0;
        for(int i = 0;i < 33;i ++){
//            System.out.print("{");
            for(int j = 0;j < 5;j ++){
                _chars[k++] = chars[i][j];
                if (j == 4) {
//					System.out.print(chars[i][j] + " ");
                    System.out.print("0x"+Integer.toHexString(chars[i][j]));
                }else
//					System.out.print(chars[i][j] + " ");
                    System.out.print("0x"+Integer.toHexString(chars[i][j])+",");
            }
            System.out.println();
//            System.out.println("},");
        }
        return String.valueOf(_chars);
    }
    public static String getIndex(int index){
        return String.valueOf(_chars).substring(index*15,(index+1)*15);
    }
    private static char name(char _char,int j) {
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

    public static void main(String[] args) throws IOException {
        qr_code(null);
    }
}
