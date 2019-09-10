package com.example.calculator2_bistu;

import android.content.DialogInterface;
import android.content.res.Configuration;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    private static String TAG = "ErJike";
    private int bracketPoint = 0;//用来防止左右括号不对应
    private int numberIn = 0;
    private String logNegativeS="";//暂存log里的inputText，取消时方便恢复



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        View.OnClickListener normalOperationListener = null;
        if (this.getResources().getConfiguration().orientation == Configuration.ORIENTATION_LANDSCAPE) {
            Log.i(TAG, "横屏landscape ");
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_main_land);
            numberIn = 1;
            normalOperationListener = new View.OnClickListener() {
                EditText outText;
                TextView inputText;
                String midinput;

                @Override
                public void onClick(View view) {
                    outText = (EditText) findViewById(R.id.OutputTextView2);
                    inputText = (TextView) findViewById(R.id.InputTextView);

                    switch (view.getId()) {


                        case R.id.buttonAdd://加法
                            Log.i("OnClick", "+");//DEBUG:测试输入
                            outText = (EditText) findViewById(R.id.OutputTextView2);
                            inputText = (TextView) findViewById(R.id.InputTextView);
                            if (numberIn != 0) {
                                if (!outText.getText().toString().equals("") && outText.getText() != null) {
                                    inputText.setText(outText.getText(), null);//如果结果有值，则替换输入端
                                    outText.setText("");
                                    Log.i(TAG, "onClick:-" + outText.getText());

                                }
                                midinput = inputText.getText().toString();
                                midinput = midinput + "+";
                                inputText.setText(midinput);
                                numberIn = 0;
                            } else
                                Toast.makeText(MainActivity.this, "输入错误，符号不要相连", Toast.LENGTH_SHORT).show();
                            break;
                        case R.id.buttonMinus://减法
                            Log.i("OnClick", "-");//DEBUG:测试输入
                            outText = (EditText) findViewById(R.id.OutputTextView2);
                            inputText = (TextView) findViewById(R.id.InputTextView);
                            if (!outText.getText().toString().equals("") && outText.getText() != null) {
                                inputText.setText(outText.getText(), null);//如果结果有值，则替换输入端
                                outText.setFocusableInTouchMode(true);
                                outText.setFocusable(true);
                                outText.setText("");//如果结果有值，输入数字时则清空
                                outText.setFocusableInTouchMode(false);
                                outText.setFocusable(false);
                                Log.i(TAG, "onClick:-" + outText.getText());

                            }
                            midinput = inputText.getText().toString();
                            midinput = midinput + "-";
                            inputText.setText(midinput);
                            break;
                        case R.id.buttonMultiply://乘法
                            if (numberIn != 0) {
                                Log.i("OnClick", "*");//DEBUG:测试输入
                                outText = (EditText) findViewById(R.id.OutputTextView2);
                                inputText = (TextView) findViewById(R.id.InputTextView);
                                if (!outText.getText().toString().equals("") && outText.getText() != null) {
                                    inputText.setText(outText.getText(), null);//如果结果有值，则替换输入端
                                    outText.setFocusableInTouchMode(true);
                                    outText.setFocusable(true);
                                    outText.setText("");//如果结果有值，输入数字时则清空
                                    outText.setFocusableInTouchMode(false);
                                    outText.setFocusable(false);
                                    Log.i(TAG, "onClick:-" + outText.getText());

                                }
                                midinput = inputText.getText().toString();
                                midinput = midinput + "*";
                                inputText.setText(midinput);
                                numberIn = 0;
                            } else {
                                Toast.makeText(MainActivity.this, "输入错误，不要连续输入符号", Toast.LENGTH_SHORT).show();

                            }

                            break;
                        case R.id.buttonExcept://除法
                            if (numberIn != 0) {
                                Log.i("OnClick", "/");//DEBUG:测试输入
                                outText = (EditText) findViewById(R.id.OutputTextView2);
                                inputText = (TextView) findViewById(R.id.InputTextView);
                                if (!outText.getText().toString().equals("") && outText.getText() != null) {
                                    inputText.setText(outText.getText(), null);//如果结果有值，则替换输入端
                                    outText.setFocusableInTouchMode(true);
                                    outText.setFocusable(true);
                                    outText.setText("");//如果结果有值，输入数字时则清空
                                    outText.setFocusableInTouchMode(false);
                                    outText.setFocusable(false);
                                    Log.i(TAG, "onClick:-" + outText.getText());

                                }
                                midinput = inputText.getText().toString();
                                midinput = midinput + "/";
                                inputText.setText(midinput);
                                numberIn = 0;
                            } else
                                Toast.makeText(MainActivity.this, "输入错误，不要连续输入符号", Toast.LENGTH_SHORT).show();

                            break;
                        case R.id.buttonBack://退格
                            Log.i("OnClick", "退格");//DEBUG:测试输入
                            outText = (EditText) findViewById(R.id.OutputTextView2);
                            inputText = (TextView) findViewById(R.id.InputTextView);
                            String outtext = outText.getText().toString();
                            if (!outtext.equals("")) {
                                outText.setText("");
                                inputText.setText("");
                                numberIn = 0;
                            }
                            if (inputText.getText().toString().equals("") || inputText.getText() == null) {
                                numberIn = 0;
                                break;

                            }

                            midinput = inputText.getText().toString();
                            if (midinput.length() > 1) {
                                String bdnumber = midinput.substring(midinput.length() - 2, midinput.length() - 1);
                                switch (bdnumber) {//防止异常处理影响退格
                                    case "0":
                                    case "1":
                                    case "2":
                                    case "3":
                                    case "4":
                                    case "5":
                                    case "6":
                                    case "7":
                                    case "8":
                                    case "9":
                                    case ".":
                                        numberIn = 1;//默认前方的为数字
                                        break;
                                    case ")":
                                        bracketPoint++;
                                    case "(":
                                        bracketPoint--;
                                    default:
                                        numberIn = 0;

                                }

                            } else
                                numberIn = 1;

                            midinput = midinput.substring(0, midinput.length() - 1);

                            inputText.setText(midinput);
                            break;
                        case R.id.buttonClear://清空
                            Log.i("OnClick", "清空");//DEBUG:测试输入

                            outText = (EditText) findViewById(R.id.OutputTextView2);
                            inputText = (TextView) findViewById(R.id.InputTextView);
                            outText.setFocusableInTouchMode(true);
                            outText.setFocusable(true);
                            outText.setText("");//如果结果有值，输入数字时则清空
                            outText.setFocusableInTouchMode(false);
                            outText.setFocusable(false);
                            inputText.setText("");
                            numberIn = 0;
                            break;
                        case R.id.buttonRoot://开根号
                            Log.i("OnClick", "开根号");//DEBUG:测试输入
                            outText = (EditText) findViewById(R.id.OutputTextView2);
                            inputText = (TextView) findViewById(R.id.InputTextView);
                            if (outText.getText().toString().equals("") || outText.getText() == null) {
                                AlertDialog.Builder dialogRoot = new AlertDialog.Builder(MainActivity.this);
                                dialogRoot.setTitle("输入要开根号的值(表达式)");
                                LayoutInflater rootDialog = getLayoutInflater();
                                final View rootView = rootDialog.inflate(R.layout.root_layout, null);
                                dialogRoot.setView(rootView);
                                dialogRoot.setPositiveButton("计算", new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialog, int which) {
                                        //Toast.makeText(MainActivity.this,"填写确定监听事件",Toast.LENGTH_SHORT).show();
                                        EditText rootET = rootView.findViewById(R.id.textRootIn);
                                        String rootN = rootET.getText().toString();
                                        midinput = inputText.getText().toString();
                                        double middouble = Double.valueOf(CalculationCode.calculate(rootN));
                                        Log.i("ErJikeRoot:", "middouble：" + middouble);

                                        inputText.setText(midinput + String.valueOf(CalculationCode.root(middouble)));
                                        Log.i(TAG, "最终结果：" + midinput);
                                    }
                                });
                                dialogRoot.setNegativeButton("取消", new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialog, int which) {
                                        //finish();//测试如何升级GitHub内容
                                    }
                                });
                                dialogRoot.show();
                                numberIn = 1;

                            } else {
                                midinput = inputText.getText().toString();
                                String outmid = outText.getText().toString();
                                double middoubles = Double.valueOf(outmid);
                                midinput = String.valueOf(CalculationCode.root(middoubles));
                                inputText.setText(midinput);
                                outText.setText("");
                                Log.i("ErJikeRoot", "output有数字，输出：" + midinput);
                                numberIn = 1;
                            }
                            break;
                        case R.id.buttonEqual://等于
                            Log.i("OnClick", "等于");//DEBUG:测试输入
                            outText = (EditText) findViewById(R.id.OutputTextView2);
                            inputText = (TextView) findViewById(R.id.InputTextView);
                            if (numberIn == 0) {
                                Toast.makeText(MainActivity.this, "算式错误，请保证四则运算符左右有数字", Toast.LENGTH_LONG).show();

                            }
                            if (bracketPoint == 0 && numberIn != 0) {
                                midinput = String.valueOf(CalculationCode.calculate(inputText.getText().toString()));
                                Log.i(TAG, "最终结果：" + midinput);
                                outText.setFocusableInTouchMode(true);
                                outText.setFocusable(true);
                                outText.setText(midinput);
                                outText.setFocusableInTouchMode(false);
                                outText.setFocusable(false);

                            } else
                                Toast.makeText(MainActivity.this, "算式格式错误，无法计算", Toast.LENGTH_LONG).show();


                            //调用Calculation方法
                            break;
                        case R.id.buttonPercentage://百分比
                            Log.i("OnClick", "百分比");//DEBUG:测试输入
                            outText = (EditText) findViewById(R.id.OutputTextView2);
                            inputText = (TextView) findViewById(R.id.InputTextView);
                            if ((outText.getText().toString().equals("") || outText.getText() == null) && (numberIn != 0)
                                    && !inputText.getText().toString().equals("") && inputText.getText() != null) {
                                midinput = inputText.getText().toString();
                                int l = midinput.length();
                                Log.i(TAG, "onClickPercentage: " + l);
                                boolean tag = true;
                                String outNumber = "";
                                while (tag == true) {
                                    String bdsnumber = midinput.substring(l - 1, l);
                                    Log.i(TAG, "onClickBDsnumber: " + bdsnumber);
                                    switch (bdsnumber) {//防止异常处理影响退格
                                        case "0":
                                        case "1":
                                        case "2":
                                        case "3":
                                        case "4":
                                        case "5":
                                        case "6":
                                        case "7":
                                        case "8":
                                        case "9":
                                        case ".":
                                            outNumber = outNumber + bdsnumber;
                                            //numberIn = 1;//默认前方的为数字
                                            break;
                                        case "-":
                                            if ((l > 1) && (midinput.substring(l - 2, l - 1).equals("+") || midinput.substring(l - 2, l - 1).equals("-") ||
                                                    midinput.substring(l - 2, l - 1).equals("+") || midinput.substring(l - 2, l - 1).equals("*") || midinput.substring(l - 2, l - 1).equals("/"))) {
                                                outNumber = outNumber + bdsnumber;
                                            } else tag = false;
                                        default:
                                            tag = false;


                                    }
                                    if (l == 1) {
                                        break;
                                    }
                                    l--;


                                }
                                midinput = midinput.substring(0, midinput.length() - outNumber.length());
                                String out2 = "";
                                for (int i = 0; i < outNumber.length(); i++) {
                                    String bdnumber = outNumber.substring(outNumber.length() - 1 - i, outNumber.length() - i);
                                    out2 = out2 + bdnumber;

                                }
                                double outDouble = Double.valueOf(out2) * 0.01;
                                inputText.setText(midinput + String.valueOf(outDouble));
                            } else if (!outText.getText().toString().equals("")) {
                                numberIn = 1;
                                midinput = outText.getText().toString();
                                double outDouble = Double.valueOf(midinput) * 0.01;
                                inputText.setText(String.valueOf(outDouble));
                                outText.setText("");


                            }
                            break;
                        case R.id.buttonPoint://点
                            Log.i("OnClick", "点");//DEBUG:测试输入
                            outText = (EditText) findViewById(R.id.OutputTextView2);
                            inputText = (TextView) findViewById(R.id.InputTextView);
                            if (!outText.getText().equals("")) {
                                outText.setFocusableInTouchMode(true);
                                outText.setFocusable(true);
                                outText.setText("");//如果结果有值，输入数字时则清空
                                outText.setFocusableInTouchMode(false);
                                outText.setFocusable(false);
                            }
                            midinput = inputText.getText().toString();
                            midinput = midinput + '.';
                            inputText.setText(midinput);
                            break;
                        case R.id.buttonLbrackets://左括号
                            Log.i("OnClick", "左括号");//DEBUG:测试输入
                            outText = (EditText) findViewById(R.id.OutputTextView2);
                            inputText = (TextView) findViewById(R.id.InputTextView);
                            if (!outText.getText().equals("")) {
                                outText.setText("");//如果结果有值，输入数字时则清空
                            }
                            midinput = inputText.getText().toString();
                            midinput = midinput + '(';
                            inputText.setText(midinput);
                            bracketPoint = bracketPoint + 1;


                            break;
                        case R.id.buttonRbrackets://右括号
                            Log.i("OnClick", "右括号");//DEBUG:测试输入
                            outText = (EditText) findViewById(R.id.OutputTextView2);
                            inputText = (TextView) findViewById(R.id.InputTextView);
                            if (bracketPoint > 0) {
                                if (!outText.getText().equals("")) {
                                    outText.setText("");//如果结果有值，输入数字时则清空
                                }
                                midinput = inputText.getText().toString();
                                midinput = midinput + ')';
                                inputText.setText(midinput);
                                bracketPoint--;
                            } else {
                                Toast.makeText(MainActivity.this, "请保证左右括号数量相同，无法创建新的右括号", Toast.LENGTH_LONG).show();
                            }

                            break;
                        case R.id.buttonSquare://平方
                            Log.i("OnClick", "平方");//DEBUG:测试输入
                            outText = (EditText) findViewById(R.id.OutputTextView2);
                            inputText = (TextView) findViewById(R.id.InputTextView);
                            if ((outText.getText().toString().equals("") || outText.getText() == null) && (numberIn != 0)
                                    && !inputText.getText().toString().equals("") && inputText.getText() != null) {
                                midinput = inputText.getText().toString();
                                int l = midinput.length();
                                Log.i(TAG, "onClickPercentage: " + l);
                                boolean tag = true;
                                String outNumber = "";
                                while (tag == true) {
                                    String bdsnumber = midinput.substring(l - 1, l);
                                    //Log.v(TAG, "onClickBDsnumber: "+bdsnumber);
                                    switch (bdsnumber) {//防止异常处理影响退格
                                        case "0":
                                        case "1":
                                        case "2":
                                        case "3":
                                        case "4":
                                        case "5":
                                        case "6":
                                        case "7":
                                        case "8":
                                        case "9":
                                        case ".":
                                            outNumber = outNumber + bdsnumber;
                                            //numberIn = 1;//默认前方的为数字
                                            break;
                                        case "-":
                                            if ((l > 1) && (midinput.substring(l - 2, l - 1).equals("+") || midinput.substring(l - 2, l - 1).equals("-") ||
                                                    midinput.substring(l - 2, l - 1).equals("+") || midinput.substring(l - 2, l - 1).equals("*") || midinput.substring(l - 2, l - 1).equals("/"))) {
                                                outNumber = outNumber + bdsnumber;
                                            } else tag = false;
                                        default:
                                            tag = false;


                                    }
                                    if (l == 1) {
                                        break;
                                    }
                                    l--;


                                }
                                midinput = midinput.substring(0, midinput.length() - outNumber.length());
                                String out2 = "";
                                for (int i = 0; i < outNumber.length(); i++) {
                                    String bdnumber = outNumber.substring(outNumber.length() - 1 - i, outNumber.length() - i);
                                    out2 = out2 + bdnumber;

                                }
                                double outDouble = Double.valueOf(out2) * Double.valueOf(out2);
                                inputText.setText(midinput + String.valueOf(outDouble));

                            } else if (!outText.getText().toString().equals("")) {
                                numberIn = 1;
                                midinput = outText.getText().toString();
                                double outDouble = Double.valueOf(midinput) * Double.valueOf(midinput);
                                inputText.setText(String.valueOf(outDouble));
                                outText.setText("");


                            }
                            break;

                        case R.id.buttonSin://SIN
                            Log.i("OnClick", "SIN");//DEBUG:测试输入
                            outText = (EditText) findViewById(R.id.OutputTextView2);
                            inputText = (TextView) findViewById(R.id.InputTextView);
                            if ((outText.getText().toString().equals("") || outText.getText() == null) && (numberIn != 0)
                                    && !inputText.getText().toString().equals("") && inputText.getText() != null) {
                                midinput = inputText.getText().toString();
                                int l = midinput.length();
                                Log.i(TAG, "onClickPercentage: " + l);
                                boolean tag = true;
                                String outNumber = "";
                                while (tag == true) {
                                    String bdsnumber = midinput.substring(l - 1, l);
                                    Log.i(TAG, "onClickBDsnumber: " + bdsnumber);
                                    switch (bdsnumber) {//防止异常处理影响退格
                                        case "0":
                                        case "1":
                                        case "2":
                                        case "3":
                                        case "4":
                                        case "5":
                                        case "6":
                                        case "7":
                                        case "8":
                                        case "9":
                                        case ".":
                                            outNumber = outNumber + bdsnumber;
                                            //numberIn = 1;//默认前方的为数字
                                            break;
                                        case "-":
                                            if ((l > 1) && (midinput.substring(l - 2, l - 1).equals("+") || midinput.substring(l - 2, l - 1).equals("-") ||
                                                    midinput.substring(l - 2, l - 1).equals("+") || midinput.substring(l - 2, l - 1).equals("*") || midinput.substring(l - 2, l - 1).equals("/"))) {
                                                outNumber = outNumber + bdsnumber;
                                            } else tag = false;
                                        default:
                                            tag = false;


                                    }
                                    if (l == 1) {
                                        break;
                                    }
                                    l--;


                                }
                                midinput = midinput.substring(0, midinput.length() - outNumber.length());
                                String out2 = "";
                                for (int i = 0; i < outNumber.length(); i++) {
                                    String bdnumber = outNumber.substring(outNumber.length() - 1 - i, outNumber.length() - i);
                                    out2 = out2 + bdnumber;

                                }
                                double outDouble = Double.valueOf(out2);
                                inputText.setText(midinput + String.valueOf(Math.sin(outDouble)));

                            } else if (!outText.getText().toString().equals("")) {
                                numberIn = 1;
                                midinput = outText.getText().toString();
                                double outDouble = Math.sin(Double.valueOf(midinput));
                                inputText.setText(String.valueOf(outDouble));
                                outText.setText("");


                            }
                            break;
                        case R.id.buttonCos://COS
                            Log.i("OnClick", "COS");//DEBUG:测试输入
                            outText = (EditText) findViewById(R.id.OutputTextView2);
                            inputText = (TextView) findViewById(R.id.InputTextView);
                            if ((outText.getText().toString().equals("") || outText.getText() == null) && (numberIn != 0)
                                    && !inputText.getText().toString().equals("") && inputText.getText() != null) {
                                midinput = inputText.getText().toString();
                                int l = midinput.length();
                                Log.i(TAG, "onClickPercentage: " + l);
                                boolean tag = true;
                                String outNumber = "";
                                while (tag == true) {
                                    String bdsnumber = midinput.substring(l - 1, l);
                                    Log.i(TAG, "onClickBDsnumber: " + bdsnumber);
                                    switch (bdsnumber) {//防止异常处理影响退格
                                        case "0":
                                        case "1":
                                        case "2":
                                        case "3":
                                        case "4":
                                        case "5":
                                        case "6":
                                        case "7":
                                        case "8":
                                        case "9":
                                        case ".":
                                            outNumber = outNumber + bdsnumber;
                                            //numberIn = 1;//默认前方的为数字
                                            break;
                                        case "-":
                                            if ((l > 1) && (midinput.substring(l - 2, l - 1).equals("+") || midinput.substring(l - 2, l - 1).equals("-") ||
                                                    midinput.substring(l - 2, l - 1).equals("+") || midinput.substring(l - 2, l - 1).equals("*") || midinput.substring(l - 2, l - 1).equals("/"))) {
                                                outNumber = outNumber + bdsnumber;
                                            } else tag = false;
                                        default:
                                            tag = false;


                                    }
                                    if (l == 1) {
                                        break;
                                    }
                                    l--;


                                }
                                midinput = midinput.substring(0, midinput.length() - outNumber.length());
                                String out2 = "";
                                for (int i = 0; i < outNumber.length(); i++) {
                                    String bdnumber = outNumber.substring(outNumber.length() - 1 - i, outNumber.length() - i);
                                    out2 = out2 + bdnumber;

                                }
                                double outDouble = Double.valueOf(out2);
                                inputText.setText(midinput + String.valueOf(Math.cos(outDouble)));

                            } else if (!outText.getText().toString().equals("")) {
                                numberIn = 1;
                                midinput = outText.getText().toString();
                                double outDouble = Math.cos(Double.valueOf(midinput));
                                inputText.setText(String.valueOf(outDouble));
                                outText.setText("");


                            }
                            break;

                        case R.id.buttonTAN://TAN
                            Log.i("OnClick", "TAN");//DEBUG:测试输入
                            outText = (EditText) findViewById(R.id.OutputTextView2);
                            inputText = (TextView) findViewById(R.id.InputTextView);
                            if ((outText.getText().toString().equals("") || outText.getText() == null) && (numberIn != 0)
                                    && !inputText.getText().toString().equals("") && inputText.getText() != null) {
                                midinput = inputText.getText().toString();
                                int l = midinput.length();
                                Log.i(TAG, "onClickPercentage: " + l);
                                boolean tag = true;
                                String outNumber = "";
                                while (tag == true) {
                                    String bdsnumber = midinput.substring(l - 1, l);
                                    Log.i(TAG, "onClickBDsnumber: " + bdsnumber);
                                    switch (bdsnumber) {//防止异常处理影响退格
                                        case "0":
                                        case "1":
                                        case "2":
                                        case "3":
                                        case "4":
                                        case "5":
                                        case "6":
                                        case "7":
                                        case "8":
                                        case "9":
                                        case ".":
                                            outNumber = outNumber + bdsnumber;
                                            //numberIn = 1;//默认前方的为数字
                                            break;
                                        case "-":
                                            if ((l > 1) && (midinput.substring(l - 2, l - 1).equals("+") || midinput.substring(l - 2, l - 1).equals("-") ||
                                                    midinput.substring(l - 2, l - 1).equals("+") || midinput.substring(l - 2, l - 1).equals("*") || midinput.substring(l - 2, l - 1).equals("/"))) {
                                                outNumber = outNumber + bdsnumber;
                                            } else tag = false;
                                        default:
                                            tag = false;


                                    }
                                    if (l == 1) {
                                        break;
                                    }
                                    l--;


                                }
                                midinput = midinput.substring(0, midinput.length() - outNumber.length());
                                String out2 = "";
                                for (int i = 0; i < outNumber.length(); i++) {
                                    String bdnumber = outNumber.substring(outNumber.length() - 1 - i, outNumber.length() - i);
                                    out2 = out2 + bdnumber;

                                }
                                double outDouble = Double.valueOf(out2);
                                inputText.setText(midinput + String.valueOf(Math.tan(outDouble)));

                            } else if (!outText.getText().toString().equals("")) {
                                numberIn = 1;
                                midinput = outText.getText().toString();
                                double outDouble = Math.tan(Double.valueOf(midinput));
                                inputText.setText(String.valueOf(outDouble));
                                outText.setText("");


                            }
                            break;
                        case R.id.buttonLn://ln
                            Log.i("OnClick", "ln");//DEBUG:测试输入
                            outText = (EditText) findViewById(R.id.OutputTextView2);
                            inputText = (TextView) findViewById(R.id.InputTextView);
                            if ((outText.getText().toString().equals("") || outText.getText() == null) && (numberIn != 0)
                                    && !inputText.getText().toString().equals("") && inputText.getText() != null) {
                                midinput = inputText.getText().toString();
                                int l = midinput.length();
                                Log.i(TAG, "onClickPercentage: " + l);
                                boolean tag = true;
                                String outNumber = "";
                                while (tag == true) {
                                    String bdsnumber = midinput.substring(l - 1, l);
                                    Log.i(TAG, "onClickBDsnumber: " + bdsnumber);
                                    switch (bdsnumber) {//防止异常处理影响退格
                                        case "0":
                                        case "1":
                                        case "2":
                                        case "3":
                                        case "4":
                                        case "5":
                                        case "6":
                                        case "7":
                                        case "8":
                                        case "9":
                                        case ".":
                                            outNumber = outNumber + bdsnumber;
                                            //numberIn = 1;//默认前方的为数字
                                            break;
                                        case "-":
                                            if ((l > 1) && (midinput.substring(l - 2, l - 1).equals("+") || midinput.substring(l - 2, l - 1).equals("-") ||
                                                    midinput.substring(l - 2, l - 1).equals("+") || midinput.substring(l - 2, l - 1).equals("*") || midinput.substring(l - 2, l - 1).equals("/"))) {
                                                outNumber = outNumber + bdsnumber;
                                            } else tag = false;
                                        default:
                                            tag = false;


                                    }
                                    if (l == 1) {
                                        break;
                                    }
                                    l--;


                                }
                                midinput = midinput.substring(0, midinput.length() - outNumber.length());
                                String out2 = "";
                                for (int i = 0; i < outNumber.length(); i++) {
                                    String bdnumber = outNumber.substring(outNumber.length() - 1 - i, outNumber.length() - i);
                                    out2 = out2 + bdnumber;

                                }
                                double outDouble = Double.valueOf(out2);
                                inputText.setText(midinput + String.valueOf(Math.log(outDouble)));

                            } else if (!outText.getText().toString().equals("")) {
                                numberIn = 1;
                                midinput = outText.getText().toString();
                                double outDouble = Math.log(Double.valueOf(midinput));
                                inputText.setText(String.valueOf(outDouble));
                                outText.setText("");


                            }
                            break;
                        case R.id.buttonLog://开根号
                            Log.i("OnClick", "计算loga(x)");//DEBUG:测试输入
                            outText = (EditText) findViewById(R.id.OutputTextView2);
                            inputText = (TextView) findViewById(R.id.InputTextView);
                            AlertDialog.Builder dialogRoot = new AlertDialog.Builder(MainActivity.this);
                            dialogRoot.setTitle("输入要运算的log的数（或表达式）");
                            final LayoutInflater logDialog = getLayoutInflater();
                            final View logView = logDialog.inflate(R.layout.log_layout, null);
                            dialogRoot.setView(logView);
                            final EditText ET_x = logView.findViewById(R.id.textXin);
                            final EditText ET_a = logView.findViewById(R.id.textAin);
                            if (!outText.getText().toString().equals("") || outText.getText() == null) {
                                logNegativeS=inputText.getText().toString();
                                inputText.setText("");
                                ET_x.setText(outText.getText().toString());

                            }
                            else {
                                logNegativeS="";
                            }
                            dialogRoot.setPositiveButton("计算", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    //Toast.makeText(MainActivity.this,"填写确定监听事件",Toast.LENGTH_SHORT).show();
                                    if(!ET_a.getText().toString().equals("")&&!ET_x.getText().toString().equals("")){
                                        double x=CalculationCode.calculate(ET_x.getText().toString());
                                        double a=CalculationCode.calculate(ET_a.getText().toString());
                                        double d=Math.log(a)/Math.log(x);
                                        if(!logNegativeS.equals("")){//判断outText是否有值

                                            inputText.setText(String.valueOf(d));
                                        }//outText有值
                                        else{
                                            midinput=inputText.getText().toString();
                                            inputText.setText(midinput+d);
                                        }
                                        outText.setText("");
                                    }
                                    else{
                                        Toast.makeText(MainActivity.this,"必须大于零，二者均不能为空",Toast.LENGTH_LONG);
                                    }

                                }
                            });
                            dialogRoot.setNegativeButton("取消", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    if(logNegativeS.equals("")){

                                    }
                                    else{
                                        inputText.setText(logNegativeS);

                                    }

                                }
                            });
                            dialogRoot.show();
                            numberIn = 1;

                            break;
                        case R.id.buttonE:
                            if(!outText.getText().toString().equals("")){
                                outText.setText("");
                                inputText.setText(String.valueOf(Math.E));
                            }
                            else{
                                midinput=inputText.getText().toString();
                                inputText.setText(midinput+Math.E);
                            }
                            numberIn=1;
                            break;
                        case R.id.buttonXshift1:
                            Log.i("OnClick", "X!");//DEBUG:测试输入
                            outText = (EditText) findViewById(R.id.OutputTextView2);
                            inputText = (TextView) findViewById(R.id.InputTextView);
                            if ((outText.getText().toString().equals("") || outText.getText() == null) && (numberIn != 0)
                                    && !inputText.getText().toString().equals("") && inputText.getText() != null) {
                                midinput = inputText.getText().toString();
                                int l = midinput.length();
                                Log.i(TAG, "onClickPercentage: " + l);
                                boolean tag = true;
                                String outNumber = "";
                                while (tag == true) {
                                    String bdsnumber = midinput.substring(l - 1, l);
                                    Log.i(TAG, "onClickBDsnumber: " + bdsnumber);
                                    switch (bdsnumber) {//防止异常处理影响退格
                                        case "0":
                                        case "1":
                                        case "2":
                                        case "3":
                                        case "4":
                                        case "5":
                                        case "6":
                                        case "7":
                                        case "8":
                                        case "9":
                                        case ".":
                                            outNumber = outNumber + bdsnumber;
                                            //numberIn = 1;//默认前方的为数字
                                            break;
                                        case "-":
                                            if ((l > 1) && (midinput.substring(l - 2, l - 1).equals("+") || midinput.substring(l - 2, l - 1).equals("-") ||
                                                    midinput.substring(l - 2, l - 1).equals("+") || midinput.substring(l - 2, l - 1).equals("*") || midinput.substring(l - 2, l - 1).equals("/"))) {
                                                outNumber = outNumber + bdsnumber;
                                            } else tag = false;
                                        default:
                                            tag = false;


                                    }
                                    if (l == 1) {
                                        break;
                                    }
                                    l--;


                                }
                                midinput = midinput.substring(0, midinput.length() - outNumber.length());
                                String out2 = "";
                                for (int i = 0; i < outNumber.length(); i++) {
                                    String bdnumber = outNumber.substring(outNumber.length() - 1 - i, outNumber.length() - i);
                                    out2 = out2 + bdnumber;

                                }
                                int outint1 = Integer.valueOf(out2);
                                double outint2 = 1;
                                for(int i=outint1;i>=1;i--){
                                    outint2=outint2*i;

                                }
                                inputText.setText(midinput+outint2);

                            } else if (!outText.getText().toString().equals("")) {
                                numberIn = 1;
                                midinput = outText.getText().toString();
                                int outint1 = Integer.valueOf(midinput);
                                double outint2 = 1;
                                for(int i=outint1;i>=1;i--){
                                    outint2=outint2*i;

                                }

                                inputText.setText(String.valueOf(outint2));
                                outText.setText("");


                            }
                            break;
                        case R.id.buttonLongchang://长度转换
                            Log.i("OnClick", "开启长度转换窗口");//DEBUG:测试输入
                            outText = (EditText) findViewById(R.id.OutputTextView2);
                            inputText = (TextView) findViewById(R.id.InputTextView);
                            AlertDialog.Builder LongChange = new AlertDialog.Builder(MainActivity.this);
                            LongChange.setTitle("长度换算");
                            final LayoutInflater longCD = getLayoutInflater();
                            final View longView = longCD.inflate(R.layout.long_change_layout, null);
                            LongChange.setView(longView);
                            final EditText textKM = longView.findViewById(R.id.editKm);
                            final EditText textM = longView.findViewById(R.id.editM);
                            final EditText textCM = longView.findViewById(R.id.editCm);
                            Button buttonLchange=longView.findViewById(R.id.buttonLCchange);
                            Button buttonLcancel=longView.findViewById(R.id.buttonLCchance);
                            buttonLcancel.setOnClickListener(new View.OnClickListener() {
                                @Override
                                public void onClick(View view) {
                                    textKM.setText("");
                                    textCM.setText("");
                                    textM.setText("");
                                }
                            });
                            buttonLchange.setOnClickListener(new View.OnClickListener() {
                                @Override
                                public void onClick(View view) {
                                    if(!textKM.getText().toString().equals("")){
                                        double km=Double.valueOf(textKM.getText().toString());
                                        textM.setText(String.valueOf(km*1000));
                                        textCM.setText(String.valueOf(km*1000000));
                                    }
                                    else if(!textM.getText().toString().equals("")){
                                        double m=Double.valueOf(textM.getText().toString());
                                        textKM.setText(String.valueOf(m/1000));
                                        textCM.setText(String.valueOf(m*1000));

                                    }
                                    else if(!textCM.getText().toString().equals("")){
                                        double cm=Double.valueOf(textCM.getText().toString());
                                        textKM.setText(String.valueOf(cm/1000000));
                                        textM.setText(String.valueOf(cm/1000));

                                    }
                                    else
                                        Toast.makeText(MainActivity.this,"请输入数字",Toast.LENGTH_LONG);
                                }
                            });

                            LongChange.setNegativeButton("关闭", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {


                                }
                            });
                            LongChange.show();
                            break;
                        case R.id.buttonWChange://重量转换
                            Log.i("OnClick", "开启重量转换窗口");//DEBUG:测试输入
                            outText = (EditText) findViewById(R.id.OutputTextView2);
                            inputText = (TextView) findViewById(R.id.InputTextView);
                            AlertDialog.Builder WeightChange = new AlertDialog.Builder(MainActivity.this);
                            WeightChange.setTitle("重量换算");
                            final LayoutInflater WeightCD = getLayoutInflater();
                            final View WeightView = WeightCD.inflate(R.layout.weight_change_layout, null);
                            WeightChange.setView(WeightView);
                            final EditText textT = WeightView.findViewById(R.id.editT);
                            final EditText textKg = WeightView.findViewById(R.id.editKg);
                            final EditText textG = WeightView.findViewById(R.id.editG);
                            Button buttonWchange=WeightView.findViewById(R.id.buttonW_change);
                            Button buttonWcancel=WeightView.findViewById(R.id.buttonW_chance);
                            buttonWcancel.setOnClickListener(new View.OnClickListener() {
                                @Override
                                public void onClick(View view) {
                                    textT.setText("");
                                    textKg.setText("");
                                    textG.setText("");
                                }
                            });
                            buttonWchange.setOnClickListener(new View.OnClickListener() {
                                @Override
                                public void onClick(View view) {
                                    if(!textT.getText().toString().equals("")){
                                        double km=Double.valueOf(textT.getText().toString());
                                        textKg.setText(String.valueOf(km*1000));
                                        textG.setText(String.valueOf(km*1000000));
                                    }
                                    else if(!textKg.getText().toString().equals("")){
                                        double m=Double.valueOf(textKg.getText().toString());
                                        textT.setText(String.valueOf(m/1000));
                                        textG.setText(String.valueOf(m*1000));

                                    }
                                    else if(!textG.getText().toString().equals("")){
                                        double cm=Double.valueOf(textG.getText().toString());
                                        textT.setText(String.valueOf(cm/1000000));
                                        textKg.setText(String.valueOf(cm/1000));

                                    }
                                    else
                                        Toast.makeText(MainActivity.this,"请输入数字",Toast.LENGTH_LONG);
                                }
                            });

                            WeightChange.setNegativeButton("关闭", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {


                                }
                            });
                            WeightChange.show();
                            break;
                        case R.id.buttonNumberChange://进制转换
                            Log.i("OnClick", "开启进制转换窗口");//DEBUG:测试输入
                            outText = (EditText) findViewById(R.id.OutputTextView2);
                            inputText = (TextView) findViewById(R.id.InputTextView);
                            AlertDialog.Builder NumberChange = new AlertDialog.Builder(MainActivity.this);
                            NumberChange.setTitle("重量换算");
                            final LayoutInflater NumberCD = getLayoutInflater();
                            final View NumberView = NumberCD.inflate(R.layout.linear_number_change_layout, null);
                            NumberChange.setView(NumberView);
                            final EditText editIn1 = NumberView.findViewById(R.id.editInput1);
                            final EditText editIn2 = NumberView.findViewById(R.id.editInput2);
                            final Spinner  spinner1=NumberView.findViewById(R.id.spinner1);//获取两下拉栏
                            final Spinner spinner2=NumberView.findViewById(R.id.spinner2);
                            Button buttonNchange=NumberView.findViewById(R.id.buttonN_change);
                            Button buttonNchance=NumberView.findViewById(R.id.buttonN_chance);
                            buttonNchange.setOnClickListener(new View.OnClickListener() {
                                @Override
                                public void onClick(View view) {
                                    int s1=spinner1.getSelectedItemPosition();
                                    int s2=spinner2.getSelectedItemPosition();
                                    Log.i(TAG, "onClick:spinner1 position:"+s1+"spinner2:"+s2);//测试位置
                                    Integer midInt = null;
                                    String midStr;
                                    if(!editIn1.getText().toString().equals("")){//第一个空不为空
                                        switch (s1){//输入处理
                                            case 0://二进制
                                                midStr=editIn1.getText().toString();
                                                midInt=Integer.parseInt(midStr,2);
                                                break;
                                            case 1://十进制
                                                midInt=Integer.valueOf(editIn1.getText().toString());
                                                break;
                                            case 2://八进制
                                                midStr=editIn1.getText().toString();
                                                midInt=Integer.parseInt(midStr,8);
                                                break;
                                            case 3://十六进制
                                                midStr=editIn1.getText().toString();
                                                midInt=Integer.parseInt(midStr,16);
                                                break;
                                            default:
                                        }
                                        switch (s2){//输出处理
                                            case 0://二进制
                                                midStr=Integer.toBinaryString(midInt);
                                                editIn2.setText(midStr);
                                                break;
                                            case 1://十进制
                                                editIn2.setText(String.valueOf(midInt));
                                                break;
                                            case 2://八进制
                                                midStr=Integer.toOctalString(midInt);
                                                editIn2.setText(midStr);

                                                break;
                                            case 3://十六进制
                                                midStr=Integer.toHexString(midInt);
                                                editIn2.setText(midStr);
                                                break;
                                            default:

                                        }

                                    }
                                    else if(!editIn2.getText().toString().equals("")){//第二个空不为空
                                        switch (s2){//输入处理
                                            case 0://二进制
                                                midStr=editIn2.getText().toString();
                                                midInt=Integer.parseInt(midStr,2);
                                                break;
                                            case 1://十进制
                                                midInt=Integer.valueOf(editIn2.getText().toString());
                                                break;
                                            case 2://八进制
                                                midStr=editIn2.getText().toString();
                                                midInt=Integer.parseInt(midStr,8);
                                                break;
                                            case 3://十六进制
                                                midStr=editIn2.getText().toString();
                                                midInt=Integer.parseInt(midStr,16);
                                                break;
                                            default:
                                        }
                                        switch (s1){//输出处理
                                            case 0://二进制
                                                midStr=Integer.toBinaryString(midInt);
                                                editIn1.setText(midStr);
                                                break;
                                            case 1://十进制
                                                editIn1.setText(String.valueOf(midInt));
                                                break;
                                            case 2://八进制
                                                midStr=Integer.toOctalString(midInt);
                                                editIn1.setText(midStr);

                                                break;
                                            case 3://十六进制
                                                midStr=Integer.toHexString(midInt);
                                                editIn1.setText(midStr);
                                                break;
                                            default:

                                        }

                                    }
                                    else{
                                        //无操作
                                    }

                                }
                            });
                            buttonNchance.setOnClickListener(new View.OnClickListener() {
                                @Override
                                public void onClick(View view) {
                                    int s1=spinner1.getSelectedItemPosition();
                                    editIn1.setText("");
                                    editIn2.setText("");


                                }
                            });

                            NumberChange.setNegativeButton("关闭", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {


                                }
                            });
                            NumberChange.show();
                            break;

                        case R.id.buttonMoney://实时汇率
                            Log.i("OnClick", "开启实时汇率窗口");//DEBUG:测试输入
                           //TODO
                            //免费api不太好找
                            break;

                        case R.id.buttonData://进制转换
                            Log.i("OnClick", "开启日期转换");//DEBUG:测试输入
                            outText = (EditText) findViewById(R.id.OutputTextView2);
                            inputText = (TextView) findViewById(R.id.InputTextView);
                            AlertDialog.Builder DataChange = new AlertDialog.Builder(MainActivity.this);
                            DataChange.setTitle("日期换算");
                            final LayoutInflater DataCD = getLayoutInflater();
                            final View dataView = DataCD.inflate(R.layout.linear_datas_change_layout, null);
                            DataChange.setView(dataView);
//                            final EditText editIn1 = NumberView.findViewById(R.id.editInput1);
//                            final EditText editIn2 = NumberView.findViewById(R.id.editInput2);
//                            final Spinner  spinner1=NumberView.findViewById(R.id.spinner1);//获取两下拉栏
//                            final Spinner spinner2=NumberView.findViewById(R.id.spinner2);
//                            Button buttonNchange=NumberView.findViewById(R.id.buttonN_change);
//                            Button buttonNchance=NumberView.findViewById(R.id.buttonN_chance);
//                            buttonNchange.setOnClickListener(new View.OnClickListener() {
//                                @Override
//                                public void onClick(View view) {
//                                    int s1=spinner1.getSelectedItemPosition();
//                                    int s2=spinner2.getSelectedItemPosition();
//                                    Log.i(TAG, "onClick:spinner1 position:"+s1+"spinner2:"+s2);//测试位置
//                                    Integer midInt = null;
//                                    String midStr;
//                                    if(!editIn1.getText().toString().equals("")){//第一个空不为空
//                                        switch (s1){//输入处理
//                                            case 0://二进制
//                                                midStr=editIn1.getText().toString();
//                                                midInt=Integer.parseInt(midStr,2);
//                                                break;
//                                            case 1://十进制
//                                                midInt=Integer.valueOf(editIn1.getText().toString());
//                                                break;
//                                            case 2://八进制
//                                                midStr=editIn1.getText().toString();
//                                                midInt=Integer.parseInt(midStr,8);
//                                                break;
//                                            case 3://十六进制
//                                                midStr=editIn1.getText().toString();
//                                                midInt=Integer.parseInt(midStr,16);
//                                                break;
//                                            default:
//                                        }
//                                        switch (s2){//输出处理
//                                            case 0://二进制
//                                                midStr=Integer.toBinaryString(midInt);
//                                                editIn2.setText(midStr);
//                                                break;
//                                            case 1://十进制
//                                                editIn2.setText(String.valueOf(midInt));
//                                                break;
//                                            case 2://八进制
//                                                midStr=Integer.toOctalString(midInt);
//                                                editIn2.setText(midStr);
//
//                                                break;
//                                            case 3://十六进制
//                                                midStr=Integer.toHexString(midInt);
//                                                editIn2.setText(midStr);
//                                                break;
//                                            default:
//
//                                        }
//
//                                    }
//                                    else if(!editIn2.getText().toString().equals("")){//第二个空不为空
//                                        switch (s2){//输入处理
//                                            case 0://二进制
//                                                midStr=editIn2.getText().toString();
//                                                midInt=Integer.parseInt(midStr,2);
//                                                break;
//                                            case 1://十进制
//                                                midInt=Integer.valueOf(editIn2.getText().toString());
//                                                break;
//                                            case 2://八进制
//                                                midStr=editIn2.getText().toString();
//                                                midInt=Integer.parseInt(midStr,8);
//                                                break;
//                                            case 3://十六进制
//                                                midStr=editIn2.getText().toString();
//                                                midInt=Integer.parseInt(midStr,16);
//                                                break;
//                                            default:
//                                        }
//                                        switch (s1){//输出处理
//                                            case 0://二进制
//                                                midStr=Integer.toBinaryString(midInt);
//                                                editIn1.setText(midStr);
//                                                break;
//                                            case 1://十进制
//                                                editIn1.setText(String.valueOf(midInt));
//                                                break;
//                                            case 2://八进制
//                                                midStr=Integer.toOctalString(midInt);
//                                                editIn1.setText(midStr);
//
//                                                break;
//                                            case 3://十六进制
//                                                midStr=Integer.toHexString(midInt);
//                                                editIn1.setText(midStr);
//                                                break;
//                                            default:
//
//                                        }
//
//                                    }
//                                    else{
//                                        //无操作
//                                    }
//
//                                }
//                            });
//                            buttonNchance.setOnClickListener(new View.OnClickListener() {
//                                @Override
//                                public void onClick(View view) {
//                                    int s1=spinner1.getSelectedItemPosition();
//                                    editIn1.setText("");
//                                    editIn2.setText("");
//
//
//                                }
//                            });

                            DataChange.setNegativeButton("关闭", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {


                                }
                            });
                            DataChange.show();
                            break;


                        default:


                    }
                }
            };
            /*******************横屏按钮绑定************************/
            Button buttonSin = (Button) findViewById(R.id.buttonSin);//sin
            buttonSin.setOnClickListener(normalOperationListener);
            Button buttonCos = (Button) findViewById(R.id.buttonCos);//cos
            buttonCos.setOnClickListener(normalOperationListener);
            Button buttonTan = (Button) findViewById(R.id.buttonTAN);//tan
            buttonTan.setOnClickListener(normalOperationListener);
            Button buttonLn = (Button) findViewById(R.id.buttonLn);//ln
            buttonLn.setOnClickListener(normalOperationListener);
            Button buttonLog = (Button) findViewById(R.id.buttonLog);//log
            buttonLog.setOnClickListener(normalOperationListener);
            Button buttonE=(Button)findViewById(R.id.buttonE);//E
            buttonE.setOnClickListener(normalOperationListener);
            Button buttonXshitf1=(Button)findViewById(R.id.buttonXshift1);//X!
            buttonXshitf1.setOnClickListener(normalOperationListener);
            Button buttonLChange=(Button)findViewById(R.id.buttonLongchang);//长度换算
            buttonLChange.setOnClickListener(normalOperationListener);
            Button buttonWChange=(Button)findViewById(R.id.buttonWChange);//重量换算
            buttonWChange.setOnClickListener(normalOperationListener);
            Button buttonNumberChange=(Button)findViewById(R.id.buttonNumberChange);//进制转换
            buttonNumberChange.setOnClickListener(normalOperationListener);
            Button buttonMoney=(Button)findViewById(R.id.buttonMoney);//因为api关系，未实现
            buttonMoney.setOnClickListener(normalOperationListener);
            Button buttonData=(Button)findViewById(R.id.buttonData);
            buttonData.setOnClickListener(normalOperationListener);




        }//横屏布局与监听器
        else if (this.getResources().getConfiguration().orientation == Configuration.ORIENTATION_PORTRAIT) {
            Log.i(TAG, "竖屏portrait");
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_main);
            normalOperationListener = new View.OnClickListener() {
                EditText outText;
                TextView inputText;
                String midinput;

                @Override
                public void onClick(View view) {
                    outText = (EditText) findViewById(R.id.OutputTextView2);
                    inputText = (TextView) findViewById(R.id.InputTextView);

                    switch (view.getId()) {


                        case R.id.buttonAdd://加法
                            Log.i("OnClick", "+");//DEBUG:测试输入
                            outText = (EditText) findViewById(R.id.OutputTextView2);
                            inputText = (TextView) findViewById(R.id.InputTextView);
                            if (numberIn != 0) {
                                if (!outText.getText().toString().equals("") && outText.getText() != null) {
                                    inputText.setText(outText.getText(), null);//如果结果有值，则替换输入端
                                    outText.setText("");
                                    Log.i(TAG, "onClick:-" + outText.getText());

                                }
                                midinput = inputText.getText().toString();
                                midinput = midinput + "+";
                                inputText.setText(midinput);
                                numberIn = 0;
                            } else
                                Toast.makeText(MainActivity.this, "输入错误，符号不要相连", Toast.LENGTH_SHORT).show();
                            break;
                        case R.id.buttonMinus://减法
                            Log.i("OnClick", "-");//DEBUG:测试输入
                            outText = (EditText) findViewById(R.id.OutputTextView2);
                            inputText = (TextView) findViewById(R.id.InputTextView);
                            if (!outText.getText().toString().equals("") && outText.getText() != null) {
                                inputText.setText(outText.getText(), null);//如果结果有值，则替换输入端
                                outText.setFocusableInTouchMode(true);
                                outText.setFocusable(true);
                                outText.setText("");//如果结果有值，输入数字时则清空
                                outText.setFocusableInTouchMode(false);
                                outText.setFocusable(false);
                                Log.i(TAG, "onClick:-" + outText.getText());

                            }
                            midinput = inputText.getText().toString();
                            midinput = midinput + "-";
                            inputText.setText(midinput);
                            break;
                        case R.id.buttonMultiply://乘法
                            if (numberIn != 0) {
                                Log.i("OnClick", "*");//DEBUG:测试输入
                                outText = (EditText) findViewById(R.id.OutputTextView2);
                                inputText = (TextView) findViewById(R.id.InputTextView);
                                if (!outText.getText().toString().equals("") && outText.getText() != null) {
                                    inputText.setText(outText.getText(), null);//如果结果有值，则替换输入端
                                    outText.setFocusableInTouchMode(true);
                                    outText.setFocusable(true);
                                    outText.setText("");//如果结果有值，输入数字时则清空
                                    outText.setFocusableInTouchMode(false);
                                    outText.setFocusable(false);
                                    Log.i(TAG, "onClick:-" + outText.getText());

                                }
                                midinput = inputText.getText().toString();
                                midinput = midinput + "*";
                                inputText.setText(midinput);
                                numberIn = 0;
                            } else {
                                Toast.makeText(MainActivity.this, "输入错误，不要连续输入符号", Toast.LENGTH_SHORT).show();

                            }

                            break;
                        case R.id.buttonExcept://除法
                            if (numberIn != 0) {
                                Log.i("OnClick", "/");//DEBUG:测试输入
                                outText = (EditText) findViewById(R.id.OutputTextView2);
                                inputText = (TextView) findViewById(R.id.InputTextView);
                                if (!outText.getText().toString().equals("") && outText.getText() != null) {
                                    inputText.setText(outText.getText(), null);//如果结果有值，则替换输入端
                                    outText.setFocusableInTouchMode(true);
                                    outText.setFocusable(true);
                                    outText.setText("");//如果结果有值，输入数字时则清空
                                    outText.setFocusableInTouchMode(false);
                                    outText.setFocusable(false);
                                    Log.i(TAG, "onClick:-" + outText.getText());

                                }
                                midinput = inputText.getText().toString();
                                midinput = midinput + "/";
                                inputText.setText(midinput);
                                numberIn = 0;
                            } else
                                Toast.makeText(MainActivity.this, "输入错误，不要连续输入符号", Toast.LENGTH_SHORT).show();

                            break;
                        case R.id.buttonBack://退格
                            Log.i("OnClick", "退格");//DEBUG:测试输入
                            outText = (EditText) findViewById(R.id.OutputTextView2);
                            inputText = (TextView) findViewById(R.id.InputTextView);
                            String outtext = outText.getText().toString();
                            if (!outtext.equals("")) {
                                outText.setText("");
                                inputText.setText("");
                                numberIn = 0;
                            }
                            if (inputText.getText().toString().equals("") || inputText.getText() == null) {
                                numberIn = 0;
                                break;

                            }

                            midinput = inputText.getText().toString();
                            if (midinput.length() > 1) {
                                String bdnumber = midinput.substring(midinput.length() - 2, midinput.length() - 1);
                                switch (bdnumber) {//防止异常处理影响退格
                                    case "0":
                                    case "1":
                                    case "2":
                                    case "3":
                                    case "4":
                                    case "5":
                                    case "6":
                                    case "7":
                                    case "8":
                                    case "9":
                                    case ".":
                                        numberIn = 1;//默认前方的为数字
                                        break;
                                    case ")":
                                        bracketPoint++;
                                    case "(":
                                        bracketPoint--;
                                    default:
                                        numberIn = 0;

                                }

                            } else
                                numberIn = 1;

                            midinput = midinput.substring(0, midinput.length() - 1);

                            inputText.setText(midinput);
                            break;
                        case R.id.buttonClear://清空
                            Log.i("OnClick", "清空");//DEBUG:测试输入

                            outText = (EditText) findViewById(R.id.OutputTextView2);
                            inputText = (TextView) findViewById(R.id.InputTextView);
                            outText.setFocusableInTouchMode(true);
                            outText.setFocusable(true);
                            outText.setText("");//如果结果有值，输入数字时则清空
                            outText.setFocusableInTouchMode(false);
                            outText.setFocusable(false);
                            inputText.setText("");
                            numberIn = 0;
                            break;
                        case R.id.buttonRoot://开根号
                            Log.i("OnClick", "开根号");//DEBUG:测试输入
                            outText = (EditText) findViewById(R.id.OutputTextView2);
                            inputText = (TextView) findViewById(R.id.InputTextView);
                            if (outText.getText().toString().equals("") || outText.getText() == null) {
                                AlertDialog.Builder dialogRoot = new AlertDialog.Builder(MainActivity.this);
                                dialogRoot.setTitle("输入要开根号的值(表达式)");
                                LayoutInflater rootDialog = getLayoutInflater();
                                final View rootView = rootDialog.inflate(R.layout.root_layout, null);
                                dialogRoot.setView(rootView);
                                dialogRoot.setPositiveButton("计算", new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialog, int which) {
                                        //Toast.makeText(MainActivity.this,"填写确定监听事件",Toast.LENGTH_SHORT).show();
                                        EditText rootET = rootView.findViewById(R.id.textRootIn);
                                        String rootN = rootET.getText().toString();
                                        midinput = inputText.getText().toString();
                                        double middouble = Double.valueOf(CalculationCode.calculate(rootN));
                                        Log.i("ErJikeRoot:", "middouble：" + middouble);

                                        inputText.setText(midinput + String.valueOf(CalculationCode.root(middouble)));
                                        Log.i(TAG, "最终结果：" + midinput);
                                    }
                                });
                                dialogRoot.setNegativeButton("取消", new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialog, int which) {
                                        //finish();//测试如何升级GitHub内容
                                    }
                                });
                                dialogRoot.show();
                                numberIn = 1;

                            } else {
                                midinput = inputText.getText().toString();
                                String outmid = outText.getText().toString();
                                double middoubles = Double.valueOf(outmid);
                                midinput = String.valueOf(CalculationCode.root(middoubles));
                                inputText.setText(midinput);
                                outText.setText("");
                                Log.i("ErJikeRoot", "output有数字，输出：" + midinput);
                                numberIn = 1;
                            }
                            break;
                        case R.id.buttonEqual://等于
                            Log.i("OnClick", "等于");//DEBUG:测试输入
                            outText = (EditText) findViewById(R.id.OutputTextView2);
                            inputText = (TextView) findViewById(R.id.InputTextView);
                            if (numberIn == 0) {
                                Toast.makeText(MainActivity.this, "算式错误，请保证四则运算符左右有数字", Toast.LENGTH_LONG).show();

                            }
                            if (bracketPoint == 0 && numberIn != 0) {
                                midinput = String.valueOf(CalculationCode.calculate(inputText.getText().toString()));
                                Log.i(TAG, "最终结果：" + midinput);
                                outText.setFocusableInTouchMode(true);
                                outText.setFocusable(true);
                                outText.setText(midinput);
                                outText.setFocusableInTouchMode(false);
                                outText.setFocusable(false);

                            } else
                                Toast.makeText(MainActivity.this, "算式格式错误，无法计算", Toast.LENGTH_LONG).show();


                            //调用Calculation方法
                            break;
                        case R.id.buttonPercentage://百分比
                            Log.i("OnClick", "百分比");//DEBUG:测试输入
                            outText = (EditText) findViewById(R.id.OutputTextView2);
                            inputText = (TextView) findViewById(R.id.InputTextView);
                            if ((outText.getText().toString().equals("") || outText.getText() == null) && (numberIn != 0)
                                    && !inputText.getText().toString().equals("") && inputText.getText() != null) {
                                midinput = inputText.getText().toString();
                                int l = midinput.length();
                                Log.i(TAG, "onClickPercentage: " + l);
                                boolean tag = true;
                                String outNumber = "";
                                while (tag == true) {
                                    String bdsnumber = midinput.substring(l - 1, l);
                                    Log.i(TAG, "onClickBDsnumber: " + bdsnumber);
                                    switch (bdsnumber) {//防止异常处理影响退格
                                        case "0":
                                        case "1":
                                        case "2":
                                        case "3":
                                        case "4":
                                        case "5":
                                        case "6":
                                        case "7":
                                        case "8":
                                        case "9":
                                        case ".":
                                            outNumber = outNumber + bdsnumber;
                                            //numberIn = 1;//默认前方的为数字
                                            break;
                                        case "-":
                                            if ((l > 1) && (midinput.substring(l - 2, l - 1).equals("+") || midinput.substring(l - 2, l - 1).equals("-") ||
                                                    midinput.substring(l - 2, l - 1).equals("+") || midinput.substring(l - 2, l - 1).equals("*") || midinput.substring(l - 2, l - 1).equals("/"))) {
                                                outNumber = outNumber + bdsnumber;
                                            } else tag = false;
                                        default:
                                            tag = false;


                                    }
                                    if (l == 1) {
                                        break;
                                    }
                                    l--;


                                }
                                midinput = midinput.substring(0, midinput.length() - outNumber.length());
                                String out2 = "";
                                for (int i = 0; i < outNumber.length(); i++) {
                                    String bdnumber = outNumber.substring(outNumber.length() - 1 - i, outNumber.length() - i);
                                    out2 = out2 + bdnumber;

                                }
                                double outDouble = Double.valueOf(out2) * 0.01;
                                inputText.setText(midinput + String.valueOf(outDouble));
                            } else if (!outText.getText().toString().equals("")) {
                                numberIn = 1;
                                midinput = outText.getText().toString();
                                double outDouble = Double.valueOf(midinput) * 0.01;
                                inputText.setText(String.valueOf(outDouble));
                                outText.setText("");


                            }
                            break;
                        case R.id.buttonPoint://点
                            Log.i("OnClick", "点");//DEBUG:测试输入
                            outText = (EditText) findViewById(R.id.OutputTextView2);
                            inputText = (TextView) findViewById(R.id.InputTextView);
                            if (!outText.getText().equals("")) {
                                outText.setFocusableInTouchMode(true);
                                outText.setFocusable(true);
                                outText.setText("");//如果结果有值，输入数字时则清空
                                outText.setFocusableInTouchMode(false);
                                outText.setFocusable(false);
                            }
                            midinput = inputText.getText().toString();
                            midinput = midinput + '.';
                            inputText.setText(midinput);
                            break;
                        case R.id.buttonLbrackets://左括号
                            Log.i("OnClick", "左括号");//DEBUG:测试输入
                            outText = (EditText) findViewById(R.id.OutputTextView2);
                            inputText = (TextView) findViewById(R.id.InputTextView);
                            if (!outText.getText().equals("")) {
                                outText.setText("");//如果结果有值，输入数字时则清空
                            }
                            midinput = inputText.getText().toString();
                            midinput = midinput + '(';
                            inputText.setText(midinput);
                            bracketPoint = bracketPoint + 1;


                            break;
                        case R.id.buttonRbrackets://右括号
                            Log.i("OnClick", "右括号");//DEBUG:测试输入
                            outText = (EditText) findViewById(R.id.OutputTextView2);
                            inputText = (TextView) findViewById(R.id.InputTextView);
                            if (bracketPoint > 0) {
                                if (!outText.getText().equals("")) {
                                    outText.setText("");//如果结果有值，输入数字时则清空
                                }
                                midinput = inputText.getText().toString();
                                midinput = midinput + ')';
                                inputText.setText(midinput);
                                bracketPoint--;
                            } else {
                                Toast.makeText(MainActivity.this, "请保证左右括号数量相同，无法创建新的右括号", Toast.LENGTH_LONG).show();
                            }

                            break;
                        case R.id.buttonSquare://平方
                            Log.i("OnClick", "平方");//DEBUG:测试输入
                            outText = (EditText) findViewById(R.id.OutputTextView2);
                            inputText = (TextView) findViewById(R.id.InputTextView);
                            if ((outText.getText().toString().equals("") || outText.getText() == null) && (numberIn != 0)
                                    && !inputText.getText().toString().equals("") && inputText.getText() != null) {
                                midinput = inputText.getText().toString();
                                int l = midinput.length();
                                Log.i(TAG, "onClickPercentage: " + l);
                                boolean tag = true;
                                String outNumber = "";
                                while (tag == true) {
                                    String bdsnumber = midinput.substring(l - 1, l);
                                    //Log.v(TAG, "onClickBDsnumber: "+bdsnumber);
                                    switch (bdsnumber) {//防止异常处理影响退格
                                        case "0":
                                        case "1":
                                        case "2":
                                        case "3":
                                        case "4":
                                        case "5":
                                        case "6":
                                        case "7":
                                        case "8":
                                        case "9":
                                        case ".":
                                            outNumber = outNumber + bdsnumber;
                                            //numberIn = 1;//默认前方的为数字
                                            break;
                                        case "-":
                                            if ((l > 1) && (midinput.substring(l - 2, l - 1).equals("+") || midinput.substring(l - 2, l - 1).equals("-") ||
                                                    midinput.substring(l - 2, l - 1).equals("+") || midinput.substring(l - 2, l - 1).equals("*") || midinput.substring(l - 2, l - 1).equals("/"))) {
                                                outNumber = outNumber + bdsnumber;
                                            } else tag = false;
                                        default:
                                            tag = false;


                                    }
                                    if (l == 1) {
                                        break;
                                    }
                                    l--;


                                }
                                midinput = midinput.substring(0, midinput.length() - outNumber.length());
                                String out2 = "";
                                for (int i = 0; i < outNumber.length(); i++) {
                                    String bdnumber = outNumber.substring(outNumber.length() - 1 - i, outNumber.length() - i);
                                    out2 = out2 + bdnumber;

                                }
                                double outDouble = Double.valueOf(out2) * Double.valueOf(out2);
                                inputText.setText(midinput + String.valueOf(outDouble));

                            } else if (!outText.getText().toString().equals("")) {
                                numberIn = 1;
                                midinput = outText.getText().toString();
                                double outDouble = Double.valueOf(midinput) * Double.valueOf(midinput);
                                inputText.setText(String.valueOf(outDouble));
                                outText.setText("");


                            }
                            break;
//                        case R.id.buttonLog://开根号
//                            Log.i("OnClick", "计算loga(x)");//DEBUG:测试输入
//                            outText = (EditText) findViewById(R.id.OutputTextView2);
//                            inputText = (TextView) findViewById(R.id.InputTextView);
//                            AlertDialog.Builder dialogRoot = new AlertDialog.Builder(MainActivity.this);
//                            dialogRoot.setTitle("输入要运算的log的数（或表达式）");
//                            final LayoutInflater logDialog = getLayoutInflater();
//                            final View logView = logDialog.inflate(R.layout.log_layout, null);
//                            dialogRoot.setView(logView);
//                            final EditText ET_x = logView.findViewById(R.id.textXin);
//                            final EditText ET_a = logView.findViewById(R.id.textAin);
//                            if (!outText.getText().toString().equals("") || outText.getText() == null) {
//                                logNegativeS=inputText.getText().toString();
//                                inputText.setText("");
//                                ET_x.setText(outText.getText().toString());
//
//                            }
//                            else {
//                                logNegativeS="";
//                            }
//                            dialogRoot.setPositiveButton("计算", new DialogInterface.OnClickListener() {
//                                @Override
//                                public void onClick(DialogInterface dialog, int which) {
//                                    //Toast.makeText(MainActivity.this,"填写确定监听事件",Toast.LENGTH_SHORT).show();
//                                    if(!ET_a.getText().toString().equals("")&&!ET_x.getText().toString().equals("")){
//                                        double x=CalculationCode.calculate(ET_x.getText().toString());
//                                        double a=CalculationCode.calculate(ET_a.getText().toString());
//                                        double d=Math.log(a)/Math.log(x);
//                                        if(!logNegativeS.equals("")){//判断outText是否有值
//
//                                            inputText.setText(String.valueOf(d));
//                                        }//outText有值
//                                        else{
//                                            midinput=inputText.getText().toString();
//                                            inputText.setText(midinput+d);
//                                        }
//                                        outText.setText("");
//                                    }
//                                    else{
//                                        Toast.makeText(MainActivity.this,"必须大于零，二者均不能为空",Toast.LENGTH_LONG);
//                                    }
//
//                                }
//                            });
//                            dialogRoot.setNegativeButton("取消", new DialogInterface.OnClickListener() {
//                                @Override
//                                public void onClick(DialogInterface dialog, int which) {
//                                    if(logNegativeS.equals("")){
//
//                                    }
//                                    else{
//                                        inputText.setText(logNegativeS);
//
//                                    }
//
//                                }
//                            });
//                            dialogRoot.show();
//                            numberIn = 1;
//
//                            break;


                        default:


                    }
                }
            };//不同界面装载不同的按键控制器
            numberIn = 1;
        }//竖屏布局与监听器
        /*****创建数字公共按键监听器*******/
        View.OnClickListener AllNumberClickListener = new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //TODO
                EditText outText;
                outText = (EditText) findViewById(R.id.OutputTextView2);
                outText.setKeyListener(null);//不可粘贴
                TextView inputText;
                String midinput;

                switch (view.getId()) {
                    case R.id.button0:
                        Log.i("OnClick", "0");//DEBUG:测试输入
                        outText = (EditText) findViewById(R.id.OutputTextView2);
                        inputText = (TextView) findViewById(R.id.InputTextView);
                        if (!outText.getText().toString().equals("") && outText.getText() != null) {

                            outText.setText("");//如果结果有值，输入数字时则清空
                            inputText.setText("");

                        }

                        midinput = inputText.getText().toString();
                        midinput = midinput + '0';
                        inputText.setText(midinput);
                        numberIn++;
                        break;
                    case R.id.button1:
                        Log.i("OnClick", "1");//DEBUG:测试输入
                        outText = (EditText) findViewById(R.id.OutputTextView2);
                        inputText = (TextView) findViewById(R.id.InputTextView);
                        if (!outText.getText().toString().equals("") && outText.getText() != null) {

                            outText.setText("");//如果结果有值，输入数字时则清空
                            inputText.setText("");

                        }

                        midinput = inputText.getText().toString();
                        midinput = midinput + '1';
                        inputText.setText(midinput);
                        numberIn++;
                        break;
                    case R.id.button2:
                        Log.i("OnClick", "6");//DEBUG:测试输入
                        outText = (EditText) findViewById(R.id.OutputTextView2);
                        inputText = (TextView) findViewById(R.id.InputTextView);
                        if (!outText.getText().toString().equals("") && outText.getText() != null) {

                            outText.setText("");//如果结果有值，输入数字时则清空
                            inputText.setText("");

                        }

                        midinput = inputText.getText().toString();
                        midinput = midinput + '2';
                        inputText.setText(midinput);
                        numberIn++;
                        break;
                    case R.id.button3:
                        Log.i("OnClick", "3");//DEBUG:测试输入
                        outText = (EditText) findViewById(R.id.OutputTextView2);
                        inputText = (TextView) findViewById(R.id.InputTextView);
                        if (!outText.getText().toString().equals("") && outText.getText() != null) {

                            outText.setText("");//如果结果有值，输入数字时则清空
                            inputText.setText("");

                        }

                        midinput = inputText.getText().toString();
                        midinput = midinput + '3';
                        inputText.setText(midinput);
                        numberIn++;
                        break;
                    case R.id.button4:
                        Log.i("OnClick", "4");//DEBUG:测试输入
                        outText = (EditText) findViewById(R.id.OutputTextView2);
                        inputText = (TextView) findViewById(R.id.InputTextView);
                        if (!outText.getText().toString().equals("") && outText.getText() != null) {

                            outText.setText("");//如果结果有值，输入数字时则清空
                            inputText.setText("");

                        }

                        midinput = inputText.getText().toString();
                        midinput = midinput + '4';
                        inputText.setText(midinput);
                        numberIn++;
                        break;
                    case R.id.button5:
                        Log.i("OnClick", "5");//DEBUG:测试输入
                        outText = (EditText) findViewById(R.id.OutputTextView2);
                        inputText = (TextView) findViewById(R.id.InputTextView);
                        if (!outText.getText().toString().equals("") && outText.getText() != null) {

                            outText.setText("");//如果结果有值，输入数字时则清空
                            inputText.setText("");

                        }

                        midinput = inputText.getText().toString();
                        midinput = midinput + '5';
                        inputText.setText(midinput);
                        numberIn++;
                        break;
                    case R.id.button6:
                        Log.i("OnClick", "6");//DEBUG:测试输入
                        outText = (EditText) findViewById(R.id.OutputTextView2);
                        inputText = (TextView) findViewById(R.id.InputTextView);
                        if (!outText.getText().toString().equals("") && outText.getText() != null) {

                            outText.setText("");//如果结果有值，输入数字时则清空
                            inputText.setText("");

                        }

                        midinput = inputText.getText().toString();
                        midinput = midinput + '6';
                        inputText.setText(midinput);
                        numberIn++;
                        break;
                    case R.id.button7:
                        Log.i("OnClick", "7");//DEBUG:测试输入
                        outText = (EditText) findViewById(R.id.OutputTextView2);
                        inputText = (TextView) findViewById(R.id.InputTextView);
                        if (!outText.getText().toString().equals("") && outText.getText() != null) {

                            outText.setText("");//如果结果有值，输入数字时则清空
                            inputText.setText("");

                        }
                        midinput = inputText.getText().toString();
                        midinput = midinput + '7';
                        inputText.setText(midinput);
                        numberIn++;
                        break;
                    case R.id.button8:
                        Log.i("OnClick", "8");//DEBUG:测试输入
                        outText = (EditText) findViewById(R.id.OutputTextView2);
                        inputText = (TextView) findViewById(R.id.InputTextView);
                        if (!outText.getText().toString().equals("") && outText.getText() != null) {

                            outText.setText("");//如果结果有值，输入数字时则清空
                            inputText.setText("");

                        }

                        midinput = inputText.getText().toString();
                        midinput = midinput + '8';
                        inputText.setText(midinput);
                        numberIn++;
                        break;
                    case R.id.button9:
                        Log.i("OnClick", "9");//DEBUG:测试输入
                        outText = (EditText) findViewById(R.id.OutputTextView2);
                        inputText = (TextView) findViewById(R.id.InputTextView);
                        if (!outText.getText().toString().equals("") && outText.getText() != null) {

                            outText.setText("");//如果结果有值，输入数字时则清空
                            inputText.setText("");

                        }

                        midinput = inputText.getText().toString();
                        midinput = midinput + '9';
                        inputText.setText(midinput);
                        numberIn++;
                        break;
                    default:


                }//数字按钮


            }
        };

        /*****************获取布局元素********************/
        Button buttonNumber[] = new Button[10];
        //获取数字按钮
        buttonNumber[0] = (Button) findViewById(R.id.button0);
        buttonNumber[1] = (Button) findViewById(R.id.button1);
        buttonNumber[2] = (Button) findViewById(R.id.button2);
        buttonNumber[3] = (Button) findViewById(R.id.button3);
        buttonNumber[4] = (Button) findViewById(R.id.button4);
        buttonNumber[5] = (Button) findViewById(R.id.button5);
        buttonNumber[6] = (Button) findViewById(R.id.button6);
        buttonNumber[7] = (Button) findViewById(R.id.button7);
        buttonNumber[8] = (Button) findViewById(R.id.button8);
        buttonNumber[9] = (Button) findViewById(R.id.button9);
        for (int i = 0; i <= 9; i++) {
            buttonNumber[i].setOnClickListener(AllNumberClickListener);
        }//数字绑定通用按钮布局
        /*********************通用布局*********************************/
        //获取，加，减，乘，除，
        // 平方，根号,括号左，括号右，退格, 清空, 等于
        Button buttonAdd = (Button) findViewById(R.id.buttonAdd);//加
        buttonAdd.setOnClickListener(normalOperationListener);//绑定按压动作
        Button buttonSub = (Button) findViewById(R.id.buttonMinus);//减
        buttonSub.setOnClickListener(normalOperationListener);//绑定按压动作
        Button buttonMut = (Button) findViewById(R.id.buttonMultiply);//乘
        buttonMut.setOnClickListener(normalOperationListener);//绑定按压动作
        Button buttonDiv = (Button) findViewById(R.id.buttonExcept);//除
        buttonDiv.setOnClickListener(normalOperationListener);//绑定按压动作
        Button buttonSquare = (Button) findViewById(R.id.buttonSquare);//平方
        buttonSquare.setOnClickListener(normalOperationListener);
        Button buttonRoot = (Button) findViewById(R.id.buttonRoot);//根号
        buttonRoot.setOnClickListener(normalOperationListener);
        Button buttonLbrackets = (Button) findViewById(R.id.buttonLbrackets);//左括号
        buttonLbrackets.setOnClickListener(normalOperationListener);
        Button buttonRbrackets = (Button) findViewById(R.id.buttonRbrackets);//右括号
        buttonRbrackets.setOnClickListener(normalOperationListener);
        Button buttonBack = (Button) findViewById(R.id.buttonBack);//退格
        buttonBack.setOnClickListener(normalOperationListener);
        Button buttonClear = (Button) findViewById(R.id.buttonClear);//清空
        buttonClear.setOnClickListener(normalOperationListener);
        Button buttonEqual = (Button) findViewById(R.id.buttonEqual);//等于
        buttonEqual.setOnClickListener(normalOperationListener);
        Button buttonPercentage = (Button) findViewById(R.id.buttonPercentage);//百分比
        buttonPercentage.setOnClickListener(normalOperationListener);
        Button buttonPoint = (Button) findViewById(R.id.buttonPoint);//点
        buttonPoint.setOnClickListener(normalOperationListener);

        //TextView inputTextV = (TextView) findViewById(R.id.InputTextView);//上方的输入口
        //EditText outText = (EditText) findViewById(R.id.OutputTextView2);//下方的显示等于后的数字用的

        //inputTextV.setMovementMethod(new ScrollingMovementMethod());
        //otherOut= (String) inputTextV.getText().toString();

        //outputTextV.setMovementMethod(new ScrollingMovementMethod());
        /********************上方为获取页面元素*****************************/


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main_menu,menu); //通过getMenuInflater()方法得到MenuInflater对象，再调用它的inflate()方法就可以给当前活动创建菜单了，第一个参数：用于指定我们通过哪一个资源文件来创建菜单；第二个参数：用于指定我们的菜单项将添加到哪一个Menu对象当中。
        return true; // true：允许创建的菜单显示出来，false：创建的菜单将无法显示。
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {


            if(item.getItemId()==R.id.id_help_item) {
                //Toast.makeText(MainActivity.this,"作者：耳机壳",Toast.LENGTH_LONG);
                AlertDialog.Builder dialog=new AlertDialog.Builder(MainActivity.this);
                dialog.setTitle("帮助");
                LayoutInflater myLayout=getLayoutInflater();//获得layout
                final View view1=myLayout.inflate(R.layout.help_layout,null);//通过dialog获得视图
                dialog.setView(view1);
                dialog.setPositiveButton("知道了", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                });

                dialog.show();


            }
            else{
                Toast.makeText(MainActivity.this,"未绑定的菜单项",Toast.LENGTH_LONG);
            }


                return true;
        }





}
