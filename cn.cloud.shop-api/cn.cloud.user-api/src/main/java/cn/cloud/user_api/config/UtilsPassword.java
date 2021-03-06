package cn.cloud.user_api.config;

import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;

import com.alibaba.druid.filter.config.ConfigTools;

/**
 * @author Dream
 *	druid  密码加密
 */
public class UtilsPassword {
	
	public static void main(String[] args) {
		String passwordString = "123456";
		try {
			String[] arr = ConfigTools.genKeyPair(512);
			
			System.out.println("publickey: " + arr[1]);
			System.out.println("password: " +ConfigTools.encrypt(arr[0], passwordString));
		} catch (NoSuchAlgorithmException | NoSuchProviderException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
