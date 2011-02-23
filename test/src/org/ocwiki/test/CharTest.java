package org.ocwiki.test;

import org.ocwiki.util.Utils;

import org.junit.Assert;
import org.junit.Test;

public class CharTest {

	public static final String SPECIAL_CHARACTERS = "àÀảẢãÃáÁạẠăĂằẰẳẲẵẴắẮặẶâÂầẦẩẨẫẪấẤậẬđĐèÈẻẺẽẼéÉẹẸêÊềỀểỂễỄếẾệỆìÌỉỈĩĨíÍịỊòÒỏỎõÕóÓọỌôÔồỒổỔỗỖốỐộỘơƠờỜởỞỡỠớỚợỢùÙủỦũŨúÚụỤưƯừỪửỬữỮứỨựỰýÝ :+\\<>\"*,!?%$=@#~[]`|^";

	public static final String REPLACEMENTS = "aAaAaAaAaAaAaAaAaAaAaAaAaAaAaAaAaAdDeEeEeEeEeEeEeEeEeEeEeEiIiIiIiIiIoOoOoOoOoOoOoOoOoOoOoOoOoOoOoOoOoOuUuUuUuUuUuUuUuUuUuUuUyY____\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0";

	@Test
	public void perform() {
		// lấy dữ liệu
		char[] c1 = SPECIAL_CHARACTERS.toCharArray();
		char[] c2 = REPLACEMENTS.toCharArray();
		Assert.assertTrue(c1.length == c2.length);
		// sắp xếp cả hai theo thứ tự tăng dần của c1
		for (int i = 1; i < c2.length; i++) {
			int j = i;
			for (; j > 0; j--) {
				if (c1[j - 1] > c1[j]) {
					char t = c1[j - 1]; c1[j - 1] = c1[j]; 	c1[j] = t;
					t = c2[j - 1]; c2[j - 1] = c2[j]; c2[j] = t;
				} else {
					break;
				}
			}
		}
		// in ra dưới định dạng phù hợp với cú pháp Java
		for (int i = 0; i < c1.length; i++) {
			System.out.print(quote(c1[i]));
		}
		System.out.println();
		for (int i = 0; i < c2.length; i++) {
			System.out.print(quote(c2[i]));
		}
		System.out.println();
		// cho biết độ dài mảng
		System.out.println("Total: " + c1.length);
	}

	private String quote(char ch) {
		String s;
		if (ch == '\0') {
			s = "\\0";
		} else if (ch == '\\') {
			s = "\\\\";
		} else {
			s = String.valueOf(ch);
		}
		return "'" + s + "', ";
	}

	@Test
	public void urlFriendly() {
		Assert.assertEquals("khong_dau", Utils.toUrlFriendly("không dấu"));
		Assert.assertEquals("De_thi_thu_dai_hoc",
				Utils.toUrlFriendly("Đề thi thử đại học"));
		Assert.assertEquals("kY_tu_DaC_bIET",
				Utils.toUrlFriendly("k,Ý* tự:Đ?<ặ>!C bI\"ỆT///"));
		Assert.assertEquals("html_special",
				Utils.toUrlFriendly("html# s@p><\"ecial"));
		Assert.assertEquals("linux_special",
				Utils.toUrlFriendly("lin|^ux s[p]eci`al"));
		Assert.assertEquals("", Utils.toUrlFriendly("//!\""));
		Assert.assertEquals("//__", Utils.toUrlFriendly("//\\\\!\"///"));
	}

}
