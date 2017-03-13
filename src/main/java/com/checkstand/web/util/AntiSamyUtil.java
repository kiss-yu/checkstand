package com.checkstand.web.util;

import org.owasp.validator.html.AntiSamy;
import org.owasp.validator.html.CleanResults;
import org.owasp.validator.html.Policy;

public class AntiSamyUtil {
	public static String getCleanHTML(String data) {
		AntiSamy antiSamy = new AntiSamy();
		try {
			Policy policy = Policy.getInstance(AntiSamy.class.getResource("/antisamy-myspace.xml").openStream());
			CleanResults cleanResults = antiSamy.scan(data, policy);
			return cleanResults.getCleanHTML();
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
}
