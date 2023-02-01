package home.medecine.serviceImpl.formatter;

import home.medecine.service.FormatterService;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

import java.text.DecimalFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Service
@Primary
public class FormatterServiceImpl implements FormatterService {

    private DateTimeFormatter formatterDate;

    //시간 formatter. localDate to String(YY/MM/DD)
    public String formatByDate(LocalDate data){
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yy/MM/dd");
        String result = data.format(dateTimeFormatter);
        return result;
    }
    //시간 formatter. localDateTime to String(YY/MM/DD)
    public String formatByDateTime(LocalDateTime data){
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yy/MM/dd");
        String result = data.format(dateTimeFormatter);
        return result;
    }
    //시간 formatter. localDate to String(YY-MM-DD)
    public String formatByDateSpace(LocalDateTime data){
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        String result = data.format(dateTimeFormatter);
        return result;
    }

    public LocalDateTime StringToLocalDateTime(String date, String time){
        formatterDate = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        LocalDateTime result = LocalDateTime.parse(date + " "+time, formatterDate);
        return result;

    }

    //가격 formatter. Integer to String(₩ 0,000)
    public String formatByPrice(Integer data){
        DecimalFormat formatter = new DecimalFormat();
        return  formatter.format(data.intValue());
    }

    //포인트 소수,정수 환산.
    /*
    소수점 있을 경우 double형 사용.
    소수점 없을 경우 Int형 사용.
    포맷된 숫자는 문자형으로 변환하여 리턴.
    * */
    public String formatByPoint(double point){
        String formatPoint;
        int intPoint = (int) point;
        if(intPoint == point) formatPoint = Integer.toString(intPoint);
        else formatPoint = Double.toString(point);
        return formatPoint;
    }
    //개행 관련.
    public String lineSeparator(){
        String nlString = System.getProperty("line.separator").toString();
        return nlString;
    }

}
