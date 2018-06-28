package com.funweb.web.test;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
 
 
public class ScheduleExecTest {
 
    public static void main(String[] args) {
        // TODO Auto-generated method stub
 
         
        // 실행간격 지정(3초)
        int sleepSec = 3 ;
         
        // 시간 출력 포맷
        final SimpleDateFormat fmt = new SimpleDateFormat("HH:mm:ss");
 
         
        // 주기적인 작업을 위한
        final ScheduledThreadPoolExecutor exec = new ScheduledThreadPoolExecutor(1);
         
         
        exec.scheduleAtFixedRate(new Runnable(){
             
            public void run(){
                try {
 
                    Calendar cal = Calendar.getInstance() ;
                     
                     
                    // 콘솔에 현재 시간 출력
                    System.out.println(fmt.format(cal.getTime())) ;
                     
                } catch (Exception e) {
                     
                    e.printStackTrace();
                     
                     
                    // 에러 발생시 Executor를 중지시킨다
                    exec.shutdown() ;
                }
            }
        }, 0, sleepSec, TimeUnit.SECONDS);
        
    }
 
}


