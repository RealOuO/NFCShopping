package scut.bgooo.db;

import java.nio.ByteBuffer;
import java.nio.CharBuffer;
import java.nio.charset.Charset;

public class TransistionUtil {

	public static char[] getChars(byte[] bytes) {
		Charset cs = Charset.forName("GBK");
		ByteBuffer bb = ByteBuffer.allocate(bytes.length);
		bb.put(bytes);
		bb.flip();
		CharBuffer cb = cs.decode(bb);
		
		return cb.array();
	}
}
