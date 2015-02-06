package com.example.mysqltest;

import java.util.ArrayList;
import java.util.HashMap;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import android.app.ListActivity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.SimpleAdapter;

public class SearchBook extends ListActivity {

    // Progress Dialog
    private ProgressDialog pDialog;

    // php read comments script

    // localhost :
    // testing on your device
    // put your local ip instead, on windows, run CMD > ipconfig
    // or in mac's terminal type ifconfig and look for the ip under en0 or en1
    // private static final String READ_COMMENTS_URL =
    // "http://xxx.xxx.x.x:1234/webservice/comments.php";

    // testing on Emulator:
    //private static final String READ_COMMENTS_URL = "http://10.0.2.2:1234/webservice/comments.php";

    // testing from a real server:
    //private static final String READ_COMMENTS_URL = "http://www.theartistandtheengineer.co/webservice/comments.php";
    private static final String READ_COMMENTS_URL = "http://192.168.0.108/search.php";

    // JSON IDS:
    private static final String TAG_RESULT_COUNT = "result_count";
    private static final String TAG_TITLE = "title";
    private static final String TAG_BEGIN = "begin";
    private static final String TAG_MESSAGE = "message";
    private static final String TAG_INDEX_SEARCHED = "index_searched";
    private static final String TAG_PAGE_COUNT = "page_count";
    private static final String TAG_DATA = "data";
    private static final String TAG_ISBN13 = "isbn13";
    private static final String TAG_AUTHOR_DATA = "author_data";
    private static final String TAG_AUTHOR_NAME = "name";
    private static final String TAG_AUTHOR_ID = "id";
    // it's important to note that the message is both in the parent branch of
    // our JSON tree that displays a "Post Available" or a "No Post Available"
    // message,
    // and there is also a message for each individual post, listed under the
    // "posts"
    // category, that displays what the user typed as their message.

    // An array of all of our comments
    private JSONArray mBooks = null;
    // manages all of our comments in a list.
    private ArrayList<HashMap<String, String>> mBookList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // note that use read_comments.xml instead of our single_post.xml
        setContentView(R.layout.read_comments);
    }

    @Override
    protected void onResume() {
        // TODO Auto-generated method stub
        super.onResume();
        // loading the comments via AsyncTask
        new LoadBooks().execute();
    }


    public void addComment(View v) {
        Intent i = new Intent(SearchBook.this, AddComment.class);
        startActivity(i);
    }

    /**
     * Retrieves recent post data from the server.
     */
    public void updateJSONdata() {

        // Instantiate the arraylist to contain all the JSON data.
        // we are going to use a bunch of key-value pairs, referring
        // to the json element name, and the content, for example,
        // message it the tag, and "I'm awesome" as the content..

        mBookList = new ArrayList<HashMap<String, String>>();

        // Bro, it's time to power up the J parser
        JSONParser jParser = new JSONParser();
        // Feed the beast our comments url, and it spits us
        // back a JSON object. Boo-yeah Jerome.
        JSONObject json = jParser.getJSONFromUrl(READ_COMMENTS_URL);

        // when parsing JSON stuff, we should probably
        // try to catch any exceptions:
        try {

            // I know I said we would check if "Posts were Avail." (success==1)
            // before we tried to read the individual posts, but I lied...
            // mBooks will tell us how many "posts" or comments are
            // available
            mBooks = json.getJSONArray(TAG_DATA);

            // looping through all posts according to the json object returned
            //for (int i = 0; i < mBooks.length(); i++) {
            for (int i = 0; i < mBooks.length(); i++) {
                JSONObject c = mBooks.getJSONObject(i);

                // gets the content of each tag
                String title = c.getString(TAG_TITLE);
                String isbn13 = c.getString(TAG_ISBN13);
                //String username = c.getString(TAG_AUTHOR_DATA);

                // creating new HashMap
                HashMap<String, String> map = new HashMap<String, String>();

                map.put(TAG_TITLE, title);
                map.put(TAG_ISBN13, isbn13);
                //map.put(TAG_USERNAME, username);

                // adding HashList to ArrayList
                mBookList.add(map);

                // annndddd, our JSON data is up to date same with our array
                // list
            }

        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    /**
     * Inserts the parsed data into the listview.
     */
    private void updateList() {
        // For a ListActivity we need to set the List Adapter, and in order to do
        //that, we need to create a ListAdapter.  This SimpleAdapter,
        //will utilize our updated Hashmapped ArrayList,
        //use our single_post xml template for each item in our list,
        //and place the appropriate info from the list to the
        //correct GUI id.  Order is important here.
        ListAdapter adapter = new SimpleAdapter(this, mBookList,
                R.layout.single_book, new String[] { TAG_TITLE, TAG_ISBN13},
                new int[] { R.id.title, R.id.isbn13});

        // I shouldn't have to comment on this one:
        setListAdapter(adapter);

        // Optional: when the user clicks a list item we
        //could do something.  However, we will choose
        //to do nothing...
        ListView lv = getListView();
        lv.setOnItemClickListener(new OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View view,
                                    int position, long id) {

                // This method is triggered if an item is click within our
                // list. For our example we won't be using this, but
                // it is useful to know in real life applications.

            }
        });
    }

    public class LoadBooks extends AsyncTask<Void, Void, Boolean> {

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            pDialog = new ProgressDialog(SearchBook.this);
            pDialog.setMessage("Loading Books...");
            pDialog.setIndeterminate(false);
            pDialog.setCancelable(true);
            pDialog.show();
        }

        @Override
        protected Boolean doInBackground(Void... arg0) {
            updateJSONdata();
            return null;

        }

        @Override
        protected void onPostExecute(Boolean result) {
            super.onPostExecute(result);
            pDialog.dismiss();
            updateList();
        }
    }
}




/*package com.example.mysqltest;

import java.util.ArrayList;
import java.util.List;
import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONException;
import org.json.JSONObject;
import android.app.Activity;
import android.os.Bundle;
import android.os.StrictMode;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class SearchBook extends Activity {

    private BookJSONParser jsonParser;
    //10.0.2.2 is the address used by the Android emulators to refer to the host address
    // change this to the IP of another host if required
    private static String searchURL = "http://192.168.0.108/search.php";
    private static String getBooks = "getBooks";
    private static String jsonResult = "success";
    String bookData;
    String age_res; // ????? Is this needed?
    TextView Results;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.book_results);
        StrictMode.enableDefaults();

        //Invoke the Json Parser
        jsonParser = new BookJSONParser();


        final EditText searchField = (EditText) findViewById(R.id.searchField);
        Results = (TextView) findViewById(R.id.Results);
        Button GetBooks = (Button) findViewById(R.id.GetBooks);

        //Get Books Button
        GetBooks.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {

                //Get the contents of the edit text
                bookData = searchField.getText().toString();


                //Pass the book data to the JSON method and create a JSON object from the return
                JSONObject json = getBooks(bookData);

                // check the success of the JSON call
                try {
                    if (json.getString(jsonResult) != null) {
                        Results.setText("");
                        String res = json.getString(jsonResult);
                        if(Integer.parseInt(res) == 1){
                            //If it's a success create a new JSON object for the user element
                            JSONObject json_message = json.getJSONObject("message");
                            //Set the results text to the age from the above JSON object
                            Results.setText("Message: " + json_message.getString("message"));

                        }else{
                            //If the user could not be found
                            Results.setText("Message could not be found");
                        }
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }


            }


        });

    }

    //The below passes the contents and the user name over to the JSON parser class
    public JSONObject getBooks(String message){
        List<NameValuePair> params = new ArrayList<NameValuePair>();
        params.add(new BasicNameValuePair("contents", getBooks));
        params.add(new BasicNameValuePair("message", message));
        JSONObject json = jsonParser.getJSONFromUrl(searchURL, params);
        return json;
    }

}*/