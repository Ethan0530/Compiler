package org.example.lexer;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static org.example.lexer.Word.*;


public class LexAnalyse {
    //将字符数组转换为字符串的函数(指定开始和结束位置)
    public static String charArrayToString(char[] str,int start,int end){
        String s ="";
        while(start<end){
            s+=str[start];
            start++;
        }
        return s;
    }

    public static void main(String[] args) throws IOException {
        FileReader fileReader = new FileReader("src/main/resources/test.c");
        BufferedReader bufferedReader = new BufferedReader(fileReader);
        String thisLine= "";//表示读取的当前行

        List<Word> list = new ArrayList<>();
        int line = 1;

        //一行一行读取文件
        while((thisLine = bufferedReader.readLine())!=null){
            thisLine.trim();//去掉首位空格
            char[] str = thisLine.toCharArray();//转换为字符数组
            for(int i = 0;i<str.length;){
                int temp = i;//中间变量,记录本轮循环开始时i的起始位置
                Word word = new Word();//token单元
                /**
                 * 从当前位置开始分为以下几种情况
                 */
                //1.如果当前位置为空格，跳过
                if(str[i]==' '){
                    i++;
                    continue;
                }
                //2.判断当前位置是否为标识符，如果是则读到不是标识符的位置或者字符数组结尾（注意还要判断标识符是否为关键字）
                if(str[i]>='a'&&str[i]<='z'){
                    while(i<str.length&&((str[i]>='a'&&str[i]<='z')||(str[i]>='0'&&str[i]<='9')||str[i]=='_')){
                        i++;
                    }
                    String s = charArrayToString(str,temp,i);
                    if(word.isKey(s)){
                        word.setId(1);
                        word.setType(OPERATOR);
                        word.setValue(s);
                        word.setLine(line);
                        list.add(word);
                        continue;
                    }
                    else{
                        word.setId(2);
                        word.setType(IDENTIFIER);
                        word.setValue(s);
                        word.setLine(line);
                        list.add(word);
                        continue;
                    }
                }
                //3.判断是否为整型常量
                if(str[i]>='0'&&str[i]<='9'){
                    while(i<str.length&&(str[i]>='0'&&str[i]<='9')){
                        i++;
                    }
                    String s = charArrayToString(str,temp,i);
                    word.setId(3);
                    word.setType(INT_CONST);
                    word.setValue(s);
                    word.setLine(line);
                    list.add(word);
                    continue;
                }
                //4.判断是否为运算符
                String s = "";
                s+=str[i];
                if(word.isOperator(s)){
                    word.setId(4);
                    word.setType(OPERATOR);
                    word.setValue(s);
                    word.setLine(line);
                    list.add(word);
                    i++;
                    continue;
                }
                //5.判断是否为界符
                if(word.isBoundarySign(s)){
                    word.setId(5);
                    word.setType(BOUNDARYSIGN);
                    word.setValue(s);
                    word.setLine(line);
                    list.add(word);
                    i++;
                    continue;
                }
            }
            line++;
        }
        System.out.println("单词序号---单词的值---单词类号---所在行");
        int count=1;
        for (Word wd:list){
            System.out.println(count+"\t\t"+wd.getValue()+"\t\t"+wd.getId()+"\t\t"+wd.getLine());
            count++;
        }
    }
}
