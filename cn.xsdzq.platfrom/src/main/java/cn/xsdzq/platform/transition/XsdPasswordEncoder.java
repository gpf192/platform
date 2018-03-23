package cn.xsdzq.platform.transition;

import org.springframework.security.crypto.password.PasswordEncoder;

public class XsdPasswordEncoder implements PasswordEncoder {

	@Override
	public String encode(CharSequence rawPassword) {
		// TODO Auto-generated method stub
		return rawPassword.toString();
	}

	@Override
	public boolean matches(CharSequence rawPassword, String encodedPassword) {
		// TODO Auto-generated method stub
		return rawPassword.equals(encodedPassword);
	}

}
