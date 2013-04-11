package com.gui;

import java.util.StringTokenizer;
import java.util.TreeMap;

public class MorseCodes {

	public TreeMap<String, String> asciiToMorse;
	public TreeMap<String, String> morseToAscii;

	public MorseCodes() {
		asciiToMorse = new TreeMap<String, String>();
		morseToAscii = new TreeMap<String, String>();

		for (int i = 0; i < Resources.morseCodes.length; i++) {
			asciiToMorse.put(Resources.asciiCodes[i], Resources.morseCodes[i]);
			morseToAscii.put(Resources.morseCodes[i], Resources.asciiCodes[i]);
		}
	}

	private String getAsciiCode(String morseCode) {
		if (!morseToAscii.containsKey(morseCode)) {
			return null;
		}

		return morseToAscii.get(morseCode);
	}

	private String getMorseCode(String asciiCode) {
		asciiCode = asciiCode.toUpperCase();

		if (!asciiToMorse.containsKey(asciiCode)) {
			return null;
		}

		return asciiToMorse.get(asciiCode) + " ";
	}

	public String toMorse(String message) {
		String morseMessage = "";
		String morsePartial;

		for (int i = 0; i < message.length(); i++) {
			morsePartial = getMorseCode("" + message.charAt(i));

			/* Verific daca am primit un cod de eroare */
			if (morsePartial == null) {
				return null;
			}

			morseMessage += morsePartial;
		}

		return morseMessage;
	}

	public String toAscii(String morseMessage) {
		String message = "";
		String token;
		String code;
		Boolean space;

		morseMessage = morseMessage.replaceAll("    ", "*");
		morseMessage = morseMessage.replaceAll(" ", "~");
		morseMessage = morseMessage.replaceAll("\\*", " ");

		System.out.println("Mesaj Final : " + morseMessage);
		StringTokenizer st = new StringTokenizer(morseMessage, "~");

		while (st.hasMoreTokens()) {
			space = false;

			token = st.nextToken();
			if (token.trim().length() < token.length()) {
				space = true;
				token = token.trim();
			}
			System.out.println(token);

			code = getAsciiCode(token);
			if (code == null) {
				new Error("Parse error :  " + token);
				return null;
			} else {
				message += code;
				if (space) {
					message += " ";
					space = false;
				}
			}
		}

		return message;
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		String message = "Liviu are multe mere";
		String morseCode;

		MorseCodes codes = new MorseCodes();

		morseCode = codes.toMorse(message);
		System.out.println(morseCode);
		System.out.println(codes.toAscii(morseCode));
	}
}
