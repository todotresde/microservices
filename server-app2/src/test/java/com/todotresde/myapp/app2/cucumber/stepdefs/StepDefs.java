package com.todotresde.myapp.app2.cucumber.stepdefs;

import com.todotresde.myapp.app2.App2App;

import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.ResultActions;

import org.springframework.boot.test.context.SpringBootTest;

@WebAppConfiguration
@SpringBootTest
@ContextConfiguration(classes = App2App.class)
public abstract class StepDefs {

    protected ResultActions actions;

}
