package com.oracle.entity;

import java.util.List;
/**
 * ��ҳʵ����
 *
 *
 * @param <T>
 */
public class PageBean<T> {

	private int pc;//��ǰҳ��page code
	private int tr;//�ܼ�¼��total record
	private int ps = 10;//ÿҳ��¼��page size
	private List<T> beanList;//��ǰҳ�ļ�¼
	
	public int getPc() {
		return pc;
	}
	public void setPc(int pc) {
		this.pc = pc;
	}
	
	//�����ҳ��
	public int getTp(){
		int tp = tr / ps;
		return tr%ps == 0 ? tp : tp+1;
	}
	
	public int getTr() {
		return tr;
	}
	public void setTr(int tr) {
		this.tr = tr;
	}
	public int getPs() {
		return ps;
	}
	public void setPs(int ps) {
		this.ps = ps;
	}
	public List<T> getBeanList() {
		return beanList;
	}
	public void setBeanList(List<T> beanList) {
		this.beanList = beanList;
	}
	
	
	
}
