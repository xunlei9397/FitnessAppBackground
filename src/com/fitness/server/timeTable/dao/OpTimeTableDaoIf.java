package com.fitness.server.timeTable.dao;

import java.util.List;
import java.util.Map;

import com.fitness.datastruts.TranTimeTable;

public interface OpTimeTableDaoIf {

	//�α����ʱ�Ĳ�ѯ������userid����ʼ����ʱ�䣬���ղ�ѯ�ֶ�Ϊ �����ֶ��е�����
		/*дһ������������һ�жϣ��жϵ��ֶ���������userid&&��ʼ����ʱ�䣨Ϊ������޸�ʱ��ʱ�䣩��һ��ֵ�Ƿ���ڣ�
		 *����ֵ����insert��䣬
		 *����������upString���
		 */
	
	//�ж�userid&&��ʼ����ʱ�� �Ƿ����
	boolean judgeExist(String openid,String startTime,String endTime);
	
	//���ﴫ����ʱ����Ӧ�ý�����һ��һ���Ľ��д���map�����ŵ���һ�����յĿγ̼�ֵ��
	void insertClass(List<TranTimeTable> timeTable);
	
	//upString�������������Ե���������ʱ���ʱ��ε�����Ӧ�ñ�������һ��һ�������ݣ�����ʱֱ�Ӵ��䣬��ĳ�γ�֮Ω����ֱ��set ����=null
	void updateClass(String userid,String startTime,String endTime,Map<String,String> course);
	
	//ɾ��ĳһʱ��εĿγ�
	void deleteClass(String userid,String startTime,String endTime);
	
	//��ѯ�û������пγ�
	List<TranTimeTable> selectClass(String userid);
	
	
}
