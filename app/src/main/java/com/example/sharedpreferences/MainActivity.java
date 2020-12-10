package com.example.sharedpreferences;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

/**
 * The type Main activity.
 *
 * @author Dean David <dd0167@bs.amalnet.k12.il>
 * @version 1.0
 * @since 10/12/2020
 */
public class MainActivity extends AppCompatActivity {

    EditText et;
    TextView tv;
    int num=0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        et=(EditText) findViewById(R.id.et);
        tv=(TextView) findViewById(R.id.tv);

        try {
            SharedPreferences settings=getSharedPreferences("TEXT_NUMBER",MODE_PRIVATE);
            num=settings.getInt("Number",0);
            String text=settings.getString("Text","Enter Text");
            tv.setText(String.valueOf(num));
            et.setText(text);
        }
        catch (Exception e) {
        }
    }

    /**
     * Count - Counter promotes.
     * @param view the view
     */
    public void count(View view) {
        num++;
        tv.setText(String.valueOf(num));
    }

    /**
     * Reset - Resets the counter.
     * @param view the view
     */
    public void reset(View view) {
        num=0;
        tv.setText(String.valueOf(num));
    }

    /**
     * Exit - Saves the data and closes the program.
     * @param view the view
     */
    public void exit(View view) {
        String st=et.getText().toString();
        SharedPreferences settings=getSharedPreferences("TEXT_NUMBER",MODE_PRIVATE);
        SharedPreferences.Editor editor=settings.edit();
        editor.putInt("Number",num);
        editor.putString("Text",st);
        editor.commit();
        System.exit(0);
    }

    /**
     * Create Options menu
     * @param menu the menu
     */
    @Override
    public boolean onCreateOptionsMenu(Menu menu)
    {
        getMenuInflater().inflate(R.menu.main, menu);
        menu.add("Credits");
        return true;
    }

    /**
     * Click in Options menu
     * @param item the item
     */
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        String st=item.getTitle().toString();
        if (st.equals("Credits"))
        {
            Intent in=new Intent(this,CreditsActivity.class);
            startActivity(in);
        }
        return true;
    }
}