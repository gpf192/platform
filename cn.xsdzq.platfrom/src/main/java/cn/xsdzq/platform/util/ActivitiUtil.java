package cn.xsdzq.platform.util;

import org.activiti.engine.ProcessEngine;
import org.activiti.engine.ProcessEngines;

public class ActivitiUtil {
	//private ProcessEngine processEngine;
	public static void main(String[] args) {
		ProcessEngine p = ProcessEngines.getDefaultProcessEngine();
	//	processEngine.
		System.out.println(p.toString());
	}
}
