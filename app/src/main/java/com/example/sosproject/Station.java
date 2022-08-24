package com.example.sosproject;

public class Station {
    Name cname = new Name();
    Num cnum = new Num();
    Fare cfare = new Fare();
    StartEnd cstartEnd = new StartEnd();
    double[] incomeGradeMi = {0.0, 0.3, 0.5, 0.7, 0.9, 1.0, 1.3, 1.5, 2.0, 3.0};

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

    public int getNewFare(int originalFare, int incomeGrade, int age){
        double rate;
        int flag;

        // 65세 이상 && 소득분위 5분위 미만: 0원
        // 65세 이상 && 소득분위 5분위 이상: 성인 요금 * (소득분위 기준 중위소득 비율 / 2)
        // 단, 만약 이렇게 계산한 금액이 원래 성인 요금을 초과한다면, 성인 요금으로 부과
        if (age >= 65) {
            if (incomeGrade < 5) return 0;

            rate = incomeGradeMi[incomeGrade - 1] * 0.5;
            if (rate > 1)
                rate = 1;
            flag = 0;
        }

        // 65세 미만: 성인 요금 + 성인 요금 * (소득분위 기준 중위소득 비율 / 4)
        else {
            rate = incomeGradeMi[incomeGrade - 1] * 0.25;
            flag = 1;
        }

        int rawNewFare = (int) (originalFare * (flag + rate));  // 계산 & 소수점 날리기
        return rawNewFare - rawNewFare % 10;  // 1의 자리 날리기
    }

    public int originalFare(int startNum, int endNum){
        String startName = this.num2name(startNum);
        String endName = this.num2name(endNum);
        if (!startName.equals("None") && !endName.equals("None")){
            int idx = cstartEnd.startEnd.indexOf(startName + endName);
            return cfare.fare.get(idx);
        }
        return -1;
    }

    public int getFareFromNum(int startNum, int endNum, int age){
        int fare = originalFare(startNum, endNum);
        if (fare == -1) return -1;
        else if (age >= 65) return 0;
        else return fare;
    }

    public String getLineNum(String stationName){
        String lineNum = "";
        return Integer.toString(this.name2num(stationName) / 100);
    }

    public int getFareFromName(String startName, String endName, int age){
        int startNum = this.name2num(startName);
        int endNum = this.name2num(endName);
        if (startNum != -1 && endNum != -1){
            return this.getFareFromNum(startNum, endNum, age);
        }
        return -1;
    }
}
