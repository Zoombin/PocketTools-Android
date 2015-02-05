package com.juhe.pockettools.adaptive;

import java.util.List;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.util.DisplayMetrics;
import android.view.View;
import android.view.ViewGroup.LayoutParams;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

/**
 * ������ͨ����
 * 
 * @author daiy
 * 
 */
public class LayoutAdapter {
	/**
	 * ��׼�ֱ��ʵĿ�
	 */
	public float STANDARD_SCREEN_WIDTH;

	/**
	 * ��׼�ֱ��ʵĸ�
	 */
	public float STANDARD_SCREEN_HEIGHT;

	/**
	 * ϵͳ��ǰ�ķֱ��ʵĿ�
	 */
	public float CURRENT_SCREEN_WIDTH;

	/**
	 * ϵͳ��ǰ�ķֱ��ʵĸ�
	 */
	public float CURRENT_SCREEN_HEIGHT;

	/**
	 * ��׼��Ļ�ܶ�
	 */
	public static final float STANDARD_DENSITY = 160;

	/**
	 * ��ǰ��Ļ�ܶ�
	 */
	private float CURRENT_DENSITY;

	/**
	 * ��Ļ�ܶȱ���
	 */
	private float DENSITY_RATIO;

	/**
	 * ��Ļ��ȱ���
	 */
	private float WIDTH_RATIO;

	/**
	 * ��Ļ�߶ȱ���
	 */
	private float HEIGHT_RATIO;

	/**
	 * �����׼�Ŀ��
	 */
	private float viewStandardWidth;

	/**
	 * �����׼�ĸ߶�
	 */
	private float viewStandardHeight;

	/**
	 * �����׼�ľ�����ߵľ���
	 */
	private float viewStandardMarginLeft;

	/**
	 * �����׼�ľ��붥���ľ���
	 */
	private float viewStandardMarginTop;

	/**
	 * �����׼�ľ����ҵľ���
	 */
	private float viewStandardMarginRight;

	/**
	 * �����׼�ľ���ײ��ľ���
	 */
	private float viewStandardMarginBottom;

	/**
	 * �齨��׼�ı���
	 * */
	private float viewStandarWeight;

	/**
	 * �����ǰ�Ŀ�
	 */
	private float viewCurrentWidth;

	/**
	 * �����ǰ�ĸ�
	 */
	private float viewCurrentHeight;

	/**
	 * �����ǰ������ߵľ���
	 */
	private float viewCurrentMarginLeft;

	/**
	 * �����ǰ�����ұߵľ���
	 */
	private float viewCurrentMarginRight;

	/**
	 * �����ǰ����ײ��ľ���
	 */
	private float viewCurrentMarginBottom;

	/**
	 * �����ǰ���붥���ľ���
	 */
	private float viewCurrentMarginTop;

	/**
	 * �����ǰ����
	 * */
	private float viewCurrentWeight;

	/**
	 * UI����Ķ���
	 */
	private View view;

	/**
	 * ��View�ĸ��಼�ֵ�����
	 */
	private int parentLayoutType;

	/**
	 * ���಼�ֵ�����Ϊ��Բ���
	 */
	private final int LAYOUT_TYPE_RELATiVELAYOUT = LayoutInformation.R;

	/**
	 * ���಼�ֵ�����Ϊ���Բ���
	 */
	private final int LAYOUT_TYPE_LINEARLAYOUT = LayoutInformation.L;

	/**
	 * ��������Ϊwrap_content
	 */
	private final int LAYOUTPARAMS_WARP_CONTENT = LayoutParams.WRAP_CONTENT;

	/**
	 * ��������Ϊfill_parent
	 */
	private final int LAYOUTPARAMS_FILL_PARENT = LayoutParams.FILL_PARENT;

	private Context context;

	/**
	 * �����ʵ��ʱ,���� ��׼��Ļ���,�߶�
	 * 
	 * @param context
	 *            Context
	 * @param standardWidth
	 *            ��׼��Ļ�Ŀ�
	 * @param standardHeight
	 *            ��׼��Ļ�ĸ�
	 */
	public LayoutAdapter(Context context, float standardWidth,
			float standardHeight) {
		this.context = context;
		getScreenSize();
		STANDARD_SCREEN_HEIGHT = standardHeight;
		STANDARD_SCREEN_WIDTH = standardWidth;
		// �����߱���
		WIDTH_RATIO = CURRENT_SCREEN_WIDTH / STANDARD_SCREEN_WIDTH;
		HEIGHT_RATIO = CURRENT_SCREEN_HEIGHT / STANDARD_SCREEN_HEIGHT;
	}

	/**
	 * ��ȡ��ǰ��Ļ��С���ܶ�
	 */
	private void getScreenSize() {
		SharedPreferences preferences = context.getSharedPreferences("screenconfig", 0);
		DisplayMetrics displayMetrics = new DisplayMetrics();
		((Activity) context).getWindowManager().getDefaultDisplay()
				.getMetrics(displayMetrics);
		//���
		if(preferences.getInt("width", 0) != displayMetrics.widthPixels){
			CURRENT_SCREEN_WIDTH = displayMetrics.widthPixels;
			preferences.edit().putInt("width", displayMetrics.widthPixels).commit();
		}
		else{
			CURRENT_SCREEN_WIDTH = preferences.getInt("width", 0);
		}
		//�߶�
		if(preferences.getInt("height", 0) != displayMetrics.heightPixels){
			CURRENT_SCREEN_HEIGHT = displayMetrics.heightPixels;
			preferences.edit().putInt("height", displayMetrics.heightPixels).commit();
		}
		else{
			CURRENT_SCREEN_HEIGHT = preferences.getInt("height", 0);
		}
		//dpiֵ
		if(preferences.getInt("densityDpi", 0) != displayMetrics.densityDpi){
			CURRENT_DENSITY = displayMetrics.densityDpi;
			preferences.edit().putInt("densityDpi", displayMetrics.densityDpi).commit();
		}
		else{
			CURRENT_DENSITY = preferences.getInt("densityDpi", 0);
		}
//		CURRENT_SCREEN_HEIGHT = displayMetrics.heightPixels;
//		CURRENT_DENSITY = displayMetrics.densityDpi;
		DENSITY_RATIO = STANDARD_DENSITY / CURRENT_DENSITY;
	}

	public void addViewLayout(LayoutInformation information) {
		view = information.getView();
		viewStandardWidth = information.getViewWidth();
		viewStandardHeight = information.getViewHeight();
		viewStandardMarginLeft = information.getViewMarginLeft();
		viewStandardMarginTop = information.getViewMarginTop();
		viewStandardMarginRight = information.getViewMarginRight();
		viewStandardMarginBottom = information.getViewMarginBottom();
		viewStandarWeight = information.getWeight();

		setLayoutParams();
		viewCurrentMarginLeft = viewStandardMarginLeft * WIDTH_RATIO;
		viewCurrentMarginTop = viewStandardMarginTop * HEIGHT_RATIO;
		viewCurrentMarginRight = viewStandardMarginRight * WIDTH_RATIO;
		viewCurrentMarginBottom = viewStandardMarginBottom * HEIGHT_RATIO;
		viewCurrentWeight = viewStandarWeight;
		if (view.getParent() instanceof LinearLayout)
			parentLayoutType = LayoutInformation.L;
		if (view.getParent() instanceof RelativeLayout)
			parentLayoutType = LayoutInformation.R;
		setLayoutByParentLayoutType();
	}

	/**
	 * ����ͨ��
	 * 
	 * @param listdata
	 */
	public void setViewLayout(List<LayoutInformation> listdata) {

		for (int i = 0; i < listdata.size(); i++) {

			view = listdata.get(i).getView();
			viewStandardWidth = listdata.get(i).getViewWidth();
			viewStandardHeight = listdata.get(i).getViewHeight();
			viewStandardMarginLeft = listdata.get(i).getViewMarginLeft();
			viewStandardMarginTop = listdata.get(i).getViewMarginTop();
			viewStandardMarginRight = listdata.get(i).getViewMarginRight();
			viewStandardMarginBottom = listdata.get(i).getViewMarginBottom();
			viewStandarWeight = listdata.get(i).getWeight();

			setLayoutParams();
			viewCurrentMarginLeft = viewStandardMarginLeft * WIDTH_RATIO;
			viewCurrentMarginTop = viewStandardMarginTop * HEIGHT_RATIO;
			viewCurrentMarginRight = viewStandardMarginRight * WIDTH_RATIO;
			viewCurrentMarginBottom = viewStandardMarginBottom * HEIGHT_RATIO;
			viewCurrentWeight = viewStandarWeight;
			if (listdata.get(i).getView().getParent() instanceof LinearLayout)
				parentLayoutType = LayoutInformation.L;
			if (listdata.get(i).getView().getParent() instanceof RelativeLayout)
				parentLayoutType = LayoutInformation.R;
			setLayoutByParentLayoutType();
		}
	}

	/**
	 * �жϲ������Ե�ֵ�����ò��ֵ�����
	 */
	private void setLayoutParams() {
		// ����׼�Ŀ���wrap_content����fill_parent��ʹ��ԭֵ��������м���õ�ͨ����ֵ
		if (viewStandardWidth == LAYOUTPARAMS_WARP_CONTENT
				|| viewStandardWidth == LAYOUTPARAMS_FILL_PARENT) {
			viewCurrentWidth = viewStandardWidth;
		} else {
			viewCurrentWidth = viewStandardWidth * WIDTH_RATIO;
		}

		// ����׼�Ŀ���wrap_content����fill_parent��ʹ��ԭֵ��������м���õ�ͨ����ֵ
		if (viewStandardHeight == LAYOUTPARAMS_WARP_CONTENT
				|| viewStandardHeight == LAYOUTPARAMS_FILL_PARENT) {
			viewCurrentHeight = viewStandardHeight;
		} else {
			viewCurrentHeight = viewStandardHeight * HEIGHT_RATIO;
		}
	}

	/**
	 * ͨ���жϴ�View����Ĳ�������,���View���ò���
	 */
	private void setLayoutByParentLayoutType() {

		if (parentLayoutType == LAYOUT_TYPE_RELATiVELAYOUT) {
			RelativeLayout.LayoutParams params = null;
			if (view.getLayoutParams() == null)
				params = new RelativeLayout.LayoutParams(
						(int) viewCurrentWidth, (int) viewCurrentHeight);
			else
				params = (RelativeLayout.LayoutParams) view.getLayoutParams();
			params.width = (int) viewCurrentWidth;
			params.height = (int) viewCurrentHeight;

			params.setMargins((int) viewCurrentMarginLeft,
					(int) viewCurrentMarginTop, (int) viewCurrentMarginRight,
					(int) viewCurrentMarginBottom);

			view.setLayoutParams(params);

		} else if (parentLayoutType == LAYOUT_TYPE_LINEARLAYOUT) {

			LinearLayout.LayoutParams params = null;
			if (view.getLayoutParams() == null)
				params = new LinearLayout.LayoutParams(
						(int) viewCurrentWidth, (int) viewCurrentHeight);
			else
				params = (LinearLayout.LayoutParams) view.getLayoutParams();
			params.width = (int) viewCurrentWidth;
			params.height = (int) viewCurrentHeight;
			if (viewCurrentWeight != -1)
				params.weight = (float) viewCurrentWeight;
			params.setMargins((int) viewCurrentMarginLeft,
					(int) viewCurrentMarginTop, (int) viewCurrentMarginRight,
					(int) viewCurrentMarginBottom);
			view.setLayoutParams(params);
		}
	}

	/**
	 * ���������С
	 * 
	 * @param standardSize
	 *            ԭʼ��С
	 * @return int
	 */
	public int setTextSize(int standardSize) {
		int currentSize;
		currentSize = (int) (standardSize * WIDTH_RATIO * DENSITY_RATIO);

		return currentSize;
	}

	public int CalcWidth(int width) {
		return (int) (width * WIDTH_RATIO);
	}

	public int CalcHeight(int height) {
		return (int) (WIDTH_RATIO * height);
	}

	public int CalcReverseWidth(int width) {
		return (int) ((float) width / WIDTH_RATIO);
	}

	public int CalcReverseHeight(int height) {
		return (int) ((float) height / HEIGHT_RATIO);
	}

	public float CalcWidth(float width) {
		return (float) (width * WIDTH_RATIO);
	}

	public float CalcHeight(float height) {
		return (float) (HEIGHT_RATIO * height);
	}

	public float CalcReverseWidth(float width) {
		return (float) (width / WIDTH_RATIO);
	}

	public float CalcReverseHeight(float height) {
		return (float) (height / HEIGHT_RATIO);
	}
}