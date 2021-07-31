package com.example.workflow;

import org.camunda.bpm.dmn.engine.DmnDecisionTableResult;
import org.camunda.bpm.engine.DecisionService;
import org.camunda.bpm.engine.ProcessEngine;
import org.camunda.bpm.engine.variable.VariableMap;
import org.camunda.bpm.engine.variable.Variables;
import org.camunda.bpm.spring.boot.starter.annotation.EnableProcessApplication;
import org.camunda.bpm.spring.boot.starter.event.PostDeployEvent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.event.EventListener;

//import com.example.workflow.dmn.DinnerApplication;

/**
 * This program is a sample of how to define simple rule table,
 * trigger an event, evaluate decision table and produce the result. 
 * This class is the main class currently performing all the above 
 * functions. 
 * @author ketannakum
 *
 */

@SpringBootApplication
@EnableProcessApplication("Dinner App DMN")
public class Application {
	
	@Autowired
	public ProcessEngine processEngine;
	
	//@Autowired
	//public DinnerApplication dinnerApp;
	
	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}
	
	 
	 @EventListener
	 public void onPostDeploy(PostDeployEvent event) {
	  
		 System.out.println("Inside Evaluate Decision Table ");
		 
		 DecisionService decisionService = processEngine.getDecisionService();

		 VariableMap variables = Variables.createVariables()
		      .putValue("season", "Spring")
		      .putValue("guestCount", 10);

		 DmnDecisionTableResult dishDecisionResult = decisionService.evaluateDecisionTableByKey("dish", variables);
		 String desiredDish = dishDecisionResult.getSingleEntry();

		 System.out.println("Desired dish: " + desiredDish);
		 
	 }
	
	/*
	@EventListener
	 public void onPostDeploy(PostDeployEvent event) {
	  
		 System.out.println("Inside post deploy method... ");
		 dinnerApp.evaluateDecisionTable(processEngine);
	
	}*/
}
