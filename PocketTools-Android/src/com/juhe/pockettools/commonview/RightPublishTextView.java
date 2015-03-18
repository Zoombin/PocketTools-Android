package com.juhe.pockettools.commonview;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.TextView;

public class RightPublishTextView extends TextView {
	public RightPublishTextView(Context context,
			AttributeSet attributeSet) {
		super(context, attributeSet);
	}

//	public static String a(String paramString) {
//		for (;;) {
//			int i;
//			try {
//				char[] arrayOfChar = paramString.toCharArray();
//				i = 0;
//				if (i >= arrayOfChar.length) {
//					return b(new String(arrayOfChar));
//				}
//				if (arrayOfChar[i] == '　') {
//					arrayOfChar[i] = ' ';
//				} else if ((arrayOfChar[i] > 65280) && (arrayOfChar[i] < 65375)) {
//					arrayOfChar[i] = ((char) (arrayOfChar[i] - 65248));
//				}
//			} catch (Exception localException) {
//				return paramString;
//			}
//			i++;
//		}
//	}

//	public static String b(String paramString) {
//		try {
//			String str1 = paramString.replaceAll("【", "[").replaceAll("】", "]")
//					.replaceAll("！", "!").replaceAll("：", ":");
//			String str2 = Pattern.compile("[『』]").matcher(str1).replaceAll("")
//					.trim();
//			return str2;
//		} catch (Exception localException) {
//		}
//		return paramString;
//	}

	public void setText(CharSequence text,
			TextView.BufferType buffertype) {
//		a(paramCharSequence.toString());
		super.setText(text, buffertype);
	}
}
