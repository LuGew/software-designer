package com.lugew.study.softwaredesigner.csv;

import com.lugew.study.softwaredesigner.csv.entity.BusinessLogForCsv;
import com.opencsv.bean.*;
import com.opencsv.exceptions.CsvDataTypeMismatchException;
import com.opencsv.exceptions.CsvRequiredFieldEmptyException;
import lombok.experimental.UtilityClass;
import lombok.extern.slf4j.Slf4j;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Slf4j
@UtilityClass
public class CsvUtil {
    /**
     * 解析csv文件并转成bean
     *
     * @param inputStream csv文件
     * @param clazz       类
     * @param <T>         泛型
     * @return 泛型bean集合
     */
    public <T> List<T> readCsv2List(InputStream inputStream, Class<T> clazz) {
        InputStreamReader in = null;
        try {
            in = new InputStreamReader(inputStream);
        } catch (Exception e) {
            e.printStackTrace();
        }

        HeaderColumnNameMappingStrategy<T> strategy = new HeaderColumnNameMappingStrategy<>();
        strategy.setType(clazz);

        CsvToBean<T> csvToBean = new CsvToBeanBuilder<T>(in)
                /*.withSeparator(',')
                .withQuoteChar('\'')*/
                .withMappingStrategy(strategy).build();
        return csvToBean.parse();
    }


    public <T> void writeList2Csv(List<T> list, Class<T> clazz) {
        String fileName = "d:/cars.csv";
        Path myPath = Paths.get(fileName);
        try (BufferedWriter writer = Files.newBufferedWriter(myPath,
                StandardCharsets.UTF_8)) {
            HeaderColumnNameMappingStrategy<T> strategy = new HeaderColumnNameMappingStrategy<>();
            strategy.setType(clazz);
            StatefulBeanToCsv<T> beanToCsv = new StatefulBeanToCsvBuilder<T>(writer)
                    .withMappingStrategy(strategy)
                    .build();
            beanToCsv.write(list);
        } catch (CsvDataTypeMismatchException | CsvRequiredFieldEmptyException |
                IOException ex) {
            log.error("写入csv文件出错，文件：{}，错误：{}", fileName, ex.getMessage());
            ex.printStackTrace();
        }
    }


   /* public static void main(String[] args) {
        List<BusinessLogForCsv> businessLogForCsvs = new ArrayList<>();
        businessLogForCsvs.add(new BusinessLogForCsv(1L, new Date(), "ok"));
        businessLogForCsvs.add(new BusinessLogForCsv(
                1L,
                24L,
                "module",
                "content",
                "operationType",
                new Date(),
                "58.40.131.162",
                "inputParam",
                1,
                "failCause"

        ));

        CsvUtil.writeList2Csv(businessLogForCsvs, BusinessLogForCsv.class);


    }*/
    public static void main(String[] args) {

        try {
            InputStream inputStream = new FileInputStream(System.getProperty("user.dir") + "/cars.csv");
            List<BusinessLogForCsv> logForCsvs = CsvUtil.readCsv2List(inputStream, BusinessLogForCsv.class);
            System.out.println(logForCsvs.size());
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }
}
