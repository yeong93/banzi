package com.kh.banzi.common;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;

import com.oreilly.servlet.multipart.FileRenamePolicy;

public class MyFileRenamePolicy implements FileRenamePolicy {

    @Override
    public File rename(File originFile) {
        // 원본 파일명 -> 20200723102231_12345.gif
        
        long currentTime = System.currentTimeMillis();
        // 1970년 1월 1일 오전9시 (대한민국 기준) 기준으로
        // 현재 시간까지의 ms를 반환
        System.out.println();
        
        int ranNum = (int)(Math.random() * 100000);
        
        String str = "_" +String.format("%05d", ranNum);
        // %05d : 오른쪽 정렬된 십진 정수(d) 5자리(5)형태로 변경. 빈자리는 0으로 채움(0)
        
        // 파일명을 변경해도 확장자를 유지하기
        String name = originFile.getName(); // 원본 파일 명
        String ext = null; // 확장자를 저장할 String 변수
        
        int dot = name.lastIndexOf("."); // 파일명 중 "." 인덱스 반환
        
        // 파일명에 "."이 없을 경우
        if(dot != -1) {
            ext = name.substring(dot); // "."포함 이후 문자열을 반환함.
        }else {
            ext = "";
        }
        
        SimpleDateFormat stf = new SimpleDateFormat("yyyyMMddHHmmss");
        String fileName = stf.format(new Date(currentTime)) + str + ext;
        
        File newFile = new File(originFile.getParent(), fileName);
        // originFile.getParent() : 기존 파일이 저장된 폴더 경로 반환
        // 기존 파일 이름을 새 파일명으로 변경
        
        return newFile;
    }

}
