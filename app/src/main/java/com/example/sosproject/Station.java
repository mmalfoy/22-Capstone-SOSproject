package com.example.sosproject;
import java.lang.Math;

public class Station {
//    public Station() {
//    }
//
//    private String rideStation;
//    private String quitStation;
//
//    public String getRideStation(){ return rideStation; }
//    public String getQuitStation(){ return quitStation; }

    public boolean isRightNum(int num){
        return (300 <= num && num < 300 + stations_3.length) || (400 <= num && num < 400 + stations_4.length);
    }

    private final static String[] stations_3 = {
        "대화", "주엽", "정발산", "마두", "백석",
        "대곡", "화정", "원당", "원흥", "삼송",
        "지축", "구파발", "연신내", "불광", "녹번",
        "홍제", "무악재", "독립문", "경복궁", "안국",
        "종로3가", "을지로3가", "충무로", "동대입구", "약수",
        "금호", "옥수", "압구정", "신사", "잠원",
        "고속터미널", "교대", "남부터미널", "양재", "매봉",
        "도곡", "대치", "학여울", "대청", "일원",
        "수서", "가락시장", "경찰병원", "오금"
    };

    private final static String[] stations_4 = {
        "당고개", "상계", "노원", "창동", "쌍문",
        "수유", "미아", "미아사거리", "길음", "성신여대입구",
        "한성대입구", "혜화", "동대문", "동대문역사문화공원", "충무로",
        "명동", "회현", "서울역", "숙대입구", "삼각지",
        "신용산", "이촌", "동작", "총신대입구(이수)", "사당",
        "남태령", "선바위", "경마공원", "대공원", "과천",
        "정부과천청사", "인덕원", "평촌", "범계", "금정",
        "산본", "수리산", "대야미", "반원", "상록수",
        "한대앞", "중앙", "고잔", "초지", "안산",
        "신길온천", "정왕", "오이도"
    };

    // 없는 역일 경우 -1 반환
    public int string2num(String str){
        for (int i = 0; i < stations_3.length; ++i){
            if (stations_3[i].equals(str)){
                return 300 + i;
            }
        }
        for (int i = 0; i < stations_4.length; ++i){
            if (stations_4[i].equals(str)){
                return 400 + i;
            }
        }
        return -1;
    }

    // 없는 번호일 경우 "None" 반환
    public String num2string(int num){
        if (300 <= num && num < 300 + stations_3.length){
            return stations_3[num - 300];
        }
        else if (400 <= num && num < 400 + stations_4.length) {
            return stations_4[num - 400];
        }
        else{
            return "None";
        }
    }

    public int getDiff(int num1, int num2){
        if (isRightNum(num1) && isRightNum(num2)) {
            if (num1 % 100 == num2 % 100){
                return Math.abs(num1 - num2);
            }
            else{
                if (num1 % 100 == 4){
                    int temp = num1;
                    num1 = num2;
                    num2 = temp;
                }
                return Math.abs(num1 - 322) + Math.abs(num2 - 414);
            }
        }
        return -1;
    }
}
