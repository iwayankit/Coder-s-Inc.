package com.example.iway.codersinc.Contests;

/**
 * Created by Shashank on 25-Sep-16.
 */

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Shashank on 24-Sep-16.
 */


public class DatabaseHandler extends SQLiteOpenHelper {



    // All Static variables
    // Database Version
    private static final int DATABASE_VERSION = 1;

    // Database Name
    private static final String DATABASE_NAME = "eventsManager";

    // Contacts table name
    private static final String TABLE_EVENTS = "events";

    // Contacts Table Columns names
    private static final String KEY_NAME= "Name";
    private static final  String KEY_TYPE="Type";
    private static final String KEY_SITE="Site";
    private static final String KEY_BEGINDATE="Begindate";
    private static final String KEY_BEGIN_TIMING = "Begin";
    private static final String KEY_ENDDATE="Enddate";
    private static final String KEY_END_TIMING = "End";




    private static final String KEY_LINK="Link";


    public DatabaseHandler(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    // Creating Tables
    @Override
    public void onCreate(SQLiteDatabase db) {
        String CREATE_EVENTS_TABLE = "CREATE TABLE " + TABLE_EVENTS + "("
                + KEY_NAME + " TEXT ," + KEY_TYPE + " TEXT ," + KEY_SITE + " TEXT  ," + KEY_BEGINDATE + " TEXT ," +  KEY_BEGIN_TIMING + " TEXT," + KEY_ENDDATE + " TEXT ," +
                KEY_END_TIMING + " TEXT," + KEY_LINK + " TEXT " + ")";
        db.execSQL(CREATE_EVENTS_TABLE);
    }

    // Upgrading database
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // Drop older table if existed
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_EVENTS);
        // Create tables again
        onCreate(db);
    }

    /**
     * All CRUD(Create, Read, Update, Delete) Operations
     */

    // Adding ne
    // w contact
    public void addContact(Contact contact) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(KEY_NAME, contact.getName()); // Contact Name
        values.put(KEY_TYPE,contact.getType());
        values.put(KEY_SITE,contact.getSite());
        values.put(KEY_BEGINDATE,contact.getBegindate());
        values.put(KEY_BEGIN_TIMING, contact.getBegin());
        values.put(KEY_ENDDATE,contact.getEnddate());
        values.put(KEY_END_TIMING,contact.getEnd());
        values.put(KEY_LINK,contact.getLink());

        // Inserting Row
        db.insert(TABLE_EVENTS, null, values);
        db.close();
        // Closing database connection
    }

    // Getting single contact
    /*Contact getContact(int id) {
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.query(TABLE_CONTACTS, new String[] { KEY_ID,
                        KEY_NAME, KEY_PH_NO }, KEY_ID + "=?",
                new String[] { String.valueOf(id) }, null, null, null, null);
        if (cursor != null)
            cursor.moveToFirst();

        Contact contact = new Contact(Integer.parseInt(cursor.getString(0)),
                cursor.getString(1), cursor.getString(2));
        // return contact
        return contact;
    }*/

    // Getting All Contacts
    public List<Contact> getAllContacts() {
        List<Contact> contactList = new ArrayList<Contact>();
        // Select All Query
        String selectQuery = "SELECT  * FROM " + TABLE_EVENTS;

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        // looping through all rows and adding to list
        if (cursor.moveToFirst()) {
            do {
                Contact contact = new Contact();
                contact.setName(cursor.getString(0));
                contact.setType(cursor.getString(1));
                contact.setSite(cursor.getString(2));
                contact.setBegindate(cursor.getString(3));
                contact.setBegin(cursor.getString(4));
                contact.setEnddate(cursor.getString(5));
                contact.setEnd(cursor.getString(6));
                contact.setLink(cursor.getString(7));
                // Adding contact to list
                contactList.add(contact);
            } while (cursor.moveToNext());
        }

        // return contact list
        return contactList;
    }

    // Updating single contact
    /*public int updateContact(Contact contact) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(KEY_BEGIN_TIMING, contact.getBegin_timing());
        values.put(KEY_END_TIMING, contact.getEnd_timing());
        values.put(KEY_TYPE, contact.getType());



        // updating row
        return db.update(TABLE_CONTACTS, values, KEY_ID + " = ?",
                new String[] { String.valueOf(contact.getNAME()) });
    }

     //Deleting single contact*/
    public void deleteContact(Contact contact) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABLE_EVENTS, KEY_NAME + " = ?",
                new String[] { String.valueOf(contact.getName()) });
        db.close();
    }


    // Getting contacts Count
   /* public int getContactsCount() {
        String countQuery = "SELECT  * FROM " + TABLE_CONTACTS;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(countQuery, null);
        cursor.close();

        // return count
        return cursor.getCount();
    }*/

}

