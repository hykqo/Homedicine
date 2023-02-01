package home.medecine.service;

import org.springframework.stereotype.Service;

import java.text.DecimalFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public interface FormatterService {
    public String formatByDate(LocalDate data);
    public String formatByDateTime(LocalDateTime data);
    public String formatByDateSpace(LocalDateTime data);
    public LocalDateTime StringToLocalDateTime(String date, String time);
    public String formatByPrice(Integer data);
    public String formatByPoint(double point);
    public String lineSeparator();
}
