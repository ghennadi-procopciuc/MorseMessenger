package gui;

public class Resources {
	public static String CONFIG_FILE = "morse.conf";

	public static String MORSE_ICON = "resources/morse_mini.png";
	public static String MORSE_IMAGE = "resources/morse.png";
	public static String GROUP_ICON = "resources/group.gif";
	public static String CHECK_ICON = "resources/check.png";

	public static String MAIN_MENU = "Menu";
	public static String REMOVE_FRIEND_OPTION = "Remove Friend";
	public static String INITIATE_CHAT_OPTION = "Initiate chat";
	public static String SIGN_OUT_OPTION = "Sign Out";
	public static String ADD_FRIEND_OPTION = "Add friend";
	public static String QUIT_OPTION = "Quit";

	public static String EDIT_MENU = "Edit";
	public static String PROFILE_OPTION = "Profile";
	public static String GROUP_MANAGMENT_OPTION = "Group Managment";

	public static String HELP_MENU = "Help";
	public static String MORSE_ALPHABET_OPTION = "Morse Alphabet";

	public static String PREFERENCES_OPTION = "Preferences";
	public static int MIN_FONT_SIZE = 7;
	public static int MAX_FONT_SIZE = 30;

	public static Integer ASCII_MODE = 0;
	public static Integer MORSE_MODE = 1;

	public static String[] morseCodes = { ".-", ".---", "...", ".----",
			".-.-.-", "---...", "-...", "-.-", "-", "..---", "--..--",
			"-.-.-.", "-.-.", ".-..", "..-", "...--", "..--..", "-...-", "-..",
			"--", "...-", "....-", ".----.", ".-.-.", ".", "-.", ".--",
			".....", "-.-.--", "-....-", "..-.", "---", "-..-", "-....",
			"-..-.", "..--.-", "--.", ".--.", "-.--", "--...", "-.--.",
			".-..-.", "....", "--.-", "--..", "---..", "-.--.-", "...-..-",
			"..", ".-.", "-----", "----.", ".-...", ".--.-.", "   " };

	public static String[] asciiCodes = { "A", "J", "S", "1", ".", ":", "B",
			"K", "T", "2", ",", ";", "C", "L", "U", "3", "?", "=", "D", "M",
			"V", "4", "'", "+", "E", "N", "W", "5", "!", "-", "F", "O", "X",
			"6", "/", "_", "G", "P", "Y", "7", "(", "\"", "H", "Q", "Z", "8",
			")", "$", "I", "R", "0", "9", "&", "@", " " };
}
