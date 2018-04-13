package farhadarts.birdwatching;

import android.os.AsyncTask;
import android.util.Log;

import java.io.IOException;

/**
 * Created by art_i on 4/12/2018.
 */

public class ReadHttpTask extends AsyncTask<String, Void, CharSequence> {
    @Override
    protected CharSequence doInBackground(String... urls) {
        String urlString = urls[0];
        try {
            CharSequence result = HttpHelper.GetHttpResponse(urlString);
            return result;
        } catch (IOException ex) {
            cancel(true);
            String errorMessage = ex.getMessage() + "\n" + urlString;
            Log.e("BOOK", errorMessage);
            return errorMessage;
        }
    }
}