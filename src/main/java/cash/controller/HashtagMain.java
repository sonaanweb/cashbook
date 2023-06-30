package cash.controller;

public class HashtagMain {
	public static void main(String[] args) {
		String content = "안녕하세요 #구디아카데미 입니다. 하반기 #자바 교육과정 시간표 공지하였습니다";
		
		
		/*
 		String content = "안녕하세요 #구디#아카데미 입니다. 하반기 ###자바 교육과정 시간표 공지하였습니다";

		* [해시태그만 출력] 구디아카데미 자바

		*/
		String[] arr = content.split(" ");
		int count = 0;
		for (String hashtag : arr) {
			if (hashtag.startsWith("#")) {
				String h = hashtag.replace("#", "");
				if (h.length() > 0) {
					count++;
					System.out.println(h);
				}
			}
		}
		System.out.println("해시태그개수 : " + count);
	}
}