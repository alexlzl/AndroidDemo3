package com.weeho.petim.lib.utils;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipException;
import java.util.zip.ZipFile;
import java.util.zip.ZipOutputStream;
import android.content.Context;
import android.os.Environment;
import android.os.StatFs;
import android.util.Log;

/**
 * 
 * 
 * @describe:文件管理类
 * 
 * @author:wangkui
 * 
 * @time:2014-11-4上午10:20:43
 * 
 */
public class FileManager {

	/**
	 * 写文本文件 在Android系统中，文件保存在 /data/data/PACKAGE_NAME/files 目录下
	 * 
	 * @param context
	 * @param msg
	 */
	private static final int BUFFER = 8192;
	public static final String CATEGORY_FILE = "category";
	public static final String RECENT_BROWSE_FILE = "recentbrowse";
	public static final String SEARCH_HISTORY_FILE = "searchhistory";
	public static final String LOGIN_THIRD_PLATFORM = "loginplatform";
	public static final String LOGIN_USER_NAME_LIST = "loginusernamelist";
	public static final String MYBEST_USER_INFO = "userinfo"; // 我的优选用户信息
	public static final String MYBEST_USER_HEAD_IMG = "userheadimg"; // 我的优选用户头像bitmap对象
	public static final String SEARCH_HOT_WORDS = "hotwords"; // 搜索热词
	public static final String ORDER_DETAIL = "orderdetail"; // 订单详情
	public static final String HOMRPAGE_POSITION_FILE = "homepageposition";
	public static final String HOMRPAGE_MODULE_FILE = "homepagemodule";
	public static final String HOMRPAGE_CHINAFOOD_FILE = "homepagechinafood";
	public static final String HOMEPAGE_ANNOUNCEMENT_FILE = "homepageannouncement";
	public static final String LOGIN_SUCCESS_INFO = "login_success_info"; // 登录成功返回信息
	public static final String SETTLEMENT_RECEIVER_ADDRESS = "receiver_address"; // 收货地址

	public static void write(Context context, String fileName, String content) {
		Log.e("写入本地文件", content);
		if (content == null)
			content = "";

		try {
			FileOutputStream fos = context.openFileOutput(fileName,
					Context.MODE_PRIVATE);
			fos.write(content.getBytes());

			fos.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * 读取文本文件
	 * 
	 * @param context
	 * @param fileName
	 * @return
	 */
	public static String read(Context context, String fileName) {
		try {
			FileInputStream in = context.openFileInput(fileName);
			return readInStream(in);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "";
	}

	private static String readInStream(FileInputStream inStream) {
		try {
			ByteArrayOutputStream outStream = new ByteArrayOutputStream();
			byte[] buffer = new byte[512];
			int length = -1;
			while ((length = inStream.read(buffer)) != -1) {
				outStream.write(buffer, 0, length);
			}

			outStream.close();
			inStream.close();
			return outStream.toString();
		} catch (IOException e) {
			Log.i("FileTest", e.getMessage());
		}
		return null;
	}

	public static File createFile(String folderPath, String fileName) {
		File destDir = new File(folderPath);
		if (!destDir.exists()) {
			destDir.mkdirs();
		}
		return new File(folderPath, fileName + fileName);
	}

	/**
	 * 向手机写图片
	 * 
	 * @param buffer
	 * @param folder
	 * @param fileName
	 * @return
	 */
	public static boolean writeFile(byte[] buffer, String folder,
			String fileName) {
		boolean writeSucc = false;

		boolean sdCardExist = Environment.getExternalStorageState().equals(
				android.os.Environment.MEDIA_MOUNTED);

		String folderPath = "";
		if (sdCardExist) {
			folderPath = Environment.getExternalStorageDirectory()
					+ File.separator + folder + File.separator;
		} else {
			writeSucc = false;
		}

		File fileDir = new File(folderPath);
		if (!fileDir.exists()) {
			fileDir.mkdirs();
		}

		File file = new File(folderPath + fileName);
		FileOutputStream out = null;
		try {
			out = new FileOutputStream(file);
			out.write(buffer);
			writeSucc = true;
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				out.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}

		return writeSucc;
	}

	/**
	 * 根据文件绝对路径获取文件名
	 * 
	 * @param filePath
	 * @return
	 */
	// public static String getFileName(String filePath) {
	// if (StringUtils.isEmpty(filePath))
	// return "";
	// return filePath.substring(filePath.lastIndexOf(File.separator) + 1);
	// }

	/**
	 * 根据文件的绝对路径获取文件名但不包含扩展名
	 * 
	 * @param filePath
	 * @return
	 */
	// public static String getFileNameNoFormat(String filePath) {
	// if (StringUtils.isEmpty(filePath)) {
	// return "";
	// }
	// int point = filePath.lastIndexOf('.');
	// return filePath.substring(filePath.lastIndexOf(File.separator) + 1,
	// point);
	// }

	/**
	 * 获取文件扩展名
	 * 
	 * @param fileName
	 * @return
	 */
	// public static String getFileFormat(String fileName) {
	// if (StringUtils.isEmpty(fileName))
	// return "";
	//
	// int point = fileName.lastIndexOf('.');
	// return fileName.substring(point + 1);
	// }

	/**
	 * 获取文件大小
	 * 
	 * @param filePath
	 * @return
	 */
	public static long getFileSize(String filePath) {
		long size = 0;

		File file = new File(filePath);
		if (file != null && file.exists()) {
			size = file.length();
		}
		return size;
	}

	/**
	 * 获取文件大小
	 * 
	 * @param size
	 *            字节
	 * @return
	 */
	public static String getFileSize(long size) {
		if (size <= 0)
			return "0";
		java.text.DecimalFormat df = new java.text.DecimalFormat("##.##");
		float temp = (float) size / 1024;
		if (temp >= 1024) {
			return df.format(temp / 1024) + "M";
		} else {
			return df.format(temp) + "K";
		}
	}

	/**
	 * 转换文件大小
	 * 
	 * @param fileS
	 * @return B/KB/MB/GB
	 */
	public static String formatFileSize(long fileS) {
		java.text.DecimalFormat df = new java.text.DecimalFormat("#.00");
		String fileSizeString = "";
		if (fileS < 1024) {
			fileSizeString = df.format((double) fileS) + "B";
		} else if (fileS < 1048576) {
			fileSizeString = df.format((double) fileS / 1024) + "KB";
		} else if (fileS < 1073741824) {
			fileSizeString = df.format((double) fileS / 1048576) + "MB";
		} else {
			fileSizeString = df.format((double) fileS / 1073741824) + "G";
		}
		return fileSizeString;
	}

	/**
	 * 获取目录文件大小
	 * 
	 * @param dir
	 * @return
	 */
	public static long getDirSize(File dir) {
		if (dir == null) {
			return 0;
		}
		if (!dir.isDirectory()) {
			return 0;
		}
		long dirSize = 0;
		File[] files = dir.listFiles();
		for (File file : files) {
			if (file.isFile()) {
				dirSize += file.length();
			} else if (file.isDirectory()) {
				dirSize += file.length();
				dirSize += getDirSize(file); // 递归调用继续统计
			}
		}
		return dirSize;
	}

	/**
	 * 获取目录文件个数
	 * 
	 * @param f
	 * @return
	 */
	public long getFileList(File dir) {
		long count = 0;
		File[] files = dir.listFiles();
		count = files.length;
		for (File file : files) {
			if (file.isDirectory()) {
				count = count + getFileList(file);// 递归
				count--;
			}
		}
		return count;
	}

	public static byte[] toBytes(InputStream in) throws IOException {
		ByteArrayOutputStream out = new ByteArrayOutputStream();
		int ch;
		while ((ch = in.read()) != -1) {
			out.write(ch);
		}
		byte buffer[] = out.toByteArray();
		out.close();
		return buffer;
	}

	/**
	 * 检查文件是否存在
	 * 
	 * @param name
	 * @return
	 */
	public static boolean checkFileExists(String name) {
		boolean status;
		if (!name.equals("")) {
			File path = Environment.getExternalStorageDirectory();
			File newPath = new File(path.toString() + name);
			status = newPath.exists();
		} else {
			status = false;
		}
		return status;
	}

	/**
	 * 检查路径是否存在
	 * 
	 * @param path
	 * @return
	 */
	public static boolean checkFilePathExists(String path) {
		return new File(path).exists();
	}

	/**
	 * 计算SD卡的剩余空间
	 * 
	 * @return 返回-1，说明没有安装sd卡
	 */
	@SuppressWarnings("deprecation")
	public static long getFreeDiskSpace() {
		String status = Environment.getExternalStorageState();
		long freeSpace = 0;
		if (status.equals(Environment.MEDIA_MOUNTED)) {
			try {
				File path = Environment.getExternalStorageDirectory();
				StatFs stat = new StatFs(path.getPath());
				long blockSize = stat.getBlockSize();
				long availableBlocks = stat.getAvailableBlocks();
				freeSpace = availableBlocks * blockSize / 1024;
			} catch (Exception e) {
				e.printStackTrace();
			}
		} else {
			return -1;
		}
		return (freeSpace);
	}

	/**
	 * 新建目录
	 * 
	 * @param directoryName
	 * @return
	 */
	public static boolean createDirectory(String directoryName) {
		boolean status;
		if (!directoryName.equals("")) {
			File path = Environment.getExternalStorageDirectory();
			File newPath = new File(path.toString() + directoryName);
			status = newPath.mkdir();
			status = true;
		} else
			status = false;
		return status;
	}

	/**
	 * 检查是否安装SD卡
	 * 
	 * @return
	 */
	public static boolean checkSaveLocationExists() {
		String sDCardStatus = Environment.getExternalStorageState();
		boolean status;
		if (sDCardStatus.equals(Environment.MEDIA_MOUNTED)) {
			status = true;
		} else
			status = false;
		return status;
	}

	/**
	 * 删除目录(包括：目录里的所有文件)
	 * 
	 * @param fileName
	 * @return
	 */
	public static boolean deleteDirectory(String fileName) {
		boolean status;
		SecurityManager checker = new SecurityManager();

		if (!fileName.equals("")) {

			File path = Environment.getExternalStorageDirectory();
			File newPath = new File(path.toString() + fileName);
			checker.checkDelete(newPath.toString());
			if (newPath.isDirectory()) {
				String[] listfile = newPath.list();
				// delete all files within the specified directory and then
				// delete the directory
				try {
					for (int i = 0; i < listfile.length; i++) {
						File deletedFile = new File(newPath.toString() + "/"
								+ listfile[i].toString());
						deletedFile.delete();
					}
					newPath.delete();
					Log.i("DirectoryManager deleteDirectory", fileName);
					status = true;
				} catch (Exception e) {
					e.printStackTrace();
					status = false;
				}

			} else
				status = false;
		} else
			status = false;
		return status;
	}

	/**
	 * 删除文件
	 * 
	 * @param fileName
	 * @return
	 */
	public static boolean deleteFile(String fileName) {
		boolean status;
		SecurityManager checker = new SecurityManager();

		if (!fileName.equals("")) {

			File path = Environment.getExternalStorageDirectory();
			File newPath = new File(path.toString() + File.separator+ fileName);
			checker.checkDelete(newPath.toString());
			if (newPath.isFile()) {
				try {
					Log.i("DirectoryManager deleteFile", fileName);
					newPath.delete();
					status = true;
				} catch (SecurityException se) {
					se.printStackTrace();
					status = false;
				}
			} else
				status = false;
		} else
			status = false;
		return status;
	}

	/**
	 * 删除空目录
	 * 
	 * 返回 0代表成功 ,1 代表没有删除权限, 2代表不是空目录,3 代表未知错误
	 * 
	 * @return
	 */
	public static int deleteBlankPath(String path) {
		File f = new File(path);
		if (!f.canWrite()) {
			return 1;
		}
		if (f.list() != null && f.list().length > 0) {
			return 2;
		}
		if (f.delete()) {
			return 0;
		}
		return 3;
	}

	/**
	 * 重命名
	 * 
	 * @param oldName
	 * @param newName
	 * @return
	 */
	public static boolean reNamePath(String oldName, String newName) {
		File f = new File(oldName);
		return f.renameTo(new File(newName));
	}

	/**
	 * 删除文件
	 * 
	 * @param filePath
	 */
	public static boolean deleteFileWithPath(String filePath) {
		SecurityManager checker = new SecurityManager();
		File f = new File(filePath);
		checker.checkDelete(filePath);
		if (f.isFile()) {
			Log.i("DirectoryManager deleteFile", filePath);
			f.delete();
			return true;
		}
		return false;
	}

	/**
	 * 获取SD卡的根目录，末尾带\
	 * 
	 * @return
	 */
	public static String getSDRoot() {
		return Environment.getExternalStorageDirectory().getAbsolutePath()
				+ File.separator;
	}

	/**
	 * 列出root目录下所有子目录
	 * 
	 * @param path
	 * @return 绝对路径
	 */
	public static List<String> listPath(String root) {
		List<String> allDir = new ArrayList<String>();
		SecurityManager checker = new SecurityManager();
		File path = new File(root);
		checker.checkRead(root);
		if (path.isDirectory()) {
			for (File f : path.listFiles()) {
				if (f.isDirectory()) {
					allDir.add(f.getAbsolutePath());
				}
			}
		}
		return allDir;
	}

	public enum PathStatus {
		SUCCESS, EXITS, ERROR
	}

	/**
	 * 创建目录
	 * 
	 * @param path
	 */
	public static PathStatus createPath(String newPath) {
		File path = new File(newPath);
		if (path.exists()) {
			return PathStatus.EXITS;
		}
		if (path.mkdir()) {
			return PathStatus.SUCCESS;
		} else {
			return PathStatus.ERROR;
		}
	}

	/**
	 * 截取路径名
	 * 
	 * @return
	 */
	public static String getPathName(String absolutePath) {
		int start = absolutePath.lastIndexOf(File.separator) + 1;
		int end = absolutePath.length();
		return absolutePath.substring(start, end);
	}

	/**
	 * 
	 * 删除文件夹里面的所有文件
	 * 
	 * 
	 * @param path
	 * 
	 *            String 文件夹路径 如 c:/fqf
	 */

	public static void delAllFile(String path) {

		File file = new File(path);

		if (!file.exists()) {

			return;

		}

		if (!file.isDirectory()) {

			return;

		}

		String[] tempList = file.list();

		File temp = null;

		for (int i = 0; i < tempList.length; i++) {

			if (path.endsWith(File.separator)) {

				temp = new File(path + tempList[i]);

			} else {

				temp = new File(path + File.separator + tempList[i]);

			}

			if (temp.isFile()) {

				temp.delete();

			}

			if (temp.isDirectory()) {

				delAllFile(path + "/" + tempList[i]);// 先删除文件夹里面的文件

				delBitmapFolder(path + "/" + tempList[i]);// 再删除空文件夹

			}

		}

	}

	/**
	 * 
	 * 清理图片缓存
	 * 
	 * 
	 * @param filePathAndName
	 * 
	 *            String 文件夹路径及名称
	 * 
	 * @param fileContent
	 * 
	 *            String
	 * 
	 * @return boolean
	 */

	public static void delBitmapFolder(String folderPath) {
		try {
			delAllFile(folderPath); // 删除完里面所有内容

			String filePath = folderPath;

			filePath = filePath.toString();

			java.io.File myFilePath = new java.io.File(filePath);

			myFilePath.delete(); // 删除空文件夹

		} catch (Exception e) {


			e.printStackTrace();

		}

	}

	// 复制文件
	public static void copyFile(File sourceFile, File targetFile)
			throws IOException {
		BufferedInputStream inBuff = null;
		BufferedOutputStream outBuff = null;
		try {
			inBuff = new BufferedInputStream(new FileInputStream(sourceFile));
			outBuff = new BufferedOutputStream(new FileOutputStream(targetFile));
			byte[] buffer = new byte[BUFFER];
			int length;
			while ((length = inBuff.read(buffer)) != -1) {
				outBuff.write(buffer, 0, length);
			}
			outBuff.flush();
		} finally {
			if (inBuff != null) {
				inBuff.close();
			}
			if (outBuff != null) {
				outBuff.close();
			}
		}
	}

	public static void addFile2Zip(byte[] bytes, String entryName) {
		ZipOutputStream zos = null;
		ByteArrayInputStream bis = null;
		try {
			zos = new ZipOutputStream(new ByteArrayOutputStream());
			zos.putNextEntry(new ZipEntry(entryName));
			bis = new ByteArrayInputStream(bytes);
			byte[] buf = new byte[1024];
			int len = 0;
			while ((len = bis.read(buf, 0, len)) != -1) {
				zos.write(buf, 0, len);
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (zos != null) {
				try {
					zos.closeEntry();
					zos.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
			if (bis != null) {
				try {
					bis.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	}

	@SuppressWarnings("resource")
	public byte[] getFileFromZip(String zipFilePath, String entryName) {
		ZipFile zipFile = null;
		try {
			zipFile = new ZipFile(new File(zipFilePath));
			ZipEntry ze = zipFile.getEntry(entryName);
			InputStream in = zipFile.getInputStream(ze);
			ByteArrayOutputStream bos = new ByteArrayOutputStream();
			byte[] buf = new byte[1024];
			int length = 0;
			while ((length = in.read(buf)) != -1) {
				bos.write(buf, 0, length);
			}
			byte[] bytes = bos.toByteArray();
			return bytes;
		} catch (ZipException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * 
	 * @description 获取object
	 * @author Skyin_wd
	 * @param context
	 *            上下文
	 * @param name
	 *            文件名
	 * @return
	 */
	public static Object getObject(Context context, String name) {
		Object obj = null;
		try {
			FileInputStream fis = context.openFileInput(name);
			ObjectInputStream ois = new ObjectInputStream(fis);
			obj = ois.readObject();
			ois.close();
			fis.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return obj;
	}

	/**
	 * 
	 * @description 保存 object
	 * @author Skyin_wd
	 * @param context
	 *            上下文
	 * @param obj
	 *            对象
	 * @param name
	 *            文件名
	 */
	public static void saveObject(Context context, Object obj, String name) {
		try {
			FileOutputStream fos = context.openFileOutput(name,
					Context.MODE_PRIVATE);
			ObjectOutputStream oos = new ObjectOutputStream(fos);
			oos.writeObject(obj);
			oos.close();
			fos.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * 
	 * @description 删除文件
	 * @author Skyin_wd
	 * @param context
	 *            上下文
	 * @param name
	 *            文件名
	 * @return
	 */
	public static void deleteFile(Context context, String name) {
		try {
			if (null == context || StringUtil.isEmpty(name)) {
				return;
			}
			File f = new File(context.getFilesDir(), name);
			if (f.exists()) {
				f.delete();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
