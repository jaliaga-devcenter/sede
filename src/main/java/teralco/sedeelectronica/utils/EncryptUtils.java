package teralco.sedeelectronica.utils;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.security.InvalidKeyException;
import java.security.Key;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.spec.SecretKeySpec;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class EncryptUtils {

	public EncryptUtils() {
	}

	private static String fileStrKey;
	private static Key aesKey;

	@Autowired
	@Value("${file.strKey}")
	public void testValue(String value) {
		fileStrKey = value;
		aesKey = new SecretKeySpec(fileStrKey.getBytes(), "AES");
	}

	public static String encrypt(String strClearText) {

		Cipher cipher;
		byte[] encrypted = null;
		try {
			cipher = Cipher.getInstance("AES");
			cipher.init(Cipher.ENCRYPT_MODE, aesKey);
			encrypted = cipher.doFinal(strClearText.getBytes());
		} catch (InvalidKeyException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (NoSuchAlgorithmException | NoSuchPaddingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalBlockSizeException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (BadPaddingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		Base64.Encoder encoder = Base64.getEncoder();
		String encryptedString = encoder.encodeToString(encrypted);
		try {
			encryptedString = URLEncoder.encode(encryptedString, "UTF-8");
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return encryptedString;
	}

	public static String decrypt(String strEncrypted) throws Exception {
		String decodedUrl = URLDecoder.decode(strEncrypted, "UTF-8");

		Base64.Decoder decoder = Base64.getDecoder();
		Cipher cipher = Cipher.getInstance("AES");
		cipher.init(Cipher.DECRYPT_MODE, aesKey);
		String decrypted = new String(cipher.doFinal(decoder.decode(decodedUrl)));
		return decrypted;
	}
}
