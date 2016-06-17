package org.eclipse.rcptt.tesla.swt.download;

import java.util.HashSet;
import java.util.Set;

import org.eclipse.rcptt.util.StringUtils;

public class RapDownloadHandlerManager {

	private final static Set<String> handlers = new HashSet<String>();
	private static String fileName;
	private static String content;


	public static void addHandler(String name)
	{
		handlers.add(name);
	}

	public static void clear()
	{
		//handlers.clear();
		fileName = null;
		content = null;
	}

	public static boolean contains(String name)
	{
		return handlers.contains(name);
	}

	public static boolean ckeckName(String file) {

		if(!StringUtils.isEmpty(file))
		{
			return file.equals(fileName);
		}
		return true;
	}

	public static boolean checkContent(String base64Content) {
		return base64Content.equals(content);
	}

	public static void addFile(String name, String base64)
	{
		fileName = name;
		content = base64;
	}
}
