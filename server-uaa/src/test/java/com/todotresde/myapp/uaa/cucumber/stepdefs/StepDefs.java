package com.todotresde.myapp.uaa.cucumber.stepdefs;

import com.todotresde.myapp.uaa.UaaApp;

import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.ResultActions;

import org.springframework.boot.test.context.SpringBootTest;

@WebAppConfiguration
@SpringBootTest
@ContextConfiguration(classes = UaaApp.class)
public abstract class StepDefs {

    protected ResultActions actions;

}
