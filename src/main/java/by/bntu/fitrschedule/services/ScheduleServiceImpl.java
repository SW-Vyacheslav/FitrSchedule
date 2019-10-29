package by.bntu.fitrschedule.services;

import by.bntu.fitrschedule.config.ProjectConfig;
import by.bntu.fitrschedule.domain.dto.ScheduleDtoOut;
import by.bntu.fitrschedule.domain.schedule.*;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.io.*;
import java.net.URL;
import java.nio.file.*;
import java.util.HashSet;
import java.util.Set;

@Service
public class ScheduleServiceImpl implements ScheduleService {

    @Autowired
    private ProjectConfig projectConfig;

    private static Schedule fullSchedule;

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
            if (!Files.exists(Paths.get("./temp/course2.xls"))) {
                inputStream = new URL(projectConfig.getSecondCourseScheduleUrl()).openStream();
                Files.copy(inputStream, Paths.get("./temp/course2.xls"), StandardCopyOption.REPLACE_EXISTING);
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

    private Schedule getFirstCourseSchedule() throws IOException {
        Schedule schedule = new Schedule();
        return schedule;
    }

    private Schedule getSecondCourseSchedule() throws IOException {
        Schedule schedule = new Schedule();
        return schedule;
    }

    private Schedule getThirdCourseSchedule() throws IOException {
        final int NUMBER_OF_GROUPS = 11;
        final int NUMBER_OF_WEEKDAYS = 6;
        final int GROUP_ROW = 13;
        final int GROUP_CELL = 3;
        final int CELLS_PER_GROUP = 4;
        final int WEEKDAY_ROW = 15;
        final int ROWS_PER_WEEKDAY = 20;
        final int WEEKDAY_CELL = 1;
        final int HOURS_CELL = 2;
        final int CELLS_PER_HOURS = 4;

        Schedule schedule = new Schedule();
        FileInputStream inputStream = new FileInputStream("./temp/course3and4.xls");
        Workbook workbook = new HSSFWorkbook(inputStream);
        Sheet sheet = workbook.getSheetAt(2);

        Course course = new Course();
        //TODO: Parse Excel File
        for (int groupNum = 0; groupNum < NUMBER_OF_GROUPS; groupNum++) {
            Group group = new Group();
            group.setName(getStringValueFromSheet(GROUP_ROW - 1, groupNum * CELLS_PER_GROUP + GROUP_CELL - 1, sheet));
            for (int dayNum = 0; dayNum < NUMBER_OF_WEEKDAYS; dayNum++) {
                WeekDay weekDay = new WeekDay();
                weekDay.setName(getStringValueFromSheet(dayNum * ROWS_PER_WEEKDAY + WEEKDAY_ROW - 1, WEEKDAY_CELL - 1, sheet));
                for (int subjectNum = 0; subjectNum < 5; subjectNum++) {
                    Pair pair = new Pair();
                    pair.setHours(getStringValueFromSheet(dayNum * ROWS_PER_WEEKDAY + subjectNum * CELLS_PER_HOURS + WEEKDAY_ROW - 1, HOURS_CELL - 1, sheet));
                    weekDay.addPair(pair);
                }
                group.addWeekDay(weekDay);
            }
            course.addGroup(group);
        }
        schedule.addCourse(course);

        workbook.close();
        inputStream.close();

        return schedule;
    }

    private Schedule getFourthCourseSchedule() throws IOException {
        Schedule schedule = new Schedule();
        return schedule;
    }

    private String getStringValueFromSheet(int rowPos, int cellPos, Sheet sheet) {
        String value = null;

        Cell cell = sheet.getRow(rowPos).getCell(cellPos);
        switch (cell.getCellTypeEnum()) {
            case STRING:
                value = cell.getStringCellValue();
                break;
            case NUMERIC:
                value = Double.toString(cell.getNumericCellValue());
                break;
            default:
                break;
        }

        return value;
    }

    private void loadFullSchedule() {
        fullSchedule = new Schedule();
        try
        {
            fullSchedule.getCourses().addAll(getThirdCourseSchedule().getCourses());
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public ScheduleDtoOut getAllSchedule() {
        ScheduleDtoOut scheduleDtoOut = new ScheduleDtoOut();
        return scheduleDtoOut;
    }

    @Override
    public ScheduleDtoOut getScheduleByCourse(int course) {
        ScheduleDtoOut scheduleDtoOut = new ScheduleDtoOut();
        return scheduleDtoOut;
    }

    @Override
    public ScheduleDtoOut getScheduleByGroup(String group) {
        ScheduleDtoOut scheduleDtoOut = new ScheduleDtoOut();
        return scheduleDtoOut;
    }

    @Override
    public Set<Long> getAllCourses() {
        Set<Long> courses = new HashSet<>();
        return courses;
    }

    @Override
    public Set<String> getGroupsByCourse(int course) {
        Set<String> groups = new HashSet<>();
        return groups;
    }
}
