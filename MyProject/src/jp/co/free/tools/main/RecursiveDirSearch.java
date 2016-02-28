package jp.co.free.tools.main;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class RecursiveDirSearch {

	/*--------------------------------*
	 *			変数定義
	 *--------------------------------*/
	private int cnt = 0;
	private List<File> fileList = new ArrayList<File>();

	/**
	 * メインメソッド
	 * @param args
	 */
	public static void main(String[] args) {
		RecursiveDirSearch me = new RecursiveDirSearch();
		me.execute();
	}
	/**
	 * サービス実行メソッド
	 */
	private void execute(){
		try{
			/*==================================*
			 * 対象ディレクトリにあるファイルを取得します
			 *==================================*/
			File rootDir = new File("D:\\work");
			traceFileName(rootDir);
			System.out.println("終了");

		}catch(IOException e){
			System.out.println(e);
		}

	}

	/**
	 * 再帰的に対象ディレクトリを検索し、対象ディレクトリに存在するファイルをリストに格納します。
	 * @param file
	 * @throws IOException
	 */
	private void traceFileName(File file) throws IOException{
		if(file.isDirectory()) {
			System.out.println(file.getPath());

			File[] files = file.listFiles();
			for(int i = 0; i < files.length; i++) {
				// 配下のファイルに対してtraceFileNameメソッドを実行
				traceFileName(files [i]);
			}
		} else {
			System.out.println(file.getPath());
			fileList.add(file);
		}
	}
}
