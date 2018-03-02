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

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import teralco.sedeelectronica.exception.ExceptionType;
import teralco.sedeelectronica.exception.SedeElectronicaException;

@Component
public class EncryptUtils {

	@Value("${file.strKey}")
	private static String fileStrKey;
	private static final Key aesKey =new SecretKeySpec(fileStrKey.getBytes(), "AES");
	
	private EncryptUtils() {
		
	}

	public static String encrypt(String strClearText) {

		Cipher cipher = null;
		byte[] encrypted = null;
		try {
			cipher = Cipher.getInstance("AES");
		} catch (NoSuchAlgorithmException | NoSuchPaddingException e) {
			throw new SedeElectronicaException(ExceptionType.UNEXPECTED, e);
		}

		try {
			cipher.init(Cipher.ENCRYPT_MODE, aesKey);
		} catch (InvalidKeyException e) {
			throw new SedeElectronicaException(ExceptionType.UNEXPECTED, e);
		}

		try {
			encrypted = cipher.doFinal(strClearText.getBytes());
		} catch (IllegalBlockSizeException | BadPaddingException e) {
			throw new SedeElectronicaException(ExceptionType.UNEXPECTED, e);
		}

		Base64.Encoder encoder = Base64.getEncoder();
		String encryptedString = encoder.encodeToString(encrypted);
		try {
			encryptedString = URLEncoder.encode(encryptedString, "UTF-8");
		} catch (UnsupportedEncodingException e) {
			throw new SedeElectronicaException(ExceptionType.UNEXPECTED, e);
		}

		return encryptedString;
	}

	public static String decrypt(String strEncrypted) {
		String decodedUrl = null;
		try {
			decodedUrl = URLDecoder.decode(strEncrypted, "UTF-8");
		} catch (UnsupportedEncodingException e) {
			throw new SedeElectronicaException(ExceptionType.UNEXPECTED, e);
		}

		Base64.Decoder decoder = Base64.getDecoder();
		Cipher cipher = null;
		try {
			cipher = Cipher.getInstance("AES");
		} catch (NoSuchAlgorithmException e) {
			throw new SedeElectronicaException(ExceptionType.UNEXPECTED, e);
		} catch (NoSuchPaddingException e) {
			throw new SedeElectronicaException(ExceptionType.UNEXPECTED, e);
		}
		try {
			cipher.init(Cipher.DECRYPT_MODE, aesKey);
		} catch (InvalidKeyException e) {
			throw new SedeElectronicaException(ExceptionType.UNEXPECTED, e);
		}

		String decrypted;

		try {
			decrypted = new String(cipher.doFinal(decoder.decode(decodedUrl)));
		} catch (IllegalBlockSizeException | BadPaddingException e) {
			throw new SedeElectronicaException(ExceptionType.UNEXPECTED, e);
		}
	
		return decrypted;
	}
}
