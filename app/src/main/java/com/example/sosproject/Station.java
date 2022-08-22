package com.example.sosproject;

public class Station {
    Name cname = new Name();
    Num cnum = new Num();
    Fare cfare = new Fare();
    StartEnd cstartEnd = new StartEnd();

    // 없는 역일 경우 -1 반환
    public int name2num(String str){
        if (cname.stationName.contains(str)) {
            int idx = cname.stationName.indexOf(str);
            return cnum.stationNum.get(idx);
        }
        else
            return -1;
    }

    // 없는 번호일 경우 "None" 반환
    public String num2name(int num){
        if (cnum.stationNum.contains(num)) {
            int idx = cnum.stationNum.indexOf(num);
            return cname.stationName.get(idx);
        }
        else
            return "None";
    }

    public int getFareFromNum(int startNum, int endNum){
        String startName = this.num2name(startNum);
        String endName = this.num2name(endNum);
        if (!startName.equals("None") && !endName.equals("None")){
            int idx = cstartEnd.startEnd.indexOf(startName + endName);
            return cfare.fare.get(idx);
        }
        return -1;
    }

    public int getFareFromName(String startName, String endName){
        int startNum = this.name2num(startName);
        int endNum = this.name2num(endName);
        if (startNum != -1 && endNum != -1){
            int idx = cstartEnd.startEnd.indexOf(startName + endName);
            return cfare.fare.get(idx);
        }
        return -1;
    }
}
