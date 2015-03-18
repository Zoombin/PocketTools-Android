package com.juhe.pockettools.commonview;

import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup.LayoutParams;
import android.widget.Button;
import android.widget.TextView;

import com.juhe.pockettools.R;

public class CustomStyleDialog extends Dialog {

	public CustomStyleDialog(Context context) {
		super(context);
	}

	public CustomStyleDialog(Context context, int theme) {
		super(context, theme);
	}

	public static class Builder {
		private Context context;
		private String b = "";
		private String c = "";
		private String str1;
		private String str2;
		private View f;
		private DialogInterface.OnClickListener clicklistener1;
		private DialogInterface.OnClickListener clicklistener2;
		private int i = -16777216;
		private int center = Gravity.CENTER;

		public Builder(Context context) {
			this.context = context;
		}

		public Builder a(int paramInt) {
			this.i = paramInt;
			return this;
		}

		public Builder a(int paramInt,
				DialogInterface.OnClickListener clicklistener) {
			str1 = ((String) context.getText(paramInt));
			this.clicklistener1 = clicklistener;
			return this;
		}

		public Builder a(View paramView) {
			this.f = paramView;
			return this;
		}

		public Builder a(String paramString) {
			this.c = paramString;
			return this;
		}

		public Builder a(String paramString,
				DialogInterface.OnClickListener clicklistener) {
			str1 = paramString;
			this.clicklistener1 = clicklistener;
			return this;
		}

//		public CustomStyleDialog show() {
//			LayoutInflater localLayoutInflater = (LayoutInflater) context
//					.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
//			CustomStyleDialog localCustomStyleDialog = new CustomStyleDialog(
//					context, 2131296283);
//			View localView = localLayoutInflater.inflate(2130903118, null);
//			localCustomStyleDialog.addContentView(localView,
//					new ViewGroup.LayoutParams(-2, -2));
//			if (this.b.equals("")) {
//				((TextView) localView.findViewById(2131362111))
//						.setVisibility(8);
//				if (str1 == null) {
//					break label257;
//				}
//				((Button) localView.findViewById(2131362160)).setText(str1);
//				if (clicklistener1 != null) {
//					((Button) localView.findViewById(2131362160))
//							.setOnClickListener(new c(this,
//									localCustomStyleDialog));
//				}
//				label128: if (this.e == null) {
//					break label271;
//				}
//				((Button) localView.findViewById(2131362161)).setText(this.e);
//				if (clicklistener2 != null) {
//					((Button) localView.findViewById(2131362161))
//							.setOnClickListener(new d(this,
//									localCustomStyleDialog));
//				}
//			}
//			for (;;) {
//				if (this.c != null) {
//					((TextView) localView.findViewById(2131362159))
//							.setText(this.c);
//					((TextView) localView.findViewById(2131362159))
//							.setGravity(center);
//				}
//				localCustomStyleDialog.setContentView(localView);
//				return localCustomStyleDialog;
//				((TextView) localView.findViewById(2131362111)).setText(this.b);
//				((TextView) localView.findViewById(2131362111))
//						.setVisibility(0);
//				break;
//				label257: localView.findViewById(2131362160).setVisibility(8);
//				break label128;
//				label271: localView.findViewById(2131362161).setVisibility(8);
//			}
		}

//		public Builder b(int paramInt) {
//			this.j = paramInt;
//			return this;
//		}
//
//		public Builder b(int paramInt,
//				DialogInterface.OnClickListener paramOnClickListener) {
//			this.e = ((String) context.getText(paramInt));
//			this.h = paramOnClickListener;
//			return this;
//		}
//
//		public Builder b(String paramString) {
//			this.b = paramString;
//			return this;
//		}
//
//		public Builder b(String paramString,
//				DialogInterface.OnClickListener paramOnClickListener) {
//			this.e = paramString;
//			this.h = paramOnClickListener;
//			return this;
//		}
//
//		public Builder c(int paramInt) {
//			this.c = ((String) this.a.getText(paramInt));
//			return this;
//		}
//
//		public Builder d(int paramInt) {
//			this.b = ((String) this.a.getText(paramInt));
//			return this;
//		}
//	}
}
