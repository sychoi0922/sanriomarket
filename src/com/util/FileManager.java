package com.util;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.OutputStream;

import javax.servlet.http.HttpServletResponse;

public class FileManager {
	
	//파일 다운로드
	
	public static boolean doFileDownload(HttpServletResponse response, 
			String saveFileName, String originalFileName, String path) {
		
		try {
			
			String filePath = path + File.separator + saveFileName;
			
			if(originalFileName==null || originalFileName.equals("")) {
				
				originalFileName = saveFileName;
			}
			
			
			//한글명의 파일을 다운받아서 클라이언트 컴에 파일 이름을 생성시
			//한글 깨짐 현상이 발생한다. 이를 방지한다.
			originalFileName = new String(
					originalFileName.getBytes("euc-kr"),"ISO-8859-1");	//8859_1로도 사용 가능
			
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
	
	
	
	//파일 삭제
	
	public static void doFileDelete(String fileName, String path) {
		
		try {
			
			String filePath = path + File.separator + fileName;
			
			File f = new File(filePath);
			
			if(f.exists()) {
				f.delete();		//물리적 파일 삭제
			}
			
		} catch (Exception e) {
			
			System.out.println(e.toString());
			// TODO: handle exception
		}
		
		
	}

}
