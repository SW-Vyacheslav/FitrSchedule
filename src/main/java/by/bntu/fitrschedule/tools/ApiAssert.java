package by.bntu.fitrschedule.tools;

import by.bntu.fitrschedule.tools.exceptions.NotFoundException;

public class ApiAssert {
    public static void notFound(boolean isThrow) {
        if (isThrow) throw new NotFoundException();
    }

    public static void notFound(boolean isThrow, String error) {
        if (isThrow) throw new NotFoundException(error);
    }
}
