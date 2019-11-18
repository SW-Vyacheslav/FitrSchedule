package by.bntu.fitrschedule.services;

import by.bntu.fitrschedule.config.ProjectConfig;
import by.bntu.fitrschedule.domain.dto.ScheduleDtoOut;
import by.bntu.fitrschedule.domain.schedule.*;
import by.bntu.fitrschedule.tools.ApiAssert;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

@Service
public class ScheduleServiceImpl implements ScheduleService {
    private final String DOWNLOAD_FOLDER = "./temp";
    private final String FIRST_COURSE_FILE_NAME = DOWNLOAD_FOLDER + "/course1.xls";
    private final String SECOND_COURSE_FILE_NAME = DOWNLOAD_FOLDER + "/course2.xls";
    private final String THIRD_AND_FOURTH_COURSE_FILE_NAME = DOWNLOAD_FOLDER + "/course3and4.xls";

    @Autowired
    private ProjectConfig projectConfig;

    private static Schedule fullSchedule;

    @PostConstruct
    private void init() {
        if(fullSchedule == null) {
            fullSchedule = new Schedule();
            //downloadAllSchedules();
            loadFullSchedule();
        }
    }

    private void downloadAllSchedules() {
        try {
            if (!Files.exists(Paths.get(DOWNLOAD_FOLDER))) Files.createDirectory(Paths.get(DOWNLOAD_FOLDER));

            InputStream inputStream = null;
            if (!Files.exists(Paths.get(FIRST_COURSE_FILE_NAME))) {
                inputStream = new URL(projectConfig.getFirstCourseScheduleUrl()).openStream();
                Files.copy(inputStream, Paths.get(FIRST_COURSE_FILE_NAME), StandardCopyOption.REPLACE_EXISTING);
                inputStream.close();
            }
            if (!Files.exists(Paths.get(SECOND_COURSE_FILE_NAME))) {
                inputStream = new URL(projectConfig.getSecondCourseScheduleUrl()).openStream();
                Files.copy(inputStream, Paths.get(SECOND_COURSE_FILE_NAME), StandardCopyOption.REPLACE_EXISTING);
                inputStream.close();
            }
            if (!Files.exists(Paths.get(THIRD_AND_FOURTH_COURSE_FILE_NAME))) {
                inputStream = new URL(projectConfig.getThirdAndFourthCourseScheduleUrl()).openStream();
                Files.copy(inputStream, Paths.get(THIRD_AND_FOURTH_COURSE_FILE_NAME), StandardCopyOption.REPLACE_EXISTING);
                inputStream.close();
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private Subgroup getSubgroupFromString(String subgroupData) {
        if (subgroupData == null) return new Subgroup();

        String formattedData = subgroupData.replace('\n', ' ');
        Matcher tutorMatcher = Pattern
                .compile("((проф\\.)|(доц\\.)|(ст\\.пр\\.)|(пр\\.))(.+)[А-Я]\\.(\\s?)[А-Я]\\.")
                .matcher(formattedData);
        Matcher lectureHallMatcher = Pattern
                .compile("а\\.\\d{1,3}[а-я]?")
                .matcher(formattedData);
        Matcher universityHallMatcher = Pattern
                .compile("к\\.\\d{1,2}[а-я]?")
                .matcher(formattedData);

        Subgroup subgroup = new Subgroup();
        if (lectureHallMatcher.find()) {
            subgroup.setLectureHall(formattedData
                    .substring(lectureHallMatcher.start() + 2, lectureHallMatcher.end())
                    .trim());
        }
        if (universityHallMatcher.find()) {
            subgroup.setUniversityHall(formattedData
                    .substring(universityHallMatcher.start() + 2, universityHallMatcher.end())
                    .trim());
        }
        if (tutorMatcher.find()) {
            subgroup.setTutor(formattedData
                    .substring(tutorMatcher.start(), tutorMatcher.end())
                    .trim());
        }
        subgroup.setSubject(formattedData
                .replace(subgroup.getTutor(), "")
                .replace(subgroup.getLectureHall(), "")
                .replace(subgroup.getUniversityHall(), "")
                .replace("а.", "")
                .replace("к.", "")
                .trim());

        return subgroup;
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
        final int WEEKDAY_ROW = 15;
        final int WEEKDAY_CELL = 1;
        final int HOURS_CELL = 2;
        final int CELLS_PER_GROUP = 4;
        final int CELLS_PER_SUBJECT = 4;
        final int CELLS_PER_SUBGROUP = 2;
        final int ROWS_PER_WEEKDAY = 20;
        final int ROWS_PER_WEEK = 2;

        Schedule schedule = new Schedule();
        FileInputStream inputStream = new FileInputStream(THIRD_AND_FOURTH_COURSE_FILE_NAME);
        Workbook workbook = new HSSFWorkbook(inputStream);
        Sheet sheet = workbook.getSheetAt(2);

        Course course = new Course();
        course.setCourseNumber(3);
        //TODO: Parse Excel File
        for (int groupNum = 0; groupNum < NUMBER_OF_GROUPS; ++groupNum) {
            Group group = new Group();
            group.setName(getStringValueFromSheet(GROUP_ROW - 1, groupNum * CELLS_PER_GROUP + GROUP_CELL - 1, sheet));
            for (int dayNum = 0; dayNum < NUMBER_OF_WEEKDAYS; ++dayNum) {
                WeekDay weekDay = new WeekDay();
                weekDay.setName(getStringValueFromSheet(dayNum * ROWS_PER_WEEKDAY + WEEKDAY_ROW - 1, WEEKDAY_CELL - 1, sheet));
                for (int subjectNum = 0; subjectNum < 5; ++subjectNum) {
                    Pair pair = new Pair();
                    pair.setHours(getStringValueFromSheet(dayNum * ROWS_PER_WEEKDAY + subjectNum * CELLS_PER_SUBJECT + WEEKDAY_ROW - 1, HOURS_CELL - 1, sheet));
                    for (int weekNum = 0; weekNum < 2; ++weekNum) {
                        Week week = new Week();
                        week.setWeekNumber(weekNum + 1);
                        for (int subgroupNum = 0; subgroupNum < 2; ++subgroupNum) {
                            String subgroupData = getStringValueFromSheet(
                                    dayNum * ROWS_PER_WEEKDAY + subjectNum * CELLS_PER_SUBJECT + weekNum * ROWS_PER_WEEK + WEEKDAY_ROW - 1,
                                    groupNum * CELLS_PER_GROUP + subgroupNum * CELLS_PER_SUBGROUP + GROUP_CELL - 1,
                                    sheet);
                            Subgroup subgroup = getSubgroupFromString(subgroupData);
                            subgroup.setSubgroupNumber(subgroupNum + 1);
                            week.addSubgroup(subgroup);
                        }
                        pair.addWeek(week);
                    }
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
                value = Integer.toString((int)cell.getNumericCellValue());
                break;
            default:
                break;
        }

        return value;
    }

    private void loadFullSchedule() {
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
        return new ScheduleDtoOut(fullSchedule);
    }

    @Override
    public ScheduleDtoOut getScheduleByCourse(int course) {
        Schedule schedule = new Schedule();
        List<Course> courses = fullSchedule.getCourses().stream()
                .filter(c -> c.getCourseNumber() == course)
                .collect(Collectors.toList());
        ApiAssert.notFound(courses.isEmpty());
        schedule.getCourses().addAll(courses);

        return new ScheduleDtoOut(schedule);
    }

    @Override
    public ScheduleDtoOut getScheduleByCourseAndGroup(int course, String group) {
        Schedule schedule = new Schedule();
        List<Group> groups = fullSchedule.getCourses().stream()
                .filter(c -> c.getCourseNumber() == course)
                .flatMap(c -> c.getGroups().stream()
                        .filter(g -> g.getName().equals(group)))
                .collect(Collectors.toList());
        ApiAssert.notFound(groups.isEmpty());
        Course c = new Course();
        c.setCourseNumber(course);
        c.getGroups().addAll(groups);
        schedule.addCourse(c);

        return new ScheduleDtoOut(schedule);
    }

    @Override
    public List<Integer> getAllCourses() {
        return fullSchedule.getCourses().stream()
                .map(Course::getCourseNumber)
                .collect(Collectors.toList());
    }

    @Override
    public List<String> getGroupsByCourse(int course) {
        return fullSchedule.getCourses().stream()
                .filter(c -> c.getCourseNumber() == course)
                .flatMap(c -> c.getGroups().stream()
                        .map(Group::getName))
                .collect(Collectors.toList());
    }
}
