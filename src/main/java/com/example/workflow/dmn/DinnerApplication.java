package com.example.workflow.dmn;

import org.camunda.bpm.application.PostDeploy;
import org.camunda.bpm.application.ProcessApplication;
import org.camunda.bpm.application.impl.ServletProcessApplication;
import org.camunda.bpm.dmn.engine.DmnDecisionTableResult;
import org.camunda.bpm.engine.DecisionService;
import org.camunda.bpm.engine.ProcessEngine;
import org.camunda.bpm.engine.variable.VariableMap;
import org.camunda.bpm.engine.variable.Variables;
//import org.springframework.stereotype.Component;

/**
 * This class is currently NOT in use.
 * @author ketannakum
 *
 */

@ProcessApplication("Dinner App DMN")
public class DinnerApplication  extends ServletProcessApplication {
	
	 @PostDeploy
	  public void evaluateDecisionTable(ProcessEngine processEngine) {

		System.out.println("Inside Evaluate Decision Table ");
		 
	    DecisionService decisionService = processEngine.getDecisionService();

	    VariableMap variables = Variables.createVariables()
	      .putValue("season", "Spring")
	      .putValue("guestCount", 10);

	    DmnDecisionTableResult dishDecisionResult = decisionService.evaluateDecisionTableByKey("dish", variables);
	    String desiredDish = dishDecisionResult.getSingleEntry();

	    System.out.println("Desired dish: " + desiredDish);
	 }

}
