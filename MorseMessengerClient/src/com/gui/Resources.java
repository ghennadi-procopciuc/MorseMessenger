package com.gui;

public class Resources {
	public static String CONFIG_FILE = "morse.conf";

	public static String MORSE_ICON = "resources/morse_mini.png";
	public static String MORSE_IMAGE = "resources/morse.png";
	public static String GROUP_ICON = "resources/group.gif";
	public static String CHECK_ICON = "resources/check.png";
	public static String DEFAULT_AVATAR_IMAGE = "resources/default_avatar.png";
	public static String DEFAULT_BIG_AVATAR_IMAGE = "resources/big_default_avatar.png";
	public static String LOGIN_IMAGE = "resources/login_morse.png";

	public static String MAIN_MENU = "Menu";
	public static String REMOVE_FRIEND_OPTION = "Remove Friend";
	public static String INITIATE_CHAT_OPTION = "Initiate chat";
	public static String SIGN_OUT_OPTION = "Sign Out";
	public static String ADD_FRIEND_OPTION = "Add friend";
	public static String VIEW_ARCHIVE_OPTION = "View archieve";
	public static String QUIT_OPTION = "Quit";

	public static String EDIT_MENU = "Edit";
	public static String PROFILE_OPTION = "Profile";
	public static String GROUP_MANAGMENT_MENU = "Group Managment";
	public static String ADD_GROUP_OPTION = "Add group";
	public static String REMOVE_GROUP_OPTION = "Remove group";
	public static String ADD_FRIEND_TO_GROUP_MENU = "Move to";

	public static String HELP_MENU = "Help";
	public static String MORSE_ALPHABET_OPTION = "Morse Alphabet";

	public static String PREFERENCES_OPTION = "Preferences";

	public static String INIT_GROUP_NAME = "INIT";
	public static String SENT_REQUEST_GROUP_NAME = "__SENT_REQUEST";
	public static String DEFAULT_GROUP_NAME = "Init";
	public static String INCOMING_REQUESTS_GROUP = "__INCOMING_REQUEST";

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

	public static String[] countries = { "Afghanistan", "Albania", "Algeria",
			"American Samoa", "Andorra", "Angola", "Anguilla",
			"Antigua and Barbuda", "Argentina", "Armenia", "Aruba",
			"Australia", "Austria", "Azerbaijan", "Bahamas", "Bahrain",
			"Bangladesh", "Barbados", "Belarus", "Belgium", "Belize", "Benin",
			"Bermuda", "Bhutan", "Bolivia", "Bosnia-Herzegovina", "Botswana",
			"Bouvet Island", "Brazil", "Brunei", "Bulgaria", "Burkina Faso",
			"Burundi", "Cambodia", "Cameroon", "Canada", "Cape Verde",
			"Cayman Islands", "Central African Republic", "Chad", "Chile",
			"China", "Christmas Island", "Cocos Islands", "Colombia",
			"Comoros", "Congo", "Congo, Republic of", "Cook Islands",
			"Costa Rica", "Croatia", "Cuba", "Cyprus", "Czech Republic",
			"Denmark", "Djibouti", "Dominica", "Dominican Republic", "Ecuador",
			"Egypt", "El Salvador", "Equatorial Guinea", "Eritrea", "Estonia",
			"Ethiopia", "Falkland Islands", "Faroe Islands", "Fiji", "Finland",
			"France", "French Guiana", "Gabon", "Gambia", "Georgia", "Germany",
			"Ghana", "Gibraltar", "Greece", "Greenland", "Grenada",
			"Guadeloupe (French)", "Guam (USA)", "Guatemala", "Guinea",
			"Guinea Bissau", "Guyana", "Haiti", "Holy See", "Honduras",
			"Hong Kong", "Hungary", "Iceland", "India", "Indonesia", "Iran",
			"Iraq", "Ireland", "Israel", "Italy", "Ivory Coast", "Jamaica",
			"Japan", "Jordan", "Kazakhstan", "Kenya", "Kiribati", "Kuwait",
			"Kyrgyzstan", "Laos", "Latvia", "Lebanon", "Lesotho", "Liberia",
			"Libya", "Liechtenstein", "Lithuania", "Luxembourg", "Macau",
			"Macedonia", "Madagascar", "Malawi", "Malaysia", "Maldives",
			"Mali", "Malta", "Marshall Islands", "Martinique", "Mauritania",
			"Mauritius", "Mayotte", "Mexico", "Micronesia", "Moldova",
			"Monaco", "Mongolia", "Montenegro", "Montserrat", "Morocco",
			"Mozambique", "Myanmar", "Namibia", "Nauru", "Nepal",
			"Netherlands", "Netherlands Antilles", "New Caledonia",
			"New Zealand", "Nicaragua", "Niger", "Nigeria", "Niue",
			"Norfolk Island", "North Korea", "Northern Mariana Islands",
			"Norway", "Oman", "Pakistan", "Palau", "Panama",
			"Papua New Guinea", "Paraguay", "Peru", "Philippines",
			"Pitcairn Island", "Poland", "Polynesia", "Portugal",
			"Puerto Rico", "Qatar", "Reunion", "Romania", "Russia", "Rwanda",
			"Saint Helena", "Saint Kitts and Nevis", "Saint Lucia",
			"Saint Pierre and Miquelon", "Saint Vincent and Grenadines",
			"Samoa", "San Marino", "Sao Tome and Principe", "Saudi Arabia",
			"Senegal", "Serbia", "Seychelles", "Sierra Leone", "Singapore",
			"Slovakia", "Slovenia", "Solomon Islands", "Somalia",
			"South Africa", "South Georgia and South Sandwich Islands",
			"South Korea", "Spain", "Sri Lanka", "Sudan", "Suriname",
			"Svalbard and Jan Mayen Islands", "Swaziland", "Sweden",
			"Switzerland", "Syria", "Taiwan", "Tajikistan", "Tanzania",
			"Thailand", "East Timor", "Togo", "Tokelau", "Tonga",
			"Trinidad and Tobago", "Tunisia", "Turkey", "Turkmenistan",
			"Turks and Caicos Islands", "Tuvalu", "Uganda", "Ukraine",
			"United Arab Emirates", "United Kingdom", "United States",
			"Uruguay", "Uzbekistan", "Vanuatu", "Venezuela", "Vietnam",
			"Virgin Islands", "Wallis and Futuna Islands", "Yemen", "Zambia",
			"Zimbabwe" };

}
