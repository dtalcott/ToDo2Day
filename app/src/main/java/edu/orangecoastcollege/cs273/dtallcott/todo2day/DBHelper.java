package edu.orangecoastcollege.cs273.dtallcott.todo2day;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by dtallcott on 9/28/2017.
 */

class DBHelper extends SQLiteOpenHelper {

    //Create some useful database constants
    public static final String DATABASE_NAME = "ToDo2Day";
    public static final String DATABASE_TABLE = "Tasks";
    public static final int DATABASE_VERSION = 1;

    //create some useful table constants
    public static final String KEY_FIELD_ID = "_id";
    public static final String FIELD_DESCRIPTION = "description";
    public static final String FIELD_DONE = "done";

    /**
     * Initializes a DBHelper with the context and our constants.
     * @param context
     */
    public DBHelper(Context context)
    {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }


    /**
     * Creates a new table for holding our tasks.
     * @param db
     */
    @Override
    public void onCreate(SQLiteDatabase db)
    {
        //Generate an SQL statement for creating a new table
        //CREATE TABLE Tasks (_id INTEGER PRIMARY KEY, description TEXT, done INTEGER)
        String createTable = "CREATE TABLE " + DATABASE_TABLE + " ("
                + KEY_FIELD_ID + " INTEGER PRIMARY KEY, "
                + FIELD_DESCRIPTION + " TEXT, "
                + FIELD_DONE + " INTEGER" + ")";
        db.execSQL(createTable);
    }

    /**
     * Removes the previous table and builds a new one.
     * @param sqLiteDatabase
     * @param i
     * @param i1
     */
    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1)
    {
        //1) Drop the existing table
        db.execSQL("DROP TABLE IF EXISTS " + DATABASE_TABLE);
        //2) Build (create) the new one
        onCreate(db);
    }

    /**
     * Adds a new task to the Database.
     * Id is left blank to automatically be
     * created by DataBase.
     * @param newTask
     */
    public void addTask(Task newTask)
    {
        SQLiteDatabase db = getWritableDatabase();
        //Specify the values (fields) to insert into the database
        //Everything except the primary key (_id) auto assigned
        ContentValues values = new ContentValues();
        values.put(FIELD_DESCRIPTION, newTask.getDescription());
        values.put(FIELD_DONE, newTask.isDone() ? 1 : 0);
        db.insert(DATABASE_TABLE, null, values);
        db.close();
    }

    /**
     * Gets all the Tasks from the database and
     * returns a list of the Tasks.
     * @return List<Task> all tasks
     */
    public List<Task> getAllTasks()
    {
        List<Task> allTasksList = new ArrayList<>();
        SQLiteDatabase db = getReadableDatabase();
        //To retrieve data from database table, use a Cursor
        //Cursor stores the result of the query
        Cursor cursor = db.query(DATABASE_TABLE, new String[] {KEY_FIELD_ID, FIELD_DESCRIPTION, FIELD_DONE},
                null, null, null, null, null);
        if (cursor.moveToFirst())
        {
            //guarantees at least one result from query
            do
            {
                Task task = new Task(cursor.getInt(0), cursor.getString(1), cursor.getInt(2) == 1);
                allTasksList.add(task);
            } while (cursor.moveToNext());
        }
        cursor.close();
        db.close();
        return allTasksList;
    }

    /**
     * Deletes a single task.
     * @param taskToDelete
     */
    public void deleteTask(Task taskToDelete)
    {
        SQLiteDatabase db = getReadableDatabase();
        db.delete(DATABASE_TABLE, KEY_FIELD_ID + "=" + taskToDelete.getId(), null);
        db.close();
    }

    /**
     * Updates the description and whether it is done
     * in the database.
     * @param taskToEdit
     */
    public void updateTask(Task taskToEdit)
    {
        SQLiteDatabase db = getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(FIELD_DESCRIPTION, taskToEdit.getDescription());
        values.put(FIELD_DONE, taskToEdit.isDone() ? 1 : 0);

        db.update(DATABASE_TABLE, values, KEY_FIELD_ID + "=" + taskToEdit.getId(), null);
        db.close();
    }

    /**
     * Gets and returns a single task from the database from a given id.
     * @param id
     */
    public Task getSingleTask(int id)
    {
        Task singleTask = null;
        SQLiteDatabase db = getReadableDatabase();
        //To retrieve data from database table, use a Cursor
        //Cursor stores the result of the query
        Cursor cursor = db.query(DATABASE_TABLE, new String[] {KEY_FIELD_ID, FIELD_DESCRIPTION, FIELD_DONE},
                KEY_FIELD_ID + "=" + id, null, null, null, null);
        if (cursor.moveToFirst())
        {
            //guarantees at least one result from query
            singleTask = new Task(cursor.getInt(0), cursor.getString(1), cursor.getInt(2) == 1);
        }
        cursor.close();
        db.close();
        return singleTask;
    }

}

