package me.alikomi.endminecraft.utils;

import java.util.HashMap;
import java.util.Scanner;

import luohuayu.EndMinecraftPlus.proxy.ProxyPool;
import luohuayu.EndMinecraftPlus.tasks.attack.DistributedBotAttack;
import luohuayu.EndMinecraftPlus.tasks.attack.IAttack;
import luohuayu.EndMinecraftPlus.tasks.attack.MotdAttack;

public class Menu extends Util {
	private String ip;
	private Scanner scanner;
	private int port;

	public Menu(Scanner sc, String ip, int port) {
		this.ip = ip;
		this.port = port;
		this.scanner = sc;
	}

	public void _1() {
		log("MOTD����ѡ��");
		log("�����빥��ʱ��(��λ����)(60)");
		int time = getCo(scanner.nextLine(),60);
		log("�������߳���(10)");
		int thread = getCo(scanner.nextLine(),16);
		IAttack attack = new MotdAttack(time,thread,0,false,false,null);
		attack.start(ip, port);
	}

	public void _2() {
		log("�ֲ�ʽ����ѹ��ѡ��", "�����빥��ʱ����(3600s)");
		int time = getCo(scanner.nextLine(),3600);
		log("��������󹥻���(10000)");
		int maxAttack = getCo(scanner.nextLine(),10000);
		log("������ÿ�μ�����������(ms)");
		int sleepTime = getCo(scanner.nextLine(),0);
		log("�������Ƿ���TAB���� y/n��Ĭ�Ϲر�(n)");
		boolean tab = getCo(scanner.nextLine(),"n").equals("y");
		log("�������Ƿ�����������ģʽ y/n��Ĭ�Ϲر�(n)");
		boolean lele = getCo(scanner.nextLine(),"n").equals("y");
		getProxy();
		IAttack attack = new DistributedBotAttack(time,maxAttack,sleepTime,lele,tab,new HashMap<String,String>());
		attack.start(ip, port);
	}

	public void getProxy() {
		log("���������ip�б��ȡ��ʽ��1���� 1.ͨ��API��ȡ 2.ͨ�����ػ�ȡ 3.ͨ������+API��ȡ");
		switch (getCo(scanner.nextLine(),1)) {
		case 1:
			ProxyPool.getProxysFromAPIs();
			ProxyPool.runUpdateProxysTask(1200);
			break;
		case 2:
			ProxyPool.getProxysFromFile();
			break;
		case 3:
			ProxyPool.getProxysFromFile();
			ProxyPool.getProxysFromAPIs();
			ProxyPool.runUpdateProxysTask(1200);
			break;
		default:
			ProxyPool.getProxysFromAPIs();
			ProxyPool.runUpdateProxysTask(1200);
		}
	}
}
