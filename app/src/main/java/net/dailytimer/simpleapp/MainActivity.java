package net.dailytimer.simpleapp;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.os.AsyncTask;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

	private TestTask testTask0,testTask1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        
        testTask0 = testTask1 = null;
        
        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
    }

	@Override
	protected void onResume()
	{
		super.onResume();
		testTask0 = new TestTask(R.id.line0);
		testTask0.execute();
		testTask1 = new TestTask(R.id.line1);
		testTask1.execute();
		// next method can fix this problem but it should not be necessary
		// testTask1.executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR);
	}

	@Override
	public void onPause()
	{
		super.onPause();
		if(testTask0 != null)
		{
			testTask0.cancel(false);
			testTask0 = null;
		}
		if(testTask1 != null)
		{
			testTask1.cancel(false);
			testTask1 = null;
		}
	}

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

	public class TestTask extends AsyncTask<Void,Integer,Boolean>
	{
		private TextView textView;
		private int value = 0;
		public TestTask(int id)
		{
			textView = (TextView)findViewById(id);
		}

		@Override
		protected void onPreExecute()
		{
		}

		@Override
		protected Boolean doInBackground(Void... parms)
		{
			while(true)
			{
				try
				{
					Thread.sleep(200);
				}
				catch(InterruptedException ie)
				{
					//
				}
				publishProgress(0);
				if(isCancelled())
				{
					break;
				}
			}
			return true;
		}

		@Override
		protected void onProgressUpdate(Integer... progress)
		{
			textView.setText("" + value++);
		}

		@Override
		protected void onCancelled()
		{
		}

		@Override
		protected void onPostExecute(Boolean result)
		{
		}
	}
}
