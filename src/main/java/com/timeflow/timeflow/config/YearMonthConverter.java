package com.timeflow.timeflow.config;
import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;
import java.time.YearMonth;
import java.time.format.DateTimeFormatter;
@Converter(autoApply = true)
public class YearMonthConverter implements AttributeConverter<YearMonth, String> {
    private static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM");

    @Override
    public String convertToDatabaseColumn(YearMonth attribute) {
        if (attribute == null) {
            return null;
        }
        return attribute.format(FORMATTER);
    }

    @Override
    public YearMonth convertToEntityAttribute(String dbData) {
        if (dbData == null || dbData.isBlank()) {
            return null;
        }
        return YearMonth.parse(dbData, FORMATTER);
    }
}