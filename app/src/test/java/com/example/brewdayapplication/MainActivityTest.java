package com.example.brewdayapplication;

import com.example.brewdayapplication.activity.MainActivity;

import androidx.appcompat.app.AppCompatActivity;

import org.junit.Test;
import org.junit.Before;

import android.content.Intent;
import android.os.Bundle;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;


public class MainActivityTest {
    MainActivity mainActivityTest;
    Bundle attivita;

    @Before
    public  void initMethod(){
        mainActivityTest = new MainActivity();
    }

}
