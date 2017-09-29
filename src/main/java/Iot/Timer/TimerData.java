package Iot.Timer;

import javax.persistence.Column;
import javax.persistence.Convert;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import Iot.common.db.booleanToIntegerConverter;

/*
  create database iot;
  
  use iot;
  
  create table  timerdata(
  id int unsigned not null auto_increment primary key,
  product_id varchar(50),
  function_id int,
  on_time varchar(10),
  off_time varchar(10),
  timer_on tinyint
  );
 */

/**
 * Created by EDE67167 on 2017-08-15.
 */
@Entity
@Table(name = "timerdata")
public class TimerData   {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(name="product_id", nullable = false,length=32)
	String pruductID;
	
	@Column(name="function_id", nullable = false)
	int functionID;
	
	@Column(name="on_time", nullable = false,length=10)
	String onTime;
	
	@Column(name="off_time", nullable = false,length=10)
    String offTime;
	
	@Column(name="timer_on", nullable = false)
	@Convert(converter=booleanToIntegerConverter.class)
    Boolean timerOn;
	
	@Column(name="repeat_case", nullable = false)
	private int repeatCase;
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public int getRepeatCase() {
		return repeatCase;
	}

	public void setRepeatCase(int repeatCase) {
		this.repeatCase = repeatCase;
	}

	
   
   
	public TimerData() {
		
	
	}

	public String getPruductID() {
		return pruductID;
	}

	public void setPruductID(String pruductID) {
		this.pruductID = pruductID;
	}

	public int getFunctionID() {
		return functionID;
	}

	public void setFunctionID(int functionID) {
		this.functionID = functionID;
	}
    public Boolean getTimerOn() {
        return timerOn;
    }

    public void setTimerOn(Boolean timerOn) {
        this.timerOn = timerOn;
    }

    public String getOnTime() {
        return onTime;
    }

    public void setOnTime(String onTime) {
        this.onTime = onTime;
    }

    public String getOffTime() {
        return offTime;
    }

    public void setOffTime(String offTime) {
        this.offTime = offTime;
    }
    
    
    
   
}
