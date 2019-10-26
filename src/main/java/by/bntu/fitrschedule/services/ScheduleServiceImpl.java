package by.bntu.fitrschedule.services;

import by.bntu.fitrschedule.config.ProjectConfig;
import by.bntu.fitrschedule.domain.ScheduleData;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.io.*;
import java.net.URL;
import java.nio.file.*;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ScheduleServiceImpl implements ScheduleService {

    @Autowired
    private ProjectConfig projectConfig;

    private static List<ScheduleData> fullSchedule;

    @PostConstruct
    private void init() {
        if(fullSchedule == null) {
            downloadAllSchedules();
            loadFullSchedule();
        }
    }

    private void downloadAllSchedules() {
        try {
            if (!Files.exists(Paths.get("./temp"))) Files.createDirectory(Paths.get("./temp"));

            InputStream inputStream = null;
            if (!Files.exists(Paths.get("./temp/course1.xls"))) {
                inputStream = new URL(projectConfig.getFirstCourseScheduleUrl()).openStream();
                Files.copy(inputStream, Paths.get("./temp/course1.xls"), StandardCopyOption.REPLACE_EXISTING);
                inputStream.close();
            }
            if (!Files.exists(Paths.get("./temp/course1.xls"))) {
                inputStream = new URL(projectConfig.getSecondCourseScheduleUrl()).openStream();
                Files.copy(inputStream, Paths.get("./temp/course1.xls"), StandardCopyOption.REPLACE_EXISTING);
                inputStream.close();
            }
            if (!Files.exists(Paths.get("./temp/course3and4.xls"))) {
                inputStream = new URL(projectConfig.getThirdAndFourthCourseScheduleUrl()).openStream();
                Files.copy(inputStream, Paths.get("./temp/course3and4.xls"), StandardCopyOption.REPLACE_EXISTING);
                inputStream.close();
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    //TODO: Parse First Course Schedule
    private List<ScheduleData> getFirstCourseSchedule() throws IOException {
        List<ScheduleData> scheduleData = new ArrayList<ScheduleData>();
        return scheduleData;
    }

    //TODO: Parse Second Course Schedule
    private List<ScheduleData> getSecondCourseSchedule() throws IOException {
        List<ScheduleData> scheduleData = new ArrayList<ScheduleData>();
        return scheduleData;
    }

    private List<ScheduleData> getThirdCourseSchedule() throws IOException {
        List<ScheduleData> scheduleData = new ArrayList<ScheduleData>();
        FileInputStream inputStream = new FileInputStream("./temp/course3and4.xls");
        Workbook workbook = new HSSFWorkbook(inputStream);
        Sheet sheet = workbook.getSheetAt(2);

        //TODO: Parse Excel File
        for (int groupNum = 0; groupNum < 11; groupNum++) {
            for (int dayNum = 0; dayNum < 6; dayNum++) {
                String weekDay = sheet.getRow(dayNum * 20 + 15 - 1).getCell(0).getStringCellValue();
                for (int subjectNum = 0; subjectNum < 5; subjectNum++) {
                    String hours = sheet.getRow(dayNum * 20 + subjectNum * 4 + 15 - 1).getCell(1).getStringCellValue();
                    /*for (int subGroupNum = 0; subGroupNum < 2; subGroupNum++) {
                    }*/
                    int i = 0;
                }
            }
        }

        workbook.close();
        inputStream.close();

        return scheduleData;
    }

    //TODO: Parse Fourth Course Schedule
    private List<ScheduleData> getFourthCourseSchedule() throws IOException {
        List<ScheduleData> scheduleData = new ArrayList<ScheduleData>();
        return scheduleData;
    }

    private void loadFullSchedule() {
        fullSchedule = new ArrayList<ScheduleData>();
        try
        {
            //fullSchedule.addAll(getFirstCourseSchedule());
            //fullSchedule.addAll(getSecondCourseSchedule());
            fullSchedule.addAll(getThirdCourseSchedule());
            //fullSchedule.addAll(getFourthCourseSchedule());
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<ScheduleData> getAllSchedule() {
        return fullSchedule;
    }

    @Override
    public List<ScheduleData> getScheduleByCourse(int course) {
        return fullSchedule.stream().filter(scheduleData -> scheduleData.getCourse() == course).collect(Collectors.toList());
    }

    @Override
    public List<ScheduleData> getScheduleByGroup(String group) {
        return fullSchedule.stream().filter(scheduleData -> scheduleData.getGroup().equals(group)).collect(Collectors.toList());
    }

    @Override
    public List<Integer> getAllCourses() {
        List<Integer> courses = new ArrayList<Integer>();
        for (ScheduleData data : fullSchedule) {
            if (!courses.contains(data.getCourse())) courses.add(data.getCourse());
        }
        return courses;
    }

    @Override
    public List<String> getGroupsByCourse(int course) {
        List<String> groups = new ArrayList<String>();
        for (ScheduleData data : fullSchedule) {
            if ((data.getCourse() == course) && !groups.contains(data.getGroup())) groups.add(data.getGroup());
        }
        return groups;
    }
}
