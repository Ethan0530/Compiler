package org.example.parser;

import java.util.*;

public class SyntacticParser {
    //步骤
    public static int count;
    //下推栈
    public static Deque<Character> stack = new ArrayDeque<>();
    //输入带尚未分析串
    public static Deque<Character> queue = new ArrayDeque<>();
    //分析表
    public static List<Action> list = new ArrayList<>();


    /**文法
     * E -> TG
     * F -> (E) | i
     * G -> +TG | -TG | null
     * T -> FS
     * S -> *FS | /FS | null
     */

    /**
     * i+i*i最左推导
     * E
     * TG
     * FSG
     * iSG
     * iG
     * i+TG
     * i+FSG
     * i+iSG
     * i+i*FSG
     * i+i*iSG
     * i+i*iG
     * i+i*i
     */
    //递归子程序法语法分析器
    public static void E(){
        stack.pop();
        stack.push('G');
        stack.push('T');
        action(list,stack,queue,"推导:E->TG");
        T();
        G();
    }
    public static void F(){
        char c = queue.peek();
        if(c=='('){
            stack.pop();
            stack.push(')');
            stack.push('E');
            stack.push('(');
            action(list,stack,queue,"推导: F -> (E)");
            stack.pop();
            queue.remove();
            action(list,stack,queue,"匹配");
            E();
        }
        else if(c=='i'){
            stack.pop();
            stack.push('i');
            action(list,stack,queue,"推导: F -> i");
            stack.pop();
            queue.remove();
            action(list,stack,queue,"匹配");
        }
        else {
            System.out.println("匹配失败");
        }
    }
    public static void G(){
        char c = queue.peek();
        if(c=='+'){
            stack.pop();
            stack.push('G');
            stack.push('T');
            stack.push('+');
            action(list,stack,queue,"推导: G -> +TG");
            stack.pop();
            queue.remove();
            action(list,stack,queue,"匹配");
            T();
            G();
        }
        else if(c=='-'){
            stack.pop();
            stack.push('G');
            stack.push('T');
            stack.push('-');
            action(list,stack,queue,"推导: G -> -TG");
            stack.pop();
            queue.remove();
            action(list,stack,queue,"匹配");
            T();
            G();
        }
        else {
            stack.pop();
            action(list,stack,queue,"推导: G -> null");
        }

    }
    public static void T(){
        stack.pop();
        stack.push('S');
        stack.push('F');
        action(list,stack,queue,"推导:T->FS");
        F();
        S();
    }
    public static void S(){
        char c = queue.peek();
        if(c=='*'){
            stack.pop();
            stack.push('S');
            stack.push('F');
            stack.push('*');
            action(list,stack,queue,"推导: S -> *FS");
            stack.pop();
            queue.remove();
            action(list,stack,queue,"匹配");
            F();
            S();
        }
        else if(c=='/'){
            stack.pop();
            stack.push('S');
            stack.push('F');
            stack.push('/');
            action(list,stack,queue,"推导: S -> /FS");
            stack.pop();
            queue.remove();
            action(list,stack,queue,"匹配");
            F();
            S();
        }
        else {
            stack.pop();
            action(list,stack,queue,"推导: S -> null");
        }

    }

    //执行动作的标记
    public static void action(List<Action> list,Deque<Character> stack,Deque<Character> queue,String s){
        Action action = new Action(count,stack,queue,s);
        list.add(action);
        count++;
    }

    public static void main(String[] args) {
        //初始化操作
        char[] str = ("i+i*i#").toCharArray();
        stack.push('#');
        stack.push('E');

        for(char c:str){
            queue.add(c);
        }

        System.out.println("下推栈：");
        System.out.println(stack);
        System.out.println("输入带尚未分析串：");
        System.out.println(queue);
        action(list,stack,queue,"");

        E();
        System.out.println("-------------------------------");
        for (Action a:list){
            System.out.println(a);
        }
        if(stack.pop()=='#'&&queue.peek()=='#'){
            System.out.println("匹配成功");
        }else System.out.println("匹配失败");

    }
}
