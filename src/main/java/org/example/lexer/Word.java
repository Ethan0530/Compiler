package org.example.lexer;

import java.util.ArrayList;
import java.util.List;

/**
 * 单词类
 */
public class Word {
    public final static String KEY = "关键字";
    public final static String IDENTIFIER = "标识符";
    public final static String INT_CONST = "整形常量";
    public final static String OPERATOR = "运算符";
    public final static String BOUNDARYSIGN = "界符";
    //关键字集合
    public static List<String> key = new ArrayList<>();
    //运算符集合
    public static List<String> operator = new ArrayList<>();
    //界符集合
    public static List<String> boundarySign = new ArrayList<>();

    static {
        Word.key.add("main");
        Word.key.add("void");
        Word.key.add("int");
        Word.key.add("if");
        Word.key.add("else");
        Word.key.add("for");
        Word.key.add("while");
        Word.key.add("do");
        Word.key.add("break");
        Word.key.add("continue");
        Word.key.add("printf");
        Word.key.add("return");

        Word.operator.add("+");
        Word.operator.add("-");
        Word.operator.add("*");
        Word.operator.add("/");
        Word.operator.add("%");
        Word.operator.add("++");
        Word.operator.add("--");
        Word.operator.add("=");
        Word.operator.add(">");
        Word.operator.add("<");
        Word.operator.add(">=");
        Word.operator.add("<=");
        Word.operator.add("==");
        Word.operator.add("!=");
        Word.operator.add("!");
        Word.operator.add("&&");
        Word.operator.add("||");
        Word.operator.add(".");
        Word.operator.add("?");
        Word.operator.add(":");
        Word.operator.add("&");
        Word.operator.add("|");

        Word.boundarySign.add("{");
        Word.boundarySign.add("}");
        Word.boundarySign.add("(");
        Word.boundarySign.add(")");
        Word.boundarySign.add("[");
        Word.boundarySign.add("]");
        Word.boundarySign.add(",");
        Word.boundarySign.add(";");
        Word.boundarySign.add("#");
        Word.boundarySign.add("\'");
        Word.boundarySign.add("\"");
    }

    //单词的词种
    private int id;
    //单词的值
    private String value;
    //单词类型
    private String type;
    //单词所在行
    private int line;
    public Word(){}

    public Word(String value){
        this.value = value;
        this.id = 0;
        this.line = 0;
        this.type = "";
    }

    public Word(int id, String value, String type, int line) {
        this.id = id;
        this.value = value;
        this.type = type;
        this.line = line;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public void setType(String type) {
        this.type = type;
    }

    public void setLine(int line) {
        this.line = line;
    }

    public int getId() {
        return id;
    }

    public String getValue() {
        return value;
    }

    public String getType() {
        return type;
    }

    public int getLine() {
        return line;
    }

    //判断是否为关键字
    public boolean isKey(String s){
        return key.contains(s);
    }
    //判断是否为界符
    public boolean isBoundarySign(String s){
        return boundarySign.contains(s);
    }
    //判断是否为运算符
    public boolean isOperator(String s){
        return operator.contains(s);
    }
}
