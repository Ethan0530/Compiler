package org.example.parser;

import java.util.ArrayDeque;
import java.util.Deque;

public class Action {
    //步骤
    private int count;
    //分析栈状态
    private String stack;
    //输入带尚未分析串
    private String queue;
    //动作
    private String action;

    public Action(int count,Deque<Character> stack, Deque<Character> queue) {
        this.stack = "";
        this.queue = "";
        for(char c1:stack){
            this.stack+=c1;
        }
        for(char c2:queue){
            this.queue+=c2;
        }
        this.count = count;
    }

    public Action(int count,Deque<Character> stack, Deque<Character> queue,String action) {
        this.stack = "";
        this.queue = "";
        Deque<Character> stack2 = new ArrayDeque<>();
        for(char c1:stack){
            stack2.push(c1);
        }
        for (char c2:stack2){
            this.stack+=c2;
        }
        for(char c3:queue){
            this.queue+=c3;
        }
        this.count = count;
        this.action = action;
    }

    @Override
    public String toString() {
        return "Action{" +
                "count=" + count +
                ", stack='" + stack + '\'' +
                ", queue='" + queue + '\'' +
                ", action='" + action + '\'' +
                '}';
    }
}
