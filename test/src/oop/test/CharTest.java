package oop.test;

import oop.util.Utils;

import org.junit.Assert;
import org.junit.Test;

public class CharTest {
    
	public static final String CHARACTERS =
			"àÀảẢãÃáÁạẠăĂằẰẳẲẵẴắẮặẶâÂầẦẩẨẫẪấẤậẬđĐèÈẻẺẽẼéÉẹẸêÊềỀểỂễỄếẾệỆ"
		    + "ìÌỉỈĩĨíÍịỊòÒỏỎõÕóÓọỌôÔồỒổỔỗỖốỐộỘơƠờỜởỞỡỠớỚợỢ"
		    + "ùÙủỦũŨúÚụỤưƯừỪửỬữỮứỨựỰ ";
    public static final String CHARS2 = 
			"aAaAaAaAaAaAaAaAaAaAaAaAaAaAaAaAaAdDeEeEeEeEeEeEeEeEeEeEeE"
		    + "iIiIiIiIiIoOoOoOoOoOoOoOoOoOoOoOoOoOoOoOoOoO"
		    + "uUuUuUuUuUuUuUuUuUuUuU_";

    @Test
    public void perform() {
    	Assert.assertTrue(CHARACTERS.length() == CHARS2.length());
    	char[] c1 = new char[CHARACTERS.length()];
    	char[] c2 = new char[CHARACTERS.length()];
    	for (int i = 0; i < c2.length; i++) {
    		int j = i;
			for ( ; j > 0; j--) {
				if (c1[j-1] > CHARACTERS.charAt(i)) {
					char t = c1[j-1]; c1[j-1] = c1[j]; c1[j] = t;
					t = c2[j-1]; c2[j-1] = c2[j]; c2[j] = t;
				} else {
					break;
				}
			}
			c1[j] = CHARACTERS.charAt(i);
			c2[j] = CHARS2.charAt(i);
		}
//    	System.out.println(c1);
//    	System.out.println(c2);
    	for (int i = 0; i < c1.length; i++) {
			System.out.print("'" + c1[i] + "', ");
		}
    	System.out.println();
    	for (int i = 0; i < c2.length; i++) {
    		System.out.print("'" + c2[i] + "', ");
    	}
    	System.out.println();
    	System.out.println("Total: " + c1.length);
    }
    
    @Test
    public void urlFriendly() {
    	Assert.assertEquals("khong_dau", Utils.toUrlFriendly("không dấu"));
    	Assert.assertEquals("De_thi_thu_dai_hoc", Utils.toUrlFriendly("Đề thi thử đại học"));
    }
    
}
