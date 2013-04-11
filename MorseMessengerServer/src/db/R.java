package db;

import java.util.Vector;


public class R {
    //Title
    public static final String TITLE = "MorseMessenger";

    
    //Database connection parameters
    public static final String SERVER 		    = "192.168.61.50";
    //public static final String SERVER 	    = "localhost";
    public static final String PORT 			= "5432";
    public static final String DATABASE 		= "postgres";
    public static final String DB_USERNAME 		= "postgres";
    public static final String DB_PASSWORD 		= "saredemasa";
    /* for sql */
    public static final String USERS_TABLE		= "users";
    public static final String HOBBIES_TABLE	= "hobbies";
    public static final String GROUPS_TABLE 	= "groups";
    public static final String WWLABELS_TABLE	= "wwlabels";
    public static final String WWPINS_TABLE		= "wwpins";
    public static final String ARCHIVE_TABLE	= "archive";
    public static final String ALL		 		= "*";

    /* users table */
    /* number of columns */
    public static final int	   USERS_COLS	= 8;
    public static final String PASSWORD 	= "password";
    public static final String USERNAME 	= "username";
    public static final String NAME     	= "name";
    public static final String SURNAME  	= "surname";
    public static final String LOCATION 	= "location";
    public static final String EMAIL      	= "email";
    public static final String BIRTH_DATE 	= "birth_date";
    public static final String IMAGE 		= "image";
    
    public static final String USERS_TABLE_ALL = String.format(
    		"%s,%s,%s,%s,%s," +
    		"%s,%s,%s",
    		USERNAME, PASSWORD, NAME, SURNAME, LOCATION, 
    		EMAIL, BIRTH_DATE, IMAGE);

    /* hobbies table */
    /* number of columns */
    public static final int	   HOBBIES_COLS	= 2;
    public static final String HOBBY = "hobby";
    /* groups table */
    /* number of columns */
    public static final String INIT_GROUP = "INIT";
    public static final String __SENT_REQUEST = "__SENT_REQUEST";
    public static final String __INCOMING_REQUEST = "__INCOMING_REQUEST";
    public static final String NULL_VALUE = "_null_";
    public static final int	   GROUPS_COLS	= 3;
    /*this is the name of the group name entry in the groups table */
    public static final String GROUPNAME 	= "groupname";
    public static final String FRIENDNAME 	= "friendname";
    public static final String FRIENDS 		= "Friends";
    /* wwlabels table */
    /* number of columns */
    public static final int	   WWLABELS_COLS	= 4;
    public static final String LAT 	= "lat";
    public static final String LONG = "long";
    public static final String TEXT = "text";
    /* wwpins table */
    /* number of columns */
    public static final int	   WWPINS_COLS	= 4;
    public static final String COLOR = "color";
    /* archive table */
    /* number of columns */
    public static final int	   ARCHIVE_COLS	= 4;
    public static final String SENDER 	= "sender";
    public static final String RECEIVER = "receiver";
    public static final String MESSAGE 	= "message";
    public static final String DATE 	= "date";
    
    /* user status */
    public static final Integer USER_ONLINE		= 0;
    public static final Integer USER_OFFLINE	= 1;
        
    public static Vector<String> getTablePrimaryKeys(String tableName){
        String primaryKey = "";
        Vector<Vector<String>> columns;

        Vector<String> readOnlyColumns = new Vector<String>();

        DBConnection connection = new DBConnection();
          
        primaryKey =
            "SELECT" +
            "      K.COLUMN_NAME  " +
            "FROM " +
            "     INFORMATION_SCHEMA.TABLE_CONSTRAINTS T" +
            "     INNER JOIN" +
            "     INFORMATION_SCHEMA.KEY_COLUMN_USAGE K" +
            "     ON T.CONSTRAINT_NAME = K.CONSTRAINT_NAME  " +
            "WHERE" +
            "      T.CONSTRAINT_TYPE = 'PRIMARY KEY'" +
            "      AND T.TABLE_NAME = '" + tableName +    "' " +
            "ORDER BY " +
            "        K.ORDINAL_POSITION";


        columns = connection.select(primaryKey, 1);

        for(int i = 0; i < columns.size(); i++){
            readOnlyColumns.add(columns.elementAt(i).elementAt(0));
        }

        return readOnlyColumns;       
    }
}