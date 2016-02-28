package jp.co.tutttuwi.bussiness.main;

import static jp.co.tutttuwi.bussiness.util.DirUril.*;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.List;

/**
 * ①検索文字列取得フォルダ
 * ②検索文字列Grepフォルダ
 * ③Grep結果出力ファイル
 * ①より検索文字列を取得後、②とぶつけて結果を③に出力する処理を実施する
 * @author Tomo
 *
 */
public class FileOperator {
	/*
	 * 変数定義領域
	 */
	private BufferedReader in = null;
	private BufferedReader br = null;
	private BufferedWriter bw = null;
	private String crlf = System.getProperty("line.separator");

	private List<String> baseStrs = null;
	private List<String> tarLines = null;

	private int COMMITCNT = 100;


	/**
	 * メインメソッド
	 * @param args
	 */
	public static void main(String[] args) {
		FileOperator me = new FileOperator();
		me.execute();

	}
	/**
	 * サービス実行メソッド
	 */
	private void execute(){
		try{

			System.out.println("文字列を入力してください");
			in = new BufferedReader(new InputStreamReader(System.in,"UTF-8"));
			String input = in.readLine();
			System.out.println("入力されたのは "+ crlf + input + crlf + " です");

			File baseDir = new File(in.readLine());
			baseStrs = grepWords(baseDir,"ID","HTMLTAG");
			if (baseStrs != null) {
				for(String bs: baseStrs){
					File tarDir = new File(in.readLine());
					tarLines.addAll(grepLines(tarDir,bs));
				}
			} else {
				System.out.println("検索対象文字列が見つかりませんでした。");
			}

			int cnt = 0;
			for(String line: tarLines){
				bw.write(line);
				bw.newLine();
				cnt ++;
				if (cnt == COMMITCNT) {
					bw.flush();
				}
			}
			//残バッファを書き込み
			bw.flush();
		} catch (IOException e ){

		} finally {
			System.out.println("処理が完了しました");
		}
	}
}
