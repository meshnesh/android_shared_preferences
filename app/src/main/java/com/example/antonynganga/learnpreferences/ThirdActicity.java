package com.example.antonynganga.learnpreferences;

import android.content.Context;
import android.content.SharedPreferences;
import android.nfc.Tag;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.Arrays;

public class ThirdActicity extends AppCompatActivity {

    private static final String TAG = ThirdActicity.class.getSimpleName();
    private TextView txvDisplay;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.third_activity);

        txvDisplay = findViewById(R.id.txvDisplay);
    }

    private Employee getEmployee() {

        Employee employee = new Employee();
        employee.setName("Antony Munene");
        employee.setProfession("CodingBoxer");
        employee.setProfId(2);
        employee.setRoles(Arrays.asList("Developer", "TTL","Boxer"));

        return employee;
    }

    public void saveObjectType(View view) {

        Employee employee = getEmployee();

        SharedPreferences sp = getPreferences(Context.MODE_PRIVATE);
        SharedPreferences.Editor ed = sp.edit();

        //serialisation of data
        Gson gson = new Gson();
        String jsonStringData = gson.toJson(employee, Employee.class);

        Log.i(TAG, jsonStringData);

        ed.putString(Constants.KEY_EMPLOYEE, jsonStringData);
        ed.apply();
    }

    public void loadObjectType(View view) {

        SharedPreferences sp = getPreferences(Context.MODE_PRIVATE);
        String Jstring = sp.getString(Constants.KEY_EMPLOYEE, "N/A");

        //Deserialisation
        Gson gson = new Gson();
        Employee employee = gson.fromJson(Jstring, Employee.class);
        
        displayEmployee(employee);
        
    }

    private void displayEmployee(Employee employee) {

        if (employee == null)
            return;

        String display = employee.getName()
                + "\n" + employee.getProfession()
                + " - " + employee.getProfId()
                + "\n" + employee.getRoles().toString();

        txvDisplay.setText(display);
    }

    public void saveGenericType(View view) {
        Employee employee = getEmployee();
        Foo<Employee> foo = new Foo<>();
        foo.setObject(employee);

        SharedPreferences sp = getPreferences(Context.MODE_PRIVATE);
        SharedPreferences.Editor ed = sp.edit();

        //serialisation of data
        Gson gson = new Gson();
        Type type = new TypeToken<Foo<Employee>>() {}.getType();
        String jsonStringData = gson.toJson(foo, type);

        Log.i(TAG, jsonStringData);

        ed.putString(Constants.KEY_FOO, jsonStringData);
        ed.apply();
    }

    public void loadGenericType(View view) {

        SharedPreferences sp = getPreferences(Context.MODE_PRIVATE);
        String Jstring = sp.getString(Constants.KEY_FOO, "N/A");

        //Deserialisation
        Gson gson = new Gson();
        Type type = new TypeToken<Foo<Employee>>() {}.getType();
        Foo<Employee> fooEmployee = gson.fromJson(Jstring, type);

        Employee employee = fooEmployee.getObject();
        displayEmployee(employee);
    }
}
