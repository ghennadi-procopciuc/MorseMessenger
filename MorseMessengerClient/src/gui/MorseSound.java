package gui;

import java.awt.Button;
import java.awt.Choice;
import java.awt.TextArea;

import sun.audio.AudioData;
import sun.audio.AudioDataStream;
import sun.audio.AudioPlayer;

public class MorseSound {
	byte[] sample = null;
	private AudioData playable = null;
	private Button playButton;
	private Button transButton;
	private TextArea input;
	private Choice wpmChoice;
	private TextArea output;
	private int wpm = 15;
	private String morse;
	private String text;

	public MorseSound(String morse) {
		char c;
		int dits = 0, dahs = 0, spcs = 0, slashes = 0;
		int dahLen = 3;
		int pause1 = 1;
		int pause2 = 3;
		int pause3 = 7;
		int wordLen = 50;
		int ditLen = (int) 1000 * 60 / (wpm * wordLen);
		// thousandths of a sec per dit, or 8-byte frames per dit

		for (int i = 0; i < morse.length(); i++) {
			c = morse.charAt(i);
			// status += c;
			switch (c) {
			case '.':
				dits++;
				break;
			case '-':
				dahs++;
				break;
			case ' ':
				spcs++;
				break;
			case '/':
				slashes++;
				break;
			// need default
			}
		}
		// status += " "+dits+" "+dahs+" "+spcs+" "+slashes;
		int length = ditLen
				* 8
				* (dits * (1 + pause1) + dahs * (dahLen + pause1) + spcs
						* (pause2 - pause1) + slashes * (pause3 - pause1));

		sample = new byte[length];

		int sPos = 0;
		for (int i = 0; i < morse.length(); i++) {
			c = morse.charAt(i);
			switch (c) {
			case '.':
				addSound(1, sPos, ditLen);
				sPos += (1 + pause1) * ditLen * 8;
				break;
			case '-':
				addSound(dahLen, sPos, ditLen);
				sPos += (dahLen + pause1) * ditLen * 8;
				break;
			case ' ':
				sPos += (pause2 - pause1) * ditLen * 8;
				break;
			case '/':
				sPos += (pause3 - pause1) * ditLen * 8;
				break;
			}
		}
		playable = new AudioData(sample);
	}

	public void playSample() {
		AudioDataStream stream = new AudioDataStream(playable);
		AudioPlayer.player.start(stream);
	}

	private void addSound(int units, int sPos, int ditLen) {
		for (int i = 0; i < units * ditLen; i++) {
			sample[sPos + i * 8] = (byte) 0xA7;
			sample[sPos + i * 8 + 1] = (byte) 0x81;
			sample[sPos + i * 8 + 2] = (byte) 0xA7;
			sample[sPos + i * 8 + 3] = (byte) 0;
			sample[sPos + i * 8 + 4] = (byte) 0x59;
			sample[sPos + i * 8 + 5] = (byte) 0x7F;
			sample[sPos + i * 8 + 6] = (byte) 0x59;
			sample[sPos + i * 8 + 7] = (byte) 0;
		}
	}

	public static void main(String[] args) {
		MorseCodes mc = new MorseCodes();
		MorseSound ms = new MorseSound(mc.toMorse("Somnoroase"));
		ms.playSample();
		System.out.println("AM TERMINAT");
	}
}
