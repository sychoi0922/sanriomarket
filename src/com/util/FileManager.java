package com.util;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.OutputStream;

import javax.servlet.http.HttpServletResponse;

public class FileManager {
	
	//���� �ٿ�ε�
	
	public static boolean doFileDownload(HttpServletResponse response, 
			String saveFileName, String originalFileName, String path) {
		
		try {
			
			String filePath = path + File.separator + saveFileName;
			
			if(originalFileName==null || originalFileName.equals("")) {
				
				originalFileName = saveFileName;
			}
			
			
			//�ѱ۸��� ������ �ٿ�޾Ƽ� Ŭ���̾�Ʈ �Ŀ� ���� �̸��� ������
			//�ѱ� ���� ������ �߻��Ѵ�. �̸� �����Ѵ�.
			originalFileName = new String(
					originalFileName.getBytes("euc-kr"),"ISO-8859-1");	//8859_1�ε� ��� ����
			
			File f = new File(filePath);
			
			if(!f.exists()) {
				return false;
			}
			
			response.setContentType("application/octet-stream");
			response.setHeader("Content-disposition", "attachment;fileName=" + originalFileName);
			
			BufferedInputStream bis = 
					new BufferedInputStream(new FileInputStream(f));
			
			OutputStream out = response.getOutputStream();
			
			int data;
			byte[] bytes = new byte[4096];
			while((data=bis.read(bytes,0,4096))!=-1) {
				out.write(bytes,0,data);
			}
			
			out.flush();
			out.close();
			bis.close();
			
			
		} catch (Exception e) {
			
			System.out.println(e.toString());
			return false;
			// TODO: handle exception
		}
		
		return true;
		
	}
	
	
	
	//���� ����
	
	public static void doFileDelete(String fileName, String path) {
		
		try {
			
			String filePath = path + File.separator + fileName;
			
			File f = new File(filePath);
			
			if(f.exists()) {
				f.delete();		//������ ���� ����
			}
			
		} catch (Exception e) {
			
			System.out.println(e.toString());
			// TODO: handle exception
		}
		
		
	}

}
