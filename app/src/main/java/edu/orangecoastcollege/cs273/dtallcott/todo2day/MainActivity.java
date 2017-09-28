package edu.orangecoastcollege.cs273.dtallcott.todo2day;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity
{
    private List<Task> mAllTasksList = new ArrayList<>();
    public static final String TAG = MainActivity.class.getSimpleName();

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //clear the existing database
        deleteDatabase(DBHelper.DATABASE_NAME);

        //pre-populate the list with 4 tasks
        mAllTasksList.add(new Task("Study for the Midterm", false));
        mAllTasksList.add(new Task("Sleep", false));
        mAllTasksList.add(new Task("Eat Dinner", true));
        mAllTasksList.add(new Task("Play Hockey", false));

        //Instantiate a new DBHelper
        DBHelper db = new DBHelper(this);

        //Loop through the list and add each one to the database
        for (Task t : mAllTasksList)
        {
            db.addTask(t);
        }

        //Clear out the list, then rebuild from the databse this time
        mAllTasksList.clear();

        //Retrieve the tasks from the database
        mAllTasksList = db.getAllTasks();

        //Loop through each of the tasks and print them to the Log.i
        Log.i(TAG, "Showing All Tasks");
        for (Task t : mAllTasksList)
        {
            Log.i(TAG, t.toString());
        }
        Log.i(TAG, "After Deleting Task 4");
        db.deleteTask(mAllTasksList.get(3));
        mAllTasksList = db.getAllTasks();
        for (Task t : mAllTasksList)
        {
            Log.i(TAG, t.toString());
        }
    }
}
