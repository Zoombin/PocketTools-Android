package com.juhe.pockettools.calendar.datepicker;

import java.util.LinkedList;
import java.util.List;

import kankan.wheel.widget.WheelAdapter;
import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.Typeface;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.GradientDrawable;
import android.os.Handler;
import android.os.Message;
import android.text.Layout;
import android.text.StaticLayout;
import android.text.TextPaint;
import android.util.AttributeSet;
import android.util.FloatMath;
import android.view.GestureDetector;
import android.view.GestureDetector.SimpleOnGestureListener;
import android.view.MotionEvent;
import android.view.View;
import android.view.animation.Interpolator;
import android.widget.Scroller;

import com.zoombin.koudai.R;

public class WheelView extends View {
	/** 滚动的间隔 */
	private static final int SCROLLING_DURATION = 400;
	/** 最小滚动的距离 */
	private static final int MIN_DELTA_FOR_SCROLLING = 1;
	/** 附加的高度 */
	private static final int ADDITIONAL_ITEM_HEIGHT = 15;
	/** 附加的宽度 */
	private static final int ADDITIONAL_ITEMS_SPACE = 10;
	/** 文本的距离 */
	private static final int LABEL_OFFSET = 8;
	/** 左右的距离 */
	private static final int PADDING = 10;
	/** 默认显示的项目 */
	private static final int DEF_VISIBLE_ITEMS = 5;

	// 标签和文本
	private String label;
	private Drawable centerDrawable;

	// 阴影的材质
	private GradientDrawable topShadow;
	private GradientDrawable bottomShadow;
	private boolean isScrollingPerformed;
	private int scrollingOffset;
	private GestureDetector gestureDetector;
	private Scroller scroller;
	private int lastScrollY;
	private List<OnWheelChangedListener> changingListeners = new LinkedList<OnWheelChangedListener>();
	private List<OnWheelScrollListener> scrollingListeners = new LinkedList<OnWheelScrollListener>();
	private final int MESSAGE_SCROLL = 0;
	private final int MESSAGE_JUSTIFY = 1;

	public int textsize;
	boolean isCyclic = false;
	boolean iscantouch = true;
	private int VALUE_TEXT_COLOR = -268435456;
	private int ITEMS_TEXT_COLOR = -7566196;

	/** Top and bottom shadows colors */
	private static int[] SHADOWS_COLORS = new int[] { Color.WHITE, 16777215,
			16777215 };

	private final int ITEM_OFFSET = textsize / 5;
	private WheelAdapter adapter = null;
	private int currentItem = 0;
	private int itemsWidth = 0;
	private int labelWidth = 0;
	private int visibleItems = 5;
	private int itemHeight = 0;

	// 条目的笔刷
	private TextPaint itemsPaint;
	private TextPaint valuePaint;
	private StaticLayout itemsLayout;
	private StaticLayout labelLayout;
	private StaticLayout valueLayout;

	/**
	 * Constructor
	 */
	public WheelView(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
		initData(context);
	}

	/**
	 * Constructor
	 */
	public WheelView(Context context, AttributeSet attrs) {
		super(context, attrs);
		initData(context);
	}

	/**
	 * Constructor
	 */
	public WheelView(Context context) {
		super(context);
		initData(context);
	}

	/**
	 * 手势监听器
	 */
	private SimpleOnGestureListener gestureListener = new SimpleOnGestureListener() {

		// 按下操作
		public boolean onDown(MotionEvent e) {
			// 如果滚动在执行
			if (isScrollingPerformed) {
				// 滚动强制停止, 按下的时候不能继续滚动
				scroller.forceFinished(true);
				// 清理信息
				clearMessages();
				return true;
			}
			return false;
		}

		/*
		 * 手势监听器监听到 滚动操作后回调
		 * 
		 * 参数解析 : MotionEvent e1 : 触发滚动时第一次按下的事件 MotionEvent e2 : 触发当前滚动的移动事件
		 * float distanceX : 自从上一次调用 该方法 到这一次 x 轴滚动的距离, 注意不是 e1 到 e2 的距离, e1 到
		 * e2 的距离是从开始滚动到现在的滚动距离 float distanceY : 自从上一次回调该方法到这一次 y 轴滚动的距离
		 * 
		 * 返回值 : 如果事件成功触发, 执行完了方法中的操作, 返回true, 否则返回 false (non-Javadoc)
		 * 
		 * @see
		 * android.view.GestureDetector.SimpleOnGestureListener#onScroll(android
		 * .view.MotionEvent, android.view.MotionEvent, float, float)
		 */
		public boolean onScroll(MotionEvent e1, MotionEvent e2,
				float distanceX, float distanceY) {
			// 开始滚动, 并回调滚动监听器集合中监听器的 开始滚动方法
			startScrolling();
			doScroll((int) -distanceY);
			return true;
		}

		/*
		 * 当一个急冲手势发生后 回调该方法, 会计算出该手势在 x 轴 y 轴的速率
		 * 
		 * 参数解析 : -- MotionEvent e1 : 急冲动作的第一次触摸事件; -- MotionEvent e2 :
		 * 急冲动作的移动发生的时候的触摸事件; -- float velocityX : x 轴的速率 -- float velocityY : y
		 * 轴的速率
		 * 
		 * 返回值 : 如果执行完毕返回 true, 否则返回false, 这个就是自己定义的
		 * 
		 * (non-Javadoc)
		 * 
		 * @see
		 * android.view.GestureDetector.SimpleOnGestureListener#onFling(android
		 * .view.MotionEvent, android.view.MotionEvent, float, float)
		 */
		public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX,
				float velocityY) {
			// 计算上一次的 y 轴位置, 当前的条目高度 加上 剩余的 不够一行高度的那部分
			lastScrollY = currentItem * getItemHeight() + scrollingOffset;
			// 如果可以循环最大值是无限大, 不能循环就是条目数的高度值
			int maxY = isCyclic ? 0x7FFFFFFF : adapter.getItemsCount()
					* getItemHeight();
			int minY = isCyclic ? -maxY : 0;
			/*
			 * Scroll 开始根据一个急冲手势滚动, 滚动的距离与初速度有关 参数介绍 : -- int startX : 开始时的 X轴位置
			 * -- int startY : 开始时的 y轴位置 -- int velocityX : 急冲手势的 x 轴的初速度, 单位
			 * px/s -- int velocityY : 急冲手势的 y 轴的初速度, 单位 px/s -- int minX : x
			 * 轴滚动的最小值 -- int maxX : x 轴滚动的最大值 -- int minY : y 轴滚动的最小值 -- int
			 * maxY : y 轴滚动的最大值
			 */
			scroller.fling(0, lastScrollY, 0, (int) -velocityY / 2, 0, 0, minY,
					maxY);
			setNextMessage(MESSAGE_SCROLL);
			return true;
		}
	};

	/**
	 * 动画控制器 animation handler
	 * 
	 * 可能会造成内存泄露 : 添加注解 HandlerLeak Handler 类应该应该为static类型，否则有可能造成泄露。
	 * 在程序消息队列中排队的消息保持了对目标Handler类的应用。 如果Handler是个内部类，那 么它也会保持它所在的外部类的引用。
	 * 为了避免泄露这个外部类，应该将Handler声明为static嵌套类，并且使用对外部类的弱应用。
	 */
	@SuppressLint("HandlerLeak")
	private Handler animationHandler = new Handler() {
		public void handleMessage(Message msg) {
			// 回调该方法获取当前位置, 如果返回true, 说明动画还没有执行完毕
			scroller.computeScrollOffset();
			// 获取当前 y 位置
			int currY = scroller.getCurrY();
			// 获取已经滚动了的位置, 使用上一次位置 减去 当前位置
			int delta = lastScrollY - currY;
			lastScrollY = currY;
			if (delta != 0) {
				// 改变值不为 0 , 继续滚动
				doScroll(delta);
			}

			/*
			 * 如果滚动到了指定的位置, 滚动还没有停止 这时需要强制停止
			 */
			if (Math.abs(currY - scroller.getFinalY()) < MIN_DELTA_FOR_SCROLLING) {
				currY = scroller.getFinalY();
				scroller.forceFinished(true);
			}

			/*
			 * 如果滚动没有停止 再向 Handler 发送一个停止
			 */
			if (!scroller.isFinished()) {
				animationHandler.sendEmptyMessage(msg.what);
			} else if (msg.what == MESSAGE_SCROLL) {
				justify();
			} else {
				finishScrolling();
			}
		}
	};

	/**
	 * 计算布局期望的高度
	 * 
	 * @param layout
	 *            组件的布局的
	 * @return 布局需要的高度
	 */
	private int getDesiredHeight(Layout layout) {
		if (layout == null) {
			return 0;
		}

		/*
		 * 布局需要的高度是 条目个数 * 可见条目数 减去 顶部和底部隐藏的一部份 减去 额外的条目高度
		 */
		int desired = getItemHeight() * visibleItems - ITEM_OFFSET * 2
				- ADDITIONAL_ITEM_HEIGHT;

		// 将计算的布局高度 与 最小高度比较, 取最大值
		desired = Math.max(desired, getSuggestedMinimumHeight());

		return desired;
	}

	/**
	 * 根据条目获取字符串
	 * 
	 * @param index
	 *            条目索引
	 * @return 条目显示的字符串
	 */
	private String getTextItem(int index) {
		if (adapter == null || adapter.getItemsCount() == 0) {
			return null;
		}
		// 适配器显示的字符串个数
		int count = adapter.getItemsCount();

		// 考虑 index 小于 0 的情况
		if ((index < 0 || index >= count) && !isCyclic) {
			return null;
		} else {
			while (index < 0) {
				index = count + index;
			}
		}

		// index 大于 0
		index %= count;
		return adapter.getItem(index);
	}

	/**
	 * 根据当前值创建 字符串
	 * 
	 * @param useCurrentValue
	 *            是否在滚动
	 * @return the text 生成的字符串
	 */
	private String buildText(boolean useCurrentValue) {
		// 创建字符串容器
		StringBuilder itemsText = new StringBuilder();
		// 计算出显示的条目相对位置, 例如显示 5个, 第 3 个是正中见选中的布局
		int addItems = visibleItems / 2 + 1;

		/*
		 * 遍历显示的条目 获取当前显示条目 上下 各 addItems 个文本, 将该文本添加到显示文本中去 如果不是最后一个 都加上回车
		 */
		for (int i = currentItem - addItems; i <= currentItem + addItems; i++) {
			// 如果在滚动
			if (useCurrentValue || i != currentItem) {
				String text = getTextItem(i);
				if (text != null) {
					itemsText.append(text);
				}
			}
			if (i < currentItem + addItems) {
				itemsText.append("\n");
			}
		}

		return itemsText.toString();
	}

	/**
	 * 初始化数据
	 * 
	 * @param context
	 *            上下文对象
	 */
	private void initData(Context context) {
		// 创建一个手势处理
		gestureDetector = new GestureDetector(context, gestureListener);
		/*
		 * 是否允许长按操作, 如果设置为 true 用户按下不松开, 会返回一个长按事件, 如果设置为 false, 按下不松开滑动的话
		 * 会收到滚动事件.
		 */
		gestureDetector.setIsLongpressEnabled(false);

		// 使用默认的 时间 和 插入器 创建一个滚动器
		scroller = new Scroller(context);
	}

	/**
	 * 画出顶部和底部的控件
	 * 
	 * @param canvas
	 *            the canvas for drawing
	 */
	private void drawShadows(Canvas canvas) {
		topShadow.setBounds(0, 0, getWidth(), getHeight() / visibleItems);
		topShadow.draw(canvas);

		bottomShadow.setBounds(0, getHeight() - getHeight() / visibleItems,
				getWidth(), getHeight());
		bottomShadow.draw(canvas);
	}

	/**
	 * Scrolls the wheel
	 * 
	 * @param delta
	 *            the scrolling value
	 */
	private void doScroll(int delta) {
		scrollingOffset += delta;
		int count = scrollingOffset / getItemHeight();
		int pos = currentItem - count;
		if (isCyclic && adapter.getItemsCount() > 0) {
			// fix position by rotating
			while (pos < 0) {
				pos += adapter.getItemsCount();
			}
			pos %= adapter.getItemsCount();
		} else if (isScrollingPerformed) {
			//
			if (pos < 0) {
				count = currentItem;
				pos = 0;
			} else if (pos >= adapter.getItemsCount()) {
				count = currentItem - adapter.getItemsCount() + 1;
				pos = adapter.getItemsCount() - 1;
			}
		} else {
			// fix position
			pos = Math.max(pos, 0);
			pos = Math.min(pos, adapter.getItemsCount() - 1);
		}

		int offset = scrollingOffset;
		if (pos != currentItem) {
			setCurrentItem(pos, false);
		} else {
			invalidate();
		}

		// update offset
		scrollingOffset = offset - count * getItemHeight();
		if (scrollingOffset > getHeight()) {
			scrollingOffset = scrollingOffset % getHeight() + getHeight();
		}
	}

	/**
	 * 绘制选中条目
	 * 
	 * @param canvas
	 *            画布
	 */
	private void drawValue(Canvas canvas) {
		valuePaint.setColor(VALUE_TEXT_COLOR);

		// 将当前 View 状态属性值 转为整型集合, 赋值给 普通条目布局的绘制属性
		valuePaint.drawableState = getDrawableState();

		Rect bounds = new Rect();
		// 获取选中条目布局的边界
		itemsLayout.getLineBounds(visibleItems / 2, bounds);

		// 绘制标签
		if (labelLayout != null) {
			canvas.save();
			canvas.translate(itemsLayout.getWidth() + LABEL_OFFSET, bounds.top);
			labelLayout.draw(canvas);
			canvas.restore();
		}

		// 绘制选中条目
		if (valueLayout != null) {
			canvas.save();
			canvas.translate(0, bounds.top + scrollingOffset);
			valueLayout.draw(canvas);
			canvas.restore();
		}
	}

	/**
	 * =进行文本的宽度和布局文件的计算
	 * 
	 * @param widthSize
	 *            the input layout width
	 * @param mode
	 *            the layout mode
	 * @return the calculated control width
	 */
	private int calculateLayoutWidth(int widthSize, int mode) {
		initResourcesIfNecessary();

		int width = widthSize;
		int maxLength = getMaxTextLength();
		if (maxLength > 0) {
			float textWidth = FloatMath.ceil(Layout.getDesiredWidth("0",
					itemsPaint));
			itemsWidth = (int) (maxLength * textWidth);
		} else {
			itemsWidth = 0;
		}
		itemsWidth += ADDITIONAL_ITEMS_SPACE; // make it some more

		labelWidth = 0;
		if (label != null && label.length() > 0) {
			labelWidth = (int) FloatMath.ceil(Layout.getDesiredWidth(label,
					valuePaint));
		}

		boolean recalculate = false;
		if (mode == MeasureSpec.EXACTLY) {
			width = widthSize;
			recalculate = true;
		} else {
			width = itemsWidth + labelWidth + 2 * PADDING;
			if (labelWidth > 0) {
				width += LABEL_OFFSET;
			}

			// Check against our minimum width
			width = Math.max(width, getSuggestedMinimumWidth());

			if (mode == MeasureSpec.AT_MOST && widthSize < width) {
				width = widthSize;
				recalculate = true;
			}
		}

		if (recalculate) {
			// recalculate width
			int pureWidth = width - LABEL_OFFSET - 2 * PADDING;
			if (pureWidth <= 0) {
				itemsWidth = labelWidth = 0;
			}
			if (labelWidth > 0) {
				double newWidthItems = (double) itemsWidth * pureWidth
						/ (itemsWidth + labelWidth);
				itemsWidth = (int) newWidthItems;
				labelWidth = pureWidth - itemsWidth;
			} else {
				itemsWidth = pureWidth + LABEL_OFFSET; // no label
			}
		}

		if (itemsWidth > 0) {
			createLayouts(itemsWidth, labelWidth);
		}

		return width;
	}

	/**
	 * 绘制普通条目
	 * 
	 * @param canvas
	 *            画布
	 */
	private void drawItems(Canvas canvas) {
		canvas.save();

		// 获取 y 轴 定点高度
		int top = itemsLayout.getLineTop(1);
		canvas.translate(0, -top + scrollingOffset);

		// 设置画笔颜色
		itemsPaint.setColor(ITEMS_TEXT_COLOR);
		// 将当前 View 状态属性值 转为整型集合, 赋值给 普通条目布局的绘制属性
		itemsPaint.drawableState = getDrawableState();
		// 将布局绘制到画布上
		itemsLayout.draw(canvas);

		canvas.restore();
	}

	/**
	 * 创建布局
	 * 
	 * @param widthItems
	 *            布局条目宽度
	 * @param widthLabel
	 *            label 宽度
	 */
	private void createLayouts(int widthItems, int widthLabel) {
		/*
		 * 创建普通条目布局 如果 普通条目布局 为 null 或者 普通条目布局的宽度 大于 传入的宽度, 这时需要重新创建布局 如果
		 * 普通条目布局存在, 并且其宽度小于传入的宽度, 此时需要将
		 */
		if (itemsLayout == null || itemsLayout.getWidth() > widthItems) {

			/*
			 * android.text.StaticLayout.StaticLayout( CharSequence source,
			 * TextPaint paint, int width, Alignment align, float spacingmult,
			 * float spacingadd, boolean includepad) 传入参数介绍 : CharSequence
			 * source : 需要分行显示的字符串 TextPaint paint : 绘制字符串的画笔 int width : 条目的宽度
			 * Alignment align : Layout 的对齐方式, ALIGN_CENTER 居中对齐, ALIGN_NORMAL
			 * 左对齐, Alignment.ALIGN_OPPOSITE 右对齐 float spacingmult : 行间距, 1.5f
			 * 代表 1.5 倍字体高度 float spacingadd : 基础行距上增加多少 , 真实行间距 等于 spacingmult
			 * 和 spacingadd 的和 boolean includepad :
			 */
			itemsLayout = new StaticLayout(buildText(isScrollingPerformed),
					itemsPaint, widthItems,
					widthLabel > 0 ? Layout.Alignment.ALIGN_OPPOSITE
							: Layout.Alignment.ALIGN_CENTER, 1,
					ADDITIONAL_ITEM_HEIGHT, false);
		} else {
			// 调用 Layout 内置的方法 increaseWidthTo 将宽度提升到指定的宽度
			itemsLayout.increaseWidthTo(widthItems);
		}

		/*
		 * 创建选中条目
		 */
		if (!isScrollingPerformed
				&& (valueLayout == null || valueLayout.getWidth() > widthItems)) {
			String text = getAdapter() != null ? getAdapter().getItem(
					currentItem) : null;
			valueLayout = new StaticLayout(text != null ? text : "",
					valuePaint, widthItems,
					widthLabel > 0 ? Layout.Alignment.ALIGN_OPPOSITE
							: Layout.Alignment.ALIGN_CENTER, 1,
					ADDITIONAL_ITEM_HEIGHT, false);
		} else if (isScrollingPerformed) {
			valueLayout = null;
		} else {
			valueLayout.increaseWidthTo(widthItems);
		}

		/*
		 * 创建标签条目
		 */
		if (widthLabel > 0) {
			if (labelLayout == null || labelLayout.getWidth() > widthLabel) {
				labelLayout = new StaticLayout(label, valuePaint, widthLabel,
						Layout.Alignment.ALIGN_NORMAL, 1,
						ADDITIONAL_ITEM_HEIGHT, false);
			} else {
				labelLayout.increaseWidthTo(widthLabel);
			}
		}
	}

	/**
	 * 绘制当前选中条目的背景图片
	 * 
	 * @param canvas
	 *            画布
	 */
	private void drawCenterRect(Canvas canvas) {
		int center = getHeight() / 2;
		int offset = getItemHeight() / 2;
		centerDrawable.setBounds(0, center - offset, getWidth(), center
				+ offset);
		centerDrawable.draw(canvas);
	}

	/**
	 * 使布局无效 将 选中条目 和 普通条目设置为 null, 滚动位置设置为0
	 */
	private void invalidateLayouts() {
		itemsLayout = null;
		valueLayout = null;
		scrollingOffset = 0;
	}

	/**
	 * 初始化资源
	 */
	private void initResourcesIfNecessary() {
		if (itemsPaint == null) {
			itemsPaint = new TextPaint(Paint.ANTI_ALIAS_FLAG);
			itemsPaint.setTextSize(textsize);
			Typeface typeface = Typeface.createFromAsset(getContext()
					.getAssets(), "fonts/HelveticaNeueLTPro-Lt.otf");
			itemsPaint.setTypeface(typeface);
		}
		if (valuePaint == null) {
			valuePaint = new TextPaint(Paint.ANTI_ALIAS_FLAG);
			valuePaint.setTextSize(textsize);
			Typeface typeface = Typeface.createFromAsset(getContext()
					.getAssets(), "fonts/HelveticaNeueLTPro-Lt.otf");
			valuePaint.setTypeface(typeface);
		}
		if (centerDrawable == null) {
			centerDrawable = getContext().getResources().getDrawable(
					R.drawable.wheel_val);
		}
		if (topShadow == null) {
			topShadow = new GradientDrawable(
					GradientDrawable.Orientation.TOP_BOTTOM, SHADOWS_COLORS);
		}
		if (bottomShadow == null) {
			bottomShadow = new GradientDrawable(
					GradientDrawable.Orientation.BOTTOM_TOP, SHADOWS_COLORS);
		}
		setBackgroundResource(R.drawable.wheel_val);
	}

	/**
	 * 清空队列中的信息
	 */
	private void clearMessages() {
		// 删除 Handler 执行队列中的滚动操作
		animationHandler.removeMessages(MESSAGE_SCROLL);
		animationHandler.removeMessages(MESSAGE_JUSTIFY);
	}

	/**
	 * 获取每个条目的高度
	 * 
	 * @return 条目的高度
	 */
	private int getItemHeight() {
		// 如果条目高度不为 0, 直接返回
		if (itemHeight != 0) {
			return itemHeight;
			// 如果条目的高度为 0, 并且普通条目布局不为null, 条目个数大于 2
		} else if (itemsLayout != null && itemsLayout.getLineCount() > 2) {
			/*
			 * itemsLayout.getLineTop(2) : 获取顶部第二行上面的垂直(y轴)位置, 如果行数等于
			 */
			itemHeight = itemsLayout.getLineTop(2) - itemsLayout.getLineTop(1);
			return itemHeight;
		}

		// 如果上面都不符合, 使用整体高度处以 显示条目数
		return getHeight() / visibleItems;
	}

	/**
	 * 返回 条目的字符串
	 * 
	 * @return 条目最大宽度
	 */
	private int getMaxTextLength() {
		WheelAdapter adapter = getAdapter();
		if (adapter == null) {
			return 0;
		}

		// 如果获取的最大条目宽度不为 -1, 可以直接返回该条目宽度
		int adapterLength = adapter.getMaximumLength();
		if (adapterLength > 0) {
			return adapterLength;
		}

		String maxText = null;
		int addItems = visibleItems / 2;
		/*
		 * 遍历当前显示的条目, 获取字符串长度最长的那个, 返回这个最长的字符串长度
		 */
		for (int i = Math.max(currentItem - addItems, 0); i < Math.min(
				currentItem + visibleItems, adapter.getItemsCount()); i++) {
			String text = adapter.getItem(i);
			if (text != null
					&& (maxText == null || maxText.length() < text.length())) {
				maxText = text;
			}
		}

		return maxText != null ? maxText.length() : 0;
	}

	/**
	 * 调整滚轮的方法
	 */
	private void justify() {
		if (adapter == null) {
			return;
		}

		lastScrollY = 0;
		int offset = scrollingOffset;
		int itemHeight = getItemHeight();
		boolean needToIncrease = offset > 0 ? currentItem < adapter
				.getItemsCount() : currentItem > 0;
		if ((isCyclic || needToIncrease)
				&& Math.abs((float) offset) > (float) itemHeight / 2) {
			if (offset < 0)
				offset += itemHeight + MIN_DELTA_FOR_SCROLLING;
			else
				offset -= itemHeight + MIN_DELTA_FOR_SCROLLING;
		}
		if (Math.abs(offset) > MIN_DELTA_FOR_SCROLLING) {
			scroller.startScroll(0, 0, 0, offset, SCROLLING_DURATION);
			setNextMessage(MESSAGE_JUSTIFY);
		} else {
			finishScrolling();
		}
	}

	/**
	 * WheelView 开始滚动
	 */
	private void startScrolling() {
		// 如果没有滚动, 将滚动状态 isScrollingPerformed 设为 true
		if (!isScrollingPerformed) {
			isScrollingPerformed = true;
			// 通知监听器开始滚动 回调所有的 滚动监听集合中 的 开始滚动方法
			notifyScrollingListenersAboutStart();
		}
	}

	/**
	 * 清空之前的 Handler 队列, 发送下一个消息到 Handler 中
	 * 
	 * @param message
	 *            要发送的消息
	 */
	private void setNextMessage(int message) {
		// 清空 Handler 队列中的 what 消息
		clearMessages();
		// 发送消息到 Handler 中
		animationHandler.sendEmptyMessage(message);
	}

	/**
	 * 回调元素改变监听器集合的元素改变监听器元素的元素改变方法
	 * 
	 * @param oldValue
	 *            旧的 WheelView选中的值
	 * @param newValue
	 *            新的 WheelView选中的值
	 */
	protected void notifyChangingListeners(int oldValue, int newValue) {
		for (OnWheelChangedListener listener : changingListeners) {
			listener.onChanged(this, oldValue, newValue);
		}
	}

	/**
	 * 设置当前元素的位置, 如果索引是错误的 不进行任何操作 -- 需要考虑该 WheelView 是否能循环 -- 根据是否需要滚动动画来确定是
	 * ①滚动到目的位置 还是 ②晴空所有条目然后重绘
	 * 
	 * @param index
	 *            要设置的元素索引值
	 * @param animated
	 *            动画标志位
	 */
	public void setCurrentItem(int index, boolean animated) {
		// 如果没有适配器或者元素个数为0 直接返回
		if (adapter == null || adapter.getItemsCount() == 0) {
			return; // throw?
		}
		// 目标索引小于 0 或者大于 元素索引最大值(个数 -1)
		if (index < 0 || index >= adapter.getItemsCount()) {
			// 入股WheelView 可循环, 修正索引值, 如果不可循环直接返回
			if (isCyclic) {
				while (index < 0) {
					index += adapter.getItemsCount();
				}
				index %= adapter.getItemsCount();
			} else {
				return; // throw?
			}
		}

		// 如果当前的索引不是传入的 索引
		if (index != currentItem) {

			/*
			 * 如果需要动画, 就滚动到目标位置 如果不需要动画, 重新设置布局
			 */
			if (animated) {
				/*
				 * 开始滚动, 每个元素滚动间隔 400 ms, 滚动次数是 目标索引值 减去 当前索引值, 这是滚动的真实方法
				 */
				scroll(index - currentItem, SCROLLING_DURATION);
			} else {
				// 所有布局设置为 null, 滚动位置设置为 0
				invalidateLayouts();

				int old = currentItem;
				currentItem = index;

				// 便利回调元素改变监听器集合中的监听器元素中的元素改变方法
				notifyChangingListeners(old, currentItem);

				// 重绘
				invalidate();
			}
		}
	}

	/**
	 * 添加 WheelView 选择的元素改变监听器
	 * 
	 * @param listener
	 *            the listener
	 */
	public void addChangingListener(OnWheelChangedListener listener) {
		changingListeners.add(listener);
	}

	/**
	 * 添加 WheelView 滚动监听器
	 * 
	 * @param listener
	 *            the listener
	 */
	public void addScrollingListener(OnWheelScrollListener listener) {
		scrollingListeners.add(listener);
	}

	/**
	 * 设置适配器
	 * 
	 * @param adapter
	 *            要设置的适配器
	 */
	public void setAdapter(WheelAdapter adapter, int[] paramArrayOfInt) {
		this.adapter = adapter;
		if (paramArrayOfInt != null) {
			SHADOWS_COLORS = paramArrayOfInt;
		}
		invalidateLayouts();
		invalidate();
	}

	/**
	 * 通知监听器开始滚动
	 */
	protected void notifyScrollingListenersAboutStart() {
		for (OnWheelScrollListener listener : scrollingListeners) {
			// 回调开始滚动方法
			listener.onScrollingStarted(this);
		}
	}

	/**
	 * 通知监听器结束滚动
	 */
	protected void notifyScrollingListenersAboutEnd() {
		for (OnWheelScrollListener listener : scrollingListeners) {
			// 回调滚动结束方法
			listener.onScrollingFinished(this);
		}
	}

	/**
	 * 滚动 WheelView
	 * 
	 * @param itemsToSkip
	 *            滚动的元素个数
	 * @param time
	 *            每次滚动的间隔
	 */
	public void scroll(int itemsToScroll, int time) {
		// 如果有滚动强制停止
		scroller.forceFinished(true);

		lastScrollY = scrollingOffset;
		int offset = itemsToScroll * getItemHeight();

		/*
		 * 给定 一个开始点, 滚动距离, 滚动间隔, 开始滚动
		 * 
		 * 参数解析 : 1. 开始的 x 轴位置 2. 开始的 y 轴位置 3. 要滚动 x 轴距离 4. 要滚动 y 轴距离 5. 滚动花费的时间
		 */
		scroller.startScroll(0, lastScrollY, 0, offset - lastScrollY, time);
		setNextMessage(MESSAGE_SCROLL);

		// 设置开始滚动状态, 并回调滚动监听器方法
		startScrolling();
	}

	/**
	 * 移除 WheelView 元素改变监听器
	 * 
	 * @param listener
	 *            the listener
	 */
	public void removeChangingListener(OnWheelChangedListener listener) {
		changingListeners.remove(listener);
	}

	/**
	 * 移除 WheelView 滚动监听器
	 * 
	 * @param listener
	 *            the listener
	 */
	public void removeScrollingListener(OnWheelScrollListener listener) {
		scrollingListeners.remove(listener);
	}

	/**
	 * 获取 WheelView 是否可以循环 -- 如果可循环 : 第一个之前是最后一个, 最后一个之后是第一个; -- 如果不可循环 :
	 * 到第一个就不能上翻, 最后一个不能下翻
	 * 
	 * @return
	 */
	public boolean isCyclic() {
		return isCyclic;
	}

	/**
	 * 结束滚动 设置滚动状态为 false, 回调滚动监听器的停止滚动方法
	 */
	void finishScrolling() {
		if (isScrollingPerformed) {
			notifyScrollingListenersAboutEnd();
			isScrollingPerformed = false;
		}
		// 设置布局无效
		invalidateLayouts();
		// 重绘布局
		invalidate();
	}

	/**
	 * 获取该 WheelView 的适配器
	 * 
	 * @return 返回适配器
	 */
	public WheelAdapter getAdapter() {
		return adapter;
	}

	/**
	 * 获取当前选中元素的索引
	 * 
	 * @return 当前元素索引
	 */
	public int getCurrentItem() {
		return currentItem;
	}

	/**
	 * 获取标签
	 * 
	 * @return the label
	 */
	public String getLabel() {
		return label;
	}

	/**
	 * 获取课件条目数
	 * 
	 * @return the count of visible items
	 */
	public int getVisibleItems() {
		return visibleItems;
	}

	/*
	 * 绘制组件 (non-Javadoc)
	 * 
	 * @see android.view.View#onDraw(android.graphics.Canvas)
	 */
	@Override
	protected void onDraw(Canvas canvas) {
		super.onDraw(canvas);

		// 如果条目布局为 null, 就创建该布局
		if (itemsLayout == null) {
			/*
			 * 如果 条目宽度为0, 说明该宽度没有计算, 先计算, 计算完之后会创建布局 如果 条目宽度 大于 0, 说明已经计算过宽度了,
			 * 直接创建布局
			 */
			if (itemsWidth == 0) {
				calculateLayoutWidth(getWidth(), MeasureSpec.EXACTLY);
			} else {
				// 创建普通条目布局, 选中条目布局, 标签条目布局
				createLayouts(itemsWidth, labelWidth);
			}
		}

		// 如果条目宽度大于0
		if (itemsWidth > 0) {
			canvas.save();
			// 使用平移方法忽略 填充的空间 和 顶部底部隐藏的一部份条目
			canvas.translate(PADDING, -ITEM_OFFSET);
			// 绘制普通条目
			drawItems(canvas);
			// 绘制选中条目
			drawValue(canvas);
			canvas.restore();
		}

		// 在中心位置绘制
		drawCenterRect(canvas);
		// 绘制阴影
		drawShadows(canvas);
	}

	/**
	 * 测量组件大小 (non-Javadoc)
	 * 
	 * @see android.view.View#onMeasure(int, int)
	 */
	@Override
	protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
		// 获取宽度 和 高度的模式 和 大小
		int widthMode = MeasureSpec.getMode(widthMeasureSpec);
		int heightMode = MeasureSpec.getMode(heightMeasureSpec);
		int widthSize = MeasureSpec.getSize(widthMeasureSpec);
		int heightSize = MeasureSpec.getSize(heightMeasureSpec);

		// 宽度就是 计算的布局的宽度
		int width = calculateLayoutWidth(widthSize, widthMode);

		int height;
		/*
		 * 精准模式 精准模式下 高度就是精确的高度
		 */
		if (heightMode == MeasureSpec.EXACTLY) {
			height = heightSize;

			// 未定义模式 和 最大模式
		} else {
			// 未定义模式下 获取布局需要的高度
			height = getDesiredHeight(itemsLayout);

			// 最大模式下 获取 布局高度 和 布局所需高度的最小值
			if (heightMode == MeasureSpec.AT_MOST) {
				height = Math.min(height, heightSize);
			}
		}

		// 设置组件的宽和高
		setMeasuredDimension(width, height);
	}

	/**
	 * 继承自 View 的触摸事件, 当出现触摸事件的时候, 就会回调该方法 (non-Javadoc)
	 * 
	 * @see android.view.View#onTouchEvent(android.view.MotionEvent)
	 */
	@Override
	public boolean onTouchEvent(MotionEvent event) {
		// 获取适配器
		WheelAdapter adapter = getAdapter();
		if (adapter == null) {
			return true;
		}

		/*
		 * gestureDetector.onTouchEvent(event) : 分析给定的动作, 如果可用, 调用 手势检测器的
		 * onTouchEvent 方法 -- 参数解析 : ev , 触摸事件 -- 返回值 : 如果手势监听器成功执行了该方法, 返回true,
		 * 如果执行出现意外 返回 false;
		 */
		if (!gestureDetector.onTouchEvent(event)
				&& event.getAction() == MotionEvent.ACTION_UP) {
			justify();
		}
		return true;
	}

	/**
	 * 设置当前选中的条目, 没有动画, 当索引出错不做任何操作
	 * 
	 * @param index
	 *            要设置的索引
	 */
	public void setCurrentItem(int index) {
		setCurrentItem(index, false);
	}

	/**
	 * 设置 WheelView 循环标志
	 * 
	 * @param isCyclic
	 *            the flag to set
	 */
	public void setCyclic(boolean isCyclic) {
		this.isCyclic = isCyclic;

		invalidate();
		invalidateLayouts();
	}

	/**
	 * 设置 Scroll 的插入器
	 * 
	 * @param interpolator
	 *            the interpolator
	 */
	public void setInterpolator(Interpolator interpolator) {
		// 强制停止滚动
		scroller.forceFinished(true);
		// 创建一个 Scroll 对象
		scroller = new Scroller(getContext(), interpolator);
	}

	public void setIsCanTouch(boolean iscantouch) {
		iscantouch = false;
	}

	public void setItemTextColor(int textcolor) {
		ITEMS_TEXT_COLOR = textcolor;
	}

	public void setLabel(String label) {
		if ((label == null) || (!label.equals(label))) {
			this.label = label;
			labelLayout = null;
			invalidate();
		}
	}

	public void setValueTextColor(int valuetextcolor) {
		VALUE_TEXT_COLOR = valuetextcolor;
	}

	public void setvisibleItems(int visibleItems) {
		this.visibleItems = visibleItems;
		invalidate();
	}
}
