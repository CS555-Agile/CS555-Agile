import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.io.*;
import java.util.concurrent.TimeUnit;



public class GedcomParser {

    HashMap<String, String> GedMap = new HashMap<>();
    //HashMap<String, Indi> Individual = new HashMap<>();
    static TreeMap<String, Indi> Individual = new TreeMap<>(new Comparator<String>() {
        @Override
        public int compare(String o1, String o2) {
            int a1 = Integer.valueOf(o1.substring(2, o1.length() - 1));
            int a2 = Integer.valueOf(o2.substring(2, o2.length() - 1));
            return Integer.compare(a1, a2);
        }
    });
    // HashMap<String, Fami> Family = new HashMap<>();
    static TreeMap<String, Fami> Family = new TreeMap<>(new Comparator<String>() {
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
    static ArrayList<String> errorList=new ArrayList<String>();


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

//            String birthDay = "NA";
//            if (bday != null) {
//                SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
//                birthDay = formatter.format(bday);
//            }
//            return birthDay;
            return bday;
        }

        public String getAge() {
            return age;
        }

        public String getAlive() {
            return alive;
        }

        public Date getDeath() {

//            String deathDay = "NA";
//            if (death != null) {
//                SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
//                deathDay = formatter.format(death);
//            }
//            return deathDay;
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

//            String marrDate = "NA";
//            if (married != null) {
//                SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
//                marrDate = formatter.format(married);
//            }
//            return marrDate;

            return married;
        }

        public void setMarried(Date married) {

            this.married = married;
        }

        public Date getDivorced() {

//            String divDate = "NA";
//            if (divorced != null) {
//                SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
//                divDate = formatter.format(divorced);
//            }
//            return divDate;

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
                        Indiobj = new Indi(splits[1]);
                        Individual.put(id, Indiobj);
                    } else if (splits[2].equals("FAM")) {
                        String fam = splits[1];
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
    	Date current=new Date();
        if (tag.equals("NAME")) {
            Indiobj.setName(value);
        }

        if (tag.equals("SEX")) {
            Indiobj.setGender(value);
        }

        if (birt) {//igonre this for now shubham, not working now, will try solving tommorw
            birt = false;
            SimpleDateFormat f = new SimpleDateFormat("dd MMM yyyy");
            Date bday = f.parse(value);
            
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
            String IndiId=Indiobj.getId();
            Indi indi=Individual.get(IndiId);
            Date birthday=indi.getBday();
            
            long diffM = Math.abs(birthday.getTime() - deatDay.getTime());
            long diff = TimeUnit.DAYS.convert(diffM, TimeUnit.MILLISECONDS);
            int years = (int) diff / 365;
            Indiobj.setAge("" + years + "");
            Indiobj.setDeathDay(deatDay);   
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
            String Fid=Famobj.getFid();
            Fami fam=Family.get(Fid);
            
            SimpleDateFormat f = new SimpleDateFormat("dd MMM yyyy");
            Date divDt = f.parse(value);
           
            
            Famobj.setDivorced(divDt);
            }
        

        if (tag.equals("DIV")) {
            divorced = true;
        }

    }
    public static boolean US01()
    {
    	boolean flag=true;
    	Date current=new Date();
    	for (Map.Entry mapElement : Individual.entrySet()) {
    	Indi indi=(Indi) mapElement.getValue();	
    	String IndiId=indi.getId();
        Date birthday=indi.getBday();
        Date deathday=indi.getDeath();
        if(current.before(birthday))
        {
        	errorList.add("ERROR: INDIVIDUAL: US01: "+indi.getId()+"BirthDay "+birthday+" is from future");
        	flag=true;
        }
        if(deathday!=null && current.before(deathday))
        {
        	errorList.add("ERROR: INDIVIDUAL- US01: "+indi.getId()+" DeathDay "+deathday+" is from future");
        	flag=true;
        }  
    	}
    	for (Map.Entry mapElement1 : Family.entrySet()) {
        	Fami fam=(Fami) mapElement1.getValue();
        	Date marrDate=fam.getMarried();
        	Date divDate=fam.getDivorced();
        	if(current.before(marrDate))
        	{
            	errorList.add("ERROR: FAMILY- US01: "+fam.getFid()+" MArriageDay "+marrDate+" is from future");
            	flag=true;

        	}
        	if(divDate!=null && current.before(divDate))
        	{
        		errorList.add("ERROR: FAMILY- US01: "+fam.getFid()+" DivorceDay "+divDate+" is from future");
        	}
        	
        	
        	
    	}
    	return flag;
    }
    public static boolean US04()
    {
    	boolean flag=true;
    	for (Map.Entry mapElement1 : Family.entrySet()) {
        	Fami fam=(Fami) mapElement1.getValue();
        	Date marrDate=fam.getMarried();
        	Date divDate=fam.getDivorced();
        	if(divDate!=null && divDate.before(marrDate))
        	{
            	errorList.add("ERROR: FAMILY- US04 "+fam.getFid()+"Divorce "+divDate+" happened before Marriage "+marrDate);
            	flag=true;

        	}
        	
        	
        	
        	
    	}
    	
    	return flag;
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
            if(bday != null){
                birthday = formatter.format(bday);
            }
            if(dday != null){
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

        String align = "| %-7s | %-10s | %-10s | %-10s | %-21s | %-7s | %-21s | %-21s |%n";
        System.out.format("##################################################### FAMILY TABLE #################################################################%n");
        System.out.format("+---------+------------+------------+------------+-----------------------+---------+-----------------------+-----------------------+%n");
        System.out.format("+ ID      | Married    | Divorced   | Husband ID | Husband Name          | Wife ID |   Wife Name           | Children              +%n");
        System.out.format("+---------+------------+------------+------------+-----------------------+---------+-----------------------+-----------------------+%n");

        for (Map.Entry mapElement : Family.entrySet()) {
            String key = (String) mapElement.getKey();
            Fami x = (Fami) mapElement.getValue();
            Date mDate = x.getMarried();
            String marriage = "NA";
            Date dDate = x.getDivorced();
            String divorce = "NA";
            SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
            if(mDate != null){
                marriage = formatter.format(mDate);
            }
            if(dDate != null){
                divorce = formatter.format(dDate);
            }

            System.out.format(align, key, marriage, divorce, x.gethID(), x.gethName(), x.getwID(), x.getwName(), x.getcSet());
            //   Object[] InsertData = {key, x.getMarried(), x.getDivorced(), x.gethID(), x.gethName(), x.getwID(), x.getwName(), x.getcSet()};
//            dtm.addRow(InsertData);

        }
        System.out.format("+---------+------------+------------+------------+-----------------------+---------+-----------------------+-----------------------+%n");
    }

    public static void main(String[] args) {

        GedcomParser lr = new GedcomParser();
        try {
            //  BufferedReader br = new BufferedReader(new FileReader("proj02test.ged"));
            BufferedReader br = new BufferedReader(new FileReader("C:\\Users\\12012\\Desktop\\CS 555\\Project01_Harishkumar_M2.ged"));
            String line = null;
            while ((line = br.readLine()) != null) {
                lr.process(line);
            }

            lr.showIndiTable();
            lr.showFamiTable();
            lr.US01();
            lr.US04();
            for(String str:errorList)
            {
            System.out.println(str);
            }
        } catch (FileNotFoundException e) {
            System.out.println("File not Found" + e);
        } catch (IOException e) {
            System.out.println("Error in IO " + e);
        }

    }
}
