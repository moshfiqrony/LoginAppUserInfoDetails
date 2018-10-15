package moshfiqrony.com.mysql2;

import android.content.Context;
import android.util.Log;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

public class SqlMain {
    static private final String connectString = "http://192.168.1.3/mysql2/conn.php";
    static private final String updateString = "http://192.168.1.3/mysql2/update.php";
    static private final String reguser = "http://192.168.1.3/mysql2/regUser.php";

    public static String getConnectString() {
        return connectString;
    }

    public static String getUpdateString() {
        return updateString;
    }

    public static String getReguser() {
        return reguser;
    }
}
