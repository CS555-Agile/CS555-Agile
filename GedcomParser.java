package Gedcom;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.time.Period;
import java.time.ZoneId;
import java.util.*;
import java.io.*;
import java.util.concurrent.TimeUnit;

import javax.xml.transform.ErrorListener;

public class GedcomParser {

    private static HashMap<String, String> GedMap = new HashMap<>();
    //HashMap<String, Indi> Individual = new HashMap<>();
    private static TreeMap<String, Indi> Individual = new TreeMap<>(new Comparator<String>() {
        @Override
        public int compare(String o1, String o2) {
            int a1 = Integer.valueOf(o1.substring(2, o1.length() - 1));
            int a2 = Integer.valueOf(o2.substring(2, o2.length() - 1));
            return Integer.compare(a1, a2);
        }
    });
    // HashMap<String, Fami> Family = new HashMap<>();
    private static TreeMap<String, Fami> Family = new TreeMap<>(new Comparator<String>() {
        @Override
        public int compare(String o1, String o2) {
            int a1 = Integer.valueOf(o1.substring(2, o1.length() - 1));
            int a2 = Integer.valueOf(o2.substring(2, o2.length() - 1));
            return Integer.compare(a1, a2);
        }
    });
    Boolean birt = false;
    Boolean deat = false;
    Boolean married = false;
    Boolean divorced = false;
    Date birthday;
    Indi Indiobj = null;
    Fami Famobj = null;
    private static ArrayList<String> Errorlist = new ArrayList<String>();
    static boolean check=false;

    private static class Indi {

        String id, name, gender, age, alive = "True", child = "NA";
        Date bday, death;
        ArrayList<String> spouse = new ArrayList<>();

        public void setName(String str) {
            name = str;
        }

        public void setGender(String str) {
            gender = str;
        }

        public void setBday(Date str) {

            bday = str;
        }

        public String getId() {
            return id;
        }

        public void setAge(String str) {
            age = str;
        }

        public void setDeath() {
            alive = "False";
        }

        public void setDeathDay(Date str) {

            death = str;
        }

        public void setChild(String str) {
            child = str;
        }

        public void setSpouse(String str) {
            spouse.add(str);
        }

        public String getName() {
            return name;
        }

        public String getGender() {
            return gender;
        }

        public Date getBday() {

            return bday;
        }

        public String getAge() {
            return age;
        }

        public String getAlive() {
            return alive;
        }

        public Date getDeath() {

            return death;

        }

        public String getChild() {
            return child;
        }

        public ArrayList<String> getSpouse() {
            return spouse;
        }

        Indi(String str) {
            id = str;
        }

    }

    private static class Fami {

        String Fid, hID, hName, wID, wName;
        Date married, divorced;
        ArrayList<String> cSet = new ArrayList<>();

        public String getFid() {
            return Fid;
        }

        public void setFid(String fid) {
            Fid = fid;
        }

        public Date getMarried() {

            return married;
        }

        public void setMarried(Date married) {

            this.married = married;
        }

        public Date getDivorced() {

            return divorced;
        }

        public void setDivorced(Date divorced) {

            this.divorced = divorced;
        }

        public String gethID() {
            return hID;
        }

        public void sethID(String hID) {
            this.hID = hID;
        }

        public String gethName() {
            return hName;
        }

        public void sethName(String hName) {
            this.hName = hName;
        }

        public String getwID() {
            return wID;
        }

        public void setwID(String wID) {
            this.wID = wID;
        }

        public String getwName() {
            return wName;
        }

        public void setwName(String wName) {
            this.wName = wName;
        }

        public ArrayList<String> getcSet() {
            return cSet;
        }

        public void setcSet(String str) {
            cSet.add(str);
        }

        Fami(String str) {
            Fid = str;
        }


    }

    public GedcomParser() {

        GedMap.put("INDI", "0");
        GedMap.put("FAM", "0");
        GedMap.put("HEAD", "0");
        GedMap.put("TRLR", "0");
        GedMap.put("NOTE", "0");
        GedMap.put("NAME", "1");
        GedMap.put("SEX", "1");
        GedMap.put("BIRT", "1");
        GedMap.put("DEAT", "1");
        GedMap.put("FAMC", "1");
        GedMap.put("FAMS", "1");
        GedMap.put("MARR", "1");
        GedMap.put("HUSB", "1");
        GedMap.put("WIFE", "1");
        GedMap.put("CHIL", "1");
        GedMap.put("DIV", "1");
        GedMap.put("DATE", "2");

    }
    public static boolean siblingCheck(String s1,String s2)
    {
    	
    	boolean flag=true;
    	Indi I1=Individual.get(s1);
    	Indi I2=Individual.get(s2);
    	if(!I1.getChild().equals(I2.getChild()) || I1.getChild().equals("NA") || I2.getChild().equals("NA"))		
    	{	
    		
    		flag=false;
    	}
    	
    	return flag;
    }
    public static ArrayList<String> getParents(String I1)
    {
    	ArrayList<String> Parents=new ArrayList<String>();
    	if(!Individual.get(I1).getChild().equals("NA"))
    	{
    		//System.out.println("inside get parents");
    		Fami fam=Family.get(Individual.get(I1).getChild());
			/*
			 * System.out.println(Individual.get(I1).getName());
			 * System.out.println(fam.gethName()); System.out.println(fam.getwName());
			 */
    		Parents.add(fam.gethID());
    		Parents.add(fam.getwID());
    	}
		/*
		 * else { Parents.add("No Father"); Parents.add("No mother"); }
		 */
    	return Parents;
    }
    public static boolean firstCousinCheck(String s1,String s2)
    {
    	
    	boolean flag=false;
    	ArrayList<String> p1=getParents(s1);
    	ArrayList<String> p2=getParents(s2);
    	if(siblingCheck(s1, s2))
    	{
    		return false;
    	}
    	for(int i=0;i<p1.size();i++)
    	{
    		for(int j=0;j<p2.size();j++)
    		{
    			if(siblingCheck(p1.get(i), p2.get(j)))
    			{
    				flag=true;
    			}
    		}
    	}
    	return flag;
    }

    private void process(String line) {

        //   System.out.println("--> " + line);
        String[] splits;
        String valid = "N";
        String output;
        splits = line.split(" ", 3);
        
        if (GedMap.containsKey(splits[1])) {
            if (GedMap.get(splits[1]).equals(splits[0])) {
                valid = "Y";
                if (splits[1].equals("INDI") || splits[1].equals("FAM")) {
                    valid = "N";
                }
                try {
                    output = "<-- " + splits[0] + "|" + splits[1] + "|" + valid + "|" + splits[2];

                    this.createID(splits[2], splits[1]);
                } catch (ArrayIndexOutOfBoundsException e) {
                    output = "<-- " + splits[0] + "|" + splits[1] + "|" + valid + "|";
                    try {
                        this.createID("", splits[1]);
                    } catch (ParseException qe) {
                        System.out.println("Parse issue" + qe + " " + splits);
                    }
                } catch (ParseException e) {
                    System.out.println("Parse issue" + e + " " + splits);
                }

            } else {
                try {
                    output = "<-- " + splits[0] + "|" + splits[1] + "|" + valid + "|" + splits[2];
                } catch (ArrayIndexOutOfBoundsException e) {
                    output = "<-- " + splits[0] + "|" + splits[1] + "|" + valid + "|";
                }
            }
        } else try {
            if (GedMap.containsKey(splits[2])) {
                if (GedMap.get(splits[2]).equals(splits[0])) {
                    valid = "Y";
                    output = "<-- " + splits[0] + "|" + splits[2] + "|" + valid + "|" + splits[1];
                    if (splits[2].equals("INDI")) {
                        String id = splits[1];
                        if(Individual.containsKey(id))
                        {
                        	check=true;
                        	Errorlist.add("ERROR: INDIVIDUAL: US22 "+id+" Already exists");
                        }
                        else
                        {
                        Indiobj = new Indi(splits[1]);
                        Individual.put(id, Indiobj);
                        }
                    } else if (splits[2].equals("FAM")) {
                        String fam = splits[1];
                        if(Family.containsKey(fam))
                        {
                        	check=true;
                        	Errorlist.add("ERROR: FAMILY: US22 "+fam+" Already exists");

                        }
                        Famobj = new Fami(splits[1]);
                        Family.put(fam, Famobj);
                    }
                } else {

                    output = "<-- " + splits[0] + "|" + splits[1] + "|" + valid + "|" + splits[2];

                }
            } else {
                output = "<-- " + splits[0] + "|" + splits[1] + "|" + valid + "|" + splits[2];

            }
        } catch (ArrayIndexOutOfBoundsException e) {
            output = "<-- " + splits[0] + "|" + splits[1] + "|" + valid + "|";
        }

        //    System.out.println(output);

    }

    public void createID(String value, String tag) throws ParseException {

        if (tag.equals("NAME")) {
            Indiobj.setName(value);
        }

        if (tag.equals("SEX")) {
            Indiobj.setGender(value);
        }

        if (birt) {
            birt = false;
            SimpleDateFormat f = new SimpleDateFormat("dd MMM yyyy");
            Date bday = f.parse(value);
            birthday = bday;
            Indiobj.setBday(bday);
            Date c = new Date();
            long diffM = Math.abs(c.getTime() - bday.getTime());
            long diff = TimeUnit.DAYS.convert(diffM, TimeUnit.MILLISECONDS);
            int years = (int) diff / 365;
            Indiobj.setAge("" + years + "");
        }

        if (tag.equals("BIRT")) {
            birt = true;
        }

        if (deat) {
            deat = false;
            SimpleDateFormat f = new SimpleDateFormat("dd MMM yyyy");
            Date deatDay = f.parse(value);
            Indiobj.setDeathDay(deatDay);
            Date birthday = Indiobj.getBday();
            long diffM = Math.abs(birthday.getTime() - deatDay.getTime());
            long diff = TimeUnit.DAYS.convert(diffM, TimeUnit.MILLISECONDS);
            int years = (int) diff / 365;
            Indiobj.setAge("" + years + "");
        }

        if (tag.equals("DEAT")) {
            deat = true;
            Indiobj.setDeath();
        }

        if (tag.equals("FAMC")) {
            Indiobj.setChild(value);
        }

        if (tag.equals("FAMS")) {
            Indiobj.setSpouse(value);
        }

        //*For Family

        if (tag.equals("HUSB")) {
            Famobj.sethID(value);
            Indi x = (Indi) Individual.get(value);
            Famobj.sethName(x.getName());
        }


        if (tag.equals("WIFE")) {
            Famobj.setwID(value);
            Indi x = (Indi) Individual.get(value);
            Famobj.setwName(x.getName());
        }

        if (tag.equals("CHIL")) {
            Famobj.setcSet(value);
        }

        if (married) {
            married = false;
            SimpleDateFormat f = new SimpleDateFormat("dd MMM yyyy");
            Date marrDt = f.parse(value);
            Famobj.setMarried(marrDt);
        }

        if (tag.equals("MARR")) {
            married = true;
        }

        if (divorced) {
            divorced = false;
            SimpleDateFormat f = new SimpleDateFormat("dd MMM yyyy");
            Date divDt = f.parse(value);
            Famobj.setDivorced(divDt);
        }

        if (tag.equals("DIV")) {
            divorced = true;
        }

    }


    public void showIndiTable() {

        String align = "| %-7s | %-21s | %-8s | %-11s | %-5s | %-7s | %-11s | %-10s | %-20s|%n";
        String[] columnNames = {"ID", "Name", "Gender", "BirthDay", "Age", "Alive", "Death", "Child", "Spouse"};
        System.out.format("############################################## INDIVIDUAL TABLE ###############################################################%n");
        System.out.format("+---------+-----------------------+----------+-------------+-------+---------+-------------+------------+---------------------+%n");
        System.out.format("+ ID      | Name                  | Gender   | BirthDay    | Age   | Alive   |   Death     | Child      | Spouse              +%n");
        System.out.format("+---------+-----------------------+----------+-------------+-------+---------+-------------+------------+---------------------+%n");

        for (Map.Entry mapElement : Individual.entrySet()) {
            String key = (String) mapElement.getKey();
            Indi x = (Indi) mapElement.getValue();
            Date bday = x.getBday();
            String birthday = "NA";
            Date dday = x.getDeath();
            String death = "NA";
            SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
            if (bday != null) {
                birthday = formatter.format(bday);
            }
            if (dday != null) {
                death = formatter.format(dday);
            }

            if (x.getSpouse().isEmpty()) {
                String str1 = "NA";
                System.out.format(align, key, x.getName(), x.getGender(), birthday, x.getAge(), x.getAlive(), death, x.getChild(), str1);
            } else {
                System.out.format(align, key, x.getName(), x.getGender(), birthday, x.getAge(), x.getAlive(), death, x.getChild(), x.getSpouse());
            }

        }
        System.out.format("+---------+-----------------------+----------+-------------+-------+---------+-------------+------------+---------------------+%n");
        System.out.println();

    }

    public void showFamiTable() {

        String align = "| %-7s | %-10s | %-10s | %-10s | %-21s | %-7s | %-21s | %-46s |%n";
        System.out.format("##################################################### FAMILY TABLE #################################################################%n");
        System.out.format("+---------+------------+------------+------------+-----------------------+---------+-----------------------+------------------------------------------------+%n");
        System.out.format("+ ID      | Married    | Divorced   | Husband ID | Husband Name          | Wife ID |   Wife Name           | Children                                       + %n");
        System.out.format("+---------+------------+------------+------------+-----------------------+---------+-----------------------+------------------------------------------------+%n");

        for (Map.Entry mapElement : Family.entrySet()) {
            String key = (String) mapElement.getKey();
            Fami x = (Fami) mapElement.getValue();
            Date mDate = x.getMarried();
            String marriage = "NA";
            Date dDate = x.getDivorced();
            String divorce = "NA";
            SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
            if (mDate != null) {
                marriage = formatter.format(mDate);
            }
            if (dDate != null) {
                divorce = formatter.format(dDate);
            }

            System.out.format(align, key, marriage, divorce, x.gethID(), x.gethName(), x.getwID(), x.getwName(), x.getcSet());
            //   Object[] InsertData = {key, x.getMarried(), x.getDivorced(), x.gethID(), x.gethName(), x.getwID(), x.getwName(), x.getcSet()};
//            dtm.addRow(InsertData);

        }
        System.out.format("+---------+------------+------------+------------+-----------------------+---------+-----------------------+------------------------------------------------+%n");
    }

    public static void main(String[] args) {

        GedcomParser lr = new GedcomParser();
        try {
            //  M2 ending is erroneous data and M ending is proper data
            //BufferedReader br = new BufferedReader(new FileReader("Project01_Harishkumar_M.ged"));
            //BufferedReader br = new BufferedReader(new FileReader("Project01_Harishkumar_M2.ged"));
            BufferedReader br = new BufferedReader(new FileReader("C:\\Users\\12012\\Desktop\\CS 555\\Project01_Harishkumar_M2.ged"));
            String line = null;
            while ((line = br.readLine()) != null) {
                lr.process(line);
            }

            lr.showIndiTable();
            lr.showFamiTable();


            lr.US05();
            lr.US06();

            lr.US07();
            lr.US08();

            lr.US01();
            lr.US04();

            lr.US02();
            lr.US03();

            lr.US13();
            lr.US14();
            lr.US09();
            lr.US12();
            lr.US16();
            lr.US18();
            lr.US17();
            lr.US10();
            lr.US22();
            lr.US23();
            
            
            for (String str : Errorlist) {
                System.out.println(str);
            }
        } catch (FileNotFoundException e) {
            System.out.println("File not Found" + e);
        } catch (IOException e) {
            System.out.println("Error in IO " + e);
        }

    }

    public static boolean US05() {

        boolean flag = true;
        for (Map.Entry mapElement : Family.entrySet()) {
            Fami Famobj = (Fami) mapElement.getValue();
            String hID = Famobj.gethID();
            Indi husIndi = (Indi) Individual.get(hID);
            Date husDeath = husIndi.getDeath();
            String wID = Famobj.getwID();
            Indi wifeIndi = (Indi) Individual.get(wID);
            Date marrDt = Famobj.getMarried();
            Date wifeDeath = wifeIndi.getDeath();
            SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");

            if (husDeath != null && marrDt.compareTo(husDeath) > 0) {
                String MarrDate = formatter.format(marrDt);
                String hDeath = formatter.format(husDeath);
                Errorlist.add("Error: FAMILY: US05: " + Famobj.getFid() + " Married on " + MarrDate + " after Death of Husband " + Famobj.gethName() + Famobj.gethID() + " on " + hDeath);
                flag = false;
            }

            if (wifeDeath != null && marrDt.compareTo(wifeDeath) > 0) {
                String MarrDate = formatter.format(marrDt);
                String wDeath = formatter.format(wifeDeath);
                Errorlist.add("Error: FAMILY: US05: " + Famobj.getFid() + " Married on " + MarrDate + " after Death of Wife " + Famobj.getwName() + Famobj.getwID() + " on " + wDeath);
                flag = false;
            }
        }

        return flag;
    }

    public static boolean US06() {

        boolean flag = true;
        for (Map.Entry mapElement : Family.entrySet()) {
            Fami Famobj = (Fami) mapElement.getValue();
            String hID = Famobj.gethID();
            Indi husIndi = (Indi) Individual.get(hID);
            Date husDeath = husIndi.getDeath();
            String wID = Famobj.getwID();
            Indi wifeIndi = (Indi) Individual.get(wID);
            Date divDt = Famobj.getDivorced();
            Date wifeDeath = wifeIndi.getDeath();
            SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");

            if (husDeath != null && divDt != null && divDt.compareTo(husDeath) > 0) {
                String DivDate = formatter.format(divDt);
                String hDeath = formatter.format(husDeath);
                Errorlist.add("Error: FAMILY: US06: " + Famobj.getFid() + " Divorced on " + DivDate + " after Death of Husband " + Famobj.gethName() + Famobj.gethID() + " on " + hDeath);
                flag = false;

            }

            if (wifeDeath != null && divDt != null && divDt.compareTo(wifeDeath) > 0) {
                String DivDate = formatter.format(divDt);
                String wDeath = formatter.format(wifeDeath);
                Errorlist.add("Error: FAMILY: US06: " + Famobj.getFid() + " Divorced on " + DivDate + " after Death of Wife " + Famobj.getwName() + Famobj.getwID() + " on " + wDeath);
                flag = false;
            }
        }
        return flag;
    }

    public static boolean US08() {
        boolean flag = true;
        for (Map.Entry mapElement1 : Family.entrySet()) {
            Fami fam = (Fami) mapElement1.getValue();
            Date marrDate = fam.getMarried();
            Date divDate = fam.getDivorced();
            ArrayList<String> child = fam.getcSet();
            for (String s : child) {
                Indi ind = Individual.get(s);
                if(ind !=null)
                {
                if (ind.getBday().before(marrDate)) {
                    Errorlist.add("ERROR: FAMILY: US08: " + ind.getId() + " is born before parents Marriage " + marrDate);
                    flag = false;
                }
                }
                if (divDate != null) {
                    long diffM = (ind.getBday().getTime() - divDate.getTime());
                    long diff = TimeUnit.DAYS.convert(diffM, TimeUnit.MILLISECONDS);
                    int months = (int) (diff / 365) * 12;
                    if (months > 9) {
                        Errorlist.add("ERROR: FAMILY: US08: CHILD " + ind.getId() + " was born after 9 months of their parents Divorce " + fam.getDivorced());
                        flag = false;
                    }
                }

            }

        }
        return flag;
    }

    public static boolean US07() {
        boolean flag = true;
        for (Map.Entry mapElement : Individual.entrySet()) {
            Indi indi = (Indi) mapElement.getValue();
            int age = Integer.parseInt(indi.getAge());
            if (Integer.parseInt(indi.getAge()) > 150) {
                Errorlist.add("ERROR: INDIVIDUAL: US07: " + indi.getId() + "is older than 150 years old " + age);
                flag = false;
            }
        }
        return flag;
    }

    public static boolean US03() {
        boolean flag = true;
        for (Map.Entry mapElement : Individual.entrySet()) {
            Indi indi = (Indi) mapElement.getValue();
            String IndiId = indi.getId();
            Date birthday = indi.getBday();
            Date deathday = indi.getDeath();
            if (deathday != null && deathday.before(birthday)) {
                Errorlist.add("ERROR: INDIVIDUAL: US03: " + indi.getId() + " Deathday " + deathday + " before Birthday " + birthday);
                flag = false;

            }

        }
        return flag;

    }

    public static boolean US02() {
        boolean flag = true;
        for (Map.Entry mapElement : Family.entrySet()) {
            Fami fam = (Fami) mapElement.getValue();
            String Hid = fam.gethID();
            Indi husbId = Individual.get(Hid);
            String Wid = fam.getwID();
            Indi wifeId = Individual.get(Wid);
            if (fam.getMarried().before(husbId.getBday())) {
                Errorlist.add("ERROR: FAMILY: US02: " + fam.getFid() + " Husband's birth date " + husbId.getBday() + " after Marriage date " + fam.getMarried());
                flag = false;
            }
            if (fam.getMarried().before(wifeId.getBday())) {
                Errorlist.add("ERROR: FAMILY: US02: " + fam.getFid() + " Husband's birth date " + wifeId.getBday() + " after Marriage date " + fam.getMarried());
                flag = false;
            }

        }
        return flag;
    }

    public static boolean US01() {
        boolean flag = true;
        Date current = new Date();
        for (Map.Entry mapElement : Individual.entrySet()) {
            Indi indi = (Indi) mapElement.getValue();
            String IndiId = indi.getId();
            Date birthday = indi.getBday();
            Date deathday = indi.getDeath();
            if (current.before(birthday)) {
                Errorlist.add("ERROR: INDIVIDUAL: US01: " + indi.getId() + "BirthDay " + birthday + " is from future");
                flag = false;
            }
            if (deathday != null && current.before(deathday)) {
                Errorlist.add("ERROR: INDIVIDUAL: US01: " + indi.getId() + " DeathDay " + deathday + " is from future");
                flag = false;
            }
        }
        for (Map.Entry mapElement1 : Family.entrySet()) {
            Fami fam = (Fami) mapElement1.getValue();
            Date marrDate = fam.getMarried();
            Date divDate = fam.getDivorced();
            if (current.before(marrDate)) {
                Errorlist.add("ERROR: FAMILY: US01: " + fam.getFid() + " MArriageDay " + marrDate + " is from future");
                flag = false;

            }
            if (divDate != null && current.before(divDate)) {
                Errorlist.add("ERROR: FAMILY: US01: " + fam.getFid() + " DivorceDay " + divDate + " is from future");
                flag = false;
            }


        }
        return flag;
    }

    public static boolean US04() {
        boolean flag = true;
        for (Map.Entry mapElement1 : Family.entrySet()) {
            Fami fam = (Fami) mapElement1.getValue();
            Date marrDate = fam.getMarried();
            Date divDate = fam.getDivorced();
            if (divDate != null && divDate.before(marrDate)) {
                Errorlist.add("ERROR: FAMILY: US04: " + fam.getFid() + "Divorce " + divDate + " happened before Marriage " + marrDate);
                flag = false;

            }

        }

        return flag;
    }

    public static boolean US13() {
        boolean flag = true;
        for (Map.Entry mapElement1 : Family.entrySet()) {
            Fami fam = (Fami) mapElement1.getValue();
            ArrayList<String> childs = fam.getcSet();
            if (childs.size() > 1) {
                for (int i = 0; i < childs.size(); i++) {
                    for (int j = i + 1; j < childs.size() - 1; j++) {
                        if (Period.between(Instant.ofEpochMilli(Individual.get(childs.get(i)).getBday().getTime()).atZone(ZoneId.systemDefault()).toLocalDate(), Instant.ofEpochMilli(Individual.get(childs.get(j)).getBday().getTime()).atZone(ZoneId.systemDefault()).toLocalDate()).getYears() * 12 < 8 && Period.between(Instant.ofEpochMilli(Individual.get(childs.get(i)).getBday().getTime()).atZone(ZoneId.systemDefault()).toLocalDate(), Instant.ofEpochMilli(Individual.get(childs.get(j)).getBday().getTime()).atZone(ZoneId.systemDefault()).toLocalDate()).getDays() >= 2) {
                            Errorlist.add("ERROR: FAMILY: US13: Siblings " + Individual.get(childs.get(i)).getName() + " & " + Individual.get(childs.get(j)).getName() + " are born within 8 months");
                            flag = false;
                        } else if (Period.between(Instant.ofEpochMilli(Individual.get(childs.get(i)).getBday().getTime()).atZone(ZoneId.systemDefault()).toLocalDate(), Instant.ofEpochMilli(Individual.get(childs.get(j)).getBday().getTime()).atZone(ZoneId.systemDefault()).toLocalDate()).getYears() / 365 > 2) {
                            Errorlist.add("ERROR: FAMILY: US13: Siblings " + Individual.get(childs.get(i)).getName() + " & " + Individual.get(childs.get(j)).getName() + " are born after 2 days");
                            flag = false;
                        }

                    }
                }
            }
        }
        return flag;
    }

    public static boolean US14() {
        boolean flag = true;
        for (Map.Entry mapElement1 : Family.entrySet()) {
            int count = 0;
            Fami fam = (Fami) mapElement1.getValue();
            ArrayList<String> childs = fam.getcSet();
            if (childs.size() > 5)
                for (int i = 0; i < childs.size(); i++) {
                    for (int j = i + 1; j < childs.size() - 1; j++) {
                        if (Individual.get(childs.get(i)).getBday().equals(Individual.get(childs.get(j)).getBday())) {
                            count++;
                        }
                    }
                }
            if (count > 5) {
                Errorlist.add("ERROR: FAMILY: US14: More than 5 siblings are born together in " + fam.getFid());
                flag = false;

            }
        }


        return flag;
    }

    public static boolean US12() {
        boolean flag = true;
        for (Map.Entry mapElement1 : Family.entrySet()) {
            Fami fam = (Fami) mapElement1.getValue();
            ArrayList<String> childs = fam.getcSet();
            String husb = fam.gethID();
            String wife = fam.getwID();
            Indi father = Individual.get(husb);
            Indi mother = Individual.get(wife);
            Date fatherBday = father.getBday();
            Date motherBday = mother.getBday();
            for (String s : childs) {
                Indi ind = Individual.get(s);
                if(ind!=null)
                {
                Period cal1 = Period.between(Instant.ofEpochMilli(fatherBday.getTime()).atZone(ZoneId.systemDefault()).toLocalDate(), Instant.ofEpochMilli(ind.getBday().getTime()).atZone(ZoneId.systemDefault()).toLocalDate());
                Period cal2 = Period.between(Instant.ofEpochMilli(motherBday.getTime()).atZone(ZoneId.systemDefault()).toLocalDate(), Instant.ofEpochMilli(ind.getBday().getTime()).atZone(ZoneId.systemDefault()).toLocalDate());
                if (cal1.getYears() > 80) {
                    Errorlist.add("ERROR: FAMILY: US12 " + fam.getFid() + "Father " + father.getId() + " is more than 80 years older than his child" + ind.getId());
                    flag = false;
                }
                if (cal2.getYears() > 60) {
                    Errorlist.add("ERROR: FAMILY: US12 " + fam.getFid() + "Mother " + mother.getId() + " is more than 80 years older than his child" + ind.getId());
                    flag = false;
                }
            }


        }
        }

        return flag;

    }

    public static boolean US18() {
        boolean flag = true;
        for (Map.Entry mapElement1 : Family.entrySet()) {
            Fami fam = (Fami) mapElement1.getValue();
            String fID = fam.getFid();

            ArrayList<String> childs = fam.getcSet();
            for (int i = 0; i < childs.size(); i++) {
            	if(Individual.containsKey(childs.get(i)))
            	{
                ArrayList<String> spouses = Individual.get(childs.get(i)).getSpouse();
                for (int j = i + 1; j < spouses.size() - 1; j++) {
                    ArrayList<String> common = spouses;
                    //boolean f=spouses.retainAll(Individual.get(childs.get(i)).getSpouse());
                    //System.out.println("boolean"+f);
                    common.retainAll(Individual.get(childs.get(i)).getSpouse());
                    if (common.size() > 0) {
                        Errorlist.add("ERROR: FAMILY: US18: " + fam.getFid() + " Two siblings are married to each other");
                        flag = false;
                    }
                }
            }
            }


        }
        return flag;
    }

    public static boolean US09() {
        boolean flag = true;
        for (Map.Entry mapElement1 : Family.entrySet()) {
            Fami fam = (Fami) mapElement1.getValue();
            ArrayList<String> childs = fam.getcSet();
            String husb = fam.gethID();
            String wife = fam.getwID();
            Indi father = Individual.get(husb);
            Indi mother = Individual.get(wife);
            Date fatherDday = father.getDeath();
            Date motherDday = mother.getDeath();

            for (String s : childs) {
                Indi ind = Individual.get(s);
                if (motherDday != null && motherDday.before(ind.getBday())) {
                    Errorlist.add("ERROR: FAMILY: US09: " + ind.getId() + " BirthDay " + ind.getBday() + " is after Mothers death " + motherDday);
                    flag = false;
                }
                if (fatherDday != null && Period.between(Instant.ofEpochMilli(fatherDday.getTime()).atZone(ZoneId.systemDefault()).toLocalDate(), Instant.ofEpochMilli(ind.getBday().getTime()).atZone(ZoneId.systemDefault()).toLocalDate()).getMonths() > 9) {
                    Errorlist.add("ERROR: FAMILY: US09: " + ind.getId() + " BirthDay " + ind.getBday() + " is after 9 months of Fathers death " + fatherDday);
                    flag = false;
                }


            }


        }
        return flag;

    }


    public static boolean US16() {
        boolean flag = true;
        for (Map.Entry mapElement1 : Family.entrySet()) {
            Fami fam = (Fami) mapElement1.getValue();
            ArrayList<String> childs = fam.getcSet();
            String husbName = fam.gethName();
            String surName = husbName.substring(husbName.lastIndexOf(" ") + 1);
            for (String s : childs) {
            	
                if (Individual.containsKey(s)&&Individual.get(s).getGender().equals("M") && !Individual.get(s).getName().substring(Individual.get(s).getName().lastIndexOf(" ") + 1).equals(surName)) {
                    Errorlist.add("ERROR: FAMILY: US16: Child " + Individual.get(s).getId() + " has a different last name than the family name " + surName);
                    flag = false;
                }
            
            }

        }
        return false;


    }

    public static boolean US10() {
        boolean flag = true;
        for (Map.Entry mapElement1 : Family.entrySet()) {
            Fami fam = (Fami) mapElement1.getValue();
            Date marrDate = fam.getMarried();
            String hID = fam.gethID();
            Indi husIndi = (Indi) Individual.get(hID);
            Date husBday = husIndi.getBday();
            String wID = fam.getwID();
            Indi wifeIndi = (Indi) Individual.get(wID);
            Date wifeBday = wifeIndi.getBday();

            long diffM = Math.abs(marrDate.getTime() - husBday.getTime());
            long diff = TimeUnit.DAYS.convert(diffM, TimeUnit.MILLISECONDS);
            int husDiff = (int) diff / 365;

            long diffMw = Math.abs(marrDate.getTime() - wifeBday.getTime());
            long diffw = TimeUnit.DAYS.convert(diffMw, TimeUnit.MILLISECONDS);
            int wifeDiff = (int) diffw / 365;

            Date divDate = fam.getDivorced();
            if (husDiff < 14 || wifeDiff < 14) {
                Errorlist.add("ERROR: FAMILY: US10: " + fam.getFid() + " husband or/and wife age less than 14 on marriage day Husband ID " + hID + " and Wife ID " + wID);
                flag = false;

            }

        }

        return flag;
    }

    public static boolean US17() {
        boolean flag = true;
        for (Map.Entry mapElement1 : Family.entrySet()) {
            Fami fam = (Fami) mapElement1.getValue();
            String hID = fam.gethID();
            Indi husIndi = (Indi) Individual.get(hID);
            String husChldOf = husIndi.getChild();
            ArrayList<String> husSpouseOf = husIndi.getSpouse();
            String wID = fam.getwID();
            Indi wifeIndi = (Indi) Individual.get(wID);
            String wifeChldOf = wifeIndi.getChild();
            ArrayList<String> wifeSpouseOf = wifeIndi.getSpouse();

            if (husSpouseOf.contains(wifeChldOf) || wifeSpouseOf.contains(husChldOf)) {
                Errorlist.add("ERROR: FAMILY: US17: " + fam.getFid() + " One of the partner in this family is a child of another ");
                flag = false;

            }

        }

        return flag;
    }
    public static boolean US22()
    {
    	boolean flag=true;
    	if(GedcomParser.check)
    	{
    		flag=false;
    	}
    	return flag;
    }
    
    public static boolean US23()
    {
    	boolean flag=true;
    	TreeMap<String, Indi> t1=new TreeMap<String, GedcomParser.Indi>();
    	t1.putAll(Individual);
    	for(Map.Entry mapElement1:Individual.entrySet())
    	{
    		Indi ind=(Indi) mapElement1.getValue();
    		for(Map.Entry mapElement2:t1.entrySet())
    		{
    			if(!mapElement1.getKey().equals(mapElement2.getKey()) && mapElement1.getKey().toString().compareTo(mapElement2.getKey().toString())<0)
    					{
    	    				Indi ind2=(Indi) mapElement2.getValue();
    	    				
    						if(ind.getName().equals(ind2.getName())&&ind.getBday().equals(ind2.getBday()))
    								{
    			                		Errorlist.add("ERROR: INDIVIDUAL: US23: Two people "+ind.getId()+" & "+ind2.getId()+" with same name and same Bday found ");

    								}
    					}
    		}
    		
    	}
    	
		return flag;
    }

    
    //
    
    
}