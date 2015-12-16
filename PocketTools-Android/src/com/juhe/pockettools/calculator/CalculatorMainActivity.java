package com.juhe.pockettools.calculator;

import android.annotation.SuppressLint;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;
import com.juhe.pockettools.R;
import com.juhe.pockettools.commonview.AutoResizeTextView;
import com.juhe.pockettools.home.FullscreenActivity;
import com.juhe.pockettools.utils.Config;
//import com.fotoable.helpr.wallpaper.w;
import java.text.DecimalFormat;
import java.util.StringTokenizer;

public class CalculatorMainActivity extends FullscreenActivity {
	private Button btn_left;
	private Button btn_right;
	private Button btn_dot;
	private Button btn_c;
	private FrameLayout btn_cancel;
	public String str_old; // 保存原来的算式样子，为了输出时好看，因计算时，算式样子被改变
	public String str_new; // 变换样子后的式子
	public boolean vbegin = true; // 输入控制，true为重新输入，false为接着输入
	public boolean drg_flag = true; // 控制DRG按键，true为角度，false为弧度
	public double pi = 4 * Math.atan(1); // 3.14
	public boolean tip_lock = true; // true表示正确，可以继续输入；false表示有误，输入被锁定
	public boolean equals_flag = true; // 判断是否是按=之后的输入，true表示输入在=之前，false反之
	private Button[] btn_num = new Button[10];
	private AutoResizeTextView input;
	private TextView mem;
	private Button btn_divide;
	private Button btn_mul;
	private Button btn_sub;
	private Button btn_add;
	private Button btn_equal;
	private Button btn_sin;
	private Button btn_cos;
	private Button btn_tan;
	private Button btn_log;
	private Button btn_ln;
	private Button btn_sqrt;
	private Button btn_square;
	private Button btn_factorial;
	private Button btn_bksp;

	/*
	 * 反馈Tip信息，加强人机交互，与TipChecker()配合使用
	 */
	private void TipShow(int bracket, int tipcode1, int tipcode2,
			String tipcommand1, String tipcommand2) {
		String tipmessage = "";
		if (tipcode1 != 0)
			tip_lock = false;
		switch (tipcode1) {
		/*
		 * case -1: tipmessage = tipcommand2 + "  不能作为第一个算符\n"; break; case 1:
		 * tipmessage = tipcommand1 + "  后应输入：数字/(/./-/函数 \n"; break; case 2:
		 * tipmessage = tipcommand1 + "  后应输入：)/算符 \n"; break; case 3:
		 * tipmessage = tipcommand1 + "  后应输入：)/数字/算符 \n"; break; case 4:
		 * tipmessage = tipcommand1 + "  后应输入：)/./数字 /算符 \n"; break; case 5:
		 * tipmessage = tipcommand1 + "  后应输入：(/./数字/函数 \n"; break; case 6:
		 * tipmessage = tipcommand1 + "  后应输入：(/./数字 \n"; break; case 7:
		 * tipmessage = tipcommand1 + "  后应输入：(/./数字 \n"; break; case 8:
		 * tipmessage = "小数点重复\n"; break;
		 */
		case 9:
			tipmessage = "不能计算，缺少 " + bracket + " 个 )";
			break;
		/*
		 * case 10: tipmessage = "不需要  )"; break;
		 */
		}
		switch (tipcode2) {
		/*
		 * case 1: tipmessage = tipmessage + "[MC 用法: 清除记忆 MEM]"; break; case 2:
		 * tipmessage = tipmessage + "[C 用法: 归零]"; break; case 3: tipmessage =
		 * tipmessage + "[DRG 用法: 选择 DEG 或 RAD]"; break; case 4: tipmessage =
		 * tipmessage + "[Bksp 用法: 退格]"; break;
		 */
		case 5:
			tipmessage = tipmessage + "sin 函数用法示例：\n"
					+ "DEG：sin30 = 0.5      RAD：sin1 = 0.84\n"
					+ "注：与其他函数一起使用时要加括号，如：\n" + "sin(cos45)，而不是sincos45";
			break;
		case 6:
			tipmessage = tipmessage + "cos 函数用法示例：\n"
					+ "DEG：cos60 = 0.5      RAD：cos1 = 0.54\n"
					+ "注：与其他函数一起使用时要加括号，如：\n" + "cos(sin45)，而不是cossin45";
			break;
		case 7:
			tipmessage = tipmessage + "tan 函数用法示例：\n"
					+ "DEG：tan45 = 1      RAD：tan1 = 1.55\n"
					+ "注：与其他函数一起使用时要加括号，如：\n" + "tan(cos45)，而不是tancos45";
			break;
		case 8:
			tipmessage = tipmessage + "log 函数用法示例：\n"
					+ "log10 = log(5+5) = 1\n" + "注：与其他函数一起使用时要加括号，如：\n"
					+ "log(tan45)，而不是logtan45";
			break;
		case 9:
			tipmessage = tipmessage + "ln 函数用法示例：\n"
					+ "ln10 = le(5+5) = 2.3   lne = 1\n"
					+ "注：与其他函数一起使用时要加括号，如：\n" + "ln(tan45)，而不是lntan45";
			break;
		case 10:
			tipmessage = tipmessage + "n! 函数用法示例：\n"
					+ "n!3 = n!(1+2) = 3×2×1 = 6\n" + "注：与其他函数一起使用时要加括号，如：\n"
					+ "n!(log1000)，而不是n!log1000";
			break;
		case 11:
			tipmessage = tipmessage + "√ 用法示例：开任意次根号\n"
					+ "如：27开3次根为  27√3 = 3\n" + "注：与其他函数一起使用时要加括号，如：\n"
					+ "(函数)√(函数) ， (n!3)√(log100) = 2.45";
			break;
		case 12:
			tipmessage = tipmessage + "^ 用法示例：开任意次平方\n" + "如：2的3次方为  2^3 = 8\n"
					+ "注：与其他函数一起使用时要加括号，如：\n"
					+ "(函数)√(函数) ， (n!3)^(log100) = 36";
			break;
		}

		// tip.setText(tipmessage);
	}

	/*
	 * 检测函数，对str进行前后语法检测 为Tip的提示方式提供依据，与TipShow()配合使用 编号 字符 其后可以跟随的合法字符 1 （
	 * 数字|（|-|.|函数 2 ） 算符|）|√ ^ 3 . 数字|算符|）|√ ^ 4 数字 .|数字|算符|）|√ ^ 5 算符
	 * 数字|（|.|函数 6 √ ^ （ |. | 数字 7 函数 数字|（|.
	 * 
	 * 小数点前后均可省略，表示0 数字第一位可以为0
	 */
	private void TipChecker(String tipcommand1, String tipcommand2) {
		int Tipcode1 = 0, Tipcode2 = 0;// Tipcode1表示错误类型，Tipcode2表示名词解释类型
		int tiptype1 = 0, tiptype2 = 0;// 表示命令类型
		int bracket = 0; // 括号数
		if (tipcommand1.compareTo("#") == 0
				&& (tipcommand2.compareTo("÷") == 0
						|| tipcommand2.compareTo("×") == 0
						|| tipcommand2.compareTo("+") == 0
						|| tipcommand2.compareTo(")") == 0
						|| tipcommand2.compareTo("√") == 0 || tipcommand2
						.compareTo("^") == 0)) {
			Tipcode1 = -1;// 不能作为第一位
		}
		if (tipcommand1.compareTo("#") != 0) {
			if (tipcommand1.compareTo("(") == 0) {
				tiptype1 = 1;
			} else if (tipcommand1.compareTo(")") == 0) {
				tiptype1 = 2;
			} else if (tipcommand1.compareTo(".") == 0) {
				tiptype1 = 3;
			} else if ("0123456789".indexOf(tipcommand1) != -1) {
				tiptype1 = 4;
			} else if ("+-×÷".indexOf(tipcommand1) != -1) {
				tiptype1 = 5;
			} else if ("√^".indexOf(tipcommand1) != -1) {
				tiptype1 = 6;
			} else if ("sincostanloglnn!".indexOf(tipcommand1) != -1) {
				tiptype1 = 7;
			}

			if (tipcommand2.compareTo("(") == 0) {
				tiptype2 = 1;
			} else if (tipcommand2.compareTo(")") == 0) {
				tiptype2 = 2;
			} else if (tipcommand2.compareTo(".") == 0) {
				tiptype2 = 3;
			} else if ("0123456789".indexOf(tipcommand2) != -1) {
				tiptype2 = 4;
			} else if ("+-×÷".indexOf(tipcommand2) != -1) {
				tiptype2 = 5;
			} else if ("√^".indexOf(tipcommand2) != -1) {
				tiptype2 = 6;
			} else if ("sincostanloglnn!".indexOf(tipcommand2) != -1) {
				tiptype2 = 7;
			}

			switch (tiptype1) {
			case 1:
				if (tiptype2 == 2
						|| (tiptype2 == 5 && tipcommand2.compareTo("-") != 0)
						|| tiptype2 == 6)
					Tipcode1 = 1;
				break;
			case 2:
				if (tiptype2 == 1 || tiptype2 == 3 || tiptype2 == 4
						|| tiptype2 == 7)
					Tipcode1 = 2;
				break;
			case 3:
				if (tiptype2 == 1 || tiptype2 == 7)
					Tipcode1 = 3;
				if (tiptype2 == 3)
					Tipcode1 = 8;
				break;
			case 4:
				if (tiptype2 == 1 || tiptype2 == 7)
					Tipcode1 = 4;
				break;
			case 5:
				if (tiptype2 == 2 || tiptype2 == 5 || tiptype2 == 6)
					Tipcode1 = 5;
				break;
			case 6:
				if (tiptype2 == 2 || tiptype2 == 5 || tiptype2 == 6
						|| tiptype2 == 7)
					Tipcode1 = 6;
				break;
			case 7:
				if (tiptype2 == 2 || tiptype2 == 5 || tiptype2 == 6
						|| tiptype2 == 7)
					Tipcode1 = 7;
				break;
			}
		}

		if (Tipcode1 == 0 && tipcommand2.compareTo(".") == 0) {// 检测小数点的重复性
			int tip_point = 0;
			for (int i = 0; i < tip_i; i++) {
				if (Tipcommand[i].compareTo(".") == 0) {
					tip_point++;
				}
				if (Tipcommand[i].compareTo("sin") == 0
						|| Tipcommand[i].compareTo("cos") == 0
						|| Tipcommand[i].compareTo("tan") == 0
						|| Tipcommand[i].compareTo("log") == 0
						|| Tipcommand[i].compareTo("ln") == 0
						|| Tipcommand[i].compareTo("n!") == 0
						|| Tipcommand[i].compareTo("√") == 0
						|| Tipcommand[i].compareTo("^") == 0
						|| Tipcommand[i].compareTo("÷") == 0
						|| Tipcommand[i].compareTo("×") == 0
						|| Tipcommand[i].compareTo("-") == 0
						|| Tipcommand[i].compareTo("+") == 0
						|| Tipcommand[i].compareTo("(") == 0
						|| Tipcommand[i].compareTo(")") == 0) {
					tip_point = 0;
				}
			}
			tip_point++;
			if (tip_point > 1) {
				Tipcode1 = 8;
			}
		}
		if (Tipcode1 == 0 && tipcommand2.compareTo(")") == 0) {
			int tip_right_bracket = 0;// 右括号匹配数，主要用于右括号
			for (int i = 0; i < tip_i; i++) {
				if (Tipcommand[i].compareTo("(") == 0) {
					tip_right_bracket++;
				}
				if (Tipcommand[i].compareTo(")") == 0) {
					tip_right_bracket--;
				}
			}
			if (tip_right_bracket == 0) {
				Tipcode1 = 10;
			}
		}
		if (Tipcode1 == 0 && tipcommand2.compareTo("=") == 0) {
			int tip_bracket = 0; // 括号匹配数，主要用于左括号
			for (int i = 0; i < tip_i; i++) {
				if (Tipcommand[i].compareTo("(") == 0) {
					tip_bracket++;
				}
				if (Tipcommand[i].compareTo(")") == 0) {
					tip_bracket--;
				}
			}
			if (tip_bracket > 0) {
				Tipcode1 = 9;
				bracket = tip_bracket;
			} else if (tip_bracket == 0) {
				if ("√^sincostanloglnn!".indexOf(tipcommand1) != -1) {
					Tipcode1 = 6;
				}
				if ("+-×÷".indexOf(tipcommand1) != -1) {
					Tipcode1 = 5;
				}
			}
		}

		if (tipcommand2.compareTo("MC") == 0)
			Tipcode2 = 1;
		if (tipcommand2.compareTo("C") == 0)
			Tipcode2 = 2;
		if (tipcommand2.compareTo("DRG") == 0)
			Tipcode2 = 3;
		if (tipcommand2.compareTo("Del") == 0)
			Tipcode2 = 4;
		if (tipcommand2.compareTo("sin") == 0)
			Tipcode2 = 5;
		if (tipcommand2.compareTo("cos") == 0)
			Tipcode2 = 6;
		if (tipcommand2.compareTo("tan") == 0)
			Tipcode2 = 7;
		if (tipcommand2.compareTo("log") == 0)
			Tipcode2 = 8;
		if (tipcommand2.compareTo("ln") == 0)
			Tipcode2 = 9;
		if (tipcommand2.compareTo("n!") == 0)
			Tipcode2 = 10;
		if (tipcommand2.compareTo("√") == 0)
			Tipcode2 = 11;
		if (tipcommand2.compareTo("^") == 0)
			Tipcode2 = 12;

		TipShow(bracket, Tipcode1, Tipcode2, tipcommand1, tipcommand2);
	}

	/*
	 * 判断一个str是否是合法的，返回值为true、false
	 * 只包含0123456789.()sincostanlnlogn!+-×÷√^的是合法的str，返回true
	 * 包含了除0123456789.()sincostanlnlogn!+-×÷√^以外的字符的str为非法的，返回false
	 */
	private boolean right(String str) {
		int i = 0;
		for (i = 0; i < str.length(); i++) {
			if (str.charAt(i) != '0' && str.charAt(i) != '1'
					&& str.charAt(i) != '2' && str.charAt(i) != '3'
					&& str.charAt(i) != '4' && str.charAt(i) != '5'
					&& str.charAt(i) != '6' && str.charAt(i) != '7'
					&& str.charAt(i) != '8' && str.charAt(i) != '9'
					&& str.charAt(i) != '.' && str.charAt(i) != '-'
					&& str.charAt(i) != '+' && str.charAt(i) != '×'
					&& str.charAt(i) != '÷' && str.charAt(i) != '√'
					&& str.charAt(i) != '^' && str.charAt(i) != 's'
					&& str.charAt(i) != 'i' && str.charAt(i) != 'n'
					&& str.charAt(i) != 'c' && str.charAt(i) != 'o'
					&& str.charAt(i) != 't' && str.charAt(i) != 'a'
					&& str.charAt(i) != 'l' && str.charAt(i) != 'g'
					&& str.charAt(i) != '(' && str.charAt(i) != ')'
					&& str.charAt(i) != '!')
				break;
		}
		if (i == str.length()) {
			return true;
		} else {
			return false;
		}
	}

	/*
	 * 检测函数，返回值为3、2、1 表示应当一次删除几个？ Three+Two+One = TTO 为Del按钮的删除方式提供依据
	 * 返回3，表示str尾部为sin、cos、tan、log中的一个，应当一次删除3个 返回2，表示str尾部为ln、n!中的一个，应当一次删除2个
	 * 返回1，表示为除返回3、2外的所有情况，只需删除一个（包含非法字符时要另外考虑：应清屏）
	 */
	private int TTO(String str) {
		if ((str.charAt(str.length() - 1) == 'n'
				&& str.charAt(str.length() - 2) == 'i' && str.charAt(str
				.length() - 3) == 's')
				|| (str.charAt(str.length() - 1) == 's'
						&& str.charAt(str.length() - 2) == 'o' && str
						.charAt(str.length() - 3) == 'c')
				|| (str.charAt(str.length() - 1) == 'n'
						&& str.charAt(str.length() - 2) == 'a' && str
						.charAt(str.length() - 3) == 't')
				|| (str.charAt(str.length() - 1) == 'g'
						&& str.charAt(str.length() - 2) == 'o' && str
						.charAt(str.length() - 3) == 'l')) {
			return 3;
		} else if ((str.charAt(str.length() - 1) == 'n' && str.charAt(str
				.length() - 2) == 'l')
				|| (str.charAt(str.length() - 1) == '!' && str.charAt(str
						.length() - 2) == 'n')) {
			return 2;
		} else {
			return 1;
		}
	}

	@SuppressLint("NewApi")
	public void onCreate(Bundle paramBundle) {
		super.onCreate(paramBundle);
		setContentView(R.layout.activity_calcautor_mian);
		((ImageView) findViewById(R.id.img_bg)).setBackgroundColor(getResources().getColor(Config.getColor()));
		input = ((AutoResizeTextView) findViewById(R.id.input));
		mem = ((TextView) findViewById(R.id.mem));
		btn_num[0] = ((Button) findViewById(R.id.zero));
		btn_num[1] = ((Button) findViewById(R.id.one));
		btn_num[2] = ((Button) findViewById(R.id.two));
		btn_num[3] = ((Button) findViewById(R.id.three));
		btn_num[4] = ((Button) findViewById(R.id.four));
		btn_num[5] = ((Button) findViewById(R.id.five));
		btn_num[6] = ((Button) findViewById(R.id.six));
		btn_num[7] = ((Button) findViewById(R.id.seven));
		btn_num[8] = ((Button) findViewById(R.id.eight));
		btn_num[9] = ((Button) findViewById(R.id.nine));
		btn_divide = ((Button) findViewById(R.id.divide));
		btn_mul = ((Button) findViewById(R.id.mul));
		btn_sub = ((Button) findViewById(R.id.sub));
		btn_add = ((Button) findViewById(R.id.add));
		btn_equal = ((Button) findViewById(R.id.equal));
		btn_sin = ((Button) findViewById(R.id.sin));
		btn_cos = ((Button) findViewById(R.id.cos));
		btn_tan = ((Button) findViewById(R.id.tan));
		btn_log = ((Button) findViewById(R.id.log));
		btn_ln = ((Button) findViewById(R.id.ln));
		btn_sqrt = ((Button) findViewById(R.id.sqrt));
		btn_square = ((Button) findViewById(R.id.square));
		btn_factorial = ((Button) findViewById(R.id.factorial));
		btn_bksp = ((Button) findViewById(R.id.bksp));
		btn_left = ((Button) findViewById(R.id.left));
		btn_right = ((Button) findViewById(R.id.right));
		btn_dot = ((Button) findViewById(R.id.dot));
		btn_c = ((Button) findViewById(R.id.c));
		Typeface typeface = Typeface.createFromAsset(getAssets(),
				"fonts/HelveticaNeue-Thin.otf");
		for (int i = 0; i < 10; i++) {
			btn_num[i].setOnClickListener(actionPerformed);
			btn_num[i].setTypeface(typeface);
		}
		btn_divide.setOnClickListener(actionPerformed);
		btn_mul.setOnClickListener(actionPerformed);
		btn_sub.setOnClickListener(actionPerformed);
		btn_add.setOnClickListener(actionPerformed);
		btn_equal.setOnClickListener(actionPerformed);
		btn_sin.setOnClickListener(actionPerformed);
		btn_cos.setOnClickListener(actionPerformed);
		btn_tan.setOnClickListener(actionPerformed);
		btn_log.setOnClickListener(actionPerformed);
		btn_ln.setOnClickListener(actionPerformed);
		btn_sqrt.setOnClickListener(actionPerformed);
		btn_square.setOnClickListener(actionPerformed);
		btn_factorial.setOnClickListener(actionPerformed);
		btn_bksp.setOnClickListener(actionPerformed);
		btn_left.setOnClickListener(actionPerformed);
		btn_right.setOnClickListener(actionPerformed);
		btn_dot.setOnClickListener(actionPerformed);
		btn_c.setOnClickListener(actionPerformed);
		btn_divide.setTypeface(typeface);
		btn_mul.setTypeface(typeface);
		btn_sub.setTypeface(typeface);
		btn_add.setTypeface(typeface);
		btn_equal.setTypeface(typeface);
		btn_sin.setTypeface(typeface);
		btn_cos.setTypeface(typeface);
		btn_tan.setTypeface(typeface);
		btn_log.setTypeface(typeface);
		btn_ln.setTypeface(typeface);
		btn_sqrt.setTypeface(typeface);
		btn_square.setTypeface(typeface);
		btn_factorial.setTypeface(typeface);
		btn_bksp.setTypeface(typeface);
		btn_left.setTypeface(typeface);
		btn_right.setTypeface(typeface);
		btn_dot.setTypeface(typeface);
		btn_c.setTypeface(typeface);
		input.setTypeface(typeface);
		mem.setTypeface(typeface);
		btn_cancel = ((FrameLayout) findViewById(R.id.btn_cancel));
		btn_cancel.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				finish();
			}
		});
	}

	/*
	 * 整个计算核心，只要将表达式的整个字符串传入calc().process()就可以实行计算了 算法包括以下几部分： 1、计算部分
	 * process(String str) 当然，这是建立在查错无错误的情况下 2、数据格式化 FP(double n) 使数据有相当的精确度
	 * 3、阶乘算法 N(double n) 计算n!，将结果返回 4、错误提示 showError(int code ,String str)
	 * 将错误返回
	 */
	public class calc {
		public calc() {

		}

		final int MAXLEN = 500;

		/*
		 * 计算表达式 从左向右扫描，数字入number栈，运算符入operator栈 +-基本优先级为1，×÷基本优先级为2，log ln sin
		 * cos tan n!基本优先级为3，√^基本优先级为4 括号内层运算符比外层同级运算符优先级高4
		 * 当前运算符优先级高于栈顶压栈，低于栈顶弹出一个运算符与两个数进行运算 重复直到当前运算符大于栈顶 扫描完后对剩下的运算符与数字依次计算
		 */
		public void process(String str) {
			int weightPlus = 0, topOp = 0, topNum = 0, flag = 1, weightTemp = 0;
			// weightPlus为同一（）下的基本优先级，weightTemp临时记录优先级的变化
			// topOp为weight[]，operator[]的计数器；topNum为number[]的计数器
			// flag为正负数的计数器，1为正数，-1为负数
			int weight[]; // 保存operator栈中运算符的优先级，以topOp计数
			double number[]; // 保存数字，以topNum计数
			char ch, ch_gai, operator[];// operator[]保存运算符，以topOp计数
			String num;// 记录数字，str以+-×÷()sctgl!√^分段，+-×÷()sctgl!√^字符之间的字符串即为数字
			weight = new int[MAXLEN];
			number = new double[MAXLEN];
			operator = new char[MAXLEN];
			String expression = str;
			StringTokenizer expToken = new StringTokenizer(expression,
					"+-×÷()sctgl!√^");
			int i = 0;
			while (i < expression.length()) {
				ch = expression.charAt(i);
				if (i == 0) {
					if (ch == '-')
						flag = -1;
				} else if (expression.charAt(i - 1) == '(' && ch == '-')
					flag = -1;
				if (ch <= '9' && ch >= '0' || ch == '.' || ch == 'E') {
					num = expToken.nextToken();
					ch_gai = ch;
					while (i < expression.length()
							&& (ch_gai <= '9' && ch_gai >= '0' || ch_gai == '.' || ch_gai == 'E'))
						ch_gai = expression.charAt(i++);
					if (i >= expression.length())
						i -= 1;
					else
						i -= 2;
					if (num.compareTo(".") == 0)
						number[topNum++] = 0;
					else {
						number[topNum++] = Double.parseDouble(num) * flag;
						flag = 1;
					}
				}
				if (ch == '(')
					weightPlus += 4;
				if (ch == ')')
					weightPlus -= 4;
				if (ch == '-' && flag == 1 || ch == '+' || ch == '×'
						|| ch == '÷' || ch == 's' || ch == 'c' || ch == 't'
						|| ch == 'g' || ch == 'l' || ch == '!' || ch == '√'
						|| ch == '^') {
					switch (ch) {
					case '+':
					case '-':
						weightTemp = 1 + weightPlus;
						break;
					case '×':
					case '÷':
						weightTemp = 2 + weightPlus;
						break;
					case 's':
					case 'c':
					case 't':
					case 'g':
					case 'l':
					case '!':
						weightTemp = 3 + weightPlus;
						break;
					// case '^':
					// case '√':
					default:
						weightTemp = 4 + weightPlus;
						break;
					}
					if (topOp == 0 || weight[topOp - 1] < weightTemp) {
						weight[topOp] = weightTemp;
						operator[topOp] = ch;
						topOp++;
					} else {
						while (topOp > 0 && weight[topOp - 1] >= weightTemp) {
							switch (operator[topOp - 1]) {
							case '+':
								number[topNum - 2] += number[topNum - 1];
								break;
							case '-':
								number[topNum - 2] -= number[topNum - 1];
								break;
							case '×':
								number[topNum - 2] *= number[topNum - 1];
								break;
							case '÷':
								if (number[topNum - 1] == 0) {
									showError(1, str_old);
									return;
								}
								number[topNum - 2] /= number[topNum - 1];
								break;
							case '√':
								if (number[topNum - 1] == 0
										|| (number[topNum - 2] < 0 && number[topNum - 1] % 2 == 0)) {
									showError(2, str_old);
									return;
								}
								number[topNum - 2] = Math.pow(
										number[topNum - 2],
										1 / number[topNum - 1]);
								break;
							case '^':
								number[topNum - 2] = Math.pow(
										number[topNum - 2], number[topNum - 1]);
								break;
							case 's':
								if (drg_flag == true) {
									number[topNum - 1] = Math
											.sin((number[topNum - 1] / 180)
													* pi);
								} else {
									number[topNum - 1] = Math
											.sin(number[topNum - 1]);
								}
								topNum++;
								break;
							case 'c':
								if (drg_flag == true) {
									number[topNum - 1] = Math
											.cos((number[topNum - 1] / 180)
													* pi);
								} else {
									number[topNum - 1] = Math
											.cos(number[topNum - 1]);
								}
								topNum++;
								break;
							case 't':
								if (drg_flag == true) {
									if ((Math.abs(number[topNum - 1]) / 90) % 2 == 1) {
										showError(2, str_old);
										return;
									}
									number[topNum - 1] = Math
											.tan((number[topNum - 1] / 180)
													* pi);
								} else {
									if ((Math.abs(number[topNum - 1]) / (pi / 2)) % 2 == 1) {
										showError(2, str_old);
										return;
									}
									number[topNum - 1] = Math
											.tan(number[topNum - 1]);
								}
								topNum++;
								break;
							case 'g':
								if (number[topNum - 1] <= 0) {
									showError(2, str_old);
									return;
								}
								number[topNum - 1] = Math
										.log10(number[topNum - 1]);
								topNum++;
								break;
							case 'l':
								if (number[topNum - 1] <= 0) {
									showError(2, str_old);
									return;
								}
								number[topNum - 1] = Math
										.log(number[topNum - 1]);
								topNum++;
								break;
							case '!':
								if (number[topNum - 1] > 170) {
									showError(3, str_old);
									return;
								} else if (number[topNum - 1] < 0) {
									showError(2, str_old);
									return;
								}
								number[topNum - 1] = N(number[topNum - 1]);
								topNum++;
								break;
							}
							topNum--;
							topOp--;
						}
						weight[topOp] = weightTemp;
						operator[topOp] = ch;
						topOp++;
					}
				}
				i++;
			}
			while (topOp > 0) {
				switch (operator[topOp - 1]) {
				case '+':
					number[topNum - 2] += number[topNum - 1];
					break;
				case '-':
					number[topNum - 2] -= number[topNum - 1];
					break;
				case '×':
					number[topNum - 2] *= number[topNum - 1];
					break;
				case '÷':
					if (number[topNum - 1] == 0) {
						showError(1, str_old);
						return;
					}
					number[topNum - 2] /= number[topNum - 1];
					break;
				case '√':
					if (number[topNum - 1] == 0
							|| (number[topNum - 2] < 0 && number[topNum - 1] % 2 == 0)) {
						showError(2, str_old);
						return;
					}
					number[topNum - 2] = Math.pow(number[topNum - 2],
							1 / number[topNum - 1]);
					break;
				case '^':
					number[topNum - 2] = Math.pow(number[topNum - 2],
							number[topNum - 1]);
					break;
				case 's':
					if (drg_flag == true) {
						number[topNum - 1] = Math
								.sin((number[topNum - 1] / 180) * pi);
					} else {
						number[topNum - 1] = Math.sin(number[topNum - 1]);
					}
					topNum++;
					break;
				case 'c':
					if (drg_flag == true) {
						number[topNum - 1] = Math
								.cos((number[topNum - 1] / 180) * pi);
					} else {
						number[topNum - 1] = Math.cos(number[topNum - 1]);
					}
					topNum++;
					break;
				case 't':
					if (drg_flag == true) {
						if ((Math.abs(number[topNum - 1]) / 90) % 2 == 1) {
							showError(2, str_old);
							return;
						}
						number[topNum - 1] = Math
								.tan((number[topNum - 1] / 180) * pi);
					} else {
						if ((Math.abs(number[topNum - 1]) / (pi / 2)) % 2 == 1) {
							showError(2, str_old);
							return;
						}
						number[topNum - 1] = Math.tan(number[topNum - 1]);
					}
					topNum++;
					break;
				case 'g':
					if (number[topNum - 1] <= 0) {
						showError(2, str_old);
						return;
					}
					number[topNum - 1] = Math.log10(number[topNum - 1]);
					topNum++;
					break;
				case 'l':
					if (number[topNum - 1] <= 0) {
						showError(2, str_old);
						return;
					}
					number[topNum - 1] = Math.log(number[topNum - 1]);
					topNum++;
					break;
				case '!':
					if (number[topNum - 1] > 170) {
						showError(3, str_old);
						return;
					} else if (number[topNum - 1] < 0) {
						showError(2, str_old);
						return;
					}
					number[topNum - 1] = N(number[topNum - 1]);
					topNum++;
					break;
				}
				topNum--;
				topOp--;
			}

			if (number[0] > 7.3E306) {
				showError(3, str_old);
				// input.setText("\""+str_old+"\": 太大了，我不行了");
				return;
			}
			input.setText(String.valueOf(FP(number[0]))); // 输出最终结果
			// tip.setText("计算完毕，要继续请按归零键 C");
			mem.setText(str_old + "=" + String.valueOf(FP(number[0])));
		}

		/*
		 * FP = floating point 控制小数位数，达到精度 否则会出现
		 * 0.6-0.2=0.39999999999999997的情况，用FP即可解决，使得数为0.4 本格式精度为15位
		 */
		public double FP(double n) {
			// NumberFormat format=NumberFormat.getInstance(); //创建一个格式化类f
			// format.setMaximumFractionDigits(18); //设置小数位的格式
			DecimalFormat format = new DecimalFormat("0.#############");
			return Double.parseDouble(format.format(n));
		}

		/*
		 * 阶乘算法
		 */
		public double N(double n) {
			int i = 0;
			double sum = 1;
			for (i = 1; i <= n; i++) {
				sum = sum * i;
			}
			return sum;
		}

		/*
		 * 错误提示，按了"="之后，若计算式在process()过程中，出现错误，则进行提示
		 */
		public void showError(int code, String str) {
			String message = "";
			switch (code) {
			case 1:
				message = "零不能作除数";
				break;
			case 2:
				message = "函数格式错误";
				break;
			case 3:
				message = "值太大了，我不行了";
			}
			input.setText("\"" + str + "\"" + ": " + message);
			// tip.setText(message + "\n" + "计算完毕，要继续请按归零键 C");
		}
	}

	/*
	 * 向input输出字符 input.setText(str);为清屏后输出 input.append(str);为在屏幕原str后增添字符
	 */
	private void print(String str) {
		if (vbegin)
			input.setText(str);
		else
			input.append(str);

		vbegin = false;
	}

	/*
	 * 键盘命令扑捉
	 */
	String[] Tipcommand = new String[500]; // 命令缓存，用于检测输入合法性
	int tip_i = 0; // Tipcommand的指针
	private OnClickListener actionPerformed = new OnClickListener() {
		public void onClick(View v) {
			String command = ((Button) v).getText().toString(), // 按键上的命令获取
			str = input.getText().toString(); // 显示器上的字符串

			if (equals_flag == false
					&& "0123456789.()sincostanlnlogn!+-×÷√^".indexOf(command) != -1) {
				if (right(input.getText().toString())) {
					if ("+-×÷√^)".indexOf(command) != -1) {
						for (int i = 0; i < input.getText().toString().length(); i++) {
							Tipcommand[tip_i] = String.valueOf(input.getText()
									.toString().charAt(i));
							tip_i++;
						}
						vbegin = false;
					}
				} else {
					input.setText("0");
					vbegin = true;
					tip_i = 0;
					tip_lock = true;
				}

				equals_flag = true;
			}
			if (tip_i > 0)
				TipChecker(Tipcommand[tip_i - 1], command);
			else if (tip_i == 0) {
				TipChecker("#", command);
			}
			if ("0123456789.()sincostanlnlogn!+-×÷√^".indexOf(command) != -1
					&& tip_lock) {
				Tipcommand[tip_i] = command;
				tip_i++;
			}

			if ("0123456789.()sincostanlnlogn!+-×÷√^".indexOf(command) != -1
					&& tip_lock) { // 共25个按键
				print(command);
			} else if (command.compareTo("DRG") == 0 && tip_lock) {
				if (drg_flag == true) {
					drg_flag = false;
				} else {
					drg_flag = true;
				}
			} else if (command.compareTo("Del") == 0 && equals_flag) {
				if (TTO(str) == 3) {
					if (str.length() > 3)
						input.setText(str.substring(0, str.length() - 3));
					else if (str.length() == 3) {
						input.setText("0");
						vbegin = true;
						tip_i = 0;
					}
				} else if (TTO(str) == 2) {
					if (str.length() > 2)
						input.setText(str.substring(0, str.length() - 2));
					else if (str.length() == 2) {
						input.setText("0");
						vbegin = true;
						tip_i = 0;
					}
				} else if (TTO(str) == 1) {
					if (right(str)) {
						if (str.length() > 1)
							input.setText(str.substring(0, str.length() - 1));
						else if (str.length() == 1) {
							input.setText("0");
							vbegin = true;
							tip_i = 0;
						}
					} else {
						input.setText("0");
						vbegin = true;
						tip_i = 0;
					}
				}
				if (input.getText().toString().compareTo("-") == 0
						|| equals_flag == false) {
					input.setText("0");
					vbegin = true;
					tip_i = 0;
				}
				tip_lock = true;
				if (tip_i > 0)
					tip_i--;
			} else if (command.compareTo("Del") == 0 && equals_flag == false) {
				input.setText("0");
				vbegin = true;
				tip_i = 0;
				tip_lock = true;
			} else if (command.compareTo("C") == 0) {
				input.setText("");
				mem.setText("");
				vbegin = true;
				tip_i = 0;
				tip_lock = true;
				equals_flag = true;
			} else if (command.compareTo("MC") == 0) {
				mem.setText("0");
			} else if (command.compareTo("exit") == 0) {
				System.exit(0);
			} else if (command.compareTo("=") == 0 && tip_lock && right(str)
					&& equals_flag) {
				tip_i = 0;
				tip_lock = false;
				equals_flag = false;
				str_old = str;// 保存原来算式样子
				str = str.replaceAll("sin", "s");
				str = str.replaceAll("cos", "c");
				str = str.replaceAll("tan", "t");
				str = str.replaceAll("log", "g");
				str = str.replaceAll("ln", "l");
				str = str.replaceAll("n!", "!");
				vbegin = true;
				str_new = str.replaceAll("-", "-1×");
				new calc().process(str_new);
			}

			tip_lock = true;
		}
	};
}
