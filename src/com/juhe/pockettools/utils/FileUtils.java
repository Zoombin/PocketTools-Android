package com.juhe.pockettools.utils;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.util.StringTokenizer;

import android.os.Environment;
import android.util.Log;

/**
 * 文件处理类
 * @author daiye
 *
 */
public class FileUtils {

	private static final String TAG = "FileUtil";

	private static FileUtils fileUtil = null;

	/**
	 * 第一次实例化时调用
	 * 
	 * @return
	 */
	public synchronized static FileUtils getInstance() {
		if (fileUtil == null) {
			fileUtil = new FileUtils();
		}
		return fileUtil;
	}

	private FileUtils() {
	}

	/**
	 * 判断sdcard是否存在
	 * 
	 * @return true存在可读写 false不存在不可读写
	 */
	public static boolean sdacrdExist() {
		if (Environment.getExternalStorageState().equals(
				Environment.MEDIA_MOUNTED)) {
			Log.d(TAG, "sdcard is exist,read and write ok");
			return true;
		}
		Log.d(TAG, "sdcard is not exist or can not read/write");
		return false;
	}

	/**
	 * 根据目录路径创建目录
	 * 
	 * @param directoryPath
	 *            目录的路径
	 * @return true代表成功 false代表失败
	 */
	public boolean createDirectory(String directoryPath) {
		StringTokenizer st = new StringTokenizer(directoryPath, "/");
		String currentPath = st.nextToken() + "/";
		String nextPath = currentPath;
		while (st.hasMoreTokens()) {
			currentPath = st.nextToken() + "/";
			nextPath += currentPath;
			File inbox = new File(nextPath);
			if (!inbox.isDirectory())
				inbox.delete();
			if (!inbox.exists())
				inbox.mkdir();
		}
		File directory = new File(directoryPath);
		if (directory.isDirectory()) {
			Log.d(TAG, "create directory" + directoryPath + " success");
			return true;
		}
		Log.d(TAG, "create the directory false");
		return false;
	}

	/**
	 * 判断该目录路径是否是文件目录
	 * 
	 * @param directoryPath
	 *            目录路径
	 * @return true代表是目录 false代表不是目录
	 */
	public boolean isDirectory(String directoryPath) {
		File directory = new File(directoryPath);
		if (directory.isDirectory()) {
			Log.d(TAG, directoryPath + " is a directory");
			return true;
		}
		Log.d(TAG, directoryPath + " is not a directory");
		return false;
	}

	/**
	 * 根据文件路径删除目录,注意目录下的子目录及文件，需要递归删除
	 * 
	 * @param directoryPath
	 *            目录路径
	 * @return true 删除成功 false删除失败
	 */
	public boolean deleteDirectory(String directoryPath) {
		try {
			File file = new File(directoryPath);
			if (!file.isDirectory()) {
				file.delete();
			} else if (file.isDirectory()) {
				String[] filelist = file.list();
				for (int i = 0; i < filelist.length; i++) {
					File delfile = new File(directoryPath + "/" + filelist[i]);
					if (!delfile.isDirectory()) {
						delfile.delete();
					} else if (delfile.isDirectory()) {
						deleteDirectory(directoryPath + "/" + filelist[i]);
					}
				}
				file.delete();
			}
		} catch (Exception e) {
			return false;
		}
		return true;

	}

	/**
	 * 根据文件路径创建文件
	 * 
	 * @param filePath
	 *            文件路径
	 * @return true创建成功 false创建失败
	 */
	public boolean createFile(String filePath) {
		try{
			File file = new File(filePath);
			if(file.exists())
				return false;
			return file.createNewFile();
		}catch (Exception e) {
			return false;
		}
		
	}

	/**
	 * 判断此文件路径是否是文件
	 * 
	 * @param filePath
	 *            文件路径
	 * @return true 代表是 false代表不是
	 */
	public boolean isFile(String filePath) {
		File file = new File(filePath);
		if(file.isFile()){
			Log.d(TAG,"the file is exist, fileName = " +filePath);
			return true;
		}
		Log.d(TAG,"the file is not exist, fileName = " +filePath);
		return false;		
	}

	/**
	 * 根据文件路径删除该文件
	 * 
	 * @param filePath
	 *            文件路径
	 * @return 如果删除成功返回true 失败 false
	 */
	public boolean deleteFile(String filePath) {
		try{
			File file = new File(filePath);
			if(file.exists()){
				boolean success =  file.delete();
				if(success){
					Log.d(TAG,"delete "+filePath+" success");	
					return true;
				}
			}
		}catch (Exception e) {
			return false;
		}
		return false;
	}

	/**
	 * 复制文件
	 * 
	 * @param srcPath
	 *            原来的文件路径
	 * @param destPath
	 *            目标目录路径
	 * @param newFilename 新的文件名
	 * @return 复制成功返回true 失败返回false
	 */
	public boolean copyFile(String srcPath, String destPath,String newFilename) {
		//源文件是否存在
		if(!isFile(srcPath))
				return false;
		//目标目录是否存在，不存在创建，创建失败返回失败
		if(!isDirectory(destPath)){
			if(!createDirectory(destPath))
				return false;			
		}
		
		//新的文件名为空或者为“”,直接返回失败
		if(null==newFilename||"".equals(newFilename))
			return false;
		
		try{
			File in = new File(srcPath);      
	        File out = new File(destPath+"/"+newFilename);  
	        FileInputStream fin = new FileInputStream(in);  
	        FileOutputStream fout = new FileOutputStream(out);  
	        FileChannel inc = fin.getChannel();  
	        FileChannel outc = fout.getChannel();  
	        int bufferLen = 2097152;  
	        ByteBuffer bb = ByteBuffer.allocateDirect(bufferLen);  
	        while (true)  
	        {  
	            int ret = inc.read(bb);  
	            if (ret == -1)  
	            {        
	                fin.close();  
	                fout.flush();  
	                fout.close();  
	                break;  
	            }  
	            bb.flip();  
	            outc.write(bb);  
	            bb.clear();  
	        }  
		} catch (Exception e) {
			return false;
		}    
        Log.d(TAG, "copy file success");   
		return true;
	}

	/**
	 * 复制文件夹 要注意目录下面有子目录及文件的复制
	 * 
	 * @param srcPath
	 *            源文件夹目录路径
	 * @param destPath
	 *            目标文件夹路径
	 * @return true代表复制成功 false代表复制失败
	 */
	public boolean copyDirectory(String srcPath, String destPath) {
		boolean copySuccess = true;
		//如果不是文件夹返回失败
		if(!isDirectory(srcPath))
			copySuccess = false;
		
		if(!isDirectory(destPath)){
			if(!createDirectory(destPath)){
				copySuccess = false;
			}			
		}
		
		File srcFile = new File(srcPath);
		File [] files = srcFile.listFiles();
		for(File file :files){
			if(file.isFile()){
				copySuccess = copyFile(file.getPath(),destPath,file.getName());
				if(!copySuccess)
					break;
					
			}else if(file.isDirectory()){
				copySuccess = copyDirectory(file.getPath(), destPath+"/"+file.getName());
				if(!copySuccess)
					break;
			}
		}
		return copySuccess;
	}

	
	/**
	 * 根据文件名获取文件的类型
	 * @param filename 文件名称
	 * @return Str 文件的后缀名
	 */
	public String getMIMEType(String filename){		
		    String type=" ";
		    //获取后缀名前的分隔符"."在fName中的位置。
		    int dotIndex = filename.lastIndexOf(".");
		    if(dotIndex < 0){
		        return type;
		    }
		    /* 获取文件的后缀名 */
		    String end=filename.substring(dotIndex+1,filename.length()).toLowerCase();
		    //Log.e("end---", "end="+end);
		    if(end.equals("")){
		    	return type;
		    }else{
		    	type = end;
		    }
		   
		    return type;
	}
	
	public static File updateDir = null;
	public static File updateFile = null;

	/***
	 * 创建更新app文件
	 */
	public void createAppFile(String name) {
		if (android.os.Environment.MEDIA_MOUNTED.equals(android.os.Environment.getExternalStorageState())) {
			
			updateDir = new File(Environment.getExternalStorageDirectory() + "/" + "update/");
			updateFile = new File(updateDir + "/" + name + ".apk");

			if (!updateDir.exists()) {
				updateDir.mkdirs();
			}
			
			if (!updateFile.exists()) {
				try {
					updateFile.createNewFile();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}

		}
	}

	/**
	 * 获得指定文件的byte数组
	 */
	public byte[] getCodeByFile(File file) {
		byte[] buffer = null;
		try {
			if (file.exists()) {
				FileInputStream fis = new FileInputStream(file);
				ByteArrayOutputStream bos = new ByteArrayOutputStream(1024);
				byte[] b = new byte[1024];
				int n;
				while ((n = fis.read(b)) != -1) {
					bos.write(b, 0, n);
				}
				fis.close();
				bos.close();
				buffer = bos.toByteArray();

				return buffer;
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

		return null;
	}
}
