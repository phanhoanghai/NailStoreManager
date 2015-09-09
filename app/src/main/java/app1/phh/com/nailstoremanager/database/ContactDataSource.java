package app1.phh.com.nailstoremanager.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;

import java.util.ArrayList;
import java.util.List;

import app1.phh.com.nailstoremanager.Common.Constants;
import app1.phh.com.nailstoremanager.database.model.Contact;

/**
 * Created by HaiPhan on 9/7/2015.
 */
public class ContactDataSource extends BaseDataSource {
    private SqlHelper mydb;

    private final int INDEXT_CONTACT_ID = 0;
    private final int INDEXT_CONTACT_NAME = 0;
    private final int INDEXT_CONTACT_EMAIL = 0;
    private final int INDEXT_CONTACT_STREET = 0;
    private final int INDEXT_CONTACT_CITY = 0;
    private final int INDEXT_CONTACT_PHONE = 0;

    private String[] allColumns = {Constants.CONTACT_ID, Constants.CONTACTS_COLUMN_CITY, Constants.CONTACTS_COLUMN_EMAIL,
            Constants.CONTACTS_COLUMN_NAME, Constants.CONTACTS_COLUMN_PHONE, Constants.CONTACTS_COLUMN_STREET,
            Constants.CONTACTS_TABLE_NAME};

    public ContactDataSource(Context context) {
        mydb = new SqlHelper(context);
    }

    public void createNewContact(Contact contact) {
        ContentValues contactValues = this.getContentValueFromContact(contact);

        try {
//            openInternal();
            database.insert(Constants.CONTACTS_TABLE_NAME, null, contactValues);
        } finally {
//            closeInternal();
        }
    }

    public List<Contact> getAllContact() {
        List<Contact> contacts = new ArrayList<Contact>();
        Cursor cursor = database.query(Constants.CONTACTS_TABLE_NAME, allColumns, null, null, null, null, null);
        cursor.moveToFirst();

        while (!cursor.isAfterLast()){
            contacts.add(cursorToNote(cursor));
            cursor.moveToNext();
        }
        return contacts;
    }

    public void deleteContact(Contact contact){
        try{
            openInternal();
            String contactId = String.valueOf(contact.getId());
            database.delete(Constants.CONTACTS_TABLE_NAME, Constants.CONTACT_ID + "=?", new String[] {contactId});
        } finally {
            closeInternal();
        }
    }

    private Contact cursorToNote(Cursor cursor) {
        Contact contact = new Contact();
        contact.setId(cursor.getLong(INDEXT_CONTACT_ID));
        contact.setEmail(cursor.getString(INDEXT_CONTACT_NAME));
        contact.setEmail(cursor.getString(INDEXT_CONTACT_EMAIL));
        contact.setEmail(cursor.getString(INDEXT_CONTACT_CITY));
        contact.setEmail(cursor.getString(INDEXT_CONTACT_PHONE));
        contact.setEmail(cursor.getString(INDEXT_CONTACT_STREET));
        return contact;
    }

    private ContentValues getContentValueFromContact(Contact contact) {
        ContentValues contentValues = new ContentValues();
        contentValues.put(Constants.CONTACT_ID, String.valueOf(contact.getId()));

        if (contact.getEmail() != null) {
            contentValues.put(Constants.CONTACTS_COLUMN_EMAIL, String.valueOf(contact.getEmail()));
        } else {
            contentValues.put(Constants.CONTACTS_COLUMN_EMAIL, "");
        }

        if (contact.getName() != null) {
            contentValues.put(Constants.CONTACTS_COLUMN_NAME, String.valueOf(contact.getName()));
        } else {
            contentValues.put(Constants.CONTACTS_COLUMN_NAME, "");
        }

        if (contact.getStreet() != null) {
            contentValues.put(Constants.CONTACTS_COLUMN_STREET, String.valueOf(contact.getStreet()));
        } else {
            contentValues.put(Constants.CONTACTS_COLUMN_STREET, "");
        }

        if (contact.getPlace() != null) {
            contentValues.put(Constants.CONTACTS_COLUMN_CITY, String.valueOf(contact.getPlace()));
        } else {
            contentValues.put(Constants.CONTACTS_COLUMN_CITY, "");
        }

        if (contact.getPhone() != null) {
            contentValues.put(Constants.CONTACTS_COLUMN_PHONE, String.valueOf(contact.getPhone()));
        } else {
            contentValues.put(Constants.CONTACTS_COLUMN_PHONE, "");
        }
        return contentValues;
    }
}
