package com.fitness.server.timeTable;


import java.util.List;
import java.util.Map;

import com.fitness.datastruts.TranTimeTable;

public interface OperateTimeTableBean {

	String SERVICE_NAME="operateTimeTableService";
	/*�ĸ�����
	 * ������ӿα�insert
	 * �������Ŀα�upString
	 * �������ҿα�select
	 * ����ɾ���ƶ��α���delete where
	 * */
	
	//�α����ʱ�Ĳ�ѯ������userid����ʼ����ʱ�䣬���ղ�ѯ�ֶ�Ϊ �����ֶ��е�����
	/*дһ������������һ�жϣ��жϵ��ֶ���������userid&&��ʼ����ʱ�䣨Ϊ������޸�ʱ��ʱ�䣩��һ��ֵ�Ƿ���ڣ�
	 *����ֵ����insert��䣬
	 *����������upString���
	 */
	//�ж�userid&&��ʼ����ʱ�� �Ƿ����
		boolean judgeExistService(String userid,String startTime,String endTime);
		
		//���ﴫ����ʱ����Ӧ�ý�����һ��һ���Ľ��д���map�����ŵ���һ�����յĿγ̼�ֵ��
		void insertClassService(List<TranTimeTable> timeTable);
		
		//upString�������������Ե���������ʱ���ʱ��ε�����Ӧ�ñ�������һ��һ�������ݣ�����ʱֱ�Ӵ��䣬��ĳ�γ�֮Ω����ֱ��set ����=null
		void updateClassService(String userid,String startTime,String endTime,Map<String,String> course);
		
		//ɾ��ĳһʱ��εĿγ�
		void deleteClassService(String userid,String startTime,String endTime);
		
		//��ѯ�û������пγ�
		List<TranTimeTable> selectClassService(String userid);
	
	
	
	

	
}
