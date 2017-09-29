package Iot.Timer;

import java.util.ArrayList;

import org.springframework.data.repository.CrudRepository;




public interface TimerDataRepository extends CrudRepository<TimerData, Long>{

	//TimerData findByPruductIDAllIgnoringCase(String pruductID);
	ArrayList<TimerData> findByPruductIDAndFunctionIDAllIgnoringCase(String pruductID,int functionID);
}
