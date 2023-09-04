package com.example.comparadordetexto
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.espresso.Espresso
import androidx.test.espresso.action.ViewActions
import androidx.test.espresso.action.ViewActions.closeSoftKeyboard
import androidx.test.espresso.action.ViewActions.typeText
import androidx.test.espresso.assertion.ViewAssertions
import androidx.test.espresso.matcher.ViewMatchers
import androidx.test.ext.junit.rules.ActivityScenarioRule
import org.junit.Assert.*

import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class MainActivityTest {

    @get: Rule
    var rule: ActivityScenarioRule<*> = ActivityScenarioRule(MainActivity::class.java)

    @Before
    fun setUp() {
    }

    @After
    fun tearDown() {
    }

    @Test
    fun mainActivity_equals_values() {
        Espresso.onView(
            ViewMatchers.withId(R.id.editTex_input1)
        ).perform(
            typeText("Azul")
        )
        Espresso.onView(
            ViewMatchers.withId(R.id.editTex_input2)
        ).perform(
            typeText("Azul"),
            closeSoftKeyboard()
        )
        Espresso.onView(
            ViewMatchers.withId(R.id.button_comparar)
        ).perform(
            ViewActions.click()
        )
        Espresso.onView(
            ViewMatchers.withId(R.id.resultado)
        ).check(
            ViewAssertions.matches(
                ViewMatchers.withText("La cadenas ingresadas son iguales")
            )
        )
    }

    @Test
    fun mainActivity_distinct_values(){
        Espresso.onView(
            ViewMatchers.withId(R.id.editTex_input1)
        ).perform(
            typeText("Azul")
        )
        Espresso.onView(
            ViewMatchers.withId(R.id.editTex_input2)
        ).perform(
            typeText("Rojo"),
            closeSoftKeyboard()
        )
        Espresso.onView(
            ViewMatchers.withId(R.id.button_comparar)
        ).perform(
            ViewActions.click()
        )
        Espresso.onView(
            ViewMatchers.withId(R.id.resultado)
        ).check(
            ViewAssertions.matches(
                ViewMatchers.withText("Las cadenas ingresadas no son iguales")
            )
        )
    }
}