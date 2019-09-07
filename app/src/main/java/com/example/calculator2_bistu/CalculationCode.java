package com.example.calculator2_bistu;

import android.util.Log;

import java.lang.invoke.MutableCallSite;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Stack;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class CalculationCode {
    public final static int ADD = 1;
    public final static int SUB = 2;
    public final static int MUT = 3;//乘
    public final static int DIV = 4;


    public static double  simpleCalculation(double a, double b, int tig) {
        switch (tig) {
            case 1://加法相关
                return add(a, b);

            case 2://减法
                return sub(a, b);

            case 3://乘法
                return mut(a, b);

            case 4://除法
                return division(a, b);

            default:
                Log.e("ErJikeCalculation:", "简单计算出现位置符号，错误");

        }
        return 0;

    }

    public static double add(double a, double b) {
        return a + b;
    }

    public static double sub(double a, double b) {
        return a + b;
    }

    public static double division(double a, double b) {
        double v = 0;
        if (b != 0) {
            v = a / b;
        } else
            Log.e("Erjike's-Calculation", "除数为0");
        return v;
    }

    public static double mut(double a, double b) {
        return a * b;
    }

    public static double square(double a, double b) {
        double out = 1;
        for (int i = 0; i < b; i++) {
            out = out * a;
        }
        return out;
    }

    public static double root(double a) {
        double k = 1;
        while (Math.abs(k * k - a) > 1e-5) {
            k = (k + a / k) / 2;
        }
        DecimalFormat df = new DecimalFormat("#.00000000");
        String str = df.format(k);
        k=Double.valueOf(str);
        return k;
    }

    public static double calculate(String strExpression) {
        //首先进行中缀转后缀
        String s = simplify(strExpression);//调用自定简化方法
        Log.i("ErJikeCalculate", "输入字符串:" + s);
        String numStr = "";//记录数字
        Stack<Character> opeStack = new Stack<>();//暂存符号
        int l = s.length();//字符串长度
        List<String> list = new ArrayList<>();

        for (int i = 0; i < l; i++)//循环所有字符
        {
            char ch = s.charAt(i);

            if (isAllOpe(ch))//如果为计算符号或者括号
            {
                if (numStr != "")//暂存数不为空
                {
                    list.add(numStr);//存数，清空
                    numStr = "";
                }


                if (ch == '(')//如果为左括号，将左括号压入符号栈
                {
                    opeStack.push(ch);
                } else if (isOpe(ch))//如果是计算符号
                {
                    char top = opeStack.peek();
                    if (isGreater(ch, top))
                    // ch优先级大于top 压栈（优先级/ > * > - > +）

                    {
                        opeStack.push(ch);
                    } else
                    //否则,将栈内元素出栈,直到遇见 '(' 然后将ch压栈
                    //（只有小括号能让低优先级的内容在高优先级下面）
                    {
                        while (true)
                        //必须先判断一下 后出栈 否则会有空栈异常
                        {
                            char t = opeStack.peek();
                            if (t == '(')
                                break;
                            if (isGreater(ch, t))//判断ch等级是否比t高，高就暂停
                                break;

                            list.add(Character.toString(t));//添加符号到list
                            t = opeStack.pop();//出栈
                        }
                        opeStack.push(ch);//最后将ch压入

                    }

                } else if (ch == ')') {
                    char t = opeStack.pop();
                    while (t != '(' && !opeStack.isEmpty()) {
                        list.add(Character.toString(t));//ch为右括号就吧符号堆顶元素提取
                        t = opeStack.pop();
                    }
                }

            } else//处理数字
            {
                numStr += ch;
            }
        }

        //计算后缀表达式
        Log.i("ErJikeCalculate", "拆分为后缀表达式：" + list.toString());
        Stack<Double> num = new Stack<>();
        int size = list.size();
        for (int i = 0; i < size; i++) {
            String t = list.get(i);
            if (isNumeric(t)) {//将t转换成int 方便计算
                num.push(Double.valueOf(t));
            } else {
                //如果t为运算符则 只有一位
                char c = t.charAt(0);
                double b = num.pop();
                //如果有 算式是类似于 -8-8 这样的需要判断一下栈是否为空
                double a = num.pop();
                switch (c) {
                    case '+':
                        num.push(a + b);
                        break;
                    case '-':
                        num.push(a - b);
                        break;
                    case '*':
                        num.push(a * b);
                        break;
                    case '/':
                        num.push(a / b);
                        break;
                    default:
                        break;
                }
            }
        }
        return num.pop();
    }


    /**
     * 化简表达式
     * 将表达式中的 {}[]替换为()
     * 负数的处理
     * 为了方便将中缀转换为后缀在字符串前后分别加上(,) eg:"1+1" 变为"(1+1)"
     *
     * @param str 输入的字符串
     * @return s 返回简化完的表达式
     */
    public static String simplify(String str) {
        //负数的处理
        // 处理负数，这里在-前面的位置加入一个0，如-4变为0-4，
        // 细节：注意-开头的地方前面一定不能是数字或者反括号，如9-0,(3-4)-5，这里地方是不能加0的
        // 它的后面可以是数字或者正括号，如-9=>0-9, -(3*3)=>0-(3*3)
        String s = str.replaceAll("(?<![0-9)}\\]])(?=-[0-9({\\[])", "0");
        //将表达式中的 {}[]替换为()
        s = s.replace('[', '(');
        s = s.replace('{', '(');
        s = s.replace(']', ')');
        s = s.replace(']', ')');
        //为了方便将中缀转换为后缀在字符串前后分别加上(,)
        s = "(" + s + ")";

        return s;
    }

    /**
     * 判断字符c是否为合理的运算符
     *
     * @param c
     * @return
     */
    public static boolean isOpe(char c) {
        if (c == '+' || c == '-' || c == '*' || c == '/')
            return true;
        else
            return false;
    }

    public static boolean isAllOpe(char c) {
        if (c == '+' || c == '-' || c == '*' || c == '/')
            return true;

        else if (c == '(' || c == ')')
            return true;
        else
            return false;
    }

    /**
     * 比较字符等级a是否大于b
     *
     * @param a
     * @param b
     * @return 大于返回true 小于等于返回false
     */
    public static boolean isGreater(char a, char b) {
        int a1 = getLevel(a);
        int b1 = getLevel(b);
        if (a1 > b1)
            return true;
        else
            return false;
    }

    /**
     * 得到一个字符的优先级
     *
     * @param a
     * @return
     */
    public static int getLevel(char a) {

        if (a == '+')
            return 0;
        else if (a == '-')
            return 1;
        else if (a == '*')
            return 3;
        else if (a == '/')
            return 4;
        else
            return -1;

    }

    //判断是不是数字
    public static boolean isNumeric(String str) {
        Pattern pattern = Pattern.compile("[0-9.]*");
        Matcher isNum = pattern.matcher(str);
        if (!isNum.matches()) {
            return false;
        }
        return true;
    }


}
