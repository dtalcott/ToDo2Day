package edu.orangecoastcollege.cs273.dtallcott.todo2day;

/**
 * A task class with id, description, and whether or not it has been completed.
 *
 * @author Devon Tallcott
 * @version 1.0
 *
 * Created on 9/28/2017.
 */

public class Task
{
    private int mId;
    private String mDescription;
    private boolean mIsDone;

    /**
     * Initializes a task with given parameters
     * @param id
     * @param description
     * @param isDone
     */
    public Task(int id, String description, boolean isDone)
    {
        mId = id;
        mDescription = description;
        mIsDone = isDone;
    }

    /**
     * Initializes a task with a given description and boolean and sets the id to -1.
     * @param description
     * @param isDone
     */
    public Task(String description, boolean isDone)
    {
        this(-1,description,isDone);
    }

    /**
     * Initializes a task with a negative ID, empty description and false for the boolean.
     */
    public Task()
    {
        this(-1,"",false);
    }

    /**
     * Gets the description
     * @return description
     */
    public String getDescription() {
        return mDescription;
    }

    /**
     * Sets the Description
     * @param description
     */
    public void setDescription(String description) {
        mDescription = description;
    }

    /**
     * Checks if task is done
     * @return if true or false
     */
    public boolean isDone() {
        return mIsDone;
    }

    /**
     * Sets whether the task is done or not.
     * @param done
     */
    public void setDone(boolean done) {
        mIsDone = done;
    }

    /**
     * Gets the unique id for the task
     * @return
     */
    public int getId() {
        return mId;
    }

    /**
     * Outputs the information to a readable String
     * @return
     */
    @Override
    public String toString() {
        return "Task{" +
                "Id=" + mId +
                ", Description='" + mDescription + '\'' +
                ", IsDone=" + mIsDone +
                '}';
    }
}
