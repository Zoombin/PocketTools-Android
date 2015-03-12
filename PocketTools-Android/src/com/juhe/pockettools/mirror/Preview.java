package com.juhe.pockettools.mirror;

import java.io.IOException;
import java.util.List;

import com.juhe.pockettools.utils.SavePhotoTool;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.hardware.Camera;
import android.hardware.Camera.Size;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.View;
import android.view.ViewGroup;

public class Preview extends ViewGroup implements SurfaceHolder.Callback {
	SurfaceView mSurfaceView;
	SurfaceHolder mHolder;
	Size mPreviewSize;
	List<Size> mSupportedPreviewSizes;
	Camera mCamera;
	public Camera.Size size = null;
	private final String TAG = "Preview";
	private boolean onoff = false;
	private CameraSizeL mCamerasizel;

	public Preview(Context context, CameraSizeL camerasizel) {
		super(context);
		mCamerasizel = camerasizel;
		mSurfaceView = new SurfaceView(context);
		addView(mSurfaceView);
		mHolder = mSurfaceView.getHolder();
		mHolder.addCallback(this);
		mHolder.setType(SurfaceHolder.SURFACE_TYPE_PUSH_BUFFERS);
	}

	private void setCameraParameters() {
		Camera.Parameters parameters = mCamera.getParameters();
		parameters.setExposureCompensation(0);
		DisplayMetrics localDisplayMetrics = getResources().getDisplayMetrics();
		int height = localDisplayMetrics.heightPixels;
		int width = localDisplayMetrics.widthPixels;
		size = getOptimalPreviewSize(parameters.getSupportedPreviewSizes(), height, width);
		if (size != null) {
			parameters.setPreviewSize(size.width, size.height);
			mCamerasizel.setSize(size);
		}
		mCamera.setParameters(parameters);
	}

	private void closeCamera() {
		if (onoff) {
			onoff = false;
			if (mCamera != null) {
				mCamera.setPreviewCallback(null);
				mCamera.stopPreview();
			}
		}
	}

	private Size getOptimalPreviewSize(List<Size> sizes, int w, int h) {
		final double ASPECT_TOLERANCE = 0.1;
		double targetRatio = (double) w / h;
		if (sizes == null)
			return null;

		Size optimalSize = null;
		double minDiff = Double.MAX_VALUE;

		int targetHeight = h;

		// Try to find an size match aspect ratio and size
		for (Size size : sizes) {
			double ratio = (double) size.width / size.height;
			if (Math.abs(ratio - targetRatio) > ASPECT_TOLERANCE)
				continue;
			if (Math.abs(size.height - targetHeight) < minDiff) {
				optimalSize = size;
				minDiff = Math.abs(size.height - targetHeight);
			}
		}

		// Cannot find the one match the aspect ratio, ignore the requirement
		if (optimalSize == null) {
			minDiff = Double.MAX_VALUE;
			for (Size size : sizes) {
				if (Math.abs(size.height - targetHeight) < minDiff) {
					optimalSize = size;
					minDiff = Math.abs(size.height - targetHeight);
				}
			}
		}
		return optimalSize;
	}

	public void takePictrue() {
		if (mCamera == null) {
			return;
		}
		mCamera.setPreviewCallback(null);
		mCamera.takePicture(null, null, null, new picturecallback(this));
	}

	class picturecallback implements Camera.PictureCallback {
		picturecallback(Preview preview) {
		}

		public void onPictureTaken(byte[] b, Camera camera) {
			savePhoto(b);
		}
	}

	public void startPreview(Camera camera) {
		mCamera = camera;
		if (mCamera != null) {
			mSupportedPreviewSizes = mCamera.getParameters()
					.getSupportedPreviewSizes();
			setCameraParameters();
			mCamera.startPreview();
			onoff = true;
		}
		try {
			mCamera.setPreviewDisplay(mHolder);
			return;
		} catch (IOException e) {
			Log.e(TAG, "IOException caused by setPreviewDisplay()", e);
		}
	}

	public void savePhoto(byte[] b) {
		new Thread(new runnable(this, b)).start();
	}

	class runnable implements Runnable {
		Preview preview;
		byte[] b;
		runnable(Preview preview, byte[] b) {
			this.preview = preview;
			this.b = b;
		}

		public void run() {
			Bitmap bitmap = BitmapFactory.decodeByteArray(b, 0,
					b.length);
			if (bitmap == null) {
				return;
			}
			SavePhotoTool.savePhote(bitmap);
			if ((bitmap != null) && (!bitmap.isRecycled())) {
				bitmap.recycle();
			}
			mCamera.startPreview();
		}
	}

	@Override
	protected void onLayout(boolean changed, int l, int t, int r, int b) {
		if (changed && getChildCount() > 0) {
			final View child = getChildAt(0);

			final int width = r - l;
			final int height = b - t;

			int previewWidth = width;
			int previewHeight = height;
			if (mPreviewSize != null) {
				previewWidth = mPreviewSize.width;
				previewHeight = mPreviewSize.height;
			}

			// Center the child SurfaceView within the parent.
			if (width * previewHeight > height * previewWidth) {
				final int scaledChildWidth = previewWidth * height
						/ previewHeight;
				child.layout((width - scaledChildWidth) / 2, 0,
						(width + scaledChildWidth) / 2, height);
			} else {
				final int scaledChildHeight = previewHeight * width
						/ previewWidth;
				child.layout(0, (height - scaledChildHeight) / 2, width,
						(height + scaledChildHeight) / 2);
			}
		}
	}

	@Override
	protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
		setMeasuredDimension(
				resolveSize(getSuggestedMinimumWidth(), widthMeasureSpec),
				resolveSize(getSuggestedMinimumHeight(), heightMeasureSpec));
	}

	public void surfaceChanged(SurfaceHolder paramSurfaceHolder, int paramInt1,
			int paramInt2, int paramInt3) {
		System.out.println("camera_surface,changed!");
	}

	public void surfaceCreated(SurfaceHolder holder) {
		System.out.println("camera_surface,created!");
		try {
			if (mCamera != null) {
				mCamera.setPreviewDisplay(holder);
			}
			return;
		} catch (IOException e) {
			Log.e("Preview", "IOException caused by setPreviewDisplay()", e);
			return;
		} catch (Exception e) {

		}
	}

	public void surfaceDestroyed(SurfaceHolder holder) {
		System.out.println("camera_surface,destroyed!");
		try {
			closeCamera();
			return;
		} catch (Exception e) {

		}
	}

	public static abstract interface CameraSizeL {
		public abstract void setSize(Camera.Size size);
	}
}
