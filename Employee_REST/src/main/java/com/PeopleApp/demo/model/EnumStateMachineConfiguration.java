package com.PeopleApp.demo.model;

import org.springframework.context.annotation.Configuration;
import org.springframework.statemachine.config.EnableStateMachineFactory;
import org.springframework.statemachine.config.StateMachineConfigurerAdapter;
import org.springframework.statemachine.config.builders.StateMachineConfigurationConfigurer;
import org.springframework.statemachine.config.builders.StateMachineModelConfigurer;
import org.springframework.statemachine.config.builders.StateMachineStateConfigurer;
import org.springframework.statemachine.config.builders.StateMachineTransitionConfigurer;
import org.springframework.statemachine.listener.StateMachineListenerAdapter;
import org.springframework.statemachine.state.State;

enum EstateEvents {
	CHANGEINCHECK,
	CHANGEAPPROVED,
	CHANGEACTIVE
}

@Configuration
@EnableStateMachineFactory
public class EnumStateMachineConfiguration extends StateMachineConfigurerAdapter <Estate,EstateEvents>
{

	@Override
	public void configure(StateMachineTransitionConfigurer<Estate, EstateEvents> transitions) throws Exception {
		// TODO Auto-generated method stub
		//super.configure(transitions);
		transitions
		 .withExternal().source(Estate.ADDED).target(Estate.INCHECK).event(EstateEvents.CHANGEINCHECK)
		 .and()
		 .withExternal().source(Estate.ADDED).target(Estate.ACTIVE).event(EstateEvents.CHANGEACTIVE)
		 .and()
		 .withExternal().source(Estate.ADDED).target(Estate.APPROVED).event(EstateEvents.CHANGEAPPROVED);
	}

	@Override
	public void configure(StateMachineStateConfigurer<Estate, EstateEvents> states) throws Exception {
		// TODO Auto-generated method stub
		//super.configure(states);
		states
			.withStates()
			.initial(Estate.ADDED)
			.state(Estate.INCHECK);
	}

	@Override
	public void configure(StateMachineConfigurationConfigurer<Estate, EstateEvents> config) throws Exception 
	{
		StateMachineListenerAdapter<Estate,EstateEvents> adapter = new StateMachineListenerAdapter<Estate,EstateEvents>()
		{
			@Override
			public void stateChanged(State<Estate,EstateEvents> from, State<Estate,EstateEvents> to)
			{
				//super.stateChanged(from, to);
			}
		};
		// TODO Auto-generated method stub
		//super.configure(config);
		
		config.withConfiguration()
			.autoStartup(false)
			.listener(adapter);
			{
				
			}
	}

	@Override
	public void configure(StateMachineModelConfigurer<Estate, EstateEvents> model) throws Exception {
		// TODO Auto-generated method stub
		super.configure(model);
	}
 
	
}
