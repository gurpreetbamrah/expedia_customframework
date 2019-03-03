package runner;

import util.Report;
import util.XMLReader;

import java.lang.reflect.Method;
import java.util.*;

public class FrameWorkLauncher {

    static List<String> getTestDetail = new ArrayList<String>();

    public static ArrayList result;


    public static void main(String[] args) {
        try {
            result=new ArrayList();
            List<String> testToInvoke = FrameWorkLauncher.getTestToInvoke();
            for (int i = 0; i < testToInvoke.size(); i++) {
                String testDetail[] = testToInvoke.get(i).split("__");
                Class cls = Class.forName(testDetail[0]);
                Object obj = cls.newInstance();
                Method method = cls.getDeclaredMethod(testDetail[1], String.class);
                method.setAccessible(true);
                method.invoke(obj, testDetail[2]);

            }
            Report report=new Report();
            report.createReport();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    public static List<String> getTestToInvoke() {
        XMLReader xmlReader = new XMLReader();
        List<String> list = xmlReader.readXML();
        String classToInvoke = "";
        String methodToInvoke = "";
        String param = "";
        for (int i = 0; i < list.size(); i++) {
            String str= String.valueOf(list.get(i));
            String arr[]=   str.split("__");
            String getClass[] = arr[0].split(",");
            classToInvoke = getClass[0].replaceAll("\n", "").trim();
            methodToInvoke = getClass[1].replaceAll("\n", "").trim();
            param = String.valueOf(arr[1].replaceAll("\n", "").trim());
            getTestDetail.add(classToInvoke + "__" + methodToInvoke + "__" + param);

        }

        return getTestDetail;
    }
}
