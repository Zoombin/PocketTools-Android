package com.juhe.pockettools.adaptive;

import android.view.View;

/**
 * 存储View信息的JavaBean类
 * 
 * @author daiy
 * 
 */
public class LayoutInformation
{
        /**
         * View的对象
         */
        private View view;

        /**
         * View的宽度
         */
        private float viewWidth;

        /**
         * View的高度
         */
        private float viewHeight;

        /**
         * View距左边的距离，即marginLeft
         */
        private float viewMarginLeft;

        /**
         * View距顶部的距离,即MarginTop;
         */
        private float viewMarginTop;
        
        /**
         * View距右边的距离，即marginRight
         */
        private float viewMarginRight;

        /**
         * View距底部的距离,即MarginBottom;
         */
        private float viewMarginBottom;
        
        /**
         * 设置比重
         * */
        private float weight = -1;
     

		/**
         * 父类布局的类型为相对布局
         */
        public static int R=-1;
        
        /**
         * 父类布局的类型为线性布局
         */
        public static int L=-2;
        

        /**
         * 存储View信息的JavaBean类
         * 
         * @param view
         *            View的对象
         * @param viewWidth
         *            View的宽
         * @param viewHeight
         *            View的高
         * @param viewMarginLeft
         *            View距左边的距离
         * @param viewMargfloatop
         *            View距上部的距离
         * @param parentLayoutType
         *            父类布局的类型,LayoutInformation.R
         *            (表示相对布局)或者LayoutInformation.L(表示线性布局)
         */
        public LayoutInformation(View view, float viewWidth, float viewHeight,
                        float viewMarginLeft, float viewMarginTop)
        {

                this.view = view;
                this.viewWidth = viewWidth;
                this.viewHeight = viewHeight;
                this.viewMarginLeft = viewMarginLeft;
                this.viewMarginTop = viewMarginTop;
        }
        /**
         * 存储View信息的JavaBean类
         * 
         * @param view
         *            View的对象
         * @param viewWidth
         *            View的宽
         * @param viewHeight
         *            View的高
         * @param viewMarginLeft
         *            View距左边的距离
         * @param viewMargfloatop
         *            View距上部的距离
         * @param parentLayoutType
         *            父类布局的类型,LayoutInformation.R
         *            (表示相对布局)或者LayoutInformation.L(表示线性布局)
         */
        public LayoutInformation(View view, float viewWidth, float viewHeight,
        		float viewMarginLeft, float viewMarginTop, int weight)
        {
        	
        	this.view = view;
        	this.viewWidth = viewWidth;
        	this.viewHeight = viewHeight;
        	this.viewMarginLeft = viewMarginLeft;
        	this.viewMarginTop = viewMarginTop;
        	this.weight = weight;
        }
        
        /**
         * 存储View信息的JavaBean类
         * 
         * @param view
         *            View的对象
         * @param viewWidth
         *            View的宽
         * @param viewHeight
         *            View的高
         * @param viewMarginLeft
         *            View距左边的距离
         * @param viewMarginTop
         *            View距上部的距离
         * @param viewMarginRight
         *            View距左边的距离
         * @param viewMarginBottom
         *            View距下部的距离
         * @param parentLayoutType
         *            父类布局的类型,LayoutInformation.R
         *            (表示相对布局)或者LayoutInformation.L(表示线性布局)
         */
        public LayoutInformation(View view, float viewWidth, float viewHeight,
                        float viewMarginLeft, float viewMarginTop,float viewMarginRight,float viewMarginBottom)
        {

                this.view = view;
                this.viewWidth = viewWidth;
                this.viewHeight = viewHeight;
                this.viewMarginLeft = viewMarginLeft;
                this.viewMarginTop = viewMarginTop;
                this.viewMarginRight = viewMarginRight;
                this.viewMarginBottom = viewMarginBottom;
        }
        
        /**
         * 存储View信息的JavaBean类
         * 
         * @param view
         *            View的对象
         * @param viewWidth
         *            View的宽
         * @param viewHeight
         *            View的高
         * @param viewMarginLeft
         *            View距左边的距离
         * @param viewMarginTop
         *            View距上部的距离
         * @param viewMarginRight
         *            View距左边的距离
         * @param viewMarginBottom
         *            View距下部的距离
         * @param parentLayoutType
         *            父类布局的类型,LayoutInformation.R
         *            (表示相对布局)或者LayoutInformation.L(表示线性布局)
         */
        public LayoutInformation(View view, float viewWidth, float viewHeight,
        		float viewMarginLeft, float viewMarginTop,float viewMarginRight,float viewMarginBottom ,int weight)
        {
        	
        	this.view = view;
        	this.viewWidth = viewWidth;
        	this.viewHeight = viewHeight;
        	this.viewMarginLeft = viewMarginLeft;
        	this.viewMarginTop = viewMarginTop;
        	this.viewMarginRight = viewMarginRight;
        	this.viewMarginBottom = viewMarginBottom;
        	this.weight = weight;
        }

        /**
         * 获取View的对象
         * 
         * @return View对象
         */
        public View getView()
        {
                return view;
        }

        /**
         * 设置View的对象
         */
        public void setView(View view)
        {
                this.view = view;
        }

        /**
         * 获取View的宽度
         * 
         * @return View的宽度,float型
         */
        public float getViewWidth()
        {
                return viewWidth;
        }

        /**
         * 设置View的宽度，float型
         * 
         * @param viewWidth
         */
        public void setViewWidth(float viewWidth)
        {
                this.viewWidth = viewWidth;
        }

        /**
         * 获取View的高度
         * 
         * @return View的高度,float型
         */
        public float getViewHeight()
        {
                return viewHeight;
        }

        /**
         * 设置View的高度，float型
         * 
         * @param viewHeight
         */
        public void setViewHeight(float viewHeight)
        {
                this.viewHeight = viewHeight;
        }

        /**
         * 获取View距离左边的距离
         * 
         * @return View距离左边的距离，float型
         */
        public float getViewMarginLeft()
        {
                return viewMarginLeft;
        }

        /**
         * 设置View距离左边的距离,float型
         * 
         * @param viewMarginLeft
         */
        public void setViewMarginLeft(float viewMarginLeft)
        {
                this.viewMarginLeft = viewMarginLeft;
        }

        /**
         * 获取View距离上部的距离
         * 
         * @return View距离上部的距离，float型
         */
        public float getViewMarginTop()
        {
                return viewMarginTop;
        }

        /**
         * 设置View距离上部的距离，float型
         * 
         * @param viewMargfloatop
         */
        public void setViewMarginTop(float viewMarginTop)
        {
                this.viewMarginTop = viewMarginTop;
        }
        
		public float getViewMarginRight() {
			return viewMarginRight;
		}

		public void setViewMarginRight(float viewMarginRight) {
			this.viewMarginRight = viewMarginRight;
		}

		public float getViewMarginBottom() {
			return viewMarginBottom;
		}

		public void setViewMarginBottom(float viewMarginBottom) {
			this.viewMarginBottom = viewMarginBottom;
		}
		
		   
        public float getWeight() {
			return weight;
		}

		public LayoutInformation setWeight(float weight) {
			this.weight = weight;
			return this;
		}

}