package jp.co.free.tools.main;

import java.io.File;
import java.io.FilenameFilter;

public class FileFilter {

	/**
	 * メインメソッド
	 * @param args
	 */
	public static void main(String[] args) {
		FileFilter me = new FileFilter();
		me.execute();
	}

	/**
	 * サービス実行メソッド
	 */
	private void execute(){
		try{
			/*===========================================*
			 * 対象ディレクトリにあるテキストファイルを取得します
			 *===========================================*/
			File rootDir = new File("D:\\work");
			File[] txtFiles = rootDir.listFiles(txtFilter);
			for (int i = 0; i < txtFiles.length; i++){
				System.out.println(txtFiles[i].getPath());
			}
			System.out.println("終了");

		}catch(Exception e){
			System.out.println(e);
		}
	}


	/*--------------------------------*
	 *			匿名クラス定義
	 *--------------------------------*/
	/* フィルタ作成 */
	FilenameFilter txtFilter = new FilenameFilter() {
		/* ここに条件を書く。trueの場合、そのファイルは選択される */
		public boolean accept(File dir, String name) {
			int index = name.lastIndexOf(".");
			String ext = name.substring(index+1).toLowerCase();
			if (ext.equals("txt") == true) {
				return true;
			} else {
				return false;
			}
		}
	};

}
