package com.juhe.pockettools.adaptive;

import android.content.Context;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

/**
 * 自适应适配
 * @author daiye
 *
 */
public class UIAdapter {

	private static int width = 0;
	private static int height = 0;
	private LayoutAdapter layoutAdapter;
	private static UIAdapter adapterManager;

	public synchronized static UIAdapter getInstance(Context context) {
		if (width <= 0 || height <= 0) {
			new Exception("请设置基准大小");
			return null;
		}
		if (adapterManager == null)
			adapterManager = new UIAdapter(context);
		return adapterManager;
	}

	public UIAdapter(Context context) {
		layoutAdapter = new LayoutAdapter(context, width, height);
	}

	/**
	 * 获取字体
	 * 
	 * @param fontSize
	 * @return
	 */
	public int CalcFontSize(int fontSize) {
		return layoutAdapter.setTextSize(fontSize);
	}

	public void setTextSize(View v, int size) {
		if (v instanceof TextView)
			((TextView) v).setTextSize(CalcFontSize(size));
		if (v instanceof Button)
			((Button) v).setTextSize(CalcFontSize(size));
	}

	/**
	 * 添加一个元素
	 * 
	 * @param information
	 */
	public void add(LayoutInformation information) {
		// informations.add(information);
		layoutAdapter.addViewLayout(information);
	}

	/**
	 * 设置外间距
	 * 
	 * @param v
	 * @param w
	 *            宽
	 * @param h
	 *            高
	 * @param left
	 * @param top
	 * @param right
	 * @param bottom
	 */
	public void setMargin(View v, float w, float h, float left, float top,
			float right, float bottom) {
		layoutAdapter.addViewLayout(new LayoutInformation(v, w, h, left, top,
				right, bottom));
	}

	/**
	 * 设置外间距
	 * 
	 * @param v
	 * @param w
	 * @param h
	 * @param left
	 * @param top
	 * @param right
	 * @param bottom
	 * @param weight
	 *            比重(设置比重需要设置w为0)
	 */
	public void setMargin(View v, float w, float h, float left, float top,
			float right, float bottom, int weight) {
		layoutAdapter.addViewLayout(new LayoutInformation(v, w, h, left, top,
				right, bottom, (int) weight));
	}

	/**
	 * 设置内间距
	 * 
	 * @param view
	 * @param left
	 * @param top
	 * @param right
	 * @param bottom
	 */
	public void setPadding(View view, int left, int top, int right, int bottom) {
		view.setPadding(CalcWidth(left), CalcHeight(top), CalcWidth(right),
				CalcHeight(bottom));
	}

	/**
	 * 基准转到实际
	 * 
	 * @param width
	 * @return
	 */
	public int CalcWidth(int width) {
		return layoutAdapter.CalcWidth(width);
	}

	/**
	 * 基准转到实际
	 * 
	 * @param height
	 * @return
	 */
	public int CalcHeight(int height) {
		return layoutAdapter.CalcHeight(height);
	}

	/**
	 * 实际转到基准
	 * 
	 * @param width
	 * @return
	 */
	public int CalcReverseWidth(int width) {
		return layoutAdapter.CalcReverseWidth(width);
	}

	/**
	 * 实际转到基准
	 * 
	 * @param height
	 * @return
	 */
	public int CalcReverseHeight(int height) {
		return layoutAdapter.CalcReverseHeight(height);
	}

	/**
	 * 基准转到实际
	 * 
	 * @param width
	 * @return
	 */
	public float CalcWidth(float width) {
		return layoutAdapter.CalcWidth(width);
	}

	/**
	 * 基准转换到实际
	 * 
	 * @param height
	 * @return
	 */
	public float CalcHeight(float height) {
		return layoutAdapter.CalcHeight(height);
	}

	/**
	 * 实际转到基准
	 * 
	 * @param width
	 * @return
	 */
	public float CalcReverseWidth(float width) {
		return layoutAdapter.CalcReverseWidth(width);
	}

	/**
	 * 实际转到基准
	 * 
	 * @param height
	 * @return
	 */
	public float CalcReverseHeight(float height) {
		return layoutAdapter.CalcReverseHeight(height);
	}

	/**
	 * 根据高度 转换为对应宽度
	 * 
	 * @param h
	 *            使用的高度
	 * @param oldW
	 *            旧的宽度
	 * @param oldH
	 *            旧的高度
	 * @return
	 */
	public float CalcWidth(float h, float oldW, float oldH) {
		float realH = CalcHeight(h);
		float realW = oldW * realH / oldH;
		return CalcReverseWidth(realW);
	}

	/**
	 * 根据宽度 转换为对应高度
	 * 
	 * @param w
	 *            使用的宽度
	 * @param oldW
	 *            旧的宽度
	 * @param oldH
	 *            旧的高度
	 * @return
	 */
	public float CalcHeight(float w, float oldW, float oldH) {
		float realW = CalcWidth(w);
		float realH = oldH * realW / oldW;
		return CalcReverseHeight(realH);
	}

	/**
	 * 设置基准大小
	 * 
	 * @param w
	 * @param h
	 */
	public static void setSize(int w, int h) {
		width = w;
		height = h;
	}

	public int getWidth() {
		return width;
	}

	public void setWidth(int width) {
		UIAdapter.width = width;
	}

	public int getHeight() {
		return height;
	}

	public void setHeight(int height) {
		UIAdapter.height = height;
	}
}
