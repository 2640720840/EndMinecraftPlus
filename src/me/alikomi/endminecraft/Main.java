package me.alikomi.endminecraft;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.Hashtable;
import java.util.Scanner;

import javax.naming.NamingException;
import javax.naming.directory.Attribute;
import javax.naming.directory.InitialDirContext;

import me.alikomi.endminecraft.utils.Menu;
import me.alikomi.endminecraft.utils.Util;

public class Main extends Util {
	private static String ip;
	public static int port = 25565;

	private static Scanner scanner = new Scanner(System.in);


	public static void main(String[] args) throws InterruptedException, IOException, IllegalAccessException, InstantiationException, NoSuchFieldException, NoSuchMethodException, InvocationTargetException, ClassNotFoundException, NamingException {
		getInfo();
		showMenu();
	}

	private static void getInfo() throws NamingException {
		log("��ӭʹ��EndMinecraftѹ�����", "",  "=======================");
		log("������ip��ַ");
		ip = scanner.nextLine();
		if (ip.contains(":")) {
			String[] tmpip = ip.split(":");
			ip = tmpip[0];
			port = Integer.parseInt(tmpip[1]);
		} else {
			log("������˿�(25565)");
			port = getCo(scanner.nextLine(), 25565);
		}
		Hashtable<String, String> hashtable = new Hashtable<String, String>();
		hashtable.put("java.naming.factory.initial", "com.sun.jndi.dns.DnsContextFactory");
		hashtable.put("java.naming.provider.url", "dns:");
		try {
			Attribute qwqre = (new InitialDirContext(hashtable)).getAttributes((new StringBuilder()).append("_Minecraft._tcp.").append(ip).toString(), new String[]{"SRV"}).get("srv");
			if (qwqre != null) {
				String[] re = qwqre.get().toString().split(" ", 4);
				log("��⵽SRV��¼���Զ���ת��SRV��¼");
				ip = re[3];
				log("ip: " + ip);
				port = Integer.parseInt(re[2]);
				log("port: " + port);
			}
		} catch (Exception e) {}
	}

	private static void showMenu() throws IOException, InterruptedException {
		Menu menu = new Menu(scanner, ip, port);
		while (true) {
			log("�����빥����ʽ��", "1 : MOTD����", "2 : �ֲ�ʽ���˹���(��Ⱥѹ��)");
			log("========================");
			switch (getCo(scanner.nextLine(), 2)) {
			case 1:
				menu._1();
				return;
			case 2:
				menu._2();
				return;
			default:
				log("����ѡ������������ѡ��");
			}
		}
	}
}