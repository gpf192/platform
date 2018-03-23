package cn.xsdzq.platform.util;

import java.util.Date;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtBuilder;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

public class JwtHelper {

	private static final String key = "secretkey";

	public static Claims parseJWT(String jsonWebToken) {
		try {
			Claims claims = Jwts.parser().setSigningKey(key).parseClaimsJws(jsonWebToken).getBody();
			return claims;
		} catch (Exception ex) {
			return null;
		}
	}

	public static String createJWT(String userId, String name, int expireInMillis) {
		SignatureAlgorithm signatureAlgorithm = SignatureAlgorithm.HS256;

		long nowMillis = System.currentTimeMillis();
		Date now = new Date(nowMillis);

		long expMillis = nowMillis + expireInMillis;
		Date exp = new Date(expMillis);

		// 添加构成JWT的参数
		JwtBuilder builder = Jwts.builder().setId(userId).setSubject(name).setIssuedAt(now).setExpiration(exp)
				.signWith(signatureAlgorithm, key);
		return builder.compact();
	}
}
