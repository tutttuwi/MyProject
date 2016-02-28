package jp.co.tutttuwi.bussiness.util;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class DirUril {
	/*---------------------
	 * 変数定義
	 ----------------------*/


	/*---------------------
	 * ユーティリティメソッド
	 ----------------------*/
	/**
	 * 指定されたディレクトリ配下に存在するファイルを再帰的に検索
	 * @param file
	 * @return
	 */
	public static List<File> getAllFiles(File file){
		List<File> rsList = new ArrayList<File>();
		if(file.isDirectory()) {
			System.out.println(file.getPath());
			File[] files = file.listFiles();
			for(int i = 0; i < files.length; i++) {
				// 配下のファイルに対してtraceFileNameメソッドを実行
				getAllFiles(files [i]);
			}
		} else {
			System.out.println(file.getPath());
			rsList.add(file);
		}
		return rsList;
	}

	public static void searchStr(List<String> setList, BufferedReader bf, String htmlTag)throws IOException{
		String startTag = "<" + htmlTag + ">";
		String endTag = "</" + htmlTag + ">";
		String str = "";
		while((str = bf.readLine()) != null){
			if(str.matches(".*" + startTag + ".*" + endTag + ".*")){
				String tmp = str.substring(str.indexOf(startTag));
				tmp = tmp.substring(startTag.length(), tmp.indexOf(endTag));
				setList.add(tmp);
			}
		}
	}

	public static List<String> grepWords(File base, String sWord, String format)throws IOException{
		List<String> baseStrList = null;
		List<File> fileList = getAllFiles(base);
		BufferedReader br = null;
		for(File baseFile: fileList){
			br = new BufferedReader(new InputStreamReader(new FileInputStream(baseFile),""));
			searchStr(baseStrList,br,sWord);
		}
		return baseStrList;
	}

	public static List<String> grepLines(File base, String sWord) throws IOException{
		List<String> baseStrList = new ArrayList<String>();
		List<File> fileList = getAllFiles(base);
		BufferedReader br = null;
		String str = "";
		for(File baseFile: fileList){
			br = new BufferedReader(new InputStreamReader(new FileInputStream(baseFile),""));
			while((str = br.readLine()) != null){
				if(str.matches(".*"+sWord+".*")){
					baseStrList.add(str);
				}
			}
		}
		return baseStrList;
	}
}
